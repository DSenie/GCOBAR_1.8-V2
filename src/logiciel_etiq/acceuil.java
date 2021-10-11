package logiciel_etiq;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class acceuil extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public String Chemin = "C:\\GCOBAR\\";
	private static String laf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	public String bdd = Chemin+Utilitaire.InitBdd()+".accdb";	
	JLabel label1 = new JLabel(" Mot de passe ");
	JLabel label2 = new JLabel(" N� Operateur ");
	private JPasswordField mPasse = new JPasswordField();
	private JFormattedTextField  numOPERA= new JFormattedTextField();
	private JButton boutonAnnuler = new JButton("Annuler");
	//private JButton boutonConfirm = new JButton("Confirmer");
	String password; 
	String user;
	String login;
	String  vcde,vsigle, vexercice ;
	public void close(){
        this.dispose();
        }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						    UIManager.setLookAndFeel(laf);
							acceuil frame = new acceuil();
					        SwingUtilities.updateComponentTreeUI( frame);
					        Toolkit kit = Toolkit.getDefaultToolkit();
					        Image img = kit.getImage(getClass().getClassLoader().getResource("icon.png"));
					        frame.setIconImage(img);
							frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*** Create the frame.*/
	public acceuil() {
		String query       = "SELECT Cde,Desunite, Exercice FROM Unite ";
        vcde = CConnect.Requete(query,bdd).get(0);
        vsigle = CConnect.Requete(query,bdd).get(1);
        vexercice = CConnect.Requete(query,bdd).get(2);

//************************************************************************** SAISIE MOT DE PASSE ************************************************************************************//          		
		JPanel pan2 = new JPanel(){   
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){
		            ImageIcon img = new ImageIcon( getClass().getClassLoader().getResource("img7.png"));
		            img.paintIcon(this, g,0, 0);
					//g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

}  };
setTitle("Gestion de Production" );


JButton boutonConfirm = new JButton("Confirmer");


		JLabel label1 = new JLabel("Mot de passe ");
		JLabel label2 = new JLabel(" Nom utilisateur ");
		Font police1 = new Font(" Time New Roman", Font.BOLD|Font.ITALIC, 18);
	    label1.setFont(police1);
	    label2.setFont(police1);
        Color c1 = new Color(255,255,255);
        label1.setForeground(c1);
        label2.setForeground(c1);
      
	//pour eliminer la fermeture par defaut
		setUndecorated(true);
		setSize(800, 500);
		setLocationRelativeTo(null);
         pan2.setLayout (null);
         label2.setBounds ( 230 , 240 , 150 , 50 ) ;
         numOPERA.setBounds ( 390 , 260 , 160 , 30 ) ;
         
         label1.setBounds ( 230 , 300 , 270 , 50 ) ;
         mPasse.setBounds (390 , 310 , 160 , 30 ) ;
         pan2.add(label2);
         pan2.add(numOPERA);
		 pan2.add(label1);
		 pan2.add(mPasse);
         getContentPane().add(pan2);
	     this.setVisible(true);
			 
		 Font police2 = new Font(" Time New Roman", Font.BOLD|Font.ITALIC,15);
		 boutonAnnuler.setFont(police2);
		 Color c2 = new Color(0,0,0);//(redValue,greenValue,BlueValue)
		 
		 
		 boutonAnnuler.setForeground(c2);
		 boutonAnnuler.setBounds ( 330 , 370 , 125 , 30 ) ;
		 boutonAnnuler.addActionListener( new ActionListener() { 
					                         public void actionPerformed(ActionEvent e) { 
					                         
						                                                                 System.exit(0);				                                                                
					                                                                    }
				                             });
		   
		    
	// confuirmer
		boutonConfirm.setFont(police2);
		boutonConfirm.setForeground(c2);
		boutonConfirm.setBounds ( 460 , 370 , 125 , 30 ) ;
		getRootPane().setDefaultButton(boutonConfirm); 
		boutonConfirm.addActionListener(new ActionListener() {
					                          public void actionPerformed(ActionEvent event) {
					                                            	               action();   
					                                                                                              }
				                                                   });
		pan2.add(boutonConfirm);
        pan2.add(boutonAnnuler);
		    
        // JLabel label3 = new JLabel( (String) CConnect.Requete(query1,wini).get(0));//vsigle
	     JLabel label3 = new JLabel( "IE / "+(String) vsigle);
	     JLabel label4 = new JLabel("CopyRight � ENIE\\DIIE\\Dpt Informatique,2015");
	    // JLabel label5 = new JLabel("Exercice  " + CConnect.Requete(query1,wini).get(1));
	     JLabel label5 = new JLabel("Exercice  " + vexercice );
	     label3.setBounds ( 18 , 450 , 205 , 30 ) ;
	     label4.setBounds ( 220 , 450 , 400 , 30 ) ;
	     label5.setBounds ( 670 , 450 , 125 , 30 ) ;
	     label3.setFont(police1);
	     label4.setFont(police1);
	     label5.setFont(police1);
	     label3.setForeground(c1);
         label4.setForeground(c1);
         label5.setForeground(c1);
	     pan2.add(label3);
	     pan2.add(label4);
	     pan2.add(label5);
	}
	
	   void action() {
         @SuppressWarnings("deprecation")
		String query = "SELECT password,login FROM user where login='"+numOPERA.getText()+"' and password='"+mPasse.getText()+"' ";
         if(CConnect.Requete(query, bdd).size()>1){
         login=(String) CConnect.Requete(query,bdd).get(1);
         new menu(login).setVisible(true); 
         close();
         }
         else{
         JOptionPane.showMessageDialog(null, "password ou login  erron� ", "Attention", JOptionPane.ERROR_MESSAGE);
           }
	}

}
