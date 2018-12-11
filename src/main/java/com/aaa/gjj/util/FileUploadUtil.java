package com.aaa.gjj.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @className:FileUploadUtil.java
 * @scription:ͨ���ϴ�������
 * @author:zz
 * @createTime:2018-9-17����2:23:31
 * @version:1.0.0
 */
public class FileUploadUtil {

	public static String uploadFile(String savePath, MultipartFile file,
			HttpServletRequest request) {
		String realPath = request.getServletContext().getRealPath(savePath);
		String originalFilename = file.getOriginalFilename();
		String oldFileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

		String newFileName = UUID.randomUUID() + oldFileSuffix;
		File tempFile = new File(realPath + "/" + newFileName); // D:\Tomcat7_1\webapps\spring-mvc-demo3\files\aaa.txt

		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}

		try {
			file.transferTo(tempFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savePath + "/" + newFileName;
	}
}
