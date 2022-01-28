--
-- create table tags
-- (
--     id   BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
--     name VARCHAR(60) NOT NULL UNIQUE
-- );
--
-- create table certificates
-- (
--     id               BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
--     name             varchar(80)  NOT NULL UNIQUE,
--     description      VARCHAR(200) NOT NULL,
--     price            DECIMAL(10, 2),
--     create_date      TIMESTAMP DEFAULT NOW(),
--     last_update_date TIMESTAMP DEFAULT NOW(),
--     duration         INT          NOT NULL
-- );
--
-- create table certificates_tags
-- (
--     id             BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
--     certificate_id BIGINT UNSIGNED NOT NULL,
--     tag_id         BIGINT UNSIGNED NOT NULL,
--     FOREIGN KEY (certificate_id) REFERENCES certificates (id)
--         ON DELETE CASCADE ON UPDATE CASCADE,
--     FOREIGN KEY (tag_id) REFERENCES tags (id)
--         ON DELETE CASCADE ON UPDATE CASCADE
-- );


create table certificates
(
    id               bigint unsigned auto_increment
        primary key,
    name             varchar(80)  not null,
    description      varchar(200) not null,
    price            decimal(10, 2) null,
    create_date      timestamp default CURRENT_TIMESTAMP null,
    last_update_date timestamp default CURRENT_TIMESTAMP null,
    duration         int          not null,
    constraint name
        unique (name)
);

create table tags
(
    id   bigint unsigned auto_increment
        primary key,
    name varchar(60) not null,
    date timestamp default CURRENT_TIMESTAMP null,
    constraint name
        unique (name)
);

create table certificates_tags
(
    id             bigint unsigned auto_increment
        primary key,
    certificate_id bigint unsigned not null,
    tag_id         bigint unsigned not null,
    constraint certificates_tags_ibfk_1
        foreign key (certificate_id) references certificates (id)
            on update cascade on delete cascade,
    constraint certificates_tags_ibfk_2
        foreign key (tag_id) references tags (id)
            on update cascade on delete cascade
);

create index certificate_id
    on certificates_tags (certificate_id);

create index tag_id
    on certificates_tags (tag_id);
