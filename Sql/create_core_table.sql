create database whoeverdb
default character set utf8
default collate utf8_general_ci;

use whoeverdb;

create table countries (
idCountry int(4) unsigned auto_increment primary key,
postalCode varchar(8) not null,
name varchar(32) not null,
unique key uniq_countries (postalCode, name)
) engine = InnoDB;

create table languages (
idLanguage int(4) unsigned auto_increment primary key, 
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
idUser bigint unsigned auto_increment primary key,
idLanguage int(4) unsigned unique not null,
ssoId varchar(32) unique not null,
password varchar(32) not null,
isAnonymous boolean not null,
email varchar(64) unique,
mobile varchar(32),
nickName varchar(64) default 'anonymous',
birthday date,
sex varchar(32) default 'unknown',
isGetAroundStatus boolean not null,
isShowOnline boolean not null,
constraint fk_users_languages foreign key (idLanguage) references languages (idLanguage)
) engine = InnoDB;

create table Users_Role (
idRole bigint unsigned auto_increment primary key,
idUser bigint unsigned not null,
role varchar(32) not null,
unique key uniq_users_role (idUser, role),
constraint fk_user_role foreign key(idUser) references users (idUser)
) engine = InnoDB;

create table User_State (
idUser bigint unsigned primary key,
state varchar(12) not null,
timeout datetime,
exp_date datetime,
constraint fk_user_state foreign key(idUser) references users (idUser)
) engine = InnoDB;

create table Token (
idUser bigint unsigned primary key,
token varchar(64) not null unique,
exp_date datetime not null,
constraint fk_token_user foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table Location (
idUser bigint unsigned primary key,
xLoc double not null,
yLoc double not null,
isOnline boolean not null,
constraint fk_user_loc foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table AlbumPhoto (
idAlbumPhoto bigint unsigned auto_increment primary key,
idUser bigint unsigned not null,
nameAlbum varchar(64) not null,
describle varchar(128),
createTime datetime default now(),
constraint fk_user_album foreign key (idUser) references users (idUser)
) engine = InnoDB ;


create table Photo (
idPhoto bigint unsigned auto_increment primary key,
idAlbumPhoto bigint unsigned not null,
urlImage varchar(128) not null,
constraint fk_photo_album foreign key (idAlbumPhoto) references AlbumPhoto (idAlbumPhoto)
) engine = InnoDB;

create table Status (
idStatus bigint unsigned auto_increment primary key,
idUser bigint unsigned not null,
idPhoto bigint unsigned,
status varchar(1024),
isShow boolean default true,
upTime datetime default now(),
xLoc double not null,
yLoc double not null,
privacy varchar(12) not null,
isUseAccount boolean not null,
constraint fk_status_user foreign key (idUser) references users (idUser),
constraint fk_status_photo foreign key (idPhoto) references photo (idPhoto)
) engine = InnoDB;

create table Interact_Status (
idUser bigint unsigned not null,
idStatus bigint unsigned not null,
interact varchar(12),
constraint fk_interact_status_user foreign key (idUser) references users (iduser),
constraint fk_interact_status_status foreign key (idStatus) references status (idStatus),
unique key uniq_interact_status (idUser, idStatus)
) engine = InnoDB;

create table Comment (
idComment bigint unsigned auto_increment primary key,
idStatus bigint unsigned not null,
idUser bigint unsigned not null,
comment varchar(128) not null,
upTime datetime not null,
constraint fk_comment_status foreign key (idStatus) references Status (idStatus),
constraint fk_comment_user foreign key (idUser) references Users (idUser)
) engine = InnoDB;

create table Interact_Comment (
idComment bigint unsigned not null,
idUser bigint unsigned not null,
interact varchar (12),
constraint fk_interact_comment_user foreign key (idUser) references Users (idUser),
constraint fk_interact_comment_comment foreign key (idComment) references Comment (idComment),
unique key (idComment, idUser)
) engine = InnoDB;

create table Top_On_TheWorld (
idUser bigint unsigned primary key,
rank int unsigned not null,
totalPoint int not null,
constraint fk_theworld_user foreign key (idUser) references Users (idUser),
index (rank)
) engine = InnoDB; 

create table Top_On_language (
idUser bigint unsigned primary key,
rank int unsigned not null,
totalPoint int not null,
constraint fk_toplanguage_user foreign key (idUser) references Users (idUser),
index (rank)
) engine = InnoDB;

create table User_Contact (
idContact bigint unsigned primary key,
idUser bigint unsigned not null,
constraint fk_user_contact foreign key (idContact) references Users (idUser),
constraint fk_user_user foreign key (idUser) references Users (idUser),
unique key (idContact, idUser)
) engine = InnoDB;

create table Message (
idMessage bigint unsigned primary key,
idUser bigint unsigned not null,
message varchar (1024) not null,
sendTime datetime not null,
constraint fk_message_user foreign key (idUser) references Users (idUser)
) engine = InnoDB;

create table Message_User (
idMessage bigint unsigned not null,
idUser bigint unsigned not null,
message varchar (1024) not null,
recvTime datetime not null,
isRead boolean not null,
constraint fk_message_user_user foreign key (idUser) references Users (idUser),
constraint fk_message_user_message foreign key (idMessage) references Message (idMessage)
) engine =  InnoDB;

create table Report_Status (
idStatus bigint unsigned primary key,
totalReport int unsigned default 0,
constraint fk_report_status foreign key (idStatus) references Status (idStatus),
index (totalReport)
) engine = InnoDB;

create table Groups (
idGroup bigint unsigned auto_increment primary key,
nameGroup varchar(128),
describle varchar(256)
) engine = InnoDB;

create table User_Group (
idUser bigint unsigned not null,
idGroup bigint unsigned not null,
constraint fk_user_group_user foreign key (idUser) references Users (iduser),
constraint fk_user_group_group foreign key (idGroup) references Groups (idGroup)
) engine = InnoDB;




































