create table T_PERSON (
  id bigint not null auto_increment,
  first_name varchar(255),
  last_name varchar(255),
  version bigint not null,
  primary key (id)
) ENGINE=InnoDB;
