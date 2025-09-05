CREATE TABLE trading_order (
    id UUID NOT NULL PRIMARY KEY,
    price DECIMAL(18,8) NOT NULL,
    quantity DECIMAL(18,8) NOT NULL,
    symbol VARCHAR(20) NOT NULL,
    order_type VARCHAR(50) NOT NULL,
    side VARCHAR(50) NOT NULL,
    filled_quantity DECIMAL(18,8) DEFAULT 0,
    status VARCHAR(50) NOT NULL,
    expires_at TIMESTAMP,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    deleted_date TIMESTAMP
);