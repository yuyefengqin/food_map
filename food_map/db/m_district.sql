create table m_district
(
    id     bigint not null
        primary key,
    name   text,
    parent bigint
);

comment on table m_district is '注册城市表';

comment on column m_district.id is '地址ID';

comment on column m_district.name is '地区名字';

comment on column m_district.parent is '父级';

INSERT INTO public.m_district (id, name, parent) VALUES (1001, '厦门', 1000);
INSERT INTO public.m_district (id, name, parent) VALUES (1011, '同安', 1001);
INSERT INTO public.m_district (id, name, parent) VALUES (1002, '泉州', 1000);
INSERT INTO public.m_district (id, name, parent) VALUES (1021, '晋江', 1002);
INSERT INTO public.m_district (id, name, parent) VALUES (1012, '翔安', 1001);
INSERT INTO public.m_district (id, name, parent) VALUES (1022, '石狮', 1002);
INSERT INTO public.m_district (id, name, parent) VALUES (1000, '福建', 0);
INSERT INTO public.m_district (id, name, parent) VALUES (1023, '德化', 1002);
INSERT INTO public.m_district (id, name, parent) VALUES (1013, '集美', 1001);
INSERT INTO public.m_district (id, name, parent) VALUES (1024, '南安', 1002);
INSERT INTO public.m_district (id, name, parent) VALUES (1014, '思明', 1001);
