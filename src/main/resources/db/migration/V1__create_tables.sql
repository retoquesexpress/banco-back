CREATE TABLE client (
    dni VARCHAR(9) PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname1 VARCHAR(50) NOT NULL,
    surname2 VARCHAR(50),
    api_token VARCHAR(50) NOT NULL
);

CREATE TABLE account (
    iban VARCHAR(50) NOT NULL,
    balance DECIMAL(10,2),
    id_client VARCHAR(9) NOT NULL
);

CREATE TABLE accountmovement (
    id_account_movement INT PRIMARY KEY AUTO_INCREMENT,
    credit_card_origin VARCHAR(50) NOT NULL,
    origin_movement VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    amount DOUBLE NOT NULL,
    movement_type VARCHAR(50) NOT NULL,
    concept VARCHAR(50)
);

CREATE TABLE creditcard (
    id_credit_card INT PRIMARY KEY,
    card_number VARCHAR(50) NOT NULL,
    expiration_date DATE NOT NULL,
    cvv INT NOT NULL,
    nombre_completo VARCHAR(50) NOT NULL,
    id_account VARCHAR(50) NOT NULL
);



