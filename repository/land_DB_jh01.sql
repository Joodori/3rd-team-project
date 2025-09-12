#drop schema if exists land;
create schema land;
use land;

create table attract(
    facility_no int not null,
    attract_person_limit int,
    attract_age_limit int,
    attract_age_limit1 int,
    constraint pk primary key(facility_no)
);

create table attract_availavle_list(
    attract_reserve_time date,
    attract_avail_amount int,
    facility_no int not null,
    constraint pkv1 primary key(facility_no)
);

create table attract_reserve(
    user_no int not null,
    attract_reserve_person int,
    attract_reserve_date date,
    facility_no int not null,
    constraint pkv2 primary key(facility_no)
);

create table user(
    user_name varchar(20) not null,
    user_birth_date date,
    user_mobile varchar(100),
    user_address varchar(100),
    user_no int not null auto_increment,
    user_id varchar(100),
    user_password varchar(100),
    ticket_no int not null,
    primary key(user_no)
);

create table event_schedule(
    event_no int not null auto_increment,
    event_name varchar(100) not null,
    event_schedule varchar(100),
    event_detail varchar(100),
    event_cost int,
    event_location varchar(100),
    primary key(event_no)
);

create table facility(
    facility_no int not null auto_increment,
    facility_code varchar(100) not null,
    facility_name varchar(100),
    facility_run_time varchar(100),
    facility_latitude decimal(16,10),
    facility_longitude decimal(16,10),
    primary key(facility_no)
);

create table ticket(
    ticket_no int not null auto_increment,
    ticket_name varchar(100),
    ticket_reserve_date date,
    adult int,
    children int,
    primary key(ticket_no)
);

alter table attract_availavle_list
    add constraint attract_availavle_list_fk foreign key(facility_no)
    references attract(facility_no);

alter table attract_reserve
    add constraint attract_reserve_fk foreign key(facility_no)
    references attract(facility_no);

alter table attract_reserve
    add constraint attract_reserve_fkv2 foreign key(user_no)
    references user(user_no);

alter table attract
    add constraint attract_fk foreign key(facility_no)
    references facility(facility_no);

alter table user
    add constraint user_fk foreign key(ticket_no)
    references ticket(ticket_no);


select * from 
