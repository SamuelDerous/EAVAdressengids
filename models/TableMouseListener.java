/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/**
 *
 * @author zenodotus
 */
public class TableMouseListener extends MouseAdapter {
    
    private JTable tabel;
     
    public TableMouseListener(JTable tabel) {
        this.tabel = tabel;
    }
     
    @Override
    public void mouseReleased(MouseEvent event) {
        // selects the row at which point the mouse is clicked
        Point point = event.getPoint();
        int currentRow = tabel.rowAtPoint(point);
        tabel.setRowSelectionInterval(currentRow, currentRow);
    }
}
    

