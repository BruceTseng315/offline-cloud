package com.turingmaker.service.schoolManage.impl;


import java.util.ArrayList;
import java.util.List;

import com.turingmaker.common.config.Constant;
import com.turingmaker.service.school.bo.ClassBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.dao.mapper.school.ext.TClassMapperExt;
import com.turingmaker.dao.mapper.school.ext.TClassStudentMapperExt;
import com.turingmaker.dao.mapper.school.ext.TStudentMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.entity.school.TClassStudent;
import com.turingmaker.entity.school.TStudent;
import com.turingmaker.service.api.AuthService;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.operation.response.StudentListResponse;
import com.turingmaker.service.school.api.StudentService;
import com.turingmaker.service.school.bo.StudentAddRequest;
import com.turingmaker.service.school.bo.StudentBo;
import com.turingmaker.service.school.request.StudentEditRequest;
import com.turingmaker.service.school.request.StudentListRequest;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    TStudentMapperExt studentMapper;
    @Autowired
    UserMapperExt userMapper;
    @Autowired
    TClassStudentMapperExt classStudentMapperExt;
    @Autowired
    TClassMapperExt classMapper;
    @Autowired
    AuthService authService;

    /**
     * 获取学生详情
     * @param studentId
     * @return
     */
    @Override
    public StudentBo getStudentByStudentId(long studentId) {
        TStudent student = studentMapper.selectByPrimaryKey(studentId);
        List<TClassStudent> classStudents = classStudentMapperExt.selectByStudentId(studentId);

        long accountId = student.getAccountId();
        YUser user = userMapper.selectByPrimaryKey(accountId);
        StudentBo studentBo = new StudentBo();
        studentBo.setAccountName(user.getAccount());
        studentBo.setAccountId(accountId);
        //获取所属班级名称，一个学生对应多个班级
        List<TClass> classes = new ArrayList<>();
        for(TClassStudent classStudent:classStudents){
            TClass tClass = classMapper.selectByPrimaryKey(classStudent.getClassId());
            classes.add(tClass);
        }
        studentBo.setClasses(classes);
        studentBo.setGuarderPhone(student.getGuarderPhone());
        studentBo.setGuarderName(student.getGuarderName());
        studentBo.setPassword(user.getPassword());
        studentBo.setStudentCode(student.getStudentCode());
        studentBo.setStudentId(student.getId());
        studentBo.setStudentName(student.getStudentName());
        studentBo.setStudentNo(student.getStudentNumber());
        studentBo.setIdentifier(student.getIdentifier());

        return studentBo;
    }

    /**
     * 编辑学生信息
     * @param studentEditRequest
     */

    @Override
    public void editStudent(StudentEditRequest studentEditRequest) {
        Long studentId = studentEditRequest.getStudentId();
        TStudent student = studentMapper.selectByPrimaryKey(studentId);
        String studentName = studentEditRequest.getStudentName();
        if(studentName != null){
            student.setStudentName(studentName);
        }
        String studentNo = studentEditRequest.getStudentNo();
        if(studentNo != null){
            student.setStudentNumber(studentNo);
        }
        String identifier = studentEditRequest.getIdentifier();
        if(identifier != null) {
            student.setIdentifier(identifier);
        }
        String guarderName = studentEditRequest.getGuarderName();
        if(guarderName != null) {
            student.setGuarderName(guarderName);
        }
        String guarderPhone = studentEditRequest.getGuarderPhone();
        if(guarderPhone != null) {
            student.setGuarderPhone(guarderPhone);
        }
        Boolean passwordRefreah = studentEditRequest.getPasswordRefresh();
        if(passwordRefreah != null && passwordRefreah==true) {
            YUser user = userMapper.selectByPrimaryKey(student.getAccountId());
            user.setPassword(Constant.DEFAULT_STUDENT_PASSWORD);
            userMapper.updateByPrimaryKeySelective(user);
        }
        studentMapper.updateByPrimaryKeySelective(student);

    }

    /**
     * 获取学生详情列表
     * @param schoolId
     * @param studentListRequest
     * @return
     */
    @Override
    public StudentListResponse getStudentDetailsList(long schoolId, StudentListRequest studentListRequest) {
        StudentListResponse studentListResponse = new StudentListResponse();

       // List<StudentBo> studentBos = new ArrayList<>();

        //分页
        Pageinfo pageinfo = new Pageinfo();
        pageinfo.setPageNo(studentListRequest.getPageNo());
        pageinfo.setPageSize(studentListRequest.getPageSize());
/*
        if(studentListRequest.getClassName() != null) {
            studentListRequest.setClassName("%" + studentListRequest.getClassName() + "%");
        }
        List<TStudent> students = studentMapper.findStudentsListBySchoolIdPage(pageinfo,schoolId,studentListRequest);
        for(TStudent student:students){
            StudentBo studentBo = this.getStudentByStudentId(student.getId());

            studentBos.add(studentBo);
        }
*/
        List<StudentBo> studentBos = studentMapper.findStudentListBySchoolNewPage(pageinfo,schoolId,studentListRequest);
        studentListResponse.setPageinfo(pageinfo);
        studentListResponse.setStudentBoList(studentBos);

        return studentListResponse;
    }

    /**
     * 新增学生 增加学生用户student，增加class_student，增加account
     * @param studentAddRequest
     * @return
     */
    @Override
    @Transactional
    public StudentBo addStudent(StudentAddRequest studentAddRequest) {
        TStudent student = new TStudent();
        TClass tClass = classMapper.selectByPrimaryKey(studentAddRequest.getClassId());
        //新增学生端账号记录
        String accountName = authService.generateAccount(3);
        YUser user = userMapper.findUserByUserName(accountName);
        //新增学生记录
        student.setGuarderPhone(studentAddRequest.getGuarderPhone());
        student.setGuarderName(studentAddRequest.getGuarderName());
        student.setIdentifier(studentAddRequest.getIdentifier());
        student.setStudentNumber(studentAddRequest.getStudentNo());
        student.setStudentName(studentAddRequest.getStudentName());
        student.setAccountId(user.getId());

        student.setStudentCode(String.valueOf(user.getId()));

        student.setSchoolId(tClass.getSchoolId());
        studentMapper.insertSelective(student);
//        student.setStudentCode(String.valueOf(student.getId()));
//        studentMapper.updateByPrimaryKeySelective(student);

        //班级里新增一条学生记录
        TClassStudent classStudent = new TClassStudent();
        classStudent.setStudentId(student.getId());
        classStudent.setClassId(studentAddRequest.getClassId());
        classStudentMapperExt.insertSelective(classStudent);

        //返回学生基本信息
        StudentBo studentBo = new StudentBo();
        studentBo.setStudent(student);
        //返回学生账号信息
        studentBo.setAccount(user);
        //返回学生所在班级
        List<TClass> classes = classMapper.selectByStudentId(student.getId());
        studentBo.setClasses(classes);

        return studentBo;
    }

    /**
     * 把指定学生移出班级
     * @param classId 班级id
     * @param studentId 学生id
     */
    @Override
    public void deleteStudentFromClass(long classId, long studentId) {
        classStudentMapperExt.deleteByStudentIdAndClassId(studentId,classId);
    }

    @Override
    public List<StudentBo> getStudentListBuClassId(Pageinfo pageinfo,long classId) {
        List<TStudent> students = studentMapper.findStudentListByClassIdPage(pageinfo,classId);
        List<StudentBo> studentBos = new ArrayList<>();
        if(students != null && students.size() > 0){
            for(TStudent student:students) {
                StudentBo studentBo = this.getStudentByStudentId(student.getId());
                studentBos.add(studentBo);
            }
        }
        return studentBos;
    }
}
