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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByKodeUser", query = "SELECT u FROM User u WHERE u.kodeUser = :kodeUser"),
    @NamedQuery(name = "User.findByNamaUser", query = "SELECT u FROM User u WHERE u.namaUser = :namaUser"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByHakAkses", query = "SELECT u FROM User u WHERE u.hakAkses = :hakAkses")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_user")
    private String kodeUser;
    @Basic(optional = false)
    @Column(name = "nama_user")
    private String namaUser;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "hak_akses")
    private String hakAkses;

    public User() {
    }

    public User(String kodeUser) {
        this.kodeUser = kodeUser;
    }

    public User(String kodeUser, String namaUser, String password, String hakAkses) {
        this.kodeUser = kodeUser;
        this.namaUser = namaUser;
        this.password = password;
        this.hakAkses = hakAkses;
    }

    public String getKodeUser() {
        return kodeUser;
    }

    public void setKodeUser(String kodeUser) {
        this.kodeUser = kodeUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeUser != null ? kodeUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.kodeUser == null && other.kodeUser != null) || (this.kodeUser != null && !this.kodeUser.equals(other.kodeUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "koperasi_simpanpinjam.data.User[ kodeUser=" + kodeUser + " ]";
    }
    
}
