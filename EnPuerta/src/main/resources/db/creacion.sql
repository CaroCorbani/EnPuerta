-- Table: public.socios

-- DROP TABLE public.socios;

CREATE TABLE public.socios
(
    id integer NOT NULL DEFAULT nextval('socios_id_seq'::regclass),
    nombre text COLLATE pg_catalog."default" NOT NULL,
    apellido text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default",
    dni text COLLATE pg_catalog."default" NOT NULL,
    celular text COLLATE pg_catalog."default",
    telefono text COLLATE pg_catalog."default" NOT NULL,
    telefono2 text COLLATE pg_catalog."default" NOT NULL,
    direccion text COLLATE pg_catalog."default" NOT NULL,
    genero text COLLATE pg_catalog."default" NOT NULL,
    localidad text COLLATE pg_catalog."default" NOT NULL,
    presente boolean NOT NULL DEFAULT false,
    fecha_de_nacimiento date NOT NULL,
    CONSTRAINT socios_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

