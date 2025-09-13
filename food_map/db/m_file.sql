CREATE TABLE m_file (
    id     INT8    PRIMARY KEY,
    name   TEXT    NOT NULL,
    enable BOOLEAN NOT NULL,
    path   TEXT    NOT NULL
);
COMMENT ON TABLE m_file         IS '文件';
COMMENT ON COLUMN m_file.id     IS '编号';
COMMENT ON COLUMN m_file.name   IS '名称';
COMMENT ON COLUMN m_file.enable IS '有效';
COMMENT ON COLUMN m_file.path   IS '路径';