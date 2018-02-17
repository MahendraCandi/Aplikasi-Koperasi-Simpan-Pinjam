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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import koperasi_simpanpinjam.data.AkunPinjaman;
import koperasi_simpanpinjam.data.AkunSimpanan;

/**
 *
 * @author Candi-PC
 */
public class AkunPinjamanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public AkunPinjamanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(AkunPinjaman Akun) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(Akun);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void update(AkunPinjaman Akun) throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(Akun);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama) throws Exception{
        EntityManager em = getEntityManager();
        AkunPinjaman us;
        try{
            us=em.getReference(AkunPinjaman.class, nama);
            us.getNoPinjaman();
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public AkunPinjaman findAkunPinjaman(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(AkunPinjaman.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        SimpleDateFormat sdf=new SimpleDateFormat("MMyy");
        Calendar cal=Calendar.getInstance();
        String tahun=sdf.format(cal.getTime());
        String kode="Pin"+tahun+"-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select u from AkunPinjaman u order by u.noPinjaman desc");
            q.setMaxResults(1);
            AkunPinjaman us=(AkunPinjaman) q.getSingleResult();
            if(q!=null){
                String no=us.getNoPinjaman().substring(0,8);
                String nomorurut = us.getNoPinjaman().substring(8);
                int urut = (Integer.parseInt(nomorurut)+1);
                String formatted=String.format("%03d", urut);
                kode=no+(formatted);
            }
        }catch(NoResultException ex){}
        return kode;
    }
    
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT `no_anggota`, anggota.nama, `no_pinjaman`, `status` FROM `akun_pinjaman`\n" +
                                         "INNER JOIN anggota USING (no_anggota)");
            while(rs.next()){
                Object[] obj = new Object[4];
                obj[0]=rs.getString("no_anggota");
                obj[1]=rs.getString("nama");
                obj[2]=rs.getString("no_pinjaman");
                obj[3]=rs.getString("status");
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
            ResultSet rs=st.executeQuery("SELECT `no_anggota`, anggota.nama, `no_pinjaman`, `status` FROM `akun_pinjaman`\n" +
                                         "INNER JOIN anggota USING (no_anggota)\n" +
                                         "WHERE no_anggota LIKE '%"+cari+"%'\n" +
                                         "OR anggota.nama LIKE '%"+cari+"%'\n" +
                                         "OR no_pinjaman LIKE '%"+cari+"%'\n" +
                                         "OR `status` LIKE '%"+cari+"%'");
            while(rs.next()){
                Object[] obj = new Object[4];
                obj[0]=rs.getString("no_anggota");
                obj[1]=rs.getString("nama");
                obj[2]=rs.getString("no_pinjaman");
                obj[3]=rs.getString("status");
                model.addRow(obj);
            }
        }catch(Exception ex){}
        return model;
    }    
}
