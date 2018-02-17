/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.control;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Candi-PC
 */
public class LaporanController implements Serializable{
    private EntityManagerFactory emf=null;
    
    public LaporanController(EntityManagerFactory emf){
        this.emf=emf;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    

    public void cetakAngsuranPinjaman(){
        EntityManager em=null;
        try{
            String no=JOptionPane.showInputDialog("Masukan nomor pinjaman yang mau dicetak!");
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportAngsuran.jasper";
            
            HashMap param = new HashMap();
            param.put("noPinjam", no);
            
            JasperPrint jprint=JasperFillManager.fillReport (namafile, param,connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.close();
                connect.close();
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void cetakTransaksiSimpanan(){
        EntityManager em=null;
        try{
            String no=JOptionPane.showInputDialog("Masukan nomor simpanan yang mau dicetak!");
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportTransaksiSimpanan.jasper";
            
            HashMap param = new HashMap();
            param.put("noSimpan", no);
            
            JasperPrint jprint=JasperFillManager.fillReport (namafile, param,connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.close();
                connect.close();
        }catch(Exception e){e.printStackTrace();}
    }
        
        public void cetakAkunPinjaman(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportAkunPinjaman.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                connect.close();
                em.close();
            
        }catch(Exception e){e.printStackTrace();
        }
    }   
        
        public void cetakAkunSimpanan(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportAkunSimpanan.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                em.getTransaction().commit();
                em.close();
                connect.close();
            
        }catch(Exception e){e.printStackTrace();
        }
    }
        
        public void cetakAnggota(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportAnggota.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                connect.close();
                em.close();
                
        }catch(Exception e){e.printStackTrace();
        }
    }
        
    public void cetakUser(){
        EntityManager em=null;
        try{
            em=emf.createEntityManager();
            em.getTransaction().begin();
            Connection connect=em.unwrap(Connection.class);
            File file=new File("");
            String namafile=file.getAbsolutePath()+"\\Laporan\\reportUser.jasper";
            JasperPrint jprint=JasperFillManager.fillReport (namafile, new HashMap(),connect);
            JasperViewer viewer=new JasperViewer(jprint, false);
                viewer.setFitPageZoomRatio();
                viewer.setVisible(true);
                connect.close();
                em.close();
                
        }catch(Exception e){}
    }
}
