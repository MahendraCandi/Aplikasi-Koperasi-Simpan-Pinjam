/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.control;

/**
 *
 * @author User
 */
import koperasi_simpanpinjam.data.Anggota;
import java.io.Serializable;
import javax.persistence.Query;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.swing.table.DefaultTableModel;

public class AnggotaController implements Serializable{
    private EntityManagerFactory emf=null;

public AnggotaController(EntityManagerFactory emf){
    this.emf=emf;
}

public EntityManager getEntityManager(){
    return emf.createEntityManager();
}
public void save(Anggota anggota) throws Exception{ 
    EntityManager em=getEntityManager();
    try{ 
        em.getTransaction().begin(); 
        em.persist(anggota); 
        em.getTransaction().commit();
    }catch(Exception ex){} 
    
} public void update(Anggota anggota) throws Exception{ 
    EntityManager em=getEntityManager();
    try{ 
        em.getTransaction().begin();
        em.merge(anggota); 
        em.getTransaction().commit(); 
    }catch(Exception ex){}
}

public void delete(String kode) throws Exception{
    EntityManager em=getEntityManager();
    Anggota pd;
    try{
        pd=em.getReference(Anggota.class, kode);
        pd.getNoAnggota();
        em.getTransaction().begin();
        em.remove(pd);
        em.getTransaction().commit();
    }catch(Exception ex){}
}
public Anggota findAnggota(String kode){
    EntityManager em=getEntityManager();
    try{
        return em.find(Anggota.class, kode);
    }finally{}
}
public String nomorOtomatis(){ 
    String kode="Member-001"; 
    EntityManager em=null;
    try{ em = getEntityManager();
    Query q=em.createQuery("select b from Anggota b order by b.noAnggota desc"); 
    q.setMaxResults(1);
    Anggota p=(Anggota) q.getSingleResult();
    if(q!=null){ 
        DecimalFormat formatnomor = new DecimalFormat("Member-000"); 
        String nomorurut = p.getNoAnggota().substring(7);
        kode=formatnomor.format(Double.parseDouble(nomorurut)+1);
    } 
    }catch(NoResultException ex){} 
    return kode; 
}

public DefaultTableModel showTable(DefaultTableModel model){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT u.noAnggota, u.nama, u.pekerjaan, u.tglLahir from Anggota u");
            List<Object> hasil=(List<Object>) q.getResultList();
            Iterator itr = hasil.iterator();
            while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
    
    //cari user
    public DefaultTableModel cari(DefaultTableModel model, String cari){
        EntityManager em=getEntityManager();
        try{
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            Query q=em.createQuery("SELECT u.noAnggota, u.nama, u.pekerjaan, u.tglLahir from Anggota u WHERE u.noAnggota like '%"+cari+"%'"
                                 + " or u.nama like '%"+cari+"%'"
                                 + " or u.pekerjaan like '%"+cari+"%'"
                                 + " or u.tglLahir like '%"+cari+"%'");
            List<Object> hasil=(List<Object>) q.getResultList();
                Iterator itr = hasil.iterator();
                while(itr.hasNext()){
                Object[] obj = (Object[]) itr.next();
                model.addRow(obj);
            }
            return model;
        }finally{}
    }
}