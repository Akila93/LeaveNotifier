DROP DATABASE LMS;
CREATE DATABASE LMS;
CONNECT LMS;

CREATE TABLE USERH(
  USERID  BIGSERIAL PRIMARY KEY     NOT NULL,
  NAME           TEXT    NOT NULL,
  EMAIL  VARCHAR UNIQUE  NOT NULL,
  ROLE VARCHAR NOT NULL

);

CREATE TABLE LEAVE(

  USERID INT    NOT NULL  REFERENCES USERH(USERID) ,
  LEAVEDATE           DATE  NOT NULL,
  LEAVETYPE           VARCHAR   NOT NULL,
  REASON VARCHAR (50),
  COMMENT VARCHAR (100),
  PRIMARY KEY(USERID,LEAVEDATE)

);


INSERT INTO USERH(name,email,role) VALUES ('nuwantha','nuwanthad@hsenidmobile.com','ROLE_ADMIN');
INSERT INTO USERH(name,email,role) VALUES ('hameed','hameed@hsenidmobile.com','ROLE_USER');
INSERT INTO USERH(name,email,role) VALUES ('root','root@hsenidmobile.com','User','ROLE_USER');
INSERT INTO USERH(name,email,role) VALUES ('duncket','duncket@hsenidmobile.com','ROLE_USER');
INSERT INTO USERH(name,email,role) VALUES ('ali','ali@hsenidmobile.com','ROLE_USER');
INSERT INTO USERH(name,email,role) VALUES ('stroke','stroke@hsenidmobile.com','ROLE_USER');
INSERT INTO USERH(name,email,role) VALUES ('bestrow','bestrow@hsenidmobile.com','ROLE_USER');
INSERT INTO USERH(name,email,role) VALUES ('nuwantha','nuwanthad@hsenidmobile.com','ROLE_USER');


INSERT INTO LEAVE VALUES ('3','2016-10-01','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2016-10-02','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2016-10-10','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2016-10-01','first half','sick','');
INSERT INTO LEAVE VALUES ('2','2016-10-21','second half','sick','');
INSERT INTO LEAVE VALUES ('2','2016-10-25','second half','sick','');
