insert into roles(name) values ('Admin');
insert into users(name, roles_id) values ('Egor', 1);
insert into rules(name) values ('Read');
insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into сategories(name) values ('main');
insert into states(name) values ('draft');
insert into items(name, user_id, сategories_id, states_id) values ('Task1', 1, 1, 1);
insert into comments(name, items_id) values ('Priority 1', 1);
insert into attachs(name, items_id) values ('scheme.png', 1);
