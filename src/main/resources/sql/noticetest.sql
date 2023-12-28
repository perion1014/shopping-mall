-- 순서대로 실행해주세요

-- 1
USE perion;
DROP procedure IF EXISTS generate_notice_test_data;

-- 2
DELIMITER $$
USE perion$$
CREATE PROCEDURE generate_notice_test_data()
BEGIN

    DECLARE i INT DEFAULT 1;
    DECLARE admin_no INT DEFAULT 1; -- 임의의 admin_no 값 지정, 필요에 따라 수정

    WHILE i <= 200 DO
        INSERT INTO notice (admin_no, notice_title, notice_content)
        VALUES (admin_no, CONCAT('공지사항 테스트 제목 ', i), CONCAT('공지사항 테스트 내용 ', i));
        SET i = i + 1;
END WHILE;

END $$

-- 3
DELIMITER ;
CALL perion.generate_notice_test_data();