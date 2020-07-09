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
    job varchar2(200) not null,
    
    mailing varchar2(5) not null,
    interest varchar2(50) not null,
    member_level varchar2(6) not null,
    register_date date not null,
    
    primary key(num)
);

create sequence member_num_seq;

create table zipcode(
    zipcode varchar2(50),
    sido varchar2(70),
    gugun varchar2(80),
    dong varchar2(100),
    ri varchar2(100),
    bunji varchar2(100)
);

create table board(
    board_number number(8) not null,
    writer varchar2(10) not null,
    subject varchar2(100) not null,
    email varchar2(50) not null,
    content varchar2(4000) not null,
    password varchar2(20) not null,
    
    write_date date not null,
    read_count number(5) default 0,
    group_number number(5) not null,
    sequence_number number(5) not null,
    sequence_level number(5) not null,
    primary key(board_number)
);

create sequence board_board_number_seq;