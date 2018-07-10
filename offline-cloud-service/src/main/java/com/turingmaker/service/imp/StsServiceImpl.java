package com.turingmaker.service.imp;

import org.springframework.stereotype.Service;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.turing.common.sts.OSSCredential;
import com.turing.common.sts.StsUtils.OSSOploadUrlConfig;
import com.turing.common.sts.StsUtils.OSSUtils;
import com.turingmaker.service.api.StsService;


/**
 * Created by zengdingyang on 2018\6\25 0025.
 */
@Service
public class StsServiceImpl implements StsService {
    @Override
    public OSSCredential getOSSCredential(String type) {
        AssumeRoleResponse.Credentials credential = OSSUtils.getStsToken();

        OSSCredential ossCredential=new OSSCredential();
        ossCredential.setCredential(credential);
       String uploadUrl = OSSOploadUrlConfig.getUrl(type);
       ossCredential.setBaseUrl(uploadUrl);
        return ossCredential;
    }
}
