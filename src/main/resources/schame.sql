
-- Создание таблицы для пользователей
CREATE TABLE t_user (
                        user_id varchar(255) PRIMARY KEY,
                        username VARCHAR(50) NOT NULL,
                        password VARCHAR(100) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE ,
                        full_name VARCHAR(100) NOT NULL,
                        date_of_birth DATE NOT NULL
);
-- Создание таблицы для ролей пользователей
CREATE TABLE t_role (
                        role_id varchar(255) PRIMARY KEY,
                        role_name VARCHAR(50) NOT NULL,
                        user_id varchar(255) NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES t_user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы для счетов пользователей
CREATE TABLE t_account (
                           account_id varchar(255) PRIMARY KEY,
                           user_id varchar(255) NOT NULL,
                           balance DECIMAL(10,2) NOT NULL,
                           account_type VARCHAR(50) NOT NULL,
                           date_created DATE NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES t_user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы для банковских карт
CREATE TABLE t_card (
                        card_id varchar(255) PRIMARY KEY,
                        account_id varchar(255) NOT NULL,
                        card_number VARCHAR(16) NOT NULL,
                        expiration_date DATE NOT NULL,
                        cvv VARCHAR(3) NOT NULL,
                        is_active BOOLEAN NOT NULL,
                        FOREIGN KEY (account_id) REFERENCES t_account(account_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы для истории транзакций
CREATE TABLE t_history (
                           transaction_id varchar(255) PRIMARY KEY,
                           account_id varchar(255) NOT NULL,
                           transaction_type VARCHAR(50) NOT NULL,
                           amount DECIMAL(10,2) NOT NULL,
                           transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           description TEXT,
                           FOREIGN KEY (account_id) REFERENCES t_account(account_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы для истории кредитных операций
CREATE TABLE t_credit_history (
                                  credit_id varchar(255) PRIMARY KEY,
                                  user_id varchar(255) NOT NULL,
                                  credit_amount DECIMAL(10,2) NOT NULL,
                                  interest_rate DECIMAL(5,2) NOT NULL,
                                  credit_date DATE NOT NULL,
                                  repayment_date DATE NOT NULL,
                                  status VARCHAR(50) NOT NULL,
                                  FOREIGN KEY (user_id) REFERENCES t_user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);
