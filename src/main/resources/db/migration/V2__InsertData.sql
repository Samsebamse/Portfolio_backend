INSERT INTO authority (authorityid, type) VALUES (uuid_generate_v4(), 'USER');
INSERT INTO authority (authorityid, type) VALUES (uuid_generate_v4(), 'MODERATOR');
INSERT INTO authority (authorityid, type) VALUES (uuid_generate_v4(), 'ADMIN');

INSERT INTO author (authorid, name, title, field1, field2, field3, field4, field5) VALUES
(uuid_generate_v4(), 'Dev name', 'Job title', 'Field1', 'Field2', 'Field3', 'Field4', 'Field5');
