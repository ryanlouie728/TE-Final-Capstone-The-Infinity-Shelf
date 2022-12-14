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

INSERT INTO public.comic VALUES (106140, 'Spider-Verse Unlimited Infinity Comic (2022) #35', '35', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/6/90/63977be6050bd.jpg');
INSERT INTO public.comic VALUES (106139, 'Spider-Verse Unlimited Infinity Comic (2022) #34', '34', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/9/c0/638a4f5fa619c.jpg');
INSERT INTO public.comic VALUES (106138, 'Spider-Verse Unlimited Infinity Comic (2022) #33', '33', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/c/a0/638a4f5fa2f8a.jpg');
INSERT INTO public.comic VALUES (106137, 'Spider-Verse Unlimited Infinity Comic (2022) #32', '32', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/d/80/637e534183622.jpg');
INSERT INTO public.comic VALUES (106136, 'Spider-Verse Unlimited Infinity Comic (2022) #31', '31', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/b/03/637e53404278d.jpg');
INSERT INTO public.comic VALUES (105638, 'Spider-Verse Unlimited Infinity Comic (2022) #30', '30', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/f/60/6377ac1dcf6ae.jpg');
INSERT INTO public.comic VALUES (103033, 'Spider-Man: The Lost Hunt (2022) #2', '2', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/8/a0/632dac7d4221b.jpg');
INSERT INTO public.comic VALUES (105637, 'Spider-Verse Unlimited Infinity Comic (2022) #29', '29', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/c/60/635ffedd307c1.jpg');
INSERT INTO public.comic VALUES (105636, 'Spider-Verse Unlimited Infinity Comic (2022) #28', '28', '', 'http://i.annihil.us/u/prod/marvel/i/mg/c/d0/635ffedd34f26.jpg');
INSERT INTO public.comic VALUES (99745, 'Spider-Man (2022) #3', '3', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/c/e0/63863cee9feb0.jpg');
INSERT INTO public.comic VALUES (105635, 'Spider-Verse Unlimited Infinity Comic (2022) #27', '27', 'May and Benjy Parker have arrived at Classic Arcade Dining and Entertainment, AKA C.A.D.E. They’re excitedly taking in the wall-to-wall games, music, and food! But keeping a low profile is not as easy as they thought.', 'http://i.annihil.us/u/prod/marvel/i/mg/4/30/6356b8485df81.jpg');
INSERT INTO public.comic VALUES (103936, 'Spider-Verse Unlimited Infinity Comic (2022) #26', '26', 'It’s Friday night and May Parker teams up with the Lady Hawk twins on her way home to watch her brother Benjy in this visit to Earth-982!', 'http://i.annihil.us/u/prod/marvel/i/mg/2/f0/63571051cdd34.jpg');
INSERT INTO public.comic VALUES (98488, 'Spider-Gwen: Gwenverse (Trade Paperback)', '0', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/e/60/63754819d9751.jpg');
INSERT INTO public.comic VALUES (103935, 'Spider-Verse Unlimited Infinity Comic (2022) #25', '25', 'Spider-Man joins a Super Villain street race hot on the tail of White Rabbit in the thrilling conclusion of this wild arc.', 'http://i.annihil.us/u/prod/marvel/i/mg/8/c0/635067bba24cb.jpg');
INSERT INTO public.comic VALUES (103934, 'Spider-Verse Unlimited Infinity Comic (2022) #24', '24', 'The chase continues! Monster trucks! An underground circus! Will Spider-Man find White Rabbit’s bombs in time?', 'http://i.annihil.us/u/prod/marvel/i/mg/f/90/633c35a133504.jpg');
INSERT INTO public.comic VALUES (103032, 'Spider-Man: The Lost Hunt (2022) #1', '1', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/4/03/63658b25120f5.jpg');
INSERT INTO public.comic VALUES (99744, 'Spider-Man (2022) #2', '2', 'The End of the Spider-Verse rages on! After the tragedy of the first issue, our Spider-heroes try to make a plan against Morlun. But Morlun isn’t their only problem…Shathra, is back and more powerful than the last time she nearly killed Peter and Ezekiel. Last time, Peter tricked Shathra, and she’s had plenty of time to plan his death and the death of ALL SPIDERS!', 'http://i.annihil.us/u/prod/marvel/i/mg/6/e0/63658b3e8ce52.jpg');
INSERT INTO public.comic VALUES (103933, 'Spider-Verse Unlimited Infinity Comic (2022) #23', '23', 'The chase begins! White Rabbit sets fire to Central Park to get Spider-Man’s attention. But where will she lead him?', 'http://i.annihil.us/u/prod/marvel/i/mg/e/e0/6335fa30accef.jpg');
INSERT INTO public.comic VALUES (98773, 'Spider-Gwen: Unmasked (Trade Paperback)', '0', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/f/10/635fcbac83dd0.jpg');
INSERT INTO public.comic VALUES (98307, 'Spider-Man 2099: Exodus (Trade Paperback)', '0', NULL, 'http://i.annihil.us/u/prod/marvel/i/mg/6/e0/635fcbc750489.jpg');

WITH aa AS (
    SELECT username, user_id FROM users WHERE username = 'reedhannah'
),
     base AS (
         SELECT coll_id
         FROM user_collection
                  JOIN users ON user_collection.user_id = users.user_id
         WHERE users.username = (SELECT username FROM aa)
     ),
     d1 AS (
INSERT INTO public.collection (user_id, coll_name, coll_description, coll_cover, coll_public) VALUES ((SELECT user_id FROM aa), 'Spiderman', 'The best Spiderman comics ever written. Or at least the first 20 that were returned from the Marvel API when queried. ', 'http://i.annihil.us/u/prod/marvel/i/mg/f/60/6377ac1dcf6ae.jpg', false)
    RETURNING coll_id
    ),
    d6 AS (
INSERT INTO collection_comic (comic_id, coll_id)
VALUES (106140,(SELECT coll_id FROM d1)),(106139,(SELECT coll_id FROM d1)),(106138,(SELECT coll_id FROM d1)),(106137,(SELECT coll_id FROM d1)),(106136,(SELECT coll_id FROM d1)),(105638,(SELECT coll_id FROM d1)),(103033,(SELECT coll_id FROM d1)),(105637,(SELECT coll_id FROM d1)),(105636,(SELECT coll_id FROM d1)),(99745,(SELECT coll_id FROM d1)),(105635,(SELECT coll_id FROM d1)),(103936,(SELECT coll_id FROM d1)),(98488,(SELECT coll_id FROM d1)),(103935,(SELECT coll_id FROM d1)),(103934,(SELECT coll_id FROM d1)),(103032,(SELECT coll_id FROM d1)),(99744,(SELECT coll_id FROM d1)),(103933,(SELECT coll_id FROM d1)),(98773,(SELECT coll_id FROM d1)),(98307,(SELECT coll_id FROM d1))
    RETURNING comic_id
    )
SELECT coll_id from base LIMIT 1;

WITH aa AS (
    SELECT username, user_id FROM users WHERE username = 'smithzach'
),
     base AS (
         SELECT coll_id
         FROM user_collection
                  JOIN users ON user_collection.user_id = users.user_id
         WHERE users.username = (SELECT username FROM aa)
     ),
     d1 AS (
INSERT INTO public.collection (user_id, coll_name, coll_description, coll_cover, coll_public) VALUES ((SELECT user_id FROM aa), 'Spiderman', 'The best Spiderman comics ever written. Or at least the first 20 that were returned from the Marvel API when queried. ', 'http://i.annihil.us/u/prod/marvel/i/mg/f/60/6377ac1dcf6ae.jpg', true)
    RETURNING coll_id
    ),
    d6 AS (
INSERT INTO collection_comic (comic_id, coll_id)
VALUES (106140,(SELECT coll_id FROM d1)),(106139,(SELECT coll_id FROM d1)),(106138,(SELECT coll_id FROM d1)),(106137,(SELECT coll_id FROM d1)),(106136,(SELECT coll_id FROM d1)),(105638,(SELECT coll_id FROM d1)),(103033,(SELECT coll_id FROM d1)),(105637,(SELECT coll_id FROM d1)),(105636,(SELECT coll_id FROM d1)),(99745,(SELECT coll_id FROM d1)),(105635,(SELECT coll_id FROM d1)),(103936,(SELECT coll_id FROM d1)),(98488,(SELECT coll_id FROM d1)),(103935,(SELECT coll_id FROM d1)),(103934,(SELECT coll_id FROM d1)),(103032,(SELECT coll_id FROM d1)),(99744,(SELECT coll_id FROM d1)),(103933,(SELECT coll_id FROM d1)),(98773,(SELECT coll_id FROM d1)),(98307,(SELECT coll_id FROM d1))
    RETURNING comic_id
    )
SELECT coll_id from base LIMIT 1;

WITH aa AS (
    SELECT username, user_id FROM users WHERE username = 'louieryan'
),
     base AS (
         SELECT coll_id
         FROM user_collection
                  JOIN users ON user_collection.user_id = users.user_id
         WHERE users.username = (SELECT username FROM aa)
     ),
     d1 AS (
INSERT INTO public.collection (user_id, coll_name, coll_description, coll_cover, coll_public) VALUES ((SELECT user_id FROM aa), 'Spiderman', 'The best Spiderman comics ever written. Or at least the first 20 that were returned from the Marvel API when queried. ', 'http://i.annihil.us/u/prod/marvel/i/mg/f/60/6377ac1dcf6ae.jpg', true)
    RETURNING coll_id
    ),
    d6 AS (
INSERT INTO collection_comic (comic_id, coll_id)
VALUES (106140,(SELECT coll_id FROM d1)),(106139,(SELECT coll_id FROM d1)),(106138,(SELECT coll_id FROM d1)),(106137,(SELECT coll_id FROM d1)),(106136,(SELECT coll_id FROM d1)),(105638,(SELECT coll_id FROM d1)),(103033,(SELECT coll_id FROM d1)),(105637,(SELECT coll_id FROM d1)),(105636,(SELECT coll_id FROM d1)),(99745,(SELECT coll_id FROM d1)),(105635,(SELECT coll_id FROM d1)),(103936,(SELECT coll_id FROM d1)),(98488,(SELECT coll_id FROM d1)),(103935,(SELECT coll_id FROM d1)),(103934,(SELECT coll_id FROM d1)),(103032,(SELECT coll_id FROM d1)),(99744,(SELECT coll_id FROM d1)),(103933,(SELECT coll_id FROM d1)),(98773,(SELECT coll_id FROM d1)),(98307,(SELECT coll_id FROM d1))
    RETURNING comic_id
    )
SELECT coll_id from base LIMIT 1;



COMMIT TRANSACTION;
