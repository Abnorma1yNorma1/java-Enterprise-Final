CREATE SCHEMA IF NOT EXISTS cabinet_service
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS cabinet_service.authentication
(
    uuid uuid NOT NULL,
    code character varying,
    CONSTRAINT uuid_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS cabinet_service.authentication
    OWNER to postgres;