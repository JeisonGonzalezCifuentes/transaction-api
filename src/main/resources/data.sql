-- Insert 15 accounts
INSERT INTO accounts (id, number, customer_name) VALUES (1, 'ACC001', 'Juan Pérez');
INSERT INTO accounts (id, number, customer_name) VALUES (2, 'ACC002', 'Ana García');
INSERT INTO accounts (id, number, customer_name) VALUES (3, 'ACC003', 'Luis Gómez');
INSERT INTO accounts (id, number, customer_name) VALUES (4, 'ACC004', 'María López');
INSERT INTO accounts (id, number, customer_name) VALUES (5, 'ACC005', 'Carlos Sánchez');
INSERT INTO accounts (id, number, customer_name) VALUES (6, 'ACC006', 'Laura Díaz');
INSERT INTO accounts (id, number, customer_name) VALUES (7, 'ACC007', 'Pedro Ramírez');
INSERT INTO accounts (id, number, customer_name) VALUES (8, 'ACC008', 'Sofía Herrera');
INSERT INTO accounts (id, number, customer_name) VALUES (9, 'ACC009', 'Andrés Martínez');
INSERT INTO accounts (id, number, customer_name) VALUES (10, 'ACC010', 'Gabriela Castro');
INSERT INTO accounts (id, number, customer_name) VALUES (11, 'ACC011', 'Ricardo Torres');
INSERT INTO accounts (id, number, customer_name) VALUES (12, 'ACC012', 'Daniela Rojas');
INSERT INTO accounts (id, number, customer_name) VALUES (13, 'ACC013', 'Francisco Méndez');
INSERT INTO accounts (id, number, customer_name) VALUES (14, 'ACC014', 'Camila Morales');
INSERT INTO accounts (id, number, customer_name) VALUES (15, 'ACC015', 'Emilio Navarro');
INSERT INTO accounts (id, number, customer_name) VALUES (16, 'ACC0016', 'Juan Pérez');
INSERT INTO accounts (id, number, customer_name) VALUES (17, 'ACC017', 'Daniela Rojas');

-- Insert transactions for various accounts
INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1000.00, 'Starbucks', '2025-04-28T12:00:00', 1);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (2500.50, 'Amazon', '2025-04-27T14:30:00', 2);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1500.75, 'Netflix', '2025-04-26T18:45:00', 3);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (300.00, 'Uber', '2025-04-25T09:15:00', 1);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (30.00, 'Tenpo', '2025-04-25T09:15:00', 16);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (700.00, 'Spotify', '2025-04-24T10:20:00', 2);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1200.00, 'Apple', '2025-04-23T16:00:00', 4);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (900.00, 'Google Play', '2025-04-22T19:30:00', 5);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (650.00, 'Falabella', '2025-04-21T13:40:00', 6);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1100.00, 'Lider', '2025-04-20T15:10:00', 7);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (500.00, 'MercadoLibre', '2025-04-19T11:50:00', 8);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1700.00, 'AliExpress', '2025-04-18T08:20:00', 9);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (800.00, 'Ripley', '2025-04-17T20:00:00', 10);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (200.00, 'Broota', '2025-04-16T17:45:00', 11);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (1300.00, 'Cinemark', '2025-04-15T14:30:00', 12);

INSERT INTO transactions (amount, merchant, date, account_id)
VALUES (400.00, 'La Polar', '2025-04-14T10:00:00', 13);
