drop database if exists guidr_test;
create database guidr_test;
use guidr_test;

CREATE TABLE Address (
	address_id integer primary key auto_increment,
	address varchar(1000) NOT NULL,
	zip_code integer NOT NULL,
	city varchar(255) NOT NULL,
    state varchar(2) NOT NULL);

CREATE TABLE Collection (
	collection_id integer primary key auto_increment,
	`name` varchar(255) NOT NULL,
	`description` varchar(2355) NOT NULL);

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
	`description` varchar(2355),
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
  
  CREATE TABLE Facts (
	facts_id integer primary key auto_increment,
    `description` varchar(2355) NOT NULL,
    landmark_id integer NOT NULL,
    constraint fk_landmark_id foreign key(landmark_id)
	references Landmarks(landmark_id));
    
delimiter //
create procedure set_known_good_state()
begin

delete from Reviews;
alter table Reviews auto_increment = 1;
delete from Facts;
alter table Facts auto_increment = 1;
delete from Landmarks;
alter table Landmarks auto_increment = 1;
delete from Collection;
alter table Collection auto_increment = 1;
delete from Address;
alter table Address auto_increment = 1;
delete from user_role;
alter table user_role auto_increment = 1;
delete from `User`;
alter table `User` auto_increment = 1;
delete from `Role`;
alter table `Role` auto_increment = 1;


insert into Address (address, zip_code, city, state)
	values
    ('Stone St', '10004', 'NYC', 'NY'),
    ('1 Bowling Green', '10004', 'NYC', 'NY'),
    ('Broadway & Morris St', '10004', 'NYC', 'NY'),
    ('World Trade Center', '10004', 'NYC', 'NY');

insert into Collection (`name`, `description`)
	values
    ('New York-Collection #1', 'Go on a tour of downtown New York!'),
    ('Test Collection', 'Test Description');

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
    
insert into Facts (`description`, landmark_id)
	values
    ('Stone Street Historic District description fun fact', 1),
    ('US Custom House description fun fact', 2);
    
    end //
    delimiter ;