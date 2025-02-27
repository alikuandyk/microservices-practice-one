create table if not exists users
(
    id      serial8 primary key,
    name    varchar   not null,
    created timestamp not null
);

create table if not exists products
(
    id    serial8 primary key,
    name  varchar not null,
    price float   not null
);

create table if not exists orders
(
    id          serial8 primary key,
    customer_id int8 references users (id)    not null,
    product_id  int8 references products (id) not null,
    created     timestamp                     not null
);