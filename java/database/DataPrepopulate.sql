
BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('Michael Corleone','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Johnny Candito','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Anthony Ten','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbHW9pft8uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Michael Nguyen','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbHW9pft8uRtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Flash Fan 700','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHQnDwqbHW9pft8uRtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Marvel Fan1','$2a$08$UkVvwpUXis18S19S5pZFn.YHPZt3oaqHQnDwqbHW9pft8uRtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('711Num1Customer','$2a$08$UkVvwpULis18S19S5pZFn.YHPGt3oaqHQnDwqbHW9pft8uRtkXKDC','ROLE_USER');

INSERT INTO collections(collection_name, user_id)
VALUES('The Famous Italian Collection',3);
INSERT INTO collections(collection_name, user_id)
VALUES('Favorite comics of a strong dude',4);
INSERT INTO collections(collection_name, user_id)
VALUES('Anthonys Top Ten',5);
INSERT INTO collections(collection_name, user_id)
VALUES('Double Double',5);
INSERT INTO collections(collection_name, user_id)
VALUES('Nguyen Win',6);
INSERT INTO collections(collection_name, user_id)
VALUES('Hello Marvel',6);
INSERT INTO collections(collection_name, user_id)
VALUES('Quick Flashy Collection',7);
INSERT INTO collections(collection_name, user_id)
VALUES('Speedster Faves',7);
INSERT INTO collections(collection_name, user_id)
VALUES('I love Marvel',8);
INSERT INTO collections(collection_name, user_id)
VALUES('Comics To Drink A Slurpee To',9);

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(57717,'Doctor Strange: Masterworks Vol. 7 (Hardcover)','http://i.annihil.us/u/prod/marvel/i/mg/3/90/581d13687d4c2/portrait_uncanny.jpg','The Master of the Mystic Arts crosses into the Quadriverse, but little does he realize unknown agents have set the Cosmic Wheel of Change in motion. Chaos is unleashed as the universe goes mad. Only with the return of the Ancient One can Doctor Strange hope to restore order to creation, but hell have to conquer the cosmic might of the In-Between to do it.'); 

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(90126,'Wolverine (2020) #16','http://i.annihil.us/u/prod/marvel/i/mg/3/b0/614b3ff346665/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(20254,'Doctor Strange, Sorcerer Supreme (1988) #88','http://i.annihil.us/u/prod/marvel/i/mg/3/e0/59fa2d2085748/portrait_uncanny.jpg','Doctor Strange returns to New York to fight a mighty demon!');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(92311,'X-Men Unlimited Infinity Comic (2021) #5','http://i.annihil.us/u/prod/marvel/i/mg/5/03/611e5b6360197/portrait_uncanny.jpg','Creators Gerry Duggan and Emilio Laiso kick off a new arc in this anthology series straight from the island of Krakoa! Nature Girl witnesses an injustice and the human world will pay. The first part of “X-Men: Green.”');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(3500,'Marvel Milestones (2005) #11','http://i.annihil.us/u/prod/marvel/i/mg/5/b0/4bc60318b31a8/portrait_uncanny.jpg','Who - or what - is the Wani? And can Tako Shamara, latest in the line of men trained to combat the creature, stay its wrath? Its a question of honor as the Dragon Lord debuts in MARVEL SPOTLIGHT #5 (March 1980).');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(93340,'The Mighty Valkyries (2021) #4','http://i.annihil.us/u/prod/marvel/i/mg/6/30/60fad327953a3/portrait_uncanny.jpg','THE TERRIBLE THREES! The bowers of Hel are filled – with new life?! Just what is Karnilla, Queen of the Dead, trying to do? And if the cycles of life and death are in flux, where does a Valkyrie’s allegiance fall? Jane Foster and her new comrade in arms, Rúna, must untangle a web of lies before the realms of both the living and the dead are irrevocably changed!');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(89602,'Daredevil (2019) #32','http://i.annihil.us/u/prod/marvel/i/mg/6/70/60fad327e5636/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(767,'Avengers (1998) #501','http://i.annihil.us/u/prod/marvel/i/mg/8/c0/4f5a50236ebb2/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(58327,'Guidebook to the Marvel Cinematic Universe (2015) #1','http://i.annihil.us/u/prod/marvel/i/mg/8/f0/58d3df0c037c0/portrait_uncanny.jpg','Doctor Strange introduces magic and mysticism to the Marvel Cinematic Universe, and the MCU Guidebooks are here to record every spell and incantation! Our comprehensive coverage of the fan-favorite Marvel Studios films and Marvel Television series continues as we reveal everything you need to know about Stephen Stranger and stills, this issue is packed with profiles on Doctor Strange himself; friends and allies including Doctor Christine Palmer, Wong, Mordo and The Ancient One; foes such as Kaecilius and Dormammu; and mystical relics including the Cloak of Levitation and the Eye of Agamotto! And more!');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(90363,'Dark Ages (2021) #2','http://i.annihil.us/u/prod/marvel/i/mg/8/f0/614b3fd4ef410/portrait_uncanny.jpg','It has been years since the age of technology ended in a single moment, like a switch had been flicked to off for an entire planet. Now Earths heroes attempt to bring humanity together in the darkness. X-Men and Avengers, vigilantes and villains all work together to create something better. But something darker than the night is descending on the world. Our postapocalyptic world is about to face Apocalypse.');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(8505,'Deadpool (1997) #49','http://i.annihil.us/u/prod/marvel/i/mg/9/80/51b763b83b8ec/portrait_uncanny.jpg','Deadpool is on the rebound after his recent romantic trouble. Will a series of attractive women help mend his broken heart?');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(64239,'Untold Tales of Spider-Man: Strange Encounter (1998) #1','http://i.annihil.us/u/prod/marvel/i/mg/9/80/5978db2f3bf01/portrait_uncanny.jpg','Spider-Man teams up with Sorcerer Supreme Doctor Strange to defeat Baron Mordo.');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(85586,'Iron Man (2020) #10','http://i.annihil.us/u/prod/marvel/i/mg/9/c0/60e72a6ea4f79/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(1332,'X-Men: Days of Future Past (Trade Paperback)','http://i.annihil.us/u/prod/marvel/i/mg/9/d0/58b5cfb6d5239/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(700,'Official Handbook of the Marvel Universe (2004) #2','http://i.annihil.us/u/prod/marvel/i/mg/9/e0/4bc682ece6395/portrait_uncanny.jpg','This is it: The Mighty Marvel Handbook returns! Featuring in-depth profiles on more than 20 of Spider-Mans closest allies and most infamous enemies');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(1005,'SPIDER-MAN: SPIDER-MANS TANGLED WEB VOL. 1 TPB (Trade Paperback)','http://i.annihil.us/u/prod/marvel/i/mg/9/f0/4bc66d6f7426f/portrait_uncanny.jpg','Spider-Man has been operating in New York City for years and he has touched most of its eight million citizens, each with a story.  Its an entirely different way at looking at Spider-Man, the way he operates and the world he lives in.');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(62140,'Doctor Strange and the Sorcerers Supreme Vol. 1: Out of Time (Trade Paperback)','http://i.annihil.us/u/prod/marvel/i/mg/a/30/591f45f836feb/portrait_uncanny.jpg','An ancient evil threatens to unravel the fabric of reality, and one Master of the Mystic Arts may not be enough to stop it. Doctor Strange must unite Sorcerers Supreme past, present and future to stem the coming darkness — including Merlin, the Ancient One and Wiccan! Then there are the surprise packages: Sir Isaac Newton! A Ghost Rider from the 1800s! And the mysterious Nina! But Strange should watch his back with this super group of spellcrafters, as not all of these mages have his best interests in mind! When the Forgotten arrives, it will take the whole team to put him down — but if anything happens to the young Ancient One from the past, what will that mean for Stephen?');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(8500,'Deadpool (1997) #44','http://i.annihil.us/u/prod/marvel/i/mg/b/80/4f206cc0ac28a/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(27649,'Incredible Hulks (2010) #604 (DJURDJEVIC 70TH ANNIVERSARY VARIANT)','http://i.annihil.us/u/prod/marvel/i/mg/b/d0/4badb223f33c9/portrait_uncanny.jpg','After picking fights with the Juggernaut, Norman Osborn, and the Wolverine clan, the most insane father and son team in the Marvel Universe might actually be bonding.  But everythings about to blow wide open for Bruce Banner and his big, green, barbarian son Skaar when one of the Hulks greatest enemies brings back the most important villainess Bruce Banners ever faced.  Who is the Harpy?  And whose side will Banner take when she and Skaar meet sword to claw in a gamma-powered deathmatch?  Stone might bleed, feathers might fly, and hearts might break in the highest stakes battle yet for Banner and Son!');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(89677,'Iron Man (2020) #11','http://i.annihil.us/u/prod/marvel/i/mg/b/d0/60e5e1bdbe135/portrait_uncanny.jpg','After bringing down an Ultimo robot nearly singlehandedly, Iron Man continues to investigate the strange and small colony on the remote planet where hes been marooned. Life here seems perfect…but is it? And whos possibly pulling the strings behind its delicate design? As Tony digs deeper for answers, an astral projection of Hellcat warns him that Korvac is still the biggest threat they all face…unless Tony gives into addiction, this time in the form of pain medication for his many injuries.');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(230,'Weapon X (2002) #16','http://i.annihil.us/u/prod/marvel/i/mg/c/70/59441e5aa5ecf/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(9008,'Incredible Hulk (1962) #197','http://i.annihil.us/u/prod/marvel/i/mg/c/a0/5b7dbfd738f30/portrait_uncanny.jpg','');

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(211,'Ultimate X-Men (2001) #40','http://i.annihil.us/u/prod/marvel/i/mg/e/e0/5babe9eb3721a/portrait_uncanny.jpg','NEW MUTANTS PART 1 The debut of Ultimate Angel is here! Warren Worthington III enters the fray and the Ultimate X-Men grow by one!');

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,1);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,2);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,6);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,22);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,3);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,17);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,8);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,11);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,25);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(2,9);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(2,24);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(2,14);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(2,21);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(3,20);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(3,19);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(3,18);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(3,17);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(3,16);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(4,23);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(4,21);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(4,19);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(4,8);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(4,2);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(4,3);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(4,4);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(5,2);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(5,5);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(5,12);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(5,11);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(5,13);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(5,24);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(5,6);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(6,1);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(6,13);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(6,15);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(6,25);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(6,17);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,2);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,4);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,6);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,8);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,10);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,12);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,14);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,16);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,18);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,20);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,22);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,24);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(7,5);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,10);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,12);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,14);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,16);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,18);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,20);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,22);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,24);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(8,5);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,2);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,5);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,12);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,11);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,13);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,24);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,6);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,1);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(9,13);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,6);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,22);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,3);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,17);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,8);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,11);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,25);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,9);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,24);

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(10,14);

COMMIT TRANSACTION;