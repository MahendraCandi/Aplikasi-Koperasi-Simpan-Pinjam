/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "akun_pinjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AkunPinjaman.findAll", query = "SELECT a FROM AkunPinjaman a"),
    @NamedQuery(name = "AkunPinjaman.findByNoPinjaman", query = "SELECT a FROM AkunPinjaman a WHERE a.noPinjaman = :noPinjaman"),
    @NamedQuery(name = "AkunPinjaman.findByNoAnggota", query = "SELECT a FROM AkunPinjaman a WHERE a.noAnggota = :noAnggota"),
    @NamedQuery(name = "AkunPinjaman.findByStatus", query = "SELECT a FROM AkunPinjaman a WHERE a.status = :status")})
public class AkunPinjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_pinjaman")
    private String noPinjaman;
    @Basic(optional = false)
    @Column(name = "no_anggota")
    private String noAnggota;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public AkunPinjaman() {
    }

    public AkunPinjaman(String noPinjaman) {
        this.noPinjaman = noPinjaman;
    }

    public AkunPinjaman(String noPinjaman, String noAnggota, String status) {
        this.noPinjaman = noPinjaman;
        this.noAnggota = noAnggota;
        this.status = status;
    }

    public String getNoPinjaman() {
        return noPinjaman;
    }

    public void setNoPinjaman(String noPinjaman) {
        this.noPinjaman = noPinjaman;
    }

    public String getNoAnggota() {
        return noAnggota;
    }

    public void setNoAnggota(String noAnggota) {
        this.noAnggota = noAnggota;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noPinjaman != null ? noPinjaman.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AkunPinjaman)) {
            return false;
        }
        AkunPinjaman other = (AkunPinjaman) object;
        if ((this.noPinjaman == null && other.noPinjaman != null) || (this.noPinjaman != null && !this.noPinjaman.equals(other.noPinjaman))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "koperasi_simpanpinjam.data.AkunPinjaman[ noPinjaman=" + noPinjaman + " ]";
    }
    
}
