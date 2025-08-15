

DROP TABLE IF EXISTS order_item, orders, person, user_role, landing, species, fish_catch CASCADE;

-- Table creation statements
CREATE TABLE user_role (
                           id BIGINT PRIMARY KEY,
                           type VARCHAR(50) NOT NULL,
                           label VARCHAR(100),
                           description TEXT
);

CREATE TABLE person (
                        id BIGINT PRIMARY KEY,
                        username VARCHAR(100) UNIQUE NOT NULL,
                        email VARCHAR(100) UNIQUE NOT NULL,
                        password VARCHAR(100) NOT NULL,
                        role_id BIGINT REFERENCES user_role(id)
);

CREATE TABLE landing (
                         id BIGINT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL
);

CREATE TABLE species (
                         id BIGINT PRIMARY KEY,
                         species_name VARCHAR(100) NOT NULL
);

CREATE TABLE fisher_profile (
                         person_id BIGINT PRIMARY KEY,
                         fishing_license_number VARCHAR(255),
                         default_landing_id BIGINT
);




CREATE TABLE catch (
                            id BIGINT PRIMARY KEY,
                            fisher_profile_id BIGINT REFERENCES person(id),
                            species_id BIGINT REFERENCES species(id),
                            available BOOLEAN,
                            sold BOOLEAN,
                            time_stamp TIMESTAMP,
                            quantity_in_kg DECIMAL,
                            price DECIMAL,
                            landing_id BIGINT REFERENCES landing(id),
                            pickup_instructions VARCHAR(255),
                            pickup_time TIMESTAMP,
                            latitude DECIMAL,
                            longitude DECIMAL
);

CREATE TABLE orders (
                        id BIGINT PRIMARY KEY,
                        customer_id BIGINT REFERENCES person(id),
                        order_date_time TIMESTAMP,
                        order_status VARCHAR(50)
);

CREATE TABLE order_item (
                            id BIGINT PRIMARY KEY,
                            order_id BIGINT REFERENCES orders(id),
                            catch_id BIGINT REFERENCES catch(id),
                            quantity_in_kg DECIMAL
);