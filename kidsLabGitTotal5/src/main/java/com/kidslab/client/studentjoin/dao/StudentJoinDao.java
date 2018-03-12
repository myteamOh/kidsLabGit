package com.kidslab.client.studentjoin.dao;

import com.kidslab.client.student.vo.StudentVO;

public interface StudentJoinDao {

	public StudentVO studentSelect(String userId);

	public int studentInsert(StudentVO svo);

	public int studentUpdate(StudentVO svo);

}
