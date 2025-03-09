create database dbkrs;
use dbkrs;

create table prodi (
	id_prodi INT primary key,
	nm_prodi VARCHAR(100) not null
);

create table mhs (
	npm varchar(11) primary key,
	nm_mhs varchar(100) not null,
	id_prodi INT,
	statusRegis ENUM('A', 'N') not null,
	constraint fk_mhs_prodi foreign key (id_prodi) references prodi(id_prodi)
);

create table dosen (
    id_dosen INT primary key auto_increment,
    nm_dosen VARCHAR(100) not null,
    id_prodi INT,
    constraint fk_dosen_prodi foreign key (id_prodi) references prodi(id_prodi)         
);

create table smt (
    id_smt INT primary key auto_increment,
    thnAjaran VARCHAR(10) not null,  -- Contoh format: "2024/2025"
    periode ENUM('Ganjil', 'Genap') not null,
    tglMulai DATE not null,
    tglSelesai DATE not null,
    statusAktif ENUM('Aktif', 'Non-Aktif') not null
);

create table mk (
	id_mk VARCHAR(10) primary key,
	nmMK VARCHAR(100) not null,
	SKS INT not null,
	id_prodi INT,
	constraint fk_mk_prodi foreign key (id_prodi) references prodi(id_prodi)
);

create table jadwal (
    id_jadwal INT primary key auto_increment,
    id_smt INT,
    id_mk VARCHAR(10),
    id_dosen INT,
    id_prodi INT,
    hari VARCHAR(10) not null,
    jamMulai TIME not null,
    jamSelesai TIME not null,
    ruangan VARCHAR(100) not null,
    kapasitas INT not null check (kapasitas > 0),
    constraint fk_jadwal_smt foreign key (id_smt) references smt(id_smt),        
    constraint fk_jadwal_mk foreign key (id_mk) references mk(id_mk), 
    constraint fk_jadwal_dosen foreign key (id_dosen) references dosen(id_dosen), 
    constraint fk_jadwal_prodi foreign key (id_prodi) references prodi(id_prodi) 
);

CREATE TABLE krs (
    id_KRS INT primary key auto_increment,
    npm VARCHAR(11),
    id_jadwal INT,
    id_smt INT,
    statusDisetujui ENUM('Disetujui', 'Ditolak') not null,
    totalSKS INT not null check (totalSKS between 1 and 24),
    constraint fk_krs_mhs foreign key (npm) references mhs(npm),
    constraint fk_krs_jadwal foreign key (id_jadwal) references jadwal(id_jadwal) ,
    constraint fk_krs_smt FOREIGN KEY (id_smt) references smt(id_smt) 
);

create table nilai (
    id_nilai INT primary key auto_increment,
    npm VARCHAR(11),
    id_mk VARCHAR(10),
    id_smt INT,
    NA DECIMAL(5,2) check (NA between 0 and 100),
    NH ENUM('A', 'B', 'C', 'D', 'E') not null,
    IPS DECIMAL(3,2) check (IPS between 0 and 4),
    constraint fk_nilai_mhs foreign key (npm) references mhs(npm),
    constraint fk_nilai_mk foreign key (id_mk) references mk(id_mk),
    constraint fk_nilai_smt foreign key (id_smt) references smt(id_smt)
);

-- DATA CONTOH
-- data prodi
insert into prodi (id_prodi, nm_prodi) values
(1, 'Informatika'), 
(2, 'Sistem Informasi'),
(3, 'Sains Data'),
(4, 'Bisnis Digital');

-- data mhs
insert into mhs (npm, nm_mhs, id_prodi, statusRegis) values
('24081010001', 'Aulia Rahman', 1, 'A'),
('24082010001', 'Budi Santoso', 2, 'A'),
('24083010001', 'Citra Dewi', 3, 'N'),
('24084010001', 'Dian Pertiwi', 4, 'A'),
('24081010002', 'Eko Saputra', 1, 'A'),
('24082010002', 'Farah Indah', 2, 'N'),
('24083010002', 'Gilang Prasetyo', 3, 'A'),
('24084010002', 'Hani Wulandari', 4, 'A'),
('24081010003', 'Rico Setiawan', 1, 'A'),
('24082010003', 'Adela Nayli', 2, 'A'),
('24083010003', 'Reva Aurellia', 3, 'N'),
('24084010003', 'Amelia Uswatun', 4, 'A');

