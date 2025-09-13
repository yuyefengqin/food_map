CREATE TABLE m_user (
    id          INT8      PRIMARY KEY, --物理主键
    code        INT8      UNIQUE NOT NULL, --逻辑主键
    name        TEXT      NOT NULL,
    password    TEXT      NOT NULL,
    avatar      INT8,
    sex         TEXT      NOT NULL,
    city        TEXT      NOT NULL,
    create_time TIMESTAMP NOT NULL,
    enable      BOOLEAN   NOT NULL
);
COMMENT ON TABLE m_user              IS '用户';
COMMENT ON COLUMN m_user.id          IS '编号';
COMMENT ON COLUMN m_user.code        IS '账号';
COMMENT ON COLUMN m_user.name        IS '名称';
COMMENT ON COLUMN m_user.password    IS '密码';
COMMENT ON COLUMN m_user.avatar      IS '头像';
COMMENT ON COLUMN m_user.sex         IS '性别';
COMMENT ON COLUMN m_user.city        IS '城市';
COMMENT ON COLUMN m_user.create_time IS '创建时间';
COMMENT ON COLUMN m_user.enable      IS '有效';