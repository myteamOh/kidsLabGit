package com.kidslab.common.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

public class FileUploadUtil {
	static Logger logger = Logger.getLogger(FileUploadUtil.class);

	/* 파일 업로드 할 폴더 생성 */
	public static void makeDir(String docRoot) {
		File fileDir = new File(docRoot);
		if (fileDir.exists()) {
			return;
		}
		fileDir.mkdirs();
	}

	/* 파일 업로드 메소드 */
	public static String fileUpload(MultipartFile file, HttpServletRequest request, String fileName)
			throws IOException {
		logger.info("fileUpload 호출 성공");

		String real_name = null;
		// MultipartFile 클래스의 getFile() 메소드로 클라이언트가 업로드한 파일
		String org_name = file.getOriginalFilename();
		logger.info("org_name : " + org_name);

		// 파일명 변경(중복되지 않게)
		if (org_name != null && (!org_name.equals(""))) {
			real_name = fileName + "_" + System.currentTimeMillis() + "_" + org_name;
			// 저장할 파일 이름

			String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage/" + fileName);
			String path = "C:/downLoad/" + fileName;
			makeDir(docRoot);
			makeDir(path);

			// 파일 생성 후
			File fileAdd = new File(docRoot + "/" + real_name);
			File fileAddC = new File(path + "/" + real_name);
			logger.info("업로드 할 파일(fileAdd) : " + fileAdd);
			logger.info("업로드 할 파일(fileAdd) : " + fileAddC);

			file.transferTo(fileAdd);
			file.transferTo(fileAddC);

		}
		return real_name;
	}

	/* 파일 삭제 메소드 */
	public static void fileDelete(String fileName, HttpServletRequest request) throws IOException {
		logger.info("fileDelete 호출 성공");
		boolean result = false;
		String dirName = fileName.substring(0, fileName.indexOf("_"));
		String docRoot = request.getSession().getServletContext().getRealPath("/uploadStorage/" + dirName);
		String path = "C:/downLoad/" + dirName;

		// 파일 생성 후
		File fileDelete = new File(docRoot + "/" + fileName);
		File fileDeleteC = new File(path + "/" + fileName);
		logger.info("삭제할 파일(fileDelete) : " + fileDelete);
		logger.info("삭제할 파일(fileDelete) : " + fileDeleteC);
		if (fileDelete.exists() && fileDelete.isFile()) {
			result = fileDelete.delete();
		}
		if (fileDeleteC.exists() && fileDeleteC.isFile()) {
			result = fileDeleteC.delete();
		}
		logger.info("파일 삭제 여부(true/false) : " + result);
	}

	/* 파일 Thumbnail 생성 메소드 */
	public static String makeThumbnail(String fileName, HttpServletRequest request) throws Exception {
		// 이미지가 존재하는 폴더 추출
		String dirName = fileName.substring(0, fileName.indexOf("_"));
		// 추출된 폴더의 실제 경로(물리적 위치 : C:\...)
		String imgPath = request.getSession().getServletContext().getRealPath("uploadStorage/" + dirName);
		String path = "C:/downLoad/" + fileName;
		File fileAdd = new File(imgPath, fileName);
		File fileAddC = new File(path, fileName);
		logger.info("원본 이미지 파일(fileAdd) : " + fileAdd);
		logger.info("원본 이미지 파일(fileAdd) : " + fileAddC);

		BufferedImage sourceImg = ImageIO.read(fileAdd);
		BufferedImage sourceImgC = ImageIO.read(fileAddC);
		// resize(대상[BufferedImage 타입], 원본비율, 높이 또는 너비, 크기)
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 133);
		BufferedImage destImgC = Scalr.resize(sourceImgC, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 133);

		String thumbnailName = "thumbnail_" + fileName;
		String docRoot = imgPath + "/thumbnail";
		String docRootC = path + "/thumbnail";
		makeDir(docRoot);
		makeDir(docRootC);

		File newFile = new File(docRoot, thumbnailName);
		File newFileC = new File(docRootC, thumbnailName);
		logger.info("업로드할 파일(newFile) : " + newFile);
		logger.info("업로드할 파일(newFile) : " + newFileC);

		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		logger.info("확장자(formatName) : " + formatName);

		ImageIO.write(destImg, formatName, newFile);
		ImageIO.write(destImgC, formatName, newFileC);
		return thumbnailName;

	}

}
