package com.kidslab.common.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileViewAction {
	// C 드라이브에 있는 파일 목록을 리스트로 리턴하는
	// 메소드
	public List<String> execute() {
		// 파일 경로 만들기
		File f = new File("c:\\");
		// f의 하위 목록 가져오기
		String[] ar = f.list();
		// 하위 목록에서 파일(확장자가 있는)들만
		// 리스트에 추가
		List<String> list = new ArrayList<String>();
		for (String imsi : ar) {
			if (imsi.indexOf('.') != -1) {
				list.add(imsi);
			}
		}
		return list;
	}
}
