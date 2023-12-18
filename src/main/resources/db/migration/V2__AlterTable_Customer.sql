-- Tabela Customer
ALTER TABLE customers
    ADD CONSTRAINT unique_email UNIQUE (email);
