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
import databank.dao.PlaatsenDao;
import databank.dao.SamenkomDao;
import databank.dao.VerantwDao;
import databank.pojo.Codes;
import databank.pojo.Denomin;
import databank.pojo.Gemorg;
import databank.pojo.Plaatsen;
import databank.pojo.Samenkom;
import databank.pojo.Verantw;
import filters.ImageFilter;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.TableMouseListener;

/**
 *
 * @author zenodotus
 */
public class DlgAanpassen extends javax.swing.JDialog {

    private DataAdapter drm = new DataAdapter();
    private int refnum, soort;
    private Verantw verantwoordelijke;
    private Samenkom samenkomst;
    private Denomin denominatie;
    private Gemorg gemorg;
    List<Codes> gemorgCodes;
    private List<Verantw> verantwoordelijken;
    private JComboBox cmbTitel, cmbLand, cmbWeekdag, cmbGemorg, cmbSorteer, cmbDenomin;
    private JCheckBox chkPrincode, chkEavLid;
    private JTable lstKerk;
    private JScrollPane jScrollPane1;
    
    private JLabel lblRef; 
    private JLabel lblTitel; 
    private JLabel lblVoornaam;
    private JLabel lblNaam1;
    private JLabel lblNaam2;
    private JLabel lblStraat;
    private JLabel lblPostcode;
    private JLabel lblWoonpl;
    private JLabel lblLand;
    private JLabel lblTelefoon;
    private JLabel lblGsm;
    private JLabel lblEmail;
    private JLabel lblWebsite;
    private JLabel lblGemorg;
    private JLabel lblDenomin;
    private JLabel lblPlaats;
    private JLabel lblTaalcode;
    private JLabel lblPrincode;
    private JLabel lblUur;
    private JLabel lblDag;
    private JLabel lblLogo;
    private JLabel lblUur1;
    private JLabel lblKerk;
    private JLabel lblFuncties;
    
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtWebsite;
    private javax.swing.JTextField txtGsm;
    private javax.swing.JTextField txtNaam1;
    private javax.swing.JTextField txtNaam2;
    private javax.swing.JTextField txtPostcode;
    private javax.swing.JTextField txtRef;
    private javax.swing.JTextField txtStraat;
    private javax.swing.JTextField txtTaalcode;
    private javax.swing.JTextField txtTelefoon;
    private javax.swing.JTextField txtWoonpl;
    private javax.swing.JTextField txtVoornaam;
    private javax.swing.JTextField txtUur;
    private javax.swing.JTextField txtMinuten;
    private javax.swing.JTextField txtFunctie1;
    private javax.swing.JTextField txtFunctie2;
    
  
    
    private JButton btnAanpassen = new JButton("Aanpassen");
    private JButton btnAnnuleren = new JButton("Annuleren");
    private JButton btnVerwijderen = new JButton("Verwijderen");
    
    private int kerkrijen = 0;
    
    List<Integer> lstId = new ArrayList<Integer>();
    /**
     * Creates new form DlgAanpassen
     */
    public DlgAanpassen(java.awt.Frame parent, boolean modal, int refnum, int soort) {
        super(parent, modal);
        initComponents();
        this.soort = soort;
        this.refnum = refnum;
        switch(soort) {
            case 1:
                initVerantwoordelijken();
                break;
            case 2:
                initSamenkomst();
                break;
            case 3:
                initDenominaties();
                break;
            case 4:
                initGemorg();
                break;
        }
        this.setLocationRelativeTo(null);
    }
    
