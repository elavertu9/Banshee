CREATE TABLE public.users
(
    user_id uuid NOT NULL,
    username text NOT NULL
    CONSTRAINT user_id_pk PRIMARY KEY (user_id)
)
ALTER TABLE public.users
    OWNER to postgres;

COMMENT ON TABLE public.users
    IS 'Keeps track of Banshee users'


CREATE TABLE public.games
(
    game_id uuid NOT NULL,
    game_object text[] NOT NULL,
    player1_id uuid NOT NULL,
    player2_id uuid NOT NULL,
    finished boolean NOT NULL,
    create_date timestamp with time zone NOT NULL,
    update_date timestamp with time zone NOT NULL,
    CONSTRAINT game_id_pk PRIMARY KEY (game_id)
    CONSTRAINT player1_id_fk FOREIGN KEY (player1_id)
        REFERENCES public.users (player1_id) MATCH SIMPLE
           ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT player2_id_fk FOREIGN KEY (player2_id)
        REFERENCES public.users (player2_id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE CASCADE
)
ALTER TABLE public.games
    OWNER to postgres;

COMMENT ON TABLE public.games
    IS 'Keeps track of Banshee games';