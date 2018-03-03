package com.kidslab.admin.jointeacher.dao;

import com.kidslab.admin.jointeacher.vo.TeacherSecurity;
import com.kidslab.admin.jointeacher.vo.TeacherVO;

public interface TeacherDao {
	public int securityInsert(TeacherSecurity set);

	public TeacherSecurity securitySelect(String userId);

	public int securityDelete(String userId);

	public TeacherVO teacherSelect(String teacher_id);

	public int teacherInsert(TeacherVO tvo);

	public int teacherUpdate(TeacherVO tvo);

	public int teacherDelete(String teacher_id);
}
