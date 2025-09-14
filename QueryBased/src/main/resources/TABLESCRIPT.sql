CREATE TABLE Movie(
movie_id int PRIMARY KEY,
movie_name VARCHAR(20) UNIQUE NOT NULL,
director_name VARCHAR(20) NOT NULL,
imdb_rating float NOT NULL,
release_year int
);

INSERT INTO Movie Values(101, 'The Dunkrik', 'Christopher Nolan', 8.5, 2017);
INSERT INTO Movie Values(102, 'The Emoji Movie', 'Tony Leondis', 4.7, 2002);
INSERT INTO Movie Values(103, 'Anna Belle', 'David Sandberg', 8.4, 2015);
INSERT INTO Movie Values(104, 'Amazing Spider-Man', 'John Watts', 8.5, 2017);
INSERT INTO Movie Values(105,  'Wonder Women', 'Patty Jenkins', 8.9, 2004);
INSERT INTO Movie Values(106, 'Atomic Blonde', 'David Leitch', 7.2, 2009);
INSERT INTO Movie Values(107, 'Despicable Me 2', 'Kyle Balda', 8.5, 2013);

commit;