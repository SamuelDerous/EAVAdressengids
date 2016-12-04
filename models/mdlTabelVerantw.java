/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zenodotus
 */
public class mdlTabelVerantw extends DefaultTableModel {
    
    private String[] kols = {"vref", "titel", "voornaam", "naam1", "naam2",
        "straat", "postcode", "woonplaats", "land", "telefoon", "faxnum",
        "e-mail", "taalcode", "princode", "update"};
    

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return kols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
