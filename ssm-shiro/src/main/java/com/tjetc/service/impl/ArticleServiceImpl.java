package com.tjetc.service.impl;

import com.tjetc.dao.ArticleMapper;
import com.tjetc.entity.Article;
import com.tjetc.entity.CheckLog;
import com.tjetc.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public int removeByPrimaryKey(Long articleId) {
		return articleMapper.deleteByPrimaryKey(articleId);
	}

	@Override
	public int insert(Article record) {
		return articleMapper.insert(record);
	}

	@Override
	public Article findByPrimaryKey(Long articleId) {
		return articleMapper.selectByPrimaryKey(articleId);
	}

	@Override
	public List<Article> findAll() {
		return articleMapper.selectAll();
	}


	@Override
	public int updateByPrimaryKey(Article record) {
		return 0;
	}

	@Override
	public Boolean selectByArticle_title(String articleTitle) {
		Long aLong = articleMapper.selectByArticle_title(articleTitle);
		if(aLong == null){
			return true;
		}else {
			return false;
		}
	}
	@Override
	public List<Article> selectByUsername(String username) {
		List<Article> articles = articleMapper.selectByUsername(username);
		return articles;
	}
	
	@Override
	public List<Article> selectByEdit(String username) {
		List<Article> articles = articleMapper.selectByEdit(username);
		return articles;
	}
	
	@Override
	public void updateStatusZ(String articleTitle) {
		articleMapper.updateStatusZ(articleTitle);
	}

	@Override
	public void updateStatusT(String articleTitle) {
		articleMapper.updateStatusT(articleTitle);
	}

	@Override
	public void noUpdateStatusZ(@Param("status") String status, @Param("title")String articleTitle) {
		articleMapper.noUpdateStatusZ(status, articleTitle);
	}

	@Override
	public void noUpdateStatusT(String status, String articleTitle) {
		articleMapper.noUpdateStatusT(status, articleTitle);
	}

	@Override
	public List<Article> findAllByGroup(Long memberId) {
		List<Article> articles = articleMapper.selectAllByGroup(memberId);
		return articles;
	}

	@Override
	public List<Article> selectAllF() {
		return articleMapper.selectAllF();
	}

	@Override
	public List<Article> selectAllT() {
		return articleMapper.selectAllT();
	}

	@Override
	public void updateStatus(String article_title) {
		articleMapper.updateStatus(article_title);
	}

}
