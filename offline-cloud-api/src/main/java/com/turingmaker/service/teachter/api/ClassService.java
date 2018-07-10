package com.turingmaker.service.teachter.api;

import com.turingmaker.service.teachter.response.ClassinfoPageResponse;

/**
 * 班级相关的service
 * @author tzj
 */
public interface ClassService {


    /**
     * 返回教师所教的班级列表
     * @return
     */
     ClassinfoPageResponse findTeachterClasses();
}
