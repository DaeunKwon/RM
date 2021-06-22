-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- tbl_member Table Create SQL
CREATE TABLE tbl_member
(
    email       VARCHAR2(100)    NOT NULL, 
    name        VARCHAR2(100)    NOT NULL, 
    password    VARCHAR2(100)    NOT NULL, 
    rmv_YN      CHAR(1)          DEFAULT 'N' NOT NULL, 
    CONSTRAINT PK_tbl_member PRIMARY KEY (email)
)
/

COMMENT ON TABLE tbl_member IS '직원 테이블'
/

COMMENT ON COLUMN tbl_member.email IS '이메일'
/

COMMENT ON COLUMN tbl_member.name IS '이름'
/

COMMENT ON COLUMN tbl_member.password IS '비밀번호'
/

COMMENT ON COLUMN tbl_member.rmv_YN IS '삭제여부'
/


-- tbl_member Table Create SQL
CREATE TABLE tbl_project
(
    prj_no            NUMBER            NOT NULL, 
    prj_title         VARCHAR2(200)     NOT NULL, 
    start_d8          DATE              NOT NULL, 
    end_d8            DATE              NOT NULL, 
    prj_writer        VARCHAR2(100)     NOT NULL, 
    prj_mod_writer    VARCHAR2(100)     NOT NULL, 
    prj_write_d8      DATE              DEFAULT sysdate NOT NULL, 
    prj_mod_d8        DATE              DEFAULT sysdate NOT NULL, 
    prj_content       VARCHAR2(2000)    NOT NULL, 
    prj_remark        VARCHAR2(1000)    DEFAULT null NULL, 
    cond              VARCHAR2(20)      NOT NULL, 
    rmv_YN            CHAR(1)           DEFAULT 'N' NOT NULL, 
    CONSTRAINT PK_tbl_project PRIMARY KEY (prj_no)
)
/

CREATE SEQUENCE tbl_project_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER tbl_project_AI_TRG
BEFORE INSERT ON tbl_project 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT tbl_project_SEQ.NEXTVAL
    INTO :NEW.prj_no
    FROM DUAL;
END;
/

--DROP TRIGGER tbl_project_AI_TRG;
/

--DROP SEQUENCE tbl_project_SEQ;
/

COMMENT ON TABLE tbl_project IS '프로젝트'
/

COMMENT ON COLUMN tbl_project.prj_no IS '프로젝트 번호'
/

COMMENT ON COLUMN tbl_project.prj_title IS '프로젝트 이름'
/

COMMENT ON COLUMN tbl_project.start_d8 IS '시작 날짜'
/

COMMENT ON COLUMN tbl_project.end_d8 IS '종료 날짜'
/

COMMENT ON COLUMN tbl_project.prj_writer IS '프로젝트 작성자'
/

COMMENT ON COLUMN tbl_project.prj_mod_writer IS '프로젝트 수정자'
/

COMMENT ON COLUMN tbl_project.prj_write_d8 IS '프로젝트 작성 날짜'
/

COMMENT ON COLUMN tbl_project.prj_mod_d8 IS '프로젝트 수정 날짜'
/

COMMENT ON COLUMN tbl_project.prj_content IS '프로젝트 내용'
/

COMMENT ON COLUMN tbl_project.prj_remark IS '프로젝트 특이사항'
/

COMMENT ON COLUMN tbl_project.cond IS '진행 상황'
/

COMMENT ON COLUMN tbl_project.rmv_YN IS '삭제 여부'
/


-- tbl_member Table Create SQL
CREATE TABLE tbl_project_in
(
    prj_in_no     NUMBER           NOT NULL, 
    email         VARCHAR2(100)    NOT NULL, 
    authority     VARCHAR2(50)     NOT NULL, 
    prj_in_d8     DATE             NOT NULL, 
    prj_out_d8    DATE             NOT NULL, 
    prj_no        NUMBER           NOT NULL, 
    CONSTRAINT PK_tbl_project_in PRIMARY KEY (prj_in_no)
)
/

CREATE SEQUENCE tbl_project_in_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER tbl_project_in_AI_TRG
BEFORE INSERT ON tbl_project_in 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT tbl_project_in_SEQ.NEXTVAL
    INTO :NEW.prj_in_no
    FROM DUAL;
END;
/

--DROP TRIGGER tbl_project_in_AI_TRG;
/

--DROP SEQUENCE tbl_project_in_SEQ;
/

COMMENT ON TABLE tbl_project_in IS '프로젝트 참여원'
/

