create table m_user
(
    id            bigint      not null
        primary key,
    code          text        not null
        constraint m_user_pk
            unique,
    password_hash varchar(64) not null,
    password_salt varchar(24) not null,
    name          text,
    gender        text      default '男'::text,
    avatar        bigint,
    city          bigint,
    level_id      smallint  default 1,
    create_time   timestamp default CURRENT_TIMESTAMP,
    update_time   timestamp,
    valid         boolean   default true,
    identity      varchar(20) default 'user' not null

);
-- 用户表

comment on table m_user is '用户表';

comment on column m_user.id is '用户ID';

comment on column m_user.code is '登录账号';

comment on column m_user.password_hash is '加密密码';

comment on column m_user.name is '用户名';

comment on column m_user.gender is '性别（男/女）';

comment on column m_user.avatar is '头像';

comment on column m_user.city is '注册城市';

comment on column m_user.level_id is '会员等级';

comment on column m_user.create_time is '注册时间';

comment on column m_user.update_time is '更新时间';

comment on column m_user.valid is '状态';

comment on column m_user.identity is '身份';

comment on column m_user.password_salt is '盐值';
