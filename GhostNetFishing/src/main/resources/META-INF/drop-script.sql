SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

alter table `ghostnet` drop foreign key if exists FKav0hk5fpcalun990h2swb7b6h;

alter table `ghostnet` drop foreign key if exists FKjgsweskjy4kt6mj6r9ag48mje;

alter table `ghostnet` drop foreign key if exists FK91qfu4yswb8tpj8eeda2a3tdl;

alter table `ghostnet` drop foreign key if exists FKgdyu52vn1o179aos466joc5dx;

alter table `user` drop foreign key if exists FKe802x9r45hjb14p4so15juv6y;

drop table if exists `contact`;

drop table if exists `ghostnet`;

drop table if exists `user`;
COMMIT;
