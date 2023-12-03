CREATE TABLE `user` (
	`uid`	LONG	NOT NULL	COMMENT 'Auto Increment',
	`email`	VARCHAR(40)	NOT NULL	COMMENT '로컬용 계정',
	`nick`	VARCHAR(15)	NOT NULL	DEFAULT Nickname	COMMENT '닉네임, 별도로 설정 가능',
	`password`	VARCHAR(40)	NOT NULL	COMMENT '암호화',
	`profilleImage`	VARCHAR200)	NOT NULL	DEFAULT assets/images/user/default-profile.jpg	COMMENT '프로필 이미지',
	`created_at`	TIMESTAMP	NOT NULL,
	`updated_at`	TIMESTAMP	NOT NULL	COMMENT '처음 생성한 시간이 첫 수정시간',
	`status`	VARCHAR(255)	NOT NULL	DEFAULT activated	COMMENT '계정 삭제 확인용',
	`role`	VARCHAR(100)	NOT NULL	DEFAULT guest	COMMENT 'admin/guest 구분용'
);

CREATE TABLE `board` (
	`bid`	LONG	NOT NULL	COMMENT 'Auto Increment',
	`uid`	LONG	NOT NULL	COMMENT 'Auto Increment',
	`kind`	VARCHAR(40)	NOT NULL	DEFAULT receive	COMMENT '받은 질문/보낸 질문 구분'
);

CREATE TABLE `post` (
	`pid`	LONG	NOT NULL	COMMENT 'Auto Increment',
	`bid`	LONG	NOT NULL	COMMENT 'Auto Increment',
	`title`	VARCHAR(100)	NOT NULL,
	`content`	TEXT	NOT NULL	COMMENT '내용 없으면 생성 안됨',
	`created_at`	TIMESTAMP	NOT NULL,
	`updated_at`	TIMESTAMP	NOT NULL	COMMENT '처음 생성시간 = 수정시간',
	`upper_post`	LONG	NULL	COMMENT 'depth 구현용'
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`uid`
);

ALTER TABLE `board` ADD CONSTRAINT `PK_BOARD` PRIMARY KEY (
	`bid`
);

ALTER TABLE `post` ADD CONSTRAINT `PK_POST` PRIMARY KEY (
	`pid`
);

