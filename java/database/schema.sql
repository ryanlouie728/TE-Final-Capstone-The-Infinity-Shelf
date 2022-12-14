BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, user_friend, comic, collection, collection_comic, character, creator CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE user_friend (
    user_id int NOT NULL,
    friend_id int NOT NULL,
    CONSTRAINT PK_user_friend PRIMARY KEY (user_id, friend_id),
    CONSTRAINT FK_user_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_friend FOREIGN KEY (friend_id) REFERENCES users(user_id)
);

CREATE TABLE friend_request (
    request_id serial,
    to_id int NOT NULl,
    from_id int NOT NULL,
    status VARCHAR(15) DEFAULT 'pending', -- pending accepted rejected canceled
    CONSTRAINT PK_friend_request PRIMARY KEY (request_id),
    CONSTRAINT FK_friend_request_to FOREIGN KEY (to_id) REFERENCES users(user_id),
    CONSTRAINT FK_friend_request_from FOREIGN KEY (from_id) REFERENCES users(user_id)
);

CREATE TABLE comic (
	comic_id int UNIQUE,
	title varchar(150) NOT NULL,
	issue_number varchar(10),
	description text,
	thumbnail varchar(250),
	CONSTRAINT PK_comic PRIMARY KEY (comic_id)
);

CREATE TABLE trade (
    trade_id serial,
    status VARCHAR(15) DEFAULT 'pending',
    CONSTRAINT PK_trade PRIMARY KEY (trade_id)
);

CREATE TABLE trade_user (
    trade_id int NOT NULL,
    user_id int NOT NULL,
    role VARCHAR(15) NOT NULL, --sender recipient
    CONSTRAINT PK_trade_user PRIMARY KEY (trade_id, user_id),
    CONSTRAINT FK_trade_user_trade FOREIGN KEY (trade_id) REFERENCES trade(trade_id),
    CONSTRAINT FK_trade_user_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE collection (
    coll_id serial,
    user_id int NOT NULL,
    coll_name VARCHAR(50),
    coll_description VARCHAR(250),
    coll_cover VARCHAR(250),
    coll_public BOOLEAN DEFAULT false,
    CONSTRAINT PK_collection PRIMARY KEY (coll_id),
    CONSTRAINT FK_collection_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE trade_comic (
    trade_id int NOT NULL,
    from_id int NOT NULL,
    to_id int NOT NULL,
    comic_id int NOT NULL,
    coll_id int NOT NULL,
    CONSTRAINT PK_trade_comic PRIMARY KEY (trade_id, comic_id),
    CONSTRAINT FK_trade_comic_trade FOREIGN KEY (trade_id) REFERENCES trade(trade_id),
    CONSTRAINT FK_trade_comic_from FOREIGN KEY (from_id) REFERENCES users(user_id),
    CONSTRAINT FK_trade_comic_to FOREIGN KEY (to_id) REFERENCES users(user_id),
    CONSTRAINT FK_trade_comic_comic FOREIGN KEY (comic_id) REFERENCES comic(comic_id),
    CONSTRAINT FK_trade_comic_coll FOREIGN KEY (coll_id) REFERENCES collection(coll_id)
);

CREATE TABLE user_collection (
    user_id int NOT NULL,
    coll_id int NOT NULL,
    CONSTRAINT PK_user_collection PRIMARY KEY (user_id, coll_id),
    CONSTRAINT FK_user_collection_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_user_collection_coll FOREIGN KEY (coll_id) REFERENCES collection(coll_id)
);

CREATE TABLE collection_comic (
	coll_id int NOT NULL,
	comic_id int NOT NULL,
	CONSTRAINT PK_collection_comic PRIMARY KEY (coll_id, comic_id),
	CONSTRAINT FK_collection_comic_coll_id FOREIGN KEY (coll_id) REFERENCES collection(coll_id),
	CONSTRAINT FK_collection_comic_comic_id FOREIGN KEY (comic_id) REFERENCES comic(comic_id)
);

CREATE TABLE character (
    char_id int NOT NULL,
    name VARCHAR(150) NOT NULL,
    thumbnail VARCHAR(250),
    CONSTRAINT PK_character PRIMARY KEY (char_id)
);

CREATE TABLE character_comic (
    char_id int NOT NULL,
    comic_id int NOT NULL,
    CONSTRAINT PK_character_comic PRIMARY KEY (char_id, comic_id),
    CONSTRAINT FK_character_comic_char_id FOREIGN KEY (char_id) REFERENCES character(char_id),
    CONSTRAINT FK_character_comic_comic_id FOREIGN KEY (comic_id) REFERENCES comic(comic_id)
);

CREATE TABLE creator (
    creator_id int NOT NULL,
    name VARCHAR(150) NOT NULL,
    role VARCHAR(50),
    thumbnail VARCHAR(250),
    CONSTRAINT PK_creator PRIMARY KEY (creator_id)
);

CREATE TABLE creator_comic (
    creator_id int NOT NULL,
    comic_id int NOT NULL,
    CONSTRAINT PK_creator_comic PRIMARY KEY (creator_id, comic_id),
    CONSTRAINT FK_creator_comic_creator_id FOREIGN KEY (creator_id) REFERENCES creator(creator_id),
    CONSTRAINT FK_creator_comic_comic_id FOREIGN KEY (comic_id) REFERENCES comic(comic_id)
);




COMMIT TRANSACTION;
