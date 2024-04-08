
use projeto_santander_coders;

CREATE TABLE IF NOT EXISTS products (
    id INTEGER,
    name VARCHAR(255) NOT NULL,
    amount INTEGER NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(id)
    ) ENGINE=INNODB;