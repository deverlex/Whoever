use whoeverdb;

drop table user_state;
drop table user_role;
drop table user;
drop table language;
drop table countries;

create table countries (
postalCode varchar(4) primary key,
name varchar(32) not null
) engine = InnoDB;

create table languages (
langCode varchar(4) primary key,
standardName varchar(32) not null,
nativeName varchar(64),
postalCode varchar(4),
constraint fk_language_country foreign key (postalCode) references countries (postalCode)
) engine = InnoDB;

create table users (
ssoId varchar(32) primary key,
email varchar(64) not null,
password varchar(32) not null,
nickName varchar(64),
birthday date,
langCode varchar(4),
constraint fk_users_languages foreign key (langCode) references languages (langCode),
index(ssoId)
) engine = InnoDB;

create table Users_Role (
ssoId varchar(32) not null,
role varchar(32) not null,
unique key uniq_users_role (ssoId, role),
constraint fk_user_role foreign key(ssoId) references users (ssoId)
) engine = InnoDB;

create table Users_State (
ssoId varchar(32) not null,
state varchar(12) not null,
exp_date datetime,
unique key uniq_users_state (ssoId, state),
constraint fk_user_state foreign key(ssoId) references users (ssoId)
) engine = InnoDB;

