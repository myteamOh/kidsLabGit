--<ScriptOptions statementTerminator=";"/>

-- 회원 테이블 (학부모)
CREATE TABLE PARENT(
	PARENT_NO NUMBER,
	PARENT_ID VARCHAR2(30) NOT NULL, 
	PARENT_PASSWORD VARCHAR2(100) NOT NULL , 
	PARENT_NAME VARCHAR2(20) NOT NULL , 
	PARENT_PHONE VARCHAR2(20) NOT NULL,
	PARENT_TERMSAGREE VARCHAR2(2) NOT NULL,
	PARENT_KAKAOAGREE VARCHAR2(2) NOT NULL,
	PARENT_SMSAGREE VARCHAR2(2) NOT NULL,
	PARENT_EMAILAGREE VARCHAR2(2) NOT NULL,
	PARENT_STATUS VARCHAR2(20) DEFAULT '가입',
	PARENT_REGISTERDATE DATE DEFAULT SYSDATE,
	PARENT_KNOWROUTE VARCHAR2(30) NOT NULL,
	PARENT_ADDRESS VARCHAR2(100) NOT NULL,
	CONSTRAINT PARENT_PK PRIMARY KEY(PARENT_NO),
	CONSTRAINT PARENT_UK UNIQUE(PARENT_ID)
);
-- 회원 테이블 (학생)
CREATE TABLE STUDENT(
	STUDENT_NO NUMBER NOT NULL,
	STUDENT_ID VARCHAR2(30) NOT NULL,
	STUDENT_PASSWORD VARCHAR2(100) NOT NULL,
	STUDENT_NAME VARCHAR2(20) NOT NULL,
	STUDENT_GENDER VARCHAR2(10) NOT NULL,
	STUDENT_SCHOOL VARCHAR2(30) NOT NULL,
	STUDENT_BIRTHDAY DATE NOT NULL,
	STUDENT_REFERENCE VARCHAR2(500) DEFAULT 'EMPTY',
	STUDENT_REGISTERDATE DATE DEFAULT SYSDATE,
	STUDENT_EVALUATION VARCHAR2(500) DEFAULT 'EMPTY',
	STUDENT_STATUS VARCHAR2(20) DEFAULT '가입',
	PARENT_NO NUMBER NOT NULL,
	CONSTRAINT STUDENT_PK PRIMARY KEY(STUDENT_NO),
	CONSTRAINT STUDENT_UK UNIQUE(STUDENT_ID),
	CONSTRAINT STUDENT_FK FOREIGN KEY(PARENT_NO)
	REFERENCES PARENT(PARENT_NO)
);
-- 회원 암호화 테이블 (학생, 학부모)
-- 해시함수 솔트값을 저장하기 위한 테이블(비밀번호 암호화)
CREATE TABLE USER_SECURITY(
	USER_ID VARCHAR2(70),
	SALT VARCHAR2(70),
	CONSTRAINT USER_SECURITY_PK PRIMARY KEY(USER_ID)
);

-- 관리자 테이블
CREATE TABLE ADMIN(
	ADMIN_NO NUMBER NOT NULL,
	ADMIN_ID VARCHAR2(30) NOT NULL,
	ADMIN_PASSWORD VARCHAR2(40) NOT NULL,
	CONSTRAINT ADMIN_PK PRIMARY KEY(ADMIN_NO),
	CONSTRAINT ADMIN_UK UNIQUE(ADMIN_ID)
);

-- 강사 테이블
CREATE TABLE TEACHER(
	TEACHER_NO NUMBER NOT NULL,
	TEACHER_ID VARCHAR2(30) NOT NULL,
	TEACHER_PASSWORD VARCHAR2(100) NOT NULL,
	TEACHER_NAME VARCHAR2(20) NOT NULL,
	TEACHER_PHONE VARCHAR2(20) NOT NULL,
	TEACHER_PHOTO VARCHAR2(100) NOT NULL,
	TEACHER_REGISTERDATE DATE DEFAULT SYSDATE,
	TEACHER_STATUS VARCHAR2(20) DEFAULT '가입',
	TEACHER_THUMB VARCHAR2(100) NOT NULL,
	ADMIN_NO NUMBER NOT NULL,
	CONSTRAINT TEACHER_PK PRIMARY KEY(TEACHER_NO),
	CONSTRAINT TEACHER_UK UNIQUE(TEACHER_ID),
	CONSTRAINT TEACHER_FK FOREIGN KEY(ADMIN_NO)
	REFERENCES ADMIN(ADMIN_NO)
);

