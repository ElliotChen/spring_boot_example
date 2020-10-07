
    drop table if exists User;

    create table User (
       id bigint not null auto_increment,
        age integer,
        name varchar(50),
        primary key (id)
    ) engine=InnoDB;
