/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/3/7 15:08:19                            */
/*==============================================================*/


drop table if exists book_chapter;

drop table if exists book_type;

drop table if exists bookinfo;

/*==============================================================*/
/* Table: book_chapter                                          */
/*==============================================================*/
create table book_chapter
(
   id                   bigint not null auto_increment,
   content              longtext,
   title                varchar(100) not null,
   book_id              bigint,
   primary key (id)
)
charset = UTF8
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

alter table book_chapter comment '书籍章节';

/*==============================================================*/
/* Table: book_type                                             */
/*==============================================================*/
create table book_type
(
   id                   bigint not null auto_increment,
   name                 varchar(255) not null,
   note                 text,
   primary key (id),
   unique key AK_Key_2 (name)
)
charset = UTF8
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

alter table book_type comment '书籍类型';

/*==============================================================*/
/* Table: bookinfo                                              */
/*==============================================================*/
create table bookinfo
(
   id                   bigint not null auto_increment,
   type_id              bigint not null,
   name                 varchar(100) not null,
   note                 text,
   author               varchar(100) not null,
   image               varchar(100),
   primary key (id),
   unique key AK_Key_2 (name)
)
charset = UTF8
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

alter table bookinfo comment '书籍';

alter table book_chapter add constraint FK_Reference_2 foreign key (book_id)
      references bookinfo (id) on delete restrict on update restrict;

alter table bookinfo add constraint FK_Reference_1 foreign key (type_id)
      references book_type (id) on delete restrict on update restrict;

