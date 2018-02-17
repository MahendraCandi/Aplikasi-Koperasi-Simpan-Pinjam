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
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koperasi_simpanpinjam.data.Simpanan;

/**
 *
 * @author Candi-PC
 */
public class SimpanTarikController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public SimpanTarikController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(Simpanan simpan) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(simpan);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public int pk(String no, int trans){
        EntityManager em=null;
        int pk=0;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT s.idSimpanan FROM Simpanan s WHERE s.noSimpan = :nomor AND s.transaksiKe = :transaksi");
            q.setParameter("nomor", no);
            q.setParameter("transaksi", trans);
            if(q!=null){
                int ke= (int) q.getSingleResult();
                pk=pk+ke;
            }
        }catch(NoResultException ex){}
        
        return pk;
    }
    
    public Simpanan findSimpanan(int PK){
        EntityManager em=getEntityManager();
        try{
            return em.find(Simpanan.class, PK);
        }finally{}
    }
    
    public List<Simpanan> findSIM(String no, int trans){
        List<Simpanan> sim;
        EntityManager em=null;
        try{
            em=getEntityManager();
                                    //SELECT `no_simpan`, `transaksi_ke` FROM `simpanan` WHERE `no_simpan` LIKE '%Sim0917-001%' AND `transaksi_ke` LIKE '%3%'
            Query q = em.createQuery("SELECT s.noSimpan, s.transaksiKe FROM Simpanan s WHERE s.noSimpan LIKE '%:nomor%' and s.transaksiKe LIKE :transaksi order by s.transaksiKe desc");
            q.setParameter("nomor", no);
            q.setParameter("transaksi", trans);
            q.getMaxResults();
            sim = q.getResultList();
            return sim;
        }finally{} 
    }
    
    public int transaksiKe(String no){
        int Trans=1;
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT s.noSimpan, s.transaksiKe FROM Simpanan s order by s.transaksiKe desc");
            q.setMaxResults(1);
            if(q!=null){
                Query tr=em.createQuery("SELECT s.transaksiKe FROM Simpanan s WHERE s.noSimpan LIKE '%"+no+"%' order by s.transaksiKe desc");
                tr.setMaxResults(1);
                int x=Integer.parseInt(tr.getSingleResult().toString());
                Trans=x+1;
            }
        }catch(NoResultException ex){}
        return Trans;
    }
    
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT `no_simpan`, akun_simpanan.no_anggota, transaksi_ke, tanggal_simpan, masuk_simpanan, penarikan, total_simpanan  FROM `simpanan`\n" +
                                         "INNER JOIN akun_simpanan ON simpanan.no_simpan=akun_simpanan.no_simpanan\n" +
                                         "ORDER BY no_simpan, transaksi_ke ASC");
            while(rs.next()){
                Object[] obj = new Object[7];
                obj[0]=rs.getString("no_simpan");
                obj[1]=rs.getString("no_anggota");
                obj[2]=rs.getString("transaksi_ke");
                obj[3]=rs.getString("tanggal_simpan");
                obj[4]=rs.getString("masuk_simpanan");
                obj[5]=rs.getString("penarikan");
                obj[6]=rs.getString("total_simpanan");
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
            ResultSet rs=st.executeQuery("SELECT `no_simpan`, akun_simpanan.no_anggota, transaksi_ke, tanggal_simpan, masuk_simpanan, penarikan, total_simpanan  FROM `simpanan`\n" +
                                         "INNER JOIN akun_simpanan ON simpanan.no_simpan=akun_simpanan.no_simpanan\n" +
                                         "WHERE no_simpan LIKE '%"+cari+"%'\n" +
                                         "OR akun_simpanan.no_anggota LIKE '%"+cari+"%'\n" +
                                         "OR transaksi_ke LIKE '%Sim0917-001%'\n" +
                                         "OR tanggal_simpan LIKE '%Sim0917-001%'\n" +
                                         "ORDER BY no_simpan, transaksi_ke ASC");
            while(rs.next()){
                Object[] obj = new Object[7];
                obj[0]=rs.getString("no_simpan");
                obj[1]=rs.getString("no_anggota");
                obj[2]=rs.getString("transaksi_ke");
                obj[3]=rs.getString("tanggal_simpan");
                obj[4]=rs.getString("masuk_simpanan");
                obj[5]=rs.getString("penarikan");
                obj[6]=rs.getString("total_simpanan");
                model.addRow(obj);
            }
        }catch(Exception ex){}
        return model;
    }
}
