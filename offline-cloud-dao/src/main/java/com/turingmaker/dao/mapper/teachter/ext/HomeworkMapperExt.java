package com.turingmaker.dao.mapper.teachter.ext;

import com.turingmaker.dao.mapper.teachter.THomeworkMapper;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.student.entity.StudentHomeWorkDetail;
import com.turingmaker.service.student.entity.StudentHomeworkOutline;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.turingmaker.service.teachter.entity.ClassHomeWork;

import org.apache.ibatis.annotations.SelectProvider;

import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public interface HomeworkMapperExt extends THomeworkMapper {
    @Select({
            "select th.*,ths.`COMMENT`,ths.COOPERATIVE_LEARN,ths.INQUIRY_LEARN,ths.SELF_LEARN,ths.UTILIZATION,\n" +
                    "tpl.LIKE_COUNT,tpv.VIEW_COUNT\n" +
                    " from T_HOMEWORK th \n" +
                    " left join T_HOMEWORK_SCORE ths ON th.ID=ths.HOMEWORK_ID\n" +
                    " LEFT JOIN T_PROGRAM_LIKE tpl ON th.PROGRAM_ID=tpl.PROGRAM_ID\n" +
                    " LEFT JOIN T_PROGRAM_VIEW tpv ON th.PROGRAM_ID=tpv.PROGRAM_ID\n" +
                    " where th.ID=#{arg0} and th.IS_DELETE=0"
    })
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="STUDENT_ID", property="studentId", jdbcType=JdbcType.BIGINT),
            @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
            @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="TASK_ID", property="taskId", jdbcType=JdbcType.BIGINT),
            @Result(column="HOMEWORK_DESC", property="homeworkDesc", jdbcType=JdbcType.VARCHAR),
            @Result(column="HOMEWORK_NAME", property="homeworkName", jdbcType=JdbcType.VARCHAR),
            @Result(column="HOMEWORK_AVATAR", property="homeworkAvatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="MARK", property="mark", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column = "VIEW_COUNT",property = "viewCount",jdbcType = JdbcType.INTEGER),
            @Result(column = "LIKE_COUNT",property = "likeCount",jdbcType = JdbcType.INTEGER),
            @Result(column="SELF_LEARN", property="selfLearn", jdbcType=JdbcType.INTEGER),
            @Result(column="INQUIRY_LEARN", property="inquiryLearn", jdbcType=JdbcType.INTEGER),
            @Result(column="COOPERATIVE_LEARN", property="cooperativeLearn", jdbcType=JdbcType.INTEGER),
            @Result(column="UTILIZATION", property="utilization", jdbcType=JdbcType.INTEGER),
            @Result(column="COMMENT", property="comment", jdbcType=JdbcType.VARCHAR)
    })
    StudentHomeWorkDetail getHomeworkDetail(Long homeworkId);

    @Select("select  count(0)  from `T_HOMEWORK` th LEFT JOIN T_PROGRAM_LIKE tpl on th.`PROGRAM_ID` =tpl.PROGRAM_ID\n" +
            "             where th.`ID` =#{arg1}  AND    JSON_CONTAINS_PATH(tpl.`LIKE_USERS`,'one','$.u${arg0}')")
    boolean checkUserIsLiked(@Param("arg0") Long userId, Long homeworkId );

    /**
     * 查询作业列表
     * @param pageinfo
     * @param studentId
     * @return
     */
    @SelectProvider(type=HomeworkMapperSqlProvider.class,method = "getStudentHomeworkOutlinePage")
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="STUDENT_ID", property="studentId", jdbcType=JdbcType.BIGINT),
            @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
            @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="TASK_ID", property="taskId", jdbcType=JdbcType.BIGINT),
            @Result(column = "TASK_NAME",property = "taskName",jdbcType = JdbcType.VARCHAR),
            @Result(column="HOMEWORK_DESC", property="homeworkDesc", jdbcType=JdbcType.VARCHAR),
            @Result(column="HOMEWORK_NAME", property="homeworkName", jdbcType=JdbcType.VARCHAR),
            @Result(column="HOMEWORK_AVATAR", property="homeworkAvatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="MARK", property="mark", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SELF_LEARN", property="selfLearn", jdbcType=JdbcType.INTEGER),
            @Result(column="INQUIRY_LEARN", property="inquiryLearn", jdbcType=JdbcType.INTEGER),
            @Result(column="COOPERATIVE_LEARN", property="cooperativeLearn", jdbcType=JdbcType.INTEGER),
            @Result(column="UTILIZATION", property="utilization", jdbcType=JdbcType.INTEGER),
            @Result(column = "COURSE_NAME",property = "courseName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "LESSON_SECTION",property = "lessonSection",jdbcType = JdbcType.INTEGER)
    })
    List<StudentHomeworkOutline> getStudentHomeworkOutlinePage(Pageinfo pageinfo,@Param("studentId") Long studentId,@Param("mark") Integer mark);



    /**
     * 查询班级作业
     * @param pageinfo 分页参数
     * @param classId  班级id
     * @param courseId  课程id
     * @param lessonId  课时id
     * @param isMark 标志是够批阅
     * @param studentId 学生ID
     * @return
     */

    @SelectProvider(type=HomeworkMapperSqlProvider.class,method = "findClassHomeWord")
    @Results({
            @Result(column = "ID", property = "homeworkId", jdbcType = JdbcType.BIGINT),
            @Result(column = "STUDENT_NAME", property = "studentName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "LESSON_NAME", property = "lessonName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "COURSE_NAME", property = "courseName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SELF_LEARN", property = "homeworkId", jdbcType = JdbcType.BIGINT),
            @Result(column = "INQUIRY_LEARN", property = "homeworkId", jdbcType = JdbcType.BIGINT),
            @Result(column = "COOPERATIVE_LEARN", property = "homeworkId", jdbcType = JdbcType.BIGINT),
    })
    List<ClassHomeWork>  findClassHomeWorkPage(Pageinfo pageinfo, Long classId, Long courseId, Long lessonId,Long studentId,Boolean isMark);


}
