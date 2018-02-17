/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koperasi_simpanpinjam.form;

import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
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
import koperasi_simpanpinjam.control.AnggotaController;
import koperasi_simpanpinjam.data.Anggota;

/**
 *
 * @author User
 */
public class anggota extends javax.swing.JInternalFrame implements NavigatorFormInt{

    AnggotaController aCont=new AnggotaController(Koperasi_SimpanPinjam.emf);
    Anggota anggota=new Anggota();
    DefaultTableModel model;
    String foto="Tidak Ada", KK="Tidak Ada", BPKB="Tidak Ada";
    /**
     * Creates new form anggota
     */
    public anggota() {
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
        model.addColumn("Pekerjaan");
        model.addColumn("Tanggal Lahir");
        seleksiTable();
        tableAnggota.getTableHeader().setFont(new Font("Leelawadee", Font.BOLD, 14));
    }
    
    private void setJDC(JDateChooser JDC){
        JDC.setLocale(Locale.forLanguageTag("in-ID")); //membuat locale indonesia
        JDC.setDateFormatString("EEEE, dd MMMM yyyy");
        JDC.setMaxSelectableDate(new Date());
        JDC.setDate(new Date());
    }
    
    private void showTableAnggota(){
        tableAnggota.setModel(aCont.showTable(model));
    }
    
