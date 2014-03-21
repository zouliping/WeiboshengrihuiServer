# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table activity (
  aid                       varchar(255) not null,
  title                     varchar(255),
  location                  varchar(255),
  description               varchar(255),
  start_time                varchar(255),
  end_time                  varchar(255),
  constraint pk_activity primary key (aid))
;

create table friendship (
  fid                       varchar(255) not null,
  aid                       varchar(255),
  bid                       varchar(255),
  constraint pk_friendship primary key (fid))
;

create table production (
  pid                       varchar(255) not null,
  uid                       varchar(255),
  title                     varchar(255),
  description               varchar(255),
  type                      varchar(255),
  constraint pk_production primary key (pid))
;

create table users (
  uid                       varchar(255) not null,
  nick                      varchar(255),
  pwd                       varchar(255),
  location                  varchar(255),
  birthday                  varchar(255),
  interesting               varchar(255),
  gender                    boolean,
  constraint pk_users primary key (uid))
;

create table wish_item (
  wid                       varchar(255) not null,
  uid                       varchar(255),
  title                     varchar(255),
  description               varchar(255),
  type                      varchar(255),
  constraint pk_wish_item primary key (wid))
;

create sequence activity_seq;

create sequence friendship_seq;

create sequence production_seq;

create sequence users_seq;

create sequence wish_item_seq;




# --- !Downs

drop table if exists activity cascade;

drop table if exists friendship cascade;

drop table if exists production cascade;

drop table if exists users cascade;

drop table if exists wish_item cascade;

drop sequence if exists activity_seq;

drop sequence if exists friendship_seq;

drop sequence if exists production_seq;

drop sequence if exists users_seq;

drop sequence if exists wish_item_seq;

