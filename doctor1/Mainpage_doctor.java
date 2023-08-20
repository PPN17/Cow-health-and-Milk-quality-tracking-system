/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package doctor1;

import connections.DatabaseConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mainpage1.*;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class Mainpage_doctor extends javax.swing.JFrame {

    /**
     * Creates new form Mainpage_doctor
     */
    private DatabaseConnection db;

    public Mainpage_doctor() {
        initComponents();
        db = new DatabaseConnection();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
         headerRenderer.setBackground(Color.decode("#6C4AB6"));
         jTable1.setBackground(Color.decode("#8D9EFF"));
         jTable1.setSelectionBackground(Color.decode("#B9E0FF"));
         jScrollPane1.getViewport().setBackground(Color.decode("#8D72E1"));       
        headerRenderer.setPreferredSize(new Dimension(100, 40));
        headerRenderer.setForeground(Color.BLACK);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);      
        jTable1.setRowHeight(30);
        jTable1.setDefaultRenderer(Object.class, centerRenderer);
        jTable1.getTableHeader().setDefaultRenderer(headerRenderer);
        jTable1.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 17));
        jTabbedPane4.setVisible(false);
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (jTable1.getSelectedRow() > -1) {
                    int row = jTable1.getSelectedRow();
                    String manuId = (String) jTable1.getValueAt(row, 0);
                    String itemNo = (String) jTable1.getValueAt(row, 1);
                    jTextField2.setText(manuId);
                    jTextField3.setText(itemNo);

                }
            }
        });

