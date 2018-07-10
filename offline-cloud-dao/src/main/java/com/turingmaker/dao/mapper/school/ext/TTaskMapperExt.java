package com.turingmaker.dao.mapper.school.ext;

import com.turingmaker.dao.mapper.school.TTaskMapper;
import com.turingmaker.service.student.entity.StudentLessonTask;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\9 0009
 */
public interface TTaskMapperExt extends TTaskMapper {
    @Select({
            "select t.*,h.ID AS HOMEWORK_ID,tl.LESSON_SECTION,tl.LESSON_NAME from T_TASK t \n" +
                    "LEFT JOIN T_HOMEWORK h ON h.TASK_ID=t.ID AND h.STUDENT_ID=#{arg0} AND h.IS_DELETE=0\n" +
                    "LEFT JOIN T_LESSON tl ON tl.ID=#{arg1}",
                    " WHERE t.LESSON_ID=#{arg1} order by t.ID asc"
    })
    @Results({
            @Result(column="ID", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="TASK_NAME", property="taskName", jdbcType=JdbcType.VARCHAR),
            @Result(column="TASK_DESCURL", property="taskDescurl", jdbcType=JdbcType.VARCHAR),
            @Result(column="LESSON_ID", property="lessonId", jdbcType=JdbcType.BIGINT),
            @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column = "LESSON_SECTION",property = "lessonSection",jdbcType = JdbcType.INTEGER),
            @Result(column = "LESSON_NAME",property = "lessonName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "HOMEWORK_ID",property = "homeworkId",jdbcType = JdbcType.BIGINT)
    })
    List<StudentLessonTask> getStudentTaskLessonList(Long studentId,Long lessonId);
}
