CREATE SCHEMA classifier_service
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS classifier_service.currencies
(
    uuid uuid NOT NULL,
    dt_create time without time zone NOT NULL DEFAULT now(),
    dt_update time without time zone NOT NULL,
    title character varying COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    CONSTRAINT currencies_pkey PRIMARY KEY (uuid)
)

    CREATE TABLE IF NOT EXISTS classifier_service.operation_categories
    (
        uuid uuid NOT NULL,
        dt_create time without time zone NOT NULL DEFAULT now(),
        dt_update time without time zone NOT NULL,
        title character varying COLLATE pg_catalog."default",
        CONSTRAINT operation_categories_pkey PRIMARY KEY (uuid)
    )

    TABLESPACE pg_default;

    ALTER TABLE IF EXISTS classifier_service.operation_categories
        OWNER to postgres;

    ALTER TABLE IF EXISTS classifier_service.currencies
        OWNER to postgres;