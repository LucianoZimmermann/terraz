CREATE TABLE price_factors (
    id SERIAL PRIMARY KEY,
    factor NUMERIC NOT NULL
);

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street VARCHAR(255),
    city VARCHAR(255),
    neighborhood VARCHAR(255),
    cep VARCHAR(20),
    price_factor_id INT,
    CONSTRAINT fk_address_price_factor FOREIGN KEY (price_factor_id) REFERENCES price_factors(id)
);

CREATE TABLE third_parties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) NOT NULL
);

CREATE TABLE factors (
    id SERIAL PRIMARY KEY,
    third_party_id INT NOT NULL,
    material_cost NUMERIC NOT NULL,
    labor_cost NUMERIC NOT NULL,
    factor_type VARCHAR(50) NOT NULL,
    CONSTRAINT fk_factors_third_party FOREIGN KEY (third_party_id) REFERENCES third_parties(id)
);

CREATE TABLE tracts (
    id SERIAL PRIMARY KEY,
    square_meters NUMERIC NOT NULL
    CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES tracts(id)
);

CREATE TABLE tract_owners (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tract_id INT NOT NULL,
    CONSTRAINT fk_tract_owners_tract FOREIGN KEY (tract_id) REFERENCES tracts(id)
);

CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    tract_id INT NOT NULL,
    tract_owner_id INT NOT NULL,
    total_price NUMERIC NOT NULL,
    create_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_quotes_tract FOREIGN KEY (tract_id) REFERENCES tracts(id),
    CONSTRAINT fk_quotes_tract_owner FOREIGN KEY (tract_owner_id) REFERENCES tract_owners(id)
);

CREATE TABLE quote_factors (
    quote_id BIGINT NOT NULL,
    factor_id BIGINT NOT NULL,
    PRIMARY KEY (quote_id, factor_id),
    FOREIGN KEY (quote_id) REFERENCES quotes(id),
    FOREIGN KEY (factor_id) REFERENCES factors(id)
);
