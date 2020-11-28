-- Create users table
CREATE TABLE public.users
(
    user_id uuid NOT NULL PRIMARY KEY,
    username text NOT NULL,
    first_name text NOT NULl,
    last_name text NOT NULL,
    email_address text NOT NULL,
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
    create_date timestamp with time zone NOT NULL,
    update_date timestamp with time zone NOT NULL,
    player1_id uuid NOT NULL REFERENCES users (user_id) ON UPDATE NO ACTION ON DELETE CASCADE,
    player2_id uuid NOT NULL REFERENCES users (user_id) ON UPDATE NO ACTION ON DELETE CASCADE
);
ALTER TABLE public.games OWNER to postgres;
COMMENT ON TABLE public.games IS 'Keeps track of Banshee games';

-- Insert default Users records
 INSERT INTO public.users(user_id, username, first_name, last_name, email_address, create_date, update_date)
 	VALUES (uuid_generate_v4(), 'Player1', 'Player', 'One', 'Player1@gmail.com', current_timestamp, current_timestamp);
 INSERT INTO public.users(user_id, username, first_name, last_name, email_address, create_date, update_date)
    VALUES (uuid_generate_v4(), 'Player2', 'Player', 'Two', 'Player2@gmail.com', current_timestamp, current_timestamp);