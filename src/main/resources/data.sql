INSERT INTO accounts (id, number, customer_name)
SELECT 1, 'ACC001', 'Juan Pérez'
WHERE NOT EXISTS (SELECT 1 FROM accounts WHERE id = 1);

INSERT INTO accounts (id, number, customer_name)
SELECT 2, 'ACC002', 'Ana García'
WHERE NOT EXISTS (SELECT 1 FROM accounts WHERE id = 2);

INSERT INTO accounts (id, number, customer_name)
SELECT 3, 'ACC003', 'Luis Gómez'
WHERE NOT EXISTS (SELECT 1 FROM accounts WHERE id = 3);

INSERT INTO accounts (id, number, customer_name)
SELECT 4, 'ACC004', 'María López'
WHERE NOT EXISTS (SELECT 1 FROM accounts WHERE id = 4);

INSERT INTO accounts (id, number, customer_name)
SELECT 5, 'ACC005', 'Carlos Sánchez'
WHERE NOT EXISTS (SELECT 1 FROM accounts WHERE id = 5);

INSERT INTO accounts (id, number, customer_name)
SELECT 6, 'ACC006', 'Laura Díaz'
WHERE NOT EXISTS (SELECT 1 FROM accounts WHERE id = 6);

INSERT INTO accounts (id, number, customer_name)
SELECT 7, 'ACC007', 'Pedro Ramírez'
WHERE NOT EXISTS (SELECT 1 FROM accounts WHERE id = 7);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 1000.00, 'Starbucks', '2025-04-28T12:00:00', 1
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 1000.00 AND merchant = 'Starbucks' AND date = '2025-04-28T12:00:00' AND account_id = 1
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 300.00, 'Uber', '2025-04-25T09:15:00', 1
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 300.00 AND merchant = 'Uber' AND date = '2025-04-25T09:15:00' AND account_id = 1
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 2500.50, 'Amazon', '2025-04-27T14:30:00', 2
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 2500.50 AND merchant = 'Amazon' AND date = '2025-04-27T14:30:00' AND account_id = 2
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 700.00, 'Apple', '2025-04-24T10:20:00', 2
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 700.00 AND merchant = 'Apple' AND date = '2025-04-24T10:20:00' AND account_id = 2
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 320.00, 'Tenpo', '2025-04-24T10:20:00', 2
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 320.00 AND merchant = 'Tenpo' AND date = '2025-04-24T10:20:00' AND account_id = 2
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 450.00, 'Tekton', '2025-04-24T10:20:00', 2
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 450.00 AND merchant = 'Tekton' AND date = '2025-04-24T10:20:00' AND account_id = 2
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 60.00, 'Spotify', '2025-04-24T10:20:00', 2
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 60.00 AND merchant = 'Spotify' AND date = '2025-04-24T10:20:00' AND account_id = 2
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 900.00, 'Google Play', '2025-04-22T19:30:00', 5
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 900.00 AND merchant = 'Google Play' AND date = '2025-04-22T19:30:00' AND account_id = 5
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 650.00, 'Falabella', '2025-04-21T13:40:00', 6
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 650.00 AND merchant = 'Falabella' AND date = '2025-04-21T13:40:00' AND account_id = 6
);

INSERT INTO transactions (amount, merchant, date, account_id)
SELECT 1100.00, 'Lider', '2025-04-20T15:10:00', 7
WHERE NOT EXISTS (
    SELECT 1 FROM transactions
    WHERE amount = 1100.00 AND merchant = 'Lider' AND date = '2025-04-20T15:10:00' AND account_id = 7
);
