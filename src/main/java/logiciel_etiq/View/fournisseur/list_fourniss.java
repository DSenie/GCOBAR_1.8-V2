package logiciel_etiq.View.fournisseur;

import logiciel_etiq.Controller.gestion_fournisseur;
import logiciel_etiq.View.generale;
import logiciel_etiq.View.tableau.Tableau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class list_fourniss extends generale {


	private  Object [] entete={"Code Fournisseur","Intitule Fournisseur"};
	private final Tableau tab=new Tableau(entete);
	private  final JTextField filterText = new JTextField("Recherche");
		
	private final JPanel paneltext =new JPanel();
	private final JPanel pan=new JPanel();
	private	final JPanel pan1=new JPanel();
	private final JPanel pan2=new JPanel();
	private final JPanel pan3=new JPanel();
	private JLabel count=new JLabel();
	    
	private JScrollPane p=new JScrollPane(tab);
	private gestion_fournisseur forn=new gestion_fournisseur();


public list_fourniss(final String log){

	composant(log);
}
private void composant(String log){
	JPanel panel = generalPanle();
	initFrame("Liste des fournisseurs", panel, this, log,"Liste des fournisseurs");



	filterText.setForeground(Color.gray);


	actionMouseJtextField(filterText);
	actionKeyJtextFieldSorter(filterText, tab,  count) ;



	ArrayList <String> list_fourniss=forn.select();
	remplirTab(list_fourniss, 2,  tab,0);
	count.setText("Nombre Total : " +tab.table.getRowCount());


	labelStyleTable(count);
	StyleTable(this, tab);





	//***********************add panel component ***************/
	panel.add(paneltext);
	panel.add(p);
	panel.add(pan);
	paneltext.add(pan2);
	paneltext.add(pan3);
	pan2.add(filterText);
	pan.add(count);
	pan.add(pan1);


	//*************************visibilite  panel  ***************/

	panel.setOpaque(false);
	pan.setOpaque(false);
	paneltext.setOpaque(false);
	pan1.setOpaque(false);
	pan2.setOpaque(false);
	pan3.setOpaque(false);


	///*************************Layout Component  ***************/

	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
	paneltext.setLayout(new BoxLayout(paneltext,BoxLayout.X_AXIS));
	pan2.setLayout(null);



	///*************************dimension of comopnent ***************/

	filterText.setPreferredSize(new Dimension(50,30));
	p.setPreferredSize(new Dimension(700,700));
	paneltext.setPreferredSize(new Dimension(40, 150));




	///*************************border of comopnent ***************/

	filterText.setBounds(0, 30, 150,30);
	panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	pan1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));



	setContentPane(panel);


}

public static void main(String[] args) {
	EventQueue.invokeLater(() -> {
		try {


		 new list_fourniss("7");
		} catch (Exception e) {
			e.printStackTrace();
		}
	});
}
}