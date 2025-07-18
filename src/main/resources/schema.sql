-- Таблица с пользователями
create table if not exists users(
                                    id bigserial primary key,
                                    first_name varchar(256) not null,
                                    last_name varchar(256) not null,
                                    age integer not null,
                                    active boolean not null);

insert into users(first_name, last_name, age, active) values ('Иван', 'Иванов', 30, true);
insert into users(first_name, last_name, age, active) values ('Пётр', 'Петров', 25, false);
insert into users(first_name, last_name, age, active) values ('Мария', 'Сидорова', 28, true);
insert into users(first_name, last_name, age, active) values ('Casdasd', 'sdfasdf', 28, true);

create table if not exists posts(
                                    id bigserial PRIMARY KEY,
                                    title varchar(256) not null,
                                    content text not null,
                                    tags text not null,
                                    likes integer
                                    );


insert into posts(title,content, tags, likes) values ('Sonik', 'asdfasdfasdfasdfasdfasdf', '#os#java#spring#sonik', 30);
insert into posts(title,content, tags, likes) values ('Java ','asdfasdfasdfasdfasdfasdf',  '#os#java#spring#sonik', 12);
insert into posts(title,content, tags, likes) values ('Killer','asdfasdfasdfasdfasdfasdf',  '#os#java#spring#sonik', 23);
insert into posts(title,content, tags, likes) values ('ggvp','asdfasdfasdfasdfasdfasdf',  '#os#java#spring#sonik', 12);
insert into posts(title,content, tags, likes) values ('sss,','asdfasdfasdfasdfasdfasdf',  '#os#java#spring#sonik', 1);
