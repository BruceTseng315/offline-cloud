package com.turingmaker.service.api;


import com.turing.common.sts.OSSCredential;

/**
 * Created by zengdingyang on 2018\6\25 0025.
 */
public interface StsService {
    OSSCredential getOSSCredential(String type);
}
