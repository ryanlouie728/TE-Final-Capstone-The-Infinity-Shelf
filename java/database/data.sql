BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (1, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (1, 1);

INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO collection (user_id, coll_name) VALUES (2, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (2, 2);

INSERT INTO users (username,password_hash,role) VALUES ('test-user', '$2a$10$6okttCWmnMxktiDBdLzbKOB9xHKxZyyfpJ/UUZwQUGyyZGBMgpJnG', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (3, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (3, 3);

INSERT INTO user_friend (user_id, friend_id) VALUES (1, 4);
INSERT INTO user_friend (user_id, friend_id) VALUES (4, 1);

INSERT INTO users (username,password_hash,role) VALUES ('braunsben', '$2a$10$6okttCWmnMxktiDBdLzbKOB9xHKxZyyfpJ/UUZwQUGyyZGBMgpJnG', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (4, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (4, 4);

INSERT INTO users (username,password_hash,role) VALUES ('reedhannah', '$2a$10$6okttCWmnMxktiDBdLzbKOB9xHKxZyyfpJ/UUZwQUGyyZGBMgpJnG', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (5, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (5, 5);

INSERT INTO users (username,password_hash,role) VALUES ('smithzach', '$2a$10$6okttCWmnMxktiDBdLzbKOB9xHKxZyyfpJ/UUZwQUGyyZGBMgpJnG', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (6, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (6, 6);

INSERT INTO users (username,password_hash,role) VALUES ('louieryan', '$2a$10$6okttCWmnMxktiDBdLzbKOB9xHKxZyyfpJ/UUZwQUGyyZGBMgpJnG', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (7, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (7, 7);

COMMIT TRANSACTION;
