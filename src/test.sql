
create table stock(
	spk int primary key ,
	sname varchar(80) ,
	smkprice int ,
	snprice int ,
	sudpercent number ,
	sytrade int ,
	sntrade int 
);
create table mem(
	mid varchar(80) primary key,
	mname varchar(80) not null ,
	mwallet int default 0 ,
	mplwallet int default 0 ,
	mplpercent number default 0, 
	mtotal int default 0,
	mpltotal int default 0
);
CREATE TABLE having_(
	hpk int not null ,
	hname varchar(80) not null ,
	hcnt int not null ,
	hstock varchar(80) not null, 
	htotal int not null,
	hnprice int not null,
	hplpercent number not null,
	hpltotal int not null
);


drop table having_;
drop table mem;
drop table stock;

select*from stock;
select*from having_;
select*from mem;



