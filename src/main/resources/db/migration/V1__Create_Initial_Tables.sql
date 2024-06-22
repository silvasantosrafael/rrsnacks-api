-- Tabela Customer
CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

-- Tabela Address
CREATE TABLE addresses (
    address_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id) UNIQUE NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(50),
    district VARCHAR(255),
    complement VARCHAR(255),
    city VARCHAR(255),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Tabela Snack
CREATE TABLE snacks (
    snack_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL
);

-- Tabela Order
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT REFERENCES customers(customer_id) NOT NULL,
    payment_type VARCHAR(50),
    order_date TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Tabela de junção para a relação Many-to-Many entre Order e Snack
CREATE TABLE order_snack (
    order_snack_id SERIAL PRIMARY key,
    order_id INT REFERENCES orders(order_id) NOT NULL,
    snack_id INT REFERENCES snacks(snack_id) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (snack_id) REFERENCES snacks(snack_id)
);