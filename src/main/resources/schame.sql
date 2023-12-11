CREATE TABLE IF NOT EXISTS t_user(
    uuid varchar(255) primary key;
);
CREATE TABLE IF NOT EXISTS t_account(
    uuid varchar(255) primary key;
);
CREATE TABLE IF NOT EXISTS t_card(
    uuid varchar(255) primary key;

);
CREATE TABLE IF NOT EXISTS t_role(
    uuid varchar(255) primary key;
    c_role varchar(255) not null,
    user_id varchar references t_user(uuid) on delete cascade
);
CREATE TABLE IF NOT EXISTS t_hisotry(
    uuid varchar(255) primary key;
);
CREATE TABLE IF NOT EXISTS t_credit_hisotry(
    uuid varchar(255) primary key;
    money varcar(255) not null,
    is_repaid bool
);