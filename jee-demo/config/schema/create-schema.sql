CREATE TABLE project
        (
        id bigint NOT NULL,
        title character varying(128) NOT NULL,
        sub_title character varying(256), 
        company character varying(128) NOT NULL, 
        color_code character varying(10) NOT NULL, 
        priority integer NOT NULL DEFAULT 0, 
        active boolean NOT NULL DEFAULT true,
        CONSTRAINT  xpk_project  PRIMARY KEY (id)
        );
ALTER TABLE project OWNER TO jeedemo;

CREATE SEQUENCE project_seq INCREMENT 1 CACHE 1 MINVALUE 1 START 1;
ALTER TABLE project_seq OWNER TO jeedemo;