//        jComboBox1.setSelectedIndex(0); 
        jComboBox1.setVisible(false);

        jDateChooser1.addPropertyChangeListener("date", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    Connection connection = getNewConnection();
                    String doctorId = jTextField1.getText();
                    java.util.Date date = jDateChooser1.getDate();
                    PreparedStatement checkPS = connection.prepareStatement("SELECT * FROM Reportdate WHERE Report_Date = ? and Doctor_Id = ?");
                    checkPS.setDate(1, new java.sql.Date(date.getTime()));
                    checkPS.setString(2, doctorId);
                    ResultSet checkRS = checkPS.executeQuery();
                    System.out.println("HI");
                    if (checkRS.next()) {
                        jComboBox1.setVisible(true);
                        PreparedStatement fetchReportIdPS = connection.prepareStatement("SELECT Report_Id FROM Reportdate WHERE Doctor_Id = ? and Report_Date = ?");
                        fetchReportIdPS.setString(1, doctorId);
                        fetchReportIdPS.setDate(2, new java.sql.Date(date.getTime()));
                        ResultSet fetchReportIdRS = fetchReportIdPS.executeQuery();
                        System.out.println("HI");
                        jComboBox1.removeAllItems();
                        jComboBox1.addItem("Select ReportId");
                        while (fetchReportIdRS.next()) {
                            String reportId = fetchReportIdRS.getString("Report_Id");
                            jComboBox1.addItem(reportId);

                        }
                    } else {
                        jComboBox1.setVisible(false);
                        jComboBox1.removeAllItems();
                        jTextField5.setText(" ");
                        jTextField6.setText(" ");
                        jTextField7.setText(" ");
                        jTextField8.setText(" ");
                        jTextField9.setText(" ");
                         jTextField10.setText(" ");
                          jTextField11.setText(" ");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        /////
        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object selectedItem = e.getItem();
                    if (!selectedItem.equals("Select ReportId")) {
                        String reportId = (String) selectedItem;
                        try {
                            Connection connection = getNewConnection();
                            PreparedStatement fetchReportPS = connection.prepareStatement("SELECT User_Id, Fat, SNF, CLR FROM Report WHERE Report_Id = ?");
                            fetchReportPS.setString(1, reportId);
                            ResultSet fetchReportRS = fetchReportPS.executeQuery();
                            if (fetchReportRS.next()) {
                                String userId = fetchReportRS.getString("User_Id");
                                jTextField5.setText(userId);
                                String doctorId = jTextField1.getText();
                                jTextField8.setText(doctorId);
                                double fat = fetchReportRS.getDouble("Fat");
                                jTextField9.setText(String.valueOf(fat));
                                double snf = fetchReportRS.getDouble("SNF");
                                jTextField10.setText(String.valueOf(snf));
                                double clr = fetchReportRS.getDouble("CLR");
                                jTextField11.setText(String.valueOf(clr));
                                PreparedStatement fetchUserNamePS = connection.prepareStatement("SELECT User_Name FROM Dairyfarmer WHERE User_Id = ?");
                                fetchUserNamePS.setString(1, userId);
                                ResultSet fetchUserNameRS = fetchUserNamePS.executeQuery();
                                if (fetchUserNameRS.next()) {
                                    String userName = fetchUserNameRS.getString("User_Name");
                                    jTextField6.setText(userName);
                                    jTextField7.setText(reportId);
                                }
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }

            }
        });
        /////

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel24 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-thick-long-left-arrow-70.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 70));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel2.setText("DOCTOR ID :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jTextField1.setToolTipText("Enter Doctor Id ");
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 200, 40));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("HELLO DOCTOR!!");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 30, 490, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-medical-report-100.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 120, 120));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel5.setText("REPORT");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, -1, -1));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        jPasswordField1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jPasswordField1.setToolTipText("Enter Doctor Password");
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 200, 40));

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel24.setText(" PASSWORD :");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/doctor poster.jpg"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -70, 430, 840));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 430, 640));
        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, -1, -1));
        jPanel1.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));
        jPanel1.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, -1, -1));

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane4.setEnabled(false);

        jPanel3.setBackground(new java.awt.Color(153, 255, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel8.setText("REPORT !");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 160, 30));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setText("Date :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("User id :");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setText("User name :");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel12.setText("Report id :");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel13.setText("Doctor id :");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, -1, -1));

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel14.setText("Fat :");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("SNF :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, -1, -1));

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel15.setText("CLR :");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, -1, -1));

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 0));
        jLabel16.setText("Suggestion :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-cow-100.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 530, -1, 110));

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel17.setText("Feed manufacturer");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 650, -1, -1));

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 170, 40));

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 170, 40));

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jPanel3.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 170, 40));

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jPanel3.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 170, 40));

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 170, 30));

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, 170, 40));

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jPanel3.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 490, 170, 30));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/patient-medical-records.png"))); // NOI18N
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 510, 390));

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel25.setText("Fat :");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel26.setText("Fat :");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel27.setText("Fat :");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, -1, -1));
        jPanel3.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 82, 170, 40));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 160, 40));

        jTabbedPane4.addTab("", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 102));
        jPanel4.setForeground(new java.awt.Color(255, 255, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Manufacture_Id", "Item No", "Item Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 580, 340));

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel19.setText("Manufacture_id :");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 160, 30));

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel20.setText("Item no :");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 600, 80, 20));

        jTextField2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jPanel4.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 110, 30));

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel4.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 110, 30));

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-ok-70.png"))); // NOI18N
        jLabel21.setText("OK!!");
        jLabel21.setToolTipText("OK");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 140, 100));

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-home-70.png"))); // NOI18N
        jLabel22.setText("HOME");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 530, 80, 100));

        jPanel5.setBackground(new java.awt.Color(255, 153, 0));

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("GIVE SUGGESTION :");
        jPanel5.add(jLabel18);

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 340, 190));

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("List of Items ");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 900, -1));

        jPanel6.setBackground(new java.awt.Color(255, 102, 102));
        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 830, 440));

        jTabbedPane4.addTab("", jPanel4);

        jPanel1.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 900, 720));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/doctorposter2.jpg"))); // NOI18N
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 800));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        setVisible(false);
        new Mainpage().setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    Connection conn = null;

    private Connection getNewConnection() {
        conn = db.getConnection();
        return conn;
    }
    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        if (jTextField2.getText().trim().isEmpty() || jTextField3.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "These fields cannot be empty, If no suggestion type  \"none\"");
        } else {
            try {
    Connection connection = getNewConnection();
    PreparedStatement insertPS = connection.prepareStatement("INSERT INTO suggestion (Report_Id, Manu_Id, Item_No) VALUES (?, ?, ?)");
    insertPS.setString(1, jTextField7.getText());
    insertPS.setString(2, jTextField2.getText());
    insertPS.setString(3, jTextField3.getText());
    insertPS.executeUpdate();
    System.out.println("hellobye3");
    JOptionPane.showMessageDialog(null, "Suggestion Submitted");
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Suggestion for this Report_Id is already submitted,Contact depot for further querry");

    ex.printStackTrace();
}

                 // TODO add your handling code here:
    }//GEN-LAST:event_jLabel21MouseClicked
    }
    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     if(jTextField5.getText().isEmpty()||jTextField6.getText().isEmpty()||jTextField7.getText().isEmpty()||jTextField8.getText().isEmpty()||jTextField9.getText().isEmpty()||jTextField10.getText().isEmpty()||jTextField11.getText().isEmpty())
     {
        JOptionPane.showMessageDialog(null,"Fields cannot be empty"); 
     }
     else{
        try {
            jTabbedPane4.setSelectedIndex(1);
            String manuId = jTextField1.getText();
            conn = getNewConnection();
            PreparedStatement stmt;
            // Fetch the items from the Feedman2 table
            stmt = conn.prepareStatement("SELECT Manu_Id, Item_No, Item_Name FROM Feedman2 ");
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                String manu_id = rs.getString("Manu_Id");
                String item_no = rs.getString("Item_No");
                String item_name = rs.getString("Item_Name");
                model.addRow(new Object[]{manu_id, item_no, item_name});
                jPanel2.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        if (jTextField1.getText().trim().isEmpty() || jPasswordField1.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "These fields cannot be empty");
        } else {
            try {
                String doctorId = jTextField1.getText();
                String password1 = new String(jPasswordField1.getPassword());
                conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Doctor WHERE Doctor_Id = ? and Password=? ");
                stmt.setString(1, doctorId);
                stmt.setString(2, password1);
                ResultSet rs = stmt.executeQuery();
            
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "Doctor Id doesn't exist ,Recheck DoctorId or Password!!.");
                    jTextField1.setText("");
                    jPasswordField1.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Logged in Successfully" );
                    jTabbedPane4.setVisible(true);
                    jTabbedPane4.setSelectedIndex(0);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "An error occurred while checking the Doctor Id.\n" + e.getMessage());
            }

        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        setVisible(false);
        Mainpage mp = new Mainpage();
        mp.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mainpage_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mainpage_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mainpage_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mainpage_doctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mainpage_doctor().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