COMMENT ON COLUMN tbl_project_in.prj_in_no IS '프로젝트 참여 번호'
/

COMMENT ON COLUMN tbl_project_in.email IS '이메일'
/

COMMENT ON COLUMN tbl_project_in.auth_id IS '권한 아이디'
/

COMMENT ON COLUMN tbl_project_in.prj_in_d8 IS '참여 시작 날짜'
/

COMMENT ON COLUMN tbl_project_in.prj_out_d8 IS '참여 종료 날짜'
/

COMMENT ON COLUMN tbl_project_in.prj_no IS '프로젝트 번호'
/

ALTER TABLE tbl_project_in
    ADD CONSTRAINT FK_tbl_project_in_email_tbl_me FOREIGN KEY (email)
        REFERENCES tbl_member (email)
/

ALTER TABLE tbl_project_in
    ADD CONSTRAINT FK_tbl_project_in_prj_no_tbl_p FOREIGN KEY (prj_no)
        REFERENCES tbl_project (prj_no)
/

ALTER TABLE tbl_project_in
    ADD CONSTRAINT FK_tbl_project_in_authority FOREIGN KEY (authority)
        REFERENCES roles (authority)
/


-- tbl_member Table Create SQL
CREATE TABLE tbl_report
(
    rpt_no            NUMBER     NOT NULL, 
    prj_no            NUMBER     NOT NULL, 
    rmv_YN            CHAR(1)    DEFAULT 'N' NOT NULL, 
    rpt_write_d8      DATE       DEFAULT sysdate NOT NULL, 
    rpt_writer        NUMBER     NOT NULL, 
    rpt_mod_d8        DATE       DEFAULT sysdate NOT NULL, 
    rpt_mod_writer    NUMBER     NOT NULL, 
    CONSTRAINT PK_tbl_report PRIMARY KEY (rpt_no)
)
/

CREATE SEQUENCE tbl_report_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER tbl_report_AI_TRG
BEFORE INSERT ON tbl_report 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT tbl_report_SEQ.NEXTVAL
    INTO :NEW.rpt_no
    FROM DUAL;
END;
/

--DROP TRIGGER tbl_report_AI_TRG;
/

--DROP SEQUENCE tbl_report_SEQ;
/

COMMENT ON TABLE tbl_report IS '업무 일지'
/

COMMENT ON COLUMN tbl_report.rpt_no IS '업무 일지 번호'
/

COMMENT ON COLUMN tbl_report.prj_no IS '프로젝트 번호'
/

COMMENT ON COLUMN tbl_report.rmv_YN IS '삭제 여부'
/

COMMENT ON COLUMN tbl_report.rpt_write_d8 IS '업무 일지 작성 날짜'
/

COMMENT ON COLUMN tbl_report.rpt_writer IS '업무 일지 작성자'
/

COMMENT ON COLUMN tbl_report.rpt_mod_d8 IS '업무 일지 수정 날짜'
/

COMMENT ON COLUMN tbl_report.rpt_mod_writer IS '업무 일지 수정자'
/

ALTER TABLE tbl_report
    ADD CONSTRAINT FK_tbl_report_prj_no_tbl_proje FOREIGN KEY (prj_no)
        REFERENCES tbl_project (prj_no)
/

ALTER TABLE tbl_report
    ADD CONSTRAINT FK_tbl_report_rpt_writer_tbl_p FOREIGN KEY (rpt_writer)
        REFERENCES tbl_project_in (prj_in_no)
/

ALTER TABLE tbl_report
    ADD CONSTRAINT FK_tbl_report_rpt_mod_writer_t FOREIGN KEY (rpt_mod_writer)
        REFERENCES tbl_project_in (prj_in_no)
/


-- tbl_member Table Create SQL
CREATE TABLE roles
(
    authority      VARCHAR2(50)     NOT NULL, 
    description    VARCHAR2(100)    NOT NULL, 
    auth_level     NUMBER           NOT NULL, 
    CONSTRAINT  PK_roles PRIMARY KEY (authority)
)
/

COMMENT ON TABLE roles IS '권한 내용'
/

COMMENT ON COLUMN roles.authority IS '권한 아이디'
/

COMMENT ON COLUMN roles.description IS '권한 이름'
/

COMMENT ON COLUMN roles.auth_level IS '권한 번호'
/


