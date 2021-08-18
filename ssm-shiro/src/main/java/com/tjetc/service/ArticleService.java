package com.tjetc.service;

import com.tjetc.entity.Article;

import java.util.List;

/**
 * 稿子表
 */
public interface ArticleService {
	int removeByPrimaryKey(Long articleId);
	
	int insert(Article record);
	
	Article findByPrimaryKey(Long articleId);
	
	/**
	 * 查找所有稿件
	 *
	 * @return
	 */
	List<Article> findAll();
	
	int updateByPrimaryKey(Article record);
	
	Boolean selectByArticle_title(String articleTitle);
	
	/**
	 * 组长查看组内稿件
	 *
	 * @param username
	 * @return
	 */
	List<Article> selectByUsername(String username);
	
	/**
	 * 编辑查看个人稿件
	 *
	 * @param username
	 * @return
	 */
	List<Article> selectByEdit(String username);
	
	/**
	 * 组长审阅
	 *
	 * @param articleTitle
	 */
	void updateStatusZ(String articleTitle);
	
	/**
	 * 台长审阅
	 */
	void updateStatusT(String articleTitle);
	
	/**
	 * 组长未通过审阅
	 */
	void noUpdateStatusZ(String status, String articleTitle);
	
	/**
	 * 台长未通过审阅
	 */
	void noUpdateStatusT(String status, String articleTitle);
	
	/**
	 * 组长查看所有稿件
	 *
	 * @return
	 */
	List<Article> findAllByGroup(Long memberId);
	
	/**
	 * 查找全台待复审的稿子
	 */
	List<Article> selectAllF();
	
	/**
	 * 查找全台的稿子
	 */
	List<Article> selectAllT();

	/**
	 * 使用稿子后更改稿子状态
	 */
	void updateStatus(String article_title);
}