-- 강사 암호화 테이블
-- 해시함수 솔트값을 저장하기 위한 테이블(비밀번호 암호화)
CREATE TABLE SECURITY(
	USERID VARCHAR2(70),
	SALT VARCHAR2(70),
	CONSTRAINT SECURITY_PK PRIMARY KEY(USERID)
);

-- 로그인 정보 저장 테이블
CREATE TABLE LOGIN_HISTORY(
	idx NUMBER, userid VARCHAR2(70),
	retry NUMBER DEFAULT 0,
	lastFailedLogin NUMBER DEFAULT 0,
	lastSuccessedLogin NUMBER DEFAULT 0,
	clientIp VARCHAR2(15),
	CONSTRAINT login_history_pk PRIMARY KEY(idx) 
);

COMMENT ON TABLE login_history is '로그인 정보 저장 테이블'; 
COMMENT ON COLUMN login_history.idx is '순번';
COMMENT ON COLUMN login_history.userid is '로그인 아이디';
COMMENT ON COLUMN login_history.retry is '로그인 시도 횟수'; 
COMMENT ON COLUMN login_history.lastfailedlogin is '마지막으로 실패한 로그인 시간'; 
COMMENT ON COLUMN login_history.lastsuccessedlogin is '마지막으로 성공한 로그인 시간'; 
COMMENT ON COLUMN login_history.clientip is '로그인한 사용자의 ip 주소'; 

-- 강의 테이블
CREATE TABLE COURSE(
	COURSE_NO NUMBER NOT NULL,
	COURSE_SUMMARY VARCHAR2(200) NOT NULL,
	COURSE_NAME VARCHAR2(50) NOT NULL,
	COURSE_SUBJECT VARCHAR2(20) NOT NULL,
	COURSE_LEVEL VARCHAR2(30) NOT NULL,
	COURSE_TIME VARCHAR2(100) DEFAULT '미정',
	COURSE_TOTALPERSON NUMBER DEFAULT '10',
	COURSE_ROOM NUMBER DEFAULT '0',
	COURSE_PLAN VARCHAR2(150) NOT NULL,
	COURSE_PAY NUMBER DEFAULT '0',
	COURSE_REGISTERDATE DATE DEFAULT SYSDATE,
	COURSE_STATUS VARCHAR2(20) DEFAULT '등록대기',
	COURSE_STARTDATE DATE DEFAULT SYSDATE,
	COURSE_ENDDATE DATE DEFAULT SYSDATE,
	TEACHER_NO NUMBER NOT NULL,
	CONSTRAINT COURSE_PK PRIMARY KEY(COURSE_NO),
	CONSTRAINT COURSE_FK FOREIGN KEY(TEACHER_NO)
	REFERENCES TEACHER(TEACHER_NO)
);

