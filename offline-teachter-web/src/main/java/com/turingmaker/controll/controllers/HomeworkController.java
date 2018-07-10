package com.turingmaker.controll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.common.domain.Result;
import com.turingmaker.dao.mapper.teachter.THomeworkScoreMapper;
import com.turingmaker.service.teachter.request.CorrectHomeWorkRequest;

/**
 * 作业相关接口
 * @author  tzj
 */
@RestController
@RequestMapping("/turing/api/v2/teacher/work/homework/")
public class HomeworkController {

	
	@Autowired
	THomeworkScoreMapper homeworkScoreMapper;
	
	/**
	 * 打分
	 * @param correctHomeWorkRequest
	 * @return
	 */
	@PostMapping("correct")
	public Result<?>  correctHomeWork(@RequestBody CorrectHomeWorkRequest correctHomeWorkRequest){
		
		homeworkScoreMapper.insertSelective(correctHomeWorkRequest);
		
		return Result.successresult;
	}


}
