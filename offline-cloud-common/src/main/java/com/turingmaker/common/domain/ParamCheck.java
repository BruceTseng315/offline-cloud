package com.turingmaker.common.domain;

import com.turingmaker.common.exception.CheckParamException;

/**
 * 参数检查
 * @author Administrator
 *
 */
public interface ParamCheck {

	public void check() throws CheckParamException;
}
