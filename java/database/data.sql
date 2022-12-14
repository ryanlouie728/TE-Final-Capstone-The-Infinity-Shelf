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

INSERT INTO users (username,password_hash,role) VALUES ('braunsben', '$2a$10$/ShksapY4jyhW89paYdMn..Ssmgv0.N..5F2ZY9EP3YJ1LS77KRum', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (4, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (4, 4);

INSERT INTO users (username,password_hash,role) VALUES ('reedhannah', '$2a$10$/ShksapY4jyhW89paYdMn..Ssmgv0.N..5F2ZY9EP3YJ1LS77KRum', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (5, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (5, 5);

INSERT INTO users (username,password_hash,role) VALUES ('smithzach', '$2a$10$/ShksapY4jyhW89paYdMn..Ssmgv0.N..5F2ZY9EP3YJ1LS77KRum', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (6, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (6, 6);

INSERT INTO users (username,password_hash,role) VALUES ('louieryan', '$2a$10$/ShksapY4jyhW89paYdMn..Ssmgv0.N..5F2ZY9EP3YJ1LS77KRum', 'ROLE_USER');
INSERT INTO collection (user_id, coll_name) VALUES (7, 'base');
INSERT INTO user_collection (user_id, coll_id) VALUES (7, 7);

INSERT INTO user_friend (user_id, friend_id) VALUES (5, 6);
INSERT INTO user_friend (user_id, friend_id) VALUES (6, 5);

INSERT INTO user_friend (user_id, friend_id) VALUES (6, 7);
INSERT INTO user_friend (user_id, friend_id) VALUES (7, 6);

INSERT INTO user_friend (user_id, friend_id) VALUES (5, 7);
INSERT INTO user_friend (user_id, friend_id) VALUES (7, 5);

INSERT INTO friend_request(to_id, from_id) VALUES (5, 4);
INSERT INTO friend_request(to_id, from_id) VALUES (6, 4);
INSERT INTO friend_request(to_id, from_id) VALUES (6, 4);


COMMIT TRANSACTION;
