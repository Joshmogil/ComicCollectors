BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE comics (

        comic_id int,
	comic_title varchar(60) not null,
	img_url varchar(185),
	
        constraint pk_comics primary key (comic_id)
);

CREATE TABLE collections (

        collection_id serial,
        collection_name varchar(60),
	user_id int not null,
	
        constraint pk_collections primary key (collection_id),
        constraint fk_collections_users foreign key (user_id) references users(user_id)
);


CREATE TABLE collection_comic (

        collection_id int not null,
	comic_id int not null,
	
        constraint fk_collection_comic_collections foreign key(collection_id) references collections(collection_id),
        constraint fk_collection_comic_comics foreign key(comic_id) references comics(comic_id)
        
);

CREATE TABLE characters (

        character_id int,
	character_name varchar(60) not null,
	
        constraint pk_characters primary key (character_id)
);


CREATE TABLE comic_character (

        comic_id int not null,
        character_id int not null,
        
        constraint fk_comic_character_comics foreign key(comic_id) references comics(comic_id),
        constraint fk_comic_character_characters foreign key(character_id) references characters(character_id)
        
);

CREATE TABLE series (

        series_id int,
	series_title varchar(60),
	
        constraint series_id primary key (series_id)
);

CREATE TABLE series_comic (

        series_id int not null,
        comic_id int not null,
        
        constraint fk_series_comic_series foreign key(series_id) references series(series_id),
        constraint fk_series_comic_comic foreign key(comic_id) references comics(comic_id)
);


COMMIT TRANSACTION;
