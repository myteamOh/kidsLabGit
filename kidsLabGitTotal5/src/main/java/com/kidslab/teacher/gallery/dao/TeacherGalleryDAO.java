package com.kidslab.teacher.gallery.dao;

import java.util.List;

import com.kidslab.teacher.gallery.vo.TeacherGalleryVO;

public interface TeacherGalleryDAO {

	public List<TeacherGalleryVO> galleryList();

	public int galleryInsert(TeacherGalleryVO tgvo);

	public TeacherGalleryVO galleryDetail(TeacherGalleryVO tgvo);

	public int galleryUpdate(TeacherGalleryVO tgvo);

	public int galleryDelete(int gallery_no);

	public int galleryListCnt(TeacherGalleryVO tgvo);

}
