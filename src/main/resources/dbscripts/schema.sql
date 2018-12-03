DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
  id              INT PRIMARY KEY AUTO_INCREMENT,
  name            VARCHAR(255),
  password        CLOB,
  role            ENUM ('ROLE_USER', 'ROLE_ADMIN'),
  last_login_time TIMESTAMP,
  reg_time        TIMESTAMP
);