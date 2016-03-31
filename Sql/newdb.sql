create database whoeverdb
default character set utf8
default collate utf8_general_ci;

use whoeverdb;

create table countries (
idCountry int(4) unsigned auto_increment primary key,
postalCode varchar(8) not null,
name varchar(32) not null,
unique key uniq_countries (postalCode, name)
) engine = InnoDB ;

create table languages (
idLanguage int(4) unsigned auto_increment primary key,
langCode varchar(8) unique not null,
standardName varchar(32) not null,
nativeName varchar(64)
) engine = InnoDB ;


create table countries_languages (
idLanguage int(4) unsigned not null,
idCountry int(4) unsigned not null,
constraint fk_cl_languages foreign key (idLanguage) references languages (idLanguage),
constraint fk_cl_countries foreign key (idCountry) references countries (idCountry),
unique key (idLanguage, idCountry)
) engine = InnoDB ;

create table users (
idUser varchar(16) primary key,
idLanguage int(4) unsigned default 15 not null,
ssoId varchar(32) unique not null,
password varchar(32) not null,
state varchar(12) default 'inactive' not null,
xLoc double,
yLoc double,
token varchar(32) not null,
exp_token datetime not null,
constraint fk_user_language foreign key (idLanguage) references languages (idLanguage)
) engine = InnoDB;

create table roles (
idUser varchar(16) not null,
role varchar(12) not null,
constraint fk_role_user foreign key (iduser) references users (idUser),
unique key (idUser, role)
) engine = InnoDB ;

create table profiles (
idProfile varchar(16) primary key,
idUser varchar(16) unique not null,
nickName varchar(32) default 'anonymous',
birthday datetime not null,
gender varchar(12) default 'unknown' not null,
mobile varchar(16),
email varchar(64),
privacy varchar(12) default 'normal',
constraint fk_profile_user foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table status (
idStatus varchar(16) primary key,
idUser varchar(16) not null,
content text not null,
timePost datetime default now() not null,
xLoc double not null,
yLoc double not null,
privacy varchar(12) default 'open' not null,
isUseAccount boolean default false not null,
constraint fk_status_user foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table status_users (
idStatus varchar(16) not null,
idUser varchar(16) not null,
interact varchar(12) not null,
constraint fk_stu_status foreign key (idStatus) references status (idStatus),
constraint fk_stu_users foreign key (idUser) references users (idUser),
unique key (idUser, idStatus)
) engine = InnoDB;

create table comments (
idComment varchar(16) primary key,
idUser varchar(16) not null,
idStatus varchar(16) not null,
timePost datetime default now() not null,
content text not null,
constraint fk_comment_status foreign key (idStatus) references status (idStatus),
constraint fk_comment_user foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table comment_users (
idComment varchar(16) not null,
idUser varchar(16) not null,
interact varchar(12) not null,
constraint fk_ctu_comment foreign key (idComment) references comments (idComment),
constraint fk_ctu_users foreign key (idUser) references users (idUser),
unique key (idUser, idComment)
) engine = InnoDB;

create table photos (
idPhoto varchar(16) primary key,
idStatus varchar(16) not null,
urlImage varchar(254) not null,
constraint fk_photo_status foreign key (idStatus) references status (idStatus) 
) engine = InnoDB ;

create table avatartphotos (
idPhoto varchar(16) unique not null,
urlImageSmall varchar(254) not null,
isCurrent boolean not null,
constraint fk_avatart_photo foreign key (idPhoto) references photos (idPhoto)
) engine = InnoDB;

create table coverphotos (
idPhoto varchar(16) unique not null,
isCurrent boolean not null,
constraint fk_cover_photo foreign key (idPhoto) references photos (idPhoto)
) engine = InnoDB;

create table contacts (
idContact varchar(16) primary key,
idUser varchar(16) not null,
constraint fk_contact_user foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table contacts_users (
idContact varchar(16) not null,
idUser varchar(16) not null,
constraint fk_cau_contact foreign key (idContact) references contacts (idContact),
constraint fk_cau_user foreign key (idUser) references users (idUser),
unique key (idContact, idUser)
) engine = InnoDB;

create table messages (
idMessage varchar(16) primary key,
idUser varchar(16) not null,
content text,
urlImage varchar(254),
timeSend datetime default now() not null,
isSendDone boolean default false not null,
constraint fk_message_user foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table messages_users (
idUser varchar(16) not null,
idMessage varchar(16) not null,
timeReceiver datetime,
isReaded boolean default false not null,
constraint fk_msu_message foreign key (idMessage) references messages (idMessage),
constraint fk_msu_user foreign key (idUser) references users (idUser),
unique key (idUser, idMessage)
) engine = InnoDB;
 
create table notifies (
idNotify varchar(16) primary key,
quickNotice text not null,
typeNotice varchar(32) not null,
idContent varchar(16) unique not null,
timeNotice datetime default now() not null
) engine =  InnoDB;

create table notifies_users (
idUser varchar(16) not null,
idNotify varchar(16) not null,
isReaded boolean default false not null,
constraint fk_ntu_user foreign key (idUser) references users (idUser),
constraint fk_ntu_notify foreign key (idNotify) references notifies (idNotify),
unique key (iduser, idNotify)
) engine = InnoDB;

create table groups (
idGroup varchar(16) primary key,
name varchar(64) default 'anonymous group' not null,
describle text
) engine = InnoDB;

create table groups_users (
idGroup varchar(16) not null,
idUser varchar(16) not null,
timeJoin datetime default now() not null,
constraint fk_gru_user foreign key (idUser) references users (idUser),
constraint fk_gru_group foreign key (idGroup) references groups (idGroup),
unique key (idGroup, idUser) 
) engine = InnoDB;

create table TopOnTheWorld (
idUser varchar(16) unique not null,
rank double default 0.0 not null,
point bigint default 0 not null,
constraint fk_top_user foreign key (idUser) references users (idUser),
index (rank desc),
index (point desc)
) engine = InnoDB;

create table TopOnLanguages (
idUser varchar(16) unique not null,
idLanguage int(4) unsigned not null,
rank double default 0.0 not null,
point bigint default 0 not null,
constraint fk_tol_user foreign key (idUser) references users (idUser),
constraint fk_tol_lang foreign key (idLanguage) references languages (idLanguage),
index (rank desc),
index (point desc)
) engine = InnoDB ;

create table reports (
idReport bigint unsigned auto_increment primary key,
idUser varchar(16) not null,
contentReport text not null,
timeReport datetime default now() not null,
constraint fk_rp_user foreign key (idUser) references users (idUser) 
) engine = InnoDB;


commit;






