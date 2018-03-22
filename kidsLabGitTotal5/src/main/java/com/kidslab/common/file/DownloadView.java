package com.kidslab.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	public DownloadView() {
		// content type을 지정.
		// 보여지는 뷰의 타입 설정 - 다운로드 뷰
		this.setContentType("apllication/download; charset=utf-8");
	}

	// 뷰를 그려줄 메소드
	// 첫번째 매개변수는 Controller에서 넘겨준 데이터
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 넘어온 데이터를 가져옵니다.
		// File 객체를 넘겨준 경우는 바로 받고
		// 파일 이름만 넘겨준 경우에는
		// 파일 이름을 가지고 File객체를 생성
		File file = (File) model.get("downloadFile");

		// 응답화면의 타입설정과 크기 설정
		response.setContentType(this.getContentType());
		// 다운로드 할 파일의 크기로 응답화면의 내용
		// 크기 설정
		// 파일 객체의 길이는 long 타입으로 리턴되기
		// 때문에 int로 형변환해서 대입
		response.setContentLength((int) file.length());

		// 브라우저의 user-agent 값 가져오기
		// 브라우저의 종류와 운영체제 종류가 포함되어
		// 있습니다.
		// 자바스크립트에서는 navigator.userAgent
		// 브라우저 별로 파일이름을 다르게 인코딩
		String fileName = null;
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		if (ie)
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		else
			fileName = URLEncoder.encode(new String(file.getName().getBytes("utf-8")), "iso-8859-1");

		// 응답화면의 헤더 설정
		// 이 설정을 안해주면 문서 파일이나 그림파일이
		// 기본적으로 다운로드가 되지 않습니다.
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");

		// file 객체의 모든 내용을 response에 복사
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			// fis의 내용을 out에 복사
			// 스프링의 클래스입니다.
			// 스프링을 사용하지 않는다면 직접 복사
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
		// 버퍼의 내용을 실제 출력할 대상에 복사
		out.flush();

	}

}
