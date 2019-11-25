drop table car if exists;

drop table usuario if exists;

drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 1 increment by 1;

create table car (
    id bigint not null,
    color varchar(255),
    foto blob,
    license_plate varchar(255),
    model varchar(255),
    year integer not null,
    usuario_id bigint,
    primary key (id)
);

create table usuario (
    id bigint not null,
    birthday date not null,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    phone varchar(255) not null,
    username varchar(255) not null,
    foto blob,
    primary key (id)
);

alter table usuario add constraint UK_5171l57faosmj8myawaucatdw unique (email);

alter table usuario add constraint UK_863n1y3x0jalatoir4325ehal unique (username);

alter table car add constraint FKc6mxeqb3n7tojne4o84hiykdy foreign key (usuario_id) references usuario;