-- data dosen
insert into dosen (id_dosen, nm_dosen, id_prodi) values 
(1, 'Dr. Arif Budiman', 1),
(2, 'Prof Ali Hidayat', 2),
(3, 'Dr. Bambang Sudirman', 3),
(4, 'Dr. Dewi Kusuma', 4);

-- data smt
insert into smt (id_smt, thnAjaran, periode, tglMulai, tglSelesai, statusAktif) values 
(1, '2023/2024', 'Ganjil', '2023-08-01', '2023-12-31', 'Non-Aktif'),
(2, '2023/2024', 'Genap', '2024-02-01', '2024-06-30', 'Non-Aktif'),
(3, '2024/2025', 'Ganjil', '2024-08-01', '2024-12-31', 'Aktif'),
(4, '2024/2025', 'Genap', '2025-02-01', '2025-06-30', 'Aktif');

-- data mk
insert into mk (id_mk, nmMK, SKS, id_prodi) values
('IF101', 'Algoritma dan Pemrograman', 4, 1),
('SI102', 'Basis Data', 3, 2),
('SD103', 'Machine Learning', 3, 3),
('BD104', 'Digital Marketing', 2, 4),
('IF105', 'Struktur Data', 3, 1),
('SI106', 'Analisis Sistem', 3, 2),
('SD107', 'Big Data', 3, 3),
('BD108', 'E-Commerce', 2, 4);

-- data jadwal
insert into jadwal (id_jadwal, id_smt, id_mk, id_dosen, id_prodi, hari, jamMulai, jamSelesai, ruangan, kapasitas) values
(1, 1, 'IF101', 1, 1, 'Senin', '08:00:00', '10:00:00', 'Lab Informatika 1', 30),
(2, 1, 'SI102', 2, 2, 'Selasa', '10:00:00', '12:00:00', 'Ruang 202', 35),
(3, 1, 'SD103', 3, 3, 'Rabu', '13:00:00', '15:00:00', 'Lab Sains Data', 25),
(4, 1, 'BD104', 4, 4, 'Kamis', '09:00:00', '11:00:00', 'Ruang 305', 20),
(5, 2, 'IF105', 1, 1, 'Jumat', '14:00:00', '16:00:00', 'Lab Informatika 2', 30),
(6, 2, 'SI106', 2, 2, 'Senin', '08:00:00', '10:00:00', 'Ruang 204', 35),
(7, 2, 'SD107', 3, 3, 'Selasa', '13:00:00', '15:00:00', 'Lab Data Science', 25),
(8, 2, 'BD108', 4, 4, 'Rabu', '11:00:00', '13:00:00', 'Ruang 306', 20);

-- data krs
insert into krs (id_KRS, npm, id_jadwal, id_smt, statusDisetujui, totalSKS) values
(1, '24081010001', 1, 1, 'Disetujui', 4),
(2, '24081010002', 1, 1, 'Disetujui', 4),
(3, '24081010003', 1, 1, 'Disetujui', 4),
(4, '24081010004', 1, 1, 'Disetujui', 4),
(5, '24082010001', 2, 1, 'Disetujui', 3),
(6, '24082010002', 2, 1, 'Disetujui', 3),
(7, '24082010003', 2, 1, 'Disetujui', 3),
(8, '24082010004', 2, 1, 'Disetujui', 3),
(9, '24083010001', 3, 1, 'Disetujui', 3),
(10, '24083010002', 3, 1, 'Disetujui', 3),
(11, '24083010003', 3, 1, 'Disetujui', 3),
(12, '24083010004', 3, 1, 'Disetujui', 3),
(13, '24084010001', 4, 1, 'Disetujui', 2),
(14, '24084010002', 4, 1, 'Disetujui', 2),
(15, '24084010003', 4, 1, 'Disetujui', 2),
(16, '24084010004', 4, 1, 'Disetujui', 2),
(17, '24081010001', 5, 2, 'Disetujui', 3),
(18, '24081010002', 5, 2, 'Disetujui', 3),
(19, '24081010003', 5, 2, 'Disetujui', 3),
(20, '24081010004', 5, 2, 'Disetujui', 3),
(21, '24082010001', 6, 2, 'Disetujui', 3),
(22, '24082010002', 6, 2, 'Disetujui', 3),
(23, '24082010003', 6, 2, 'Disetujui', 3),
(24, '24082010004', 6, 2, 'Disetujui', 3),
(25, '24083010001', 7, 2, 'Disetujui', 3),
(26, '24083010002', 7, 2, 'Disetujui', 3),
(27, '24083010003', 7, 2, 'Disetujui', 3),
(28, '24083010004', 7, 2, 'Disetujui', 3),
(29, '24084010001', 8, 2, 'Disetujui', 3),
(30, '24084010002', 8, 2, 'Disetujui', 3),
(31, '24084010003', 8, 2, 'Disetujui', 3),
(32, '24084010004', 8, 2, 'Disetujui', 3);

