drop table if exists DATABASECHANGELOG;
drop table if exists DATABASECHANGELOGLOCK;
drop table if exists user;
create table user (
    id bigint not null auto_increment,
    age integer,
    name varchar(50),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;