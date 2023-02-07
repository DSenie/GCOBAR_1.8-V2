package logiciel_etiq.View.produit;

import logiciel_etiq.Controller.gestion_produit;
import logiciel_etiq.View.combobx.jcombo;
import logiciel_etiq.View.generale;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;



public class produit extends generale {


	//******************declaration des composnat **********************************************************

	private JPanel pan_numcontrat=new JPanel();
	private JPanel pan_numcontrat_lab=new JPanel();
	private JPanel pan_numcontrat_jtext=new JPanel();
	private JLabel numcontrat_lab=new JLabel("N Contrat");
	private JTextField numcontrat=new JTextField();

    private JPanel pan_gauche=new JPanel();
    private JPanel pan_droite=new JPanel();
    private JPanel pan_gauche_droite=new JPanel();

    private JPanel pan_code=new JPanel();
    private JPanel pan_code_lab=new JPanel();
    private JPanel pan_code_jtext=new JPanel();
	private JLabel code_lab= new JLabel("Code produit ");
	private JTextField code_jtext=new JTextField();
    
    private JPanel pan_desin=new JPanel();
    private JPanel pan_desin_lab=new JPanel();
    private JPanel pan_desin_jtext=new JPanel();
	private JLabel desin_lab= new JLabel("Designation ");
	private JTextField dimension_jtext=new JTextField();

    private JPanel pan_dimension=new JPanel();
    private JPanel pan_dimension_lab=new JPanel();
    private JPanel pan_dimension_jtext=new JPanel();
	private JLabel dimension_lab= new JLabel("Dimension ");
	private JTextField desin_jtext=new JTextField();


    private JPanel pan_quantite=new JPanel();
    private JPanel pan_quantite_lab=new JPanel();
    private JPanel pan_quantite_jtext=new JPanel();
	private JTextField quantite_jtext=new JTextField();
	private JLabel quantite_lab= new JLabel("Quantite ");


    private JPanel pan_fournisseur=new JPanel();
    private JPanel pan_fournisseur_lab=new JPanel();
    private JPanel pan_fournisseur_jcom=new JPanel();
	private JLabel fournisseur_lab= new JLabel("Fournisseur ");
	private jcombo fournisseur_jcom=new jcombo(list_comb);


	private JPanel pan_produit=new JPanel();
    private JPanel pan_produit_lab=new JPanel();
    private JPanel pan_produit_jcom=new JPanel();
	private JLabel produit_lab = new JLabel("Produit");
	private jcombo produit_jcom=new jcombo(list_comb);


    private JPanel pan_type=new JPanel();
    private JPanel pan_type_lab=new JPanel();
    private JPanel pan_type_jcom=new JPanel();
    
    private JPanel pan_datelc=new JPanel();
    private JPanel pan_datelc_lab=new JPanel();
    private JPanel pan_datelc_dp=new JPanel();
	private JLabel datelc_lab= new JLabel("Date LC");
	private final JXDatePicker date_lc = new JXDatePicker();

	private JPanel pan_datelanc=new JPanel();
    private JPanel pan_datelanc_lab=new JPanel();
    private JPanel pan_datelanc_dp=new JPanel();
	private JLabel datelanc_lab= new JLabel("Date Lancement ");
	private final JXDatePicker date_lanc = new JXDatePicker();


	private JPanel pan_model_lab =new JPanel();
    private JPanel pan_model_jtext =new JPanel();
	private JLabel type_lab= new JLabel("Type  ");
	private JComboBox type_jcom= new JComboBox();

    private JPanel pan_button=new JPanel();


   // *************************** les variable**********************************
    private gestion_produit prod=new gestion_produit();


    private String msg="";
    private String part_fournisseur,part_pro;

	public produit(final String log) {


		composant(log);
	}