    private void initVerantwoordelijken() {
        lblTitel = new javax.swing.JLabel();
        lblGsm = new javax.swing.JLabel();
        lblRef = new javax.swing.JLabel();
        lblNaam2 = new javax.swing.JLabel();
        lblStraat = new javax.swing.JLabel();
        lblPostcode = new javax.swing.JLabel();
        lblWoonpl = new javax.swing.JLabel();
        lblTelefoon = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTaalcode = new javax.swing.JLabel();
        lblKerk = new javax.swing.JLabel();
        chkPrincode = new javax.swing.JCheckBox();
        btnAanpassen = new javax.swing.JButton();
        btnAnnuleren = new javax.swing.JButton();
        txtRef = new javax.swing.JTextField();
        txtNaam2 = new javax.swing.JTextField();
        txtWoonpl = new javax.swing.JTextField();
        txtTelefoon = new javax.swing.JTextField();
        txtGsm = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPostcode = new javax.swing.JTextField();
        txtStraat = new javax.swing.JTextField();
        txtNaam1 = new javax.swing.JTextField();
        txtTaalcode = new javax.swing.JTextField();
        lblVoornaam = new javax.swing.JLabel();
        lblNaam1 = new javax.swing.JLabel();
        cmbTitel = new javax.swing.JComboBox<>();
        txtVoornaam = new javax.swing.JTextField();
        lblLand = new javax.swing.JLabel();
        cmbLand = new javax.swing.JComboBox<>();
        lstKerk = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblVoornaam = new JLabel();
        //txtFunctie1 = new javax.swing.JTextField();
        //txtFunctie2 = new javax.swing.JTextField();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        VerantwDao dao = new VerantwDao(drm.getConnection());
        verantwoordelijke = dao.getVerantwoordelijkeByVRef(refnum);
        

        lblTitel.setText("Titel:");

        lblGsm.setText("GSM:");

        lblRef.setText("VRef:");

        lblNaam2.setText("Aanvulling:");

        lblStraat.setText("Straat:");

        lblPostcode.setText("Postcode:");

        lblWoonpl.setText("Woonplaats:");

        lblTelefoon.setText("Telefoon:");

        lblEmail.setText("E-mail:");

        lblTaalcode.setText("Taalcode:");

        chkPrincode.setText("Afdrukken");
        
        btnAanpassen.setText("Aanpassen");

        btnAnnuleren.setText("Annuleren");
        
        lblVoornaam.setText("Voornaam:");

        String bestandLanden = "bestanden/landen.txt";
        /*String[] landen = vulComboLanden(bestandLanden);
        for(int i = 0; i < landen.length; i++) {
            cmbLand.addItem(landen[i]);
        }
        cmbLand.setSelectedIndex(24);*/
        vulVerantwoordelijken();
        
        
        List<String> titels = dao.geefAlleTitels();
        for(int i = 0; i < titels.size(); i++) {
            cmbTitel.addItem(titels.get(i));
           
        }
        
        txtRef.setText("" + verantwoordelijke.getVref());
        txtRef.setEditable(false);

        txtNaam2.setText(verantwoordelijke.getNaam2());

        txtWoonpl.setText(verantwoordelijke.getWoonpl());

        txtTelefoon.setText(verantwoordelijke.getTelefoon());

        txtGsm.setText(verantwoordelijke.getGsm());

        txtEmail.setText(verantwoordelijke.getEmail());

        txtPostcode.setText(verantwoordelijke.getPostcode());

        txtStraat.setText(verantwoordelijke.getStraat());
        
        txtNaam1.setText(verantwoordelijke.getNaam1());

        txtTaalcode.setText(verantwoordelijke.getTaalcode());

        lblVoornaam.setText("Voornaam:");

        lblNaam1.setText("Naam:");

        txtVoornaam.setText(verantwoordelijke.getVoornaam());

        lblLand.setText("Land:");
        
        lblKerk.setText("kerken: ");
        
        cmbTitel.setSelectedItem(verantwoordelijke.getTitel());
        cmbLand.setSelectedItem(verantwoordelijke.getLand());
        if(verantwoordelijke.getPrincode() == 1) {
            chkPrincode.setSelected(true);
        } else {
            chkPrincode.setSelected(false);
        }
        
        
        jScrollPane1.setViewportView(lstKerk);
        
        GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
        List<Gemorg> gemorgs = gemorgDao.geefAlleGemorgGeordend();
        CodesDao codesDao = new CodesDao(drm.getConnection());
        gemorgCodes = codesDao.getGemorgsbyVref(refnum);
        kerkrijen = gemorgCodes.size();
             
             
        

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAanpassen)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnnuleren)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerwijderen))
                    .addComponent(lblNaam2)
                    .addComponent(lblStraat)
                    .addComponent(lblPostcode)
                    .addComponent(lblWoonpl)
                    .addComponent(lblNaam1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefoon)
                            .addComponent(lblRef)
                            .addComponent(lblVoornaam)
                            .addComponent(lblLand)
                            .addComponent(lblGsm)
                            .addComponent(lblEmail)
                            .addComponent(lblKerk))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTaalcode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTaalcode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtVoornaam, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNaam1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNaam2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtStraat, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPostcode, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtWoonpl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(cmbLand, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTelefoon, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtGsm, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPrincode))))
                   
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRef)
                    .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTaalcode)
                    .addComponent(txtTaalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVoornaam)
                    
                    .addComponent(txtVoornaam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaam1)
                    .addComponent(txtNaam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaam2)
                    .addComponent(txtNaam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStraat)
                    .addComponent(txtStraat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPostcode)
                    .addComponent(txtPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLand, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefoon)
                    .addComponent(txtTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGsm)
                    .addComponent(txtGsm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblKerk))
                       // .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(chkPrincode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAanpassen)
                    .addComponent(btnAnnuleren)
                    .addComponent(btnVerwijderen))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        
                            

        
        btnAnnuleren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAnnulerenActionPerformed(e);
            }
            
        });
        
        btnAanpassen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAanpassenVerantwActionPerformed(e);
            }
            
        });
        
        btnVerwijderen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVerwijderenActionPerformed(e, 1);
            }
        });
        
        String[] kols = {"Kerknaam", "Functie 1", "Functie 2", "Cliëntnummer", "volgnummer", "afdrukken"};
        

        DefaultTableModel mdlTable = new DefaultTableModel(kols, 0);
        

        lstKerk.setModel(mdlTable);
       
        lstKerk.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbKerken()));
        lstKerk.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(chkPrinten()));
        lstKerk.getColumnModel().getColumn(0).setPreferredWidth(250);
        for(int i = 1; i < lstKerk.getColumnCount(); i++)  {
            lstKerk.getColumnModel().getColumn(i).setPreferredWidth(70);
        }
        
        for(int i = 0; i < gemorgCodes.size(); i++) {
            int id = gemorgCodes.get(i).getId();
            lstId.add(id);
            String kerknaam = gemorgCodes.get(i).getRefnum().getNaam1();
            String functie1 = gemorgCodes.get(i).getFunccode();
            String functie2 = gemorgCodes.get(i).getFunccod2();
            String clientnr = gemorgCodes.get(i).getClientnr();
            String volgnummer = "" + gemorgCodes.get(i).getVolgnum();
            Boolean afdrukken = false;
            if (gemorgCodes.get(i).getPrincode() == 1) {
                afdrukken = true;
            }
            
            Object[] data = {kerknaam, functie1, functie2, clientnr, volgnummer, afdrukken};
            mdlTable.addRow(data);
        }
        if(gemorgCodes.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) lstKerk.getModel();
                model.addRow(new Object[] {"", "", "", "", "", ""});
                kerkrijen++;
        }
        
        lstKerk.addMouseListener(new TableMouseListener(lstKerk));
        
        JPopupMenu popupVerwijderen = new JPopupMenu();
        JMenuItem menuItemVerwijderen = new JMenuItem("Verwijder huidige kerk");
        
        popupVerwijderen.add(menuItemVerwijderen);
        
        menuItemVerwijderen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                popupVerwijderen(evt);
            }
        });
        
        lstKerk.setComponentPopupMenu(popupVerwijderen);
        lstKerk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lstKerkenKeyPressed(evt);
            }
        });
     
        
        
        
        
        
        
    pack();
    }
    
    private void lstKerkenKeyPressed(java.awt.event.KeyEvent evt) {                                     
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!(lstKerk.getValueAt((kerkrijen - 1), 0) == null) && !(lstKerk.getValueAt((kerkrijen - 1), 0).equals(""))) {
                DefaultTableModel model = (DefaultTableModel) lstKerk.getModel();
                model.addRow(new Object[] {"", "", "", "", "", ""});
                kerkrijen++;
            }
            }
             
        
    }
    
    private void popupVerwijderen(ActionEvent evt) {
        CodesDao codesDao = new CodesDao(drm.getConnection());
        
        int id = lstId.get(lstKerk.getSelectedRow());
        
        codesDao.deleteCodes(id);
        
        gemorgCodes = codesDao.getGemorgsbyVref(refnum);
        kerkrijen = gemorgCodes.size();
        String[] kols = {"Kerknaam", "Functie 1", "Functie 2", "Cliëntnummer", "volgnummer", "afdrukken"};
        DefaultTableModel mdlTable = new DefaultTableModel(kols, 0);
        lstKerk.setModel(mdlTable);
        lstKerk.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cmbKerken()));
        lstKerk.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(chkPrinten()));
        lstKerk.getColumnModel().getColumn(0).setPreferredWidth(250);
        for(int i = 1; i < lstKerk.getColumnCount(); i++)  {
            lstKerk.getColumnModel().getColumn(i).setPreferredWidth(70);
        }
       
        

        lstKerk.setModel(mdlTable);
        for(int i = 0; i < lstId.size(); i++) {
            lstId.remove(i);
        }
        for(int i = 0; i < gemorgCodes.size(); i++) {
            int newId = gemorgCodes.get(i).getId();
            lstId.add(newId);
            String kerknaam = gemorgCodes.get(i).getRefnum().getNaam1();
            String functie1 = gemorgCodes.get(i).getFunccode();
            String functie2 = gemorgCodes.get(i).getFunccod2();
            String clientnr = gemorgCodes.get(i).getClientnr();
            String volgnummer = "" + gemorgCodes.get(i).getVolgnum();
            Boolean afdrukken = false;
            if (gemorgCodes.get(i).getPrincode() == 1) {
                afdrukken = true;
            }
            
            Object[] data = {kerknaam, functie1, functie2, clientnr, volgnummer, afdrukken};
            mdlTable.addRow(data);
        }
        if(gemorgCodes.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) lstKerk.getModel();
                model.addRow(new Object[] {"", "", "", "", "", ""});
                kerkrijen++;
        }
        DefaultTableModel model = (DefaultTableModel) lstKerk.getModel();
        model.fireTableDataChanged();        
        
    }
    
        
    
    
    private JComboBox cmbKerken() {
        GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
        List<Gemorg> lijst = new ArrayList<Gemorg>();
        JComboBox cmbKerken = new JComboBox();
        
        lijst = gemorgDao.geefAlleGemorg();
        
        for(int i = 0; i < lijst.size(); i++) {
            cmbKerken.addItem(lijst.get(i).getNaam1());
        }
        return cmbKerken;
        
        
    }
    
    private JCheckBox chkPrinten() {
        return new JCheckBox("");
}
    
    
    private void initSamenkomst() {
        
        lblUur = new javax.swing.JLabel();
        lblRef = new javax.swing.JLabel();
        lblNaam2 = new javax.swing.JLabel();
        lblStraat = new javax.swing.JLabel();
        lblPostcode = new javax.swing.JLabel();
        lblWoonpl = new javax.swing.JLabel();
        lblTelefoon = new javax.swing.JLabel();
        chkPrincode = new javax.swing.JCheckBox();
        btnAanpassen = new javax.swing.JButton();
        btnAnnuleren = new javax.swing.JButton();
        txtRef = new javax.swing.JTextField();
        txtNaam2 = new javax.swing.JTextField();
        txtWoonpl = new javax.swing.JTextField();
        txtTelefoon = new javax.swing.JTextField();
        txtUur = new javax.swing.JTextField();
        txtPostcode = new javax.swing.JTextField();
        txtStraat = new javax.swing.JTextField();
        txtNaam1 = new javax.swing.JTextField();
        lblNaam1 = new javax.swing.JLabel();
        lblDag = new javax.swing.JLabel();
        cmbWeekdag = new javax.swing.JComboBox<>();
        lblUur1 = new javax.swing.JLabel();
        txtMinuten = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblUur.setText("Uur:");

        lblRef.setText("Referentie:");

        lblNaam2.setText("Aanvulling:");

        lblStraat.setText("Straat:");

        lblPostcode.setText("Postcode:");

        lblWoonpl.setText("Woonplaats:");

        lblTelefoon.setText("Telefoon:");

        chkPrincode.setText("Afdrukken");
        
        btnAanpassen.setText("Aanpassen");

        btnAnnuleren.setText("Annuleren");

        SamenkomDao dao = new SamenkomDao(drm.getConnection());
        GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
        samenkomst = dao.getSamenkomstByRef(refnum);
        gemorg = gemorgDao.getGemorgByRef(refnum);
        
        txtRef.setText("" + refnum);
        txtRef.setEditable(false);

        txtNaam2.setText(samenkomst.getNaam2());

        txtWoonpl.setText(samenkomst.getWoonpl());

        txtTelefoon.setText(samenkomst.getTelefoon());

        String uur = (samenkomst.getUur().get(GregorianCalendar.HOUR_OF_DAY) < 10 ? "0" : "") + samenkomst.getUur().get(GregorianCalendar.HOUR_OF_DAY);
        String minuten = (samenkomst.getUur().get(GregorianCalendar.MINUTE) < 10 ? "0" : "") + samenkomst.getUur().get(GregorianCalendar.MINUTE);
        
        
        if(samenkomst.getPrincode() == 1) {
            chkPrincode.setSelected(true);
        } else {
            chkPrincode.setSelected(false);
        }
        
        txtUur.setText(uur);
        

        txtPostcode.setText(samenkomst.getPostcode());

        txtStraat.setText(samenkomst.getStraat());
        
        txtNaam1.setText(samenkomst.getNaam1());

        lblNaam1.setText("Naam:");

        lblDag.setText("Dag:");

        lblUur1.setText(":");

        txtMinuten.setText(minuten);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkPrincode)
                    .addComponent(lblNaam2)
                    .addComponent(lblStraat)
                    .addComponent(lblPostcode)
                    .addComponent(lblWoonpl)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefoon)
                            .addComponent(lblDag)
                            .addComponent(lblRef))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNaam1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(txtNaam2)
                                .addComponent(txtStraat, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(txtPostcode, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(txtWoonpl, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                            .addComponent(cmbWeekdag, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblNaam1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUur)
                        .addGap(115, 115, 115)
                        .addComponent(txtUur, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUur1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(txtMinuten, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAanpassen)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnnuleren)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerwijderen)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRef)
                    .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaam1)
                    .addComponent(txtNaam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaam2)
                    .addComponent(txtNaam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStraat)
                    .addComponent(txtStraat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPostcode)
                    .addComponent(txtPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefoon)
                    .addComponent(txtTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbWeekdag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDag, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUur)
                    .addComponent(txtUur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUur1)
                    .addComponent(txtMinuten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPrincode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAanpassen)
                    .addComponent(btnAnnuleren)
                    .addComponent(btnVerwijderen))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        

        
        cmbWeekdag.setModel(new DefaultComboBoxModel(new String[] { "zondag", "maandag", "dinsdag", "woensdag", "donderdag", "vrijdag", "zaterdag" }));

        cmbWeekdag.setSelectedItem(samenkomst.getDag());
        
        
        btnAnnuleren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAnnulerenActionPerformed(e);
            }
            
        });
        
        btnAanpassen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAanpassenSamenkomstActionPerformed(e);
            }
            
        });
        
        btnVerwijderen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVerwijderenActionPerformed(e, 2);
            }
        });
        
        
        
        
        
        
        
        
        
        pack();
    }
    
    private void initDenominaties() {
        lblNaam1 = new javax.swing.JLabel();
        lblGsm = new javax.swing.JLabel();
        lblRef = new javax.swing.JLabel();
        lblNaam2 = new javax.swing.JLabel();
        lblStraat = new javax.swing.JLabel();
        lblPostcode = new javax.swing.JLabel();
        lblWoonpl = new javax.swing.JLabel();
        lblTelefoon = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTaalcode = new javax.swing.JLabel();
        chkPrincode = new javax.swing.JCheckBox();
        lblLogo = new javax.swing.JLabel();
        btnAanpassen = new javax.swing.JButton();
        btnAnnuleren = new javax.swing.JButton();
        
        txtRef = new javax.swing.JTextField();
        txtNaam2 = new javax.swing.JTextField();
        txtWoonpl = new javax.swing.JTextField();
        txtTelefoon = new javax.swing.JTextField();
        txtGsm = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPostcode = new javax.swing.JTextField();
        txtStraat = new javax.swing.JTextField();
        txtNaam1 = new javax.swing.JTextField();
        txtTaalcode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNaam1.setText("Naam:");

        lblGsm.setText("GSM:");

        lblRef.setText("Denomin:");

        lblNaam2.setText("Aanvulling:");

        lblStraat.setText("Straat:");

        lblPostcode.setText("Postcode:");

        lblWoonpl.setText("Woonplaats:");

        lblTelefoon.setText("Telefoon:");

        lblEmail.setText("E-mail:");

        lblTaalcode.setText("Taalcode:");

        chkPrincode.setText("Afdrukken");
        
        
        DenominDao dao = new DenominDao(drm.getConnection());
        
        denominatie = dao.getDenominatieByRef(refnum);
        
        
        ImageIcon icon = new ImageIcon(denominatie.getLogo());
        lblLogo.setIcon(new ImageIcon(getScaledImage(icon.getImage(), 100, 100), denominatie.getLogo()));
        lblLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoMouseClicked(evt);
            }
        });
        lblLogo.setText("");

        btnAanpassen.setText("Aanpassen");

        btnAnnuleren.setText("Annuleren");

        txtRef.setText("" + refnum);
        txtRef.setEditable(false);

        txtNaam2.setText(denominatie.getNaam2());

        txtWoonpl.setText(denominatie.getWoonpl());

        txtTelefoon.setText(denominatie.getTelefoon());

        txtGsm.setText(denominatie.getGsm());

        txtEmail.setText(denominatie.getEmail());

        txtPostcode.setText(denominatie.getPostcode());

        txtStraat.setText(denominatie.getStraat());

        txtNaam1.setText(denominatie.getNaam1());

        txtTaalcode.setText(denominatie.getTaalcode());
        
        if(denominatie.getPrincode() == 1) {
            chkPrincode.setSelected(true);
        } else {
            chkPrincode.setSelected(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNaam2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNaam2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStraat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStraat, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPostcode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblWoonpl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                .addComponent(txtWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTelefoon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGsm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtGsm, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNaam1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNaam1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail)
                                    .addComponent(lblTaalcode))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTaalcode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkPrincode)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAanpassen)
                                .addGap(18, 18, 18)
                                .addComponent(btnAnnuleren)
                                .addGap(18, 18, 18)
                                .addComponent(btnVerwijderen))
                            .addComponent(lblRef))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRef)
                    .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNaam1)
                    .addComponent(txtNaam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNaam2)
                    .addComponent(txtNaam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblStraat)
                    .addComponent(txtStraat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPostcode)
                    .addComponent(txtPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefoon)
                    .addComponent(txtTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGsm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGsm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTaalcode)
                    .addComponent(txtTaalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkPrincode)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAanpassen)
                    .addComponent(btnAnnuleren)
                    .addComponent(btnVerwijderen))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();  
        
        
        btnAnnuleren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAnnulerenActionPerformed(e);
            }
            
        });
        
        btnAanpassen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAanpassenDenominActionPerformed(e);
            }
            
        });
        
        btnVerwijderen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVerwijderenActionPerformed(e, 3);
            }
        });
        
        
        
        pack();
        
    
    }
    
    private void initGemorg() {

        lblRef = new javax.swing.JLabel();
        lblNaam2 = new javax.swing.JLabel();
        lblStraat = new javax.swing.JLabel();
        lblPostcode = new javax.swing.JLabel();
        lblWoonpl = new javax.swing.JLabel();
        lblTelefoon = new javax.swing.JLabel();
        chkPrincode = new javax.swing.JCheckBox();
        btnAanpassen = new javax.swing.JButton();
        btnAnnuleren = new javax.swing.JButton();
        txtRef = new javax.swing.JTextField();
        txtNaam2 = new javax.swing.JTextField();
        txtWoonpl = new javax.swing.JTextField();
        txtTelefoon = new javax.swing.JTextField();
        txtPostcode = new javax.swing.JTextField();
        txtStraat = new javax.swing.JTextField();
        txtNaam1 = new javax.swing.JTextField();
        lblNaam1 = new javax.swing.JLabel();
        lblGemorg = new javax.swing.JLabel();
        cmbGemorg = new javax.swing.JComboBox<>();
        btnVerwijderen = new javax.swing.JButton();
        lblGsm = new javax.swing.JLabel();
        txtGsm = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblWebsite = new javax.swing.JLabel();
        txtWebsite = new javax.swing.JTextField();
        lblDenomin = new javax.swing.JLabel();
        cmbDenomin = new javax.swing.JComboBox<>();
        lblPlaats = new javax.swing.JLabel();
        cmbSorteer = new javax.swing.JComboBox<>();
        chkEavLid = new javax.swing.JCheckBox();
        lblTaalcode = new javax.swing.JLabel();
        txtTaalcode = new javax.swing.JTextField();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        GemorgDao dao = new GemorgDao(drm.getConnection());
        gemorg = dao.getGemorgByRef(refnum);
        
        DenominDao denominDao = new DenominDao(drm.getConnection());
        List<Denomin> denominaties = denominDao.geefAlleDenominaties();
        
        PlaatsenDao plaatsenDao = new PlaatsenDao(drm.getConnection());
        List<Plaatsen> sorteer = plaatsenDao.geefAllePlaatsen();
        
        for(int i = 0; i < denominaties.size(); i++) {
            cmbDenomin.addItem(denominaties.get(i).getNaam1());
        }
        Denomin denominatie = dao.getDenomin(refnum);
        if(denominatie != null) {
            cmbDenomin.setSelectedItem(denominatie.getNaam1());
        }
        for(int i = 0; i < sorteer.size(); i++) {
            cmbSorteer.addItem(sorteer.get(i).getSorteer());
        }
        
        cmbSorteer.setSelectedItem(gemorg.getSorteer().getSorteer());
        
        if(gemorg.getEavlid() == 1) {
            chkEavLid.setSelected(true);
        }
        
        if(gemorg.getPrincode() == 1) {
            chkPrincode.setSelected(true);
        }
        
        lblRef.setText("Refnum:");
       

        lblNaam2.setText("Aanvulling:");

        lblStraat.setText("Straat:");

        lblPostcode.setText("Postcode:");

        lblWoonpl.setText("Woonplaats:");

        lblTelefoon.setText("Telefoon:");

        chkPrincode.setText("Afdrukken");
        

        btnAanpassen.setText("Aanpassen");
        

        btnAnnuleren.setText("Annuleren");

        txtRef.setText("" + refnum);
        txtRef.setEditable(false);

        txtNaam2.setText(gemorg.getNaam2());

        txtWoonpl.setText(gemorg.getWoonpl());

        txtTelefoon.setText(gemorg.getTelefoon());

        txtPostcode.setText(gemorg.getPostcode());

        txtStraat.setText(gemorg.getStraat());
        
        txtNaam1.setText(gemorg.getNaam1());

        lblNaam1.setText("Naam:");

        lblGemorg.setText("<html>Gemeente/<br>Organisatie:</html>");

        cmbGemorg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gemeente", "Organisatie" }));
        if(gemorg.getGemorg().equals("G")) {
            cmbGemorg.setSelectedItem("Gemeente");
        } else {
            cmbGemorg.setSelectedItem("Organisatie");
        }

        btnVerwijderen.setText("Verwijderen");

        lblGsm.setText("GSM:");

        txtGsm.setText(gemorg.getGsm());

        lblEmail.setText("E-mail:");

        txtEmail.setText(gemorg.getEmail());

        lblWebsite.setText("Website:");

        txtWebsite.setText(gemorg.getWebsite());

        lblDenomin.setText("Denominatie");

        lblPlaats.setText("Plaats:");

        chkEavLid.setText("EAV-lid");
        
        lblTaalcode.setText("Taalcode:");

        txtTaalcode.setText(gemorg.getTaalcode());

        
       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chkPrincode)
                        .addGap(18, 18, 18)
                        .addComponent(chkEavLid))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAanpassen)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnnuleren)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerwijderen))
                    .addComponent(lblNaam2)
                    .addComponent(lblStraat)
                    .addComponent(lblPostcode)
                    .addComponent(lblWoonpl)
                    .addComponent(lblNaam1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefoon)
                            .addComponent(lblRef)
                            .addComponent(lblGsm)
                            .addComponent(lblEmail)
                            .addComponent(lblWebsite))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGsm, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTaalcode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTaalcode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNaam1)
                                .addComponent(txtNaam2)
                                .addComponent(txtStraat)
                                .addComponent(txtPostcode)
                                .addComponent(txtWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGemorg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDenomin)
                            .addComponent(lblPlaats))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSorteer, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDenomin, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbGemorg, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRef)
                    .addComponent(txtRef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTaalcode)
                    .addComponent(txtTaalcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaam1)
                    .addComponent(txtNaam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaam2)
                    .addComponent(txtNaam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStraat)
                    .addComponent(txtStraat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPostcode)
                    .addComponent(txtPostcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWoonpl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelefoon)
                    .addComponent(txtTelefoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGsm)
                    .addComponent(txtGsm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWebsite)
                    .addComponent(txtWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbGemorg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGemorg, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDenomin)
                    .addComponent(cmbDenomin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPlaats)
                    .addComponent(cmbSorteer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkPrincode)
                    .addComponent(chkEavLid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAanpassen)
                    .addComponent(btnAnnuleren)
                    .addComponent(btnVerwijderen))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
        
        btnAnnuleren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAnnulerenActionPerformed(e);
            }
            
        });
        
        btnAanpassen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAanpassenGemorgActionPerformed(e);
            }
            
        });
        
        btnVerwijderen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVerwijderenActionPerformed(e, 4);
            }
        });
    }
    
    private Image getScaledImage(Image srcImg, int w, int h){
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
}
    
    private void btnAnnulerenActionPerformed(ActionEvent evt) {
        this.setVisible(false);
    }
    
    private void btnAanpassenVerantwActionPerformed(ActionEvent evt) {
        verantwoordelijke = new Verantw();
        
        verantwoordelijke.setVref(refnum);
        verantwoordelijke.setTitel(cmbTitel.getSelectedItem().toString());
        verantwoordelijke.setVoornaam(txtVoornaam.getText());
        verantwoordelijke.setNaam1(txtNaam1.getText());
        verantwoordelijke.setNaam2(txtNaam2.getText());
        verantwoordelijke.setStraat(txtStraat.getText());
        verantwoordelijke.setPostcode(txtPostcode.getText());
        verantwoordelijke.setWoonpl(txtWoonpl.getText());
        verantwoordelijke.setLand(cmbLand.getSelectedItem() == null ? "" : cmbLand.getSelectedItem().toString());
        verantwoordelijke.setTelefoon(txtTelefoon.getText());
        verantwoordelijke.setGsm(txtGsm.getText());
        verantwoordelijke.setEmail(txtEmail.getText());
        verantwoordelijke.setTaalcode(txtTaalcode.getText());
        verantwoordelijke.setPrincode((chkPrincode.isSelected() ? 1 : 0));
        
        VerantwDao dao = new VerantwDao(drm.getConnection());
        CodesDao codesDao = new CodesDao(drm.getConnection());
        GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
        ArrayList<Codes> codes = new ArrayList<Codes>();
        boolean volgnumFout = false;
        boolean uitgevoerd = dao.updateVerantwoordelijke(verantwoordelijke);
        
        for(int i = 0; i < kerkrijen; i++) {
                if((lstKerk.getValueAt(i, 0) != null) && !(lstKerk.getValueAt(kerkrijen - 1, 0).equals(""))) {
                    for(int j = 1; j < lstKerk.getColumnCount() - 1; j++) {
                        if (lstKerk.getValueAt(i, j) == null) {
                            lstKerk.setValueAt("", i, j);
                        }
                    }
                    System.out.println(lstKerk.getValueAt(i, 0).toString());
                    Gemorg gemorg = gemorgDao.getGemorgByName(lstKerk.getValueAt(i, 0).toString());
                    Codes code = new Codes();
                    code.setVref(verantwoordelijke);
                    code.setRefnum(gemorg);
                    code.setDenomin(gemorg.getDenomin());
                    code.setFunccode(lstKerk.getValueAt(i, 1).toString());
                    code.setFunccod2(lstKerk.getValueAt(i,2).toString());
                    code.setClientnr(lstKerk.getValueAt(i, 3).toString());
                    code.setVolgnum(Integer.parseInt(lstKerk.getValueAt(i, 4).toString()));
                    if((Boolean) lstKerk.getValueAt(i, 5)) {
                        code.setPrincode(1);
                    } else {
                        code.setPrincode(0);
                    }
                    
                     if(i >= lstId.size()) {
                        code.setId((codesDao.getHighest() + 1));
                       
                        
                        //gemorgCodes = codesDao.getGemorgsbyVref(verantwoordelijke.getVref());
                        
                        
                        
                    } else {
                        code.setId(lstId.get(i));
                        
                    }
                    
                    List<Codes> lstKerken = codesDao.getVerantwPerKerk(lstKerk.getValueAt(i,0).toString());
                    for(int k = 0; k < lstKerken.size(); k++) {
                        int codeInt = code.getId();
                        int codeKerk = lstKerken.get(k).getId();
                        if((code.getId() != lstKerken.get(k).getId()) && (code.getVolgnum() == lstKerken.get(k).getVolgnum())) {
                            JOptionPane.showMessageDialog(this, "Er is al een verantwoordelijke met dit volgnummer", "Integriteitsfout", JOptionPane.ERROR_MESSAGE);
                            volgnumFout = true;
                            break;
                        } 
                        
                    }
                    codes.add(code);
           
                
                        
                    
                }
            }
        
        if(!volgnumFout) {
            
        
            dao.toevoegenVerantw(verantwoordelijke);
            for(int i = 0; i < codes.size(); i++) {
              if(i >= lstId.size()) {
                        codesDao.toevoegenCode(codes.get(i));
                        
                        //gemorgCodes = codesDao.getGemorgsbyVref(verantwoordelijke.getVref());
                        lstId.add(codes.get(i).getId());
                        
                        
                    } else {
                        codesDao.updateCodes(codes.get(i));
                    }
            }
        } else {
            uitgevoerd = false;
        }
            
        if(uitgevoerd) {
            this.setVisible(false);
        } else {
            JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het uitvoeren van de aanpassing\nHebt u rechten om aanpassingen uit te voeren?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnAanpassenSamenkomstActionPerformed(ActionEvent evt) {
        samenkomst = new Samenkom();
        
        samenkomst.setRefnum(gemorg);
        samenkomst.setNaam1(txtNaam1.getText());
        samenkomst.setNaam2(txtNaam2.getText());
        samenkomst.setStraat(txtStraat.getText());
        samenkomst.setPostcode(txtPostcode.getText());
        samenkomst.setWoonpl(txtWoonpl.getText());
        samenkomst.setTelefoon(txtTelefoon.getText());
        samenkomst.setDag(cmbWeekdag.getSelectedItem().toString());
        GregorianCalendar uur = new GregorianCalendar(0, 0, 0, Integer.parseInt(txtUur.getText()), Integer.parseInt(txtMinuten.getText()));
        samenkomst.setUur(uur);
        samenkomst.setPrincode((chkPrincode.isSelected() ? 1 : 0));
        
        SamenkomDao dao = new SamenkomDao(drm.getConnection());
        
        boolean uitgevoerd = dao.updateSamenkomst(samenkomst);
        if(uitgevoerd) {
            this.setVisible(false);
        } else {
            JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het uitvoeren van de aanpassing\nHebt u rechten om aanpassingen uit te voeren?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnAanpassenDenominActionPerformed(ActionEvent evt) {
        denominatie = new Denomin();
        
        denominatie.setDenomin(refnum);
        denominatie.setNaam1(txtNaam1.getText());
        denominatie.setNaam2(txtNaam2.getText());
        denominatie.setStraat(txtStraat.getText());
        denominatie.setPostcode(txtPostcode.getText());
        denominatie.setWoonpl(txtWoonpl.getText());
        denominatie.setTelefoon(txtTelefoon.getText());
        denominatie.setGsm(txtGsm.getText());
        denominatie.setEmail(txtEmail.getText());
        
        denominatie.setLogo(((ImageIcon)lblLogo.getIcon()).toString());
        System.out.println(((ImageIcon)lblLogo.getIcon()).toString()); 
        denominatie.setTaalcode(txtTaalcode.getText());
        denominatie.setPrincode((chkPrincode.isSelected() ? 1 : 0));
        
        DenominDao dao = new DenominDao(drm.getConnection());
        
        boolean uitgevoerd = dao.updateDenominatie(denominatie);
        if (uitgevoerd) {
            this.setVisible(false);
        } else {
            JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het uitvoeren van de aanpassing\nHebt u rechten om aanpassingen uit te voeren?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnAanpassenGemorgActionPerformed(ActionEvent evt) {
        gemorg = new Gemorg();
        gemorg.setRefnum(refnum);
        gemorg.setNaam1(txtNaam1.getText());
        gemorg.setNaam2(txtNaam2.getText());
        gemorg.setStraat(txtStraat.getText());
        gemorg.setPostcode(txtPostcode.getText());
        gemorg.setWoonpl(txtWoonpl.getText());
        gemorg.setTelefoon(txtTelefoon.getText());
        gemorg.setGsm(txtGsm.getText());
        gemorg.setEmail(txtEmail.getText());
        gemorg.setWebsite(txtWebsite.getText());
        System.out.println(cmbGemorg.getSelectedItem().toString());
        if(cmbGemorg.getSelectedItem().equals("Gemeente")) {
            gemorg.setGemorg("G");
        } else {
            gemorg.setGemorg("O");
        }
        DenominDao denominDao = new DenominDao(drm.getConnection());
        Denomin denomin = denominDao.getDenominatieByNaam1(cmbDenomin.getSelectedItem().toString());
        gemorg.setDenomin(denomin);
        Plaatsen sorteer = new Plaatsen();
        sorteer.setSorteer(cmbSorteer.getSelectedItem().toString());
        gemorg.setDenomin(denomin);
        gemorg.setSorteer(sorteer);
        gemorg.setTaalcode(txtTaalcode.getText());
        gemorg.setPrincode((chkPrincode.isSelected() ? 1 : 0));
        gemorg.setEavlid(chkEavLid.isSelected() ? 1 : 0);
        
        GemorgDao dao = new GemorgDao(drm.getConnection());
        
        boolean uitgevoerd = dao.updateGemorg(gemorg);
        if(uitgevoerd) {
            this.setVisible(false);
        } else {
            JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het uitvoeren van de aanpassing\nHebt u rechten om aanpassingen uit te voeren?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
        }
                
    }
    
    private void btnVerwijderenActionPerformed(ActionEvent e, int tabel) {
        boolean uitgevoerd = false;
        switch(tabel) {
            case 1:
                VerantwDao verantwDao = new VerantwDao(drm.getConnection());
                CodesDao codesDao = new CodesDao(drm.getConnection());
                for(int i = 0; i < lstId.size(); i++) {
                    codesDao.deleteCodes(lstId.get(i));
                }
                uitgevoerd = verantwDao.deleteVerantwoordelijke(refnum);
                break;
            case 2:
                SamenkomDao samenkomDao = new SamenkomDao(drm.getConnection());
                uitgevoerd = samenkomDao.deletesamenkomst(refnum);
                break;
            case 3:
                DenominDao denominDao = new DenominDao(drm.getConnection());
                uitgevoerd = denominDao.deleteDenominatie(refnum);
                break;
            case 4:
                GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
                uitgevoerd = gemorgDao.deleteGemorg(refnum);
                break;
        }
        if(uitgevoerd) {
            this.setVisible(false);
        } else {
            JOptionPane.showConfirmDialog(this, "Er is een fout opgetreden bij het verwijderen van de gegevens\nHebt u rechten om verwijderingen uit te voeren?", "Fout bij het uitvoeren", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void lblLogoMouseClicked(java.awt.event.MouseEvent evt) {                                     
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);
        int openen = fc.showOpenDialog(this);
        if(openen == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String fileString = file.toString();
            ImageIcon icon = new ImageIcon(fileString);
            lblLogo.setIcon(new ImageIcon(getScaledImage(icon.getImage(), 100, 100), fileString));
        }
    }                                    
    
    public String[] vulComboLanden(String landen) {
        BufferedReader input = null;
        String[] lineArray = null;
        try {
            FileInputStream fis = new FileInputStream(new File(landen));
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            
            input = new BufferedReader(isr);
            List<String> strings = new ArrayList<String>();
            String regel = null;
            while ((regel = input.readLine()) != null) {
                strings.add(regel);
            }
            input.close();
            lineArray = strings.toArray(new String[]{});
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            return lineArray;
        }
        
        
    }
    
    private void vulVerantwoordelijken() {
        verantwoordelijken = new ArrayList<Verantw>();
        
        VerantwDao dao = new VerantwDao(drm.getConnection());
        verantwoordelijken = dao.geefAlleVerantwoordelijken();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aanpassen");
        setMinimumSize(new java.awt.Dimension(400, 515));
        setModal(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DlgAanpassen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgAanpassen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgAanpassen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgAanpassen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgAanpassen dialog = new DlgAanpassen(new javax.swing.JFrame(), true, 1, 1);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
   
}
