INSERT INTO collections(collection_name, user_id)
VALUES('Joshs cool collection',3);

INSERT INTO comics(marvel_id,comic_title,img_url)
VALUES(9000,'Adventures of Josh','Joggie')

INSERT INTO collection_comic(collection_id, comic_id)
VALUES(1,1);

SELECT comic_id FROM comics WHERE marvel_id = 9000;

INSERT INTO comics(marvel_id, comic_title, img_url, description)
VALUES(27649,'Incredible Hulks (2010) #604 (DJURDJEVIC 70TH ANNIVERSARY VARIANT)','http://i.annihil.us/u/prod/marvel/i/mg/b/d0/4badb223f33c9/portrait_uncanny.jpg','After picking fights with the Juggernaut, Norman Osborn, and the Wolverine clan,...');