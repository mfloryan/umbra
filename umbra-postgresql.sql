--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: umbra; Type: DATABASE; Schema: -; Owner: umbra
--

CREATE DATABASE umbra WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'pl_PL.UTF-8' LC_CTYPE = 'pl_PL.UTF-8';


ALTER DATABASE umbra OWNER TO umbra;

\connect umbra

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: entry; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE entry (
    id bigint NOT NULL,
    version bigint NOT NULL,
    content character varying(5000),
    date_created timestamp without time zone NOT NULL,
    last_updated timestamp without time zone NOT NULL,
    permalink character varying(1500) NOT NULL,
    publish_date timestamp without time zone NOT NULL,
    title character varying(400) NOT NULL
);


ALTER TABLE public.entry OWNER TO umbra;

--
-- Name: entry_id_seq; Type: SEQUENCE; Schema: public; Owner: umbra
--

CREATE SEQUENCE entry_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.entry_id_seq OWNER TO umbra;

--
-- Name: entry_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: umbra
--

ALTER SEQUENCE entry_id_seq OWNED BY entry.id;


--
-- Name: entry_picture; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE entry_picture (
    entry_pictures_id bigint,
    picture_id bigint,
    pictures_idx integer
);


ALTER TABLE public.entry_picture OWNER TO umbra;

--
-- Name: entry_tag; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE entry_tag (
    entry_tags_id bigint,
    tag_id bigint
);


ALTER TABLE public.entry_tag OWNER TO umbra;

--
-- Name: format; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE format (
    id bigint NOT NULL,
    version bigint NOT NULL,
    date_created timestamp without time zone NOT NULL,
    file_size bigint NOT NULL,
    height integer NOT NULL,
    path character varying(255) NOT NULL,
    picture_id bigint NOT NULL,
    type character varying(255) NOT NULL,
    width integer NOT NULL
);


ALTER TABLE public.format OWNER TO umbra;

--
-- Name: format_id_seq; Type: SEQUENCE; Schema: public; Owner: umbra
--

CREATE SEQUENCE format_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.format_id_seq OWNER TO umbra;

--
-- Name: format_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: umbra
--

ALTER SEQUENCE format_id_seq OWNED BY format.id;


--
-- Name: person; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE person (
    id bigint NOT NULL,
    version bigint NOT NULL,
    display_order integer,
    full_name character varying(120) NOT NULL,
    short_name character varying(20) NOT NULL
);


ALTER TABLE public.person OWNER TO umbra;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: umbra
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq OWNER TO umbra;

--
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: umbra
--

ALTER SEQUENCE person_id_seq OWNED BY person.id;


--
-- Name: picture; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE picture (
    id bigint NOT NULL,
    version bigint NOT NULL,
    camera character varying(255),
    date_created timestamp without time zone NOT NULL,
    date_taken timestamp without time zone NOT NULL,
    last_updated timestamp without time zone NOT NULL,
    location_altitude character varying(64),
    location_latitude character varying(32),
    location_longitude character varying(32),
    original_filename character varying(400) NOT NULL,
    title character varying(400)
);


ALTER TABLE public.picture OWNER TO umbra;

--
-- Name: picture_id_seq; Type: SEQUENCE; Schema: public; Owner: umbra
--

CREATE SEQUENCE picture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.picture_id_seq OWNER TO umbra;

--
-- Name: picture_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: umbra
--

ALTER SEQUENCE picture_id_seq OWNED BY picture.id;


--
-- Name: picture_person; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE picture_person (
    picture_people_id bigint,
    person_id bigint
);


ALTER TABLE public.picture_person OWNER TO umbra;

--
-- Name: tag; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE tag (
    id bigint NOT NULL,
    version bigint NOT NULL,
    name character varying(80) NOT NULL
);


ALTER TABLE public.tag OWNER TO umbra;

--
-- Name: tag_id_seq; Type: SEQUENCE; Schema: public; Owner: umbra
--

CREATE SEQUENCE tag_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.tag_id_seq OWNER TO umbra;

--
-- Name: tag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: umbra
--

ALTER SEQUENCE tag_id_seq OWNED BY tag.id;


