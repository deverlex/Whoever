use whoeverdb;

drop table languages_countries;
drop table user_state;
drop table users_role;
drop table users;
drop table languages;
drop table countries;

ALTER TABLE languages rename index langCode_2 to langCode;

create table countries (
idCountry int(4) unsigned auto_increment not null primary key,
postalCode varchar(6) not null,
name varchar(32) not null,
unique key uniq_countries (postalCode, name)
) engine = InnoDB;

create table languages (
idLanguage int(4) unsigned auto_increment not null primary key, 
langCode varchar(8) not null unique,
standardName varchar(32) not null,
nativeName varchar(64)
) engine = InnoDB;

create table languages_countries (
idCountry int(4) unsigned not null,
idLanguage int(4) unsigned not null,
constraint fk_country_countries foreign key (idCountry) references countries (idCountry),
constraint fk_language_languages foreign key (idLanguage) references languages (idLanguage),
unique key uniq_language_country (idCountry, idLanguage)
) engine = InnoDB;

create table users (
idUser int unsigned primary key,
ssoId varchar(32) unique not null,
password varchar(32),
email varchar(64) unique,
nickName varchar(64),
birthday date,
idLanguage int(4) unsigned unique not null,
constraint fk_users_languages foreign key (idLanguage) references languages (idLanguage)
) engine = InnoDB;

create table Users_Role (
idRole int unsigned not null primary key,
idUser int unsigned not null,
role varchar(32) not null,
unique key uniq_users_role (idUser, role),
constraint fk_user_role foreign key(idUser) references users (idUser)
) engine = InnoDB;

create table User_State (
idUser int unsigned unique not null,
state varchar(12) not null,
timeout datetime,
exp_date datetime,
constraint fk_user_state foreign key(idUser) references users (idUser)
) engine = InnoDB;