	private void composant(String log) {

		JPanel pan_global = generalPanle();

		replirDate(date_lc);
		replirDate(date_lanc);


		initFrame("Edition Produit", pan_global, this, log,"Gestion des produits");
		declarationButton(pan_button);
		visibiliteButton(true, false, false, false,
				false, false,false);


		//**************************************************************

		replirFournisseur(fournisseur_jcom);
		fournisseur_jcom.addActionListener(
				e -> part_fournisseur = splitcombo(fournisseur_jcom));



		ArrayList <String> list_type=prod.select_produit_type();
		remplirCombo(list_type, type_jcom, 1);
		type_jcom.setEditable(true);


		replirProduit(produit_jcom);
        produit_jcom.addActionListener(
        			e -> {
						if(produit_jcom.getSelectedIndex()==0){
							code_jtext.enable();

							code_jtext.setText("");
							desin_jtext.setText("");
							dimension_jtext.setText("");
							fournisseur_jcom.setSelectedIndex(0);
							type_jcom.setSelectedIndex(0);
							quantite_jtext.setText("0");
							numcontrat.setText("");
							date_lc.setDate(Calendar.getInstance().getTime());
							date_lanc.setDate(Calendar.getInstance().getTime());
							visibiliteButton(true, false, false, false,
									false, false,false);
						}
						else{
							if(produit_jcom.getSelectedIndex()>1){
								part_pro =splitcombo( produit_jcom);


								visibiliteButton(false, true, true, false,
										false, false,false);
								code_jtext.disable();

								prod.select_produit(part_pro);
								code_jtext.setText(prod.code);
								desin_jtext.setText(prod.desin);
								dimension_jtext.setText(prod.dimension);
								numcontrat.setText(prod.Ncontrat);
								fournisseur_jcom.setSelectedItem(prod.fournisseur);
								type_jcom.setSelectedItem(prod.type);
								quantite_jtext.setText(prod.quantite);
								date_lc.getEditor().setText(prod.datelc);
								date_lanc.getEditor().setText(prod.datelanc);

							}
						}



			});



    	retour.addActionListener(
				e -> {

					titleFrame("Edition Produit", pan_global);
					visibiliteButton(true, false, false, false,
							false, false,false);
	            	produit_jcom.setSelectedIndex(0);
	            });
    	
    	
    	modifier.addActionListener(
				e -> {
					titleFrame("Modifier Produit", pan_global);
					visibiliteButton(false, false, false, true,
							false, true,false);
				});
    	
    	
    	Supprimer.addActionListener(
				e -> {
					titleFrame("Supprimer Produit", pan_global);
					visibiliteButton(false, false, false, true,
							false, false,true);
				});
    	
    	
    	ajouter.addActionListener(
				e -> {
					titleFrame("Ajouter Produit", pan_global);
					visibiliteButton(false, false, false, true,
							true, false,false);

				});



    	 valid_ajou.addActionListener(
				 e -> {
					  msg=validchamp();
					  if (!msg.equals("")) {
						  JOptionPane.showMessageDialog(null, msg);
					  } else {
					  prod.ajouter_produit(code_jtext.getText(),desin_jtext.getText(),dimension_jtext.getText(),
					  part_fournisseur ,date_lc.getDate(),date_lanc.getDate(),
					  Integer.parseInt(quantite_jtext.getText()),String.valueOf(type_jcom.getSelectedItem()),
					  numcontrat.getText());

				  replirProduit(produit_jcom);


					  }
				 });
    	 
    	 valid_modif.addActionListener(
				 e -> {
								  msg=validchamp();
								  if (!msg.equals("")) {
									  JOptionPane.showMessageDialog(null, msg);
								  } else {

									  prod.update_produit(part_pro,desin_jtext.getText(),dimension_jtext.getText(),
									  part_fournisseur ,date_lc.getDate(),date_lanc.getDate(),
									  Integer.parseInt(quantite_jtext.getText()),String.valueOf(type_jcom.getSelectedItem()),code_jtext.getText(),numcontrat.getText());
									  replirProduit(produit_jcom);
								  }
				 });
    	 
	valid_supp.addActionListener(
			e -> {
				prod.delete_produit(code_jtext.getText(),desin_jtext.getText());
				replirProduit(produit_jcom);
			});


    	 
    	code_jtext.addActionListener(
				e -> {
					if(!code_jtext.getText().equals("")){
							produit_jcom.setSelectedItem(prod.code+" "+prod.desin);
					 }
					 else{
						  produit_jcom.setSelectedIndex(0);
					 }

				});


		code_jtext.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {

				existJtextCode(code_jtext);
			}
			public void focusGained(FocusEvent arg0) {
			}
		});





		code_jtext.addActionListener(
				e -> existJtextCode(code_jtext));


		desin_jtext.addActionListener(
				e -> existJtextCode(desin_jtext));



		titleFrame("", pan_gauche);
		titleFrame("", pan_droite);


	  	pan_produit.add(pan_produit_lab);
		pan_produit.add(pan_produit_jcom);
		pan_produit_lab.add(produit_lab);
		pan_produit_jcom.add(produit_jcom);
   	  	pan_code.add(pan_code_lab);
   	  	pan_code.add(pan_code_jtext);
   	  	pan_code_lab.add(code_lab);
   	  	pan_code_jtext.add(code_jtext);
   	  	
   	  	pan_desin.add(pan_desin_lab);
   	    pan_desin.add(pan_desin_jtext);
   	  	pan_desin_lab.add(desin_lab);
   	  	pan_desin_jtext.add(desin_jtext);
   	  	
   	    pan_dimension.add(pan_dimension_lab);
   	  	pan_dimension.add(pan_dimension_jtext);
   	  	pan_dimension_lab.add(dimension_lab);
   	  	pan_dimension_jtext.add(dimension_jtext);
   	  	
   	  	pan_fournisseur.add(pan_fournisseur_lab);
   	  	pan_fournisseur.add(pan_fournisseur_jcom);
   	    pan_fournisseur_lab.add(fournisseur_lab);
   	    pan_fournisseur_jcom.add(fournisseur_jcom);
   	    
   	    
   	  pan_numcontrat.add(pan_numcontrat_lab);
      pan_numcontrat.add(pan_numcontrat_jtext);
      pan_numcontrat_lab.add(numcontrat_lab);
      pan_numcontrat_jtext.add(numcontrat);

   	    
   	    pan_quantite.add(pan_quantite_lab);
	  	pan_quantite.add(pan_quantite_jtext);
	    pan_quantite_lab.add(quantite_lab);
	    pan_quantite_jtext.add(quantite_jtext);
	    
	    pan_type.add(pan_type_lab);
	    pan_type.add(pan_type_jcom);
	    pan_type_lab.add(type_lab);
	    pan_type_jcom.add(type_jcom);
	    
	    pan_datelc.add(pan_datelc_lab);
	    pan_datelc.add(pan_datelc_dp);
	    pan_datelc_lab.add(datelc_lab);
	    pan_datelc_dp.add(date_lc);
	    
	    pan_datelanc.add(pan_datelanc_lab);
	    pan_datelanc.add(pan_datelanc_dp);
	    pan_datelanc_lab.add(datelanc_lab);
	    pan_datelanc_dp.add(date_lanc);

	  
	    pan_global.add(pan_gauche_droite);
	    
	    pan_gauche.add(pan_produit);
	    pan_gauche.add(pan_code);
	    pan_gauche.add(pan_desin);
	    pan_gauche.add(pan_fournisseur);
	    pan_gauche.add(pan_dimension);
	    pan_gauche.add(pan_quantite);
	    
	    pan_droite.add(pan_datelanc);
	    pan_droite.add(pan_datelc);
	    pan_droite.add(pan_type);
	    pan_droite.add(pan_numcontrat);
	    
	    pan_gauche_droite.add(pan_gauche);
	    pan_gauche_droite.add(pan_droite);
	    
	    pan_global.add(pan_button);
        


	    ////************************ layout panel*****/
	    pan_global.setLayout(new BoxLayout(pan_global,BoxLayout.Y_AXIS));
	    pan_gauche_droite.setLayout(new BoxLayout(pan_gauche_droite,BoxLayout.X_AXIS));
	    pan_gauche.setLayout(new BoxLayout(pan_gauche,BoxLayout.Y_AXIS));
	    pan_droite.setLayout(new BoxLayout(pan_droite,BoxLayout.Y_AXIS));
	    pan_produit.setLayout(new BoxLayout(pan_produit,BoxLayout.X_AXIS));
	    pan_code.setLayout(new BoxLayout(pan_code,BoxLayout.X_AXIS));
	    pan_desin.setLayout(new BoxLayout(pan_desin,BoxLayout.X_AXIS));
	    pan_dimension.setLayout(new BoxLayout(pan_dimension,BoxLayout.X_AXIS));
	    pan_fournisseur.setLayout(new BoxLayout(pan_fournisseur,BoxLayout.X_AXIS));
	    pan_quantite.setLayout(new BoxLayout(pan_quantite,BoxLayout.X_AXIS));
		pan_datelc.setLayout(new BoxLayout(pan_datelc,BoxLayout.X_AXIS));
	    pan_datelanc.setLayout(new BoxLayout(pan_datelanc,BoxLayout.X_AXIS));
	    pan_type.setLayout(new BoxLayout(pan_type,BoxLayout.X_AXIS));
	    pan_numcontrat.setLayout(new BoxLayout(pan_numcontrat,BoxLayout.X_AXIS));

	    pan_code_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_code_jtext.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_produit_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_produit_jcom.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_desin_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_desin_jtext.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_dimension_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_dimension_jtext.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_quantite_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_quantite_jtext.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_fournisseur_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_fournisseur_jcom.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_type_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_type_jcom.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_datelc_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_datelc_dp.setLayout(new FlowLayout(  FlowLayout.LEFT));

	    pan_datelanc_lab.setLayout(new FlowLayout(  FlowLayout.RIGHT));
	    pan_datelanc_dp.setLayout(new FlowLayout(  FlowLayout.LEFT));
	    
	    pan_numcontrat_lab.setLayout( new FlowLayout( FlowLayout.RIGHT));
        pan_numcontrat_jtext.setLayout(new FlowLayout(FlowLayout.LEFT));



	   //****************enlever l 'arriere plan *******************************************
	    pan_global.setOpaque(false);
	    pan_gauche_droite.setOpaque(false);
	    pan_gauche.setOpaque(false);
	    pan_droite.setOpaque(false);

	    pan_numcontrat.setOpaque(false);
        pan_numcontrat_lab.setOpaque(false);
        pan_numcontrat_jtext.setOpaque(false);
	    
	    pan_code.setOpaque(false);
	    pan_code_lab.setOpaque(false);
	    pan_code_jtext.setOpaque(false);
	    
	    pan_desin.setOpaque(false);
	    pan_desin_lab.setOpaque(false);
	    pan_desin_jtext.setOpaque(false);
	    
	    pan_dimension.setOpaque(false);
	    pan_dimension_lab.setOpaque(false);
	    pan_dimension_jtext.setOpaque(false);
	    
	    pan_quantite.setOpaque(false);
	    pan_quantite_lab.setOpaque(false);
	    pan_quantite_jtext.setOpaque(false);
	    
	    pan_fournisseur.setOpaque(false);
	    pan_fournisseur_lab.setOpaque(false);
	    pan_fournisseur_jcom.setOpaque(false);

	    pan_produit.setOpaque(false);
	    pan_produit_lab.setOpaque(false);
	    pan_produit_jcom.setOpaque(false);
	    
	    pan_type.setOpaque(false);
	    pan_type_lab.setOpaque(false);
	    pan_type_jcom.setOpaque(false);
	    
	    pan_datelc.setOpaque(false);
	    pan_datelc_lab.setOpaque(false);
	    pan_datelc_dp.setOpaque(false);
	    
	    pan_datelanc.setOpaque(false);
	    pan_datelanc_lab.setOpaque(false);
	    pan_datelanc_dp.setOpaque(false);
	    pan_model_lab.setOpaque(false);
	    pan_model_jtext.setOpaque(false);
	    pan_button.setOpaque(false);


		///************************** coponent size  **************/
		styleComponent(produit_jcom);
		styleComponent(code_jtext);
		styleComponent(desin_jtext);
		styleComponent(dimension_jtext);
		styleComponent(quantite_jtext);
		styleComponent(fournisseur_jcom);
		styleComponent(type_jcom);
		styleComponent(date_lc);
		styleComponent(date_lanc);
		styleComponent(numcontrat);


		///************************** label style  **************/

		styleLabel(numcontrat_lab);
		styleLabel(code_lab);
		styleLabel(desin_lab);
		styleLabel(dimension_lab);
		styleLabel(quantite_lab);
		styleLabel(fournisseur_lab);
		styleLabel(produit_lab);
		styleLabel(datelc_lab);
		styleLabel(datelanc_lab);
		styleLabel(type_lab);



       ///************************** component border**************/
		pan_produit_lab.setBorder(BorderFactory.createEmptyBorder(20,57, 0,0));
		pan_produit_jcom.setBorder(BorderFactory.createEmptyBorder(20, 40, 0,0));

		pan_code_lab.setBorder(BorderFactory.createEmptyBorder(10, 20, 0,0));
		pan_code_jtext.setBorder(BorderFactory.createEmptyBorder(10, 30, 0,0));

		pan_desin_lab.setBorder(BorderFactory.createEmptyBorder(10, 20, 0,0));
		pan_desin_jtext.setBorder(BorderFactory.createEmptyBorder(10, 44, 0,0));

		pan_fournisseur_lab.setBorder(BorderFactory.createEmptyBorder(10, 20, 0,0));
		pan_fournisseur_jcom.setBorder(BorderFactory.createEmptyBorder(10, 44, 0,0));

		pan_dimension_lab.setBorder(BorderFactory.createEmptyBorder(10, 25, 0,0));
		pan_dimension_jtext.setBorder(BorderFactory.createEmptyBorder(10, 50, 0,0));

		pan_quantite.setBorder(BorderFactory.createEmptyBorder(10, 25, 0,0));
		pan_quantite_jtext.setBorder(BorderFactory.createEmptyBorder(10, 50, 0,0));


       pan_datelc_lab.setBorder(BorderFactory.createEmptyBorder(20, 40, 0,0));
       pan_datelc_dp.setBorder(BorderFactory.createEmptyBorder(20, 50, 0,0));

       pan_datelanc_lab.setBorder(BorderFactory.createEmptyBorder(10, 0, 0,0));
       pan_datelanc_dp.setBorder(BorderFactory.createEmptyBorder(10, 30, 0,0));

       pan_type_lab.setBorder(BorderFactory.createEmptyBorder(10, 60, 0,0));
       pan_type_jcom.setBorder(BorderFactory.createEmptyBorder(10, 38, 0,0));

       pan_numcontrat_lab.setBorder(BorderFactory.createEmptyBorder(10, 25, 0,0));
       pan_numcontrat_jtext.setBorder(BorderFactory.createEmptyBorder(10, 50, 0,0));
       
       pan_global.setPreferredSize(new Dimension(900, 550));
       pan_gauche_droite.setPreferredSize(new Dimension(800, 550));

	    

	    setContentPane(pan_global);

    	
    }
	private void existJtextCode(JTextField field) {

		String code = field.getText();
		boolean existe = prod.select_produit(code);

		if (existe) {

			produit_jcom.setSelectedItem(prod.code+" "+prod.desin);

		}

	}


    private String validchamp(){
    	msg="";
		if (code_jtext.getText().equals("")) {
			msg += "Vous Devez remplir le code produit\n";
		} else if (desin_jtext.getText().equals("")) {
			msg += "Vous Devez remplir la designation\n";
		}
		else if (fournisseur_jcom.getSelectedIndex()==0) {
			msg += "Vous Devez selectionner  un fournisseur\n";
		}

		else if(!isValid(quantite_jtext.getText())){
			msg+="La quantite doit etre un nombre \n";}
		else if (prod.existe_produit(code_jtext.getText(), desin_jtext.getText())) {

			msg+="La designation de  produit existe déjà";
		}
		return msg;

	}
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {


				new produit("7");
				//frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}


	
}
