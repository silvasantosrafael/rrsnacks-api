CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customer(customer_id),
    street VARCHAR(255),
    number VARCHAR(20),
    district VARCHAR(255),
    complement VARCHAR(255),
    city VARCHAR(255)
);

CREATE TABLE snack (
    snack_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price DECIMAL(10, 2)
);
