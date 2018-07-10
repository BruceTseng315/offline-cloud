package com.turingmaker.service.schoolManage.impl;

import com.turingmaker.config.Constant;
import com.turingmaker.dao.mapper.operation.ext.CourseMapperExt;
import com.turingmaker.dao.mapper.operation.ext.LessonMapperExt;
import com.turingmaker.dao.mapper.school.ext.*;
import com.turingmaker.dao.mapper.teachter.TClassLessonMapper;
import com.turingmaker.dao.mapper.teachter.ext.TClassLessonMapperExt;
import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.school.*;
import com.turingmaker.entity.teachter.TClassLesson;
import com.turingmaker.service.api.DataimportService;
import com.turingmaker.service.operation.api.DataMode;
import com.turingmaker.service.operation.api.LessonService;
import com.turingmaker.service.operation.request.AddClassRequest;
import com.turingmaker.service.operation.request.ClassListRequest;
import com.turingmaker.service.operation.response.ClassListResponse;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.school.api.ClassService;
import com.turingmaker.service.school.api.StudentService;
import com.turingmaker.service.school.bo.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.*;


@Service
@Transactional
public class ClassServiceImpl implements ClassService {
    @Autowired
    TClassMapperExt classMapperExt;
    @Autowired
    TClassStudentMapperExt classStudentMapperExt;
    @Autowired
    TClassCourseTeacherMapperExt classCourseTeacherMapperExt;
    @Autowired
    TTeacherMapperExt tTeacherMapper;
    @Autowired
    CourseMapperExt courseMapper;
    @Autowired
    DataimportService dataimportService;
    @Autowired
    TStudentMapperExt studentMapperExt;
    @Autowired
    StudentService studentService;

    @Autowired
    TClassLessonMapperExt classLessonMapper;

    @Autowired
    LessonMapperExt lessonMapperExt;

    Logger logger= LoggerFactory.getLogger(getClass());
    /**
     * 新增班级
     * @param schoolId
     * @param addClassRequest
     * @return
     */
    @Override
    public ClassBo addClass(long schoolId, AddClassRequest addClassRequest) {
        TClass tClass = new TClass();
        tClass.setClassName(addClassRequest.getClassName());
        tClass.setClassType(addClassRequest.getClassType());
        tClass.setGrade(addClassRequest.getGrade());
        tClass.setSchoolId(schoolId);
        classMapperExt.insertSelective(tClass);
        tClass.setClassCode(String.valueOf(tClass.getId()));
        classMapperExt.updateByPrimaryKeySelective(tClass);

        List<ClassCourseBo> classCourseBos = addClassRequest.getCourses();
        if(classCourseBos != null && classCourseBos.size() > 0) {
            for(ClassCourseBo classCourseBo:classCourseBos) {
                if(classCourseBo != null) {
                    //新增班级课程记录
                    TClassCourseTeacher classCourseTeacher = new TClassCourseTeacher();
                    classCourseTeacher.setTeacherId(classCourseBo.getTeacherId());
                    classCourseTeacher.setCourseId(classCourseBo.getCourseId());
                    classCourseTeacher.setClassId(tClass.getId());
                    classCourseTeacher.setState((byte)1);
                    classCourseTeacherMapperExt.insertSelective(classCourseTeacher);
                }
            }
        }

        ClassBo classBo = new ClassBo();
        classBo.setByClass(tClass);

        return classBo;
    }

    /**
     * 获取指定学校班级详情列表
     * @param schoolId
     * @param classListRequest
     * @return
     */
    @Override
    public ClassListResponse getClassDetailsList(long schoolId, ClassListRequest classListRequest) {
        ClassListResponse classListResponse = new ClassListResponse();

        Pageinfo pageinfo = new Pageinfo();
        pageinfo.setPageSize(classListRequest.getPageSize());
        pageinfo.setPageNo(classListRequest.getPageNo());

        List<ClassBo> classBos = classMapperExt.selectClassListPage(pageinfo,schoolId,classListRequest);
        classListResponse.setClasses(classBos);
        classListResponse.setPageinfo(pageinfo);

        return classListResponse;
    }

    /**
     * 班级课程开课
     * @param classId
     * @param courseId
     */
    @Override
    public void classCourseOpen(long classId, long courseId) {
        TClassCourseTeacher classCourseTeacher = classCourseTeacherMapperExt.selectByClassIdAndCourseId(classId,courseId);
        classCourseTeacher.setState((byte)1);
        classCourseTeacherMapperExt.updateByPrimaryKeySelective(classCourseTeacher);
    }

    /**
     * 班级课程停课
     * @param classId
     * @param courseId
     */
    @Override
    public void classCourseClose(long classId, long courseId) {
        TClassCourseTeacher classCourseTeacher = classCourseTeacherMapperExt.selectByClassIdAndCourseId(classId,courseId);
        classCourseTeacher.setState((byte)0);
        classCourseTeacherMapperExt.updateByPrimaryKeySelective(classCourseTeacher);
    }

