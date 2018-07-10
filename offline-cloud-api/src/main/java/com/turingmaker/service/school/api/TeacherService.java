package com.turingmaker.service.school.api;

import java.io.InputStream;
import java.util.List;

import com.turingmaker.entity.operation.YUser;
import com.turingmaker.service.operation.response.TeacherListResponse;
import com.turingmaker.service.operation.response.TeacherResponse;
import com.turingmaker.service.school.bo.ExportTeacherResponse;
import com.turingmaker.service.teachter.request.UpdatePwdRequest;

public interface TeacherService {

    /**
    * @Description:新增加教师
    * @Param:
    * @return:账号信息
    * @Date: 2018/6/21
    */
    YUser addTeacher(Long schoolId , String teacherName , String phone) ;

    /**
    * @Description: 获得一个学校所有老师
    * @Param: [schoolId]
    * @return: java.util.List<com.turingmaker.entity.school.TTeacher>
    * @Date: 2018/6/21
    */
    TeacherResponse findAllTeacherBySchoolId(Long schoolId , Integer pageNo , Integer pageSize) ;

    /**
    * @Description:  更新老师信息
    * @Param: [teacherId, teacherName, phone, refresh]
    * @return: void
    * @Date: 2018/6/21
    */
    void updateInfo(Long teacherId ,String teacherName ,String phone ,Integer refresh);

    
    
  
    /**
     *按条件查询老师信息
     *
     */
  //  TeacherListResponse getTeacherList(Long schoolId,String teacherCode,String teacherName,String className,Long courseId,Integer pageNo,Integer pageSize);

    
    
    
    
    /**
     * 按条件查询老师信息
     * @param teacherCode
     * @param teacherName
     * @param classId
     * @param courseCode
     * @param pageNo
     * @param pageSize
     * @return
     */
 //   TeacherListResponse getTeacherListNew(String teacherCode,String teacherName,Long classId,String courseCode,Integer pageNo,Integer pageSize);


    /**
     * 导入教师
     * @param schoolId
     * @param is
     */
    void importTeachers(long schoolId,InputStream is);

    /**
     * 导出教师信息
     * @param teacherCode
     * @param teacherName
     * @param className
     * @param courseId
     * @return
     */
    List<ExportTeacherResponse> getTeacherForExport(Long schoolId, String teacherCode, String teacherName, String className, Long courseId);

    
    TeacherListResponse getTeacherListAssPage(Long schoolId, String teacherCode, String teacherName, String className, Long courseId,Integer pageNo,Integer pageSize);
    
    
    
    void  updatePwd(Long teachterid,UpdatePwdRequest pwdRequest);
}
