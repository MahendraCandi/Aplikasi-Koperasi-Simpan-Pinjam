/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.form;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
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
import koperasi_simpanpinjam.control.PinjamController;
import koperasi_simpanpinjam.data.Pinjaman;

/**
 *
 * @author User
 */
public class pinjam extends javax.swing.JInternalFrame implements NavigatorFormInt{

    PinjamController pCont=new PinjamController(Koperasi_SimpanPinjam.emf);
    Pinjaman pinjam=new Pinjaman();
    DefaultTableModel model;
    public String no, ang;
    int lama;
    String kd;
    /**
     * Creates new form pinjam
     */
    public pinjam(String kode) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("No. Pinjam");
        model.addColumn("No. Anggota");
        model.addColumn("Tanggal");
        model.addColumn("Jumlah Angsuran");
        model.addColumn("Lama Angsuran");
        model.addColumn("Bunga");
        model.addColumn("Total Angsuran");
        model.addColumn("Biaya Angsuran");
        seleksiTable();
        tablePinjam.getTableHeader().setFont(new Font("Leelawadee", Font.BOLD, 14));
        kd=kode;
    }
    
    private void tanggal(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTgl.setText(sdf.format(cal.getTime()));
    }
    
    private void showTablePinjam(){
        tablePinjam.setModel(pCont.showTable(model));
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
        tablePinjam.getColumnModel().getColumn(2).setCellRenderer(tbr);
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=pCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tablePinjam.setModel(pCont.cari(model, cari));
        }
    }

    private void seleksiTable(){
        tablePinjam.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tablePinjam.getSelectedRow();       //dapatkan baris yang terseleksi
                if(baris != -1){                        //jika baris bukan sama dengan -1 atau tidak ada
                    txtNomor.setText(tablePinjam.getValueAt(baris,0).toString());
                    txtAnggota.setText(tablePinjam.getValueAt(baris,1).toString());
                    SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
                    txtTgl.setText(sdf.format(tablePinjam.getValueAt(baris,2)));
                    txtJumlah.setText((tablePinjam.getValueAt(baris,3).toString()));
                    cbAngsur.setSelectedItem((tablePinjam.getValueAt(baris,4).toString()));
                    txtBunga.setText(tablePinjam.getValueAt(baris, 5).toString());
                    txtTotal.setText(tablePinjam.getValueAt(baris, 6).toString());
                    txtBiaya.setText(tablePinjam.getValueAt(baris, 7).toString());
                }
            }
        });
    }
    
    public void pilihAkun(){
        bersih();
        PopAkunPinjam PAP=new PopAkunPinjam();
        PAP.pin=this;
        int pk=pCont.pk(no);
        pinjam=pCont.findPinjaman(pk);
        boolean eksis=false;
        String data="";
        int baris=tablePinjam.getRowCount();
        for(int i=0; i<baris; i++){
            data=tablePinjam.getValueAt(i,0).toString();
            if(no.equals(data)){
                eksis=true;
                break;
            }
        }
        
        if(!eksis){
            txtNomor.setText(no);
            txtAnggota.setText(ang);
            PopAkunPinjam.getPop().dispose();
            tanggal();
        }else{
            JOptionPane.showMessageDialog(null, "Nomor pinjaman telah ada!");
        }
        
        
    }
    
    private void hitungAngsur(){
        long jumlah=Long.parseLong(txtJumlah.getText());
        long bunga=0, total=0, biaya=0;
        if(cbAngsur.getSelectedItem().equals("3 bulan")){
            lama=3;
            float b=(float) 0.02 * jumlah;
            bunga=(long) b;
        }else if(cbAngsur.getSelectedItem().equals("6 bulan")){
            lama=3;
            float b=(float) 0.05 * jumlah;
            bunga=(long) b;
        }else{
            lama=12;
            float b=(float) 0.1 * jumlah;
            bunga=(long) b;
        }
        total=jumlah+bunga;
        long x = total / lama;
        Double tes=new BigDecimal(Double.toString(x)).setScale(-3, 2).doubleValue();
        biaya= (long) tes.longValue();
        System.out.println(x);
        System.out.println(tes);
        txtBunga.setText(String.valueOf(bunga));
        txtTotal.setText(String.valueOf(total));
        txtBiaya.setText(String.valueOf(biaya));
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        txtBunga = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNomor = new javax.swing.JTextField();
        txtAnggota = new javax.swing.JTextField();
        txtTgl = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        cbAngsur = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtBiaya = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePinjam = new javax.swing.JTable();
        txtCari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btCariNomor = new javax.swing.JButton();
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

        jLabel4.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 231, 190));
        jLabel4.setText("TANGGAL PINJAM");

        txtBunga.setBackground(new java.awt.Color(239, 231, 190));
        txtBunga.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 231, 190));
        jLabel5.setText("JUMLAH PINJAMAN");

        txtNomor.setBackground(new java.awt.Color(239, 231, 190));
        txtNomor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtAnggota.setBackground(new java.awt.Color(239, 231, 190));
        txtAnggota.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnggotaActionPerformed(evt);
            }
        });

        txtTgl.setBackground(new java.awt.Color(239, 231, 190));
        txtTgl.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(239, 231, 190));
        jLabel6.setText("LAMA ANGSURAN");

        jLabel7.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(239, 231, 190));
        jLabel7.setText("BUNGA");

        txtJumlah.setBackground(new java.awt.Color(239, 231, 190));
        txtJumlah.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtJumlahKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 231, 190));
        jLabel3.setText("NOMOR ANGGOTA");

        jLabel10.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(239, 231, 190));
        jLabel10.setText("NOMOR PINJAM");

        txtTotal.setBackground(new java.awt.Color(239, 231, 190));
        txtTotal.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(239, 231, 190));
        jLabel11.setText("TOTAL ANGSURAN");

        jLabel8.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(239, 231, 190));
        jLabel8.setText("TRANSAKSI PINJAMAN");

        cbAngsur.setBackground(new java.awt.Color(239, 231, 190));
        cbAngsur.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        cbAngsur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3 bulan", "6 bulan", "12 bulan" }));
        cbAngsur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAngsurActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(239, 231, 190));
        jLabel14.setText("BIAYA ANGSUR");

        txtBiaya.setBackground(new java.awt.Color(239, 231, 190));
        txtBiaya.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        tablePinjam.setBackground(new java.awt.Color(239, 231, 190));
        tablePinjam.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        tablePinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Pinjaman", "No. anggota", "Tanggal ", "Jumlah Angsuran", "Lama Angsur", "Bunga", "Total Angsur", "Biaya Angsur"
            }
        ));
        jScrollPane2.setViewportView(tablePinjam);

        txtCari.setBackground(new java.awt.Color(239, 231, 190));
        txtCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 231, 190));
        jLabel1.setText("Cari:");

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

        btCariNomor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btCariNomor.setForeground(new java.awt.Color(239, 231, 190));
        btCariNomor.setText("Cari");
        btCariNomor.setContentAreaFilled(false);
        btCariNomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariNomorActionPerformed(evt);
            }
        });
        jPanel2.add(btCariNomor, "card2");

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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(txtNomor)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(257, 257, 257))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jSeparator2)
                            .addGap(590, 590, 590)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtBiaya)
                                    .addComponent(txtTotal)
                                    .addComponent(txtBunga)
                                    .addComponent(cbAngsur, 0, 219, Short.MAX_VALUE)
                                    .addComponent(txtJumlah)))
                            .addComponent(jLabel14)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cbAngsur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtBunga, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnggotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnggotaActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        if(txtNomor.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Pilih nomor pinjaman!");
        }else{
            simpan();
            bersih();
        }
        
    }//GEN-LAST:event_btSaveActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        aktif();
        bersih();
    }//GEN-LAST:event_btNewActionPerformed

    private void btCariNomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariNomorActionPerformed
        PopAkunPinjam.getPop().pin=this;
        PopAkunPinjam.getPop().setVisible(true);
    }//GEN-LAST:event_btCariNomorActionPerformed

    private void txtJumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(txtJumlah.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Masukan nominal pinjaman!");
            }else{
                hitungAngsur();
            }
        }
    }//GEN-LAST:event_txtJumlahKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNomor.setEnabled(false);
        txtAnggota.setEnabled(false);
        btCariNomor.setEnabled(false);
        txtTgl.setEnabled(false);
        cbAngsur.setEnabled(false);
        txtJumlah.setEnabled(false);
        txtBunga.setEnabled(false);
        txtTotal.setEnabled(false);
        txtBiaya.setEnabled(false);
        btSave.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
        btDelete.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void cbAngsurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAngsurActionPerformed
        txtJumlah.requestFocus();
    }//GEN-LAST:event_cbAngsurActionPerformed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void txtJumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtJumlahKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariNomor;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JComboBox<String> cbAngsur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tablePinjam;
    private javax.swing.JTextField txtAnggota;
    private javax.swing.JTextField txtBiaya;
    private javax.swing.JTextField txtBunga;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNomor;
    private javax.swing.JTextField txtTgl;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        btCariNomor.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        btSave.setEnabled(true);
        btDelete.setEnabled(true);
        txtJumlah.setEnabled(true);
        cbAngsur.setEnabled(true);
    }

    @Override
    public void bersih() {
        txtNomor.setText("");
        txtAnggota.setText("");
        txtTgl.setText("");
        txtJumlah.setText("");
        cbAngsur.setSelectedIndex(0);
        txtBunga.setText("");
        txtTotal.setText("");
        txtBiaya.setText("");
        txtCari.setText("");
        showTablePinjam();
        renderTable();
    }

    @Override
    public void simpan() {
        int pk=pCont.pk(txtNomor.getText());
        pinjam=pCont.findPinjaman(pk);
        Pinjaman pin=new Pinjaman();
        if(pinjam==null){
            pin.setNoPinjam(txtNomor.getText());
            pin.setTglPinjam(new Date());
            pin.setLamaAngsuran(lama);
            pin.setJumlahPinjaman(Double.parseDouble(txtJumlah.getText()));
            pin.setBunga(Double.parseDouble(txtBunga.getText()));
            pin.setTotalAngsuran(Double.parseDouble(txtTotal.getText()));
            pin.setBiayaAngsuran(Double.parseDouble(txtBiaya.getText()));
            pin.setKodeUser(kd);
            try{
                pCont.save(pin);
            }catch(Exception ex){}
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        }else{
            JOptionPane.showMessageDialog(null, "Data telah eksis!");
        }
    }

    @Override
    public void hapus() {
        try{
            int pk=pCont.pk(txtNomor.getText());
            pCont.delete(pk);
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
