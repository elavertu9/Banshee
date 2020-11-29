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
CREATE ROLE banshee_owner WITH LOGIN PASSWORD 'password';
ALTER USER banshee_owner WITH SUPERUSER;

-- Create readonly user
CREATE ROLE read_only WITH LOGIN PASSWORD 'password';
GRANT CONNECT ON DATABASE "Banshee" TO read_only;
GRANT USAGE ON SCHEMA public TO read_only;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO read_only;
GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO read_only;

-- Add UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create users table
CREATE TABLE public.users
(
    user_id uuid NOT NULL PRIMARY KEY,
    username text NOT NULL,
    first_name text NOT NULl,
    last_name text NOT NULL,
    email_address text NOT NULL,
    wins bigint NOT NULL,
    losses bigint NOT NULL,
    games_played bigint NOT NULL,
    create_date timestamp with time zone NOT NULL,
    update_date timestamp with time zone NOT NULL
);
ALTER TABLE public.users OWNER to postgres;
COMMENT ON TABLE public.users IS 'Keeps track of Banshee users';

-- Create games table
CREATE TABLE public.games
(
    game_id uuid NOT NULL PRIMARY KEY,
    game_object character varying NOT NULL,
    finished boolean NOT NULL,
    user1_color text NOT NULL,
    user2_color text NOT NULL,
    winner int,
    is_forfeit boolean NOT NULL,
    forfeit_user smallint,
    turn smallint NOT NULL,
    create_date timestamp with time zone NOT NULL,
    update_date timestamp with time zone NOT NULL,
    user1_id uuid NOT NULL REFERENCES users (user_id) ON UPDATE NO ACTION ON DELETE CASCADE,
    user2_id uuid NOT NULL REFERENCES users (user_id) ON UPDATE NO ACTION ON DELETE CASCADE
);
ALTER TABLE public.games OWNER to postgres;
COMMENT ON TABLE public.games IS 'Keeps track of Banshee games';

-- Insert default Users records
 INSERT INTO public.users(user_id, username, first_name, last_name, email_address, wins, losses, games_played, create_date, update_date)
 	VALUES (uuid_generate_v4(), 'User1', 'User', 'One', 'User1@gmail.com', 0, 0, 0, current_timestamp, current_timestamp);
 INSERT INTO public.users(user_id, username, first_name, last_name, email_address, wins, losses, games_played, create_date, update_date)
    VALUES (uuid_generate_v4(), 'User2', 'User', 'Two', 'User2@gmail.com', 0, 0, 0, current_timestamp, current_timestamp);