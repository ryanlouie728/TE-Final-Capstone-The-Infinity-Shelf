BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, comic, collection, collection_comic CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE comic (
	comic_id int UNIQUE,
	title varchar(150) NOT NULL,
	issue_number varchar(10),
	description text,
	thumbnail varchar(250) UNIQUE,
	CONSTRAINT PK_comic PRIMARY KEY (comic_id)
);

CREATE TABLE collection (
	coll_id serial,
	user_id int NOT NULL,
	coll_name VARCHAR(150),
	coll_description VARCHAR(250),
	coll_cover VARCHAR(250),
	coll_public BOOLEAN DEFAULT false,
	CONSTRAINT PK_collection PRIMARY KEY (coll_id),
	CONSTRAINT FK_collection_cover FOREIGN KEY (coll_cover) REFERENCES comic(thumbnail),
	CONSTRAINT FK_collection_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE collection_comic (
	coll_id int NOT NULL,
	comic_id int NOT NULL,
	CONSTRAINT PK_collection_comic PRIMARY KEY (coll_id, comic_id),
	CONSTRAINT FK_collection_comic_coll_id FOREIGN KEY (coll_id) REFERENCES collection(coll_id),
	CONSTRAINT FK_collection_comic_comic_id FOREIGN KEY (comic_id) REFERENCES comic(comic_id)
);


COMMIT TRANSACTION;