    /**
     * 编辑班级
     * @param classEditRequest
     */
    @Override
    public void editClass(ClassEditRequest classEditRequest) {
        Long classId = classEditRequest.getClassId();
        //更新班级基本信息 班级名称和年级
        TClass tClass = classMapperExt.selectByPrimaryKey(classId);
        tClass.setClassName(classEditRequest.getClassName());
        tClass.setGrade(classEditRequest.getGrade());
        if(classEditRequest.getClassType() != null){
            tClass.setClassType(classEditRequest.getClassType());
        }
        classMapperExt.updateByPrimaryKeySelective(tClass);

        //更新班级课程 修改或新增
        List<ClassCourseBo> classCourseBoList = classEditRequest.getCourses();
        if(classCourseBoList != null && classCourseBoList.size() > 0) {
            for (ClassCourseBo classCourseBo : classCourseBoList) {
                if(classCourseBo == null){
                    continue;
                }
                long courseId = classCourseBo.getCourseId();
                logger.info("classId:" + classId + "/n" + "courseId:" + courseId);
                TClassCourseTeacher classCourseTeacher = classCourseTeacherMapperExt.selectByClassIdAndCourseId(classId, courseId);
                //新增课程
                if (classCourseTeacher == null) {
                    classCourseTeacher = new TClassCourseTeacher();
                    classCourseTeacher.setClassId(classId);
                    classCourseTeacher.setCourseId(classCourseBo.getCourseId());
                    classCourseTeacher.setTeacherId(classCourseBo.getTeacherId());
                    classCourseTeacher.setState(classCourseBo.getState());
                    classCourseTeacherMapperExt.insertSelective(classCourseTeacher);
                } else {
                    //更新班级课程信息 老师和状态
                    classCourseTeacher.setTeacherId(classCourseBo.getTeacherId());
                    classCourseTeacher.setState(classCourseBo.getState());
                    classCourseTeacherMapperExt.updateByPrimaryKeySelective(classCourseTeacher);
                }
            }
        }

    }

    /**
     * 导入学生信息
     * @param classId 要导入的班级id
     * @param is 学生信息Excell文件
     */
    @Override
    public void importStudents(long classId, InputStream is) {
        DataMode<StudentAddRequest> dataMode = new DataMode<>();
        dataMode.setHeadLine(0);
        dataMode.setDataStart(1);
        dataMode.setDataClass(StudentAddRequest.class);

        //Excell模板表头字段与TStudent属性字段映射表
        Map<String,String> map = new HashMap<>();
        map.put("姓名","studentName");
        map.put("学号","studentNo");
        map.put("身份证号","identifier");
        map.put("监护人","guarderName");
        map.put("监护人手机号","guarderPhone");
        dataMode.setColumnandFieldNameMap(map);

        List<StudentAddRequest> studentList = dataimportService.importExcel(is,dataMode);
        if(logger.isDebugEnabled()) {
            logger.info("导入学生数量："+studentList.size());
        }
        //新增学生
        for(StudentAddRequest studentAddRequest:studentList) {
            studentAddRequest.setClassId(classId);
            studentService.addStudent(studentAddRequest);
            if(logger.isDebugEnabled()){
                logger.info("导入学生："+studentAddRequest.toString());
            }
        }


    }

    /**
     * 导入班级
     * @param is
     */
    @Override
    public void importClasses(long schoolId,byte classType,InputStream is) {
        DataMode<AddClassRequest> dataMode = new DataMode<>();
        dataMode.setHeadLine(0);
        dataMode.setDataStart(1);
        dataMode.setDataClass(AddClassRequest.class);

        logger.info("classType="+classType);
        System.out.println("classType="+classType);
        if(classType == 0) {
            logger.info("班级类型："+classType);
            //Excell模板表头字段与AddClassRequest属性字段映射表
            Map<String, String> map = new HashMap<>();
            map.put("班级名称", "className");
            map.put("年级", "grade");
            dataMode.setColumnandFieldNameMap(map);
        }else if(classType == 1) {
            logger.info("班级类型："+classType);
            //Excell模板表头字段与AddClassRequest属性字段映射表
            Map<String, String> map = new HashMap<>();
            map.put("班级名称", "className");
            dataMode.setColumnandFieldNameMap(map);
        }

        List<AddClassRequest> addClassRequestList = dataimportService.importExcel(is,dataMode);
        if(logger.isDebugEnabled()) {
            logger.info("导入班级数量："+addClassRequestList.size());
        }
        //新增班级
        for(AddClassRequest addClassRequest:addClassRequestList) {
            TClass tClass = new TClass();
            tClass.setGrade(addClassRequest.getGrade());
            tClass.setClassName(addClassRequest.getClassName());
            tClass.setClassType(classType);
            tClass.setSchoolId(schoolId);
            classMapperExt.insertSelective(tClass);
            //班级编号 前缀+schoolId（5位）+classId（3位）
            long classId = tClass.getId();
            String classCode = Constant.CLASS_CODE_PREFOX + String.format("%05d",schoolId) + String.format("%03d",classId);
            System.out.println("班级编号："+classCode);
            tClass.setClassCode(classCode);
            classMapperExt.updateByPrimaryKeySelective(tClass);
        }

    }