--
-- Name: umbra_role; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE umbra_role (
    id bigint NOT NULL,
    version bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.umbra_role OWNER TO umbra;

--
-- Name: umbra_role_id_seq; Type: SEQUENCE; Schema: public; Owner: umbra
--

CREATE SEQUENCE umbra_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.umbra_role_id_seq OWNER TO umbra;

--
-- Name: umbra_role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: umbra
--

ALTER SEQUENCE umbra_role_id_seq OWNED BY umbra_role.id;


--
-- Name: umbra_role_permissions; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE umbra_role_permissions (
    role_id bigint,
    permissions_string character varying(255)
);


ALTER TABLE public.umbra_role_permissions OWNER TO umbra;

--
-- Name: umbra_user; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE umbra_user (
    id bigint NOT NULL,
    version bigint NOT NULL,
    password_hash character varying(255) NOT NULL,
    username character varying(40) NOT NULL
);


ALTER TABLE public.umbra_user OWNER TO umbra;

--
-- Name: umbra_user_id_seq; Type: SEQUENCE; Schema: public; Owner: umbra
--

CREATE SEQUENCE umbra_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.umbra_user_id_seq OWNER TO umbra;

--
-- Name: umbra_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: umbra
--

ALTER SEQUENCE umbra_user_id_seq OWNED BY umbra_user.id;


--
-- Name: umbra_user_permissions; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE umbra_user_permissions (
    user_id bigint,
    permissions_string character varying(255)
);


ALTER TABLE public.umbra_user_permissions OWNER TO umbra;

--
-- Name: umbra_user_roles; Type: TABLE; Schema: public; Owner: umbra; Tablespace: 
--

CREATE TABLE umbra_user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.umbra_user_roles OWNER TO umbra;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: umbra
--

ALTER TABLE entry ALTER COLUMN id SET DEFAULT nextval('entry_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: umbra
--

ALTER TABLE format ALTER COLUMN id SET DEFAULT nextval('format_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: umbra
--

ALTER TABLE person ALTER COLUMN id SET DEFAULT nextval('person_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: umbra
--

ALTER TABLE picture ALTER COLUMN id SET DEFAULT nextval('picture_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: umbra
--

ALTER TABLE tag ALTER COLUMN id SET DEFAULT nextval('tag_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: umbra
--

ALTER TABLE umbra_role ALTER COLUMN id SET DEFAULT nextval('umbra_role_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: umbra
--

ALTER TABLE umbra_user ALTER COLUMN id SET DEFAULT nextval('umbra_user_id_seq'::regclass);


--
-- Name: entry_permalink_key; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY entry
    ADD CONSTRAINT entry_permalink_key UNIQUE (permalink);


--
-- Name: entry_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY entry
    ADD CONSTRAINT entry_pkey PRIMARY KEY (id);


--
-- Name: format_path_key; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY format
    ADD CONSTRAINT format_path_key UNIQUE (path);


--
-- Name: format_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY format
    ADD CONSTRAINT format_pkey PRIMARY KEY (id);


--
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: person_short_name_key; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_short_name_key UNIQUE (short_name);


--
-- Name: picture_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY picture
    ADD CONSTRAINT picture_pkey PRIMARY KEY (id);


--
-- Name: tag_name_key; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_name_key UNIQUE (name);


--
-- Name: tag_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY tag
    ADD CONSTRAINT tag_pkey PRIMARY KEY (id);


--
-- Name: umbra_role_name_key; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY umbra_role
    ADD CONSTRAINT umbra_role_name_key UNIQUE (name);


--
-- Name: umbra_role_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY umbra_role
    ADD CONSTRAINT umbra_role_pkey PRIMARY KEY (id);


--
-- Name: umbra_user_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY umbra_user
    ADD CONSTRAINT umbra_user_pkey PRIMARY KEY (id);


--
-- Name: umbra_user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY umbra_user_roles
    ADD CONSTRAINT umbra_user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: umbra_user_username_key; Type: CONSTRAINT; Schema: public; Owner: umbra; Tablespace: 
--

ALTER TABLE ONLY umbra_user
    ADD CONSTRAINT umbra_user_username_key UNIQUE (username);


--
-- Name: fk244e41b6899b4c60; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY picture_person
    ADD CONSTRAINT fk244e41b6899b4c60 FOREIGN KEY (person_id) REFERENCES person(id);


--
-- Name: fk244e41b6d228d1c2; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY picture_person
    ADD CONSTRAINT fk244e41b6d228d1c2 FOREIGN KEY (picture_people_id) REFERENCES picture(id);


--
-- Name: fk26d47a312a0d2af4; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY entry_picture
    ADD CONSTRAINT fk26d47a312a0d2af4 FOREIGN KEY (picture_id) REFERENCES picture(id);


--
-- Name: fk6af661167111ea20; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY umbra_user_permissions
    ADD CONSTRAINT fk6af661167111ea20 FOREIGN KEY (user_id) REFERENCES umbra_user(id);


--
-- Name: fk6f3c4d41cbe72640; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY umbra_role_permissions
    ADD CONSTRAINT fk6f3c4d41cbe72640 FOREIGN KEY (role_id) REFERENCES umbra_role(id);


--
-- Name: fka5c750cf7111ea20; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY umbra_user_roles
    ADD CONSTRAINT fka5c750cf7111ea20 FOREIGN KEY (user_id) REFERENCES umbra_user(id);


--
-- Name: fka5c750cfcbe72640; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY umbra_user_roles
    ADD CONSTRAINT fka5c750cfcbe72640 FOREIGN KEY (role_id) REFERENCES umbra_role(id);


--
-- Name: fkb45ff7f72a0d2af4; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY format
    ADD CONSTRAINT fkb45ff7f72a0d2af4 FOREIGN KEY (picture_id) REFERENCES picture(id);


--
-- Name: fke3726e4d8514e274; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY entry_tag
    ADD CONSTRAINT fke3726e4d8514e274 FOREIGN KEY (tag_id) REFERENCES tag(id);


--
-- Name: fke3726e4dad233920; Type: FK CONSTRAINT; Schema: public; Owner: umbra
--

ALTER TABLE ONLY entry_tag
    ADD CONSTRAINT fke3726e4dad233920 FOREIGN KEY (entry_tags_id) REFERENCES entry(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

