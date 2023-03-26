INSERT INTO role (description, title)
VALUES ('Admin role', 'Administrator');
INSERT INTO role (description, title)
VALUES ('Moderator role', 'Moderator');
INSERT INTO role (description, title)
VALUES ('Director role', 'Director');
INSERT INTO role (description, title)
VALUES ('Viewer role', 'Viewer');

INSERT INTO users (created_by, created_when, address, birth_date, email, first_name, last_name, login, middle_name, password, phone, role_id)
VALUES ('Nikita', now(), 'Lenina St.', '09-11-2002', 'zhurkin236@gmail.com', 'Nikita', 'Zhurkin', 'alias69', 'Sergeevich', '123', '89001234567', 1);
INSERT INTO users (created_by, created_when, address, birth_date, email, first_name, last_name, login, middle_name, password, phone, role_id)
VALUES ('Nikita', now(), 'Gogol St.', '12-10-2001', 'zhurkin71@mail.ru', 'Sergey', 'Zhurkin', 'serg', 'Viktorovich', '121212', '89211235312', 4);

INSERT INTO films (created_by, created_when, country, genre, premier_year, title)
VALUES ('Nikita', now(), 'Russia', 'Historical', 2023, 'Nurnberg');
INSERT INTO films (created_by, created_when, country, genre, premier_year, title)
VALUES ('Nikita', now(), 'USA', 'Drama', 2018, 'Green book');

INSERT INTO orders (created_by, created_when, is_purchased, rent_date, rent_period, film_id, user_id)
VALUES ('Nikita', now(), true, current_date, '5', 1, 1);
INSERT INTO orders (created_by, created_when, is_purchased, rent_date, rent_period, film_id, user_id)
VALUES ('Nikita', now(), true, current_date, '8', 2, 2);

INSERT INTO directors (created_by, created_when, first_name, last_name, middle_name, position)
VALUES ('Nikita', now(), 'Peter', 'Farelly', null, 'Director');
INSERT INTO directors (created_by, created_when, first_name, last_name, middle_name, position)
VALUES ('Nikita', now(), 'Nikolai', 'Lebedev', 'Igorevich', 'Director');

INSERT INTO film_directors (film_id, director_id)
VALUES (1, 2);
INSERT INTO film_directors (film_id, director_id)
VALUES (2, 1);
