BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('Little Jimmy','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('Dad','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO family (family_name)  VALUES ('Smith');
INSERT INTO family (family_name) VALUES ('Johnson');
INSERT INTO book (author, title, isbn) VALUES ('Maurice Sendak', 'Where The Wild Things Are', '978-0-37-000772-4');
INSERT INTO book (author, title, isbn) VALUES ('Margaret Wise Brown', 'Goodnight Moon', '978-0-06-443017-3');
INSERT INTO book (author, title, isbn) VALUES ('E. B. White', 'Charlotte''s Web', '978-0-06-441093-9');
INSERT INTO book (author, title, isbn) VALUES ('Beatrix Potter', 'The Tale of Peter Rabbit', '978-0-72-324770-8');
INSERT INTO book (author, title, isbn) VALUES ('A. A. Milne', 'Winnie-the-Pooh', '978-0-52-544443-5');
INSERT INTO book (author, title, isbn) VALUES ('Shel Silverstein', 'The Giving Tree', '978-1-84-614383-0');
INSERT INTO book (author, title, isbn) VALUES ('Dr. Seuss', 'The Cat In The Hat', '978-0-39-480001-1');




COMMIT TRANSACTION;
