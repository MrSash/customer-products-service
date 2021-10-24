create table product
(
    id          uuid primary key default gen_random_uuid(),
    customer_id uuid
        constraint product_customer_fk references customer,
    title       varchar(255),
    description varchar(1024),
    price       decimal(10, 2),
    is_deleted  boolean not null default false,
    created_at  timestamp,
    modified_at timestamp
);
