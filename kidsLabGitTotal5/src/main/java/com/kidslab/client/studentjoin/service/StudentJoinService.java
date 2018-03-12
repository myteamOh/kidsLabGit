package com.kidslab.client.studentjoin.service;

import com.kidslab.client.student.vo.StudentVO;

public interface StudentJoinService {
	
	public StudentVO studentSelect(String userId);

	public int studentIdConfirm(String userId);

	public int studentInsert(StudentVO svo);

	public boolean studentUpdate(StudentVO svo);

}
