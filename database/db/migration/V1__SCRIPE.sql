
drop table car if exists

drop table usuario if exists

drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start with 1 increment by 1

create table car (
   id bigint not null,
    color varchar(255),
    license_plate varchar(255),
    model varchar(255),
    year integer not null,
    usuario_id bigint,
    primary key (id)
)

create table usuario (
   id bigint not null,
    birthday date not null,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    password varchar(255) not null,
    phone varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
)

alter table usuario add constraint UK_5171l57faosmj8myawaucatdw unique (email)

alter table usuario add constraint UK_863n1y3x0jalatoir4325ehal unique (username)

alter table car add constraint FKc6mxeqb3n7tojne4o84hiykdy foreign key (usuario_id) references usuario


INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (1,'2017-12-04','commodo.ipsum.Suspendisse@diam.ca','Thaddeus','Cervantes','daniel','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(24)841444315');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (2,'1913-02-14','imperdiet.ullamcorper@eu.net','Declan','Bishop','Cody','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(52)983961222');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (3,'1902-08-11','adipiscing@eget.ca','Josiah','Hale','Ifeoma','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(72)899715484');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (4,'2004-11-15','a.malesuada.id@fringillaornareplacerat.edu','Vera','Wiley','Jacqueline','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(45)211232316');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (5,'1930-04-05','lorem.fringilla.ornare@nisisemsemper.net','Josiah','Kemp','Desiree','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(85)538184388');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (6,'2014-02-01','metus.Vivamus.euismod@tristiqueac.edu','Kirestin','Burris','Arden','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(43)492388283');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (7,'2009-01-10','Donec.est.mauris@lobortisaugue.ca','Lana','Rojas','Gannon','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(96)335763419');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (8,'1987-03-10','tellus.imperdiet@suscipitnonummyFusce.net','Carla','Byers','Kirsten','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(82)761672322');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (9,'1977-11-10','gravida@liberoat.ca','Melanie','Hicks','Ronan','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(81)754772515');
INSERT INTO USUARIO (ID,birthday,email,FIRST_NAME,LAST_NAME,USERNAME,password,phone) VALUES (10,'1956-10-16','quis.lectus@laoreetlectus.com','Connor','Sharpe','Harper','$2a$10$mjxmujQv9afkRjw9pM2qtO2I1Fc7RdX/yo8Ubw0GVrIUgfkIFmpDC','(55)289881419');


INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (1,'orange','enim','In','1953',1);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (2,'blue','purus.','libero','1976',8);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (3,'green','semper','tellus,','1951',9);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (4,'violet','augue','erat','1985',5);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (5,'indigo','Mauris','non','1958',6);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (6,'blue','massa.','eleifend','1993',3);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (7,'yellow','vehicula','dolor.','1969',5);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (8,'violet','malesuada.','accumsan','2015',3);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (9,'orange','sed','Proin','1973',7);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (10,'violet','mollis.','dictum','1994',6);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (11,'violet','Aliquam','sapien,','1967',10);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (12,'blue','at','Proin','1971',9);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (13,'orange','enim','elementum','2012',2);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (14,'violet','bibendum.','arcu.','1944',7);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (15,'blue','massa.','est.','1971',2);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (16,'red','sit','nonummy.','1957',1);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (17,'green','Nunc','egestas','1944',4);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (18,'indigo','semper','mauris','2018',1);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (19,'indigo','Nunc','sit','1977',2);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (20,'yellow','enim.','sem','2011',2);
INSERT INTO CAR (ID,COLOR,LICENSE_PLATE,MODEL,YEAR,USUARIO_ID) VALUES (21,'orange','enim','accumsan','1978',2);
