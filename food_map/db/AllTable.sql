-- 文件表
create table m_file (
    id     bigint    primary key ,
    name   text    not null,
    enable boolean not null,
    path   text    not null
);
comment on table m_file         is '文件';
comment on column m_file.id     is '编号';
comment on column m_file.name   is '名称';
comment on column m_file.enable is '有效';
comment on column m_file.path   is '路径';
-- 用户表
create table m_user
(
    id          bigint primary key,
    code        text not null,
    password    text not null,       -- 计划采用sh256加密存储
    name        text      default null,
    gender      text      default '男',
    avatar      bigint,
    city        bigint,
    level_id    smallint  default 1, -- 关联member_level，默认普通会员
    create_time timestamp default current_timestamp,
    update_time timestamp,
    valid      boolean   default true
);
comment on table m_user is '用户表';
comment on column m_user.id is '用户ID';
comment on column m_user.code is '登录账号'; -- 登录需要邮箱或手机号
comment on column m_user.password is '加密密码';
comment on column m_user.name is '用户名';
comment on column m_user.gender is '性别（男/女）';
comment on column m_user.avatar is '头像';
comment on column m_user.city is '注册城市';
comment on column m_user.level_id is '会员等级';
comment on column m_user.create_time is '注册时间';
comment on column m_user.update_time is '更新时间';
comment on column m_user.valid is '状态';

-- 会员等级表
create table member_level
(
    level_id      smallint primary key,
    level_name    text          not null,
    discount_rate numeric(4, 2) not null
);
comment on table member_level is '会员等级表';
comment on column member_level.level_id is '会员等级ID';
comment on column member_level.level_name is '会员等级名称';
comment on column member_level.discount_rate is '折扣率';
insert into member_level(level_id, level_name, discount_rate)
values (1, '普通会员', 1.00),
       (2, '白银会员', 0.82),
       (3, '黄金会员', 0.64);

-- 注册城市表
create table m_district(
    id bigint primary key ,
    name text,
    parent bigint
);
comment on table m_district is '注册城市表';
comment on column m_district.id is '地址ID';
comment on column m_district.name is '地区名字';
comment on column m_district.parent is '父级';
-- 收货地址表
create table m_address
(
    address_id     bigint primary key,
    user_id        bigint not null,
    receiver       text   not null,
    telephone      text   not null,
    detail_address text   not null,
    zip_code       text    default null,
    is_default     boolean default false -- 最近添加为默认
);
comment on table m_address is '收货地址表';
comment on column m_address.address_id is '地址ID';
comment on column m_address.user_id is '所属用户ID';
comment on column m_address.receiver is '收件人';
comment on column m_address.telephone is '联系电话';
comment on column m_address.detail_address is '详细地址';
comment on column m_address.zip_code is '邮政编码';
comment on column m_address.is_default is '是否默认地址';

-- 商户表
create table m_merchant
(
    merchant_id      bigint primary key,
    merchant_no      text   not null,
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
    create_time      timestamp default current_timestamp,
    update_time      timestamp
);
comment on table m_merchant is '商户表';
comment on column m_merchant.merchant_id is '商户ID';
comment on column m_merchant.merchant_no is '商户编号';
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
comment on column m_merchant.update_time is '更新时间';

-- 商品SPU表
create table product_spu
(
    spu_id           bigint primary key,
    spu_name         text not null,
    spu_desc         text,
    merchant_id      bigint  not null,
    product_category text not null,
    main_image       bigint    default null,
    approval_status  text      default 'pending' check (approval_status in ('pending', 'approved', 'rejected')), -- 审批状态
    shelf_status     text      default 'off' check (shelf_status in ('on', 'off')),                              -- 上架状态
    sales            int       default 0,
    create_time      timestamp default current_timestamp,
    update_time      timestamp
);
comment on table product_spu is '商品SPU表';
comment on column product_spu.spu_id is 'SPU ID';
comment on column product_spu.spu_name is '商品名称';
comment on column product_spu.spu_desc is '商品描述';
comment on column product_spu.merchant_id is '所属商户ID';
comment on column product_spu.product_category is '商品分类';
comment on column product_spu.main_image is '主图';
comment on column product_spu.approval_status is '审批状态';
comment on column product_spu.shelf_status is '上架状态';
comment on column product_spu.sales is '总销量';
comment on column product_spu.create_time is '创建时间';
comment on column product_spu.update_time is '更新时间';
-- 商品SKU表
create table product_sku
(
    sku_id      bigint primary key,
    spu_id      bigint         not null,
    specs_price jsonb          not null,
    element_specs jsonb        not null,
    stock       int            not null default 0,
    image_url   text[]                  default null,
    create_time timestamp               default current_timestamp,
    update_time timestamp
);

