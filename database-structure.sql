DROP DATABASE IF EXISTS wannawanna;

CREATE DATABASE wannawanna
    WITH
    OWNER = postgres
    TEMPLATE = template1
    ENCODING = 'UTF8'
    STRATEGY = 'wal_log'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE wannawanna
    IS 'Database for the Wanna Wanna app';

GRANT ALL ON DATABASE wannawanna TO postgres WITH GRANT OPTION;


DROP TABLE IF EXISTS public.users

CREATE TABLE public.users
(
    uid uuid NOT NULL DEFAULT gen_random_uuid(),
    names text,
    notify_on_list_shared boolean NOT NULL DEFAULT true,
    notify_on_list_items_update boolean NOT NULL DEFAULT true,
    notify_on_item_state_update boolean NOT NULL DEFAULT true,
    expo_push_token text,
    device_push_token text,
    deleted boolean NOT NULL DEFAULT false,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    CONSTRAINT "User ID" PRIMARY KEY (uid)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

COMMENT ON TABLE public.users
    IS 'Authorization table that keeps all the user names and settings';


DROP TABLE IF EXISTS public.lists

CREATE TABLE public.lists
(
    list_id uuid NOT NULL DEFAULT gen_random_uuid(),
    share_id uuid NOT NULL DEFAULT gen_random_uuid(),
    name text,
    type text NOT NULL DEFAULT 'project',
    deadline timestamp with time zone,
    is_active boolean NOT NULL DEFAULT true,
    completed boolean NOT NULL DEFAULT false,
    completed_at timestamp with time zone,
    hide_completed boolean NOT NULL DEFAULT false,
    notify_on_list_shared boolean NOT NULL DEFAULT true,
    notify_on_list_items_update boolean NOT NULL DEFAULT true,
    notify_on_item_state_update boolean NOT NULL DEFAULT true,
    deleted boolean NOT NULL DEFAULT false,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    PRIMARY KEY (list_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lists
    OWNER to postgres;

COMMENT ON TABLE public.lists
    IS 'Users'' lists containers';


DROP TABLE IF EXISTS public.user_lists

CREATE TABLE public.user_lists
(
    user_lists_id uuid NOT NULL DEFAULT gen_random_uuid(),
    uid uuid NOT NULL,
    list_id uuid NOT NULL,
    sort_order integer,
    is_active boolean NOT NULL DEFAULT true,
    notify_on_list_shared boolean NOT NULL DEFAULT true,
    notify_on_list_items_update boolean NOT NULL DEFAULT true,
    notify_on_item_state_update boolean NOT NULL DEFAULT true,
    deleted boolean NOT NULL DEFAULT false,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    PRIMARY KEY (user_lists_id),
    CONSTRAINT "Link to the user and the list" FOREIGN KEY (uid, list_id)
        REFERENCES public.users (uid, list_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_lists
    OWNER to postgres;

COMMENT ON TABLE public.user_lists
    IS 'Link between the users and the lists';
