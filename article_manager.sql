# DB 생성
DROP DATABASE IF EXISTS article_manager;
CREATE DATABASE article_manager;
DROP TABLE article;
DROP TABLE `member`;

# DB 선택 
USE article_manager;

# 게시글 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);
# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    `name` CHAR(200) NOT NULL
);

# 임시 회원 데이터
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test1',
loginPw = 'test1',
`name` = '김철수';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
`name` = '김영희';

#게시물 테이블에 memberId 칼럼 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL;


# 임시 게시글 데이터
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1',
memberId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
memberId = 2;

SELECT * FROM article;
SELECT * FROM `member`;

/*
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = CONCAT('제목',RAND()),
`body` = CONCAT('내용',RAND());

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = CONCAT('TestId',RAND()),
loginPW = CONCAT('TestPw',RAND()),
`name` = CONCAT('TestName',RAND());
*/

SELECT COUNT(*) = 0
FROM `member`
WHERE loginId = 'aaa1';

INSERT INTO article 
SET regDate = NOW(), 
updateDate = NOW(), 
title = '123',
`body` = '123';

#select rand()
