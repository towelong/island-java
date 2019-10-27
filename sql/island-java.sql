/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : island-java

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-10-27 17:10:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `fav_nums` int(11) NOT NULL DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1120', '1', null, null, null);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(12) DEFAULT NULL,
  `nums` int(11) DEFAULT '0',
  `book_id` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('12', '真好看', '5', '1120', null, null, null);
INSERT INTO `comment` VALUES ('13', '真好看', '4', '1000', null, null, null);

-- ----------------------------
-- Table structure for favor
-- ----------------------------
DROP TABLE IF EXISTS `favor`;
CREATE TABLE `favor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `art_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of favor
-- ----------------------------
INSERT INTO `favor` VALUES ('74', '1', '100', '2019-10-26 14:02:06', '2019-10-26 14:02:06', null, '8');
INSERT INTO `favor` VALUES ('93', '1120', '400', '2019-10-27 15:24:38', '2019-10-27 15:24:38', null, '8');

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow` (
  `created_at` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indexs` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `art_id` int(11) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of flow
-- ----------------------------
INSERT INTO `flow` VALUES ('2019-04-05 17:11:14', '1', '1', '1', '200', '3', null, null);
INSERT INTO `flow` VALUES ('2019-04-05 17:11:21', '1', '2', '2', '300', '2', null, null);
INSERT INTO `flow` VALUES ('2019-04-05 17:11:27', '1', '3', '3', '200', '2', null, null);
INSERT INTO `flow` VALUES ('2019-04-02 17:11:50', '1', '4', '4', '100', '2', null, null);
INSERT INTO `flow` VALUES ('2019-04-17 17:11:56', '1', '5', '6', '300', '1', null, null);
INSERT INTO `flow` VALUES ('2019-04-05 17:12:00', '1', '6', '7', '200', '1', null, null);
INSERT INTO `flow` VALUES ('2019-04-05 17:12:04', '1', '7', '8', '100', '1', null, null);
INSERT INTO `flow` VALUES ('2019-04-05 17:12:07', '1', '8', '5', '200', '4', null, null);

