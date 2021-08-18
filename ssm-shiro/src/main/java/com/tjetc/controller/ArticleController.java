package com.tjetc.controller;

/*
import com.sun.deploy.net.HttpResponse;
*/
import com.alibaba.fastjson.JSONObject;
import com.tjetc.common.AdminConstant;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.Article;
import com.tjetc.entity.Member;
import com.tjetc.model.view.MemberVoView;
import com.tjetc.service.ArticleService;
import com.tjetc.service.MemberService;
import com.tjetc.service.ScheduleService;
import org.apache.shiro.SecurityUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AdminConstant adminConstant;
	@Autowired
	private ScheduleService scheduleService;
	
	@RequestMapping("/info")
	@ResponseBody
	public JsonResult info() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		List<String> permissionNames = memberService.findPermissionNamesByUsername(username);
		MemberVoView memberVoView = new MemberVoView(username, permissionNames);
		return new JsonResult(0, "", memberVoView);
	}
	
	/**
	 * 播音查看稿件
	 *
	 * @return
	 */
	@RequestMapping("/select")
	@ResponseBody
	public JsonResult selectArticle(@RequestParam("date")String date) {
		String article_title = scheduleService.findByB(date);
		return new JsonResult(0,"",article_title);
	}
	
	/**
	 * 编辑上传稿件
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public JsonResult insertArticle(@RequestParam("date") String date,
									@RequestPart("file")  MultipartFile file) throws IOException {
		String originalFilename = file.getOriginalFilename();
		String title = originalFilename.substring(0, originalFilename.lastIndexOf(".")).toLowerCase();
		Article article = new Article();
		article.setArticleTitle(title);
		Boolean aBoolean = articleService.selectByArticle_title(article.getArticleTitle());
		if (aBoolean) {
			String username = (String) SecurityUtils.getSubject().getPrincipal();
			Member member = memberService.findByUsername(username);
			article.setAuthor(member.getMemberName());
			LocalDateTime now = LocalDateTime.now();
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(date);
			CharSequence charSequence = stringBuilder.subSequence(1, 11);
			System.out.println("stringBuilder = " + charSequence);
			String s = charSequence.toString();
			String tmp = s + " 08:00:00";
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(tmp,df);
			article.setCreateTime(localDateTime);
			article.setUpdateTime(now);
			StringBuilder sb = new StringBuilder();
			sb.insert(0, adminConstant.getVideoBasePath() + "/" + title + ".txt");
			String path = sb.toString();
			article.setPath(path);
			articleService.insert(article);
			File file1 = new File(path);
			file.transferTo(file1);
			return new JsonResult<>(0, "添加成功", null);
		} else {
			return new JsonResult<>(1, "文件已存在，添加失败", null);
		}
	}
	
	@RequestMapping("/selectByUsername")
	@ResponseBody
	public JsonResult selectByUsername() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		List<Article> articles = articleService.selectByUsername(username);
		return new JsonResult(0, "查询结果", articles);
	}

	@RequestMapping("/selectByEdit")
	@ResponseBody
	public JsonResult selectByEdit() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		List<Article> articles = articleService.selectByEdit(username);
		return new JsonResult(0, "查询结果", articles);
	}

	@RequestMapping("/selectAll")
	@ResponseBody
	public JsonResult selectAll() {
		List<Article> articles = articleService.findAll();
		return new JsonResult(0, "查询结果", articles);
	}
	
	/**
	 * 组长通过审阅
	 *
	 * @param title
	 * @return
	 */
	@RequestMapping("/updateStatusZ")
	@ResponseBody
	public JsonResult updateStatusZ(@RequestParam("title") String title) {
		articleService.updateStatusZ(title);
		return new JsonResult(0, "审阅成功，已提交台长审阅", null);
	}
	
	/**
	 * 栏目总编通过审阅
	 */
	@RequestMapping("/updateStatusT")
	@ResponseBody
	public JsonResult updateStatusT(@RequestParam("title") String title) {
		articleService.updateStatusT(title);
		return new JsonResult(0, "审阅成功", null);
	}
	
	/**
	 * 组长未通过审阅
	 *
	 * @param title
	 * @return
	 */
	@RequestMapping("/noUpdateStatusZ")
	@ResponseBody
	public JsonResult NoUpdateStatusZ(@RequestParam("status") String status, @RequestParam("title") String title) {
		articleService.noUpdateStatusZ(status, title);
		return new JsonResult(0, "已将稿子退回", null);
	}
	
	/**
	 * 栏目总编未通过审阅
	 */
	@RequestMapping("/noUpdateStatusT")
	@ResponseBody
	public JsonResult NoUpdateStatusT(@RequestParam("status") String status, @RequestParam("title") String title) {
		articleService.noUpdateStatusT(status, title);
		return new JsonResult(0, "已将稿子退回", null);
	}
	
	/**
	 * 组长查看所有的稿件
	 */
	@RequestMapping("/selectAllByGroup")
	@ResponseBody
	public JsonResult selectAllByGroup() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		Long memberId = memberService.findByUsername(username).getMemberId();
		List<Article> articles = articleService.findAllByGroup(memberId);
		return new JsonResult(0, "", articles);
	}

	/**
	 * 栏目总编查看待复审稿子
	 */
	@RequestMapping("/selectAllF")
	@ResponseBody
	public JsonResult selectAllF(){
		List<Article> articles = articleService.selectAllF();
		return new JsonResult(0, "", articles);
	}

	/**
	 * 栏目总编查看所有稿子
	 */

	@RequestMapping("/selectAllT")
	@ResponseBody
	public JsonResult selectAllT(){
		List<Article> articles = articleService.selectAllT();
		return new JsonResult(0, "", articles);
	}
}
