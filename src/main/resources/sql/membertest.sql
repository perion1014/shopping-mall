-- member test procedure
USE perion;
DROP procedure IF EXISTS InsertDummyMembers;

DELIMITER $$
USE perion$$
CREATE PROCEDURE InsertDummyMembers()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE memberId VARCHAR(20);
    DECLARE memberHp VARCHAR(11);
    DECLARE memberEmail VARCHAR(50);
    DECLARE memberPw VARCHAR(20);
    DECLARE memberName VARCHAR(40);
    DECLARE memberPostalCode INT;
    DECLARE memberAddressBasic VARCHAR(100);
    DECLARE memberAddressDetail VARCHAR(50);

    WHILE i < 200 DO
        SET memberId = CONCAT('user', i);
    SET memberHp = CONCAT('0101234', LPAD(i, 3, '0'));
    SET memberEmail = CONCAT('user', i, '@example.com');
    SET memberPw = 'password123';
    SET memberName = CONCAT('User', i);
    SET memberPostalCode = 10000 + i;
    SET memberAddressBasic = CONCAT('Address ', i);
    SET memberAddressDetail = CONCAT('Detail ', i);

    INSERT INTO member (
        member_id, member_hp, member_email, member_pw,
        member_name, member_postal_code, member_address_basic, member_address_detail
    ) VALUES (
                 memberId, memberHp, memberEmail, memberPw,
                 memberName, memberPostalCode, memberAddressBasic, memberAddressDetail
             );

    SET i = i + 1;
END WHILE;
END$$

DELIMITER ;

-- member procedure call
CALL `perion`.`InsertDummyMembers`();