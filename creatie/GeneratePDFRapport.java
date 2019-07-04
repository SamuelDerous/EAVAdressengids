/**
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Deze klasse wordt gebruikt om een pdf te generen; gebruik makend van de
 * bibliotheek itext
 */
package creatie;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;
import databank.DataAdapter;
import databank.dao.CodesDao;
import databank.dao.DenominDao;
import databank.dao.GemorgDao;
import databank.dao.SamenkomDao;
import databank.pojo.Codes;
import databank.pojo.Denomin;
import databank.pojo.Gemorg;
import databank.pojo.Samenkom;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;


/**
 *
 * @author zenodotus
 */
public class GeneratePDFRapport {

    private static volatile GeneratePDFRapport instance = null;
    private DataAdapter drm = new DataAdapter();

    public String indexAfdruk(HTMLDocument doc, String name) {
        PdfWriter w = null;
        Document d = new Document(PageSize.A5, 5, 5, 10, 10);
        //d.setPageCount(3);
        String fileName = name;
        String file = name;
        
        try {
            w = PdfWriter.getInstance(d, new FileOutputStream(file));
            d.open();
            //d.addAuthor("Jacqueline Vandenbroecke");
            //d.addCreationDate();
            //d.addCreator("Verlofplanner");
            //d.addTitle("Vakantie " + jaar + " van Jacqueline Vandenbroecke");
            Font standaard = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font standaardBold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
            //Paragraph gegeven = new Paragraph(Initialisatie.getGegeven("voornaam") + " " 
            //        + Initialisatie.getGegeven("naam") + " Verlof " + jaar + "\n", standaardBold);
            //gegeven.setAlignment(Paragraph.ALIGN_CENTER);
            //d.add(gegeven);
            ElementIterator iterator = new ElementIterator(doc);
            javax.swing.text.Element element;
            int aantal = 0;
            PdfPTable tabel = new PdfPTable(4);
                   
                
                
        while ((element = iterator.next()) != null) {
            AttributeSet as = element.getAttributes();
            Object elementen = as.getAttribute(StyleConstants.NameAttribute);
            int count = 0;
            
           
            if (elementen == HTML.Tag.TR) {
                count = element.getElementCount();
                
                int i = 0;
                if (count < 4) {
                   javax.swing.text.Element child = element.getElement(i);
                    int startOffset = child.getStartOffset();
                    int endOffset = child.getEndOffset();
                    int length = endOffset - startOffset;
                    
                   // table.getRow(aantal).createCell();
                  //  CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();

                    //width.setType(STTblWidth.DXA);
                    //width.setW(BigInteger.valueOf(10000));
                    //XWPFParagraph paragraaf = document.createParagraph();
                    //XWPFRun runnen = paragraaf.createRun();
                    //runnen.setText(htmlDocument.getText(startOffset, length));
                    PdfPCell cell = new PdfPCell(new Paragraph(doc.getText(startOffset, length), standaard));
                    cell.setColspan(4);
                    cell.setBorder(0);
                    tabel.addCell(cell); 
                }
                else {
                for(i = 0; i < count; i++) {
                    
                    javax.swing.text.Element child = element.getElement(i);
                    int startOffset = child.getStartOffset();
                    int endOffset = child.getEndOffset();
                    int length = endOffset - startOffset;
                    
                   // table.getRow(aantal).createCell();
                  //  CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();

                    //width.setType(STTblWidth.DXA);
                    //width.setW(BigInteger.valueOf(10000));
                    //XWPFParagraph paragraaf = document.createParagraph();
                    //XWPFRun runnen = paragraaf.createRun();
                    //runnen.setText(htmlDocument.getText(startOffset, length));
                    PdfPCell cell = new PdfPCell(new Paragraph(doc.getText(startOffset, length), standaard));
                    cell.setBorder(0);
                    tabel.addCell(cell);
                    
                }
                }

//sb[i][aantal] = new StringBuffer("");
                    //sb[i][aantal].append(htmlDocument.getText(startOffset, length));
                    //table.getRow(aantal).getCell(i).setText(htmlDocument.getText(startOffset, length));
                    // CTTblWidth width = table.getCTTbl().addNewTblPr().addNewTblW();

        //width.setType(STTblWidth.DXA);
        //width.setW(BigInteger.valueOf(7000));
                   // sb.append(htmlDocument.getText(startOffset, length)); 
           
                
            }
                aantal++;
                
            
        }
        
        d.add(tabel);
            /*for (int paginas = 0; paginas < 2; paginas++) {
                int aantal = 0;
                if (paginas == 1) {
                    d.newPage();
                    aantal = 6;
                }
                PdfPTable table = new PdfPTable(6);
                for (int i = aantal; i < (aantal + 6); i++) {
                    PdfPCell cell = new PdfPCell(new Paragraph(maanden[i], standaardBold));
                    cell.setBorder(1);
                    table.addCell(cell);
                }
                int dag = 1;
                int k = aantal;
                for (int i = aantal; i < (aantal + 6); i++) {
                    for (int j = 0; j < 32; j++) {
                        if (k > ((aantal + 6) - 1)) {
                            k = aantal;
                            dag++;
                        }
                        if (dag > dagen[k]) {
                            PdfPCell cell = new PdfPCell(new Paragraph("", standaard));
                            table.addCell(cell);
                            k++;
                        } else {
                            GregorianCalendar datum = new GregorianCalendar();
                            SimpleDateFormat formatterDag = new SimpleDateFormat("dd");
                            SimpleDateFormat formatterWeek = new SimpleDateFormat("EEE");
                            datum.set(jaar, k, dag);
                            PdfPTable dagTabel = new PdfPTable(4);
                            PdfPCell cellDag = new PdfPCell(new Paragraph(formatterDag.format(datum.getTime()), standaard));
                            PdfPCell cellWeek = new PdfPCell(new Paragraph(formatterWeek.format(datum.getTime()), standaard));
                            java.util.List<Verlof> verlof = dao.geefByDate(new java.sql.Date(datum.getTimeInMillis()));
                            Feestdag feestdag = feestdagDao.geefByDate(new java.sql.Date(datum.getTimeInMillis()));
                            java.util.List<Werkdag> weekend = werkdagDao.geefWeekend();
                            String Verlof = "";
                            String uur = "";
                            if (verlof.size() > 0) {
                                if (verlof.size() > 1) {
                                    Verlof = verlof.get(0).getVerlofsoort() + "\n" + verlof.get(1).getVerlofsoort();
                                    uur = verlof.get(0).getUrental() + "\n" + verlof.get(1).getUrental();
                                } else {
                                    Verlof = verlof.get(0).getVerlofsoort();
                                    uur = verlof.get(0).getUrental();
                                }

                            }
                            PdfPCell cellVerlof = new PdfPCell(new Paragraph(Verlof, standaard));
                            PdfPCell uren = new PdfPCell(new Paragraph(uur, standaard));
                            if (verlof.size() > 0) {
                                BaseColor kleur = new BaseColor(Color.decode(Initialisatie.getGegeven("colorVerlof")));
                                cellVerlof.setBackgroundColor(kleur);
                                uren.setBackgroundColor(kleur);
                                cellDag.setBackgroundColor(kleur);
                                cellWeek.setBackgroundColor(kleur);
                            }
                            for(int z = 0; z < weekend.size(); z++) {
                                if ((formatterWeek.format(datum.getTime())).equals(weekend.get(z).getDag())) {
                                    BaseColor kleur = new BaseColor(Color.decode(Initialisatie.getGegeven("colorWeekend")));
                                    cellVerlof.setBackgroundColor(kleur);
                                    uren.setBackgroundColor(kleur);
                                    cellDag.setBackgroundColor(kleur);
                                    cellWeek.setBackgroundColor(kleur);
                                }
                            }
                             if(feestdag != null) {
                                BaseColor kleur = new BaseColor(Color.decode(Initialisatie.getGegeven("colorWeekend")));
                                uren.setBackgroundColor(kleur);
                                cellVerlof.setBackgroundColor(kleur);
                                uren.setBackgroundColor(kleur);
                                cellDag.setBackgroundColor(kleur);
                                cellWeek.setBackgroundColor(kleur);
                            }
                            dagTabel.addCell(cellDag);
                            dagTabel.addCell(cellWeek);
                            dagTabel.addCell(cellVerlof);
                            dagTabel.addCell(uren);
                            table.addCell(dagTabel);
                            k++;
                        }
                    }

                }
                d.add(table);*/
            }
        
         catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            d.close();
            w.close();
        }
        return fileName;
    }

    public String orgAfdruk(HTMLDocument htmlDocument, String name) {
        PdfWriter w = null;
        Document d = new Document(PageSize.A5, 5, 5, 10, 10);
        String fileName = name;
            String file = name;
        try {
            
        //d.setPageCount(3);
            
        
 
            w = PdfWriter.getInstance(d, new FileOutputStream(file));
            d.open();
            //d.addAuthor("Jacqueline Vandenbroecke");
            //d.addCreationDate();
            //d.addCreator("Verlofplanner");
            //d.addTitle("Vakantie " + jaar + " van Jacqueline Vandenbroecke");
            Font standaard = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font standaardBold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
            //Paragraph test = new Paragraph("Dit is een test");
            //d.add(test);
            
        
            ElementIterator iterator = new ElementIterator(htmlDocument);
            javax.swing.text.Element element;
            while ((element = iterator.next()) != null) {
            AttributeSet as = element.getAttributes();
            Object elementen = as.getAttribute(StyleConstants.NameAttribute);
            int count = 0;
            
            if (elementen == HTML.Tag.H1) {
                          
                count = element.getElementCount();
                
                int i = 0;
                Paragraph paragraaf = new Paragraph();
                //for(i = 0; i < count; i++) {
                    
                    javax.swing.text.Element child = element.getElement(i);
                    int startOffset = child.getStartOffset();
                    int endOffset = child.getEndOffset();
                    int length = endOffset - startOffset;
                    paragraaf.add(htmlDocument.getText(startOffset, length));
                    d.add(paragraaf);
                    
                    
                //}
                //d.add(paragraaf);
            } else if (elementen == HTML.Tag.P) {
                count = element.getElementCount();
                
                int i = 0;
                Paragraph paragraaf = new Paragraph();
                StringBuffer sb = new StringBuffer();
                sb.append("\n");
                for(i = 0; i < count; i++) {
                    
                    javax.swing.text.Element child = element.getElement(i);
                    int startOffset = child.getStartOffset();
                    int endOffset = child.getEndOffset();
                    int length = endOffset - startOffset;
                    sb.append(htmlDocument.getText(startOffset, length));
                    AttributeSet childAttributes = child.getAttributes();
                    
                    if (childAttributes.getAttribute(StyleConstants.NameAttribute) == HTML.Tag.BR)
                  {
                      sb.append("\n");
                  }
                    
                
            }
            Chunk chunk = new Chunk(sb.toString(), standaard);
            paragraaf.add(chunk);
            d.add(paragraaf);
            }
            }
            
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
            d.close();
            w.close();
        }
        return fileName;
    }
    
    public String rapportAfdruk(String name, int gemorg) {
        PdfWriter w = null;
        Document d = new Document(PageSize.A4, 5, 5, 10, 10);
        String fileName = name;
            String file = name;
        GemorgDao gemorgDao = new GemorgDao(drm.getConnection());
        Gemorg kerk = gemorgDao.getGemorgByRef(gemorg);
        //System.out.println(kerk.getRefnum());
        String naam = kerk.getNaam1().replace(".", "");
        naam = naam.replace("/", " ");
        file = name + File.separator + kerk.getRefnum() + "_" + naam + ".pdf";
        File f = new File(file);
        if(f.exists() && !f.isDirectory()) { 
            JOptionPane.showMessageDialog(null, "Het bestand " + file + " bestaat al en zal overschreven worden");
        }
        java.util.List<Codes> verantwoordelijken;
        java.util.List<String> plaatsen;
        java.util.List<Codes> kerken;
        Samenkom samenkomst;
        
        CodesDao codesDao = new CodesDao(drm.getConnection());
        SamenkomDao samenkomDao = new SamenkomDao(drm.getConnection());
        plaatsen = gemorgDao.getPlaatsen("G");
        try {
            w = PdfWriter.getInstance(d, new FileOutputStream(file));
            PageNumeration event = new PageNumeration();
            w.setPageEvent(event);
            d.open(); 
            Font standaard = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font standaardBold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
            Font standaardUnderline = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.UNDERLINE);
            //Paragraph test = new Paragraph("Dit is een test");
            //d.add(test);
            Paragraph paragraaf;
            
            
            paragraaf = new Paragraph(kerk.getSorteer().getSorteer() + "\n\n", standaardUnderline);
            d.add(paragraaf);
            paragraaf = new Paragraph("Correspondentie-gegevens:\n\n", standaardBold);
            d.add(paragraaf);
            PdfPTable tabel = new PdfPTable(2);
            PdfPCell cel;
            cel = new PdfPCell(new Paragraph("Naam van de gemeente:", standaard));
            tabel.addCell(cel);
            
            cel = new PdfPCell(new Paragraph(kerk.getNaam1(), standaardBold));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph("Bijkomende naam-info:", standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph((kerk.getNaam2() != null ? kerk.getNaam2() : ""), standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph("Adres van de gemeente:", standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph(kerk.getStraat(), standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph("Postcode en woonplaats:", standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph(kerk.getPostcode() + " " + kerk.getWoonpl(), standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph("E-mail", standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph((kerk.getEmail() != null ? kerk.getEmail() : ""), standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph("Website:", standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph((kerk.getWebsite() != null ? kerk.getWebsite() : ""), standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph("Lid EAV:", standaard));
            tabel.addCell(cel);
            String eavlid = "";
           if(kerk.getEavlid() == 1) {
                eavlid = "Ja";
            } else {
                eavlid = "Neen";
            }
            cel = new PdfPCell(new Paragraph(eavlid, standaard));
            tabel.addCell(cel);
            cel = new PdfPCell(new Paragraph("Denominatie:", standaard));
            tabel.addCell(cel);
            DenominDao denominDao = new DenominDao(drm.getConnection());
            Denomin denomin = denominDao.getDenominatieByRef(kerk.getDenomin().getDenomin());
            if(denomin != null) {
                String test = denomin.getNaam1();
                cel = new PdfPCell(new Paragraph(denomin.getNaam1(), standaard));
            } else {
                cel = new PdfPCell(new Paragraph("", standaard));
            }
            tabel.addCell(cel);
            d.add(tabel);
            paragraaf = new Paragraph("\nBovenstaande gegevens:\n\n ", standaardBold);
            d.add(paragraaf);
            PdfFormField checkbox = PdfFormField.createCheckBox(w);
            tabel = new PdfPTable(3);
            PdfPCell cell;
            cell = new PdfPCell(new Paragraph("ONGEWIJZIGD BEHOUDEN", standaard));
            cell.setCellEvent(new CheckboxCellEvent("Ongewijzigd behouden"));
            cell.setMinimumHeight(50);
            tabel.addCell(cell);
            cell = new PdfPCell(new Paragraph("WIJZIGEN", standaard));
            cell.setCellEvent(new CheckboxCellEvent("Wijzigen"));
            cell.setMinimumHeight(50);
            tabel.addCell(cell);
            cell = new PdfPCell(new Paragraph("SCHRAPPEN", standaard));
            cell.setCellEvent(new CheckboxCellEvent("Schrappen"));
            cell.setMinimumHeight(50);
            tabel.addCell(cell);
            d.add(tabel);
            paragraaf = new Paragraph("\nIn geval van wijzigingen, geef de nieuwe gegevens hieronder:\n\n", standaardBold);
            d.add(paragraaf);
            tabel = new PdfPTable(2);
            tabel.addCell(new Paragraph("Naam van de gemeente: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("Naam1"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Bijkomende naam-info: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("Naam2"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Adres van de gemeente: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("straat"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Postcode en plaats: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("pstcode"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("E-mail: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("Email"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Website: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("website"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("EAV-lid: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("EAVLid"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Denominatie: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("denominatie"));
            tabel.addCell(cell);
            d.add(tabel);
            samenkomst = samenkomDao.getSamenkomstByRef(kerk.getRefnum());
            if(samenkomst != null) {
                paragraaf = new Paragraph("\nPlaats van Samenkomst: \n\n", standaardBold);
                d.add(paragraaf);
                paragraaf = new Paragraph(samenkomst.getNaam1() + "\n\n" +
                        samenkomst.getStraat() + "\n" + samenkomst.getPostcode() + " " + samenkomst.getWoonpl() + "\n" +
                        "Samenkomst: " + samenkomst.getDag() + ", " + samenkomst.getUur().get(GregorianCalendar.HOUR_OF_DAY) + ":" + samenkomst.getUur().get(GregorianCalendar.MINUTE) + " u\n" +
                        "Tel.: " + (samenkomst.getTelefoon() != null ? samenkomst.getTelefoon() : "") + "\n\n", standaard);
                d.add(paragraaf);
                paragraaf = new Paragraph("Bovenstaande gegevens:\n\n", standaardBold);
                d.add(paragraaf);
                checkbox = PdfFormField.createCheckBox(w);
                tabel = new PdfPTable(2);
                cell = new PdfPCell(new Paragraph("ONGEWIJZIGD BEHOUDEN", standaard));
                cell.setCellEvent(new CheckboxCellEvent("Samenkomst Ongewijzigd behouden"));
                cell.setMinimumHeight(50);
                tabel.addCell(cell);
                cell = new PdfPCell(new Paragraph("WIJZIGEN", standaard));
                cell.setCellEvent(new CheckboxCellEvent("Samenkomst Wijzigen"));
                cell.setMinimumHeight(50);
                tabel.addCell(cell);
                d.add(tabel);
                paragraaf = new Paragraph("\nIn geval van wijzigingen, geef de nieuwe gegevens hieronder:\n\n", standaardBold);
                d.add(paragraaf);
                tabel = new PdfPTable(2);
                tabel.addCell(new Paragraph("Plaats van samenkomst: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("SamenkomstNaam1"));
                tabel.addCell(cell);
            tabel.addCell(new Paragraph("Straat: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("samenkomstStraat"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Postcode en gemeente: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("SamenkomstPostcode"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Dag van samenkomst: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("SamenkomstDag"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("Uur van samenkomst: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("samenkomstUur"));
            tabel.addCell(cell);
            tabel.addCell(new Paragraph("samenkomstTelefoon: ", standaard));
            cell = new PdfPCell();
            cell.setCellEvent(new MyCellField("telefoon"));
            tabel.addCell(cell);
            d.add(tabel);
            }
            verantwoordelijken = codesDao.getVerantwPerKerk(kerk.getNaam1());
            paragraaf = new Paragraph("\nVerantwoordelijken:\n\n", standaardBold);
            d.add(paragraaf);
            for(int i = 0; i < verantwoordelijken.size(); i++) {
                paragraaf = new Paragraph("Verantwoordelijke " + (i + 1) + "\n\n", standaard);
                d.add(paragraaf);
                tabel = new PdfPTable(2);
                cel = new PdfPCell(new Paragraph("Naam:", standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph((verantwoordelijken.get(i).getVref().getTitel() != null ? verantwoordelijken.get(i).getVref().getTitel() + " " : "") + (verantwoordelijken.get(i).getVref().getVoornaam() != null ? verantwoordelijken.get(i).getVref().getVoornaam() + " " : "") + (verantwoordelijken.get(i).getVref().getNaam2() != null ? verantwoordelijken.get(i).getVref().getNaam2() + " " : "") + (verantwoordelijken.get(i).getVref().getNaam1() != null ? verantwoordelijken.get(i).getVref().getNaam1() : ""), standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph("Adres:", standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph(verantwoordelijken.get(i).getVref().getStraat(), standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph("Woonplaats", standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph(verantwoordelijken.get(i).getVref().getPostcode() + " " + verantwoordelijken.get(i).getVref().getWoonpl(), standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph("Telefoon:", standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph((verantwoordelijken.get(i).getVref().getTelefoon() != null ? verantwoordelijken.get(i).getVref().getTelefoon() : ""), standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph("GSM", standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph((verantwoordelijken.get(i).getVref().getGsm() != null ? verantwoordelijken.get(i).getVref().getGsm() : ""), standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph("E-mail", standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph((verantwoordelijken.get(i).getVref().getEmail() != null ? verantwoordelijken.get(i).getVref().getEmail() : ""), standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph("Functie: ", standaard));
                tabel.addCell(cel);
                cel = new PdfPCell(new Paragraph(verantwoordelijken.get(i).getFunccode(), standaard));
                tabel.addCell(cel);
                if (verantwoordelijken.get(i).getFunccod2() != null && !(verantwoordelijken.get(i).getFunccod2().equals(""))) {
                    cel = new PdfPCell(new Paragraph("Functie 2: ", standaard));
                    tabel.addCell(cel);
                    cel = new PdfPCell(new Paragraph(verantwoordelijken.get(i).getFunccod2(), standaard));
                    tabel.addCell(cel);
                }
                d.add(tabel);
                paragraaf = new Paragraph("\nBovenstaande gegevens:\n\n ", standaardBold);
                d.add(paragraaf);
                checkbox = PdfFormField.createCheckBox(w);
                tabel = new PdfPTable(3);
                cell = new PdfPCell(new Paragraph("ONGEWIJZIGD BEHOUDEN", standaard));
                cell.setCellEvent(new CheckboxCellEvent("Verantw Ongewijzigd behouden_" + i));
                cell.setMinimumHeight(50);
                tabel.addCell(cell);
                cell = new PdfPCell(new Paragraph("WIJZIGEN", standaard));
                cell.setCellEvent(new CheckboxCellEvent("Verantw Wijzigen_" + i));
                cell.setMinimumHeight(50);
                tabel.addCell(cell);
                cell = new PdfPCell(new Paragraph("SCHRAPPEN", standaard));
                cell.setCellEvent(new CheckboxCellEvent("Verantw Schrappen_" + i));
                cell.setMinimumHeight(50);
                tabel.addCell(cell);
                d.add(tabel);
                paragraaf = new Paragraph("\nIn geval van wijzigingen, geef de nieuwe gegevens hieronder:\n\n ", standaardBold);
                d.add(paragraaf);
                tabel = new PdfPTable(2);
                tabel.addCell(new Paragraph("Naam van de Verantwoordelijke: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("VerantwNaam1_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Bijkomende naam-info: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("verantwNaam2_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Adres van de verantwoordelijke: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("verantwStraat_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Postcode en plaats: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("verantwPostcode_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Telefoon: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("verantwTelefoon_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("GSM: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("verantwGsm_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("E-mail: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("verantwEmail_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Functie 1: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("VerantwFunctie1_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Functie 2: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("verantwFunctie2_" + i));
                tabel.addCell(cell);
            
                d.add(tabel);
                
            }
            for(int i = 0; i < 4; i++) {
                paragraaf = new Paragraph("\nNieuwe gegevens verantwoordelijken toevoegen:\n\n ", standaardBold);
                d.add(paragraaf);
                tabel = new PdfPTable(2);
                tabel.addCell(new Paragraph("Naam van de Verantwoordelijke: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("NieuwNaam1_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Bijkomende naam-info: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("NieuwNaam2_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Adres van de verantwoordelijke: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("NieuwStraat_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Postcode en plaats: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("nieuwPostcode_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Telefoon: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("nieuwTelefoon_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("GSM: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("nieuwGsm_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("E-mail: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("nieuwEmail_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Functie 1: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("nieuwFunctie1_" + i));
                tabel.addCell(cell);
                tabel.addCell(new Paragraph("Functie 2: ", standaard));
                cell = new PdfPCell();
                cell.setCellEvent(new MyCellField("nieuwFunctie2_" + i));
                tabel.addCell(cell);
            
                d.add(tabel);
                
            }
                        
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
            d.close();
            w.close();
        }
        return name;
    
    }
    
    class MyCellField implements PdfPCellEvent {
        protected String fieldname;
        public MyCellField(String fieldname) {
            this.fieldname = fieldname;
        }
        public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
            final PdfWriter writer = canvases[0].getPdfWriter();
            final TextField textField = new TextField(writer, rectangle, fieldname);
            try {
                final PdfFormField field = textField.getTextField();
                writer.addAnnotation(field);
            } catch (final IOException ioe) {
                throw new ExceptionConverter(ioe);
            } catch (final DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
    }
    
    class CheckboxCellEvent implements PdfPCellEvent {
        // The name of the check box field
        protected String name;
        // We create a cell event
        public CheckboxCellEvent(String name) {
            this.name = name;
        }
        // We create and add the check box field
        public void cellLayout(PdfPCell cell, Rectangle position,
            PdfContentByte[] canvases) {
            PdfWriter writer = canvases[0].getPdfWriter(); 
            // define the coordinates of the middle
            float x = (position.getLeft() + position.getRight()) / 2;
            float y = (position.getTop() + position.getBottom()) / 2;
            // define the position of a check box that measures 20 by 20
            Rectangle rect = new Rectangle(x - 50, y - 10, x + 50, y + 10);
            // define the check box
            RadioCheckField checkbox = new RadioCheckField(
                    writer, rect, name, name);
            // add the check box as a field
            try {
                writer.addAnnotation(checkbox.getCheckField());
            } catch (Exception e) {
                throw new ExceptionConverter(e);
            }
        }
    }

    
    public String rapportAfdruk(HTMLDocument htmlDocument, String name) {
        PdfWriter w = null;
        Document d = new Document(PageSize.A4, 5, 5, 10, 10);
        String fileName = name;
            String file = name;
        try {
            w = PdfWriter.getInstance(d, new FileOutputStream(file));
            d.open(); 
            Font standaard = FontFactory.getFont(FontFactory.HELVETICA, 8);
            Font standaardBold = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
            //Paragraph test = new Paragraph("Dit is een test");
            //d.add(test);
            
        
            ElementIterator iterator = new ElementIterator(htmlDocument);
            javax.swing.text.Element element;
            while ((element = iterator.next()) != null) {
            AttributeSet as = element.getAttributes();
            Object elementen = as.getAttribute(StyleConstants.NameAttribute);
            int count = 0;
            
            if (elementen == HTML.Tag.P) {
                count = element.getElementCount();
                
                int i = 0;
                Paragraph paragraaf = new Paragraph();
                StringBuffer sb = new StringBuffer();
                for(i = 0; i < count; i++) {
                    
                    javax.swing.text.Element child = element.getElement(i);
                    int startOffset = child.getStartOffset();
                    int endOffset = child.getEndOffset();
                    int length = endOffset - startOffset;
                    sb.append(htmlDocument.getText(startOffset, length));
                    AttributeSet childAttributes = child.getAttributes();
                    if (childAttributes.getAttribute(StyleConstants.NameAttribute) == HTML.Tag.BR)
                  {
                      sb.append("\n");
                  }
                    
                  
                    
                
                        }
            
            Chunk chunk = new Chunk(sb.toString(), standaard);
            paragraaf.add(chunk);
            d.add(paragraaf);
            }
            else if (elementen == HTML.Tag.TABLE) {
                count = element.getElementCount();
                
                int i = 0;
                Paragraph paragraaf = new Paragraph();
                StringBuffer sb = new StringBuffer();
               
                for(i = 0; i < count; i++) {
                    
                    javax.swing.text.Element child = element.getElement(i);
                    int startOffset = child.getStartOffset();
                    int endOffset = child.getEndOffset();
                    int length = endOffset - startOffset;
                    String tekst = htmlDocument.getText(startOffset, length);
                    sb.append(tekst);
                    AttributeSet childAttributes = child.getAttributes();
                    if (childAttributes.getAttribute(StyleConstants.NameAttribute) == HTML.Tag.TD)
                  {
                      sb.append("");
                  }
                 
                
            }
            Chunk chunk = new Chunk(sb.toString(), standaard);
            paragraaf.add(chunk);
            d.add(paragraaf);
            }
            else if (elementen == HTML.Tag.BR) {
                count = element.getElementCount();
                
                int i = 0;
                Paragraph paragraaf = new Paragraph();
                StringBuffer sb = new StringBuffer();
                
                for(i = 0; i < count; i++) {
                    
                    javax.swing.text.Element child = element.getElement(i);
                    int startOffset = child.getStartOffset();
                    int endOffset = child.getEndOffset();
                    int length = endOffset - startOffset;
                    sb.append(htmlDocument.getText(startOffset, length));
                    AttributeSet childAttributes = child.getAttributes();
                    
                   
                    
                
            }
            Chunk chunk = new Chunk(sb.toString(), standaard);
            paragraaf.add(chunk);
            d.add(paragraaf);
            }
            }
            
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
            d.close();
            w.close();
        }
        return name;
    
    }
}
