-- Usage:
-- Replace $1 and $2 with appropriate arguments before running
-- To run:
--      1.) psql -U postgres postgres -f ./create_all_db_resources.sql
--      OR
--      2.) connect to psql shell, and run \i ./create_all_db_resources.sql

-- Create database
CREATE DATABASE "Banshee"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
COMMENT ON DATABASE "Banshee" IS 'Stores all data needed for the Banshee application';

-- Connect to database
\c Banshee

-- Create superuser user
CREATE ROLE banshee_owner WITH LOGIN PASSWORD $1;
-- GRANT rds_superuser to banshee_owner;
ALTER USER banshee_owner WITH SUPERUSER;

-- Create readonly user
CREATE ROLE read_only WITH LOGIN PASSWORD $2;
GRANT CONNECT ON DATABASE "Banshee" TO read_only;
GRANT USAGE ON SCHEMA public TO read_only;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO read_only;
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO read_only;

-- Create users table
CREATE TABLE public.users
(
    user_id uuid NOT NULL PRIMARY KEY,
    username text NOT NULL
);
ALTER TABLE public.users OWNER to postgres;
COMMENT ON TABLE public.users IS 'Keeps track of Banshee users';

-- Create games table
CREATE TABLE public.games
(
    game_id uuid NOT NULL PRIMARY KEY,
    game_object text[] NOT NULL,
    player1_id uuid NOT NULL,
    player2_id uuid NOT NULL,
    finished boolean NOT NULL,
    create_date timestamp with time zone NOT NULL,
    update_date timestamp with time zone NOT NULL,
    CONSTRAINT player1_id_fk FOREIGN KEY (player1_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
           ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT player2_id_fk FOREIGN KEY (player2_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
);
ALTER TABLE public.games OWNER to postgres;
COMMENT ON TABLE public.games IS 'Keeps track of Banshee games';