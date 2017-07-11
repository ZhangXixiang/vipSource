package com.boomhope.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class CommonConfig {

	/** 图片路径 */
	@Value("${log_image_path}")
	public String logImagePath;
	
	//==============人脸识别平台========================
	/** 人脸识别平台服务地址 */
	@Value("${face_ip}")
	public String faceIp;
	
	/** 人脸识别平台服务端口 */
	@Value("${face_port}")
	public String facePort;
	
}
