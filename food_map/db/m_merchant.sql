-- 商户表
create table m_merchant
(
    merchant_id      bigint primary key,
    merchant_name    text   not null,
    notes            text      default null,
    merchant_address text   not null,
    contact_phone    text   not null,
    enterprise_type  text   not null,
    logo_url         bigint not null,
    business_license bigint not null, -- 默认一张即可
    manage_account   text   not null,
    manage_password  text   not null,
    status           smallint  default 1,
    create_time      timestamp default current_timestamp
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
comment on column m_merchant.manage_account is '商户管理账号';
comment on column m_merchant.manage_password is '管理账号密码';
comment on column m_merchant.status is '商户状态';
comment on column m_merchant.create_time is '创建时间';