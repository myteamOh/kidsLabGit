package com.kidslab.teacher.gallery.vo;

import org.springframework.web.multipart.MultipartFile;

import com.kidslab.common.vo.CommonVO;

public class TeacherGalleryVO extends CommonVO {
	private int gallery_no; // 갤러리 번호
	private int teacher_no; // 등록 선생님 고유번호
	private String teacher_name; // 등록 선생님 이름
	private String gallery_title; // 갤러리 제목
	private String gallery_content; // 갤러리 내용
	private String gallery_registerdate; // 갤러리 등록일

	// 파일 업로드를 위한 속성
	private String gallery_file; // 실제 서버에 저장한 파일명
	private String gallery_thumb; // 이미지 썸네일
	private MultipartFile file; // 갤러리 등록 파일

	public TeacherGalleryVO() {
		super();
	}

	public TeacherGalleryVO(int gallery_no, int teacher_no, String gallery_title, String gallery_content,
			String gallery_registerdate, String gallery_file, MultipartFile file) {
		super();
		this.gallery_no = gallery_no;
		this.teacher_no = teacher_no;
		this.gallery_title = gallery_title;
		this.gallery_content = gallery_content;
		this.gallery_registerdate = gallery_registerdate;
		this.gallery_file = gallery_file;
		this.file = file;
	}

	public int getGallery_no() {
		return gallery_no;
	}

	public void setGallery_no(int gallery_no) {
		this.gallery_no = gallery_no;
	}

	public int getTeacher_no() {
		return teacher_no;
	}

	public void setTeacher_no(int teacher_no) {
		this.teacher_no = teacher_no;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getGallery_title() {
		return gallery_title;
	}

	public void setGallery_title(String gallery_title) {
		this.gallery_title = gallery_title;
	}

	public String getGallery_content() {
		return gallery_content;
	}

	public void setGallery_content(String gallery_content) {
		this.gallery_content = gallery_content;
	}

	public String getGallery_registerdate() {
		return gallery_registerdate;
	}

	public void setGallery_registerdate(String gallery_registerdate) {
		this.gallery_registerdate = gallery_registerdate;
	}

	public String getGallery_file() {
		return gallery_file;
	}

	public void setGallery_file(String gallery_file) {
		this.gallery_file = gallery_file;
	}

	public String getGallery_thumb() {
		return gallery_thumb;
	}

	public void setGallery_thumb(String gallery_thumb) {
		this.gallery_thumb = gallery_thumb;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
