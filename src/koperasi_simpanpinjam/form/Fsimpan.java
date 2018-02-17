/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.form;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import koperasi_simpanpinjam.Koperasi_SimpanPinjam;
import koperasi_simpanpinjam.control.SimpanTarikController;
import koperasi_simpanpinjam.data.Simpanan;

/**
 *
 * @author User
 */
public class Fsimpan extends javax.swing.JInternalFrame implements NavigatorFormInt{

    SimpanTarikController stCont =new SimpanTarikController(Koperasi_SimpanPinjam.emf);
    Simpanan simpanan=new Simpanan();
    DefaultTableModel model;
    long masuk=0, bunga=0, jumlah=0, tarik=0, tabungan=0, total=0;
    
    public String no, ang;
    String kd;
    /**
     * Creates new form simpan
     */
    public Fsimpan(String kode) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(null);
        model=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Nomor Simpanan");
        model.addColumn("Nomor Anggota");
        model.addColumn("Transaksi-ke");
        model.addColumn("Tanggal");
        model.addColumn("Simpanan");
        model.addColumn("Penarikan");
        model.addColumn("Total Simpanan");
        tableSimpan.getTableHeader().setFont(new Font("Leelawadee", Font.BOLD, 14));
        seleksiTable();
        kd=kode;
    }
    
    private void showTableSimpan(){
        tableSimpan.setModel(stCont.showTable(model));
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
        tableSimpan.getColumnModel().getColumn(3).setCellRenderer(tbr);
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=stCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableSimpan.setModel(stCont.cari(model, cari));
        }
    }

    private void seleksiTable(){
        tableSimpan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableSimpan.getSelectedRow();       //dapatkan baris yang terseleksi
                if(baris != -1){                        //jika baris bukan sama dengan -1 atau tidak ada
                txtNomor.setText(tableSimpan.getValueAt(baris, 0).toString());
                txtTransKe.setText(tableSimpan.getValueAt(baris, 2).toString());
                int pk=stCont.pk(txtNomor.getText(), (Integer.parseInt(txtTransKe.getText())));
                simpanan=stCont.findSimpanan(pk);
                txtAnggota.setText(tableSimpan.getValueAt(baris, 1).toString());
                SimpleDateFormat sdf=new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
                txtTgl.setText(sdf.format(simpanan.getTanggalSimpan()));
                if(simpanan.getPenarikan()==0){
                    ckSimpan.setSelected(true);
                    panel();
                    txtMasuk.setText(String.valueOf(Double.valueOf(simpanan.getMasukSimpanan()).longValue()));
                    txtBunga.setText(String.valueOf(Double.valueOf(simpanan.getBunga()).longValue()));
                    txtJumlah.setText(String.valueOf(Double.valueOf(simpanan.getJumlahSimpanan()).longValue()));
                }else if(simpanan.getMasukSimpanan()==0){
                    ckTarik.setSelected(true);
                    panel();
                    txtTarik.setText(String.valueOf(Double.valueOf(simpanan.getPenarikan()).longValue()));
                }
                txtTabungan.setText("--");
                txtTotal.setText(String.valueOf(Double.valueOf(simpanan.getTotalSimpanan()).longValue()));
                }
            }
        });
    }
    
    private void tanggal(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("in-ID"));
        Calendar cal=Calendar.getInstance();
        txtTgl.setText(sdf.format(cal.getTime()));
    }
    
    public void pilihAkun(){
        bersih();
        PopAkunSimpan PAS=new PopAkunSimpan();
        PAS.SIM=this;
        txtNomor.setText(no);
        txtAnggota.setText(ang);
        PopAkunSimpan.getPop().dispose();
        txtTransKe.setText(String.valueOf(stCont.transaksiKe(no)));
        tanggal();
    }
    
    private void panel(){
        if(ckSimpan.isSelected()==true){
            panelSimpan.setVisible(true);
            panelTarik.setVisible(false);
            tarik=0;
            tabungan=0;
            total=0;
            txtTarik.setText("");
            txtTabungan.setText("");
            txtTotal.setText("");
        }else if(ckTarik.isSelected()==true){
            panelSimpan.setVisible(false);
            panelTarik.setVisible(true);
            masuk=0;
            bunga=0;
            jumlah=0;
            tabungan=0;
            total=0;
            txtMasuk.setText("");
            txtBunga.setText("");
            txtJumlah.setText("");
            txtTabungan.setText("");
            txtTotal.setText("");
        }
    }
    
    private void masuk(){
        masuk=Long.parseLong(txtMasuk.getText());
        float b= (float) 0.005 * masuk;
        bunga=(long) b;
        jumlah=masuk-bunga;
        int pk=stCont.pk(no, (Integer.parseInt(txtTransKe.getText())-1));
        simpanan=stCont.findSimpanan(pk);
        Simpanan sim=new Simpanan();
        if(simpanan!=null){
            try{
                System.out.println("ada");
                sim=stCont.findSimpanan(pk);
                tabungan=Double.valueOf(sim.getTotalSimpanan()).longValue();
                total=tabungan+jumlah;
                txtTabungan.setText(String.valueOf(tabungan));
            }catch(Exception ex){}
        }else{
            System.out.println("kosong");
            total=total+jumlah;
            txtTabungan.setText(String.valueOf(tabungan));
            System.out.println(total);
        }
        txtBunga.setText(String.valueOf(bunga));
        txtJumlah.setText(String.valueOf(jumlah));
        txtTotal.setText(String.valueOf(total));
    }
    
    private void tarik(){
        int pk=stCont.pk(no, (Integer.parseInt(txtTransKe.getText())-1));
        simpanan=stCont.findSimpanan(pk);
        Simpanan sim=new Simpanan();
        if(simpanan!=null){
            try{
                sim=stCont.findSimpanan(pk);
                tabungan=Double.valueOf(sim.getTotalSimpanan()).longValue();
                tarik=Long.parseLong(txtTarik.getText());
                txtTabungan.setText(String.valueOf(tabungan));
                if(tarik>tabungan){
                    JOptionPane.showMessageDialog(null, "Jumlah penarikan kurang dari tabungan");
                    txtTarik.setText("");
                    txtTarik.requestFocus();
                }else{
                    total=tabungan-tarik;
                    txtTotal.setText(String.valueOf(total));
                }
            }catch(NoResultException ex){}
            
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

        cekGrup = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNomor = new javax.swing.JTextField();
        txtAnggota = new javax.swing.JTextField();
        txtTransKe = new javax.swing.JTextField();
        txtTgl = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSimpan = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        panelUtama = new javax.swing.JPanel();
        panelSimpan = new javax.swing.JPanel();
        txtBunga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMasuk = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        panelTarik = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTarik = new javax.swing.JTextField();
        ckSimpan = new javax.swing.JCheckBox();
        ckTarik = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        txtTabungan = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btCariSimpan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btCari = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();

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

        jLabel2.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(239, 231, 190));
        jLabel2.setText("NOMOR SIMPAN");

        jLabel3.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 231, 190));
        jLabel3.setText("NOMOR ANGGOTA");

        jLabel4.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(239, 231, 190));
        jLabel4.setText("TRANSAKSI KE");

        jLabel5.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(239, 231, 190));
        jLabel5.setText("TANGGAL TRANSAKSI");

        txtNomor.setBackground(new java.awt.Color(239, 231, 190));
        txtNomor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtAnggota.setBackground(new java.awt.Color(239, 231, 190));
        txtAnggota.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnggotaActionPerformed(evt);
            }
        });

        txtTransKe.setBackground(new java.awt.Color(239, 231, 190));
        txtTransKe.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        txtTgl.setBackground(new java.awt.Color(239, 231, 190));
        txtTgl.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        tableSimpan.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        tableSimpan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nomor Simpanan", "Nomor Anggota", "Transaksi-Ke", "Tanggal", "Simpanan", "Penarikan", "Total Simpan"
            }
        ));
        jScrollPane1.setViewportView(tableSimpan);

        jLabel8.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(239, 231, 190));
        jLabel8.setText("TRANSAKSI SIMPANAN");

        jLabel10.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(239, 231, 190));
        jLabel10.setText("TOTAL SIMPANAN");

        txtTotal.setBackground(new java.awt.Color(239, 231, 190));
        txtTotal.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 231, 190));
        jLabel1.setText("Cari:");

        txtCari.setBackground(new java.awt.Color(239, 231, 190));
        txtCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(239, 231, 190));

        panelUtama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 231, 190)));
        panelUtama.setLayout(new java.awt.CardLayout());

        panelSimpan.setBackground(new java.awt.Color(0, 38, 53));

        txtBunga.setBackground(new java.awt.Color(239, 231, 190));
        txtBunga.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(239, 231, 190));
        jLabel6.setText("JUMLAH MASUK");

        jLabel7.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(239, 231, 190));
        jLabel7.setText("BUNGA 0.5%");

        txtMasuk.setBackground(new java.awt.Color(239, 231, 190));
        txtMasuk.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMasukKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMasukKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(239, 231, 190));
        jLabel9.setText("JUMLAH SIMPANAN");

        txtJumlah.setBackground(new java.awt.Color(239, 231, 190));
        txtJumlah.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelSimpanLayout = new javax.swing.GroupLayout(panelSimpan);
        panelSimpan.setLayout(panelSimpanLayout);
        panelSimpanLayout.setHorizontalGroup(
            panelSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSimpanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBunga, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSimpanLayout.setVerticalGroup(
            panelSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSimpanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtBunga, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        panelUtama.add(panelSimpan, "card2");

        panelTarik.setBackground(new java.awt.Color(0, 38, 53));
        panelTarik.setForeground(new java.awt.Color(0, 38, 53));

        jLabel11.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(239, 231, 190));
        jLabel11.setText("JUMLAH PENARIKAN");

        txtTarik.setBackground(new java.awt.Color(239, 231, 190));
        txtTarik.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtTarik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarikActionPerformed(evt);
            }
        });
        txtTarik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTarikKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTarikKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelTarikLayout = new javax.swing.GroupLayout(panelTarik);
        panelTarik.setLayout(panelTarikLayout);
        panelTarikLayout.setHorizontalGroup(
            panelTarikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarikLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txtTarik, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTarikLayout.setVerticalGroup(
            panelTarikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTarikLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTarikLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTarik, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        panelUtama.add(panelTarik, "card3");

        ckSimpan.setBackground(new java.awt.Color(171, 26, 37));
        cekGrup.add(ckSimpan);
        ckSimpan.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        ckSimpan.setForeground(new java.awt.Color(239, 231, 190));
        ckSimpan.setText("Simpan Tunai");
        ckSimpan.setOpaque(false);
        ckSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckSimpanActionPerformed(evt);
            }
        });

        cekGrup.add(ckTarik);
        ckTarik.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        ckTarik.setForeground(new java.awt.Color(239, 231, 190));
        ckTarik.setText("Tarik Tunai");
        ckTarik.setOpaque(false);
        ckTarik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckTarikActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(239, 231, 190));
        jLabel13.setText("TABUNGAN");

        txtTabungan.setBackground(new java.awt.Color(239, 231, 190));
        txtTabungan.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtTabungan.setForeground(new java.awt.Color(239, 231, 190));

        jPanel1.setBackground(new java.awt.Color(171, 26, 37));
        jPanel1.setLayout(new java.awt.CardLayout());

        btCariSimpan.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btCariSimpan.setForeground(new java.awt.Color(239, 231, 190));
        btCariSimpan.setText("Cari");
        btCariSimpan.setContentAreaFilled(false);
        btCariSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(btCariSimpan, "card2");

        jPanel2.setBackground(new java.awt.Color(171, 26, 37));
        jPanel2.setLayout(new java.awt.CardLayout());

        btCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btCari.setForeground(new java.awt.Color(239, 231, 190));
        btCari.setText("Cari");
        btCari.setContentAreaFilled(false);
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel2.add(btCari, "card2");

        jPanel3.setBackground(new java.awt.Color(171, 26, 37));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

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
        jPanel3.add(btNew);

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
        jPanel3.add(btSave);

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
        jPanel3.add(btDelete);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ckSimpan)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAnggota)
                                    .addComponent(txtTgl)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtNomor, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ckTarik)
                                    .addComponent(txtTransKe)))
                            .addComponent(panelUtama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(78, 78, 78)
                                    .addComponent(txtTabungan, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTransKe, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTgl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckSimpan)
                            .addComponent(ckTarik))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelUtama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtTabungan, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 186, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        if(txtNomor.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Nomor simpanan masih kosong!");
        }else if(ckSimpan.isSelected()==true && txtMasuk.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Masukan jumlah simpanan!");
        }else if(ckTarik.isSelected()==true && txtTarik.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Masukan jumlah penarikan!");
        }else{
            simpan();
            bersih();
        }
        
    }//GEN-LAST:event_btSaveActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        aktif();
        bersih();
    }//GEN-LAST:event_btNewActionPerformed

    private void txtAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnggotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnggotaActionPerformed

    private void btCariSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariSimpanActionPerformed
        PopAkunSimpan.getPop().SIM=this;
        PopAkunSimpan.getPop().setVisible(true);
    }//GEN-LAST:event_btCariSimpanActionPerformed

    private void ckSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckSimpanActionPerformed
        panel();
    }//GEN-LAST:event_ckSimpanActionPerformed

    private void ckTarikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckTarikActionPerformed
        panel();
    }//GEN-LAST:event_ckTarikActionPerformed

    private void txtMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasukKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(txtAnggota.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Pilih nomor simpanan dulu!");
            }else{
                masuk();
            }
        }
    }//GEN-LAST:event_txtMasukKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNomor.setEnabled(false);
        btCariSimpan.setEnabled(false);
        txtAnggota.setEnabled(false);
        txtTransKe.setEnabled(false);
        txtTgl.setEnabled(false);
        ckSimpan.setEnabled(false);
        ckTarik.setEnabled(false);
        txtJumlah.setEnabled(false);
        txtBunga.setEnabled(false);
        txtMasuk.setEnabled(false);
        txtTarik.setEnabled(false);
        txtTabungan.setEnabled(false);
        txtTotal.setEnabled(false);
        btNew.setEnabled(true);
        btSave.setEnabled(false);
        btDelete.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtTarikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarikActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarikActionPerformed

    private void txtTarikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarikKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(txtAnggota.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Pilih nomor simpanan dulu!");
            }else{
                tarik();
            }
        }
    }//GEN-LAST:event_txtTarikKeyPressed

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void txtMasukKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMasukKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtMasukKeyTyped

    private void txtTarikKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTarikKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTarikKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btCariSimpan;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.ButtonGroup cekGrup;
    private javax.swing.JCheckBox ckSimpan;
    private javax.swing.JCheckBox ckTarik;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelSimpan;
    private javax.swing.JPanel panelTarik;
    private javax.swing.JPanel panelUtama;
    private javax.swing.JTable tableSimpan;
    private javax.swing.JTextField txtAnggota;
    private javax.swing.JTextField txtBunga;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtMasuk;
    private javax.swing.JTextField txtNomor;
    private javax.swing.JTextField txtTabungan;
    private javax.swing.JTextField txtTarik;
    private javax.swing.JTextField txtTgl;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTransKe;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        btCariSimpan.setEnabled(true);
        ckSimpan.setEnabled(true);
        ckTarik.setEnabled(true);
        txtMasuk.setEnabled(true);
        txtTarik.setEnabled(true);
        btSave.setEnabled(true);
        btDelete.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        ckSimpan.setSelected(true);
        
    }

    @Override
    public void bersih() {
        txtNomor.setText("");
        txtAnggota.setText("");
        txtTransKe.setText("");
        txtTgl.setText("");
        txtJumlah.setText("");
        txtBunga.setText("");
        txtMasuk.setText("");
        txtTarik.setText("");
        txtTabungan.setText("");
        txtTotal.setText("");
        txtCari.setText("");
        panel();
        tabungan=0;
        total=0;
        showTableSimpan();
        renderTable();
    }

    @Override
    public void simpan() {
        int pk=stCont.pk(txtNomor.getText(), (Integer.parseInt(txtTransKe.getText())));
        simpanan=stCont.findSimpanan(pk);
        Simpanan sim=new Simpanan();
        if(simpanan==null){
            sim.setNoSimpan(txtNomor.getText());
            sim.setTransaksiKe(Integer.parseInt(txtTransKe.getText()));
            sim.setTanggalSimpan(new Date());
            sim.setMasukSimpanan(Long.valueOf(masuk).doubleValue());
            sim.setBunga(Long.valueOf(bunga).doubleValue());
            sim.setJumlahSimpanan(Long.valueOf(jumlah).doubleValue());
            sim.setPenarikan(Long.valueOf(tarik).doubleValue());
            sim.setTotalSimpanan(total);
            sim.setKodeUser(kd);
            try{
                stCont.save(sim);
            }catch(Exception ex){}
            JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan!");
        }else{
            JOptionPane.showMessageDialog(null, "Simpan ditolak! Silahkan buat transaksi baru!");
            bersih();
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
