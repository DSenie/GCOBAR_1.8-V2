package logiciel_etiq;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.jdesktop.swingx.JXDatePicker;

public class etiquette_phone extends JFrame {

    
    private JPanel controlPanel = new JPanel();
    private JFrame frame = new JFrame();

    private static final long serialVersionUID = 1L;
    private static String laf = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    private String msg;
    private String Chemin = "c:\\GCOBAR\\";
    private String bdd = Chemin + Utilitaire.InitBdd() + ".accdb";
    Map<String, Object> parameters = new HashMap<String, Object>();

    private gestion_imp imp = new gestion_imp();
     private JProgressBar progressBar;
     
        ArrayList <String>list_dimension= new ArrayList<String>() ;
        ArrayList <String>list_serial= new ArrayList<String>() ;

    private JPanel pan_article = new JPanel();
    private JPanel pan_article_comb = new JPanel();
    private JPanel pan_article_lab = new JPanel();
    private JLabel article_lab = new JLabel("Liste des Articles");
    private List<String> list_ar;
    private jcombo article_comb;
    
    String report;
    ArrayList <String> listrc= new ArrayList<String>() ;

    private JPanel pan_color = new JPanel();
    private JPanel pan_color_comb = new JPanel();
    private JPanel pan_color_lab = new JPanel();
    private JLabel color_lab = new JLabel("Liste des Couleur");
    private List<String> list_c;
    private JComboBox color_comb=new JComboBox();
    
    
    private JPanel pan_num = new JPanel();
    private JPanel pan_num_comb = new JPanel();
    private JPanel pan_num_lab = new JPanel();

    private JLabel num_lab = new JLabel("ARPT");


    Calendar calendar = new GregorianCalendar();
    int year = calendar.get(Calendar.YEAR);

    private JTextField num_jtext=new JTextField("/TR/AGR/PC/ARPT/"+year);

    private JPanel pan_dimension = new JPanel();
    private JPanel pan_dimension_lab = new JPanel();
    private JPanel pan_dimension_comb = new JPanel();
    private JLabel dimension_lab = new JLabel("Etiquette dimension");
    private JComboBox dimension_comb= new JComboBox();
    
    
    private JPanel pan_serial = new JPanel();
    private JPanel pan_serial_jtext = new JPanel();
    private JPanel pan_serial_lab = new JPanel();
    private JLabel serial_lab = new JLabel("Serial Number");
    
    private JTextField serial_jtext = new JTextField();
    
    private JPanel pan_code = new JPanel();
    private JPanel pan_code_jtext = new JPanel();
    private JPanel pan_code_lab = new JPanel();
    private JLabel code_lab = new JLabel("Code");
    
    private JTextField code_jtext = new JTextField();

    
    private JPanel pan_model = new JPanel();
    private JPanel pan_model_jtext = new JPanel();
    private JPanel pan_model_lab = new JPanel();
    private JLabel model_lab = new JLabel("Modèle");
    
    private JTextField model_jtext = new JTextField();


    private JPanel pan_cont_lab = new JPanel();
    private JPanel pan_cont = new JPanel();
    private JPanel pan_cont_jtext = new JPanel();
    private JLabel cont_lab = new JLabel("Conteur");
    
    private JTextField cont_jtext = new JTextField();



    private JPanel pan = new JPanel();
    private JPanel pan_general = new JPanel();
    private JPanel pan_form = new JPanel();
    private JPanel pan_gener = new JPanel();

    private JPanel panel;
    

    private JPanel pan_button = new JPanel();

    private JButton but_sauv = new JButton("Ajouter");
    private JButton valid_ajou = new JButton("Valider");
    
    private JButton imp_etq = new JButton("Imprimer L'étiquette");
    //private JButton imp_arpt = new JButton("Imprimer ARPT");
    private JButton retour = new JButton("Retour");
    
    private String conteur = null;
    
