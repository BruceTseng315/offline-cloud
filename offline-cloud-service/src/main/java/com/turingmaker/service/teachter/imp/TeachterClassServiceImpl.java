package com.turingmaker.service.teachter.imp;

import com.turingmaker.dao.mapper.school.TClassMapper;
import com.turingmaker.dao.mapper.school.ext.TClassMapperExt;
import com.turingmaker.service.teachter.api.ClassService;
import com.turingmaker.service.teachter.response.ClassinfoPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**教师端的班级的服务
 * @author  tzj
 */
@Service
public class TeachterClassServiceImpl implements ClassService {

    @Autowired
    TClassMapperExt classMapperExt;

    @Override
    public ClassinfoPageResponse findTeachterClasses() {
        return null;
    }
}
