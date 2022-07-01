CREATE TABLE PRBOARD(
    b_num NUMBER(10) PRIMARY KEY, 
    b_writer VARCHAR2(20) NOT NULL,
    b_title VARCHAR2(30) NOT NULL, 
    b_content VARCHAR2(1000), 
    b_date DATE default sysdate
);

CREATE SEQUENCE b_num_seq
    START WITH 1
    INCREMENT BY 1 
    MAXVALUE 1000
    NOCYCLE 
    NOCACHE;
    
select * from prboard;