package com.kidslab.teacher.gallery.service;

import java.util.List;
import com.kidslab.teacher.gallery.vo.TeacherGalleryVO;

public interface TeacherGalleryService {

	public List<TeacherGalleryVO> galleryList(TeacherGalleryVO tgvo);

	public int galleryInsert(TeacherGalleryVO tgvo);

	public TeacherGalleryVO galleryDetail(TeacherGalleryVO tgvo);

	public int galleryListCnt(TeacherGalleryVO tgvo);

	public int galleryUpdate(TeacherGalleryVO tgvo);

	public int galleryDelete(int gallery_no);

}
