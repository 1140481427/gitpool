drop table Accounts;
create table Accounts(
  id          number(10)     primary key,
  username    varchar2(20)   not null,
  password    varchar2(20)  not null,
  ip          varchar2(10)   not null,
  logindate    number   
);

create sequence seq_acc
increment by 1
start with 1
maxvalue 999999999;

insert into Accounts(id,username,password,ip,logindate) values(seq_acc.nextval,'admin','admin','127.0.0.1',(SYSDATE - TO_DATE('1970-1-1 8', 'YYYY-MM-DD HH24')) * 86400000 + TO_NUMBER(TO_CHAR(SYSTIMESTAMP(3), 'FF')));