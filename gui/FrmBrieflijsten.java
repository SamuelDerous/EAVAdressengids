/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import databank.DataAdapter;
import databank.dao.CodesDao;
import databank.dao.DenominDao;
import databank.dao.GemorgDao;
import databank.dao.VerantwDao;
import databank.pojo.Codes;
import databank.pojo.Denomin;
import databank.pojo.Gemorg;
import databank.pojo.Verantw;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Element;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author zenodotus
 */
public class FrmBrieflijsten extends javax.swing.JFrame {

    private DataAdapter drm = new DataAdapter();
    
   
    /**
     * Creates new form FrmBrieflijsten
     */
    public FrmBrieflijsten() {
        
        initComponents();
        
        for(int i=0;i<trBrieflijsten.getRowCount();i++) {
            trBrieflijsten.expandRow(i);
        }
        ImageIcon leafIcon = new ImageIcon(getClass().getResource("/afbeeldingen/kerk.png"));
        ImageIcon rootIcon = new ImageIcon(getClass().getResource("/afbeeldingen/person.png"));
        
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(leafIcon);
        renderer.setOpenIcon(rootIcon);
        renderer.setClosedIcon(rootIcon);
        trBrieflijsten.setCellRenderer(renderer);
        this.setLocationRelativeTo(null);



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
        jScrollPane2 = new javax.swing.JScrollPane();
        trBrieflijsten = new javax.swing.JTree();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtUitvoer = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Brieflijsten");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Kerken");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Alle kerken");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Leden");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Per Regio");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Per Denominatie");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Organisaties");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Alle organisaties");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Leden");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Per Regio");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Personen");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Alle personen");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Per Kerk");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Per Regio");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        trBrieflijsten.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        trBrieflijsten.setRootVisible(false);
        trBrieflijsten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trBrieflijstenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(trBrieflijsten);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );

        txtUitvoer.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(txtUitvoer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trBrieflijstenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trBrieflijstenMouseClicked
        TreePath tp = trBrieflijsten.getSelectionPath();
        MutableTreeNode selectNode = (MutableTreeNode) tp.getLastPathComponent();
        MutableTreeNode parent = (MutableTreeNode) tp.getPathComponent(1);
        String ouder = parent.toString();
        System.out.println(parent.toString());
        String treenode = selectNode.toString();
        if (tp != null) {
            if (treenode.equalsIgnoreCase("Alle kerken")) {
                GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                List<Gemorg> kerken = gemorgDao.geefAlleKerken("G");
                if(kerken.isEmpty()) {
                    JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                } else {
                    createBrieflijstenKerken((ArrayList) kerken);
                    vulLijstKerken(kerken);
                }

            } else if (treenode.equalsIgnoreCase("leden") && ouder.equalsIgnoreCase("Kerken")) {
                GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                List<Gemorg> kerken = gemorgDao.geefAlleLeden("G");
                if(kerken.isEmpty()) {
                    JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                } else {
                    createBrieflijstenKerken((ArrayList) kerken);
                    vulLijstKerken(kerken);
                }
            } else if (treenode.equalsIgnoreCase("Per Regio") && ouder.equalsIgnoreCase("Kerken")) {
                String postcode = (String) JOptionPane.showInputDialog(
                        this,
                        "Geef de postcode op:",
                        "Gegevens analyse",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (postcode != null && postcode.length() > 0) {
                    GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                    List<Gemorg> gemorgs = gemorgDao.geefAllePerPostcode("G", postcode);
                    if(gemorgs.isEmpty()) {
                        JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                    } else {
                        createBrieflijstenKerken((ArrayList) gemorgs);
                        vulLijstKerken(gemorgs);
                    }
                }

            } else if (treenode.equalsIgnoreCase("Per Denominatie") && ouder.equalsIgnoreCase("Kerken")) {
                DenominDao denominDao = new DenominDao(drm.getConnection());
                List<Denomin> denominaties = denominDao.geefAlleDenominaties();
                List<String> namen = new ArrayList<String>();
                for (int i = 0; i < denominaties.size(); i++) {
                    namen.add(denominaties.get(i).getNaam1());
                }
                Object[] mogelijkheden = namen.toArray();
                String denominatie = (String) JOptionPane.showInputDialog(
                        this,
                        "Kies de denominatie",
                        "Gegevens analyse",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        mogelijkheden, mogelijkheden[0]
                );
                if (denominatie != null && denominatie.length() > 0) {
                    GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                    List<Gemorg> gemorgs = gemorgDao.geefAlleKerkenPerDenominatie("G", denominatie);
                    if(gemorgs.isEmpty()) {
                        JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                    } else {
                        createBrieflijstenKerken((ArrayList) gemorgs);
                        vulLijstKerken(gemorgs);
                    }
                }
            } else if (treenode.equalsIgnoreCase("Alle Organisaties") && ouder.equalsIgnoreCase("Organisaties")) {
                GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                List<Gemorg> kerken = gemorgDao.geefAlleKerken("O");
                if(kerken.isEmpty()) {
                    JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                } else {
                    createBrieflijstenKerken((ArrayList) kerken);
                    vulLijstKerken(kerken);
                }
            } else if (treenode.equalsIgnoreCase("Leden") && ouder.equalsIgnoreCase("Organisaties")) {
                GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                List<Gemorg> kerken = gemorgDao.geefAlleLeden("O");
                if(kerken.isEmpty()) {
                    JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                } else {
                    createBrieflijstenKerken((ArrayList) kerken);
                    vulLijstKerken(kerken);
                }
            } else if (treenode.equalsIgnoreCase("Per Regio") && ouder.equalsIgnoreCase("Organisaties")) {
                String postcode = (String) JOptionPane.showInputDialog(
                        this,
                        "Geef de postcode op:",
                        "Gegevens analyse",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (postcode != null && postcode.length() > 0) {
                    GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                    List<Gemorg> gemorgs = gemorgDao.geefAllePerPostcode("O", postcode);
                    if(gemorgs.isEmpty()) {
                        JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                    } else {
                        createBrieflijstenKerken((ArrayList) gemorgs);
                        vulLijstKerken(gemorgs);
                    }
                }
            
            } else if (treenode.equalsIgnoreCase("Alle Personen")) {
                
                VerantwDao verantwDao = new VerantwDao(drm.getConnection());
                List<Verantw> personen = verantwDao.geefAlleVerantwoordelijken();
                if(personen.isEmpty()) {
                    JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                } else {
                    createBrieflijstenPersonen((ArrayList) personen);
                    vulLijstPersonen(personen);
                }
                
            } else if (treenode.equalsIgnoreCase("Per kerk") && ouder.equalsIgnoreCase("Personen")) {
                
                GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                List<Gemorg> gemorgs = gemorgDao.geefAlleGemorg();
                List<String> namen = new ArrayList<String>();
                for (int i = 0; i < gemorgs.size(); i++) {
                    namen.add(gemorgs.get(i).getNaam1());
                }
                Object[] mogelijkheden = namen.toArray();
                String gemorg = (String) JOptionPane.showInputDialog(
                        this,
                        "Kies de kerk",
                        "Gegevens analyse",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        mogelijkheden, mogelijkheden[0]
                );
                if (gemorg != null && gemorg.length() > 0) {
                    CodesDao codesDao = new CodesDao(drm.getConnection());
                    List<Codes> verantwoordelijken = codesDao.getVerantwPerKerk(gemorg);
                    List<Verantw> personen = new ArrayList<Verantw>();
                    if(verantwoordelijken.isEmpty()) {
                        JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                    } else {
                        for (int i = 0; i < verantwoordelijken.size(); i++) {
                            personen.add(verantwoordelijken.get(i).getVref());
                        }
                        createBrieflijstenPersonen((ArrayList) personen);
                        vulLijstPersonen(personen);
                    }
                
                }

            } else if (treenode.equalsIgnoreCase("Per Regio") && ouder.equalsIgnoreCase("Personen")) {
               
                String postcode = (String) JOptionPane.showInputDialog(
                        this,
                        "Geef de postcode op:",
                        "Gegevens analyse",
                        JOptionPane.PLAIN_MESSAGE
                );
                if (postcode != null && postcode.length() > 0) {
                    VerantwDao verantwDao = new VerantwDao(drm.getConnection());
                    List<Verantw> verantwoordelijken = verantwDao.geefAlleVerantwoordelijkenPerRegio(postcode);
                    if(verantwoordelijken.isEmpty()) {
                        JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwerken van uw aanvraag\nHebt u rechten om deze inhoud te bekijken?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
                    } else {
                        createBrieflijstenPersonen((ArrayList) verantwoordelijken);
                        vulLijstPersonen(verantwoordelijken);
                    }
                }
            
            }
        }

    }//GEN-LAST:event_trBrieflijstenMouseClicked

    public void createBrieflijstenPersonen(ArrayList<Verantw>lijst) {
        HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Adreslijst");
		int rownum = 0;
                Row row = sheet.createRow(rownum++);
                int cellnum = 0;
                row.createCell(cellnum++).setCellValue("Beleefdheidstitel");
                row.createCell(cellnum++).setCellValue("voornaam");
                row.createCell(cellnum++).setCellValue("naam2");
                row.createCell(cellnum++).setCellValue("achternaam");
                row.createCell(cellnum++).setCellValue("Adres 1");
                row.createCell(cellnum++).setCellValue("Postcode");
                row.createCell(cellnum++).setCellValue("Plaats");
                row.createCell(cellnum++).setCellValue("Land of Regio");
		for (int i = 0; i < lijst.size(); i++) {
                    row = sheet.createRow(rownum++);
                    cellnum = 0;
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getTitel());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getVoornaam());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getNaam1());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getNaam2());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getStraat());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getPostcode());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getWoonpl());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getLand());
                }		
		try {
                    JFileChooser fc = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("ms-excel bestanden","xls");
                    fc.setFileFilter(filter);
                    int opslaan = fc.showSaveDialog(this);
        
                    if(opslaan == JFileChooser.APPROVE_OPTION) {
        
                    FileOutputStream output = new FileOutputStream(fc.getSelectedFile() + ".xls");
                    
                    workbook.write(output);
            
            
                    output.close();
                    }
                	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
                }
    }
    
    public void createBrieflijstenKerken(ArrayList<Gemorg>lijst) {
        HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Adreslijst");
		int rownum = 0;
                Row row = sheet.createRow(rownum++);
                int cellnum = 0;
                row.createCell(cellnum++).setCellValue("Bedrijf");
                row.createCell(cellnum++).setCellValue("Adres 1");
                row.createCell(cellnum++).setCellValue("postcode");
                row.createCell(cellnum++).setCellValue("plaats");
		for (int i = 0; i < lijst.size(); i++) {
                    row = sheet.createRow(rownum++);
                    cellnum = 0;
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getNaam1());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getStraat());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getPostcode());
                    row.createCell(cellnum++).setCellValue(lijst.get(i).getWoonpl());
                }		
		try {
                    JFileChooser fc = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("ms-excel bestanden","xls");
                    fc.setFileFilter(filter);
                    int opslaan = fc.showSaveDialog(this);
        
                    if(opslaan == JFileChooser.APPROVE_OPTION) {
        
                    FileOutputStream output = new FileOutputStream(fc.getSelectedFile() + ".xls");
                    
                    workbook.write(output);
            
            
                    output.close();
                    }
                	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
                }
    }
    
    private void vulLijstKerken(List<Gemorg> kerken) {
        txtUitvoer.setText("<html><p>");
        HTMLDocument htmlDocument = (HTMLDocument) txtUitvoer.getStyledDocument();
        StringBuffer sb = new StringBuffer();
        Element e = htmlDocument.getElement(htmlDocument.getDefaultRootElement(), StyleConstants.NameAttribute, HTML.Tag.BODY);
        try {
            for (int i = 0; i < kerken.size(); i++) {
                String naam = kerken.get(i).getNaam1();
                String straat = kerken.get(i).getStraat();
                String postcode = kerken.get(i).getPostcode();
                String woonpl = kerken.get(i).getWoonpl();
                sb.append("<p>");
                sb.append("<b>" + naam + "</b>");
                sb.append("<br>" + straat + "<br>" + postcode + " " + woonpl);
            }   
            
            htmlDocument.insertBeforeEnd(e, sb.toString());
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    private void vulLijstPersonen(List<Verantw> personen) {
        txtUitvoer.setText("<html><p>");
        HTMLDocument htmlDocument = (HTMLDocument) txtUitvoer.getStyledDocument();
        StringBuffer sb = new StringBuffer();
        Element e = htmlDocument.getElement(htmlDocument.getDefaultRootElement(), StyleConstants.NameAttribute, HTML.Tag.BODY);
        try {
            for (int i = 0; i < personen.size(); i++) {
                String titel = personen.get(i).getTitel();
                String voornaam = personen.get(i).getVoornaam();
                String aanvulling = personen.get(i).getNaam2();
                String naam = personen.get(i).getNaam1();
                String straat = personen.get(i).getStraat();
                String postcode = personen.get(i).getPostcode();
                String woonpl = personen.get(i).getWoonpl();
                String land = personen.get(i).getLand();
                sb.append("<p>");
                sb.append("<b>" + titel + " " + voornaam + " " + aanvulling + " " + naam + "</b>");
                sb.append("<br>" + straat + "<br>" + postcode + " " + woonpl + "<br>" + land);
            }   
            
            htmlDocument.insertBeforeEnd(e, sb.toString());
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    
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
            java.util.logging.Logger.getLogger(FrmBrieflijsten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBrieflijsten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBrieflijsten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBrieflijsten.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBrieflijsten().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree trBrieflijsten;
    private javax.swing.JTextPane txtUitvoer;
    // End of variables declaration//GEN-END:variables
}
