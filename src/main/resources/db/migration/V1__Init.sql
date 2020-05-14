CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS project (
    projectid UUID PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS image (
    imageid UUID PRIMARY KEY,
    projectid UUID REFERENCES project(projectid) ON DELETE CASCADE,
    path VARCHAR(200) NOT NULL,
    dimensions VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS author (
    authorid UUID PRIMARY KEY,
    name VARCHAR(50),
    title VARCHAR(200),
    field1 TEXT,
    field2 TEXT,
    field3 TEXT,
    field4 TEXT,
    field5 TEXT
);

CREATE TABLE IF NOT EXISTS skill (
    skillid UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    progress INT NOT NULL
);

CREATE TABLE IF NOT EXISTS authority (
    authorityid UUID PRIMARY KEY,
    type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS account (
    accountid UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password TEXT NOT NULL,
    email VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS account_authority (
    accountid UUID REFERENCES account(accountid) ON DELETE CASCADE,
    authorityid UUID REFERENCES authority(authorityid) ON DELETE CASCADE,
    UNIQUE (accountid, authorityid)
);