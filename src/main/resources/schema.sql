CREATE TABLE users(
                      id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(50) NOT NULL,
                      last_name  VARCHAR(50) NOT NULL,
                      age        INT         NOT NULL,
                      active     BOOLEAN     NOT NULL);