-- 수강 신청 테이블
CREATE TABLE REQUESTCOURSE(
	REQUESTCOURSE_NO NUMBER NOT NULL,
	REQUESTCOURSE_PAYMETHOD VARCHAR2(20) NOT NULL,
	REQUESTCOURSE_PAYAMOUNT NUMBER NOT NULL,
	REQUESTCOURSE_PAYMENTDATE DATE DEFAULT SYSDATE,
	-- 결제대기, 결제완료, 환불대기, 환불완료
	REQUESTCOURSE_PAYMENTSTATUS VARCHAR2(20) DEFAULT '결제대기',
	REQUESTCOURSE_ACCOUNTHOLDER VARCHAR2(20) DEFAULT '없음',
	REQUESTCOURSE_ACCOUNTNUMBER VARCHAR2(30) DEFAULT '없음',
	REQUESTCOURSE_REFUNDCHARGE NUMBER DEFAULT '0',
	REQUESTCOURSE_REFUNDBANK VARCHAR2(30) DEFAULT '없음',
	REQUESTCOURSE_PAYCOMPLETEDATE DATE,
	REQUESTCOURSE_REFUNDDATE DATE,
	REQUESTCOURSE_REFUNDCOMPLETE DATE,
	PARENT_NO NUMBER NOT NULL,
	STUDENT_NO NUMBER NOT NULL,
	COURSE_NO NUMBER NOT NULL,
	CONSTRAINT REQUESTCOURSE_PK PRIMARY KEY(REQUESTCOURSE_NO),
	CONSTRAINT REQUESTCOURSE_PARENT_FK FOREIGN KEY(PARENT_NO)
	REFERENCES PARENT(PARENT_NO),
	CONSTRAINT REQUESTCOURSE_STUDENT_FK FOREIGN KEY(STUDENT_NO)
	REFERENCES STUDENT(STUDENT_NO),
	CONSTRAINT REQUESTCOURSE_COURSE_FK FOREIGN KEY(COURSE_NO)
	REFERENCES COURSE(COURSE_NO)
);

-- 강의관련 게시판
CREATE TABLE BOARD_COURSEDATA(
	COURSEDATA_NO NUMBER NOT NULL,
	COURSEDATA_TITLE VARCHAR2(100) NOT NULL,
	COURSEDATA_WRITER VARCHAR2(40) NOT NULL,
	COURSEDATA_CONTENT VARCHAR2(1000) NOT NULL,
	COURSEDATA_REGISTERDATE DATE DEFAULT SYSDATE,
	COURSEDATA_FILE VARCHAR2(150) NULL,
	COURSEDATA_STATUS VARCHAR2(20) NOT NULL,
	COURSE_NO NUMBER NOT NULL,
	CONSTRAINT BOARD_COURSEDATA_PK PRIMARY KEY(COURSEDATA_NO),
	CONSTRAINT BOARD_COURSEDATA_FK FOREIGN KEY(COURSE_NO)
	REFERENCES COURSE(COURSE_NO)
);

-- 갤러리
CREATE TABLE GALLERY(
	GALLERY_NO NUMBER NOT NULL,
	GALLERY_TITLE VARCHAR2(100) NOT NULL,
	GALLERY_CONTENT VARCHAR2(1000) NOT NULL,
	GALLERY_REGISTERDATE DATE DEFAULT SYSDATE,
	GALLERY_FILE VARCHAR2(150) NOT NULL,
	GALLERY_THUMB VARCHAR2(150) NOT NULL,
	TEACHER_NO NUMBER NOT NULL,
	CONSTRAINT GALLERY_PK PRIMARY KEY(GALLERY_NO),
	CONSTRAINT GALLERY_FK FOREIGN KEY(TEACHER_NO)
	REFERENCES TEACHER(TEACHER_NO)
);

-- 1:1 문의 게시판
CREATE TABLE BOARD_INQUIRY(
	INQUIRY_NO NUMBER NOT NULL,
	INQUIRY_TITLE VARCHAR2(100) NOT NULL,
	INQUIRY_CONTENT VARCHAR2(1000) NOT NULL,
	INQUIRY_REGISTERDATE DATE DEFAULT SYSDATE,
	PARENT_NO NUMBER NOT NULL,
	CONSTRAINT BOARD_INQUIRY_PK PRIMARY KEY(INQUIRY_NO),
	CONSTRAINT BOARD_INQUIRY_FK FOREIGN KEY(PARENT_NO)
	REFERENCES PARENT(PARENT_NO)
);

