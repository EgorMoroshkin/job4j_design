insert into users(name) values ('Egor');
insert into roles(name) values ('Admin');
insert into rules(name) values ('Read');
insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into items(name, user_id) values ('Task1', 1);
insert into comments(name, items_id) values ('Priority 1', 1);
insert into attachs(name, items_id) values ('scheme.png', 1);
insert into сategories(name, items_id) values ('main', 1);
insert into states(name, items_id) values ('draft', 1);
