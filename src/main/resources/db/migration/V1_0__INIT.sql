create table public.begin_read_raw_message
(
    id        bigint,
    timestamp timestamp with time zone,
    input_buffer bytea,
    primary key (id)
);

create table public.begin_write_raw_message
(
    id        bigint,
    timestamp timestamp with time zone,
    input_buffer bytea,
    primary key (id)
);

create table public.end_read_raw_message
(
    id        bigint,
    timestamp timestamp with time zone,
    output_buffer bytea,
    status    bigint,
    primary key (id)
);

create table public.end_write_raw_message
(
    id        bigint,
    timestamp timestamp with time zone,
    output_buffer bytea,
    status    bigint,
    primary key (id)
);


