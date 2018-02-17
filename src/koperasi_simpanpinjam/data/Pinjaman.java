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
@Table(name = "pinjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pinjaman.findAll", query = "SELECT p FROM Pinjaman p"),
    @NamedQuery(name = "Pinjaman.findByIdPinjam", query = "SELECT p FROM Pinjaman p WHERE p.idPinjam = :idPinjam"),
    @NamedQuery(name = "Pinjaman.findByNoPinjam", query = "SELECT p FROM Pinjaman p WHERE p.noPinjam = :noPinjam"),
    @NamedQuery(name = "Pinjaman.findByTglPinjam", query = "SELECT p FROM Pinjaman p WHERE p.tglPinjam = :tglPinjam"),
    @NamedQuery(name = "Pinjaman.findByJumlahPinjaman", query = "SELECT p FROM Pinjaman p WHERE p.jumlahPinjaman = :jumlahPinjaman"),
    @NamedQuery(name = "Pinjaman.findByLamaAngsuran", query = "SELECT p FROM Pinjaman p WHERE p.lamaAngsuran = :lamaAngsuran"),
    @NamedQuery(name = "Pinjaman.findByBunga", query = "SELECT p FROM Pinjaman p WHERE p.bunga = :bunga"),
    @NamedQuery(name = "Pinjaman.findByTotalAngsuran", query = "SELECT p FROM Pinjaman p WHERE p.totalAngsuran = :totalAngsuran"),
    @NamedQuery(name = "Pinjaman.findByBiayaAngsuran", query = "SELECT p FROM Pinjaman p WHERE p.biayaAngsuran = :biayaAngsuran"),
    @NamedQuery(name = "Pinjaman.findByKodeUser", query = "SELECT p FROM Pinjaman p WHERE p.kodeUser = :kodeUser")})
public class Pinjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pinjam")
    private Integer idPinjam;
    @Basic(optional = false)
    @Column(name = "no_pinjam")
    private String noPinjam;
    @Basic(optional = false)
    @Column(name = "tgl_pinjam")
    @Temporal(TemporalType.DATE)
    private Date tglPinjam;
    @Basic(optional = false)
    @Column(name = "jumlah_pinjaman")
    private double jumlahPinjaman;
    @Basic(optional = false)
    @Column(name = "lama_angsuran")
    private int lamaAngsuran;
    @Basic(optional = false)
    @Column(name = "bunga")
    private double bunga;
    @Basic(optional = false)
    @Column(name = "total_angsuran")
    private double totalAngsuran;
    @Basic(optional = false)
    @Column(name = "biaya_angsuran")
    private double biayaAngsuran;
    @Basic(optional = false)
    @Column(name = "kode_user")
    private String kodeUser;

    public Pinjaman() {
    }

    public Pinjaman(Integer idPinjam) {
        this.idPinjam = idPinjam;
    }

    public Pinjaman(Integer idPinjam, String noPinjam, Date tglPinjam, double jumlahPinjaman, int lamaAngsuran, double bunga, double totalAngsuran, double biayaAngsuran, String kodeUser) {
        this.idPinjam = idPinjam;
        this.noPinjam = noPinjam;
        this.tglPinjam = tglPinjam;
        this.jumlahPinjaman = jumlahPinjaman;
        this.lamaAngsuran = lamaAngsuran;
        this.bunga = bunga;
        this.totalAngsuran = totalAngsuran;
        this.biayaAngsuran = biayaAngsuran;
        this.kodeUser = kodeUser;
    }

    public Integer getIdPinjam() {
        return idPinjam;
    }

    public void setIdPinjam(Integer idPinjam) {
        this.idPinjam = idPinjam;
    }

    public String getNoPinjam() {
        return noPinjam;
    }

    public void setNoPinjam(String noPinjam) {
        this.noPinjam = noPinjam;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public double getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(double jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public int getLamaAngsuran() {
        return lamaAngsuran;
    }

    public void setLamaAngsuran(int lamaAngsuran) {
        this.lamaAngsuran = lamaAngsuran;
    }

    public double getBunga() {
        return bunga;
    }

    public void setBunga(double bunga) {
        this.bunga = bunga;
    }

    public double getTotalAngsuran() {
        return totalAngsuran;
    }

    public void setTotalAngsuran(double totalAngsuran) {
        this.totalAngsuran = totalAngsuran;
    }

    public double getBiayaAngsuran() {
        return biayaAngsuran;
    }

    public void setBiayaAngsuran(double biayaAngsuran) {
        this.biayaAngsuran = biayaAngsuran;
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
        hash += (idPinjam != null ? idPinjam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pinjaman)) {
            return false;
        }
        Pinjaman other = (Pinjaman) object;
        if ((this.idPinjam == null && other.idPinjam != null) || (this.idPinjam != null && !this.idPinjam.equals(other.idPinjam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "koperasi_simpanpinjam.data.Pinjaman[ idPinjam=" + idPinjam + " ]";
    }
    
}
