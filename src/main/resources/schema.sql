-- Таблица с пользователями
create table if not exists users
(
    id         bigserial primary key,
    first_name varchar(256) not null,
    last_name  varchar(256) not null,
    age        integer      not null,
    active     boolean      not null
);

insert into users(first_name, last_name, age, active)
values ('Иван', 'Иванов', 30, true);
insert into users(first_name, last_name, age, active)
values ('Пётр', 'Петров', 25, false);
insert into users(first_name, last_name, age, active)
values ('Мария', 'Сидорова', 28, true);
insert into users(first_name, last_name, age, active)
values ('Casdasd', 'sdfasdf', 28, true);

create table if not exists posts
(
    id      bigserial PRIMARY KEY,
    title   varchar(256) not null,
    content text         not null,
    tags    text         not null,
    image   varchar(256) not null,
    likes   integer
);

create table if not exists comments
(
    id      bigserial PRIMARY KEY,
    comment text not null,
    postId  bigserial

);


insert into posts(title, content, tags, image, likes)
values ('Sonik', 'asdfasdfasdfasdfasdfasdf ', 'os java spring sonik', '1.png',30);
insert into posts(title, content, tags, image, likes)
values ('Java ', 'asdfasdfasdfasdfasdfasdf', '#os#java#spring#sonik', '2.png',12);
insert into posts(title, content, tags, image, likes)
values ('Killer', 'asdfasdfasdfasdfasdfasdf', '#os#java#spring#sonik','3.png', 23);
insert into posts(title, content, tags, image, likes)
values ('ggvp', 'asdfasdfasdfasdfasdfasdf', '#os#java#spring#sonik', '4.png',12);
insert into posts(title, content, tags, image, likes)
values ('sss,', 'asdfasdfasdfasdfasdfasdf', '#os#java#spring#sonik', '5.png',1);
