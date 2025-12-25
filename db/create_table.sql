create table avtovaz(
id serial primary key,
name varchar,
number_of_axes int,
four_wheel_drive bool
);

insert into avtovaz(name, number_of_axes, four_wheel_drive) values ('kamaz', 8, true);

update avtovaz set name = 'lada vesta' where id = '1'

delete from avtovaz;