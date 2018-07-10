package com.turingmaker.service.schoolManage.impl;

import com.turingmaker.entity.school.TSchoolAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turingmaker.config.Constant;
import com.turingmaker.dao.mapper.operation.ext.UserMapperExt;
import com.turingmaker.dao.mapper.school.ext.TSchoolAdminMapperExt;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.service.school.api.SchoolAdminService;
import com.turingmaker.service.school.bo.RefreshPasswordBo;

@Service
public class SchoolAdminServiceImpl implements SchoolAdminService{
    @Autowired
    TSchoolAdminMapperExt schoolAdminMapper;

    @Autowired
    UserMapperExt userMapper;

    /**
     * 重置学校管理员密码
     * @param accountId
     */
    @Override
    public RefreshPasswordBo refreshPassword(long accountId) {
        YUser user = userMapper.selectByPrimaryKey(accountId);
        user.setPassword(Constant.DEFAULT_SCHOOL_ADMIN_PASSWORD);
        userMapper.updateByPrimaryKeySelective(user);
        user = userMapper.selectByPrimaryKey(accountId);
        RefreshPasswordBo refreshPasswordBo = new RefreshPasswordBo();
        refreshPasswordBo.setAccountId(user.getId());
        refreshPasswordBo.setAccountName(user.getAccount());
        refreshPasswordBo.setPassword(user.getPassword());

        return refreshPasswordBo;
    }

    @Override
    public TSchoolAdmin findSchoolAdminByAccountId(long accountId) {
        TSchoolAdmin schoolAdmin = schoolAdminMapper.findByAccountId(accountId);
        return schoolAdmin;
    }
}
