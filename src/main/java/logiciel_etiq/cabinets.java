package logiciel_etiq;




import logiciel_etiq.Controller.CConnect;
import logiciel_etiq.View.generale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class cabinets {
    public static void inserttion() {
        String code="";

        NumberFormat formatter3 = new DecimalFormat("000");
        NumberFormat formatter1 = new DecimalFormat("0");
        NumberFormat formatter2 = new DecimalFormat("00");

        final Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);

        cal.set(2023, 10, 30);
        int jour= cal.get(Calendar.DAY_OF_WEEK);
        int week= cal.get(Calendar.WEEK_OF_YEAR);

        String anne = "23";

        String week1 = formatter2.format(week);
        String jour1 = formatter1.format(jour);
        int j=1;


       // String  prefix= jour1 + week1 + anne;
          String prefix="54823";
        for(int i=225; i<=275;i++){
                code=prefix+formatter3.format(i);
                String codeCabinets="EC"+code;
                String codealimentation="EA"+code;
                String codeMere="EM"+code;
             //    String requette = "INSERT  INTO etiquette_cabinets (code, alimentation, carte_mere) VALUES ('" + codeCabinets + "','" + codealimentation + "','" + codeMere + "'     )";
             //   CConnect.Requete(requette);
            }


        int groupCounter = 224;
        int k=1;
        String code2;

        for(int i=3896; i<=4085;i++){



            if (i % 4 == 1 && i != 1) {
                k=1;

                groupCounter++;
                }
            code=prefix+formatter3.format(groupCounter);
            code2="5482"+formatter3.format(i);

            String module="EL"+code2;
                    //+"/"+formatter3.format(groupCounter);
         //   System.out.println(module+" "+k+ "  "+codeCabinets);
            String requette = "INSERT  INTO etiquette_cabinets  (module) values ('"+module+"' )  ";
            System.out.println(requette);
           CConnect.Requete(requette);
            k++;
            }



    }
       /* public static void codification(String indice, String pdfName, String champ){

            NumberFormat formatter4 = new DecimalFormat("000000");
            ArrayList<Object> code_aimprimer=new ArrayList<>();


            for(int i=1; i<=224;i++){

                code=prefix+formatter3.format(i);
                code_aimprimer.add(code);
                String select = "select "+champ+" from etiquette_cabinets";
                if(!CConnect.Requete(select).contains(code)) {
                    String requette = "INSERT  INTO etiquette_cabinets ("+champ+") VALUES ('" + code + "')";
                    CConnect.Requete(requette);
                }
            }


            parameters.put("code", code_aimprimer);
            String report="etiquette_cabinet.jrxml";
            String parcour ="C:\\GCOBAR\\PDF\\"+pdfName+".pdf";



            new generale().Imprimer_pdf_Final( parcour, report, parameters);

        }*/
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pdf Cabinets generation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        frame.add(panel);
         inserttion();
        // Create four buttons
        JButton button1 = new JButton("Generate cabinets etiquettes");
        JButton button2 = new JButton("Generate alimentation etiquettes");
        JButton button3 = new JButton("Generate carte mère etiquettes");
        JButton button4 = new JButton("generate Modules LED etiquette");

        // Add action listeners to the buttons
     /*   button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codification("EC","etiquette_cabinets","code");
                JOptionPane.showMessageDialog(null, "Button 1 clicked!");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codification("EA","etiquette_alimentation","alimentation");

                JOptionPane.showMessageDialog(null, "Button 2 clicked!");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codification("EA","etiquette_cartemere","carte_mere");

                JOptionPane.showMessageDialog(null, "Button 3 clicked!");
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Button 4 clicked!");
            }
        });*/

        // Add buttons to the panel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        frame.setVisible(true);
    }

}
