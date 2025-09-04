CREATE SCHEMA IF NOT EXISTS user_service
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS user_service."user"
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

TABLESPACE pg_default;

ALTER TABLE IF EXISTS user_service."user"
    OWNER to postgres;