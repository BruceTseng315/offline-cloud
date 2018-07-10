package com.turingmaker.service.school.api;

import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.operation.response.StudentListResponse;
import com.turingmaker.service.school.bo.StudentAddRequest;
import com.turingmaker.service.school.bo.StudentBo;
import com.turingmaker.service.school.request.StudentEditRequest;
import com.turingmaker.service.school.request.StudentListRequest;

import java.util.List;

public interface StudentService {
    /**
     * 学生详情
     * @param studentId
     * @return
     */
    StudentBo getStudentByStudentId(long studentId);

    /**
     * 编辑学生信息
     * @param studentEditRequest
     */
    void editStudent(StudentEditRequest studentEditRequest);

    /**
     * 获取指定学校学生详情列表
     * @param schoolId
     * @return
     */
    StudentListResponse getStudentDetailsList(long schoolId, StudentListRequest studentListRequest);

    /**
     * 新增学生
     * @param studentAddRequest
     * @return 学生信息
     */
    StudentBo addStudent(StudentAddRequest studentAddRequest);

    /**
     * 把指定学生移出班级
     * @param classId 班级id
     * @param studentId 学生id
     */

    void deleteStudentFromClass(long classId,long studentId);

    /**
     * 获取指定班级学生详情列表
     * @param classId
     * @return
     */
    List<StudentBo> getStudentListBuClassId(Pageinfo pageinfo,long classId);


}
