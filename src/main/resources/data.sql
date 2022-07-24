INSERT INTO role (id, name) VALUES (1, 'ADMIN');
INSERT INTO role (id, name) VALUES (2, 'PLAYER');


INSERT INTO user_tbl (id, username, password, balance, has_free_round) VALUES (1, 'player1', '$2a$10$LBxADAj.G4Qg8hUl/dniYevvH2vcLRbI9kVhDBYBnF8Jd/NAGXo8m', 5000.0, FALSE);

INSERT INTO user_tbl_roles (users_id, roles_id) VALUES (1, 2);