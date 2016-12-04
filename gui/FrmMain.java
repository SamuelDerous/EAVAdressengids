/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import creatie.EncryptionIni;
import creatie.Initialisatie;
import databank.DataAdapter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author zenodotus
 */
public class FrmMain extends javax.swing.JFrame {
    
    private String dbHost;
    private String dbGebruiker;
    private String dbWachtwoord;
    
    private boolean annul;
    
    private DataAdapter drm;
    

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
        try {
            if (Initialisatie.getGegeven("DBWachtwoord") == null || Initialisatie.getGegeven("DBWachtwoord").equals("")) {
               
                final DlgLogin dialog = new DlgLogin(new JFrame(), true);
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.setVisible(false);
                        dialog.dispose();
                    }
                });
                annul = dialog.showDialog();
            
            }
            dbHost = Initialisatie.getGegeven("DBHost");
            dbGebruiker = Initialisatie.getGegeven("DBGebruikersnaam");
            dbWachtwoord = EncryptionIni.decrypt(Initialisatie.getGegeven("DBWachtwoord"));
            drm = new DataAdapter(dbHost, dbGebruiker, dbWachtwoord);
            if(drm.getConnection() != null) {
                btnVoorkeuren.setEnabled(true);
                btnRapporten.setEnabled(true);
                btnBrieven.setEnabled(true);
                btnToevoegen.setEnabled(true);
                lblInloggen.setVisible(false);
                lblUitloggen.setVisible(true);
                lblWelkomst.setVisible(true);
                lblWelkomst.setText("Welkom, " + dbGebruiker);
            } else {
                btnToevoegen.setEnabled(false);
                btnVoorkeuren.setEnabled(false);
                btnRapporten.setEnabled(false);
                btnBrieven.setEnabled(false);
                lblInloggen.setVisible(true);
                lblUitloggen.setVisible(false);
                lblWelkomst.setVisible(false);

            }
        } 
        
        
        catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Er is iets ernstig fout gegaan. Het programma wordt afgesloten", "Ernstige fout", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } 
        setLocationRelativeTo(null);
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInloggen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnToevoegen = new javax.swing.JButton();
        btnRapporten = new javax.swing.JButton();
        btnBrieven = new javax.swing.JButton();
        btnVoorkeuren = new javax.swing.JButton();
        lblUitloggen = new javax.swing.JLabel();
        lblWelkomst = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EAVAdressengids");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInloggen.setForeground(java.awt.SystemColor.activeCaption);
        lblInloggen.setText("Inloggen");
        lblInloggen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblInloggen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInloggenMouseClicked(evt);
            }
        });
        getContentPane().add(lblInloggen, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        jPanel1.setLayout(new java.awt.GridLayout(2, 2, 1, 0));

        btnToevoegen.setText("Toevoegen/aanpassen");
        btnToevoegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToevoegenActionPerformed(evt);
            }
        });
        jPanel1.add(btnToevoegen);

        btnRapporten.setText("Rapporten/mailings");
        btnRapporten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRapportenActionPerformed(evt);
            }
        });
        jPanel1.add(btnRapporten);

        btnBrieven.setText("Brieflijsten");
        btnBrieven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrievenActionPerformed(evt);
            }
        });
        jPanel1.add(btnBrieven);

        btnVoorkeuren.setText("Voorkeuren");
        btnVoorkeuren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoorkeurenActionPerformed(evt);
            }
        });
        jPanel1.add(btnVoorkeuren);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 570, 220));

        lblUitloggen.setForeground(java.awt.SystemColor.activeCaption);
        lblUitloggen.setText("Uitloggen");
        lblUitloggen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblUitloggen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUitloggenMouseClicked(evt);
            }
        });
        getContentPane().add(lblUitloggen, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));
        getContentPane().add(lblWelkomst, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 170, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/afbeeldingen/belgië.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnToevoegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToevoegenActionPerformed
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final FrmToevoegen dialog = new FrmToevoegen();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.setVisible(false);
                    }
                });
                dialog.setVisible(true);
                
            }
        });
    }//GEN-LAST:event_btnToevoegenActionPerformed

    private void btnRapportenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRapportenActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final FrmAfdrukken dialog = new FrmAfdrukken();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.setVisible(false);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_btnRapportenActionPerformed

    private void lblInloggenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInloggenMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final DlgLogin dialog = new DlgLogin(new JFrame(), true);
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        dialog.setVisible(false);
                        dialog.dispose();
                    }
                });
                annul = dialog.showDialog();
            }
        });
    }//GEN-LAST:event_lblInloggenMouseClicked

    private void btnBrievenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrievenActionPerformed
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBrieflijsten().setVisible(true);
            }
        });
    }//GEN-LAST:event_btnBrievenActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
       try {
            if(annul) {
                dbHost = Initialisatie.getGegeven("DBHost");
                dbGebruiker = Initialisatie.getGegeven("DBGebruikersnaam");
                dbWachtwoord = EncryptionIni.decrypt(Initialisatie.getGegeven("DBWachtwoord"));
            } else {
                dbHost = "";
                dbGebruiker = "";
                dbWachtwoord = "";
            }
                drm = new DataAdapter(dbHost, dbGebruiker, dbWachtwoord);
                if(drm.getConnection() != null) {
                    btnVoorkeuren.setEnabled(true);
                    btnRapporten.setEnabled(true);
                    btnBrieven.setEnabled(true);
                    btnToevoegen.setEnabled(true);
                    lblInloggen.setVisible(false);
                    lblUitloggen.setVisible(true);
                    lblWelkomst.setVisible(true);
                    lblWelkomst.setText("Welkom, " + dbGebruiker);
                } else {
                    btnToevoegen.setEnabled(false);
                    btnVoorkeuren.setEnabled(false);
                    btnRapporten.setEnabled(false);
                    btnBrieven.setEnabled(false);
                    lblInloggen.setVisible(true);
                    lblUitloggen.setVisible(false);
                    lblWelkomst.setVisible(false);

            }
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Er is iets ernstig fout gegaan. Het programma wordt afgesloten", "Ernstige fout", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(1);
        }
        
    }//GEN-LAST:event_formFocusGained

    
    private void lblUitloggenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUitloggenMouseClicked
        btnToevoegen.setEnabled(false);
        btnVoorkeuren.setEnabled(false);
        btnRapporten.setEnabled(false);
        btnBrieven.setEnabled(false);
        lblInloggen.setVisible(true);
        lblUitloggen.setVisible(false);
        lblWelkomst.setVisible(false);
    }//GEN-LAST:event_lblUitloggenMouseClicked

    private void btnVoorkeurenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoorkeurenActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVoorkeuren().setVisible(true);
            }
        });
    }//GEN-LAST:event_btnVoorkeurenActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrieven;
    private javax.swing.JButton btnRapporten;
    private javax.swing.JButton btnToevoegen;
    private javax.swing.JButton btnVoorkeuren;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblInloggen;
    private javax.swing.JLabel lblUitloggen;
    private javax.swing.JLabel lblWelkomst;
    // End of variables declaration//GEN-END:variables
}
