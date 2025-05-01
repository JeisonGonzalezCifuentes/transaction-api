-- Insert 15 accounts
INSERT INTO accounts (id, number, customer_name) VALUES (1, 'ACC001', 'Juan Pérez');
INSERT INTO accounts (id, number, customer_name) VALUES (2, 'ACC002', 'Ana García');
INSERT INTO accounts (id, number, customer_name) VALUES (3, 'ACC003', 'Luis Gómez');
INSERT INTO accounts (id, number, customer_name) VALUES (4, 'ACC004', 'María López');
INSERT INTO accounts (id, number, customer_name) VALUES (5, 'ACC005', 'Carlos Sánchez');
INSERT INTO accounts (id, number, customer_name) VALUES (6, 'ACC006', 'Laura Díaz');
INSERT INTO accounts (id, number, customer_name) VALUES (7, 'ACC007', 'Pedro Ramírez');

-- Insert transactions for various accounts
INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1000.00, 'Starbucks', '2025-04-28T12:00:00', 1);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (300.00, 'Uber', '2025-04-25T09:15:00', 1);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (2500.50, 'Amazon', '2025-04-27T14:30:00', 2);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (700.00, 'Apple', '2025-04-24T10:20:00', 2);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (320.00, 'Tenpo', '2025-04-24T10:20:00', 2);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (450.00, 'Tekton', '2025-04-24T10:20:00', 2);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (60.00, 'Spotify', '2025-04-24T10:20:00', 2);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (900.00, 'Google Play', '2025-04-22T19:30:00', 5);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (650.00, 'Falabella', '2025-04-21T13:40:00', 6);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1100.00, 'Lider', '2025-04-20T15:10:00', 7);
