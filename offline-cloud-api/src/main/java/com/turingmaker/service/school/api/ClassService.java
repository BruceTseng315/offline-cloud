package com.turingmaker.service.school.api;

import com.turingmaker.entity.operation.TCourse;
import com.turingmaker.entity.school.TClass;
import com.turingmaker.service.operation.request.AddClassRequest;
import com.turingmaker.service.operation.request.ClassListRequest;
import com.turingmaker.service.operation.response.ClassListResponse;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.school.bo.ClassBo;
import com.turingmaker.service.school.bo.ClassDetailResponse;
import com.turingmaker.service.school.bo.ClassEditRequest;

import java.io.InputStream;
import java.util.List;

public interface ClassService {

    /**
     * 新增班级
     * @param schoolId
     * @param addClassRequest
     * @return
     */
    ClassBo addClass(long schoolId,AddClassRequest addClassRequest);

    /**
     * 获取指定学校班级详情列表
     * @param schoolId
     * @param classListRequest
     * @return
     */
    ClassListResponse getClassDetailsList(long schoolId, ClassListRequest classListRequest);

    /**
     * 班级课程开课
     * @param classId
     * @param courseId
     */
    void  classCourseOpen(long classId,long courseId);

    /**
     * 班级课程停课
     * @param classId
     * @param courseId
     */
    void classCourseClose(long classId,long courseId);

    /**
     * 编辑班级
     * @param classEditRequest
     */
    void  editClass(ClassEditRequest classEditRequest);

    /**
     * 指定班级导入学生
     * @param classId 要导入的班级id
     * @param is 学生信息Excell文件
     */
    void importStudents(long classId, InputStream is);

    /**
     *
     * @param schoolId 学校id
     * @param classType 班级类型 0：常规 1：兴趣班
     * @param is
     * @Param version Excel文件版本 0:xlx 1:xlsx
     */
    void importClasses(long schoolId,byte classType,InputStream is);

    /**
     * 获取指定学校所有班级
     * @param schoolId
     * @return
     */
    List<TClass> getAllClassBySchoolId(long schoolId);

    /**
     * 获取指定班级详情
     * @param classId
     * @return
     */
    ClassDetailResponse getClassDetailsByClassId(Pageinfo pageinfo,long classId);

    /**
     * 获取班级可添加课程
     * @param schoolId
     * @param classId
     * @return
     */
    List<TCourse> getClassAddableCourses(long schoolId,long classId);

    /**
     * 老师-课程的授课班级列表
     * @param courseId,teacherId
     * @return
     */
    List<TClass> getClassByCourseIdAndTeacherId(long courseId,long teacherId);

    void pushByLessonIdAndClassId(long classId ,long lessonId);

}
