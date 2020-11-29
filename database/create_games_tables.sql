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