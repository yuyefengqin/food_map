CREATE TABLE m_address (
    id         INT8    PRIMARY KEY,
    user_id    INT8    NOT NULL,
    name       TEXT    NOT NULL,
    mobile     TEXT    NOT NULL,
    detail     TEXT    NOT NULL,
    is_default BOOLEAN NOT NULL
);
COMMENT ON TABLE m_address             IS '收货地址';
COMMENT ON COLUMN m_address.id         IS '编号';
COMMENT ON COLUMN m_address.user_id    IS '用户';
COMMENT ON COLUMN m_address.name       IS '收件人';
COMMENT ON COLUMN m_address.mobile     IS '联系电话';
COMMENT ON COLUMN m_address.detail     IS '地址详情';
COMMENT ON COLUMN m_address.is_default IS '默认地址';