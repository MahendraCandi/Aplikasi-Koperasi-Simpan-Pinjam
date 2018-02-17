/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.form;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import koperasi_simpanpinjam.Koperasi_SimpanPinjam;
import koperasi_simpanpinjam.control.AkunPinjamanController;
import koperasi_simpanpinjam.control.AnggotaController;
import koperasi_simpanpinjam.data.AkunPinjaman;
import koperasi_simpanpinjam.data.Anggota;

/**
 *
 * @author Candi-PC
 */
public class akun_pinjam extends javax.swing.JInternalFrame implements NavigatorFormInt{

    AkunPinjamanController akCont=new AkunPinjamanController(Koperasi_SimpanPinjam.emf);
    AnggotaController aCont=new AnggotaController(Koperasi_SimpanPinjam.emf);
    AkunPinjaman akun=new AkunPinjaman();
    Anggota anggota=new Anggota();
    DefaultTableModel model;
    public String ang;
    /**
     * Creates new form akun_pinjam
     */
    public akun_pinjam() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("No. Anggota");
        model.addColumn("Nama Anggota");
        model.addColumn("No. Pinjaman");
        model.addColumn("Status");
        tableAkun.getTableHeader().setFont(new Font("Leelawadee", Font.BOLD, 14));
        seleksiTable();
    }
    
    private void showTableAkun(){
        tableAkun.setModel(akCont.showTable(model));
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=akCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableAkun.setModel(akCont.cari(model, cari));
        }
    }

    private void seleksiTable(){
        tableAkun.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableAkun.getSelectedRow();       //dapatkan baris yang terseleksi
                if(baris != -1){                        //jika baris bukan sama dengan -1 atau tidak ada
                    String kd=tableAkun.getValueAt(baris, 0).toString();
                    txtNomor.setText(tableAkun.getValueAt(baris, 2).toString());
                    txtStatus.setText(tableAkun.getValueAt(baris, 3).toString());
                    anggota=aCont.findAnggota(kd);
                    txtAnggota.setText(anggota.getNoAnggota());
                    txtNama.setText(anggota.getNama());
                    txtKtp.setText(String.valueOf(anggota.getNoKtp()));
                    txtKerja.setText(anggota.getPekerjaan());
                    txtTelp.setText(String.valueOf(anggota.getNoTelp()));
                    txtTempat.setText(anggota.getTempatLahir());
                    txtAlamat.setText(anggota.getAlamat());
                    SimpleDateFormat sdf=new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
                    txtTglLahir.setText(sdf.format(anggota.getTglLahir()));
                }
            }
        });
    }
    
    public void pilihAnggota(){
        PopAnggota PA=new PopAnggota();
        PA.AP=this;
        anggota=aCont.findAnggota(ang);
        boolean eksis=false;
        String data="";
        int baris=tableAkun.getRowCount();
        for(int i=0; i<baris; i++){
            data=tableAkun.getValueAt(i,0).toString().trim();
            if(ang.equals(data)){
                eksis=true;
                break; 
                
            }
        }
        
        if(!eksis){
            SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("In-ID"));
            txtAnggota.setText(ang);
            txtNama.setText(anggota.getNama());
            txtKtp.setText(String.valueOf(anggota.getNoKtp()));
            txtKerja.setText(anggota.getPekerjaan());
            txtTempat.setText(anggota.getTempatLahir());
            txtTelp.setText(String.valueOf(anggota.getNoTelp()));
            txtTglLahir.setText(sdf.format(anggota.getTglLahir()));
            txtAlamat.setText(anggota.getAlamat());
            PopAnggota.getPop2().dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Member ini sudah membuat akun pinjaman!");
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNomor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTempat = new javax.swing.JTextField();
        txtTglLahir = new javax.swing.JTextField();
        txtKtp = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtAnggota = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtKerja = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAkun = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTelp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btCariAnggota = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btCari = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 38, 53));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel10.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(239, 231, 190));
        jLabel10.setText("NO. PINJAMAN");

        jLabel5.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 231, 190));
        jLabel5.setText("TEMPAT LAHIR");

        txtNomor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(239, 231, 190));
        jLabel6.setText("TANGGAL LAHIR");

        jLabel7.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(239, 231, 190));
        jLabel7.setText("ALAMAT");

        txtTempat.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtTglLahir.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtKtp.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtNama.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtAnggota.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        jLabel1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 231, 190));
        jLabel1.setText("CARI ANGGOTA");

        txtKerja.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(239, 231, 190));
        jLabel2.setText("NAMA ANGGOTA");

        jLabel3.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 231, 190));
        jLabel3.setText("NO. KTP");

        jLabel4.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 231, 190));
        jLabel4.setText("PEKERJAAN");

        tableAkun.setBackground(new java.awt.Color(239, 231, 190));
        tableAkun.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        tableAkun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Anggota", "Nama Anggota", "No. Pinjaman", "Status"
            }
        ));
        jScrollPane2.setViewportView(tableAkun);

        jLabel9.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(239, 231, 190));
        jLabel9.setText("Cari:");

        txtCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(239, 231, 190));
        jLabel11.setText("STATUS");

        txtStatus.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(239, 231, 190));
        jLabel12.setText("NO. TELEPON");

        txtTelp.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(239, 231, 190));
        jLabel8.setText("AKUN PINJAMAN");

        jPanel1.setBackground(new java.awt.Color(171, 26, 37));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        btNew.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btNew.setForeground(new java.awt.Color(239, 231, 190));
        btNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add File_32px_3.png"))); // NOI18N
        btNew.setText("NEW");
        btNew.setContentAreaFilled(false);
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        jPanel1.add(btNew);

        btSave.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btSave.setForeground(new java.awt.Color(239, 231, 190));
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save_32px_2.png"))); // NOI18N
        btSave.setText("SAVE");
        btSave.setContentAreaFilled(false);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jPanel1.add(btSave);

        btDelete.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btDelete.setForeground(new java.awt.Color(239, 231, 190));
        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete File_32px.png"))); // NOI18N
        btDelete.setText("DELETE");
        btDelete.setContentAreaFilled(false);
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btDelete);

        jPanel2.setBackground(new java.awt.Color(171, 26, 37));
        jPanel2.setLayout(new java.awt.CardLayout());

        btCariAnggota.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btCariAnggota.setForeground(new java.awt.Color(239, 231, 190));
        btCariAnggota.setText("Cari");
        btCariAnggota.setContentAreaFilled(false);
        btCariAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariAnggotaActionPerformed(evt);
            }
        });
        jPanel2.add(btCariAnggota, "card2");

        jPanel3.setBackground(new java.awt.Color(171, 26, 37));
        jPanel3.setLayout(new java.awt.CardLayout());

        btCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btCari.setForeground(new java.awt.Color(239, 231, 190));
        btCari.setText("Cari");
        btCari.setContentAreaFilled(false);
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel3.add(btCari, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel11))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtStatus)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(txtTglLahir)
                                    .addComponent(txtTempat)
                                    .addComponent(txtTelp)
                                    .addComponent(txtKerja)
                                    .addComponent(txtKtp)))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNama)
                                    .addComponent(txtNomor))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtKtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTempat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(34, 34, 34)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 137, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        aktif();
        bersih();
    }//GEN-LAST:event_btNewActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        if(txtAnggota.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Masukan nomor anggota!");
        }else{
            simpan();
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        if(txtStatus.getText().equalsIgnoreCase("Berlaku")){
            JOptionPane.showMessageDialog(null, "Tidak bisa hapus akun, karena akun masih berlaku!");
        }else{
            if(JOptionPane.showConfirmDialog(null, "Hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                hapus();
                bersih();
            }
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNomor.setEnabled(false);
        txtAnggota.setEnabled(false);
        txtNama.setEnabled(false);
        txtKtp.setEnabled(false);
        txtKerja.setEnabled(false);
        txtTelp.setEnabled(false);
        txtTempat.setEnabled(false);
        txtTglLahir.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtStatus.setEnabled(false);
        txtCari.setEnabled(false);
        btCariAnggota.setEnabled(false);
        btNew.setEnabled(true);
        btSave.setEnabled(false);
        btDelete.setEnabled(false);
        btCari.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void btCariAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariAnggotaActionPerformed
        akun_simpan as=new akun_simpan();
        PopAnggota.getPop2().AP=this;
        PopAnggota.getPop2().AS=as;
        PopAnggota.getPop2().setVisible(true);
    }//GEN-LAST:event_btCariAnggotaActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariAnggota;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tableAkun;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtAnggota;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKerja;
    private javax.swing.JTextField txtKtp;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNomor;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JTextField txtTempat;
    private javax.swing.JTextField txtTglLahir;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtCari.setEnabled(true);
        btCariAnggota.setEnabled(true);
        btSave.setEnabled(true);
        btDelete.setEnabled(true);
        btCari.setEnabled(true);
    }

    @Override
    public void bersih() {
        txtAnggota.setText("");
        txtNama.setText("");
        txtKtp.setText("");
        txtKerja.setText("");
        txtTelp.setText("");
        txtTempat.setText("");
        txtTglLahir.setText("");
        txtAlamat.setText("");
        txtStatus.setText("");
        txtCari.setText("");
        txtNomor.setText(akCont.nomorOtomatis());
        showTableAkun();
    }

    @Override
    public void simpan() {
        akun=akCont.findAkunPinjaman(txtNomor.getText());
        AkunPinjaman ak=new AkunPinjaman();
        if(akun==null){
            ak.setNoPinjaman(txtNomor.getText());
            ak.setNoAnggota(txtAnggota.getText());
            ak.setStatus("Berlaku");
            try{
                akCont.save(ak);
            }catch(Exception ex){}
            JOptionPane.showMessageDialog(null, "Data telah disimpan!");
            bersih();
        }else{
            JOptionPane.showMessageDialog(null, "Data telah eksis!");
        }
    }

    @Override
    public void hapus() {
        try{
            akCont.delete(txtNomor.getText());
            JOptionPane.showMessageDialog(null, "Data telah dihapus!");
        }catch(Exception ex){}
    }

    @Override
    public void cari() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tampil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
