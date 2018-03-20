package com.kidslab.client.studentjoin.dao;

import java.util.List;

import com.kidslab.client.parent.vo.ParentVO;
import com.kidslab.client.student.vo.StudentVO;

public interface StudentJoinDao {

	public StudentVO studentSelect(String userId);

	public int studentInsert(StudentVO svo);

	public int studentUpdate(StudentVO svo);
	
	public List<StudentVO> studentList(int parentNum);
	
	public int studentWithdraw(ParentVO pvo);

}
