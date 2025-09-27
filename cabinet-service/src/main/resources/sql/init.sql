CREATE SCHEMA IF NOT EXISTS cabinet_service
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS cabinet_service.authentication
(
    uuid character varying COLLATE pg_catalog."default" NOT NULL,
    code character varying COLLATE pg_catalog."default",
    CONSTRAINT uuid_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS cabinet_service.authentication
    OWNER to postgres;