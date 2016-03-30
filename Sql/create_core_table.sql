create table countries (
idCountry int(4) unsigned auto_increment not null primary key,
postalCode varchar(8) not null,
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
idUser bigint unsigned primary key,
idLanguage int(4) unsigned unique not null,
ssoId varchar(32) unique not null,
password varchar(32) not null,
isAnonymous boolean not null,
email varchar(64) unique,
mobile varchar(32),
nickName varchar(64),
birthday date,
sex varchar(32),
constraint fk_users_languages foreign key (idLanguage) references languages (idLanguage)
) engine = InnoDB;

create table Users_Role (
idRole bigint unsigned not null primary key,
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

create table token (
idUser bigint unsigned primary key,
token varchar(64) not null unique,
exp_date datetime not null,
constraint fk_token_user foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table location (
idUser bigint unsigned primary key,
xLoc double not null,
yLoc double not null,
constraint fk_user_loc foreign key (idUser) references users (idUser)
) engine = InnoDB;

create table AlbumPhoto (
idAlbumPhoto bigint unsigned primary key,
idUser bigint unsigned not null,
nameAlbum varchar(64) not null,
describle varchar(128),
createTime datetime not null,
constraint fk_user_album foreign key (idUser) references users (idUser)
) engine = InnoDB ;


create table Photo (
idPhoto bigint unsigned primary key,
idAlbumPhoto bigint unsigned not null,
name varchar(64) not null,
describle varchar(128),
urlImage varchar(128) not null,
constraint fk_photo_album foreign key (idAlbumPhoto) references AlbumPhoto (idAlbumPhoto)
) engine = InnoDB;

create table Status (
idStatus bigint unsigned primary key,
idUser bigint unsigned not null,
idPhoto bigint unsigned,
status varchar(1024),
isShow boolean not null,
upTime datetime not null,
xLoc double not null,
yLoc double not null,
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
idComment bigint unsigned primary key,
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
constraint fk_theworld_user foreign key (idUser) references Users (idUser)
) engine = InnoDB; 

create table Top_On_language (
idUser bigint unsigned primary key,
rank int unsigned not null,
constraint fk_toplanguage_user foreign key (idUser) references Users (idUser)
) engine = InnoDB;

create table Contact (
idContact bigint unsigned primary key,
idUser bigint unsigned not null,
constraint fk_contact_user foreign key (idUser) references Users (idUser)
) engine = InnoDB;

create table Contact_User (
idContact bigint unsigned not null,
idUser bigint unsigned not null,
constraint fk_contact_user_contact foreign key (idContact) references Contact (idContact),
constraint fk_contact_user_user foreign key (idUser) references Users (idUser),
unique key (idContact, idUser)
) engine = InnoDB;

create table Message (
idMessage bigint unsigned primary key,
idUser bigint unsigned not null,
message varchar (1024) not null,
sendTime datetime not null,
constraint fk_message_user foreign key (idUser) references Users (idUser),
unique key uniq_message_user (idMessage, idUser)
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



































