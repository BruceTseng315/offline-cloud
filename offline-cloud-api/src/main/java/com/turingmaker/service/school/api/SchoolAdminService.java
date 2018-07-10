package com.turingmaker.service.school.api;

import com.turingmaker.entity.school.TSchoolAdmin;
import com.turingmaker.service.school.bo.RefreshPasswordBo;
/**
 * 学校管理员service
 */
public interface SchoolAdminService {
    /**
     * 重置账号密码
     * @param accountId
     */
    RefreshPasswordBo refreshPassword(long accountId);

    /**
     * 查找指定账户名的学校管理员记录
     * @param accountId
     * @return
     */
    TSchoolAdmin findSchoolAdminByAccountId(long accountId);
}
