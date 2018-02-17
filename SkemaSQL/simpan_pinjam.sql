-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 17, 2018 at 10:46 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `simpan_pinjam`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun_pinjaman`
--

CREATE TABLE IF NOT EXISTS `akun_pinjaman` (
  `no_pinjaman` varchar(11) NOT NULL,
  `no_anggota` varchar(10) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `akun_simpanan`
--

CREATE TABLE IF NOT EXISTS `akun_simpanan` (
  `no_simpanan` varchar(11) NOT NULL,
  `no_anggota` varchar(10) NOT NULL,
  `status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE IF NOT EXISTS `anggota` (
  `no_anggota` varchar(10) NOT NULL,
  `nama` text NOT NULL,
  `no_ktp` int(16) NOT NULL,
  `pekerjaan` varchar(15) NOT NULL,
  `no_telp` int(12) NOT NULL,
  `tempat_lahir` varchar(15) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` text NOT NULL,
  `fc_kartukeluarga` varchar(10) NOT NULL,
  `foto_nasabah` varchar(10) NOT NULL,
  `bpkb` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `angsuran_pinjaman`
--

CREATE TABLE IF NOT EXISTS `angsuran_pinjaman` (
  `id_angsuran` int(11) NOT NULL,
  `no_pinjaman` varchar(11) NOT NULL,
  `angsuran_ke` int(11) NOT NULL,
  `tanggal_bayar` date NOT NULL,
  `biaya_angsuran` double NOT NULL,
  `sisa_pinjaman` double NOT NULL,
  `kode_user` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pinjaman`
--

CREATE TABLE IF NOT EXISTS `pinjaman` (
  `id_pinjam` int(11) NOT NULL,
  `no_pinjam` varchar(11) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `jumlah_pinjaman` double NOT NULL,
  `lama_angsuran` int(11) NOT NULL,
  `bunga` double NOT NULL,
  `total_angsuran` double NOT NULL,
  `biaya_angsuran` double NOT NULL,
  `kode_user` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `simpanan`
--

CREATE TABLE IF NOT EXISTS `simpanan` (
  `id_simpanan` int(11) NOT NULL,
  `no_simpan` varchar(11) NOT NULL,
  `transaksi_ke` int(11) NOT NULL,
  `tanggal_simpan` date NOT NULL,
  `masuk_simpanan` double NOT NULL,
  `bunga` double NOT NULL,
  `jumlah_simpanan` double NOT NULL,
  `penarikan` double NOT NULL,
  `total_simpanan` double NOT NULL,
  `kode_user` varchar(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `kode_user` varchar(8) NOT NULL,
  `nama_user` varchar(25) NOT NULL,
  `password` varchar(10) NOT NULL,
  `hak_akses` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun_pinjaman`
--
ALTER TABLE `akun_pinjaman`
  ADD PRIMARY KEY (`no_pinjaman`);

--
-- Indexes for table `akun_simpanan`
--
ALTER TABLE `akun_simpanan`
  ADD PRIMARY KEY (`no_simpanan`);

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`no_anggota`);

--
-- Indexes for table `angsuran_pinjaman`
--
ALTER TABLE `angsuran_pinjaman`
  ADD PRIMARY KEY (`id_angsuran`);

--
-- Indexes for table `pinjaman`
--
ALTER TABLE `pinjaman`
  ADD PRIMARY KEY (`id_pinjam`);

--
-- Indexes for table `simpanan`
--
ALTER TABLE `simpanan`
  ADD PRIMARY KEY (`id_simpanan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`kode_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `angsuran_pinjaman`
--
ALTER TABLE `angsuran_pinjaman`
  MODIFY `id_angsuran` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `pinjaman`
--
ALTER TABLE `pinjaman`
  MODIFY `id_pinjam` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `simpanan`
--
ALTER TABLE `simpanan`
  MODIFY `id_simpanan` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
