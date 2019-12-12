package ppt.com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form {
    private JPanel rootPanel;
    private JTable tb_doklady;
    private JTable tb_zauctovaneDoklady;
    private JLabel lb_doklady;
    private JLabel lb_zauctovaneDoklady;
    private JButton bt_zauctovat;
    private JButton reportDokladuButton;
    private JButton bt_reportAll;
    private JCheckBox cb_dph;
    private JCheckBox cb_datum;
    private JCheckBox cb_typ;

    public Form() {
        bt_zauctovat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
}
