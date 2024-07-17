 -- if not exist insert roles into roles table with uuid postgresql
INSERT INTO roles (id, name) VALUES ('6af5aee9-2d1d-4def-ba3f-56c15e9c22e3', 'USER') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, name) VALUES ('1e67205f-a753-42bd-8635-e81ab8725f87', 'ADMIN') ON CONFLICT DO NOTHING;