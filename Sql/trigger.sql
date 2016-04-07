DROP TRIGGER IF EXISTS before_insert_on_token;

CREATE TRIGGER before_insert_on_token 
BEFORE INSERT ON `token` 
FOR EACH ROW SET new.timeExp = IFNULL(new.timeExp, DATE_ADD(NOW(), INTERVAL 1 DAY));


drop trigger if exists before_delete_on_users;

delimiter $$

create trigger before_delete_on_users
before delete 
on users
for each row
begin
	delete from roles where idUser = OLD.idUser;
	delete from profiles where idUser = OLD.idUser;
    delete from token where idUser = OLD.idUser;
    
    delete from comment_users where idUser = OLD.idUser;
    delete from comments where idUser = OLD.idUser;
    
    delete from status_users where idUser = OLD.idUser;
    delete from status where idUser = OLD.idUser;
    
    delete from contacts_users where idUser = OLD.idUser;
    delete from contacts where idUser = OLD.idUser;
    
    delete from notifies_users where idUser = OLD.idUser;
    
    delete from messages_users where idUser = OLD.idUser;
    delete from messages where idUser = OLD.idUser;
    
    delete from groups_users where idUser = OLD.idUser;
    
    delete from blacklist_users where idUser = OLD.idUser;
    delete from blacklist where idUser = OLD.idUser;
end;

$$

commit;