-- 1. admin
CREATE TABLE `admin` (
                         `admin_no` int unsigned NOT NULL AUTO_INCREMENT,
                         `admin_id` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `admin_pw` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
                         PRIMARY KEY (`admin_no`),
                         UNIQUE KEY `admin_id_UNIQUE` (`admin_id`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 2. member
CREATE TABLE `member` (
                          `member_no` int unsigned NOT NULL AUTO_INCREMENT,
                          `member_id` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `member_hp` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `member_email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `member_pw` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `member_name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `member_postal_code` int unsigned NOT NULL,
                          `member_address_basic` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `member_address_detail` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          PRIMARY KEY (`member_no`),
                          UNIQUE KEY `member_id_UNIQUE` (`member_id`),
                          UNIQUE KEY `member_hp_UNIQUE` (`member_hp`),
                          UNIQUE KEY `member_email_UNIQUE` (`member_email`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 3. item
CREATE TABLE `item` (
                        `item_no` int unsigned NOT NULL AUTO_INCREMENT,
                        `item_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `item_category` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `item_grade` float NOT NULL DEFAULT '0',
                        `item_detail` varchar(400) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `item_price` int NOT NULL,
                        `item_onsale` tinyint unsigned NOT NULL DEFAULT '1',
                        `item_registered_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`item_no`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 4. item_photos
CREATE TABLE `item_photos` (
                               `item_no` int unsigned NOT NULL,
                               `item_thumb` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `item_img1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `item_img2` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `item_img3` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `item_thumb_modified` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `item_img1_modified` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `item_img2_modified` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               `item_img3_modified` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                               KEY `item_no` (`item_no`),
                               CONSTRAINT `item_no` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 5. item_stock
CREATE TABLE `item_stock` (
                              `item_stock_no` int unsigned NOT NULL AUTO_INCREMENT,
                              `item_no` int unsigned NOT NULL,
                              `item_size` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `item_stock` int unsigned NOT NULL DEFAULT '0',
                              PRIMARY KEY (`item_stock_no`),
                              KEY `item_no_idx` (`item_no`),
                              CONSTRAINT `item_no_fk` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 6. cart
CREATE TABLE `cart` (
                        `cart_no` int unsigned NOT NULL AUTO_INCREMENT,
                        `member_no` int unsigned NOT NULL,
                        `item_no` int unsigned NOT NULL,
                        `cart_item_quantity` int NOT NULL,
                        `item_size` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
                        PRIMARY KEY (`cart_no`),
                        KEY `item_no_fk_idx` (`item_no`),
                        KEY `member_no_fk_idx` (`member_no`),
                        CONSTRAINT `item_no_fk2` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
                        CONSTRAINT `member_no_fk` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 7. member_order
CREATE TABLE `member_order` (
                                `member_order_no` int unsigned NOT NULL AUTO_INCREMENT,
                                `member_no` int unsigned NOT NULL,
                                `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `order_hp` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
                                `order_postal_code` int unsigned NOT NULL,
                                `order_address_basic` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                `order_address_detail` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                `receiver_name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
                                `order_status` tinyint unsigned NOT NULL DEFAULT '1',
                                PRIMARY KEY (`member_order_no`),
                                KEY `member_no_fk2_idx` (`member_no`),
                                CONSTRAINT `member_no_fk2` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 8. non_member_order
CREATE TABLE `non_member_order` (
                                    `non_member_order_no` int unsigned NOT NULL AUTO_INCREMENT,
                                    `non_member_name` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    `order_hp` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `order_email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `receiver_name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `order_postal_code` int NOT NULL,
                                    `order_address_basic` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
                                    `order_address_detail` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                    `order_status` tinyint unsigned NOT NULL DEFAULT '1',
                                    PRIMARY KEY (`non_member_order_no`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 9. member_order_detail
CREATE TABLE `member_order_detail` (
                                       `member_order_detail_no` int unsigned NOT NULL AUTO_INCREMENT,
                                       `member_order_no` int unsigned NOT NULL,
                                       `item_no` int unsigned NOT NULL,
                                       `item_stock_no` int unsigned NOT NULL,
                                       `item_quantity` int unsigned NOT NULL,
                                       PRIMARY KEY (`member_order_detail_no`),
                                       KEY `member_order_no_idx` (`member_order_no`),
                                       KEY `item_no_fk4_idx` (`item_no`),
                                       KEY `item_stock_no_idx` (`item_stock_no`),
                                       CONSTRAINT `item_no_fk4` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                       CONSTRAINT `item_stock_no` FOREIGN KEY (`item_stock_no`) REFERENCES `item_stock` (`item_stock_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                       CONSTRAINT `member_order_no` FOREIGN KEY (`member_order_no`) REFERENCES `member_order` (`member_order_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;


-- 10. non_member_order_detail
CREATE TABLE `non_member_order_detail` (
                                           `non_member_order_detail_no` int unsigned NOT NULL AUTO_INCREMENT,
                                           `non_member_order_no` int unsigned NOT NULL,
                                           `item_no` int unsigned NOT NULL,
                                           `item_stock_no` int unsigned NOT NULL,
                                           `item_quantity` int unsigned NOT NULL,
                                           PRIMARY KEY (`non_member_order_detail_no`),
                                           KEY `non_member_order_no_idx` (`non_member_order_no`),
                                           KEY `item_no_fk5_idx` (`item_no`),
                                           KEY `item_stock_no_fk_idx` (`item_stock_no`),
                                           CONSTRAINT `item_no_fk5` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                           CONSTRAINT `item_stock_no_fk` FOREIGN KEY (`item_stock_no`) REFERENCES `item_stock` (`item_stock_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                           CONSTRAINT `non_member_order_no` FOREIGN KEY (`non_member_order_no`) REFERENCES `non_member_order` (`non_member_order_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 11. review
CREATE TABLE `review` (
                          `review_no` int unsigned NOT NULL AUTO_INCREMENT,
                          `member_no` int unsigned NOT NULL,
                          `item_no` int unsigned NOT NULL,
                          `review_context` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `review_created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `review_updated_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                          `review_score` int unsigned NOT NULL,
                          PRIMARY KEY (`review_no`),
                          KEY `member_no_fk4_idx` (`member_no`),
                          KEY `item_no_fk6_idx` (`item_no`),
                          CONSTRAINT `item_no_fk6` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
                          CONSTRAINT `member_no_fk4` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 12. qna
CREATE TABLE `qna` (
                       `qna_no` int unsigned NOT NULL AUTO_INCREMENT,
                       `member_no` int unsigned NOT NULL,
                       `item_no` int unsigned NOT NULL,
                       `qna_classification` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `qna_title` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `qna_question` varchar(400) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `qna_answer` varchar(400) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                       `qna_question_created_time` timestamp NOT NULL,
                       PRIMARY KEY (`qna_no`),
                       KEY `member_no_fk6_idx` (`member_no`),
                       KEY `item_no_fk6_idx` (`item_no`),
                       CONSTRAINT `item_no_fk7` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`) ON DELETE RESTRICT ON UPDATE CASCADE,
                       CONSTRAINT `member_no_fk7` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;

-- 13. noticeadminnotice
CREATE TABLE `notice` (
                          `notice_no` int unsigned NOT NULL AUTO_INCREMENT,
                          `admin_no` int unsigned NOT NULL,
                          `notice_title` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `notice_created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `notice_updated_time` timestamp NULL DEFAULT NULL,
                          `notice_content` varchar(400) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `notice_viewcount` int unsigned NOT NULL DEFAULT '0',
                          `notice_importance` tinyint unsigned NOT NULL DEFAULT '0',
                          PRIMARY KEY (`notice_no`),
                          KEY `admin_no_idx` (`admin_no`),
                          CONSTRAINT `admin_no` FOREIGN KEY (`admin_no`) REFERENCES `admin` (`admin_no`) ON DELETE RESTRICT ON UPDATE CASCADE
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;