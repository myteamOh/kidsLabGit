package com.kidslab.teacher.gallery.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.teacher.gallery.vo.TeacherGalleryVO;

@Repository
public class TeacherGalleryDAOImpl implements TeacherGalleryDAO {

	@Autowired
	private SqlSession session;

	// 갤러리 목록 구현
	@Override
	public List<TeacherGalleryVO> galleryList() {
		// TODO Auto-generated method stub
		return session.selectList("galleryList");
	}

	// 갤러리 입력 구현
	@Override
	public int galleryInsert(TeacherGalleryVO tgvo) {
		// TODO Auto-generated method stub
		return session.insert("galleryInsert", tgvo);
	}

	// 갤러리 상세 구현
	@Override
	public TeacherGalleryVO galleryDetail(TeacherGalleryVO tgvo) {
		// TODO Auto-generated method stub
		return (TeacherGalleryVO) session.selectOne("galleryDetail", tgvo);
	}

	// 갤러리 수정 구현
	@Override
	public int galleryUpdate(TeacherGalleryVO tgvo) {
		// TODO Auto-generated method stub
		return session.update("galleryUpdate");
	}

	// 갤러리 삭제 구현
	@Override
	public int galleryDelete(int gallery_no) {
		// TODO Auto-generated method stub
		return session.delete("galleryDelete", gallery_no);
	}
	
	
	// 전체 레코드 건수 구현
	@Override
	public int galleryListCnt(TeacherGalleryVO tgvo) {
		
		return (Integer)session.selectOne("galleryListCnt", tgvo);
	}
	

}
