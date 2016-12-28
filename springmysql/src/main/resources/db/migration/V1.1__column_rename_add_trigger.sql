alter table t_person add COLUMN sur_name varchar(255);

create trigger ins_lastname BEFORE INSERT on t_person for EACH ROW set NEW.sur_name = NEW.last_name;

create trigger up_lastname BEFORE UPDATE on t_person for EACH ROW set NEW.sur_name = NEW.last_name;