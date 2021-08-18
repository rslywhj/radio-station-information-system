package com.tjetc.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 自定义的配置值
 */
@Component
public class AdminConstant {
	@Value("${video.base.path}")
	private String videoBasePath;
	
	public String getVideoBasePath() {
		return videoBasePath;
	}
}
