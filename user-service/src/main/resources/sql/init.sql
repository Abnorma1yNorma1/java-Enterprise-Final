CREATE SCHEMA IF NOT EXISTS user_service
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS user_service.users
(
    uuid uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dt_update timestamp without time zone NOT NULL,
    mail character varying COLLATE pg_catalog."default" NOT NULL,
    fio character varying COLLATE pg_catalog."default",
    role character varying COLLATE pg_catalog."default",
    status character varying COLLATE pg_catalog."default",
    password character varying COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (uuid),
    CONSTRAINT user_mail_key UNIQUE (mail)
)

INSERT INTO user_service.users (
	uuid, dt_create, dt_update, mail, fio, role, status, password)
VALUES (
    '3fb7961d-d357-4f45-8e1c-9e50063b1b2f',
    now(),
    now(),
    'evgenyprihodzko@gmail.com',
    'Jeka',
    'ADMIN',
    'ACTIVATED',
    '$2a$12$YlETwAx3VuZC5S7zKgRTXuNjCbSveCSxNcENRPa6PyalZrdybikou'
);

TABLESPACE pg_default;

ALTER TABLE IF EXISTS user_service.users
    OWNER to postgres;