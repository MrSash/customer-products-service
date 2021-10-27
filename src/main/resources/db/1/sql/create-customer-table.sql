create table customer
(
    id          uuid primary key default gen_random_uuid(),
    title       varchar(255),
    is_deleted  boolean not null default false,
    created_at  timestamp,
    modified_at timestamp
);
