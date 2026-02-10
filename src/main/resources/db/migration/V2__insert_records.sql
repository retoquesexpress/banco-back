INSERT INTO client (dni, user_name, password, name, surname1, surname2, api_token)
VALUES
('12345678A', 'jlopez', '123', 'Juan', 'López', 'García', 'token123'),
('87654321B', 'mgarcia', '123', 'María', 'García', 'Pérez', 'token456'),
('11223344C', 'aperez', '123', 'Ana', 'Pérez', NULL, 'token789'),
('11111111Z', 'retoquesexpress', '123', 'Retoques', 'Express', NULL, 'token888');


INSERT INTO account (iban, balance, id_client)
VALUES
('ES9121000418450200051332', 1500.50, '12345678A'),
('ES7921000813610123456789', 250.00, '12345678A'),
('ES6621000418450200059999', 9800.75, '87654321B'),
('ES5621014813710123449772', 1.00, '11223344C'),
('ES1111111111111111111111', 1000.00, '11111111Z');




INSERT INTO accountmovement
(id_account_movement, credit_card_origin, origin_movement, date, amount, movement_type, concept)
VALUES
(1, '4111111111111111', 'TARJETA_BANCARIA', '2026-01-01', 100.00, 'RETIRAR', 'Cash withdrawal'),
(2, '4111111111111111', 'DOMICILIACION', '2026-01-03', 45.99, 'DEPOSITAR', 'Amazon purchase'),
(3, '5500000000000004', 'TRANSFERENCIA', '2026-01-05', 500.00, 'DEPOSITAR', 'Salary');

INSERT INTO creditcard
(id_credit_card, card_number, expiration_date, cvv, nombre_completo, id_account)
VALUES
(1, '4111111111111111', '2027-12-31', 123, 'Juan López', 'ES9121000418450200051332'),
(2, '5500000000000004', '2028-06-30', 456, 'María García', 'ES6621000418450200059999'),
(3, '340000000000009', '2026-09-30', 789, 'Ana Pérez', 'ES5621014813710123449772'),
(4, '11111111111111', '2027-09-30', 123, 'retoques express', 'ES1111111111111111111111');