    gestion_article art=new gestion_article();
    
    
    int diff;
    int moy;
    int co;
    Thread att; 
    
    NumberFormat format = new DecimalFormat("00000");

    
    etiquette_phone(final String log) {
        final menu fr = new menu(log);
        fr.setVisible(false);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
        setIconImage(img);
        selectioncomb.windows(this, log, laf);
        composant(log);
    }

    @SuppressWarnings("deprecation")
    public void composant(final String logi_prio) {
    
    
          frame.setUndecorated(true);

          color_comb.setEditable(true);
          
          
            dimension_comb.addItem("--- Sélectionner la dimension de l'etiquette ----");

            list_dimension=imp.select_dimension_etq("etq_portable");
              
               for(int i=0;i<list_dimension.size();i++)
               {
                      //Pour affecter une valeur de base de donn?es ? un Combobox 
                   dimension_comb.addItem(list_dimension.get(i));
                   
               }
    
          
    //  imp.select_conteur();6+66
//      imp.conteur(date_picker.getDate());
        
        list_ar = new ArrayList<String>(
        Arrays.asList(new String[] { "---Selectionner un article-----" }));
        article_comb = new jcombo(list_ar.toArray());
        selectioncomb.selectarticle_etqphone(article_comb, this, logi_prio);
        
        article_comb.setSelectedIndex(0);
          list_c = new ArrayList<String>(Arrays.asList(new String[]{"---Selectionner une couleur-----"}));
          color_comb.addItem("---Selectionner une couleur-----");
          list_c=imp.select_color();
               for(int i=0;i<list_c.size();i++)
               {
                      //Pour affecter une valeur de base de donn?es ? un Combobox 
                   color_comb.addItem(list_c.get(i));
                   
               }
        
        color_comb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    boolean ex=produit.exist(color_comb,color_comb.getEditor().getItem().toString());
                    if((ex==false))
                    {
                        int cont =color_comb.getItemCount()+2;
                        color_comb.addItem(color_comb.getEditor().getItem().toString());
                    }
                                    }});
        
        if (!selectioncomb.prv.contains("etiquette_phone"))
            selectioncomb.prv.add("etiquette_phone");

        panel = new JPanel() {
            /***  */
            private static final long serialVersionUID = 1L;
            public void paintComponent(Graphics g) {
                ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("menu.png"));
                img.paintIcon(this, g, 0, 0);
            }
        };

        final Font police2 = new Font("Comic Sans MS", Font.BOLD | Font.ITALIC,15);

        code_jtext.disable();
        article_comb.enable();
        model_jtext.disable();
        serial_jtext.disable();
    
        


        LineBorder border = new LineBorder(Color.white, 1, true);
        TitledBorder titl2 = new TitledBorder(border, "Etiquette portable",
                TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION,
                police2, Color.white);
        pan.setBorder(titl2);

        but_sauv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Ajouter",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan.setBorder(titl2);
                
                article_comb.enable();
                color_comb.enable();
                
                but_sauv.setVisible(false);
                retour.setVisible(true);
                valid_ajou.setVisible(true);
                imp_etq.setVisible(false);
                //imp_arpt.setVisible(false);

                String[] art = article_comb.getSelectedItem().toString().split(" ");
                String arti = art[0]; // 004
            //   int indic=imp.select_indice(arti);

             // imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);
        

            }
        });
        num_jtext.addFocusListener(
                new FocusListener() {
                        @Override
            public void focusGained(FocusEvent arg0) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void focusLost(FocusEvent evt) {
                String code=null;
                 int n = 4; // nbre de caract?res
                  int length = num_jtext.getText().length();
                  String anne = num_jtext.getText().substring(length -n, length);
                code = "SN" + model_jtext.getText() +anne+conteur;
                serial_jtext.setText(code);
                }
            });

        article_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code=null;
                if (article_comb.getSelectedIndex() == 0) {
                    article_comb.setSelectedIndex(0);
                    model_jtext.setText("");
                    color_comb.setSelectedIndex(0);
                    
                    code_jtext.setText("");
                    cont_jtext.setText("");
                     int n = 4; // nbre de caract?res
                      int length = num_jtext.getText().length();
                     String anne = num_jtext.getText().substring(length -n, length);
                     conteur="00000";
                    code = "SN" + model_jtext.getText() +anne+conteur;
                    serial_jtext.setText(code);
                } else {
            
                 int n = 4; // nbre de caract?res
                  int length = num_jtext.getText().length();
                  String anne = num_jtext.getText().substring(length -n, length);
                  if (article_comb.getSelectedItem()!=null) {
                String[] art = article_comb.getSelectedItem().toString().split(" ");
                String arti = art[0]; // 004
                  
                String model=imp.select_model(arti);
                model_jtext.setText(model);
                code_jtext.setText(model+"/"+arti);
                conteur =imp.afficher_conteurportable(arti);
                code = "SN" + model_jtext.getText() +anne+conteur;
                serial_jtext.setText(code);
                  }
            }   
                
                
            }});

    

        valid_ajou.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                att= new Thread(){
                public void run() {
                	list_serial.clear();
                msg = "";
                String[] art = article_comb.getSelectedItem().toString().split(" ");
                String arti = art[0]; // 004
                String des=art[1];
                String code;
                conteur =imp.afficher_conteurportable(arti);
                 int n1 = 4; // nbre de caract?res
                  int length1 = num_jtext.getText().length();
                  String anne1 = num_jtext.getText().substring(length1 -n1, length1);
            //  conteur =imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);65654
                //System.out.println(date_picker.getDate()+" "+anne+" "+" "+week+" "+jour);
                
                
                 int n = 4; // nbre de caract?res
                  int length = num_jtext.getText().length();
                 String anne = num_jtext.getText().substring(length -n, length);
                System.out.println("eee"+anne+"rrrr");

                if (article_comb.getSelectedIndex() == 0) {
                    msg += "Veuillez selectionner un article \n";
                }
                if (code_jtext.getText().equals("")) {
                    msg += "Veuillez remplir le code \n";
                }
                if (serial_jtext.getText().equals("")) {
                    msg += "Veuillez remplir  le serial number  \n";
                }
                else if (color_comb.getSelectedIndex() == 0) {
                    msg += "Veuillez selectionner une couleur \n";
                } else if (cont_jtext.getText().equals("")) {
                    msg += "Veuillez remplir le conteur \n";
                } else if (isValid(cont_jtext.getText()) == false) {
                    msg += "Le compteur doit etre un nombre \n";
                }
                
                else if (anne.equals("    ")) {
                    msg += "veillez remplir l'année \n";
                }
                
                if (msg.equals("")) {  
                //  System.out.println(serial_jtext.getText()..length());
                    String etq=null;
                    
                     retour.setEnabled(false);
                     valid_ajou.setEnabled(false);
                    co = Integer.parseInt(cont_jtext.getText()); 
                    int af_cont = Integer.parseInt(conteur);
                    progressBar = new JProgressBar(af_cont,af_cont+co-1);
                    progressBar.setValue(0);
                    
                    JLabel titr=new JLabel("la création de "+co+" etiquette");
                    titr.setFont( titr.getFont().deriveFont(Font.BOLD|Font.ITALIC) );
                    frame.add(controlPanel);
                    titr.setFont(police2);
                    progressBar.setStringPainted(true);
                    controlPanel.add(titr);
                    controlPanel.add(progressBar);

                    progressBar.setPreferredSize( new Dimension (200, 25));
                    frame.setSize(300, 70);
                    frame.setLocationRelativeTo(null);
                
                    
                    
                
                
                        valid_ajou.setVisible(false);
                        but_sauv.setVisible(true);
                        retour.setVisible(false);
                        JOptionPane.showMessageDialog(null, "l'étiquette a " + des  + "  a été bien ajouté");
                      retour.setEnabled(true);
                      valid_ajou.setEnabled(true);
                      imp_etq.setVisible(true);
                    //  imp_arpt.setVisible(true);

                      

                       for(int i=af_cont;i<=af_cont+co-1;i++ ){
                           
                           code = "SN" + model_jtext.getText() +anne1+format.format(i);
                         imp.ajout_etiqphone(arti,color_comb.getSelectedItem().toString(),
                              code_jtext.getText(),code, num_jtext.getText());
                         serial_jtext.setText(code);
                         list_serial.add(code);
                      progressBar.setValue(i);
                        progressBar.setStringPainted(true);
                        frame.validate();
                        frame.setVisible(true);
                        
                    
                       }
                       controlPanel.remove(titr);
                       controlPanel.remove(progressBar);
                        frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, msg);
                }   }}; 
                att.start();
            }
        });

        dimension_comb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if(dimension_comb.getSelectedIndex()!=0)
                report=imp.select_report(dimension_comb.getSelectedItem().toString(),"etq_portable");
            }});
        
        
        imp_etq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {// dispose();
                att= new Thread(){
                    public void run(){
                         if(dimension_comb.getSelectedIndex()!=0){
                        String serial_text=serial_jtext.getText().substring(0, 8);
                        //int indice=article_comb.getSelectedIndex();
                        
                        //String[] arti = article_comb.getSelectedItem().toString().split(" ");
                        String code = code_jtext.getText().replaceAll("\\/","_"); // 004
                        String parcour ;String model;
                        int n = 5; // nbre de caract?res
                         int length = serial_jtext.getText().length();
                        int conteur=Integer.parseInt(serial_jtext.getText().substring(length -n, length))-1;
                       
                         String serial = serial_text +""+conteur;
                    
                parcour = "C:\\GCOBAR\\pdf\\etq_portable\\"+dimension_comb.getSelectedItem()+code+"_"+color_comb.getSelectedItem()+
                            "_"+serial+"_"+cont_jtext.getText()+"_"+"_Etiquette_portable"+".pdf";
                 model = "C:\\GCOBAR\\CODE\\"+report;
                //imp.update_imp_s("1");
            //  System.out.println("ddddd"+parcour);
                UIManager.put("nimbusOrange", (new Color(70,130,180)));

                try {
                    
                    //  
                    new BufferedReader(new FileReader(
                            "C:\\GCOBAR\\pdf\\etq_portable\\"+dimension_comb.getSelectedItem()+code+"_"+color_comb.getSelectedItem()+
                            "_"+serial+"_"+cont_jtext.getText()+"_"+"_Etiquette_portable"+".pdf"));
                    
                    try {
                        Desktop.getDesktop().open(new File(
                                "C:\\GCOBAR\\pdf\\etq_portable\\"+dimension_comb.getSelectedItem()+code+"_"+color_comb.getSelectedItem()+
                            "_"+serial+"_"+cont_jtext.getText()+"_"+"_Etiquette_portable"+".pdf"));
                        
                    } catch (IOException p) {
                        // TODO Auto-generated catch block
                        p.printStackTrace();

                    }}
                
                catch (FileNotFoundException fnfe) {
                    
                    if(dimension_comb.getSelectedItem().toString().contains("ARPT"))
                    {
                    listrc.add(num_jtext.getText());}
                    else {
                        listrc=list_serial;
                    }
                    System.out.println(listrc);
                    
                    selectioncomb.imprimer("serial",listrc, bdd, parcour,model);
                    listrc.clear();
                                   

                     //controlPanel.remove(progressBar);
                }}
                  else{
                      
                      JOptionPane.showMessageDialog(null, "Vous devez choisir un type d'étiquette");
                  }
                    }}; 
                    
                    att.start();
                    
            }
        });
        
        
        
