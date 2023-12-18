-- Tabela Orders
ALTER TABLE snacks
    ADD CONSTRAINT name_unique UNIQUE (name);
