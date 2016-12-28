DROP TRIGGER IF EXISTS ins_lastname;
DROP TRIGGER IF EXISTS up_lastname;

DELIMITER $$

create trigger ins_lastname BEFORE INSERT on t_person for EACH ROW
  BEGIN
    IF new.last_name is not null then
      set NEW.sur_name = NEW.last_name;
    end if;
  END $$

create trigger up_lastname BEFORE UPDATE on t_person for EACH ROW
  BEGIN
    IF new.last_name is not null then
      set NEW.sur_name = NEW.last_name;
    end if;
  END $$

DELIMITER ;