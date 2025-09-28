-- 系统日志表
create table sys_log
(
    log_id          bigint primary key,                   -- 日志ID（雪花）
    user_id         bigint,                               -- 操作用户ID（关联m_user表）
    username        text,                                 -- 用户名（冗余存储）
    operation       text        not null,                 -- 操作描述（比如"新增用户"、"下单"）
    business_type   varchar(50) not null,                 -- 业务类型（比如"user"、"order"、"product"）
    request_method  text,                                 -- 请求方法（比如 "POST","GET"）
    request_url     text,                                 -- 请求URL
    request_param   text,                                 -- 请求参数（JSON）
    response_result text,                                 -- 响应结果（JSON）
    ip_address      text,                                 -- 操作IP地址
    execution_time  bigint,                               -- 执行时间（用毫秒当单位）
    success         boolean     not null default true,     -- 是否成功（true/false）
    error_msg       text,                                 -- 错误信息（失败时用来存储）
    create_time     timestamp   default current_timestamp -- 日志创建时间
);

comment on table sys_log is '系统操作日志表';
comment on column sys_log.log_id is '日志ID';
comment on column sys_log.user_id is '操作用户ID';
comment on column sys_log.username is '用户名';
comment on column sys_log.operation is '操作描述';
comment on column sys_log.business_type is '业务类型';
comment on column sys_log.request_method is '请求方法';
comment on column sys_log.request_url is '请求URL';
comment on column sys_log.request_param is '请求参数';
comment on column sys_log.response_result is '响应结果';
comment on column sys_log.ip_address is '操作IP';
comment on column sys_log.execution_time is '执行时间(ms)';
comment on column sys_log.success is '是否成功';
comment on column sys_log.error_msg is '错误信息';
comment on column sys_log.create_time is '创建时间';