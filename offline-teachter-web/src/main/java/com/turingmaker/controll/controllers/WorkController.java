
package com.turingmaker.controll.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turingmaker.common.domain.Result;
import com.turingmaker.entity.operation.YUser;
import com.turingmaker.service.exception.HttpException;
import com.turingmaker.service.teachter.request.SaveWorkRequest;
import com.turingmaker.service.work.api.WorkService;

/**
 * 作品的controller filter
 * @auth  tzj
 */
@RestController
@RequestMapping("/turing/api/v2/teacher/work/")
public class WorkController {


	@Autowired
	WorkService workService;
	


    /**
     * 保存作品 接口
     * @return
     */
    @PostMapping("save")
    public Result<?> saveWork(HttpSession session,@RequestBody  SaveWorkRequest saveWorkRequest){
    	
    
    	saveWorkRequest.setUserId(getUserFromSession(session));
    	workService.saveWork(saveWorkRequest);
        return Result.successresult;
    }

    /**
     * 浏览作品 接口
     * @return
     */
    @GetMapping("view")
    public Result<Void> viewWork(HttpSession session,Long workId){

    	workService.viewWork(workId, getUserFromSession(session));
        
    	return new Result<>();
    }


    /**
     * 点赞作品 接口
     * @return
     */
    @GetMapping("like")
    public Result<?> likeWork(HttpSession session,Long workId){

    	workService.likeWork(workId, getUserFromSession(session), true);
    	 return Result.successresult;
    }



    /**
     * 取消点赞作品 接口
     * @return
     */
    @GetMapping("unlike")
    public Result<?> unlikeWork(HttpSession session,Long workId){


    	workService.likeWork(workId, getUserFromSession(session), false);
    	 return Result.successresult;
    }



    /**
     * 删除作品 接口
     * @return
     */
    @GetMapping("delete")
    public Result<Void>  deleteWork(Long workId){
    	workService.updateWorkDelateState(workId, true);
        return new Result<>();
    }



    /**
     *获取作品详情 接口
     * @return
     */
    @GetMapping("detail")
    public Result<?>  detailWork(Long workId){

 
        return new Result<Object>(workService.detailWork(workId));
    }


    
    public Long getUserFromSession(HttpSession session) {
    	
    	YUser user=
    			(YUser)session.getAttribute(AuthController.SESSION_TEACHTER_USER);
    	
    	
    	if(user==null||user.getId()==null) {
    		throw new HttpException("session错误,重新登录试试");
    	}
    	
    	return user.getId();
    }





}

