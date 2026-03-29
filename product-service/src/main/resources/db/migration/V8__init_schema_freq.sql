CREATE TABLE payment_frequencies (
    id INT PRIMARY KEY,
    code VARCHAR(10) UNIQUE,
    name VARCHAR(20) NOT NULL,
    factor DECIMAL(5,4) NOT NULL,
    is_active BOOLEAN DEFAULT true
);