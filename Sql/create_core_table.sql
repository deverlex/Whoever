use whoeverdb;

drop table user_state;
drop table users_role;
drop table users;
drop table language;
drop table countries;

alter table languages drop foreign key fk_language_country;
alter table languages drop column postalCode;

create table languages_countries (
postalCode varchar(4) not null,
langCode varchar(4) not null,
constraint fk_country_countries foreign key (postalCode) references countries (postalCode),
constraint fk_language_languages foreign key (langCode) references languages (langCode),
unique key uniq_language_country (postalCode, langCode)
) engine = InnoDB;

create table countries (
postalCode varchar(4) primary key,
name varchar(32) not null
) engine = InnoDB;

create table languages (
langCode varchar(4) primary key,
standardName varchar(32) not null,
nativeName varchar(64),
postalCode varchar(4),
) engine = InnoDB;

create table users (
ssoId varchar(32) primary key,
email varchar(64) unique,
password varchar(32),
nickName varchar(64),
birthday date,
langCode varchar(4) not null,
constraint fk_users_languages foreign key (langCode) references languages (langCode),
index(ssoId),
index(email)
) engine = InnoDB;

create table Users_Role (
idUserRole int primary key,
ssoId varchar(32) not null,
role varchar(32) not null,
unique key uniq_users_role (ssoId, role),
constraint fk_user_role foreign key(ssoId) references users (ssoId),
index(idUserRole)
) engine = InnoDB;

create table User_State (
ssoId varchar(32) unique not null,
state varchar(12) not null,
timeout datetime,
exp_date datetime,
unique key uniq_users_state (ssoId, state),
constraint fk_user_state foreign key(ssoId) references users (ssoId),
index(ssoId)
) engine = InnoDB;

