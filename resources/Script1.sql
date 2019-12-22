--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `database`.`nhanvien` DROP PRIMARY KEY;

ALTER TABLE `database`.`task` DROP INDEX `database`.`nguoi_giao`;

ALTER TABLE `database`.`thongtin` DROP INDEX `database`.`ma_nhanvien`;

ALTER TABLE `database`.`task` DROP INDEX `database`.`nguoi_nhan`;

DROP TABLE `database`.`task`;

DROP TABLE `database`.`thongtin`;

DROP TABLE `database`.`nhanvien`;

CREATE TABLE `database`.`task` (
	`nguoi_nhan` INT NOT NULL,
	`nguoi_giao` INT NOT NULL,
	`ten_cv` INT NOT NULL,
	`noi_dung` VARCHAR(500) NOT NULL,
	`bat_dau` DATE NOT NULL,
	`ket_thuc` DATE NOT NULL,
	`trang_thai` INT NOT NULL,
	`ghi_chu` VARCHAR(255),
	`danh_gia` VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE `database`.`thongtin` (
	`ma_nhanvien` INT NOT NULL,
	`ten` VARCHAR(70) NOT NULL,
	`ngay_sinh` DATE,
	`email` VARCHAR(50),
	`phong_ban` VARCHAR(40) NOT NULL,
	`status` INT NOT NULL,
	`ghi_chu` VARCHAR(255)
) ENGINE=InnoDB;

CREATE TABLE `database`.`nhanvien` (
	`ma_nhanvien` INT NOT NULL,
	`user` VARCHAR(50) NOT NULL,
	`pass` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ma_nhanvien`)
) ENGINE=InnoDB;

CREATE INDEX `nguoi_giao` ON `database`.`task` (`nguoi_giao` ASC);

CREATE INDEX `ma_nhanvien` ON `database`.`thongtin` (`ma_nhanvien` ASC);

CREATE INDEX `nguoi_nhan` ON `database`.`task` (`nguoi_nhan` ASC);

