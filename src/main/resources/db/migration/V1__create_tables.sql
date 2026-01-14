CREATE TABLE Client (
    dni VARCHAR(9) PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname1 VARCHAR(50) NOT NULL,
    surname2 VARCHAR(50),
    api_token VARCHAR(50) NOT NULL
);

CREATE TABLE Account (
     iban VARCHAR(50) NOT NULL,
     balance DECIMAL(10,2),
     idClient VARCHAR(9) NOT NULL
);

CREATE TABLE AccountMovement (
    idAccountMovement INT PRIMARY KEY AUTO_INCREMENT,
    creditCardOrigin VARCHAR(50) NOT NULL,
    originMovement VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    amount DOUBLE NOT NULL,
    movementType VARCHAR(50) NOT NULL,
    concept VARCHAR(50)
);

CREATE TABLE CreditCard (
        idCreditCard INT PRIMARY KEY,
        cardNumber VARCHAR(50) NOT NULL,
        expirationDate DATE NOT NULL,
        cvv INT NOT NULL,
        nombreCompleto VARCHAR(50) NOT NULL,
        idAccount VARCHAR(50) NOT NULL
);



