
/* Drop Tables */

DROP TABLE address CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE order_detail CASCADE CONSTRAINTS;
DROP TABLE orders CASCADE CONSTRAINTS;
DROP TABLE qna CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE worker CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE address
(
	zip_num varchar2(7) NOT NULL,
	sido varchar2(30) NOT NULL,
	gugun varchar2(30) NOT NULL,
	dong varchar2(100) NOT NULL,
	zip_code varchar2(30),
	bunji varchar2(30)
);


CREATE TABLE cart
(
	cseq number(10) NOT NULL,
	id varchar2(20) NOT NULL,
	pseq number(5) NOT NULL,
	quantity number(5) DEFAULT 1,
	result char(1) DEFAULT '1',
	indate date DEFAULT SYSDATE,
	PRIMARY KEY (cseq)
);


CREATE TABLE member
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	email varchar2(40) NOT NULL,
	zip_num varchar2(10) NOT NULL,
	address1 varchar2(50) NOT NULL,
	address2 varchar2(50),
	phone varchar2(20) NOT NULL,
	indate date DEFAULT SYSDATE,
	useyn char(1) DEFAULT 'y',
	PRIMARY KEY (id)
);


CREATE TABLE orders
(
	oseq number(10) NOT NULL,
	indate date DEFAULT SYSDATE,
	id varchar2(20) NOT NULL,
	PRIMARY KEY (oseq)
);


CREATE TABLE order_detail
(
	odseq number(10) NOT NULL,
	quantity number(5) DEFAULT 1,
	result char(1) DEFAULT '1',
	oseq number(10) NOT NULL,
	pseq number(5) NOT NULL,
	PRIMARY KEY (odseq)
);


CREATE TABLE product
(
	pseq number(5) NOT NULL,
	name varchar2(100) NOT NULL,
	-- 카테고리
	kind char(1) NOT NULL,
	-- 원가
	price1 number(7),
	-- 판매가
	price2 number(7),
	-- 마진
	price3 number(7),
	content varchar2(1000),
	image varchar2(255),
	-- 상품 판매 유효 여부
	useyn char(1) DEFAULT 'y',
	bestyn char(1) DEFAULT 'n',
	-- 상품등록일
	indate date DEFAULT SYSDATE,
	PRIMARY KEY (pseq)
);


CREATE TABLE qna
(
	qseq number(5) NOT NULL,
	subject varchar2(300) NOT NULL,
	content varchar2(1000) NOT NULL,
	reply varchar2(1000),
	rep char(1) DEFAULT '1',
	indate date DEFAULT SYSDATE,
	id varchar2(20) NOT NULL,
	PRIMARY KEY (qseq)
);


CREATE TABLE worker
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20) NOT NULL,
	name varchar2(20) NOT NULL,
	phone varchar2(20) NOT NULL,
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE cart
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE orders
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE qna
	ADD FOREIGN KEY (id)
	REFERENCES member (id)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (oseq)
	REFERENCES orders (oseq)
;


ALTER TABLE cart
	ADD FOREIGN KEY (pseq)
	REFERENCES product (pseq)
;


ALTER TABLE order_detail
	ADD FOREIGN KEY (pseq)
	REFERENCES product (pseq)
;



/* Comments */

COMMENT ON COLUMN product.kind IS '카테고리';
COMMENT ON COLUMN product.price1 IS '원가';
COMMENT ON COLUMN product.price2 IS '판매가';
COMMENT ON COLUMN product.price3 IS '마진';
COMMENT ON COLUMN product.useyn IS '상품 판매 유효 여부';
COMMENT ON COLUMN product.indate IS '상품등록일';



