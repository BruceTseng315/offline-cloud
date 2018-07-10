package com.turingmaker.dao.mapper.teachter.ext;


import com.turingmaker.service.operation.response.Pageinfo;
import org.apache.ibatis.annotations.Param;


/**
 * @author  tzj
 * SQL provider
 */
public class HomeworkMapperSqlProvider {



    public String findClassHomeWord(Pageinfo  pageinfo, Long classId, Long courseId, Long lessonId,Long studentId,Boolean isMark){

        String sql="select th.`ID` ,ts.`STUDENT_NAME` ,tlp.`LESSON_ID`,tl.`LESSON_NAME`,tc.`COURSE_NAME`,ths.`SELF_LEARN` ,ths.`INQUIRY_LEARN` ,ths.`COOPERATIVE_LEARN`     from  `T_HOMEWORK`  th " +
                "LEFT JOIN  `T_LESSON_PROGRAM`  tlp    on th.`PROGRAM_ID` =tlp.`PROGRAM_ID` " +
                "LEFT JOIN  `T_STUDENT`  ts  on th.`STUDENT_ID` =ts.`ID` " +
                "LEFT JOIN `T_LESSON` tl  on tlp.`LESSON_ID` =tl.`ID`  " +
                "LEFT JOIN `T_COURSE` tc  on tl.`COURSE_ID` =tc.`ID`  " +
                "LEFT JOIN `T_HOMEWORK_SCORE` ths on th.`ID` =ths.`HOMEWORK_ID` " +
                "LEFT JOIN `T_CLASS_STUDENT`  tcs  on ts.`ID` =tcs.`STUDENT_ID` ";

        StringBuilder builder=new StringBuilder(sql);
        builder.append("WHERE 1=1");



         if(classId!=null){
             builder.append(" AND tcs.`CLASS_ID` =#{arg1}");
         }
        if(courseId!=null){
            builder.append(" AND tcs.`COURSE_ID` =#{arg2}");
        }

        if(lessonId!=null){
            builder.append(" AND tcs.`LESSON_ID` =#{arg3}");
        }
        if(studentId!=null){
            builder.append(" AND th.`STUDENT_ID` =#{arg4}");
        }

        if(isMark!=null){
            if(isMark){
                builder.append(" AND th.`MARK` ='1'");
            }else{
                builder.append(" AND th.`MARK`` ='0'");
            }
        }

        return builder.toString();
    }

    public String getStudentHomeworkOutlinePage(Pageinfo pageinfo, @Param("studentId") Long studentId, @Param("mark") Integer mark){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append( "SELECT th.*,tl.LESSON_SECTION,tc.COURSE_NAME,tt.TASK_NAME," ).append(
                "ths.`COMMENT`,ths.COOPERATIVE_LEARN,ths.INQUIRY_LEARN,ths.SELF_LEARN,ths.UTILIZATION ").append(
                "from T_HOMEWORK th ").append(
                "left join T_HOMEWORK_SCORE ths ON th.ID=ths.HOMEWORK_ID").append(
                " LEFT JOIN T_TASK tt ON th.TASK_ID=tt.ID").append(
                " LEFT JOIN T_LESSON tl ON tt.LESSON_ID=tl.ID").append(
                " LEFT JOIN T_COURSE tc ON tl.COURSE_ID=tc.ID").append(
                " where STUDENT_ID=#{studentId} and IS_DELETE=0");

        if(mark != null){
            stringBuilder.append(" and MARK=#{mark}");
        }

        return stringBuilder.toString();
    }
}
