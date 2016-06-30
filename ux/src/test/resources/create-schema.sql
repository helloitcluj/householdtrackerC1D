DROP SCHEMA PUBLIC CASCADE;

CREATE TABLE Users (
  id INTEGER IDENTITY PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR (50),
  disabled BOOLEAN
);


CREATE CACHED TABLE Expenses (
  id          INTEGER IDENTITY PRIMARY KEY,
  amount      DOUBLE,
  date        TIMESTAMP,
  description VARCHAR(30),
  userId   INTEGER NOT NULL
);

ALTER TABLE Expenses ADD FOREIGN KEY (userId) REFERENCES Users (id);