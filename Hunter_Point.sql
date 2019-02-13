	-- 1. 테이블 생성
	create table hunter
	(
		idx int, -- 일련번호
		name varchar2(100) not null, -- 이름
		gamepoint int, -- 점수
		helperpoint int
	)
	
	
	-- 2. 시퀀스 생성
	create sequence seq_hunter_idx
	
	
	-- 3. 제약조건 설정
	alter table hunter
	add constraint pk_hunter_idx primary key (idx)
	
	
	-- 4. view 생성
	create or replace view hunter_view
	as
	select
		h.*,
		rank() over(order by gamepoint desc) as rank
	from
		(select * from hunter) h
	
	