/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.form;

import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import koperasi_simpanpinjam.Koperasi_SimpanPinjam;
import koperasi_simpanpinjam.control.AngsuranPinjamanController;
import koperasi_simpanpinjam.control.PinjamController;
import koperasi_simpanpinjam.data.AngsuranPinjaman;
import koperasi_simpanpinjam.data.Pinjaman;

/**
 *
 * @author Candi-PC
 */
public class angsuran_pinjam extends javax.swing.JInternalFrame implements NavigatorFormInt{

    AngsuranPinjamanController apCont=new AngsuranPinjamanController(Koperasi_SimpanPinjam.emf);
    PinjamController pCont=new PinjamController(Koperasi_SimpanPinjam.emf);
    Pinjaman pinjam=new Pinjaman();
    AngsuranPinjaman angsur=new AngsuranPinjaman();
    DefaultTableModel model;
    public String no, ang, tot, bi;
    String kd;
    /**
     * Creates new form angsuran_pinjam
     */
    public angsuran_pinjam(String kode) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("No. Pinjaman");
        model.addColumn("No. Anggota");
        model.addColumn("Angsuran Ke");
        model.addColumn("Tanggal");
        model.addColumn("Total Pinjaman");
        model.addColumn("Biaya Angsuran");
        model.addColumn("Sisa Pinjaman");
        seleksiTable();
        tableAngsur.getTableHeader().setFont(new Font("Leelawadee", Font.BOLD, 14));
        kd=kode;
    }
    
    private void tanggal(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTgl.setText(sdf.format(cal.getTime()));
    }

    public void pilihNomor(){
        PopAngsuran PA=new PopAngsuran();
        PA.AP=this;
        long total, sisa, biaya;
        txtNomor.setText(no);
        txtAngsurKe.setText(String.valueOf(apCont.transaksiKe(no)));
        tanggal();
        txtTotal.setText(String.valueOf(tot));
        txtBiaya.setText(String.valueOf(bi));
        int pk=apCont.pk(no, Integer.parseInt(txtAngsurKe.getText())-1);
        angsur=apCont.findAngsuranPinjaman(pk);
        
        if(angsur==null){
            total=Long.parseLong(tot);
            biaya=Long.parseLong(txtBiaya.getText());
            sisa=total-biaya;
            txtSisa.setText(String.valueOf(sisa));
            PopAngsuran.getPop().dispose();
        }else{
            int lama=apCont.validasiLama(no);
            int ang=apCont.validasiAngsuran(no);
            if(lama==ang){
                JOptionPane.showMessageDialog(null, "Angsuran ini telah lunas!");
                apCont.updateStatusPinjaman(no);
                bersih();
            }else{
                total=Double.valueOf(angsur.getSisaPinjaman()).longValue();
                biaya=Long.parseLong(txtBiaya.getText());
                sisa=total-biaya;
                txtSisa.setText(String.valueOf(sisa));
                PopAngsuran.getPop().dispose();
            }
        }
    }
    
    private void showTableAngsur(){
        tableAngsur.setModel(apCont.showTable(model));
    }
    
    public void renderTable(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableAngsur.getColumnModel().getColumn(3).setCellRenderer(tbr);
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=apCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableAngsur.setModel(apCont.cari(model, cari));
        }
    }

    private void seleksiTable(){
        tableAngsur.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableAngsur.getSelectedRow();       //dapatkan baris yang terseleksi
                if(baris != -1){                        //jika baris bukan sama dengan -1 atau tidak ada
                    SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
                    txtNomor.setText(tableAngsur.getValueAt(baris, 0).toString());
                    txtAngsurKe.setText(tableAngsur.getValueAt(baris, 2).toString());
                    txtTgl.setText(sdf.format(tableAngsur.getValueAt(baris, 3)));
                    txtTotal.setText(tableAngsur.getValueAt(baris, 4).toString());
                    txtBiaya.setText(tableAngsur.getValueAt(baris, 5).toString());
                    txtSisa.setText(tableAngsur.getValueAt(baris, 6).toString());
                }
            }
        });
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
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAngsur = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtBiaya = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtTgl = new javax.swing.JTextField();
        txtAngsurKe = new javax.swing.JTextField();
        txtNomor = new javax.swing.JTextField();
        txtSisa = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btSave = new javax.swing.JButton();
        btNew = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btCariPinjam = new javax.swing.JButton();
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
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("ANGSURAN PINJAMAN");

        jLabel1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 231, 190));
        jLabel1.setText("NO. PINJAMAN");

        jLabel2.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(239, 231, 190));
        jLabel2.setText("ANGSURAN KE");

        jLabel3.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 231, 190));
        jLabel3.setText("TANGGAL BAYAR");

        jLabel4.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 231, 190));
        jLabel4.setText("BIAYA ANGSURAN");

        jLabel5.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 231, 190));
        jLabel5.setText("SISA PINJAMAN");

        jLabel7.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(239, 231, 190));
        jLabel7.setText("Cari:");

        txtCari.setBackground(new java.awt.Color(239, 231, 190));
        txtCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        tableAngsur.setBackground(new java.awt.Color(239, 231, 190));
        tableAngsur.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        tableAngsur.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Pinjaman", "No. anggota", "Angsuran Ke", "Tanggal", "Total Pinjaman", "Biaya Angsur", "Sisa Pinjaman"
            }
        ));
        jScrollPane1.setViewportView(tableAngsur);

        jLabel6.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(239, 231, 190));
        jLabel6.setText("TOTAL PINJAMAN");

        txtBiaya.setBackground(new java.awt.Color(239, 231, 190));
        txtBiaya.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtTotal.setBackground(new java.awt.Color(239, 231, 190));
        txtTotal.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtTgl.setBackground(new java.awt.Color(239, 231, 190));
        txtTgl.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtAngsurKe.setBackground(new java.awt.Color(239, 231, 190));
        txtAngsurKe.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtNomor.setBackground(new java.awt.Color(239, 231, 190));
        txtNomor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtSisa.setBackground(new java.awt.Color(239, 231, 190));
        txtSisa.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(171, 26, 37));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

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
        jPanel2.setForeground(new java.awt.Color(171, 26, 37));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        btCariPinjam.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btCariPinjam.setForeground(new java.awt.Color(239, 231, 190));
        btCariPinjam.setText("Cari");
        btCariPinjam.setContentAreaFilled(false);
        btCariPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariPinjamActionPerformed(evt);
            }
        });
        jPanel2.add(btCariPinjam);

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
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel4))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(txtNomor, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtSisa)
                                        .addComponent(txtBiaya)
                                        .addComponent(txtTotal)
                                        .addComponent(txtTgl)
                                        .addComponent(txtAngsurKe)))
                                .addComponent(jLabel5)
                                .addComponent(jSeparator1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 80, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtAngsurKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtSisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        aktif();
        bersih();
    }//GEN-LAST:event_btNewActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        if(txtNomor.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Pilih nomor pinjaman!");
        }else{
            simpan();
            bersih();
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNomor.setEnabled(false);
        btCariPinjam.setEnabled(false);
        txtAngsurKe.setEnabled(false);
        txtTgl.setEnabled(false);
        txtTotal.setEnabled(false);
        txtBiaya.setEnabled(false);
        txtSisa.setEnabled(false);
        txtCari.setEnabled(false);
        btSave.setEnabled(false);
        btCari.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void btCariPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariPinjamActionPerformed
        PopAngsuran.getPop().AP=this;
        PopAngsuran.getPop().setVisible(true);
    }//GEN-LAST:event_btCariPinjamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariPinjam;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tableAngsur;
    private javax.swing.JTextField txtAngsurKe;
    private javax.swing.JTextField txtBiaya;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNomor;
    private javax.swing.JTextField txtSisa;
    private javax.swing.JTextField txtTgl;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        btCariPinjam.setEnabled(true);
        txtCari.setEnabled(true);
        btSave.setEnabled(true);
        btCari.setEnabled(true);
    }

    @Override
    public void bersih() {
        txtNomor.setText("");
        txtAngsurKe.setText("");
        txtTgl.setText("");
        txtTotal.setText("");
        txtBiaya.setText("");
        txtSisa.setText("");
        txtCari.setText("");
        showTableAngsur();
        renderTable();
    }

    @Override
    public void simpan() {
        int pk=apCont.pk(txtNomor.getText(), Integer.parseInt(txtAngsurKe.getText()));
        angsur=apCont.findAngsuranPinjaman(pk);
        AngsuranPinjaman ap=new AngsuranPinjaman();
        if(angsur==null){
            ap.setNoPinjaman(txtNomor.getText());
            ap.setAngsuranKe(Integer.parseInt(txtAngsurKe.getText()));
            ap.setTanggalBayar(new Date());
            ap.setBiayaAngsuran(Double.parseDouble(txtBiaya.getText()));
            ap.setSisaPinjaman(Double.parseDouble(txtSisa.getText()));
            ap.setKodeUser(kd);
            try{
                apCont.save(ap);
            }catch(Exception ex){}
            JOptionPane.showMessageDialog(null, "Simpan berhasil!");
        }else{
            JOptionPane.showMessageDialog(null, "Angsuran ini telah dibayar!");
        }
    }

    @Override
    public void hapus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
