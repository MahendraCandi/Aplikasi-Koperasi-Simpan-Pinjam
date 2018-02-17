/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "simpanan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Simpanan.findAll", query = "SELECT s FROM Simpanan s"),
    @NamedQuery(name = "Simpanan.findByIdSimpanan", query = "SELECT s FROM Simpanan s WHERE s.idSimpanan = :idSimpanan"),
    @NamedQuery(name = "Simpanan.findByNoSimpan", query = "SELECT s FROM Simpanan s WHERE s.noSimpan = :noSimpan"),
    @NamedQuery(name = "Simpanan.findByTransaksiKe", query = "SELECT s FROM Simpanan s WHERE s.transaksiKe = :transaksiKe"),
    @NamedQuery(name = "Simpanan.findByTanggalSimpan", query = "SELECT s FROM Simpanan s WHERE s.tanggalSimpan = :tanggalSimpan"),
    @NamedQuery(name = "Simpanan.findByMasukSimpanan", query = "SELECT s FROM Simpanan s WHERE s.masukSimpanan = :masukSimpanan"),
    @NamedQuery(name = "Simpanan.findByBunga", query = "SELECT s FROM Simpanan s WHERE s.bunga = :bunga"),
    @NamedQuery(name = "Simpanan.findByJumlahSimpanan", query = "SELECT s FROM Simpanan s WHERE s.jumlahSimpanan = :jumlahSimpanan"),
    @NamedQuery(name = "Simpanan.findByPenarikan", query = "SELECT s FROM Simpanan s WHERE s.penarikan = :penarikan"),
    @NamedQuery(name = "Simpanan.findByTotalSimpanan", query = "SELECT s FROM Simpanan s WHERE s.totalSimpanan = :totalSimpanan"),
    @NamedQuery(name = "Simpanan.findByKodeUser", query = "SELECT s FROM Simpanan s WHERE s.kodeUser = :kodeUser")})
public class Simpanan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_simpanan")
    private Integer idSimpanan;
    @Basic(optional = false)
    @Column(name = "no_simpan")
    private String noSimpan;
    @Basic(optional = false)
    @Column(name = "transaksi_ke")
    private int transaksiKe;
    @Basic(optional = false)
    @Column(name = "tanggal_simpan")
    @Temporal(TemporalType.DATE)
    private Date tanggalSimpan;
    @Basic(optional = false)
    @Column(name = "masuk_simpanan")
    private double masukSimpanan;
    @Basic(optional = false)
    @Column(name = "bunga")
    private double bunga;
    @Basic(optional = false)
    @Column(name = "jumlah_simpanan")
    private double jumlahSimpanan;
    @Basic(optional = false)
    @Column(name = "penarikan")
    private double penarikan;
    @Basic(optional = false)
    @Column(name = "total_simpanan")
    private double totalSimpanan;
    @Basic(optional = false)
    @Column(name = "kode_user")
    private String kodeUser;

    public Simpanan() {
    }

    public Simpanan(Integer idSimpanan) {
        this.idSimpanan = idSimpanan;
    }

    public Simpanan(Integer idSimpanan, String noSimpan, int transaksiKe, Date tanggalSimpan, double masukSimpanan, double bunga, double jumlahSimpanan, double penarikan, double totalSimpanan, String kodeUser) {
        this.idSimpanan = idSimpanan;
        this.noSimpan = noSimpan;
        this.transaksiKe = transaksiKe;
        this.tanggalSimpan = tanggalSimpan;
        this.masukSimpanan = masukSimpanan;
        this.bunga = bunga;
        this.jumlahSimpanan = jumlahSimpanan;
        this.penarikan = penarikan;
        this.totalSimpanan = totalSimpanan;
        this.kodeUser = kodeUser;
    }

    public Integer getIdSimpanan() {
        return idSimpanan;
    }

    public void setIdSimpanan(Integer idSimpanan) {
        this.idSimpanan = idSimpanan;
    }

    public String getNoSimpan() {
        return noSimpan;
    }

    public void setNoSimpan(String noSimpan) {
        this.noSimpan = noSimpan;
    }

    public int getTransaksiKe() {
        return transaksiKe;
    }

    public void setTransaksiKe(int transaksiKe) {
        this.transaksiKe = transaksiKe;
    }

    public Date getTanggalSimpan() {
        return tanggalSimpan;
    }

    public void setTanggalSimpan(Date tanggalSimpan) {
        this.tanggalSimpan = tanggalSimpan;
    }

    public double getMasukSimpanan() {
        return masukSimpanan;
    }

    public void setMasukSimpanan(double masukSimpanan) {
        this.masukSimpanan = masukSimpanan;
    }

    public double getBunga() {
        return bunga;
    }

    public void setBunga(double bunga) {
        this.bunga = bunga;
    }

    public double getJumlahSimpanan() {
        return jumlahSimpanan;
    }

    public void setJumlahSimpanan(double jumlahSimpanan) {
        this.jumlahSimpanan = jumlahSimpanan;
    }

    public double getPenarikan() {
        return penarikan;
    }

    public void setPenarikan(double penarikan) {
        this.penarikan = penarikan;
    }

    public double getTotalSimpanan() {
        return totalSimpanan;
    }

    public void setTotalSimpanan(double totalSimpanan) {
        this.totalSimpanan = totalSimpanan;
    }

    public String getKodeUser() {
        return kodeUser;
    }

    public void setKodeUser(String kodeUser) {
        this.kodeUser = kodeUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSimpanan != null ? idSimpanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Simpanan)) {
            return false;
        }
        Simpanan other = (Simpanan) object;
        if ((this.idSimpanan == null && other.idSimpanan != null) || (this.idSimpanan != null && !this.idSimpanan.equals(other.idSimpanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "koperasi_simpanpinjam.data.Simpanan[ idSimpanan=" + idSimpanan + " ]";
    }
    
}
