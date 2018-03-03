package com.kidslab.admin.jointeacher.service;

import com.kidslab.admin.jointeacher.vo.TeacherVO;

public interface TeacherService {
	public int userIdConfirm(String teacher_id);

	public TeacherVO teacherSelect(String teacher_id);

	public int teacherInsert(TeacherVO tvo);

	public boolean teacherUpdate(TeacherVO tvo);

	public int teacherDelete(String teacher_id);
}
