-- 创建并使用数据库：yearbook_db
drop database if exists yearbook_db;
create database yearbook_db character set utf8mb4;
use yearbook_db;

-- 创建通讯录信息表：sys_yearbook
drop table if exists sys_yearbook;
create table sys_yearbook(
    id varchar(100) not null comment '主键',
    name varchar(100) not null comment '姓名',
    cellphone varchar(100) not null unique comment '手机号码',
    password varchar(500) not null comment '登录密码：MD5 加密',
    gender varchar(10) comment '性别：M-男性，F-女性',
    birthday date comment '出生日期',
    email varchar(500) unique comment '电子邮件',
    hobby varchar(500) comment '兴趣爱好',
    address varchar(500) comment '联系地址',
    avatar varchar(500) comment '头像',
    createTime timestamp not null default current_timestamp comment '创建时间',
    modifiedTime timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key(id)
)Engine=InnoDB charset=utf8mb4 comment='通讯录信息表';