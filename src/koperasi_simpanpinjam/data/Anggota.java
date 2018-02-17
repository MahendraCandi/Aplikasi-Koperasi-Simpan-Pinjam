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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "anggota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anggota.findAll", query = "SELECT a FROM Anggota a"),
    @NamedQuery(name = "Anggota.findByNoAnggota", query = "SELECT a FROM Anggota a WHERE a.noAnggota = :noAnggota"),
    @NamedQuery(name = "Anggota.findByNoKtp", query = "SELECT a FROM Anggota a WHERE a.noKtp = :noKtp"),
    @NamedQuery(name = "Anggota.findByPekerjaan", query = "SELECT a FROM Anggota a WHERE a.pekerjaan = :pekerjaan"),
    @NamedQuery(name = "Anggota.findByNoTelp", query = "SELECT a FROM Anggota a WHERE a.noTelp = :noTelp"),
    @NamedQuery(name = "Anggota.findByTempatLahir", query = "SELECT a FROM Anggota a WHERE a.tempatLahir = :tempatLahir"),
    @NamedQuery(name = "Anggota.findByTglLahir", query = "SELECT a FROM Anggota a WHERE a.tglLahir = :tglLahir"),
    @NamedQuery(name = "Anggota.findByFcKartukeluarga", query = "SELECT a FROM Anggota a WHERE a.fcKartukeluarga = :fcKartukeluarga"),
    @NamedQuery(name = "Anggota.findByFotoNasabah", query = "SELECT a FROM Anggota a WHERE a.fotoNasabah = :fotoNasabah"),
    @NamedQuery(name = "Anggota.findByBpkb", query = "SELECT a FROM Anggota a WHERE a.bpkb = :bpkb")})
public class Anggota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_anggota")
    private String noAnggota;
    @Basic(optional = false)
    @Lob
    @Column(name = "nama")
    private String nama;
    @Basic(optional = false)
    @Column(name = "no_ktp")
    private int noKtp;
    @Basic(optional = false)
    @Column(name = "pekerjaan")
    private String pekerjaan;
    @Basic(optional = false)
    @Column(name = "no_telp")
    private int noTelp;
    @Basic(optional = false)
    @Column(name = "tempat_lahir")
    private String tempatLahir;
    @Basic(optional = false)
    @Column(name = "tgl_lahir")
    @Temporal(TemporalType.DATE)
    private Date tglLahir;
    @Basic(optional = false)
    @Lob
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "fc_kartukeluarga")
    private String fcKartukeluarga;
    @Basic(optional = false)
    @Column(name = "foto_nasabah")
    private String fotoNasabah;
    @Basic(optional = false)
    @Column(name = "bpkb")
    private String bpkb;

    public Anggota() {
    }

    public Anggota(String noAnggota) {
        this.noAnggota = noAnggota;
    }

    public Anggota(String noAnggota, String nama, int noKtp, String pekerjaan, int noTelp, String tempatLahir, Date tglLahir, String alamat, String fcKartukeluarga, String fotoNasabah, String bpkb) {
        this.noAnggota = noAnggota;
        this.nama = nama;
        this.noKtp = noKtp;
        this.pekerjaan = pekerjaan;
        this.noTelp = noTelp;
        this.tempatLahir = tempatLahir;
        this.tglLahir = tglLahir;
        this.alamat = alamat;
        this.fcKartukeluarga = fcKartukeluarga;
        this.fotoNasabah = fotoNasabah;
        this.bpkb = bpkb;
    }

    public String getNoAnggota() {
        return noAnggota;
    }

    public void setNoAnggota(String noAnggota) {
        this.noAnggota = noAnggota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(int noKtp) {
        this.noKtp = noKtp;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public int getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(int noTelp) {
        this.noTelp = noTelp;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(Date tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFcKartukeluarga() {
        return fcKartukeluarga;
    }

    public void setFcKartukeluarga(String fcKartukeluarga) {
        this.fcKartukeluarga = fcKartukeluarga;
    }

    public String getFotoNasabah() {
        return fotoNasabah;
    }

    public void setFotoNasabah(String fotoNasabah) {
        this.fotoNasabah = fotoNasabah;
    }

    public String getBpkb() {
        return bpkb;
    }

    public void setBpkb(String bpkb) {
        this.bpkb = bpkb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noAnggota != null ? noAnggota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anggota)) {
            return false;
        }
        Anggota other = (Anggota) object;
        if ((this.noAnggota == null && other.noAnggota != null) || (this.noAnggota != null && !this.noAnggota.equals(other.noAnggota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "koperasi_simpanpinjam.data.Anggota[ noAnggota=" + noAnggota + " ]";
    }
    
}