-- ----------------------------
-- Table structure for hot_book
-- ----------------------------
DROP TABLE IF EXISTS `hot_book`;
CREATE TABLE `hot_book` (
  `created_at` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indexs` int(11) DEFAULT NULL,
  `image` varchar(64) DEFAULT NULL,
  `author` varchar(25) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51665 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of hot_book
-- ----------------------------
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '7', '1', 'https://img3.doubanio.com/lpic/s4669554.jpg', '[美]保罗·格雷厄姆', '黑客与画家', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '65', '2', 'https://img3.doubanio.com/lpic/s4059293.jpg', 'MarkPilgrim', 'Dive Into Python 3', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '183', '3', 'https://img3.doubanio.com/lpic/s4387251.jpg', 'MagnusLieHetland', 'Python基础教程', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1002', '4', 'https://img3.doubanio.com/lpic/s6384944.jpg', '[哥伦比亚]加西亚·马尔克斯', '百年孤独', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1049', '5', 'https://img1.doubanio.com/view/subject/l/public/s29775868.jpg', '[日]岩井俊二', '情书', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1061', '6', 'https://img3.doubanio.com/lpic/s1358984.jpg', '[美]乔治·R·R·马丁', '冰与火之歌（卷一）', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1120', '7', 'https://img3.doubanio.com/lpic/s4610502.jpg', '[日]东野圭吾', '白夜行', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1166', '8', 'https://img1.doubanio.com/lpic/s23632058.jpg', '金庸', '天龙八部', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1308', '9', 'https://img3.doubanio.com/lpic/s3814606.jpg', '[日]东野圭吾', '恶意', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1339', '10', 'https://img3.doubanio.com/lpic/s1074376.jpg', '[英]J·K·罗琳', '哈利·波特与阿兹卡班的囚徒', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1383', '11', 'https://img1.doubanio.com/lpic/s3557848.jpg', '韩寒', '他的国', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1398', '12', 'https://img1.doubanio.com/lpic/s2752367.jpg', '[英]J·K·罗琳', '哈利·波特与死亡圣器', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '1560', '13', 'https://img1.doubanio.com/lpic/s3463069.jpg', '王小波', '三十而立', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '7821', '14', 'https://img3.doubanio.com/lpic/s6144591.jpg', '[伊朗]玛赞·莎塔碧', '我在伊朗长大', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '8854', '15', 'https://img1.doubanio.com/lpic/s29494718.jpg', '[日]村上春树', '远方的鼓声', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '8866', '16', 'https://img3.doubanio.com/lpic/s2393243.jpg', '三毛', '梦里花落知多少', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '15198', '17', 'https://img1.doubanio.com/lpic/s1080179.jpg', '韩寒', '像少年啦飞驰', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '15984', '18', 'https://img3.doubanio.com/lpic/s27970504.jpg', '鲁迅', '朝花夕拾', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '21050', '19', 'https://img3.doubanio.com/lpic/s2853431.jpg', '[日]井上雄彦', '灌篮高手31', null, null);
INSERT INTO `hot_book` VALUES ('0000-00-00 00:00:00', '1', '51664', '20', 'https://img3.doubanio.com/lpic/s29034294.jpg', '[日]新井一二三', '东京时味记', null, null);

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `created_at` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(64) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `pubdate` date DEFAULT NULL,
  `fav_nums` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('2019-10-25 23:21:21', '1', '1', 'images/movie.8.png', '人生不能像做菜，把所有的料准备好才下锅', '2018-06-22', '171', '李安《饮食男女 》', '100', '2019-10-25 02:40:48', null);
INSERT INTO `movie` VALUES ('2019-10-25 23:21:30', '1', '2', 'images/movie.4.png', '在变换的生命里，岁月原来是最大的小偷', '2018-06-22', '46', '罗启锐《岁月神偷》', '100', '2019-10-25 04:34:38', null);

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `created_at` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(64) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `pubdate` date DEFAULT NULL,
  `fav_nums` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES ('2019-10-25 23:22:04', '1', '1', 'images/music.7.png', '无人问我粥可温 风雨不见江湖人', 'http://music.163.com/song/media/outer/url?id=393926.mp3', '2018-06-22', '95', '200', '月之门《枫华谷的枫林》', null, '2019-10-25 18:25:12');
INSERT INTO `music` VALUES ('2019-10-25 23:22:11', '1', '2', 'images/music.1.png', '你陪我步入蝉夏 越过城市喧嚣', 'http://music.163.com/song/media/outer/url?id=516076896.mp3', '2018-06-22', '47', '200', '花粥 《纸短情长》', null, '2019-10-25 09:13:12');
INSERT INTO `music` VALUES ('2019-10-25 23:22:15', '1', '3', 'images/music.3.png', '岁月长，衣裳薄', 'http://music.163.com/song/media/outer/url?id=317245.mp3', '2018-06-22', '44', '200', '杨千嬅《再见二丁目》', null, '2019-10-25 11:59:49');
INSERT INTO `music` VALUES ('2019-10-25 23:22:20', '1', '4', 'images/music.5.png', '许多人来来去去，相聚又别离', 'http://music.163.com/song/media/outer/url?id=26427662.mp3', '2018-06-22', '68', '200', '好妹妹 《一个人的北京》', null, '2019-10-25 23:22:37');

-- ----------------------------
-- Table structure for sentence
-- ----------------------------
DROP TABLE IF EXISTS `sentence`;
CREATE TABLE `sentence` (
  `created_at` datetime DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(64) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `pubdate` date DEFAULT NULL,
  `fav_nums` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sentence
-- ----------------------------
INSERT INTO `sentence` VALUES ('2019-10-25 23:22:50', '1', '1', 'images/sentence.6.png', '心上无垢，林间有风', '2018-06-22', '72', '未名', '300', '2019-10-25 12:13:37', null);
INSERT INTO `sentence` VALUES ('2019-10-25 23:22:54', '1', '2', 'images/sentence.2.png', '这个夏天又是一个毕业季', '2018-06-22', '33', '未名', '300', '2019-10-25 09:13:28', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `openid` varchar(64) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8', '1191430240@qq.com', 'fuwelong', null, 'pbkdf2sha256:64000:18:24:n:kBIl1FzIxK2BwLSncU36uRlarZIlKUVE:oMMDoaCptFcBP5DSsTzzUaeH', '2019-10-25 14:25:40', null, null);
