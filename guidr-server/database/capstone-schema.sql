drop database if exists guidr;
create database guidr;
use guidr;

CREATE TABLE Address (
	address_id integer primary key auto_increment,
	address varchar(1000) NOT NULL,
	zip_code integer NOT NULL,
	city varchar(255) NOT NULL,
    state varchar(2) NOT NULL,
    latitude decimal(8,6),
    longitude decimal(8,6)
    );

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
    image varchar(2355),
    landmark_id integer NOT NULL,
    constraint fk_landmark_id foreign key(landmark_id)
	references Landmarks(landmark_id));
  

insert into Address (address, zip_code, city, state, latitude, longitude)
	values
    ('59th Street', '10019', 'New York', 'NY', '40.785091', '-73.968285'),
    ('20 W 34th St.', '10001', 'New York', 'NY', '40.748817', '-73.985428'),
    ('Liberty Island', '10004', 'New York', 'NY', '40.689247', '-74.044502'),
    ('Fort Lee', '07024', 'New Jersey', 'NJ', '40.851616', '-73.952362'),
    ('42nd Street Manhattan', '10036', 'New York', 'NY', '40.758896', '-73.985130');

insert into Collection (`name`, `description`)
	values
    ('New York Big Attractions in the Big Apple', 'Go on a tour of downtown New York!'),
    ('Chicago', 'Tour Chicago!');

insert into Landmarks (`name`, price, address_id, collection_id)
	values
    ('Central Park', '0', 1, 1),
    ('Empire State Building', '0', 2, 1),
    ('Statue of Liberty', '0', 3, 1),
    ('George Washington Bridge', '0', 4, 1),
    ('Times Square', '0', 5, 1);
    
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
    ('The area we now call Central Park used to be home to a village founded in 1825 by freed American slaves. 
	It was home not only to property-owning African Americans but also to a healthy population of German and Irish residents.', 1),
    ('Central Park is the most filmed location in the world.', 1),
    ('Central Park is larger than the country of Monaco.', 1),
    ('It was constructed during a race to create the world’s tallest building.', 2),
    ('A few daredevils have parachuted from the building’s observation deck.', 2),
    ('The Empire State Building’s antenna is hit by lightning an average of 25 times a year.', 2),
    ('Each of the seven spikes on her crown represent the seven oceans and the seven continents of the world.', 3),
    ('In 1916 the statue suffered minor damages from the Germans and nobody has been allowed to go into 
	the torch since.', 3),
    ('The statue is green because of the oxidation of copper. The metal is 
	slightly damaged and corroded.', 3),
    ('It carries well over 100 million vehicles every year.', 4),
    ('It was designed by some of the world’s most renowned architects.', 4),
    ('The bridge is supported by 4 main cables and 105,986 wires.', 4),
    ('The first Times Square New Years Eve ball drop was held in 1907.', 5),
    ('It draws 50 million visitors a year.', 5),
    ('The largest crowd in its history—an estimated 2 million people—celebrated US victory in World War II 
	on August 14, 1945.', 5);