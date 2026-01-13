INSERT INTO Client (dni, userName, password, name, surname1, surname2, apiToken)
VALUES
('12345678A', 'jlopez', 'pass123', 'Juan', 'López', 'García', 'token123'),
('87654321B', 'mgarcia', 'pass456', 'María', 'García', 'Pérez', 'token456'),
('11223344C', 'aperez', 'pass789', 'Ana', 'Pérez', NULL, 'token789');

INSERT INTO Account (iban, balance)
VALUES
('ES9121000418450200051332', 1500.50),
('ES7921000813610123456789', 250.00),
('ES6621000418450200059999', 9800.75);

INSERT INTO AccountMovement
(idAccountMovement, creditCardOrigin, originMovement, date, amount, movementType, concept)
VALUES
(1, '4111111111111111', 'ATM', '2026-01-01', -100.00, 'WITHDRAWAL', 'Cash withdrawal'),
(2, '4111111111111111', 'ONLINE', '2026-01-03', -45.99, 'PAYMENT', 'Amazon purchase'),
(3, '5500000000000004', 'TRANSFER', '2026-01-05', 500.00, 'DEPOSIT', 'Salary');

INSERT INTO CreditCard
(idCreditCard, cardNumber, expirationDate, cvv, clientName)
VALUES
(1, '4111111111111111', '2027-12-31', 123, 'Juan López'),
(2, '5500000000000004', '2028-06-30', 456, 'María García'),
(3, '340000000000009', '2026-09-30', 789, 'Ana Pérez');
