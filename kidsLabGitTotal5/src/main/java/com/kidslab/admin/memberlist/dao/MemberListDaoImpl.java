package com.kidslab.admin.memberlist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kidslab.admin.jointeacher.vo.TeacherVO;
import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.requestcourse.vo.RequestCourseVO;
import com.kidslab.client.student.vo.StudentVO;

@Repository
public class MemberListDaoImpl implements MemberListDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<StudentVO> studentList(StudentVO svo) {
		// TODO Auto-generated method stub
		return session.selectList("studentList", svo);
	}

	@Override
	public List<StudentVO> parentList(StudentVO svo) {
		// TODO Auto-generated method stub
		return session.selectList("parentList", svo);
	}

	@Override
	public List<TeacherVO> teacherList(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return session.selectList("teacherList", tvo);
	}

	@Override
	public int studentListCnt(StudentVO svo) {
		// TODO Auto-generated method stub
		return (Integer) session.selectOne("studentListCnt");
	}

	@Override
	public int parentListCnt(StudentVO svo) {
		// TODO Auto-generated method stub
		return (Integer) session.selectOne("parentListCnt");
	}

	@Override
	public int teacherListCnt(TeacherVO tvo) {
		// TODO Auto-generated method stub
		return (Integer) session.selectOne("teacherListCnt");
	}

	@Override
	public Map<String, Integer> joinRootList() {
		// TODO Auto-generated method stub
		return session.selectMap("joinRootList", "");
	}

	@Override
	public Map<String, Integer> studentAgeList() {
		// TODO Auto-generated method stub
		return session.selectMap("studentAgeList", "");
	}

	@Override
	public RequestCourseVO paymentStatsList(RequestCourseVO rvo) {
		// TODO Auto-generated method stub
		return session.selectOne("paymentStatsList", rvo);
	}

	@Override
	public RequestCourseVO refundStatsList(RequestCourseVO rvo) {
		// TODO Auto-generated method stub
		return session.selectOne("refundStatsList", rvo);
	}

}
