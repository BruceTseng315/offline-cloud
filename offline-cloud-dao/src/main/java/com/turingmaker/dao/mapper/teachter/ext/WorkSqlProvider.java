package com.turingmaker.dao.mapper.teachter.ext;

import com.turingmaker.service.operation.response.Pageinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Author zengdingyang
 * @Date 2018\7\5 0005
 */
public class WorkSqlProvider {
    public String getWorkListPage(Pageinfo pageinfo, @Param("accountId") Long accountId, @Param("state") Integer state) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("T_WORK");
        sql.WHERE("USER_ID = #{accountId}");
        sql.WHERE("IS_DELETE=0");
        if(state != null) {
            sql.WHERE("STATE=#{state}");
        }

        return sql.toString();
    }
}
