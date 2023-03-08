BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, book, book_users, family, prizes, prizes_users, reading_activity;
CREATE TABLE family (
   family_id SERIAL,
--   user_id INTEGER,
   family_name varchar (100),
   CONSTRAINT PK_family_id PRIMARY KEY (family_id)
--   CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);
CREATE TABLE users (
   user_id SERIAL,
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL,
   family_id INTEGER,
   status BOOLEAN,
   CONSTRAINT PK_user_id PRIMARY KEY (user_id),
   CONSTRAINT FK_family_id FOREIGN KEY (family_id) REFERENCES family (family_id)

);
 CREATE TABLE book (
   book_id SERIAL,
   author varchar (100),
    title varchar (100),
    isbn varchar (100),
   CONSTRAINT PK_book_id PRIMARY KEY (book_id)

 );

CREATE TABLE book_users (
   book_users_id SERIAL,
   book_id INTEGER,
   user_id INTEGER,
   CONSTRAINT PK_book_users_id PRIMARY KEY (book_id)
);






CREATE TABLE prizes (
   prize_id SERIAL,
   family_id INTEGER NOT NULL,
--   user_id INTEGER, --only include if it's important to know who originated the prize
   prize_name varchar(100) NOT NULL,
   prize_description varchar  (100) NOT NULL ,
   milestone INTEGER NOT NULL,
   max_prizes INTEGER,
   start_date DATE NOT NULL,
   end_date DATE NOT NULL,
   CONSTRAINT PK_prize_id PRIMARY KEY (prize_id),
 --  CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id), --see user_id above
   CONSTRAINT FK_family_id FOREIGN KEY (family_id) REFERENCES family (family_id)
);

CREATE TABLE prizes_users (
    prizes_users_id SERIAL,
    prize_id INTEGER,
    user_id INTEGER,
    date_won DATE, --is this needed
    CONSTRAINT PK_prize_users_id PRIMARY KEY (prizes_users_id)
);

CREATE TABLE reading_activity (
    activity_id SERIAL,
    user_id INTEGER,
    family_id INTEGER,
    book_id INTEGER,
    time_read INTEGER,
    book_format varchar (50),
    notes varchar (1000),
   -- date_started DATE,
   -- date_finished DATE,
    CONSTRAINT PK_activity_id PRIMARY KEY (activity_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_book_id FOREIGN KEY (book_id) REFERENCES book (book_id),
    CONSTRAINT FK_family_id FOREIGN KEY (family_id) REFERENCES family (family_id)
);


COMMIT TRANSACTION;