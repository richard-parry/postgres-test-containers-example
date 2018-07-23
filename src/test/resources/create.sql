CREATE SCHEMA IF NOT EXISTS test;

create extension if not exists pgcrypto;
-- drop table psql.customer;
CREATE TABLE IF not exists  test.customer (
   ID serial PRIMARY KEY     NOT NULL,
   first_name varchar(50)    NOT NULL,
   last_name varchar(50) not null,
   sort_code varchar(6) not null,
   account_number varchar(8) not null
);

