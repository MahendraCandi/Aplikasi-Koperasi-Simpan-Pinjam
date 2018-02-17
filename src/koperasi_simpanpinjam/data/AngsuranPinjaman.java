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
@Table(name = "angsuran_pinjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AngsuranPinjaman.findAll", query = "SELECT a FROM AngsuranPinjaman a"),
    @NamedQuery(name = "AngsuranPinjaman.findByIdAngsuran", query = "SELECT a FROM AngsuranPinjaman a WHERE a.idAngsuran = :idAngsuran"),
    @NamedQuery(name = "AngsuranPinjaman.findByNoPinjaman", query = "SELECT a FROM AngsuranPinjaman a WHERE a.noPinjaman = :noPinjaman"),
    @NamedQuery(name = "AngsuranPinjaman.findByAngsuranKe", query = "SELECT a FROM AngsuranPinjaman a WHERE a.angsuranKe = :angsuranKe"),
    @NamedQuery(name = "AngsuranPinjaman.findByTanggalBayar", query = "SELECT a FROM AngsuranPinjaman a WHERE a.tanggalBayar = :tanggalBayar"),
    @NamedQuery(name = "AngsuranPinjaman.findByBiayaAngsuran", query = "SELECT a FROM AngsuranPinjaman a WHERE a.biayaAngsuran = :biayaAngsuran"),
    @NamedQuery(name = "AngsuranPinjaman.findBySisaPinjaman", query = "SELECT a FROM AngsuranPinjaman a WHERE a.sisaPinjaman = :sisaPinjaman"),
    @NamedQuery(name = "AngsuranPinjaman.findByKodeUser", query = "SELECT a FROM AngsuranPinjaman a WHERE a.kodeUser = :kodeUser")})
public class AngsuranPinjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_angsuran")
    private Integer idAngsuran;
    @Basic(optional = false)
    @Column(name = "no_pinjaman")
    private String noPinjaman;
    @Basic(optional = false)
    @Column(name = "angsuran_ke")
    private int angsuranKe;
    @Basic(optional = false)
    @Column(name = "tanggal_bayar")
    @Temporal(TemporalType.DATE)
    private Date tanggalBayar;
    @Basic(optional = false)
    @Column(name = "biaya_angsuran")
    private double biayaAngsuran;
    @Basic(optional = false)
    @Column(name = "sisa_pinjaman")
    private double sisaPinjaman;
    @Basic(optional = false)
    @Column(name = "kode_user")
    private String kodeUser;

    public AngsuranPinjaman() {
    }

    public AngsuranPinjaman(Integer idAngsuran) {
        this.idAngsuran = idAngsuran;
    }

    public AngsuranPinjaman(Integer idAngsuran, String noPinjaman, int angsuranKe, Date tanggalBayar, double biayaAngsuran, double sisaPinjaman, String kodeUser) {
        this.idAngsuran = idAngsuran;
        this.noPinjaman = noPinjaman;
        this.angsuranKe = angsuranKe;
        this.tanggalBayar = tanggalBayar;
        this.biayaAngsuran = biayaAngsuran;
        this.sisaPinjaman = sisaPinjaman;
        this.kodeUser = kodeUser;
    }

    public Integer getIdAngsuran() {
        return idAngsuran;
    }

    public void setIdAngsuran(Integer idAngsuran) {
        this.idAngsuran = idAngsuran;
    }

    public String getNoPinjaman() {
        return noPinjaman;
    }

    public void setNoPinjaman(String noPinjaman) {
        this.noPinjaman = noPinjaman;
    }

    public int getAngsuranKe() {
        return angsuranKe;
    }

    public void setAngsuranKe(int angsuranKe) {
        this.angsuranKe = angsuranKe;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public double getBiayaAngsuran() {
        return biayaAngsuran;
    }

    public void setBiayaAngsuran(double biayaAngsuran) {
        this.biayaAngsuran = biayaAngsuran;
    }

    public double getSisaPinjaman() {
        return sisaPinjaman;
    }

    public void setSisaPinjaman(double sisaPinjaman) {
        this.sisaPinjaman = sisaPinjaman;
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
        hash += (idAngsuran != null ? idAngsuran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AngsuranPinjaman)) {
            return false;
        }
        AngsuranPinjaman other = (AngsuranPinjaman) object;
        if ((this.idAngsuran == null && other.idAngsuran != null) || (this.idAngsuran != null && !this.idAngsuran.equals(other.idAngsuran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "koperasi_simpanpinjam.data.AngsuranPinjaman[ idAngsuran=" + idAngsuran + " ]";
    }
    
}
