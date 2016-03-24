use whoeverdb;

create table countries (
postalCode varchar(4) primary key,
name varchar(32) not null
) engine = InnoDB;

create table language (
langCode varchar(4) primary key,
standardName varchar(32) not null,
nativeName varchar(64),
postalCode varchar(4),
constraint fk_language_country foreign key (postalCode) references countries (postalCode)
) engine = InnoDB;

create table user (
ssoId varchar(32) primary key,
email varchar(64) not null,
password varchar(32) not null,
nickName varchar(64),
birthday date,
index(ssoId)
) engine = InnoDB;

create table User_Role (
ssoId varchar(32) unique,
role varchar(32) not null,
constraint fk_user_role foreign key(ssoId) references user (ssoId)
) engine = InnoDB;

create table User_State (
ssoId varchar(32) unique,
state varchar(12) not null,
exp_date datetime,
constraint fk_user_state foreign key(ssoId) references user (ssoId)
) engine = InnoDB;

