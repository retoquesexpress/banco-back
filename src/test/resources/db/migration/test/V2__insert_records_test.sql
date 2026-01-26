-- Insert Client
INSERT INTO Client (dni, user_name, password, name, surname1, surname2, api_token) 
VALUES ('12345678A', 'jdoe', 'pass', 'John', 'Doe', 'Smith', 'tok');

-- Insert Account
INSERT INTO Account (iban, balance, id_client) 
VALUES ('ES1234567890123456789012', 1000.00, '12345678A');

-- Insert Credit Card
INSERT INTO creditcard (id_credit_card, card_number, expiration_date, cvv, nombre_completo, id_account) 
VALUES (1, '1234567890123456', '2026-12-01', 123, 'JOHN DOE', 'ES1234567890123456789012');

-- Insert Account Movement
INSERT INTO accountmovement (id_account_movement, credit_card_origin, origin_movement, date, amount, movement_type, concept) 
VALUES (1, '1234567890123456', 'TARJETA_BANCARIA', '2026-01-01 10:00:00', 100.0, 'DEPOSITAR', 'Test');
