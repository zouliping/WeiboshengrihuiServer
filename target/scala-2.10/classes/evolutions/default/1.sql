# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table production (
  pid                       varchar(255) not null,
  title                     varchar(255),
  description               varchar(255),
  type                      varchar(255),
  constraint pk_production primary key (pid))
;

create table users (
  uid                       varchar(255) not null,
  pwd                       varchar(255),
  location                  varchar(255),
  birthday                  varchar(255),
  interesting               varchar(255),
  gender                    boolean,
  constraint pk_users primary key (uid))
;

create sequence production_seq;

create sequence users_seq;




# --- !Downs

drop table if exists production cascade;

drop table if exists users cascade;

drop sequence if exists production_seq;

drop sequence if exists users_seq;

