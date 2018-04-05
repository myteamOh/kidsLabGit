package com.kidslab.teacher.gallery.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kidslab.admin.jointeacher.service.TeacherServiceImpl;
import com.kidslab.teacher.gallery.dao.TeacherGalleryDAO;
import com.kidslab.teacher.gallery.vo.TeacherGalleryVO;


@Service
@Transactional
public class TeacherGalleryServiceImpl implements TeacherGalleryService {
	
	Logger logger = Logger.getLogger(TeacherServiceImpl.class);
	
	@Autowired
	private TeacherGalleryDAO teacherGalleryDAO;
	
	// 갤러리 목록 구현
	@Override
	public List<TeacherGalleryVO> galleryList(TeacherGalleryVO tgvo) {
		
		List<TeacherGalleryVO> list = null;
		
		list = teacherGalleryDAO.galleryList();
		
		return list;
	}
	
	// 갤러리 입력 구현
	@Override
	public int galleryInsert(TeacherGalleryVO tgvo) {

		int result = 0;
	
		try {
			
			result = teacherGalleryDAO.galleryInsert(tgvo); 
					
		} catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
	
	// 갤러리 상세 구현
	@Override
	public TeacherGalleryVO galleryDetail(TeacherGalleryVO tgvo) {
		
		TeacherGalleryVO detail = null;
		
		detail = teacherGalleryDAO.galleryDetail(tgvo);
				
		return detail;
	}
	
		
	// 글 카운터
	@Override
	public int galleryListCnt(TeacherGalleryVO tgvo) {
		return teacherGalleryDAO.galleryListCnt(tgvo);
	}
	
	
	// 갤러리 업데이트
	@Override
	public int galleryUpdate(TeacherGalleryVO tgvo) {

		int result = 0;

		try {
			result = teacherGalleryDAO.galleryUpdate(tgvo);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
	
	// 갤러리 삭제
	@Override
	public int galleryDelete(int gallery_no) {
	
		int result = 0;
	
		try {
			result = teacherGalleryDAO.galleryDelete(gallery_no);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}

}
