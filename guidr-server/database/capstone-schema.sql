drop database if exists guidr;
create database guidr;
use guidr;

CREATE TABLE State (
	state_id integer primary key auto_increment,
	`name` varchar(2) NOT NULL);

CREATE TABLE City (
	city_id integer primary key auto_increment,
	`name` varchar(255) NOT NULL,
	state_id integer NOT NULL,
    constraint fk_state_id foreign key(state_id)
	references State(state_id));

CREATE TABLE Address (
	address_id integer primary key auto_increment,
	address varchar(1000) NOT NULL,
	zip_code integer NOT NULL,
	city_id integer NOT NULL,
    constraint fk_city_id foreign key(city_id)
	references City(city_id));

CREATE TABLE Collection (
	collection_id integer primary key auto_increment,
	`name` varchar(255) NOT NULL,
	description varchar(2355) NOT NULL);

CREATE TABLE Landmarks (
	landmark_id integer primary key auto_increment,
	`name` varchar(1000) NOT NULL,
	price decimal,
	address_id integer NOT NULL,
	collection_id integer NOT NULL,
	constraint fk_address_id foreign key(address_id)
	references Address(address_id),
    constraint fk_collection_id foreign key(collection_id)
	references Collection(collection_id));
    
CREATE TABLE User (
	user_id integer primary key auto_increment,
	username varchar(255) NOT NULL UNIQUE,
	password_hash varchar(1000) NOT NULL,
	disabled boolean NOT NULL);
  
CREATE TABLE Reviews (
	review_id integer primary key auto_increment,
	description varchar(2355) NOT NULL,
	rating decimal NOT NULL,
	collection_id integer NOT NULL,
	user_id integer NOT NULL,
	constraint fk_user_id foreign key(user_id)
	references `User`(user_id),
    constraint fk_collection_id_two foreign key(collection_id)
	references Collection(collection_id));
    
CREATE TABLE Role (
	role_id integer primary key auto_increment,
	`name` varchar(255) NOT NULL);
  
CREATE TABLE user_role (
	user_role_id integer primary key auto_increment,
	user_id integer NOT NULL,
	role_id integer NOT NULL,
	constraint fk_user_id_two foreign key(user_id)
	references `User`(user_id),
    constraint fk_role_id foreign key(role_id)
	references `Role`(role_id));
  
  
  
insert into State (`name`)
	values
    ('AL'),
    ('AK'),
    ('AZ'),
    ('AR'),
    ('CA'),
    ('CO'),
    ('CT'),
    ('DE'),
    ('FL'),
    ('GA'),
    ('HI'),
    ('ID'),
    ('IL'),
    ('IN'),
    ('IA'),
    ('KS'),
    ('KY'),
    ('LA'),
    ('ME'),
    ('MD'),
    ('MA'),
    ('MI'),
    ('MN'),
    ('MS'),
    ('MO'),
    ('MT'),
    ('NE'),
    ('NV'),
    ('NH'),
    ('NJ'),
    ('NM'),
    ('NY'),
    ('NC'),
    ('ND'),
    ('OH'),
    ('OK'),
    ('OR'),
    ('PA'),
    ('RI'),
    ('SC'),
    ('SD'),
    ('TN'),
    ('TX'),
    ('UT'),
    ('VT'),
    ('VA'),
    ('WA'),
    ('WV'),
    ('WI'),
    ('WY');

insert into City (`name`, state_id)
	values
    ('New York', 32);

insert into Address (address, zip_code, city_id)
	values
    ('Stone St', '10004', 1),
    ('1 Bowling Green', '10004', 1),
    ('Broadway & Morris St', '10004', 1),
    ('World Trade Center', '10004', 1);

insert into Collection (`name`, `description`)
	values
    ('New York-Collection #1', 'Go on a tour of downtown New York!');

insert into Landmarks (`name`, price, address_id, collection_id)
	values
    ('Stone Street Historic District', '0', 1, 1),
    ('US Custom House', '0', 1, 1),
    ('Charging Bull', '0', 1, 1),
    ('Oculus', '0', 1, 1);
    
insert into `User` (username, password_hash, disabled)
	values
	('john@smith.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
	('sally@jones.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);
		
insert into Reviews (`description`, rating, collection_id, user_id)
	values
    ('Great tour!', 4.5, 1, 1),
	('Could be better', 1, 1, 2);

insert into `Role` (`name`)
	values
    ('USER'),
    ('ADMIN');

insert into user_role (user_id, role_id)
	values
    (1, 2),
    (2, 1);
		
		
		
		
		

    




