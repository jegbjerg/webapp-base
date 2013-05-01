CREATE TABLE user (
    id IDENTITY,
    version INTEGER NOT NULL,
    username VARCHAR(15) UNIQUE NOT NULL,
    email VARCHAR(250) NOT NULL,
    encoded_password VARCHAR NOT NULL,
    type VARCHAR(5) NOT NULL
);
