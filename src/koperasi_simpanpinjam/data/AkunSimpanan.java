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
@Table(name = "akun_simpanan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AkunSimpanan.findAll", query = "SELECT a FROM AkunSimpanan a"),
    @NamedQuery(name = "AkunSimpanan.findByNoSimpanan", query = "SELECT a FROM AkunSimpanan a WHERE a.noSimpanan = :noSimpanan"),
    @NamedQuery(name = "AkunSimpanan.findByNoAnggota", query = "SELECT a FROM AkunSimpanan a WHERE a.noAnggota = :noAnggota"),
    @NamedQuery(name = "AkunSimpanan.findByStatus", query = "SELECT a FROM AkunSimpanan a WHERE a.status = :status")})
public class AkunSimpanan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "no_simpanan")
    private String noSimpanan;
    @Basic(optional = false)
    @Column(name = "no_anggota")
    private String noAnggota;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public AkunSimpanan() {
    }

    public AkunSimpanan(String noSimpanan) {
        this.noSimpanan = noSimpanan;
    }

    public AkunSimpanan(String noSimpanan, String noAnggota, String status) {
        this.noSimpanan = noSimpanan;
        this.noAnggota = noAnggota;
        this.status = status;
    }

    public String getNoSimpanan() {
        return noSimpanan;
    }

    public void setNoSimpanan(String noSimpanan) {
        this.noSimpanan = noSimpanan;
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
        hash += (noSimpanan != null ? noSimpanan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AkunSimpanan)) {
            return false;
        }
        AkunSimpanan other = (AkunSimpanan) object;
        if ((this.noSimpanan == null && other.noSimpanan != null) || (this.noSimpanan != null && !this.noSimpanan.equals(other.noSimpanan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "koperasi_simpanpinjam.data.AkunSimpanan[ noSimpanan=" + noSimpanan + " ]";
    }
    
}
