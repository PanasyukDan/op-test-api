CREATE TABLE beer (
    id bigint NOT NULL,
    name character varying(255),
    description character varying,
    image_url character varying(255),
    abv float,
    ibu float,
    volume int,
    volume_unit character varying(255),
    UNIQUE(name),
    CONSTRAINT beer_pkey PRIMARY KEY (id)
);

CREATE TABLE user (
    id uuid NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE user_beer (
    user_id uuid,
    beer_id bigint,
    CONSTRAINT user_fkey FOREIGN KEY (user_id)
                REFERENCES user (id),
    CONSTRAINT beer_fkey FOREIGN KEY (beer_id)
                REFERENCES beer (id)
);
