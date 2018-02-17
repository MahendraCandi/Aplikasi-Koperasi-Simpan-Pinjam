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
import koperasi_simpanpinjam.data.AngsuranPinjaman;

/**
 *
 * @author Candi-PC
 */
public class AngsuranPinjamanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public AngsuranPinjamanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(AngsuranPinjaman angsur) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(angsur);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(int pk) throws Exception{
        EntityManager em = getEntityManager();
        AngsuranPinjaman us;
        try{
            us=em.getReference(AngsuranPinjaman.class, pk);
            us.getIdAngsuran();
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public int pk(String no, int angsur){
        EntityManager em=null;
        int pk=0;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT s.idAngsuran FROM AngsuranPinjaman s WHERE s.noPinjaman = :nomor AND s.angsuranKe = :angsuran");
            q.setParameter("nomor", no);
            q.setParameter("angsuran", angsur);
            if(q!=null){
                int ke= (int) q.getSingleResult();
                pk=pk+ke;
            }
        }catch(NoResultException ex){}
        return pk;
    }
    
    public AngsuranPinjaman findAngsuranPinjaman(int PK){
        EntityManager em=getEntityManager();
        try{
            return em.find(AngsuranPinjaman.class, PK);
        }finally{}
    }
    
    public int transaksiKe(String no){
        int Trans=1;
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("SELECT s.noPinjaman, s.angsuranKe FROM AngsuranPinjaman s order by s.angsuranKe desc");
            q.setMaxResults(1);
            if(q!=null){
                Query tr=em.createQuery("SELECT s.angsuranKe FROM AngsuranPinjaman s WHERE s.noPinjaman LIKE '%"+no+"%' order by s.angsuranKe desc");
                tr.setMaxResults(1);
                int x=Integer.parseInt(tr.getSingleResult().toString());
                Trans=x+1;
            }
        }catch(NoResultException ex){}
        return Trans;
    }
    
    public int validasiLama(String nomor){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            Query lama=em.createQuery("SELECT p.lamaAngsuran FROM Pinjaman p WHERE p.noPinjam = :nomor");
            lama.setParameter("nomor", nomor);
            lama.setMaxResults(1);
            int hasil=(int) lama.getSingleResult();
            return hasil;
        }finally{}
    }
    
    public int validasiAngsuran(String nomor){
        EntityManager em=null;
        try{
            em=getEntityManager();
            em.getTransaction().begin();
            Query ang=em.createQuery("SELECT s.angsuranKe FROM AngsuranPinjaman s WHERE s.noPinjaman = :nomor ORDER BY s.angsuranKe DESC");
            ang.setParameter("nomor", nomor);
            ang.setMaxResults(1);
            int hasil=(int)ang.getSingleResult();
            return hasil;
        }finally{}
    }
    
    public void updateStatusPinjaman(String no){
        EntityManager em=getEntityManager();
        String ubah="Lunas";
        try{
            em.getTransaction().begin();
            Query q=em.createQuery("UPDATE AkunPinjaman a SET a.status = :ubah WHERE a.noPinjaman = :nomor" );
            q.setParameter("ubah", ubah);
            q.setParameter("nomor", no);
            q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            em.getTransaction().begin();
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Connection connect=em.unwrap(Connection.class);
            Statement st=(Statement) connect.createStatement();
            ResultSet rs=st.executeQuery("SELECT no_pinjaman, akun_pinjaman.no_anggota, angsuran_ke, tanggal_bayar, pinjaman.total_angsuran, angsuran_pinjaman.biaya_angsuran, sisa_pinjaman \n" +
                                        "FROM `angsuran_pinjaman` \n" +
                                        "INNER JOIN akun_pinjaman USING (no_pinjaman)\n" +
                                        "INNER JOIN pinjaman ON angsuran_pinjaman.no_pinjaman=pinjaman.no_pinjam");
            while(rs.next()){
                Object[] obj = new Object[8];
                obj[0]=rs.getString("no_pinjaman");
                obj[1]=rs.getString("no_anggota");
                obj[2]=rs.getString("angsuran_ke");
                obj[3]=rs.getDate("tanggal_bayar");
                obj[4]=rs.getString("total_angsuran");
                obj[5]=rs.getString("biaya_angsuran");
                obj[6]=rs.getString("sisa_pinjaman");
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
            ResultSet rs=st.executeQuery("SELECT no_pinjaman, akun_pinjaman.no_anggota, angsuran_ke, tanggal_bayar, pinjaman.total_angsuran, angsuran_pinjaman.biaya_angsuran, sisa_pinjaman \n" +
                                        "FROM `angsuran_pinjaman` \n" +
                                        "INNER JOIN akun_pinjaman USING (no_pinjaman)\n" +
                                        "INNER JOIN pinjaman ON angsuran_pinjaman.no_pinjaman=pinjaman.no_pinjam\n" +
                                        "WHERE no_pinjaman LIKE '%"+cari+"%'\n" +
                                        "OR akun_pinjaman.no_anggota LIKE '%"+cari+"%'\n" +
                                        "OR tanggal_bayar LIKE '%"+cari+"%'");
            while(rs.next()){
                Object[] obj = new Object[8];
                obj[0]=rs.getString("no_pinjaman");
                obj[1]=rs.getString("no_anggota");
                obj[2]=rs.getString("angsuran_ke");
                obj[3]=rs.getDate("tanggal_bayar");
                obj[4]=rs.getString("total_angsuran");
                obj[5]=rs.getString("biaya_angsuran");
                obj[6]=rs.getString("sisa_pinjaman");
                model.addRow(obj);
            }
        }catch(Exception ex){}
        return model;
    }
}
