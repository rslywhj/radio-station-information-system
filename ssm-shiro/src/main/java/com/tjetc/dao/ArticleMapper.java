package com.tjetc.dao;

import com.tjetc.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
	int deleteByPrimaryKey(Long articleId);
	
	int insert(Article record);
	
	Article selectByPrimaryKey(Long articleId);
	
	/**
	 * 查找所有稿件
	 *
	 * @return
	 */
	List<Article> selectAll();
	
	int updateByPrimaryKey(Article record);
	
	Long selectByArticle_title(String articleTitle);
	
	/**
	 * 组长查看本组稿件
	 *
	 * @param username
	 * @return
	 */
	List<Article> selectByUsername(String username);
	
	/**
	 * 编辑查看自己稿件
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
	void noUpdateStatusZ(@Param("status") String status, @Param("articleTitle") String articleTitle);
	
	/**
	 * 台长未通过审阅
	 */
	void noUpdateStatusT(@Param("status") String status, @Param("articleTitle") String articleTitle);
	
	/**
	 * 查找本组所有稿件
	 *
	 * @return
	 */
	List<Article> selectAllByGroup(Long memberId);
	
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