//      imp_arpt.addActionListener(new ActionListener() {
//          public void actionPerformed(ActionEvent event) {// dispose();
//              att= new Thread(){
//                  public void run(){
//                  
//             String parcour ;String model;
//                     
//              String arpt=num_jtext.getText().replaceAll("\\/", "_");
//              parcour = "C:\\GCOBAR\\pdf\\etq_portable\\ARPT\\"+arpt+"_Etiquette_portable_arpt"+".pdf";
//              model = "C:\\GCOBAR\\CODE\\etiquette_portable_arpt.jrxml";
//              //imp.update_imp_s("1");
//                UIManager.put("nimbusOrange", (new Color(70,130,180)));
//                
//                File fichier = new File(parcour);
//                  fichier.delete();
//                  //int indice=article_comb.getSelectedIndex();                           
//                               
//         UIManager.put("nimbusOrange", (new Color(70,130,180)));
//
//          try {
//@SuppressWarnings("resource")
//BufferedReader bfr=new BufferedReader(new FileReader( parcour));
//
//  bfr.close();
//      
//              try {
//                  
//      Desktop.getDesktop().open(new File( parcour));
//          
//              } catch (IOException p) {
//                  // TODO Auto-generated catch block
//                  
//                  //p.printStackTrace();
//                  
//              }
//}
////            
//          
////            
////                exemple.delete();
////               
////                try {
////                    
////                    new BufferedReader(new FileReader(
////                            "C:\\GCOBAR\\pdf\\etq_portable\\ARPT\\"+arpt+"_Etiquette_portable_arpt"+".pdf"));
////                    
////                    try {
////                        Desktop.getDesktop().open(new File(
////                                "C:\\GCOBAR\\pdf\\etq_portable\\ARPT\\"+arpt+"_Etiquette_portable_arpt"+".pdf"));
////                        
////                    } catch (IOException p) {
////                        // TODO Auto-generated catch block
////                        p.printStackTrace();
////
////                    }}
//              
//              catch (FileNotFoundException fnfe) {
//                  
//                  
//                  parameters.put("ARPT",num_jtext.getText());
//                  selectioncomb.imprimer( bdd, parcour,model,parameters);
//              }
//          catch (IOException e) {
//              // TODO Auto-generated catch block
//              e.printStackTrace();
//          }   
//                  }}; 
//                  
//                  att.start();
//                  
//          }
//      });
        
        
        but_sauv.setVisible(true);
        imp_etq.setVisible(false);
    //  imp_arpt.setVisible(false);

        valid_ajou.setVisible(false);
        retour.setVisible(false);

        retour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LineBorder border = new LineBorder(Color.white, 1, true);
                TitledBorder titl2 = new TitledBorder(border, "Edition",
                        TitledBorder.DEFAULT_POSITION,
                        TitledBorder.DEFAULT_POSITION, police2, Color.white);
                pan.setBorder(titl2);
                article_comb.setSelectedIndex(1);
                color_comb.setSelectedIndex(1);
                model_jtext.setText("");
                code_jtext.setText("");
                cont_jtext.setText("");
                but_sauv.setVisible(true);
                imp_etq.setVisible(false);
            //  imp_arpt.setVisible(false);

                retour.setVisible(false);
                valid_ajou.setVisible(false);
                color_comb.enable();
                article_comb.enable();
                code_jtext.disable();
                
                String[] art = article_comb.getSelectedItem().toString().split(" ");
                String arti = art[0]; // 004
                // int indic=imp.select_indice(arti);
            //  imp.afficher_conteur(arti,code_chaine,date_picker.getDate(),indic);

                
                //action_chaine();

            }
        });
        
        



        try {
            UIManager.setLookAndFeel(laf);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        article_lab.setForeground(Color.white);
        model_lab.setForeground(Color.white);
        color_lab.setForeground(Color.white);
        serial_lab.setForeground(Color.white);
        code_lab.setForeground(Color.white);
        cont_lab.setForeground(Color.white);
        num_lab.setForeground(Color.white);
        dimension_lab.setForeground(Color.white);
        
        
        code_lab.setFont(police2);
        article_lab.setFont(police2);
        model_lab.setFont(police2);
        num_lab.setFont(police2);
        code_lab.setFont(police2);
        serial_lab.setFont(police2);
        cont_lab.setFont(police2);
        color_lab.setFont(police2);
        dimension_lab.setFont(police2);


        /*************************code_com pour le code commercial************/
        
        
        
        pan_article.add(pan_article_lab);
        pan_article_lab.add(article_lab);
        pan_article.add(pan_article_comb);
        pan_article_comb.add(article_comb);

        pan_article.setOpaque(false);
        pan_article_lab.setOpaque(false);
        pan_article_comb.setOpaque(false);
        
        pan_model.add(pan_model_lab);
        pan_model_lab.add(model_lab);
        pan_model.add(pan_model_jtext);
        pan_model_jtext.add(model_jtext);
        
        pan_num.add(pan_num_lab);
        pan_num_lab.add(num_lab);
        pan_num.add(pan_num_comb);
        pan_num_comb.add(num_jtext);

        pan_model.setOpaque(false);
        pan_model_lab.setOpaque(false);
        pan_model_jtext.setOpaque(false);
        
        pan_num.setOpaque(false);
        pan_num_lab.setOpaque(false);
        pan_num_comb.setOpaque(false);

        pan_color.add(pan_color_lab);
        pan_color_lab.add(color_lab);
        pan_color.add(pan_color_comb);
        pan_color_comb.add(color_comb);

        pan_color.setOpaque(false);
        pan_color_lab.setOpaque(false);
        pan_color_comb.setOpaque(false);

        
        pan_code.add(pan_code_lab);
        pan_code_lab.add(code_lab);
        pan_code.add(pan_code_jtext);
        pan_code_jtext.add(code_jtext);
        
        pan_serial.add(pan_serial_lab);
        pan_serial_lab.add(serial_lab);
        pan_serial.add(pan_serial_jtext);
        pan_serial_jtext.add(serial_jtext);
        
        pan_serial.setOpaque(false);
        pan_serial_lab.setOpaque(false);
        pan_serial_jtext.setOpaque(false);
        
        pan_code.setOpaque(false);
        pan_code_lab.setOpaque(false);
        pan_code_jtext.setOpaque(false);
        
        
        pan_cont.add(pan_cont_lab);
        pan_cont_lab.add(cont_lab);
        pan_cont.add(pan_cont_jtext);
        pan_cont_jtext.add(cont_jtext);

        pan_cont.setOpaque(false);
        pan_cont_lab.setOpaque(false);
        pan_cont_jtext.setOpaque(false);
        
        
        pan_dimension.add(pan_dimension_lab);
        pan_dimension_lab.add(dimension_lab);
        pan_dimension.add(pan_dimension_comb);
        pan_dimension_comb.add(dimension_comb);

        pan_dimension.setOpaque(false);
        pan_dimension_lab.setOpaque(false);
        pan_dimension_comb.setOpaque(false);

        pan_button.add(retour);
        pan_button.add(valid_ajou);
        pan_button.add(but_sauv);
        // pan_button.add(but_modif);
        pan_button.add(imp_etq);
    //  pan_button.add(imp_arpt);

        pan_button.setOpaque(false);

        
        pan_form.add(pan_article);
        pan_form.add(pan_model);

        pan_form.add(pan_color);
        pan_form.add(pan_num);

        //pan_form.add(pan_model);
        pan_form.add(pan_code);
        pan_form.add(pan_serial);
        pan_form.add(pan_cont);
        pan_form.add(pan_dimension);

        pan_general.add(pan_button);
        pan_form.setOpaque(false);

        pan_gener.add(pan_form);
        pan.add(pan_gener);
        pan.add(pan_general);

        panel.add(pan);
        pan_gener.setOpaque(false);
        pan_general.setOpaque(false);
        pan.setOpaque(false);
        
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        pan_gener.setLayout(new BoxLayout(pan_gener, BoxLayout.X_AXIS));

        pan_form.setLayout(new BoxLayout(pan_form, BoxLayout.Y_AXIS));

        but_sauv.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

        retour.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        imp_etq.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        //imp_arpt.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

        valid_ajou.setFont(new Font("Comic Sans MS", Font.BOLD, 13));

        but_sauv.setPreferredSize(new Dimension(120, 33));
        imp_etq.setPreferredSize(new Dimension(170, 33));
        //imp_arpt.setPreferredSize(new Dimension(170, 33));

        retour.setPreferredSize(new Dimension(120, 33));
        valid_ajou.setPreferredSize(new Dimension(120, 33));


        
        article_comb.setPreferredSize(new Dimension(210, 30));
        color_comb.setPreferredSize(new Dimension(210, 30));
        model_jtext.setPreferredSize(new Dimension(210, 30));
        num_jtext.setPreferredSize(new Dimension(210, 30));
        serial_jtext.setPreferredSize(new Dimension(210, 30));
        code_jtext.setPreferredSize(new Dimension(210, 30));
        cont_jtext.setPreferredSize(new Dimension(210, 30));
        dimension_comb.setPreferredSize(new Dimension(210, 30));

        pan_article.setLayout(new BoxLayout(pan_article, BoxLayout.X_AXIS));
        

        pan_num.setLayout(new BoxLayout(pan_num, BoxLayout.X_AXIS));
        pan_model.setLayout(new BoxLayout(pan_model, BoxLayout.X_AXIS));
        pan_color.setLayout(new BoxLayout(pan_color, BoxLayout.X_AXIS));
        pan_code.setLayout(new BoxLayout(pan_code, BoxLayout.X_AXIS));
        pan_serial.setLayout(new BoxLayout(pan_serial, BoxLayout.X_AXIS));
        pan_cont.setLayout(new BoxLayout(pan_cont, BoxLayout.X_AXIS));
        pan_dimension.setLayout(new BoxLayout(pan_dimension, BoxLayout.X_AXIS));

        pan_dimension_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_dimension_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(0, 85, 0, 0));
        pan_dimension_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        pan_article_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_article_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_article_lab.setBorder(BorderFactory.createEmptyBorder(0, 90, 0, 0));
        pan_article_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
    
        
        pan_num_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_num_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_num_lab.setBorder(BorderFactory.createEmptyBorder(0, 180, 0, 0));
        pan_num_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_model_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_model_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_model_lab.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
        pan_model_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    

        pan_color_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_color_comb.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_color_lab.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, -15));
        pan_color_comb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_code_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_code_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_code_lab.setBorder(BorderFactory.createEmptyBorder(0, 190, 0, 0));
        pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        pan_serial_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_serial_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_serial_lab.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
        pan_serial_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        
        pan_cont_lab.setLayout(new FlowLayout(FlowLayout.CENTER));
        pan_cont_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_cont_lab.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
        pan_cont_jtext.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        setTitle("imprime");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {

                    new etiquette_phone("7");
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean isValid(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }



//  public void serialnumber() {
//      code_jtext.addKeyListener(new KeyAdapter() {
//          public void keyReleased(KeyEvent e) {
//
//              assemblage_jtext.setText(imp.contass);
//              assqc_jtext.setText(imp.contqc);
//              actqc_jtext.setText(imp.actqcc);
//              imp.select_fiche(code_jtext.getText());
//              chaine_comb.setSelectedItem(imp.chaine + " "
//                      + imp.chaine_design);
//              model_comb.setSelectedItem(imp.cpost + " " + imp.intitule);
//              article_comb.setSelectedItem(imp.article + " "
//                      + imp.designation);
//
//          }
//      });
//
//  }


}
