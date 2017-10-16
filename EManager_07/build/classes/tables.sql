
create sequence seq_cate
increment by 1
start with 402
maxvalue 999999999;

create sequence seq_pro
increment by 4
start with 402
maxvalue 999999999;


drop table T_PRODUCT_LIST cascade constraints;

create table T_PRODUCT_LIST  (
   pid                 number(20)			          not null,
   pro_item_no        VARCHAR2(20)                    not null,
   pro_name            VARCHAR2(20)                    not null,
   pro_category_no     NUMBER(10)                      not null,
   pro_spec_no         NUMBER(10)                      not null,
   pro_img_src         VARCHAR2(40)                    not null,
   pro_price           NUMBER(20)                      not null,
   pro_hits            NUMBER(10)                      not null,
   isrecommand         INTEGER                         not null,
   isspecial_offer     INTEGER                         not null,
   isup_line           INTEGER                         not null,
   pro_paritculars     VARCHAR2(500),
   constraint PK_T_PRODUCT_LIST primary key (pid)
);

drop rule;
create table rule(
   id		 	 number 	 primary key,
   pid 	  	 	 number 	 ,
   pm_size	  	 	 varchar2(20)
);

create sequence seq_rul
increment by 1
start with 1
maxvalue 999999999;

create table account_consumer(
	id  	  	 	number 		     primary key,
	loginname 		varchar2(50) 	 not null,
	password	 	varchar2(50)  	 not null,
	registertime  	date,
	lastlogintime 	date,
	ip	 	 	  	varchar2(20)
);

create sequence seq_consumer
increment by 1
start with 1
maxvalue 999999999;

create table shoppinginfo_consumer(
	id 	 	   number	 	 primary key,
	aid	 	   number 	 	 ,
	nickname   varchar2(20)	 ,
	money 	   number	 	 ,
	lasttime   date
);

create sequence seq_shop_consumer
increment by 1
start with 1
maxvalue 999999999;

create table account_product(
	id	 	   number	 	primary key,
	loginname  varchar2(50)  not null,
	password   varchar2(50)  ,
	registertime  date    ,
	lastlogintime date    ,
	ip 	 	   varchar2(20)
);

create sequence seq_proacc
increment by 1
start with 1
maxvalue 999999999;

create table address(
	id  	 number 	 primary key,
	province varchar2(20) not null,
	city 	 varchar2(40) not null,
	area 	 varchar2(40) not null
);

create sequence seq_address
increment by 1
start with 1
maxvalue 999999999;

create table consumer_addr(
	id 	 	 number	 	 primary key,
	name	 varchar2(20)  not null,
	addr 	 varchar2(200) not null,
	phone	 varchar2(20)   not null,
	provice  varchar2(20)  ,
	city     varchar2(20)  ,
	area     varchar2(20)  
);

create sequence seq_consumer_addr
increment by 1
start with 1
maxvalue 999999999;

create table orderinfo(
	id           number            primary key,
	orderno      varchar2(50)      not null,
	orderstatus  number            ,
	paystatus    number            ,
	ordertime    number            ,
	paytime      number            ,
	addrinfo     number            ,
	mask         varchar2(200)
);

create sequence seq_oderinfo
increment by 1
start with 1
maxvalue 999999999;


create table product_orderinfo(
	orderid 	 number    ,
	productid    number    ,
	primary key(orderid,productid)
);











