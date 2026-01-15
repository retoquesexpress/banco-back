CREATE TABLE Client (
    dni VARCHAR(255) PRIMARY KEY,
    user_name VARCHAR(255),
    password VARCHAR(255),
    name VARCHAR(255),
    surname1 VARCHAR(255),
    surname2 VARCHAR(255),
    api_token VARCHAR(255)
);

CREATE TABLE Account (
    iban VARCHAR(255) PRIMARY KEY,
    balance DOUBLE,
    id_client VARCHAR(255),
    CONSTRAINT fk_account_client FOREIGN KEY (id_client) REFERENCES Client(dni)
);

CREATE TABLE creditcard (
    id_credit_card INT PRIMARY KEY,
    card_number VARCHAR(255),
    expiration_date VARCHAR(255),
    cvv INT,
    nombre_completo VARCHAR(255),
    id_account VARCHAR(255),
    CONSTRAINT fk_creditcard_account FOREIGN KEY (id_account) REFERENCES Account(iban)
);

CREATE TABLE accountmovement (
    id_account_movement INT AUTO_INCREMENT PRIMARY KEY,
    credit_card_origin VARCHAR(255),
    origin_movement VARCHAR(255),
    date TIMESTAMP,
    amount DOUBLE,
    movement_type VARCHAR(255),
    concept VARCHAR(255)
);
