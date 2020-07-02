create table member(
    num number(10),
    id varchar2(50) not null,
    password varchar2(100) not null,
    name varchar2(50) not null,
    jumin1 varchar2(20) not null,
    
    jumin2 varchar2(20) not null,
    email varchar2(50) not null,
    zipcode varchar2(50) not null,
    address varchar2(100) not null,
    mailing varchar2(5) not null,
    
    interest varchar2(50) not null,
    member_level varchar2(6) not null,
    register_date date not null,
    
    primary key(num)
);

create sequence member_num_seq;