-- 1:1문의 답글
CREATE TABLE BOARD_INQUIRY_REPLY(
	INQUIRY_REPLY_NO NUMBER NOT NULL,
	INQUIRY_REPLY_CONTENT VARCHAR2(1000) NOT NULL,
	INQUIRY_REPLY_REGISTERDATE DATE DEFAULT SYSDATE,
	ADMIN_NO NUMBER NOT NULL,
	INQUIRY_NO NUMBER NOT NULL,
	CONSTRAINT BOARD_INQUIRY_REPLY_PK PRIMARY KEY(INQUIRY_REPLY_NO),
	CONSTRAINT BOARD_INQUIRY_REPLY_ADMIN_FK FOREIGN KEY(ADMIN_NO)	
	REFERENCES ADMIN(ADMIN_NO),
	CONSTRAINT BOARD_INQUIRY_REPLY_FK FOREIGN KEY(INQUIRY_NO)
	REFERENCES BOARD_INQUIRY(INQUIRY_NO)
);

-- FAQ 
CREATE TABLE BOARD_FAQ(
	FAQ_NO NUMBER NOT NULL,
	FAQ_TITLE VARCHAR2(100) NOT NULL,
	FAQ_TYPE VARCHAR2(50) NOT NULL,
	FAQ_CONTENT VARCHAR2(1000) NOT NULL,
	ADMIN_NO NUMBER NOT NULL,
	CONSTRAINT BOARD_FAQ_PK PRIMARY KEY(FAQ_NO),
	CONSTRAINT BOARD_FAQ_FK FOREIGN KEY(ADMIN_NO)
	REFERENCES ADMIN(ADMIN_NO)
);

-- 공지사항
CREATE TABLE BOARD_NOTICE(
	NOTICE_NO NUMBER NOT NULL,
	NOTICE_TITLE VARCHAR2(100) NOT NULL,
	NOTICE_CONTENT VARCHAR2(2000) NOT NULL,
	NOTICE_REGISTERDATE DATE DEFAULT SYSDATE,
	ADMIN_NO NUMBER NOT NULL,
	CONSTRAINT BOARD_NOTICE_PK PRIMARY KEY(NOTICE_NO),
	CONSTRAINT BOARD_NOTICE_FK FOREIGN KEY(ADMIN_NO)
	REFERENCES ADMIN(ADMIN_NO)
);

CREATE SEQUENCE PARENT_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;



CREATE SEQUENCE STUDENT_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE ADMIN_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;

CREATE SEQUENCE TEACHER_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;

CREATE SEQUENCE COURSE_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE REQUESTCOURSE_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE BOARD_REVIEW_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;

CREATE SEQUENCE BOARD_COURSEDATA_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE GALLERY_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE BOARD_INQUIRY_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE BOARD_INQUIRY_REPLY_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE BOARD_FAQ_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


CREATE SEQUENCE BOARD_NOTICE_SEQ
START WITH 1
INCREMENT BY 1
NOCYCLE;


-- 로그인 정보 저장시 사용할 순번(시퀀스)
CREATE SEQUENCE LOGIN_HISTORY_SEQ;

insert into ADMIN
values(1, 'admin', 'test1234');

-- 테이블 초기화 --
delete from board_coursedata;
delete from gallery;
delete from board_inquiry_reply;
delete from board_inquiry;
delete from board_faq;
delete from board_notice;
delete from requestcourse;
delete from course;
delete from login_history;
delete from security;
delete from teacher;
delete from user_security;
delete from student;
delete from parent;

-- 시퀀스 초기화--
drop sequence parent_seq;
drop sequence student_seq;
drop sequence admin_seq;
drop sequence teacher_seq;
drop sequence course_seq;
drop sequence requestcourse_seq;
drop sequence board_coursedata_seq;
drop sequence gallery_seq;
drop sequence board_inquiry_seq;
drop sequence board_inquiry_reply_seq;
drop sequence board_faq_seq;
drop sequence board_notice_seq;
drop sequence login_history_seq;

select *
from parent;
	
select *
from USER_SECURITY;

select *
from student
where student_no = 62;

select *
from teacher
where teacher_name = '이하나';

select *
from requestcourse
where course_no = 13;
 
select *
from COURSE
where course_no = 3;

update course set course_status = '모집중' where course_no=1;


