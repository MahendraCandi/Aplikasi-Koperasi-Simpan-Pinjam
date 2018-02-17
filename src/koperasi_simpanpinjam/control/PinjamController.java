/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.control;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import koperasi_simpanpinjam.data.Pinjaman;

/**
 *
 * @author Candi-PC
 */
public class PinjamController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public PinjamController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Pinjaman pinjam) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(pinjam);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(int pk) throws Exception{
        EntityManager em = getEntityManager();
        Pinjaman us;
        try{
            us=em.getReference(Pinjaman.class, pk);
            us.getIdPinjam();
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public int pk(String no){
        EntityManager em=null;
        int pk=0;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT s.idPinjam FROM Pinjaman s WHERE s.noPinjam = :nomor ");
            q.setParameter("nomor", no);
            if(q!=null){
                int ke= (int) q.getSingleResult();
                pk=pk+ke;
            }
        }catch(NoResultException ex){}
        return pk;
    }
    
    public Pinjaman findPinjaman(int PK){
        EntityManager em=getEntityManager();
        try{
            return em.find(Pinjaman.class, PK);
        }finally{}
    }
    
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT `no_pinjam`, akun_pinjaman.no_anggota, `tgl_pinjam`,`jumlah_pinjaman`, `lama_angsuran`,  bunga, `total_angsuran`,`biaya_angsuran` \n" +
                                        "FROM pinjaman \n" +
                                        "INNER JOIN akun_pinjaman ON pinjaman.no_pinjam=akun_pinjaman.no_pinjaman\n" +
                                        "");
            while(rs.next()){
                Object[] obj = new Object[8];
                obj[0]=rs.getString("no_pinjam");
                obj[1]=rs.getString("no_anggota");
                obj[2]=rs.getDate("tgl_pinjam");
                obj[3]=rs.getString("jumlah_pinjaman");
                obj[4]=rs.getString("lama_angsuran");
                obj[5]=rs.getString("bunga");
                obj[6]=rs.getString("total_angsuran");
                obj[7]=rs.getString("biaya_angsuran");
                model.addRow(obj);
            }
            
        }catch(Exception ex){}
        return model;
    }
    
    //cari Akun
    public DefaultTableModel cari(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT `no_pinjam`, akun_pinjaman.no_anggota, `tgl_pinjam`,`jumlah_pinjaman`, `lama_angsuran`,  bunga, `total_angsuran`,`biaya_angsuran` \n" +
                                        "FROM pinjaman \n" +
                                        "INNER JOIN akun_pinjaman ON pinjaman.no_pinjam=akun_pinjaman.no_pinjaman\n" +
                                        "WHERE no_pinjam LIKE '%"+cari+"%'\n" +
                                        "OR akun_pinjaman.no_anggota LIKE '%"+cari+"%'\n" +
                                        "OR tgl_pinjam LIKE '%"+cari+"%'");
            while(rs.next()){
                Object[] obj = new Object[8];
                obj[0]=rs.getString("no_pinjam");
                obj[1]=rs.getString("no_anggota");
                obj[2]=rs.getString("tgl_pinjam");
                obj[3]=rs.getString("jumlah_pinjaman");
                obj[4]=rs.getString("lama_angsuran");
                obj[5]=rs.getString("bunga");
                obj[6]=rs.getString("total_angsuran");
                obj[7]=rs.getString("biaya_angsuran");
                model.addRow(obj);
            }
        }catch(Exception ex){}
        return model;
    }
}
