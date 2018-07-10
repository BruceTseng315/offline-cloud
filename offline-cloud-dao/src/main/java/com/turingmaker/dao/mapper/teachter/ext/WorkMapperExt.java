package com.turingmaker.dao.mapper.teachter.ext;

import com.turingmaker.dao.mapper.teachter.TWorkMapper;
import com.turingmaker.entity.teachter.TWork;
import com.turingmaker.service.operation.response.Pageinfo;
import com.turingmaker.service.work.entity.WorkDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public interface WorkMapperExt extends TWorkMapper {


    /**
     * 发布work
     * @param workId
     * @param publishFlag
     */
    @Update(value = "UPDATE  `T_WORK`  SET IS_DELETE=#{arg1} WHERE ID=#{arg0}")
     void updateWorkPublishState(Long workId,int publishFlag);



    /**
     * 删除work
     * @param workId
     * @param deleteFlag
     */
    @Update(value = "UPDATE  `T_WORK`  SET STATE=#{arg1}  WHERE ID=#{arg0}")
     void updateWorkDeletehState(Long workId,int deleteFlag);


    /**
     * 点赞
     * @param paramMap
     */
    @Select(value=" call likeWork(" +
            "  #{param.workId,mode=IN,jdbcType=BIGINT}," +
            "  #{param.userId,mode=IN,jdbcType=BIGINT}," +
            "  #{param.workType,mode=IN,jdbcType=INTEGER}," +
            "  #{param.result,mode=OUT,jdbcType=INTEGER});")
    @Options(statementType= StatementType.CALLABLE)
    void likeWork(@Param("param") Map<String, Object> paramMap);

    /**
     * 浏览
     * @param paramMap
     */
    @Select(value=" call viewWork(" +
            "  #{param.workId,mode=IN,jdbcType=BIGINT}," +
            "  #{param.userId,mode=IN,jdbcType=BIGINT}," +
            "  #{param.workType,mode=IN,jdbcType=INTEGER}," +
            "  #{param.result,mode=OUT,jdbcType=INTEGER});")
    @Options(statementType=StatementType.CALLABLE)
    void viewWork(@Param("param") Map<String, Object> paramMap);


    @SelectProvider(type = WorkSqlProvider.class,method = "getWorkListPage")
    @Results({
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="WORK_DESC", property="workDesc", jdbcType=JdbcType.VARCHAR),
            @Result(column="WORK_AUTHOR", property="workAuthor", jdbcType=JdbcType.VARCHAR),
            @Result(column="WORK_NAME", property="workName", jdbcType=JdbcType.VARCHAR),
            @Result(column="WORK_AVATAR", property="workAvatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TWork> findWorkListByPage(Pageinfo pageinfo, @Param("accountId") Long accountId, @Param("state") Integer state);



    /**
    * 查询作品详情，并且包括 浏览数，点赞数
    * @return
    */
    @Select(value = "select tw.*,tpl.`LIKE_COUNT` ,tpv.`VIEW_COUNT` " +
        "  from `T_WORK` tw " +
        "  LEFT JOIN `T_PROGRAM_VIEW` tpv on tw.`PROGRAM_ID`= tpv.`PROGRAM_ID`" +
        "  LEFT JOIN `T_PROGRAM_LIKE` tpl on tw.`PROGRAM_ID`= tpl.`PROGRAM_ID` " +
        " where tw.`ID`= #{arg0}")
    @Results({
        @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="WORK_DESC", property="workDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_AUTHOR", property="workAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_NAME", property="workName", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_AVATAR", property="workAvatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        
        @Result(column="VIEW_COUNT", property="viewCount", jdbcType=JdbcType.INTEGER),
        @Result(column="LIKE_COUNT", property="likeCount", jdbcType=JdbcType.INTEGER)
    })
    WorkDetail selectWorkDetailById(Long workId);


    /**
     * 查询这个用户的作品
     * @param userId
     * @return
     */
    @Select(value = "select ID, USER_ID, WORK_DESC, WORK_AUTHOR, WORK_NAME, WORK_AVATAR  from  `T_WORK` where  `USER_ID` =#{arg0}")
    @Results(id="resultMap",value = {
            @Result(column="ID", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="USER_ID", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="WORK_DESC", property="workDesc", jdbcType=JdbcType.VARCHAR),
            @Result(column="WORK_AUTHOR", property="workAuthor", jdbcType=JdbcType.VARCHAR),
            @Result(column="WORK_NAME", property="workName", jdbcType=JdbcType.VARCHAR),
            @Result(column="WORK_AVATAR", property="workAvatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
            @Result(column="PROGRAM_ID", property="programId", jdbcType=JdbcType.BIGINT),
            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TWork> sectWorksByUserId(Long userId);


    /**
     * 查询这个班级下面的所有作品
     * @param userId
     * @return
     */
    @Select(value = "select tw.*" +
            "  from `T_CLASS_STUDENT` tcs" +
            "  LEFT JOIN `T_STUDENT` tstu on tstu.`ID`= tcs.`STUDENT_ID`" +
            "  RIGHT JOIN `T_WORK` tw on tw.`USER_ID`= tstu.`ACCOUNT_ID`" +
            " where class_id= #{arg0}")
    @ResultMap("resultMap")
    List<TWork> sectWorksByUserClass(Long userId);

    @Select(value = "select  count(0)  from `T_WORK` tw LEFT JOIN T_PROGRAM_LIKE tpl on tw.`PROGRAM_ID` =tpl.PROGRAM_ID" +
            " where tw.`ID` =#{arg1}  AND    JSON_CONTAINS_PATH(tpl.`LIKE_USERS`,'one','$.u${arg0}') ")
    boolean checkUserIsLiked(@Param("arg0") Long userId,Long workId );
}


