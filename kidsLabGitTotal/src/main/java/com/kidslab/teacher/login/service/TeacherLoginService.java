package com.kidslab.teacher.login.service;

import com.kidslab.teacher.login.vo.TeacherLoginVO;

public interface TeacherLoginService {
	public TeacherLoginVO userIdSelect(String teacher_id);

	public TeacherLoginVO loginSelect(String teacher_id, String teacher_password);

	public int loginHistoryInsert(TeacherLoginVO lvo);

	public int loginHistoryUpdate(TeacherLoginVO lvo);

	public TeacherLoginVO loginHistorySelect(String teacher_id);
}
