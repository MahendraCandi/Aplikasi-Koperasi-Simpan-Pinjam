/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.control;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;
import koperasi_simpanpinjam.data.User;

/**
 *
 * @author Candi-PC
 */
public class UserController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public UserController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public void save(User user) throws Exception {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void update(User user) throws Exception{
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public void delete(String nama) throws Exception{
        EntityManager em = getEntityManager();
        User us;
        try{
            us=em.getReference(User.class, nama);
            us.getKodeUser();
            em.getTransaction().begin();
            em.remove(us);
            em.getTransaction().commit();
        }catch(Exception ex){}
    }
    
    public User findUser(String nama){
        EntityManager em=getEntityManager();
        try{
            return em.find(User.class, nama);
        }finally{}
    }
    
    public String nomorOtomatis(){
        String kode="User-001";
        EntityManager em=null;
        try{
            em=getEntityManager();
            Query q=em.createQuery("select u from User u order by u.kodeUser desc");
            q.setMaxResults(1);
            User us=(User) q.getSingleResult();
            if(q!=null){
                DecimalFormat formatnomor = new DecimalFormat("User-000");
                String nomorurut = us.getKodeUser().substring(5);
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
            Query q=em.createQuery("SELECT u.kodeUser, u.namaUser, u.password, u.hakAkses from User u");
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
            Query q=em.createQuery("SELECT u.kodeUser, u.namaUser, u.password, u.hakAkses from User u WHERE u.kodeUser like '%"+cari+"%'"
                                 + " or u.namaUser like '%"+cari+"%'"
                                 + " or u.password like '%"+cari+"%'"
                                 + " or u.hakAkses like '%"+cari+"%'");
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
