SET search_path TO public;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'factor_type_enum') THEN
        CREATE TYPE factor_type_enum AS ENUM (
            'HYDRO_SANITARY_SYSTEM',
            'RAINWATER_DRAINAGE_SYSTEM',
            'PAVING',
            'ELECTRICAL_NETWORK',
            'EARTHWORKS'
        );
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'feasibility_enum') THEN
        CREATE TYPE feasibility_enum AS ENUM (
            'IMPOSSIBLE',
            'LOW_PROFITABILITY',
            'MODERATE',
            'PROFITABLE',
            'VERY_PROFITABLE'
        );
    END IF;
END
$$;

CREATE TABLE factor_types (
    id SERIAL PRIMARY KEY,
    factor_type factor_type_enum UNIQUE NOT NULL
);

CREATE TABLE price_factors (
    id SERIAL PRIMARY KEY,
    factor NUMERIC UNIQUE NOT NULL
);

CREATE TABLE neighborhoods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    price_factor_id INT NOT NULL
      CONSTRAINT fk_neighborhood_price_factor
        REFERENCES price_factors(id)
);

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street    VARCHAR(255),
    number    VARCHAR(255),
    city      VARCHAR(255),
    state     VARCHAR(255),
    cep       VARCHAR(20),
    neighborhood_id  INT NOT NULL
      CONSTRAINT fk_address_neighborhood
        REFERENCES neighborhoods(id)
);

CREATE TABLE third_parties (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) UNIQUE NOT NULL,
    factor_type_id INT NOT NULL
      CONSTRAINT fk_factor_type
        REFERENCES factor_types(id)
);

CREATE TABLE tract_owners (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE tracts (
    id SERIAL PRIMARY KEY,
    square_meters NUMERIC NOT NULL,
    address_id    INT
      CONSTRAINT fk_tract_address
        REFERENCES addresses(id)
        ON DELETE CASCADE,
    tract_owner_id INT
      CONSTRAINT fk_tract_owner
        REFERENCES tract_owners(id)
);

CREATE TABLE quotes (
    id SERIAL PRIMARY KEY,
    tract_id            INT
      CONSTRAINT fk_quotes_tract
        REFERENCES tracts(id)
        ON DELETE SET NULL,
    total_factors_price NUMERIC NOT NULL,
    lot_count           NUMERIC,
    price_per_lot       NUMERIC,
    feasibility         feasibility_enum,
    create_date         TIMESTAMP NOT NULL
);

CREATE TABLE factors (
    id SERIAL PRIMARY KEY,
    quote_id       INT NOT NULL
      CONSTRAINT fk_factors_quote
        REFERENCES quotes(id),
    third_party_id INT NOT NULL
      CONSTRAINT fk_factors_third_party
        REFERENCES third_parties(id),
    material_cost  NUMERIC NOT NULL,
    labor_cost     NUMERIC NOT NULL,
    factor_type_id INT NOT NULL
      CONSTRAINT fk_factors_type
        REFERENCES factor_types(id)
);
