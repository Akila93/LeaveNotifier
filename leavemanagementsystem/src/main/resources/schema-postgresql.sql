DROP DATABASE LMS;
CREATE DATABASE LMS;
CONNECT LMS;

CREATE TABLE DEPARTMENT(
  DEP_ID  BIGSERIAL PRIMARY KEY     NOT NULL,
  DEP_NAME  VARCHAR NOT NULL
);


CREATE TABLE USERH(
  USERID  BIGSERIAL PRIMARY KEY     NOT NULL,
  NAME           TEXT    NOT NULL,
  EMAIL  VARCHAR UNIQUE  NOT NULL,
  ROLE VARCHAR NOT NULL,
  DEP_ID INT    NOT NULL  REFERENCES DEPARTMENT(DEP_ID)

);

CREATE TABLE LEAVE(

  USERID INT    NOT NULL  REFERENCES USERH(USERID) ,
  LEAVEDATE           DATE  NOT NULL,
  LEAVETYPE           VARCHAR   NOT NULL,
  REASON VARCHAR (50),
  COMMENT VARCHAR (100),
  PRIMARY KEY(USERID,LEAVEDATE)

);


INSERT INTO DEPARTMENT(DEP_NAME) VALUES ('Engineering');
INSERT INTO DEPARTMENT(DEP_NAME) VALUES ('Delivery Assurance');
INSERT INTO DEPARTMENT(DEP_NAME) VALUES ('Business Analyst');
INSERT INTO DEPARTMENT(DEP_NAME) VALUES ('Human Resources');


INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Nuwantha','nuwanthad@hsenidmobile.com','ROLE_ADMIN',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Akila','akilac@hsenidmobile.com','ROLE_ADMIN',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Sadarenu','sadarenu@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Harsha','harsha@hsenidmobile.com','User','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Udara','udara@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Naveen','naveen@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Vbujithan','vbujithan@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Pesala','pesala@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Vmukthi','vmukthi@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Udayanga','udayanga@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Prageeth','prageeth@hsenidmobile.com','ROLE_USER',2);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Hasitha','hasitha@hsenidmobile.com','ROLE_USER',3);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Ajith','ajith@hsenidmobile.com','ROLE_USER',3);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Rajitha','rajitha@hsenidmobile.com','ROLE_USER',3);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Thilina','thilina@hsenidmobile.com','ROLE_USER',3);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Tiran','tiran@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Eshan','eshan@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Tharanga','tharanga@hsenidmobile.com','ROLE_USER',2);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Sanidda','sanidda@hsenidmobile.com','ROLE_USER',1);
INSERT INTO USERH(name,email,role,DEP_ID) VALUES ('Isuri','isuri@hsenidmobile.com','ROLE_USER',4);




INSERT INTO LEAVE VALUES ('1','2016-12-02','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2016-12-02','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2016-12-02','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2016-12-02','first half','sick','');
INSERT INTO LEAVE VALUES ('5','2016-12-02','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2016-12-02','second half','sick','');
INSERT INTO LEAVE VALUES ('1','2016-12-04','first half','sick','');
INSERT INTO LEAVE VALUES ('2','2016-12-04','first half','sick','');
INSERT INTO LEAVE VALUES ('3','2016-12-04','second half','sick','');
INSERT INTO LEAVE VALUES ('4','2016-12-04','second half','sick','');
INSERT INTO LEAVE VALUES ('5','2016-12-04','full day','sick','');
INSERT INTO LEAVE VALUES ('6','2016-12-04','full day','sick','');
INSERT INTO LEAVE VALUES ('1','2016-12-07','second half','sick','');
INSERT INTO LEAVE VALUES ('2','2016-12-07','second half','sick','');
INSERT INTO LEAVE VALUES ('3','2016-12-07','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2016-12-07','full day','sick','');
INSERT INTO LEAVE VALUES ('5','2016-12-07','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2016-12-07','second half','sick','');






INSERT INTO LEAVE VALUES ('1','2016-12-14','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2016-12-14','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2016-12-14','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2016-12-14','first half','sick','');
INSERT INTO LEAVE VALUES ('5','2016-12-14','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2016-12-14','second half','sick','');
INSERT INTO LEAVE VALUES ('1','2016-11-14','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2016-11-14','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2016-11-14','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2016-11-14','first half','sick','');
INSERT INTO LEAVE VALUES ('5','2016-11-14','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2016-11-14','second half','sick','');
INSERT INTO LEAVE VALUES ('1','2016-05-14','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2016-05-14','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2016-05-14','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2016-05-14','first half','sick','');
INSERT INTO LEAVE VALUES ('5','2016-05-14','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2016-05-14','second half','sick','');
INSERT INTO LEAVE VALUES ('1','2016-01-14','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2016-01-14','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2016-01-14','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2016-01-14','first half','sick','');
INSERT INTO LEAVE VALUES ('5','2016-01-14','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2016-01-14','second half','sick','');
INSERT INTO LEAVE VALUES ('1','2015-01-14','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2015-01-14','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2015-01-14','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2015-01-14','first half','sick','');
INSERT INTO LEAVE VALUES ('5','2015-01-14','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2015-01-14','second half','sick','');
INSERT INTO LEAVE VALUES ('1','2015-10-14','full day','sick','');
INSERT INTO LEAVE VALUES ('2','2015-10-14','full day','sick','');
INSERT INTO LEAVE VALUES ('3','2015-10-14','full day','sick','');
INSERT INTO LEAVE VALUES ('4','2015-10-14','first half','sick','');
INSERT INTO LEAVE VALUES ('5','2015-10-14','second half','sick','');
INSERT INTO LEAVE VALUES ('6','2015-10-14','second half','sick','');
INSERT INTO LEAVE VALUES ('7','2016-10-14','full day','sick','');
INSERT INTO LEAVE VALUES ('8','2016-10-14','full day','sick','');
INSERT INTO LEAVE VALUES ('9','2016-10-14','full day','sick','');
INSERT INTO LEAVE VALUES ('10','2016-10-14','first half','sick','');
INSERT INTO LEAVE VALUES ('11','2016-10-14','second half','sick','');
INSERT INTO LEAVE VALUES ('12','2016-10-14','second half','sick','');
INSERT INTO LEAVE VALUES ('7','2016-11-14','full day','sick','');
INSERT INTO LEAVE VALUES ('8','2016-11-14','full day','sick','');
INSERT INTO LEAVE VALUES ('9','2016-11-14','full day','sick','');
INSERT INTO LEAVE VALUES ('10','2016-11-14','first half','sick','');
INSERT INTO LEAVE VALUES ('11','2016-11-14','second half','sick','');
INSERT INTO LEAVE VALUES ('12','2016-11-14','second half','sick','');
INSERT INTO LEAVE VALUES ('7','2016-11-30','full day','sick','');
INSERT INTO LEAVE VALUES ('8','2016-11-30','full day','sick','');
INSERT INTO LEAVE VALUES ('9','2016-11-30','full day','sick','');
INSERT INTO LEAVE VALUES ('10','2016-11-30','first half','sick','');
INSERT INTO LEAVE VALUES ('11','2016-11-30','second half','sick','');
INSERT INTO LEAVE VALUES ('12','2016-11-30','second half','sick','')