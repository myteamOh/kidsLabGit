package com.kidslab.client.coursepage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.course.vo.CourseVO;
import com.kidslab.client.coursedata.vo.CourseDataVO;

@Repository
public class CoursePageDaoImpl implements CoursePageDao {
	
	@Autowired
	private SqlSession session;

	// 강의 정보 가져오기
	@Override
	public CourseVO selectCourse(CourseVO cvo) {
		return session.selectOne("selectCourse", cvo);
	}

	// 리스트 5개
	@Override
	public List<CourseDataVO> homeCourseDataList(CourseDataVO cdvo) {
		return session.selectList("homeCourseDataList", cdvo);
	}

	// 리스트 카운트
	@Override
	public int coursePageListCnt(CourseDataVO cdvo) {
		return (Integer) session.selectOne("coursePageListCnt");
	}

	// 글입력처리
	@Override
	public int coursePageInsert(CourseDataVO cdvo) {
		return session.insert("coursePageInsert", cdvo);
	}

	// 글 상세보기
	@Override
	public CourseDataVO coursePageDetail(CourseDataVO cdvo) {
		return session.selectOne("coursePageDetail", cdvo);
	}

	// 글 수정 처리
	@Override
	public int coursePageUpdate(CourseDataVO cdvo) {
		return session.update("coursePageUpdate", cdvo);
	}

	// 글 삭제
	@Override
	public int coursePageDelete(int coursedata_no) {
		return session.delete("coursePageDelete", coursedata_no);
	}

}
