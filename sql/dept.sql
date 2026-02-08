-- auto-generated definition
create table dept
(
    id          int unsigned auto_increment comment '主键ID'
        primary key,
    name        varchar(10) not null comment '部门名称',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间',
    constraint name
        unique (name)
)
    comment '部门表';

INSERT INTO pt.dept (id, name, create_time, update_time) VALUES (1, '学工部', '2024-12-19 14:28:56', '2024-12-22 11:04:29');
INSERT INTO pt.dept (id, name, create_time, update_time) VALUES (2, '教研部', '2024-12-19 14:28:56', '2024-12-19 14:28:56');
INSERT INTO pt.dept (id, name, create_time, update_time) VALUES (3, '咨询部', '2024-12-19 14:28:56', '2024-12-19 14:28:56');
INSERT INTO pt.dept (id, name, create_time, update_time) VALUES (4, '就业部', '2024-12-19 14:28:56', '2024-12-19 14:28:56');
INSERT INTO pt.dept (id, name, create_time, update_time) VALUES (5, '人事部', '2024-12-19 14:28:56', '2024-12-19 14:28:56');

-- auto-generated definition
create table emp
(
    id          int auto_increment
        primary key,
    username    varchar(32)   null,
    name        varchar(32)   null,
    gender      varchar(32)   null,
    image       varchar(1000) null,
    job         varchar(10)   null,
    entrydate   date          null,
    dept_id     int           null,
    create_time datetime      null,
    update_time datetime      null,
    password    varchar(32)   null
)
    charset = utf8;



INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10001, 'Georgi', 'Facello', 'M');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10002, 'Bezalel', 'Simmel', 'F');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10003, 'Parto', 'Bamford', 'M');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10004, 'Chirstian', 'Koblick', 'M');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10005, 'Kyoichi', 'Maliniak', 'M');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10006, 'Anneke', 'Preusig', 'F');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10007, 'Tzvetan', 'Zielinski', 'F');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10008, 'Saniya', 'Kalloufi', 'M');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10009, 'Sumant', 'Peac', 'F');
INSERT INTO MY_TABLE(id, first_name, last_name, birth) VALUES (10010, 'Duangkaew', 'Piveteau', 'F');
