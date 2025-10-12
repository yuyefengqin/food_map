-- 商户表
create table m_merchant
(
    merchant_id      bigint not null
        primary key,
    user_id          bigint not null,
    merchant_name    text   not null,
    notes            text,
    merchant_address text   not null,
    contact_phone    text   not null,
    enterprise_type  text   not null,
    logo_url         bigint,
    business_license bigint not null,
    status           smallint  default 1,
    create_time      timestamp default CURRENT_TIMESTAMP,

    update_time      timestamp
);

comment on table m_merchant is '商户表';

comment on column m_merchant.merchant_id is '商户ID';

comment on column m_merchant.merchant_name is '商户名称';

comment on column m_merchant.notes is '备注';

comment on column m_merchant.merchant_address is '所在城市';

comment on column m_merchant.contact_phone is '联系人电话';

comment on column m_merchant.enterprise_type is '企业类型';

comment on column m_merchant.logo_url is 'logo图片URL';

comment on column m_merchant.business_license is '营业执照URL';

comment on column m_merchant.status is '商户状态';

comment on column m_merchant.create_time is '创建时间';

comment on column m_merchant.user_id is '用户编号';