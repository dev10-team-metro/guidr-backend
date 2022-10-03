drop database if exists guidr;
create database guidr;
use guidr;

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
	references Landmark(landmark_id));
  

insert into Address (address, zip_code, city, state)
	values
    ('Stone St', '10004', 'NYC', 'NY'),
    ('1 Bowling Green', '10004', 'NYC', 'NY'),
    ('Broadway & Morris St', '10004', 'NYC', 'NY'),
    ('World Trade Center', '10004', 'NYC', 'NY');

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
    
insert into Facts (`description`, landmark_id)
	values
    ('Stone Street Historic District description fun fact', 1),
    ('US Custom House description fun fact', 2);
    



insert into Collection (`name`, `description`)
	values
    ('New York Cinema collection', 'As New York City is one of the most famous cities in the entire world, it is no surprise that some of your favorite films and television shows have actually been set in the Big Apple. When you think about films such as The Godfather, Ghostbusters, Spider-Man, and TV shows such as Friends and Seinfeld, one of the first things that pops into your head regarding the setting is quite simply, New York. Though today, Hollywood would most likely to be associated with films and television, New York City has had an enormous impact on said arts, and is sometimes considered the original Hollywood. Combined with the fact that NYC is a vast cultural melting pot, AND the biggest metropolitan area in the world, it makes sense that it would be a prime location for filming. So come along, as we venture through some of the most famous spots in cinema history, that are in the concrete jungle that is the empire state.');


insert into Landmarks (`name`, price, address_id, collection_id)
	values
    ('Friends Apartment', '0', 1, 1),
    ('Puck Building', '0', 2, 1),
    ('Grand Central Terminal', '0', 3, 1),
    ('Rockefeller Center', '40', 4, 1);
    
insert into Address (address, zip_code, city, state)
	values
    ('90 Bedford Street', '10014', 'NYC', 'NY'),
    ('295 Lafayette Street', '10012', 'NYC', 'NY'),
    ('45 Grand Central Terminal', '10017', 'NYC', 'NY'),
    ('45 Rockefeller Plaza', '10111', 'NYC', 'NY');
    
insert into Facts (`description`, landmark_id)
	values
    ('Although there is not Central Perk Cafe on the botton of the Friends Apartment building, there is a cafe called Little Owl, and yes they do sell Friends memorabilia', 1),
    ('The actual apartments are completely different to those portrayed in the show. Sadly the show was filmed on a set in Los Angeles, not in NYC', 1),
    ('Unlike the rent price portrayed in the show, which was $200 per month, the current price for an apartment on 90 Bedford Street could go for about $4,500 per month. However, the apartment is not for sale.', 1),
    ('The exterior shot of this building was used in the TV show, Will & Grace, portraying the workplace of Grace Adler', 2),
    ('The building has also been featured in shots of American Psycho, as well as the 1990 film Teenage Mutant Ninja Turtles',2),
    ('The building is considered to be ahistorical building, buit in 1885, and is currently used as an office/retail space', 2),
    ('The Puck building was originally a printing facillity, for the J.Ottoman Lithographic Company', 2),
    ('Grand Central Terminal, has been in a great amount of movies. However, it is most famous for the battle scenes in The Avengers, as well as being the inspiration for the animated TV show Penguins of the Madagascar',3),
    ('The actual building is used as a transportation hub for many of forms of transit in New York, serving the MTA NYC Subway, and The MetroNorth RailRoad, linking central NYC with neighboring states of New Jersey, and Connecticut', 3),
    ('Opened in 1913, the building is the home to 44 train platforms, which is the most in the world', 3),
    ('The terminal is also home to many shops and cafes for travellers, and once even housed a U.S. Postal Office', 3),
    ('Rockefeller Center is the home of many famous television shows, particularly late night shows such as The Tonight Show, Late Night with Seth Meyers, and is home to the one and only Saturday Night Live', 4),
    ('Rockefeller Center is set on the Rockefeller Plaza, which is the home to two more famous NYC landmarks, the Rockefeller Ice Skating Rink, as well as the Rockefeller Tree(Seasonal), both of which have been the background for even more films', 4),
    ('Originally built from 1930 - 1939, the Rockefeller Center was originally planned as a building to replace the Metropolitan Opera House', 4),
    ('The term Radio City Music Hall originated from John Rockefeller Jr. not wanting the name of his family to be associated with the building, though as we know now that did not go to plan entirely', 4);
    