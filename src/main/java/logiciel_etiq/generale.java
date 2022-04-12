package logiciel_etiq;

import javax.swing.*;
import java.awt.*;

public class generale  {

   public static void styles(String style) {
        try {

           //private static String laf="javax.swing.plaf.metal.MetalLookAndFeel";

           for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
               if (style.equals(info.getName())) {
                   UIManager.setLookAndFeel(info.getClassName());

                   break;
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
