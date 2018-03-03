package com.kidslab.teacher.login.dao;

import com.kidslab.teacher.login.vo.TeacherLoginVO;

public interface TeacherLoginDao {
	public TeacherLoginVO userIdSelect(String teacher_id);

	public TeacherLoginVO loginSelect(TeacherLoginVO lvo);

	public int loginHistoryInsert(TeacherLoginVO lvo);

	public int loginHistoryUpdate(TeacherLoginVO lvo);

	public TeacherLoginVO loginHistorySelect(String teacher_id);
}
