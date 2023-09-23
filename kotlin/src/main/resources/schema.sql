CREATE TABLE IF NOT EXISTS products
(
    id    BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
    pname VARCHAR(120) NOT NULL COMMENT '제품명',
    stock INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '재고량',
    PRIMARY KEY (id)
);