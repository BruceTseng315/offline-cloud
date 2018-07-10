package com.turingmaker.dao.mapper.teachter.ext;

import com.turingmaker.dao.mapper.teachter.TProgramLikeMapper;
import org.apache.ibatis.annotations.Update;

public  interface ProgramLikeMapperExt extends TProgramLikeMapper {


    @Update( value="update `T_PROGRAM_LIKE` set `LIKE_COUNT`=`LIKE_COUNT`+1, `LIKE_USERS` =JSON_SET(`LIKE_USERS`,'$.u${arg0}','0')  WHERE  PROGRAM_ID=${arg1}")
    public int likeProgram(Long userId,Long programId);


    @Update( value="update `T_PROGRAM_LIKE` set `LIKE_COUNT`=`LIKE_COUNT`-1, `LIKE_USERS` =JSON_REMOVE(`LIKE_USERS`,'$.u${arg0}')  WHERE  PROGRAM_ID=${arg1}")
    public int unlikeProgram(Long userId,Long programId);
}
