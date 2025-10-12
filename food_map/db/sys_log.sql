-- 系统日志表
create table operation_log
(
    id             bigint  not null
        primary key,
    operation      text    not null,
    method         text    not null,
    params         text,
    execution_time bigint,
    success        boolean not null,
    error_msg      text,
    create_time    timestamp default CURRENT_TIMESTAMP
);

comment on table operation_log is '系统操作日志表';

comment on column operation_log.id is '日志ID';

comment on column operation_log.operation is '操作描述';

comment on column operation_log.method is '调用方法';

comment on column operation_log.params is '请求参数';

comment on column operation_log.execution_time is '执行耗时(ms)';

comment on column operation_log.success is '是否成功';

comment on column operation_log.error_msg is '错误信息';

comment on column operation_log.create_time is '操作时间';