    /**
     * 获取指定学校所有班级
     * @param schoolId
     * @return
     */
    @Override
    public List<TClass> getAllClassBySchoolId(long schoolId) {
        List<TClass> result = classMapperExt.selectClassesBySchoolId(schoolId);
        return result;
    }

    @Override
    public ClassDetailResponse getClassDetailsByClassId(Pageinfo pageinfo,long classId) {
        ClassDetailResponse classDetailResponse = new ClassDetailResponse();

        TClass tClass = classMapperExt.selectByPrimaryKey(classId);
        ClassBo classBo = new ClassBo();
        //班级基本信息
        classBo.setByClass(tClass);
        TClassStudent classStudent = new TClassStudent();
        classStudent.setClassId(tClass.getId());
        //班级学生数量
        int studentCount = classStudentMapperExt.selectForCount(classStudent);
        classBo.setStudentCount(studentCount);
        //班级开设的课程
        List<TClassCourseTeacher> classCourseTeacherList = classCourseTeacherMapperExt.selectByClassId(tClass.getId());
        List<ClassCourseBo> classCourseBoList = new ArrayList<>();
        for(TClassCourseTeacher classCourseTeacher:classCourseTeacherList){
            ClassCourseBo classCourseBo = new ClassCourseBo();
            //教师信息
            TTeacher tTeacher = tTeacherMapper.selectByPrimaryKey(classCourseTeacher.getTeacherId());
            classCourseBo.setTeacherId(tTeacher.getId());
            classCourseBo.setTeacherName(tTeacher.getTeacherName());
            //课程信息
            TCourse course = courseMapper.selectByPrimaryKey(classCourseTeacher.getCourseId());
            classCourseBo.setCourseId(course.getId());
            classCourseBo.setCourseName(course.getCourseName());
            classCourseBo.setClassId(classCourseTeacher.getClassId());
            //课程状态
            classCourseBo.setStatus(classCourseTeacher.getState());

            classCourseBoList.add(classCourseBo);
        }
        classBo.setCourses(classCourseBoList);
        classDetailResponse.setClassBo(classBo);

        List<StudentBo> studentBos = studentService.getStudentListBuClassId(pageinfo,classId);
        StudentsResponse studentsResponse = new StudentsResponse();
        studentsResponse.setPageinfo(pageinfo);
        studentsResponse.setStudentBoList(studentBos);
        classDetailResponse.setStudents(studentsResponse);


        return classDetailResponse;
    }

    /**
     * 获取班级可添加课程
     * @param schoolId
     * @param classId
     * @return
     */
    @Override
    public List<TCourse> getClassAddableCourses(long schoolId, long classId) {
        List<TCourse> courseList = classMapperExt.getAddableCourses(schoolId,classId);
        return courseList;
    }


    /**
     * 获取表格重复姓名的学生
     * @param studentList
     * @return
     */
    private List<List<TStudent>> getRepeatNameInTable(List<TStudent> studentList) {
        List<List<TStudent>> result = new ArrayList<>();
        Map<String,List<TStudent>> map = new HashMap<>();
        for(TStudent student:studentList) {
            String studentName = student.getStudentName();
            if(map.containsKey(studentName)) {
                map.get(studentName).add(student);
            }else{
                List<TStudent> students = new ArrayList<>();
                students.add(student);
                map.put(studentName,students);
            }
        }
       Iterator<List<TStudent>> it = map.values().iterator();
        while(it.hasNext()) {
            List<TStudent> list = it.next();
            if(list.size() > 1) {
                result.add(list);
            }
        }

        return result;
    }

    /**
     * 获取学校里已经存在的学生姓名
     * @param schoolId
     * @param studentList
     * @return
     */
    private List<List<TStudent>> getRepeatNameInSchool(long schoolId,List<TStudent> studentList) {
        List<List<TStudent>> lists = new ArrayList<>();
        for(TStudent student:studentList) {
            String studentName = student.getStudentName();
            List<TStudent> students = studentMapperExt.findStudentsBySchoolIdAndStudentName(schoolId,studentName);
            if(students != null && students.size() > 0) {
                lists.add(students);
            }
        }

        return lists;

    }

    @Override
    public List<TClass> getClassByCourseIdAndTeacherId(long courseId,long teacherId){
        List<TClass> classes = classMapperExt.getClassByTeacherIdAndCourseId(teacherId,courseId);
        return classes;
    }

    /**
     * 班级课时推送
     *
     */
    @Override
    public  void pushByLessonIdAndClassId(long classId ,long lessonId){
        TClassLesson tClassLesson = classLessonMapper.getByClassIdAndLessonId(classId,lessonId);
        if(tClassLesson==null) {
            TClassLesson classLesson = new TClassLesson();
            classLesson.setClassId(classId);
            classLesson.setLessonId(lessonId);
            classLesson.setCourseId(lessonMapperExt.selectByPrimaryKey(lessonId).getCourseId());
            classLesson.setLessonSection(lessonMapperExt.selectByPrimaryKey(lessonId).getLessonSection());
            classLessonMapper.insertSelective(classLesson);
        }
    }

}
