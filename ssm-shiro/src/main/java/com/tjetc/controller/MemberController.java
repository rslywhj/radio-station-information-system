package com.tjetc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.Member;
import com.tjetc.model.view.MemberVoView;
import com.tjetc.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/info")
	@ResponseBody
	public JsonResult info(){
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		List<String> permissionNames = memberService.findPermissionNamesByUsername(username);
		MemberVoView memberVoView = new MemberVoView(username, permissionNames);
		return new JsonResult(0, "", memberVoView);
	}
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(@RequestParam("username") String username,
	                        @RequestParam("password") String password,
	                        @RequestParam(value = "rememberMe", required = false, defaultValue = "false") Boolean rememberMe) {
		//实例化UsernamepasswordToken对象，两个参数 ：username  password
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		//token对象设置rememberMe
		token.setRememberMe(rememberMe);
		//校验用户名和密码 SecurityUtils.getSubject().login(),通过异常来显示用户名错误或者密码错误
		try {
			//检验登录
			SecurityUtils.getSubject().login(token);
			//返回登录成功
			return new JsonResult(0, "登录成功", null);
		} catch (UnknownAccountException e) {
			return new JsonResult(1, "用户名错误", null);
		} catch (IncorrectCredentialsException e) {
			return new JsonResult(1, "密码错误", null);
		}
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public JsonResult logout() {
		//登出
		SecurityUtils.getSubject().logout();
		return new JsonResult(0, "登出成功", null);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public JsonResult list(@RequestParam(value = "page",required = false,defaultValue = "1")int page,
						   @RequestParam(value = "limit",required = false,defaultValue = "20") int limit) {
		/*System.out.println("page = " + page);
		System.out.println("limit = " + limit);*/
		PageHelper.startPage(page,limit);
		List<Member> members = memberService.findAll();
		/*System.out.println("members = " + members);*/
		PageInfo<Member> pageInfo = new PageInfo<>(members);
		/*System.out.println("pageInfo = " + pageInfo);*/
		return new JsonResult(0, "", pageInfo);
	}
	
	@RequestMapping("/getMemberName")
	@ResponseBody
	public JsonResult getMemberName() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		String memberName = memberService.findByUsername(username).getMemberName();
		return new JsonResult(0,"",memberName);
	}
	/**
	 * 选择编辑
	 *
	 */
	@RequestMapping("/selectEdit")
	@ResponseBody
	public JsonResult selectEdit(){
		List<String> list = memberService.selectEdit();
		return new JsonResult(0,"所有编辑",list);
	}

	/**
	 * 选择播音
	 */
	@RequestMapping("/selectBroadcast")
	@ResponseBody
	public JsonResult selectBroadcast(){
		List<String> list = memberService.selectBroadcast();
		return new JsonResult(0,"所有播音",list);
	}
	@RequestMapping("/delete/{memberId}")
	@ResponseBody
	public boolean delete(@PathVariable("memberId")Long memberId){
		memberService.deleteByPrimaryKey(memberId);
		return true;
	}


}
































