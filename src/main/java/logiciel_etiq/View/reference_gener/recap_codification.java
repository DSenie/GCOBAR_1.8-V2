package logiciel_etiq.View.reference_gener;

import logiciel_etiq.Controller.gestion_reference_codegener;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Tableau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class recap_codification extends generale {


	private ArrayList <String>list_recap_code= new ArrayList<>() ;
private JPanel pan_code_lab=new JPanel();
private JPanel pan_code_combo=new JPanel();
private JPanel pan_code=new JPanel();
private JPanel pan_ref_lab=new JPanel();
private JPanel pan_ref_combo=new JPanel();
private JPanel pan_ref=new JPanel();
private JPanel pan_codegener_combo=new JPanel();
private JPanel pan_codegener_lab=new JPanel();

private JPanel pan_codegener=new JPanel();
private JPanel pan_tab=new JPanel();
private JPanel pan_rech_lab=new JPanel();
private JPanel pan_rech_jtext=new JPanel();
private JPanel pan_rech=new JPanel();
private JPanel pan_pied=new JPanel();
private JPanel pan_but=new JPanel();
private JPanel pan_count=new JPanel();

private JPanel pan_entete=new JPanel();
private JLabel code_lab=new JLabel("Codification");
private JComboBox<String> code_combo=new JComboBox<>();

private JLabel rech_lab=new JLabel("Recherche");
private JTextField filterText=new JTextField();
private JButton recherche=new JButton("Recherche");



    private Object [] entete={"code réfèrence ou famille","designation"};
	private final Tableau tab=new Tableau(entete);
	private JScrollPane p=new JScrollPane(tab);

	private gestion_reference_codegener ref_code=new gestion_reference_codegener();

    private JLabel count = new JLabel();

    private String choix_code="";

    public recap_codification(final String log){
	composant(log);
     }


   public void composant(String log){

	JPanel panel = generalPanle();
	initFrame("Liste des codifications", panel, this, log,"Liste des codifications");

	filterText.setForeground(Color.gray);

	count.setText("Nombre Total : " +tab.table.getRowCount());

	code_combo.addItem("---Selectionner une Codification-----");
	code_combo.addItem("Code réfèrence");
	code_combo.addItem("Code famille");



	code_combo.addActionListener(
			e -> {
				if(code_combo.getSelectedIndex()==1){
					choix_code="Code réfèrence";
				}
				else  if(code_combo.getSelectedIndex()==2){
					choix_code="Code famille";
				}

			}
	);


			  
		   recherche.addActionListener(
				   e -> {
					   if(filterText.getText().equals("")&&code_combo.getSelectedIndex()==0){
						   JOptionPane.showMessageDialog(null,
									   "Vous devez au moins selectionner un critére", "",
								   JOptionPane.INFORMATION_MESSAGE);
					   }
					   else {
						   removeTabRow(tab);
						   list_recap_code = ref_code.recherche(choix_code, filterText.getText());
						   remplirTab(list_recap_code, 2, tab,0);


						   if (tab.table.getRowCount() == 0) {
							   JOptionPane.showMessageDialog(null,
									   "réfèrence ou code famille inexistant", "",
									   JOptionPane.INFORMATION_MESSAGE);
						   }
					   }

						   count.setText("Nombre Total : " +tab.table.getRowCount());

					   });




	labelStyleTable(count);
	StyleTable(this, tab);





	///***********************add panel component ***************/
	       pan_entete.add(pan_rech);
		   pan_entete.add(pan_code);
		   pan_entete.add(pan_ref);
		   pan_entete.add(pan_codegener);
		   
		   pan_rech.add(pan_rech_lab);
		   pan_rech_lab.add(rech_lab);
		   pan_rech.add(pan_rech_jtext);
		   pan_rech_jtext.add(filterText);

		   pan_code.add(pan_code_lab);
		   pan_code_lab.add(code_lab);
		   pan_code.add(pan_code_combo);
		   pan_code_combo.add(code_combo);

           pan_pied.add(pan_count);
           pan_pied.add(pan_but);
           
           pan_count.add(count);
           pan_but.add(recherche);

		   panel.add(pan_entete);
		   panel.add(pan_tab);
		   pan_tab.add(p);
		   panel.add(pan_pied);


     //************************ layout

	styleLabel(code_lab);
	styleLabel(rech_lab);




	//***********************style btoon ***************/

	recherche.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
	recherche.setPreferredSize(new Dimension(120, 33));
	//***********************size component ***************/
	p.setPreferredSize(new Dimension(930,700));
	pan_tab.setPreferredSize(new Dimension(40, 400));
	filterText.setPreferredSize(new Dimension(210,30));
	code_combo.setPreferredSize(new Dimension(210,30));


	//***********************visibilite panel component ***************/

	  pan_code.setOpaque(false);
	  pan_code_lab.setOpaque(false);
	  pan_code_combo.setOpaque(false);
	  pan_ref.setOpaque(false);
	  pan_ref_lab.setOpaque(false);
	  pan_ref_combo.setOpaque(false);
	  pan_codegener.setOpaque(false);
	  pan_codegener_lab.setOpaque(false);
	  pan_codegener_combo.setOpaque(false);
	  pan_rech.setOpaque(false);
	  pan_rech_lab.setOpaque(false);
	  pan_rech_jtext.setOpaque(false);
      pan_entete.setOpaque(false);
      panel.setOpaque(false);
	  pan_tab.setOpaque(false);
      pan_pied.setOpaque(false);
      pan_but.setOpaque(false);
      pan_count.setOpaque(false);


  

        //µ********************** les bordure de panel***********************/
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    pan_entete.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


	    ////**************************  layout de panel************/
		pan_tab.setLayout(new BoxLayout(pan_tab,BoxLayout.Y_AXIS));
	    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	    pan_rech.setLayout(new BoxLayout(pan_rech,BoxLayout.Y_AXIS));
	    pan_rech_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));
	    pan_rech_jtext.setLayout( new FlowLayout( FlowLayout.LEFT ));

	    pan_code.setLayout(new BoxLayout(pan_code,BoxLayout.Y_AXIS));
	    pan_pied.setLayout(new BoxLayout(pan_pied,BoxLayout.X_AXIS));
		pan_but.setLayout( new FlowLayout( FlowLayout.RIGHT));
		pan_count.setLayout( new FlowLayout( FlowLayout.LEFT));

	    pan_code_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
	    pan_code_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));


		pan_ref.setLayout(new BoxLayout(pan_ref,BoxLayout.Y_AXIS));
		pan_ref_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
		pan_ref_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));

		pan_codegener.setLayout(new BoxLayout(pan_codegener,BoxLayout.Y_AXIS));
		pan_codegener_combo.setLayout( new FlowLayout( FlowLayout.LEFT ));
		pan_codegener_lab.setLayout( new FlowLayout( FlowLayout.LEFT ));

		pan_entete.setLayout(new BoxLayout(pan_entete,BoxLayout.X_AXIS));




	     

    setContentPane(panel);
  



}

}