    public void renderTable(){
        TableCellRenderer tbr = new DefaultTableCellRenderer(){
            SimpleDateFormat sdf=new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("In-ID"));
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column){
                if(value instanceof Date){
                    value = sdf.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tableAnggota.getColumnModel().getColumn(3).setCellRenderer(tbr);
    }
    
    private void cariTable(String cari){
        DefaultTableModel x=aCont.cari(model, cari);
        if(x.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }else{
            tableAnggota.setModel(aCont.cari(model, cari));
        }
    }
    
    private void seleksiTable(){
        tableAnggota.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int baris=tableAnggota.getSelectedRow();       //dapatkan baris yang terseleksi
                if(baris != -1){                        //jika baris bukan sama dengan -1 atau tidak ada
                    String kd=tableAnggota.getValueAt(baris, 0).toString();
                    anggota=aCont.findAnggota(kd);
                    txtNomor.setText(anggota.getNoAnggota());
                    txtNama.setText(anggota.getNama());
                    txtKtp.setText(String.valueOf(anggota.getNoKtp()));
                    txtKerja.setText(anggota.getPekerjaan());
                    txtTelp.setText(String.valueOf(anggota.getNoTelp()));
                    txtTempat.setText(anggota.getTempatLahir());
                    txtAlamat.setText(anggota.getAlamat());
                    jdcTglLahir.setDate(anggota.getTglLahir());
                    
                    if(anggota.getFotoNasabah().equalsIgnoreCase("Ada")){
                        ckFoto.setSelected(true);
                        foto();
                    }else{
                        ckFoto.setSelected(false);
                        foto();
                    }
                    
                    if(anggota.getFcKartukeluarga().equalsIgnoreCase("Ada")){
                        ckKK.setSelected(true);
                        KK();
                    }else{
                        ckKK.setSelected(false);
                        KK();
                    }
                    
                    if(anggota.getBpkb().equalsIgnoreCase("Ada")){
                        ckBPKB.setSelected(true);
                        BPKB();
                    }else{
                        ckBPKB.setSelected(false);
                        BPKB();
                    }
                    
                    
                    
                    
                }
            }
        });
    }
    
    private void foto(){
        if(ckFoto.isSelected()==true){
            foto="Ada";
        }else{
            foto="Tidak Ada";
        }
    }
    
    private void KK(){
        if(ckKK.isSelected()==true){
            KK="Ada";
        }else{
            KK="Tidak Ada";
        }
    }
    
    private void BPKB(){
        if(ckBPKB.isSelected()==true){
            BPKB="Ada";
        }else{
            BPKB="Tidak Ada";
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
        txtNomor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAnggota = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtKtp = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtKerja = new javax.swing.JTextField();
        txtTempat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jdcTglLahir = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        ckKK = new javax.swing.JCheckBox();
        ckFoto = new javax.swing.JCheckBox();
        ckBPKB = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTelp = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btCari = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btDelete = new javax.swing.JButton();
        btSave = new javax.swing.JButton();

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
        jLabel8.setText("ANGGOTA KOPERASI");

        txtNomor.setBackground(new java.awt.Color(239, 231, 190));
        txtNomor.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtNomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomorActionPerformed(evt);
            }
        });

        tableAnggota.setBackground(new java.awt.Color(239, 231, 190));
        tableAnggota.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        tableAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Anggota", "Nama Anggota", "Pekerjaan", "Tanggal Lahir"
            }
        ));
        jScrollPane1.setViewportView(tableAnggota);

        jLabel3.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(239, 231, 190));
        jLabel3.setText("NOMOR ANGGOTA");

        jLabel14.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(239, 231, 190));
        jLabel14.setText("NAMA ANGGOTA");

        txtNama.setBackground(new java.awt.Color(239, 231, 190));
        txtNama.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(239, 231, 190));
        jLabel15.setText("NO. KTP");

        txtKtp.setBackground(new java.awt.Color(239, 231, 190));
        txtKtp.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtKtp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKtpActionPerformed(evt);
            }
        });
        txtKtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKtpKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(239, 231, 190));
        jLabel16.setText("PEKERJAAN");

        jLabel17.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(239, 231, 190));
        jLabel17.setText("TEMPAT LAHIR");

        txtKerja.setBackground(new java.awt.Color(239, 231, 190));
        txtKerja.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtKerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKerjaActionPerformed(evt);
            }
        });

        txtTempat.setBackground(new java.awt.Color(239, 231, 190));
        txtTempat.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtTempat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempatActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(239, 231, 190));
        jLabel18.setText("TANGGAL LAHIR");

        jdcTglLahir.setBackground(new java.awt.Color(239, 231, 190));
        jdcTglLahir.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jdcTglLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jdcTglLahirKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(239, 231, 190));
        jLabel19.setText("ALAMAT");

        txtAlamat.setBackground(new java.awt.Color(239, 231, 190));
        txtAlamat.setColumns(20);
        txtAlamat.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtAlamat.setRows(5);
        jScrollPane2.setViewportView(txtAlamat);

        ckKK.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        ckKK.setForeground(new java.awt.Color(239, 231, 190));
        ckKK.setText("KARTU KELUARGA");
        ckKK.setContentAreaFilled(false);
        ckKK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckKKActionPerformed(evt);
            }
        });

        ckFoto.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        ckFoto.setForeground(new java.awt.Color(239, 231, 190));
        ckFoto.setText("FOTO NASABAH");
        ckFoto.setContentAreaFilled(false);
        ckFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckFotoActionPerformed(evt);
            }
        });

        ckBPKB.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        ckBPKB.setForeground(new java.awt.Color(239, 231, 190));
        ckBPKB.setText("BPKB");
        ckBPKB.setContentAreaFilled(false);
        ckBPKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckBPKBActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(239, 231, 190));
        jLabel20.setText("LAMPIRAN");

        jLabel1.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(239, 231, 190));
        jLabel1.setText("Cari:");

        txtCari.setBackground(new java.awt.Color(239, 231, 190));
        txtCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(239, 231, 190));
        jLabel21.setText("NO. TELEPON");

        txtTelp.setBackground(new java.awt.Color(239, 231, 190));
        txtTelp.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        txtTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelpActionPerformed(evt);
            }
        });
        txtTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelpKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(171, 26, 37));
        jPanel1.setLayout(new java.awt.CardLayout());

        btCari.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        btCari.setForeground(new java.awt.Color(239, 231, 190));
        btCari.setText("Cari");
        btCari.setContentAreaFilled(false);
        btCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCariActionPerformed(evt);
            }
        });
        jPanel1.add(btCari, "card2");

        jPanel2.setBackground(new java.awt.Color(171, 26, 37));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

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
        jPanel2.add(btNew);

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
        jPanel2.add(btDelete);

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
        jPanel2.add(btSave);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNomor, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(txtNama)
                                    .addComponent(txtKtp)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel21))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtKerja)
                                    .addComponent(txtTelp)
                                    .addComponent(txtTempat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jdcTglLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ckFoto)
                                            .addComponent(ckKK)
                                            .addComponent(ckBPKB))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtNomor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtKtp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtKerja, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtTempat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19)
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(ckFoto)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jdcTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckKK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ckBPKB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(137, 137, 137))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        aktif();
    }//GEN-LAST:event_btNewActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        if(txtNama.getText().equalsIgnoreCase("")&&txtKtp.getText().equalsIgnoreCase("")&&txtKerja.getText().equalsIgnoreCase("")&&
                txtTempat.getText().equalsIgnoreCase("")&&txtAlamat.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Data masih kosong!");
            txtNama.requestFocus();
        }else if(txtNama.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Nama kosong!");
            txtNama.requestFocus();
        }else if(txtKtp.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Ktp kosong!");
            txtKtp.requestFocus();
        }else if(txtKerja.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Pekerjaan kosong!");
            txtKerja.requestFocus();
        }else if(txtTelp.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Telepon kosong!");
            txtTelp.requestFocus();
        }else if(txtTempat.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Tempat lahir kosong!");
            txtTempat.requestFocus();
        }else if(jdcTglLahir.getDate()==null){
            JOptionPane.showMessageDialog(null, "Tanggal lahir tidak valid!");
            jdcTglLahir.requestFocus();
        }else if(txtAlamat.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Alamat kosong!");
            txtAlamat.requestFocus();
        }else{
            simpan();
            System.out.println(foto);
            System.out.println(KK);
            System.out.println(BPKB);
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        
    }//GEN-LAST:event_btDeleteActionPerformed

    private void txtNomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomorActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtKtpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKtpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKtpActionPerformed

    private void txtKerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKerjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKerjaActionPerformed

    private void txtTempatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempatActionPerformed

    private void ckKKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckKKActionPerformed
        KK();
        //System.out.println(KK);
    }//GEN-LAST:event_ckKKActionPerformed

    private void ckFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckFotoActionPerformed
        foto();
        //System.out.println(foto);
    }//GEN-LAST:event_ckFotoActionPerformed

    private void ckBPKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckBPKBActionPerformed
        BPKB();
        //System.out.println(BPKB);
    }//GEN-LAST:event_ckBPKBActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txtNomor.setEnabled(false);
        txtNama.setEnabled(false);
        txtKtp.setEnabled(false);
        txtKerja.setEnabled(false);
        txtTelp.setEnabled(false);
        txtTempat.setEnabled(false);
        jdcTglLahir.setEnabled(false);
        txtAlamat.setEnabled(false);
        ckFoto.setEnabled(false);
        ckKK.setEnabled(false);
        ckBPKB.setEnabled(false);
        txtCari.setEnabled(false);
        btCari.setEnabled(false);
        btNew.setEnabled(true);
        btSave.setEnabled(false);
        btDelete.setEnabled(false);
    }//GEN-LAST:event_formInternalFrameActivated

    private void btCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCariActionPerformed
        cariTable(txtCari.getText());
    }//GEN-LAST:event_btCariActionPerformed

    private void txtTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelpActionPerformed

    private void txtKtpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKtpKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtKtpKeyTyped

    private void txtTelpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelpKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtTelpKeyTyped

    private void jdcTglLahirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jdcTglLahirKeyTyped
        char karakter = evt.getKeyChar();
        if(!(((karakter == KeyEvent.VK_BACKSPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jdcTglLahirKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCari;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btSave;
    private javax.swing.JCheckBox ckBPKB;
    private javax.swing.JCheckBox ckFoto;
    private javax.swing.JCheckBox ckKK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser jdcTglLahir;
    private javax.swing.JTable tableAnggota;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtKerja;
    private javax.swing.JTextField txtKtp;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNomor;
    private javax.swing.JTextField txtTelp;
    private javax.swing.JTextField txtTempat;
    // End of variables declaration//GEN-END:variables

    @Override
    public void aktif() {
        txtNama.setEnabled(true);
        txtKtp.setEnabled(true);
        txtKerja.setEnabled(true);
        txtTelp.setEnabled(true);
        txtTempat.setEnabled(true);
        jdcTglLahir.setEnabled(true);
        txtAlamat.setEnabled(true);
        ckFoto.setEnabled(true);
        ckKK.setEnabled(true);
        ckBPKB.setEnabled(true);
        txtCari.setEnabled(true);
        btCari.setEnabled(true);
        btSave.setEnabled(true);
        btDelete.setEnabled(true);
        bersih();
        renderTable();
    }

    @Override
    public void bersih() {
        txtNama.setText("");
        txtKtp.setText("");
        txtKerja.setText("");
        txtTelp.setText("");
        txtTempat.setText("");
        setJDC(jdcTglLahir);
        txtAlamat.setText("");
        ckFoto.setSelected(false);
        ckKK.setSelected(false);
        ckBPKB.setSelected(false);
        txtCari.setText("");
        txtNomor.setText(aCont.nomorOtomatis());
        showTableAnggota();
        
    }

    @Override
    public void simpan() {
        anggota=aCont.findAnggota(txtNomor.getText());
        Anggota ag=new Anggota();
        if(anggota==null){
            ag.setNoAnggota(txtNomor.getText());
            ag.setNama(txtNama.getText());
            ag.setNoKtp(Integer.parseInt(txtKtp.getText()));
            ag.setPekerjaan(txtKerja.getText());
            ag.setNoTelp(Integer.parseInt(txtTelp.getText()));
            ag.setTempatLahir(txtTempat.getText());
            ag.setTglLahir(jdcTglLahir.getDate());
            ag.setAlamat(txtAlamat.getText());
            ag.setFotoNasabah(foto);
            ag.setFcKartukeluarga(KK);
            ag.setBpkb(BPKB);
            try{
                aCont.save(ag);
            }catch(Exception ex){}
            JOptionPane.showMessageDialog(null, "Simpan berhasil!");
        }else{
            ag.setNoAnggota(txtNomor.getText());
            ag.setNama(txtNama.getText());
            ag.setNoKtp(Integer.parseInt(txtKtp.getText()));
            ag.setPekerjaan(txtKerja.getText());
            ag.setNoTelp(Integer.parseInt(txtTelp.getText()));
            ag.setTempatLahir(txtTempat.getText());
            ag.setTglLahir(jdcTglLahir.getDate());
            ag.setAlamat(txtAlamat.getText());
            ag.setFotoNasabah(foto);
            ag.setFcKartukeluarga(KK);
            ag.setBpkb(BPKB);
            try{
                aCont.update(ag);
            }catch(Exception ex){}
            JOptionPane.showMessageDialog(null, "Update berhasil!");
        }
        bersih();
    }

    @Override
    public void hapus() {
        try{
            aCont.delete(txtNomor.getText());
            JOptionPane.showMessageDialog(null, "Data telah di hapus!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data tidak bisa di hapus karena "+ex.getMessage());
        }
        bersih();
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
