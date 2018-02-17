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
import koperasi_simpanpinjam.control.AkunSimpananController;
import koperasi_simpanpinjam.control.AnggotaController;
import koperasi_simpanpinjam.data.AkunSimpanan;
import koperasi_simpanpinjam.data.Anggota;

/**
 *
 * @author Candi-PC
 */
public class akun_simpan extends javax.swing.JInternalFrame implements NavigatorFormInt{

    AkunSimpananController akCont=new AkunSimpananController(Koperasi_SimpanPinjam.emf);
    AnggotaController aCont=new AnggotaController(Koperasi_SimpanPinjam.emf);
    AkunSimpanan akun=new AkunSimpanan();
    Anggota anggota=new Anggota();
    DefaultTableModel model;
    public String ang;
    
    /**
     * Creates new form akun_simpan
     */
    public akun_simpan() {
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
        model.addColumn("No. Simpanan");
        model.addColumn("Status");
        seleksiTable();
        tableAkun.getTableHeader().setFont(new Font("Leelawadee", Font.BOLD, 14));
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
        PA.AS=this;
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
            PopAnggota.getPop().dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Member ini sudah membuat akun simpanan!");
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

        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtKerja = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTempat = new javax.swing.JTextField();
        txtTglLahir = new javax.swing.JTextField();
        txtKtp = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtAnggota = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableAkun = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtNomor = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtTelp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btDelete = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btNew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btcariAnggota = new javax.swing.JButton();
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

        jLabel8.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(239, 231, 190));
        jLabel8.setText("AKUN SIMPANAN");

        jLabel1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 231, 190));
        jLabel1.setText("NO. ANGGOTA");

        txtKerja.setBackground(new java.awt.Color(239, 231, 190));
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

        jLabel5.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 231, 190));
        jLabel5.setText("TEMPAT LAHIR");

        jLabel6.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(239, 231, 190));
        jLabel6.setText("TANGGAL LAHIR");

        jLabel7.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(239, 231, 190));
        jLabel7.setText("ALAMAT");

        txtTempat.setBackground(new java.awt.Color(239, 231, 190));
        txtTempat.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtTglLahir.setBackground(new java.awt.Color(239, 231, 190));
        txtTglLahir.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtKtp.setBackground(new java.awt.Color(239, 231, 190));
        txtKtp.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtNama.setBackground(new java.awt.Color(239, 231, 190));
        txtNama.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtAnggota.setBackground(new java.awt.Color(239, 231, 190));
        txtAnggota.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        tableAkun.setBackground(new java.awt.Color(239, 231, 190));
        tableAkun.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        tableAkun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Anggota", "Nama Anggota", "No. Simpanan", "Status"
            }
        ));
        jScrollPane2.setViewportView(tableAkun);

        jLabel9.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(239, 231, 190));
        jLabel9.setText("Cari:");

        txtCari.setBackground(new java.awt.Color(239, 231, 190));
        txtCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(239, 231, 190));
        jLabel10.setText("NO. SIMPANAN");

        txtNomor.setBackground(new java.awt.Color(239, 231, 190));
        txtNomor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(239, 231, 190));
        jLabel11.setText("NO. TELEPON");

        txtTelp.setBackground(new java.awt.Color(239, 231, 190));
        txtTelp.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(239, 231, 190));
        jLabel12.setText("STATUS");

        txtStatus.setBackground(new java.awt.Color(239, 231, 190));
        txtStatus.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(171, 26, 37));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

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

        jPanel2.setBackground(new java.awt.Color(171, 26, 37));
        jPanel2.setLayout(new java.awt.CardLayout());

        btcariAnggota.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btcariAnggota.setForeground(new java.awt.Color(239, 231, 190));
        btcariAnggota.setText("Cari");
        btcariAnggota.setContentAreaFilled(false);
        btcariAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcariAnggotaActionPerformed(evt);
            }
        });
        jPanel2.add(btcariAnggota, "card2");

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
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtStatus)
                                    .addComponent(jScrollPane1)
                                    .addComponent(txtTempat)
                                    .addComponent(txtTelp)
                                    .addComponent(txtKerja)
                                    .addComponent(txtKtp)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtAnggota)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNomor)
                                    .addComponent(txtNama)
                                    .addComponent(txtTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTempat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 142, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        if(txtStatus.getText().equalsIgnoreCase("Berlaku")){
            JOptionPane.showMessageDialog(null, "Tidak bisa hapus akun, karena akun masih berlaku!");
        }else{
            if(JOptionPane.showConfirmDialog(null, "Yakin hapus data ini?", "Warning!", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                hapus();
                bersih();
            }
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        if(txtAnggota.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Masukan nomor anggota!");
        }else{
            simpan();
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        aktif();
        bersih();
    }//GEN-LAST:event_btNewActionPerformed

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
        btcariAnggota.setEnabled(false);
        btNew.setEnabled(true);
        btSave.setEnabled(false);
        btDelete.setEnabled(false);
        btCari.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void btcariAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcariAnggotaActionPerformed
        akun_pinjam ap=new akun_pinjam();
        PopAnggota.getPop().AS=this;
        PopAnggota.getPop().AP=ap;
        PopAnggota.getPop().setVisible(true);
        
    }//GEN-LAST:event_btcariAnggotaActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btcariAnggota;
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
        btcariAnggota.setEnabled(true);
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
        akun=akCont.findAkunSimpanan(txtNomor.getText());
        AkunSimpanan ak=new AkunSimpanan();
        if(akun==null){
            ak.setNoSimpanan(txtNomor.getText());
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
