-- Idempotent Script (you can run it multiple times without facing error).

-- Data Definition Language

create table if not exists User
(
    id       integer not null
        constraint User_pk
            primary key autoincrement,
    name     TEXT    not null,
    username TEXT    not null
        constraint username_unique
            unique
);

UPDATE User SET name = 'John Doe', username = 'john' WHERE id = 1;
UPDATE User SET name = 'John Doe', username = 'john2' WHERE id = 2;

create table if not exists Post
(
    id      integer not null
        constraint Post_pk
            primary key autoincrement,
    title   TEXT    not null,
    content TEXT    not null,
    user_id integer not null
        constraint Post_User_id_fk
            references User
);

-- DELETE from Post;
--
-- INSERT INTO Post (id, title, content, user_id) VALUES (1, 'Post1', 'Foo', 1);
-- INSERT INTO Post (id, title, content, user_id) VALUES (2, 'Post2', 'Foo', 2);

UPDATE Post SET title = 'Post1', content = 'Foo', user_id = 1 WHERE id = 1;
UPDATE Post SET title = 'Post2', content = 'Foo', user_id = 2 WHERE id = 2;


-- Data Query Language

with posts as (
select P.id    as post_id,
       P.title as naam,
       P.user_id
from Post as P
where id == 1)
select User.name, posts.naam as postName from posts, User
where posts.user_id == User.id;