-- 商品SKU表
comment on table product_sku is '商品SKU表';
comment on column product_sku.sku_id is 'SKU ID';
comment on column product_sku.spu_id is '关联SPU ID';
comment on column product_sku.specs_price is '规格:价格'; -- 甜 ：5.00
comment on column product_sku.element_specs is '元素:规格';-- 口味 ：甜
comment on column product_sku.stock     is '库存数量';
comment on column product_sku.image_url is 'SKU图片URL';
comment on column product_sku.create_time is '创建时间';
comment on column product_sku.update_time is '更新时间';

-- 购物车表
create table shopping_cart
(
    cart_id     bigint                  not null
        primary key,
    user_id     bigint                  not null,
    spu_id      bigint                  not null,
    quantity    integer   default 1     not null,
    specs_price jsonb                   not null,
    is_selected boolean   default false not null,
    create_time timestamp default CURRENT_TIMESTAMP,
    update_time timestamp,
    total_price numeric(7, 2)
);

comment on table shopping_cart is '购物车表';

comment on column shopping_cart.cart_id is '购物车ID';

comment on column shopping_cart.user_id is '所属用户ID';

comment on column shopping_cart.spu_id is '商品SKU ID';

comment on column shopping_cart.quantity is '购买数量';

comment on column shopping_cart.specs_price is '规格:价钱';

comment on column shopping_cart.is_selected is '是否选中';

comment on column shopping_cart.create_time is '添加时间';

comment on column shopping_cart.update_time is '更新时间';

-- 普通商品订单表
create table product_order
(
    order_id          bigint primary key,
    user_id           bigint         not null,
    merchant_id       bigint         not null,
    order_amount      numeric(10, 2) not null,
    product_amount    numeric(10, 2) not null,
    delivery_fee      numeric(10, 2) not null,
    delivery_method   varchar(50)    not null   default '顺丰快递',
    delivery_time     timestamp   default null,
    address_id        bigint         not null,
    pay_method        varchar(20) default null, -- 后续考虑微信/支付宝等方式
    trade_no          bigint      default null,
    logistics_company varchar(50)  default '顺丰公司',
    logistics_no      bigint      default null,
    order_status      smallint    not null,-- 1待付款、2待发货、3待收货、4已完成、5已取消、6交易完成、7交易关闭、8未开始
    create_time       timestamp   default current_timestamp,
    pay_time          timestamp   default null,
    ship_time         timestamp   default null,
    receive_time      timestamp   default null,
    complete_time     timestamp   default null,
    close_time        timestamp   default null
);
comment on table product_order is '普通商品订单表';
comment on column product_order.order_id is '订单ID/订单编号';
comment on column product_order.user_id is '用户ID';
comment on column product_order.merchant_id is '商户ID';
comment on column product_order.order_amount is '订单总金额（商品+配送费）';
comment on column product_order.product_amount is '商品金额';
comment on column product_order.delivery_fee is '配送费';
comment on column product_order.delivery_method is '配送方式（如顺丰速递）'; -- 统一先用顺风
comment on column product_order.delivery_time is '配送时间（用户选择）';--功能未实现，默认实现代替
comment on column product_order.address_id is '收货地址ID（关联user_address）';
comment on column product_order.pay_method is '支付方式'; -- 默认统一填网页端
comment on column product_order.trade_no is '交易流水号（支付成功后生成）';--默认
comment on column product_order.logistics_company is '物流公司'; -- 统一先用顺丰
comment on column product_order.logistics_no is '物流单号'; -- 随机生成
comment on column product_order.order_status is '订单状态'; -- 1待付款、2待发货、3待收货、4已完成、5已取消、6交易完成、7交易关闭、8未开始
comment on column product_order.create_time is '创建时间';
comment on column product_order.pay_time is '支付时间';--
comment on column product_order.ship_time is '发货时间';
comment on column product_order.receive_time is '收货时间';
comment on column product_order.complete_time is '交易完成时间';
comment on column product_order.close_time is '交易关闭时间';

-- 订单明细表
create table order_item
(
    item_id       bigint primary key,
    order_id      bigint         not null,
    spu_id        bigint         not null,
    product_name  text           not null,
    specs         text[]          not null,
    unit_price    numeric(10, 2) not null,
    quantity      int            not null,
    sub_amount    numeric(10, 2) not null,                                                                                     -- 单价*数量
    refund_status varchar(20) default 'none' check (refund_status in ('none', 'applying', 'processing', 'success', 'failed')), -- 退款状态
    create_time   timestamp   default current_timestamp
);
comment on table order_item is '订单明细表';
comment on column order_item.item_id is '明细ID';
comment on column order_item.order_id is '关联订单ID';
comment on column order_item.spu_id is '商品SPU ID';
comment on column order_item.product_name is '商品名称';
comment on column order_item.specs is '规格';
comment on column order_item.unit_price is '单价';
comment on column order_item.quantity is '购买数量';
comment on column order_item.sub_amount is '小计金额';
comment on column order_item.refund_status is '退款状态';
comment on column order_item.create_time is '创建时间';

