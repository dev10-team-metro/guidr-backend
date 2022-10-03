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
    image varchar(2355),
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
  

insert into Address (address, zip_code, city, state, latitude, longitude)
	values
    ('59th Street', '10019', 'New York', 'NY', '40.785091', '-73.968285'),
    ('20 W 34th St.', '10001', 'New York', 'NY', '40.748817', '-73.985428'),
    ('Liberty Island', '10004', 'New York', 'NY', '40.689247', '-74.044502'),
    ('Fort Lee', '07024', 'New Jersey', 'NJ', '40.851616', '-73.952362'),
    ('42nd Street Manhattan', '10036', 'New York', 'NY', '40.758896', '-73.985130'),
    ('90 Bedford Street', '10014', 'NYC', 'NY', '40.732351', '-74.007514'),
    ('295 Lafayette Street', '10012', 'NYC', 'NY', '40.724709', '-73.999847'),
    ('45 Grand Central Terminal', '10017', 'NYC', 'NY', '40.752949', '-73.979026'),
    ('45 Rockefeller Plaza', '10111', 'NYC', 'NY', '40.758744', '-73.980868');

insert into Collection (`name`, `description`)
	values
    ('New York Big Attractions in the Big Apple', 'Go on a tour of downtown New York!'),
    ('New York Cinema collection', 'As New York City is one of the most famous cities in the entire world, it is no surprise that some of your favorite films and television shows have actually been set in the Big Apple. When you think about films such as 
    The Godfather, Ghostbusters, Spider-Man, and TV shows such as Friends and Seinfeld, one of the first things that pops into your head regarding the setting is quite simply, New York. 
    Though today, Hollywood would most likely to be associated with films and television, New York City has had an enormous impact on said arts, and is sometimes considered the original Hollywood. Combined with the fact that NYC is a vast cultural melting pot, 
    AND the biggest metropolitan area in the world, it makes sense that it would be a prime 
    location for filming. So come along, as we venture through some of the most famous spots in cinema history, that are in the concrete jungle that is the empire state.');

insert into Landmarks (`name`, price, image, address_id, collection_id)
	values
    ('Central Park', '0', 'https://i.imgur.com/4LA456D.jpg', 1, 1),
    ('Empire State Building', '0', 'https://i.imgur.com/gsSDo0R.jpg', 2, 1),
    ('Statue of Liberty', '0', 'https://i.imgur.com/ZVP8su3.jpg', 3, 1),
    ('George Washington Bridge', '0', 'https://i.imgur.com/I82neo6.jpg', 4, 1),
    ('Times Square', '0', 'https://i.imgur.com/OJO6ME9.jpg', 5, 1),
    
    ('Friends Apartment', '0', 'https://i.imgur.com/53QLhbS.jpg', 6, 2),
    ('Puck Building', '0', 'https://i.imgur.com/fuWb1LT.jpg', 7, 2),
    ('Grand Central Terminal', '0', 'https://i.imgur.com/YjI4143.jpg', 8, 2),
    ('Rockefeller Center', '40', 'https://i.imgur.com/gHPE9Zo.jpg', 9, 2);
    
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
	on August 14, 1945.', 5),
  
    ('Although there is not Central Perk Cafe on the botton of the Friends Apartment building, there is a cafe called Little Owl, and yes they do sell Friends memorabilia', 6),
    ('The actual apartments are completely different to those portrayed in the show. Sadly the show was filmed on a set in Los Angeles, not in NYC', 6),
    ('Unlike the rent price portrayed in the show, which was $200 per month, the current price for an apartment on 90 Bedford Street could go for about $4,500 per month. However, the apartment is not for sale.', 6),
    ('The exterior shot of this building was used in the TV show, Will & Grace, portraying the workplace of Grace Adler', 7),
    ('The building has also been featured in shots of American Psycho, as well as the 1990 film Teenage Mutant Ninja Turtles', 7),
    ('The building is considered to be ahistorical building, buit in 1885, and is currently used as an office/retail space', 7),
    ('The Puck building was originally a printing facillity, for the J.Ottoman Lithographic Company', 7),
    ('Grand Central Terminal, has been in a great amount of movies. However, it is most famous for the battle scenes in The Avengers, as well as being the inspiration for the animated TV show Penguins of the Madagascar', 8),
    ('The actual building is used as a transportation hub for many of forms of transit in New York, serving the MTA NYC Subway, and The MetroNorth RailRoad, linking central NYC with neighboring states of New Jersey, and Connecticut', 8),
    ('Opened in 1913, the building is the home to 44 train platforms, which is the most in the world', 8),
    ('The terminal is also home to many shops and cafes for travellers, and once even housed a U.S. Postal Office', 8),
    ('Rockefeller Center is the home of many famous television shows, particularly late night shows such as The Tonight Show, Late Night with Seth Meyers, and is home to the one and only Saturday Night Live', 9),
    ('Rockefeller Center is set on the Rockefeller Plaza, which is the home to two more famous NYC landmarks, the Rockefeller Ice Skating Rink, as well as the Rockefeller Tree(Seasonal), both of which have been the background for even more films', 9),
    ('Originally built from 1930 - 1939, the Rockefeller Center was originally planned as a building to replace the Metropolitan Opera House', 9),
    ('The term Radio City Music Hall originated from John Rockefeller Jr. not wanting the name of his family to be associated with the building, though as we know now that did not go to plan entirely', 9);
    