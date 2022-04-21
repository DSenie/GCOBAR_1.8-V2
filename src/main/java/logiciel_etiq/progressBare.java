package logiciel_etiq;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class progressBare extends JPanel {

    JProgressBar pbar;
    JFrame f;
 //   static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

    public progressBare(int min, int max) {


        pbar = new JProgressBar();
        pbar.setMinimum(min);
        pbar.setMaximum(max);
        add(pbar);

        pbar.setStringPainted(true);
        Border border = BorderFactory.createTitledBorder("Generation de l'exel...");
        pbar.setBorder(border);

         f = new JFrame("Attendre");

        f.setUndecorated(true);

        f.getContentPane().add(pbar, SwingConstants.CENTER);
        f.setSize(300, 70);
        f.setVisible(true);
        f.setLocationRelativeTo(null);


        generale.styles("Nimbus");
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void updateBar(int newValue) {

        pbar.setValue(newValue);
    }

    public static void main(String args[]) {

       /* final progressBare it = new progressBare(1,1000);



        for (int i = 1; i <= 1000; i++) {
            final int percent = i;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        it.updateBar(percent);

                    }
                });
                if(i==1000){
                 it.f.setVisible(false);
                }
                java.lang.Thread.sleep(100);
            } catch (InterruptedException e) {
                ;
            }
        }*/
    }
}