-- 退款订单表
create table refund_order
(
    refund_id      bigint primary key,
    order_id       bigint         not null,
    item_id        bigint         not null,
    user_id        bigint         not null,
    merchant_id    int            not null,
    apply_amount   numeric(10, 2) not null,
    actual_amount  numeric(10, 2) default null,                                                                      -- 退款成功后填写
    refund_reason  text           default null,
    refund_desc    text           default null,
    voucher_images bigint         default null,                                                                      -- 限制一张即可
    refund_status  varchar(20)    not null check (refund_status in ('applying', 'processing', 'success', 'failed')), -- 退款状态
    reject_reason  text           default null,                                                                      -- 退款失败时填写
    apply_time     timestamp      default current_timestamp,
    audit_time     timestamp      default null,
    complete_time  timestamp      default null
);
comment on table refund_order is '退款订单表';
comment on column refund_order.refund_id is '退款订单ID';
comment on column refund_order.order_id is '关联正向订单ID';
comment on column refund_order.item_id is '关联订单明细ID';
comment on column refund_order.user_id is '用户ID';
comment on column refund_order.merchant_id is '商户ID';
comment on column refund_order.apply_amount is '申请退款金额';
comment on column refund_order.actual_amount is '实际退款金额';
comment on column refund_order.refund_reason is '退款原因';
comment on column refund_order.refund_desc is '退款描述';
comment on column refund_order.voucher_images is '凭证图片URL';
comment on column refund_order.refund_status is '退款状态';
comment on column refund_order.reject_reason is '拒绝原因';
comment on column refund_order.apply_time is '申请时间';
comment on column refund_order.audit_time is '审核时间';
comment on column refund_order.complete_time is '完成时间';

-- 发票申请表
create table invoice_apply
(
    invoice_id      bigint primary key,
    order_id        bigint         not null,
    user_id         bigint         not null,
    invoice_no      varchar(32) default null,
    invoice_amount  numeric(10, 2) not null,                                                         -- 不含运费
    invoice_type    varchar(50) default '增值税普通发票',
    title_type      varchar(20)    not null check (title_type in ('personal', 'enterprise')),        -- 抬头类型
    invoice_title   varchar(30)    not null,
    invoice_content varchar(20) default 'detail',                                                    -- 发票内容
    address_id      bigint         not null,
    invoice_status  varchar(20) default 'applying' check (invoice_status in ('applying', 'issued')), -- 开票状态
    apply_time      timestamp   default current_timestamp,
    issue_time      timestamp   default null
);
comment on table invoice_apply is '发票申请表';
comment on column invoice_apply.invoice_id is '发票ID';
comment on column invoice_apply.order_id is '关联订单ID';
comment on column invoice_apply.user_id is '用户ID';
comment on column invoice_apply.invoice_no is '发票编号';
comment on column invoice_apply.invoice_amount is '开票金额';
comment on column invoice_apply.invoice_type is '发票类型';
comment on column invoice_apply.title_type is '抬头类型';
comment on column invoice_apply.invoice_title is '发票抬头';
comment on column invoice_apply.invoice_content is '发票内容';
comment on column invoice_apply.address_id is '发票收件地址ID';
comment on column invoice_apply.invoice_status is '开票状态';
comment on column invoice_apply.apply_time is '申请时间';
comment on column invoice_apply.issue_time is '开票时间';

-- 系统消息表
create table system_message
(
    msg_id           bigint primary key,
    user_id          bigint      not null,
    msg_type         varchar(50) not null,      -- register/ship/refund等
    msg_content      text        not null,
    related_order_no varchar(32) default null,
    is_read          boolean     default false, -- 0未读/1已读
    send_time        timestamp   default current_timestamp
);
comment on table system_message is '系统消息表';
comment on column system_message.msg_id is '消息ID';
comment on column system_message.user_id is '接收用户ID';
comment on column system_message.msg_type is '消息类型';
comment on column system_message.msg_content is '消息内容';
comment on column system_message.related_order_no is '关联订单编号';
comment on column system_message.is_read is '未读状态';
comment on column system_message.send_time is '发送时间';
