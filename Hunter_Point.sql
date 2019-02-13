	-- 1. ���̺� ����
	create table hunter
	(
		idx int, -- �Ϸù�ȣ
		name varchar2(100) not null, -- �̸�
		gamepoint int, -- ����
		helperpoint int
	)
	
	
	-- 2. ������ ����
	create sequence seq_hunter_idx
	
	
	-- 3. �������� ����
	alter table hunter
	add constraint pk_hunter_idx primary key (idx)
	
	
	-- 4. view ����
	create or replace view hunter_view
	as
	select
		h.*,
		rank() over(order by gamepoint desc) as rank
	from
		(select * from hunter) h
	
	