-- tbl_member Table Create SQL
-- CREATE TABLE tbl_commute
-- (
--     com_no       number    NOT NULL, 
--     com_d8       DATE      NOT NULL, 
--     com_start    DATE      NOT NULL, 
--     com_end      DATE      NULL, 
--     com_total    NUMBER    NULL, 
--     prj_in_no    NUMBER    NOT NULL, 
--     CONSTRAINT primary key PRIMARY KEY (com_no)
-- )
-- /

-- CREATE SEQUENCE tbl_commute_SEQ
-- START WITH 1
-- INCREMENT BY 1;
-- /

-- CREATE OR REPLACE TRIGGER tbl_commute_AI_TRG
-- BEFORE INSERT ON tbl_commute 
-- REFERENCING NEW AS NEW FOR EACH ROW 
-- BEGIN 
--     SELECT tbl_commute_SEQ.NEXTVAL
--     INTO :NEW.com_no
--     FROM DUAL;
-- END;
-- /

-- --DROP TRIGGER tbl_commute_AI_TRG;
-- /

-- --DROP SEQUENCE tbl_commute_SEQ;
-- /

-- COMMENT ON COLUMN tbl_commute.com_no IS '근태 번호'
-- /

-- COMMENT ON COLUMN tbl_commute.com_d8 IS '근태 날짜'
-- /

-- COMMENT ON COLUMN tbl_commute.com_start IS '출근시간'
-- /

-- COMMENT ON COLUMN tbl_commute.com_end IS '퇴근시간'
-- /

-- COMMENT ON COLUMN tbl_commute.com_total IS '총 근무시간'
-- /

-- COMMENT ON COLUMN tbl_commute.prj_in_no IS '프로젝트 참여 번호'
-- /

-- ALTER TABLE tbl_commute
--     ADD CONSTRAINT FK_tbl_commute_prj_in_no_tbl_p FOREIGN KEY (prj_in_no)
--         REFERENCES tbl_project_in (prj_in_no)
-- /


-- tbl_member Table Create SQL
CREATE TABLE tbl_report_detail
(
    rpt_detail_no     NUMBER            NOT NULL, 
    rpt_start_time    DATE              NOT NULL, 
    rpt_end_time      DATE              NOT NULL, 
    rpt_content       VARCHAR2(2000)    NOT NULL, 
    rpt_no            NUMBER            NOT NULL, 
    CONSTRAINT  PK_tbl_report_detail PRIMARY KEY (rpt_detail_no)
)
/

CREATE SEQUENCE tbl_report_detail_SEQ
START WITH 1
INCREMENT BY 1;
/

CREATE OR REPLACE TRIGGER tbl_report_detail_AI_TRG
BEFORE INSERT ON tbl_report_detail 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT tbl_report_detail_SEQ.NEXTVAL
    INTO :NEW.rpt_detail_no
    FROM DUAL;
END;
/

--DROP TRIGGER tbl_report_detail_AI_TRG;
/

--DROP SEQUENCE tbl_report_detail_SEQ;
/

COMMENT ON TABLE tbl_report_detail IS '업무 일지 상세 내용'
/

COMMENT ON COLUMN tbl_report_detail.rpt_detail_no IS '업무 일지 내용 번호'
/

COMMENT ON COLUMN tbl_report_detail.rpt_start_time IS '시작 시간'
/

COMMENT ON COLUMN tbl_report_detail.rpt_end_time IS '끝난 시간'
/

COMMENT ON COLUMN tbl_report_detail.rpt_content IS '업무 일지 내용'
/

COMMENT ON COLUMN tbl_report_detail.rpt_no IS '업무 일지 번호'
/

ALTER TABLE tbl_report_detail
    ADD CONSTRAINT FK_tbl_report_detail_rpt_no_tb FOREIGN KEY (rpt_no)
        REFERENCES tbl_report (rpt_no)
/


-- tbl_member Table Create SQL
CREATE TABLE authorities
(
    member_email       VARCHAR2(100)    NOT NULL, 
    roles_authority    VARCHAR2(50)     NOT NULL, 
    CONSTRAINT  PK_authorities PRIMARY KEY (member_email, roles_authority)
)
/

COMMENT ON TABLE authorities IS '권한'
/

COMMENT ON COLUMN authorities.member_email IS '이메일'
/

COMMENT ON COLUMN authorities.roles_authority IS '권한'
/

ALTER TABLE authorities
    ADD CONSTRAINT FK_authorities_member_email_tb FOREIGN KEY (member_email)
        REFERENCES tbl_member (email)
/

ALTER TABLE authorities
    ADD CONSTRAINT FK_authorities_roles_authority FOREIGN KEY (roles_authority)
        REFERENCES roles (authority)
/


