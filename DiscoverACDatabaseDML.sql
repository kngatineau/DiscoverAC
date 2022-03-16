-- Data insertion for DiscoverAC

INSERT INTO bulletin (`bulletinId`,`bulletinName`) VALUES ('930cc92d-8a1f-4ba5-90b6-4373d90d6e22','Algonquin College');

INSERT INTO user (`userId`,`userName`,`firstName`,`lastName`,`email`,`role`,`password`) VALUES ('aa7e74f5-d1be-4d1b-9536-84c92a5d7518','amoo','Aladah ','Moore','moor0667@algonquinlive.com','Admin','amoo');
INSERT INTO user (`userId`,`userName`,`firstName`,`lastName`,`email`,`role`,`password`) VALUES ('ceb5f8eb-a350-42fc-86fb-d7e04ecbb748','kga','Kaitlin','Gatineau','gati0010@algonquinlive.com','Admin','kga');
INSERT INTO user (`userId`,`userName`,`firstName`,`lastName`,`email`,`role`,`password`) VALUES ('eba675ab-2a37-43de-9223-b78422be9723','hjung','Hyosung','Jung','jung0067@algonquinlive.com','Admin','hjung');

INSERT INTO post (`postId`,`title`,`description`,`url`,`postDate`,`bulletinId`,`authorId`) VALUES ('b007a9f2-0389-4944-8827-772727b823eb','AC Early Childhood Education','A Discord server for the Early Childhood Education program at Algonquin College','https://discord.gg/jU2cpHbd','2022-03-01','930cc92d-8a1f-4ba5-90b6-4373d90d6e22','aa7e74f5-d1be-4d1b-9536-84c92a5d7518');
INSERT INTO post (`postId`,`title`,`description`,`url`,`postDate`,`bulletinId`,`authorId`) VALUES ('f36fa58f-5dad-4b78-88ec-a9933f502083','AC Electro-Mechanical Engineering Technician','A Discord server for the Electro-Mechanical Engineering Technician program at Algonquin College','https://discord.gg/aMC7u5Gu','2022-03-02','930cc92d-8a1f-4ba5-90b6-4373d90d6e22','ceb5f8eb-a350-42fc-86fb-d7e04ecbb748');
INSERT INTO post (`postId`,`title`,`description`,`url`,`postDate`,`bulletinId`,`authorId`) VALUES ('f66b3b82-cf46-4909-a57c-d9b617f6ab75','AC Computer Engineering Technology','A Discord server for the Computer Engineering Technology program at Algonquin College','https://discord.gg/x9Huwhsh','2022-03-03','930cc92d-8a1f-4ba5-90b6-4373d90d6e22','eba675ab-2a37-43de-9223-b78422be9723');