-- data nilai
insert into nilai (id_nilai, npm, id_mk, id_smt, NA, NH, IPS) values 
(1, '24081010001', 'IF101', 1, 85.50, 'A', 3.80),
(2, '24081010002', 'IF105', 2, 78.00, 'B', 3.20),
(3, '24082010001', 'SI102', 1, 92.00, 'A', 4.00),
(4, '24082010002', 'SI106', 2, 81.50, 'B', 3.50),
(5, '24083010001', 'SD103', 1, 69.00, 'C', 2.50),
(6, '24083010002', 'SD107', 2, 88.00, 'A', 3.75),
(7, '24084010001', 'BD104', 1, 76.50, 'B', 3.10),
(8, '24084010002', 'BD108', 2, 80.00, 'B', 3.30),
(9, '24081010003', 'IF101', 1, 90.00, 'A', 3.90),
(10, '24082010003', 'SI102', 1, 87.50, 'A', 3.70),
(11, '24081010004', 'IF101', 1, 88.00, 'A', 3.75),
(12, '24081010005', 'IF101', 1, 72.50, 'C', 2.80),
(13, '24081010006', 'IF105', 2, 85.00, 'A', 3.75),
(14, '24082010004', 'SI102', 1, 79.00, 'B', 3.20),
(15, '24082010005', 'SI106', 2, 91.00, 'A', 3.90),
(16, '24083010003', 'SD103', 1, 67.50, 'C', 2.40),
(17, '24083010004', 'SD107', 2, 83.00, 'B', 3.50),
(18, '24084010003', 'BD104', 1, 88.50, 'A', 3.80),
(19, '24084010004', 'BD108', 2, 77.50, 'B', 3.15),
(20, '24081010007', 'IF101', 1, 95.00, 'A', 4.00);

-- Menampilkan daftar mahasiswa yang terdaftar di mata kuliah tertentu.
select m.npm, m.nm_mhs, mk.id_mk, mk.nmMK
from krs k
join mhs m on k.npm = m.npm
join jadwal j on k.id_jadwal = j.id_jadwal
join mk on j.id_mk = mk.id_mk
where mk.nmMK = 'Basis Data'; 

-- Menampilkan daftar mata kuliah yang diajarkan oleh seorang dosen tertentu.
select d.id_dosen, d.nm_dosen, mk.id_mk, mk.nmMK, mk.SKS, j.hari, j.jamMulai, j.jamSelesai, j.ruangan
from jadwal j
join mk on j.id_mk = mk.id_mk
join dosen d on j.id_dosen = d.id_dosen
where d.nm_dosen = 'Dr. Arif Budiman'; 

-- Menampilkan nilai mahasiswa berdasarkan mata kuliah.
select mk.id_mk, mk.nmMK as nama_mata_kuliah, m.npm, m.nm_mhs as nama_mahasiswa, n.NA as nilai_angka, n.NH as nilai_huruf, n.IPS
from nilai n
join mhs m on n.npm = m.npm
join mk on n.id_mk = mk.id_mk
where mk.nmMK = 'Algoritma dan Pemrograman'; 

-- Menampilkan semua mata kuliah beserta SKS dan jadwalnya.
select mk.id_mk, mk_nmMK, mk.SKS, j.hari, j.jamMulai, j.jamSelesai, d.nm_dosen
from matkul mk
join jadwal j on mk.id_mk = j.id_mk
join dosen d on j.id_dosen = d.id_dosen;
