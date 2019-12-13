package ppt.com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Form {
    private JPanel rootPanel;
    private JTable tb_doklady;
    private JLabel lb_doklady;
    private JLabel lb_zauctovaneDoklady;
    private JButton bt_zauctovat;
    private JButton reportDokladuButton;
    private JButton bt_reportAll;
    private JCheckBox cb_dph;
    private JCheckBox cb_datum;
    private JCheckBox cb_typ;
    private JList l_doklady;
    private JList l_zauctovane;

    ArrayList<Doklad> lDoklady = new ArrayList<>();
    ArrayList<Zauctovani> lDokladyZauctovane = new ArrayList<>();

    public Form() {

        Doklad testDoklad = new Doklad("1234", LocalDate.now(), new BigDecimal("100"), 21);
        lDoklady.add(testDoklad);

        refreshLists();

        bt_zauctovat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Zauctovani zauct = new Zauctovani("MM", LocalDate.now(), (Doklad)l_doklady.getSelectedValue());
                try{
                    zauct.zauctuj();
                    lDokladyZauctovane.add(zauct);
                    //lDoklady.remove(l_doklady.getSelectedIndex());
                    refreshLists();
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        reportDokladuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder report = new StringBuilder();
                Doklad doklad = (Doklad) l_doklady.getSelectedValue();

                buildHeader(report);
                buildReport(report, doklad);
                writeReport(report);

            }
        });
        bt_reportAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder report = new StringBuilder();
                buildHeader(report);
                for (Doklad doklad: lDoklady) {
                    buildReport(report, doklad);
                }
                writeReport(report);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Účetní doklady");



        frame.setContentPane(new Form().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void buildHeader(StringBuilder report){
        report.append("Číslo dokladu;");
        if(cb_datum.isSelected())
            report.append("datum vystavení;datum splatnosti");
        report.append("cena s dph;");
        if(cb_dph.isSelected())
            report.append("sazbaDPH");
        report.append(System.lineSeparator());
    }
    public void buildReport(StringBuilder report, Doklad doklad){
        report.append(doklad.getCisloDokladu());
        report.append(";");
        if(cb_datum.isSelected()){
            report.append(doklad.getDatumVystaveni());
            report.append(";");
            report.append(doklad.getDatumSplatnosti());
            report.append(";");
        }
        report.append(doklad.getCenaSDPH().doubleValue());
        report.append(";");
        if(cb_dph.isSelected()){
            report.append(doklad.getSazbaDPH());
            report.append(";");
        }
        report.append(System.lineSeparator());
    }

    public void writeReport(StringBuilder report){
        try {
            FileWriter fw = new FileWriter("Report.txt");
            fw.write(report.toString());
            fw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());;
        }
    }

    public void refreshLists(){
        l_doklady.setListData(lDoklady.toArray());
        l_zauctovane.setListData(lDokladyZauctovane.toArray());
    }
}
