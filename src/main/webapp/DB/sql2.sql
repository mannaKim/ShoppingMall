drop sequence product_seq;
create sequence product_seq start with 1;

drop sequence cart_seq;
create sequence cart_seq start with 1;

drop sequence orders_seq;
create sequence orders_seq start with 1;

drop sequence order_detail_seq;
create sequence order_detail_seq start with 1;

drop sequence qna_seq;
create sequence qna_seq start with 1;

-----샘플 데이터 입력-----
-- 관리자 입력
insert into worker values('admin','admin','관리자','010-7777-7777');
insert into worker values('scott','tiger','홍길동','010-6666-6666');

-- 회원 입력
insert into member(id,pwd,name,zip_num,address1,address2,phone,email) values
('one','1111','김나리','133-110','서울시 성동수 성수동1가','1번지21호','017-777-7777','abc@abc.com');
insert into member(id,pwd,name,zip_num,address1,address2,phone,email) values
('two','2222','김길동','130-120','서울시 송파구 잠실2동','리센츠 아파트 201동 505호','011-123-4567','acc@abc.com');

-- 상품 입력
insert into product(pseq,name,kind,price1,price2,price3,content,image)
values(product_seq.nextVal,'크로크다일부츠','2',40000,50000,10000,'오리지날 크로크다일부츠입니다.','w2.jpg');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'롱부츠','2',40000,50000,10000,'따듯한 롱부츠입니다.','w-28.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'힐','1',10000,12000,2000,'10cm 힐입니다.','w-14.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'슬리퍼','4',5000,5500,500,'편안한 슬리퍼입니다.','w-25.jpg','y');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'회색힐','1',10000,12000,2000,'회색 힐입니다.','w-23.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image)
values(product_seq.nextVal,'여성부츠','2',12000,18000,6000,'여성용 부츠.','w4.jpg');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'핑크샌달','3',5000,5500,500,'사계절용 샌달입니다.','w-24.jpg','y');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'슬리퍼','3',5000,5500,500,'편안한 슬리퍼입니다.','w11.jpg','y');

insert into product(pseq,name,kind,price1,price2,price3,content,image)
values(product_seq.nextVal,'스니커즈','4',15000,20000,5000,'활동성이 좋은 스니커즈입니다.','w-13.jpg');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'샌달','3',5000,5500,500,'사계절용 샌달입니다.','w-09.jpg','n');

insert into product(pseq,name,kind,price1,price2,price3,content,image,bestyn)
values(product_seq.nextVal,'스니커즈','5',15000,20000,5000,'편안한 스니커즈입니다.','w-05.jpg','n');

-- 카트
select * from cart;
insert into cart(cseq,id,pseq,quantity) values(cart_seq.nextVal,'one',1,1);
insert into cart(cseq,id,pseq,quantity) values(cart_seq.nextVal,'two',2,1);

-- orders와 order_detail
select * from orders;
select * from order_detail;
insert into orders(oseq,id) values(orders_seq.nextVal,'one');
insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,1,1,1);
insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,1,2,2);

insert into orders(oseq,id) values(orders_seq.nextVal,'two');
insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,2,4,3);
insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,2,5,2);

insert into orders(oseq,id) values(orders_seq.nextVal,'one');
insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,3,3,1);
insert into order_detail(odseq,oseq,pseq,quantity) values(order_detail_seq.nextVal,3,2,1);

-- QnA
select * from qna;
insert into qna(qseq,subject,content,id)
values(qna_seq.nextVal,'배송관련 문의입니다.','현재 배송상태와 예상 배송일 답변 부탁드립니다.','one');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextVal,'환불관련','환불 절차 안내 부탁드려요. 배송사 선택은 어떻게 되는지도','one');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextVal,'사이즈 교환 하고 싶어요.','사이즈가 예상보다 작습니다. 교환 절차를 안내해주세요','two');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextVal,'배송이 많이 지연되고 있습니다.','언제 받을 수 있나요?','two');
insert into qna(qseq,subject,content,id)
values(qna_seq.nextVal,'불량품 교환 문의.','교환 또는 환불 등의 안내가 필요합니다. 유선 안내 부탁드려요.','one');

-- cart 안의 상품번호와 사용자 아이디로 상품이름과 사용자 이름을 함께 조회하는 view 생성
create or replace view cart_view
as
select c.cseq, c.id, m.name as mname, c.pseq, p.name as pname, c.quantity, p.price2, c.result, c.indate
from cart c, product p, member m
where c.pseq=p.pseq and c.id=m.id;

select * from cart_view;

-- orders와 order_datail의 join으로
-- 1. 주문번호(oseq)에 따른 주문 상품들의 표시
-- 2. 상품 번호에 따른 상품 이름과 가격 등의 정보 표시
-- 3. 아이디에 따른 고객 이름과 배송주소 등의 정보 표시
create or replace view order_view
as
select d.odseq, o.oseq, o.indate, o.id,
	m.name as mname, m.zip_num, m.address1, m.address2, m.phone,
	d.pseq, p.name as pname, p.price2, d.quantity, d.result
from orders o, order_detail d, member m, product p
where o.oseq=d.oseq and o.id=m.id and d.pseq=p.pseq;

select * from order_view;

-- 신상품 view 생성
create or replace view new_pro_view
as
select * from
(select rownum,pseq,name,price2,image from product where useyn='y' order by indate desc)
where rownum<=4;

select * from new_pro_view;

-- 베스트 상품 view 생성
create or replace view best_pro_view
as
select * from
(select rownum,pseq,name,price2,image from product where bestyn='y' order by indate desc)
where rownum<=4;

select * from best_pro_view;

----------------------------------------------
-- address에 튜플 insert하기 : CMD 창에 아래 명령 시행 후 확인
-- C:\Users\JAVA02>sqlplus
-- Enter user-name: scott
-- Enter password:

-- Connected to:
-- Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

-- SQL>@C:\JAVA02\backup\zip.sql 		-- zip.sql이 있는 경로에 따라..
select * from address;