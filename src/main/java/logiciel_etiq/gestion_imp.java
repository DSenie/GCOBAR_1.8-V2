package logiciel_etiq;

		import javafx.scene.control.DatePicker;

		import java.text.*;
		import java.util.ArrayList;
		import java.util.Calendar;
		import java.util.Date;
		import java.util.zip.DataFormatException;

		import javax.swing.*;


public class gestion_imp {
	//String bdd;
	int accqc;
	int conte,assembln;
	public String Chemin = "C:\\GCOBAR\\";
	public String bdd = Chemin+Utilitaire.InitBdd()+".accdb";
	public int ass=0;
	public int assqc=0;
	public int actqc;
	public int cont;
	public String article="";
	public String cart="";
	public String cpost="";
	public String chaine="";
	public String contass="";
	public String contqc="";
	public String actqcc="";
	public String designation="";
	public String intitule="";
	public String chaine_design="";
	public String exist="";
	public String serial;
	public int count_ass;
	public  int count_assqc;
	public int imp_assqc;
	public int count_actqc,count_assqc2,nom_tuple;
	public String des_chaine;
	public String article_c,model,couleur,qte,nw,gw,delivery,cartsize,palette="";
	public String parcel,gw_tpe,code_article,carton_size,commentaire,qte_tpe,designation_tpe;
	String date_emb;
	public String  code_article_composant,designation_composant,qte_bobine,nbr_bobine,reference,serie;
	NumberFormat form = new DecimalFormat("0");


	public String  date_traiter(String dates) {
		System.out.println("hgjhfgjh"+dates);
		Date date= null;
		String date_jour="";
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(dates);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

			date_jour = dateFormat.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date_jour;

	}


	public void update_imp_c(String commercial){
	    System.out.println(commercial);
		String update= "update imp set imp_com='"+commercial+"' ";
		CConnect.Insert(update,bdd);
	}

	public String rech_tpe(String parcel){
		ArrayList<String>   parcef=new  ArrayList<String>();
		String rech= "select parcel from imp_emballage_tpe where parcel='"+parcel+"' ";
		parcef=CConnect.Requete(rech,bdd);
		if(parcef.size()!=0){
			return parcef.get(0);
		}else return "";

	}
	public void update_imp_s(String simple){
		String update= "update imp set imp_simple='"+simple+"' ";
		CConnect.Insert(update,bdd);
	}


	public static boolean isdate(String champ) {
		try{
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyyy");
			Date date = dt.parse(champ);


		}
		catch(ParseException e){
			return false;
		}
		return true;
	}


	public  ArrayList<String>  select_emei_table(String champ) throws ParseException{
		System.out.println(isdate(champ));
		String query5 = "SELECT  parcel,imp_emballage.code_article,designation,model,couleur,delivery,gw,nw,carton_size,commentaire,date_emb,"
				+ " imei1,imei2 "
				+ " from article,imp_emballage,emei_table"
				+ " where article.code_article=imp_emballage.code_article"
				+ " and  emei_table.parcel_code=imp_emballage.parcel"
				+ " and (parcel='"+champ+"' or imp_emballage.code_article='"+champ+"' or designation='"+champ+"'or model='"+champ+"'"
				+ " or couleur='"+champ+"' or delivery='"+champ+"' or gw='"+champ+"' or  nw='"+champ+"' or carton_size='"+champ+"' "
				+ " or commentaire='"+champ+"'  or imei1='"+champ+"' or imei2='"+champ+"' " ;


		if(isdate(champ)){
			query5+= " or date_emb= #"+date_traiter(champ)+"#";

		}



		query5+= " ) ";
		query5+= "   order by date_emb DESC" ;



		ArrayList<String> resultat =CConnect.Requete(query5,bdd);
		System.out.println("hhhhhhhhhhhh"+resultat);
		return resultat;

	}

	public ArrayList<String> listCarton(String table, String palette){
		int id=0;
		ArrayList<String> resultat = new ArrayList<String>();

		String select = "select id_pal from  palette where parcel_palette='"+palette+"'";
		if(CConnect.Requete(select,bdd).size()!=0) {
			id = Integer.parseInt(CConnect.Requete(select, bdd).get(0));

			String insert = "select parcel from  " + table + " where id_palette=" + id;
			resultat = CConnect.Requete(insert, bdd);
		}
		return resultat;
	}


	public ArrayList<String> recherche_portable(String filter,String datedebut,String datefin)
	{

		String insert = "select  article.code_article, article.designation, imp_emballage.parcel, emei_table.imei1, emei_table.imei2 , imp_emballage.couleur "
				+ "from article,imp_emballage,emei_table  "
				+ " where  article.code_article = imp_emballage.code_article and imp_emballage.parcel = emei_table.parcel_code and "
				+ "imp_emballage.date_emb BETWEEN #"+date_traiter(datedebut)+"#   and #"+date_traiter(datefin)+"# and"
				+ " (imei1 like '%"+filter+"%' or imei2 like '%"+filter+"%' or imp_emballage.parcel like '%"+filter+"%' or"
				+ " article.designation like '%"+filter+"%' or  article.code_article like '%"+filter+"%' or couleur like '%"+filter+"%')";
		ArrayList <String>resultat=CConnect.Requete(insert,bdd);
		// System.out.println("eeee edfsf\\"+resultat);
		return  resultat;
	}


	public ArrayList<String> recherche_tpe(String filter,String datedebut,String datefin)
	{

		String insert = "select article.code_article, article.designation, imp_emballage_tpe.parcel, emei_tpe.sn, emei_tpe.imei  from article,imp_emballage_tpe,emei_tpe "
				+ " where  article.code_article = imp_emballage_tpe.code_article and imp_emballage_tpe.parcel = emei_tpe.parcel "+
				" and" +
				" date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"# "
				+ "AND( imei like '%"+filter+"%' or emei_tpe.sn like '%"+filter+"%' or  imp_emballage_tpe.parcel  like '%"+filter+"%'"
				+ " or article.code_article like '%"+filter+"%' or article.designation like '%"+filter+"%')"
				;
		ArrayList <String>resultat=CConnect.Requete(insert,bdd);
		System.out.println("eeee edfsf88888mmmmDSenie2015\\"+resultat);
		return  resultat;
	}



	public  ArrayList<String>  select_list_palette(String champ){
		String parcel = "";
		String query5 = "SELECT  parcel,imp_embalage_palette.code_article,designation,model,gw,nw,carton_size,date_emb,"
				+ " parcel_carton "
				+ " from article,imp_embalage_palette,emei_palette"
				+ " where article.code_article=imp_embalage_palette.code_article"
				+ " and  emei_palette.parcel_palette=imp_embalage_palette.parcel"
				+ " and ( parcel='"+champ+"' or imp_embalage_palette.code_article like '%"+champ+"%'"
				+ " or designation like '%"+champ+"%' or model='"+champ+"'"
				+ "   or gw='"+champ+"' or  nw='"+champ+"' or carton_size='"+champ+"' or"
				+ " commentaire='"+champ+"'  or parcel_carton='"+champ+"'";

		if(isdate(champ)){
			query5+= " or date_emb= #"+date_traiter(champ)+"#";

		}

		query5+= " ) ";
		query5+= "   order by date_emb DESC" ;





		ArrayList<String> resultat=CConnect.Requete(query5,bdd);
		return resultat;

	}

	public void ajouter_imprim(String code,String code_art,String code_com,String type){
		String insert = "insert into imp (code,code_art,code_commercial,imp_com,imp_simple,type_etq) "
				+ "values('"+code+"','"+code_art+"','"+code_com+"','FALSE','FALSE','"+type+"')";
		CConnect.Insert(insert,bdd);
	}



	public void ajouter_composant(String code_carton,String code_art,String qte_bobine,String nbr_bobine,String reference){
		String insert = " insert into imp_composant (code_carton,code_article,qte_bobine,nbr_bobine,reference) "
				+ " values('"+code_carton+"','"+code_art+"','"+qte_bobine+"','"+nbr_bobine+"','"+reference+"')";
		CConnect.Insert(insert,bdd);
	}



	public  ArrayList <String> ajouter_imprim_fourn(String imei1, String imei2){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT model, couleur, imei1,imei2,date_imp FROM etiq_fournisseur "
				+ "where  imei1='"+imei1+"' or imei2='"+imei2+"' ";

		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}






	public  ArrayList <String> remplir_etq_tpe(String code){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT model, etiquette_tpe.code_article, article.designation,arpt,date_tpe FROM etiquette_tpe, article  "
				+ "where  code_tpe='"+code+"' and etiquette_tpe.code_article=article.code_article ";
		//+ "where intitule='poste pr?paration 1' or "
		//	+ "intitule='poste pr?paration 2'  " ;
		if(CConnect.Requete(query5, bdd).size()>=1){

			//System.out.println(resultat.size()+"hyh"+(String) CConnect.Requete(query5,bdd).get(0));

			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}


	public  ArrayList <String> select_imprim_fourn(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT model, couleur, imei1,imei2,date_imp FROM etiq_fournisseur "
				;

		if(CConnect.Requete(query5, bdd).size()>=1){

			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}

	public void delete_imp(){
		String insert = "delete from imp where imp_com='TRUE' and imp_simple='TRUE'";
		CConnect.Insert(insert,bdd);

	}


	public void chaine(String code)
	{   ArrayList <String>list_imp= new ArrayList<String>() ;

		String query2 ="SELECT designation_chaine FROM Chaine where code_chaine='"+code+"' ";
		list_imp=CConnect.Requete(query2, bdd);
		if( list_imp.size()>=1){
			des_chaine= list_imp.get(0);}
	}
	public String afficher_code_imp(String famille)
	{   ArrayList <String>list_imp_code= new ArrayList<String>() ;
		String code;
		NumberFormat formatter = new DecimalFormat("000");
//		   String query2 = "SELECT  r.max FROM ("
//     +" SELECT  code_gener,MAX(code_article) as max"+
//    "  FROM article  GROUP BY code_gener)  r INNER JOIN article t ON t.code_gener = r.code_gener AND t.code_article = r.max";

		String query2 ="SELECT  max(code_imp) FROM imprime where code_famille='"+famille+"' ";
		list_imp_code=CConnect.Requete(query2, bdd);
		if( list_imp_code.get(0)!=null){
			int cz=Integer.parseInt( list_imp_code.get(0).substring(8))+1;
			code=formatter.format(cz);
		}
		else code="001";
		return code;
	}


	public void insert_phone_fournisseur(String model,String couleur,String imei1,String imei2,String date)
	{
	    System.out.println(date);
		String insert = "insert into etiq_fournisseur (model,couleur,imei1,imei2,date_imp)"
				+ " values('"+model+"','"+couleur+"','"+imei1+"','"+imei2+"', #"+date_traiter(date)+"# )";
		CConnect.Insert(insert,bdd);

	}



    public boolean exist_imei_sn(String sn,String imei) {
    String existe_sn="select sn from etiquette_tpe_3code where sn ='"+sn+"'";
    String existe_imei="select imei from etiquette_tpe_3code where imei ='"+imei+"'";
    boolean exist=false;
      if(CConnect.Requete(existe_sn,bdd).size()!=0){
          JOptionPane.showMessageDialog(
                  null,
                  "Le SN exist deja" ,
                  "", JOptionPane.ERROR_MESSAGE);
          exist=true;
      }
        else if(CConnect.Requete(existe_imei,bdd).size()!=0){
            JOptionPane.showMessageDialog(
                    null,
                    "L'imei exist deja" ,
                    "", JOptionPane.ERROR_MESSAGE);
            exist=true;
        }

      return exist;
    }



	public void insert_Tpe_3Code(String code_enie,String sn,String imei,String code_chaine,String code_article,String date)
	{
            String insert = "insert into etiquette_tpe_3code" +
                    " (code_enie" +
                    ",sn,imei,code_chaine,id_article,date_tpe)"
                    + " values('" + code_enie + "'" +
                    ",'" + sn + "','" + imei + "','" + code_chaine + "','" + code_article + "',#" + date_traiter(date) + "#)";
            CConnect.Insert(insert, bdd);


	}


	public void update_phone_fournisseur(String model,String couleur,String imei1,String imei2,String date)
	{
    	/*System.out.println(date);
        String insert = "update  etiq_fournisseur set model='"+model+"', couleur='"+couleur+"',date_imp='"+date+"' " +
                "where  imei1='"+imei1+"' or  imei2='"+imei2+"' ";
        CConnect.Insert(insert,bdd);*/
		delete_phone_fournisseur(  imei1,  imei2 );
		insert_phone_fournisseur(model, couleur, imei1, imei2, date);

	}

	public void delete_phone_fournisseur(String  imei1, String imei2 ){
		String delete= "delete from etiq_fournisseur where imei1='"+imei1+"' or  imei1='"+imei2+"' or imei2='"+imei1+"' or  imei2='"+imei2+"' ";

		CConnect.Insert(delete,bdd);

	}

	public void delete_tpe_3code(String  code_enie ){
		String delete= "delete from etiquette_tpe_3code where code_enie='"+code_enie+"' ";
		CConnect.Insert(delete,bdd);
	}

	public boolean exist_tpe_modif(String sn, String imei, String code_enie){
		String existe_sn="select sn from etiquette_tpe_3code where sn ='"+sn+"' and  code_enie!='"+code_enie+"' ";
		String existe_imei="select imei from etiquette_tpe_3code where imei ='"+imei+"' and  code_enie!='"+code_enie+"'  ";
		boolean exist=false;

		if(CConnect.Requete(existe_sn,bdd).size()!=0){
			   JOptionPane.showMessageDialog(
					null,
					"Le SN exist deja" ,
					"", JOptionPane.ERROR_MESSAGE);
			exist=true;
		}

		else if(CConnect.Requete(existe_imei,bdd).size()!=0){
		    	JOptionPane.showMessageDialog(
					null,
					"L'imei exist deja" ,
					"", JOptionPane.ERROR_MESSAGE);
			exist=true;

		}
		return exist;

	}
    public void update_tpe_3code(String sn,String imei,String code_article, String code_enie)
    {

            String insert = "update etiquette_tpe_3code set sn='" + sn + "', imei='" + imei + "' , id_article='" + code_article + "' "
                    + " where code_enie='" + code_enie + "'"
                    + "";
            CConnect.Insert(insert, bdd);

    }


    public void insert_etq_tpe(String code_article,String code_tpe,String arpt,String date)
	{
		String insert = "insert into etiquette_tpe (code_article,code_tpe,arpt,date_tpe)"
				+ " values('"+code_article+"','"+code_tpe+"','"+arpt+"',#"+date_traiter(date)+"#)";
		CConnect.Insert(insert,bdd);
	}


	public void modif_etq_tpe(String code_article,String code_tpe,String arpt,String date)
	{
		String insert = "update etiquette_tpe set code_article='"+code_article+"', arpt='"+arpt+"' ,date_tpe=#"+date_traiter(date)+"# "
				+ " where code_tpe='"+code_tpe+"'"
				+ "";
		CConnect.Insert(insert,bdd);
	}

	public String select_report(String dimension,String indice)
	{
		ArrayList <String>resultat= new ArrayList<String>() ;

		String insert = "select nom_report from dimension_etq"
				+ " where dimension ='"+dimension+"' and indice='"+indice+"'";
		resultat=CConnect.Requete(insert,bdd);
		System.out.println(dimension+"eeee edfsf"+resultat);
		return  resultat.get(0);
	}


	public ArrayList<String> select_poste_code()
	{

		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT Code_Poste,Intitule FROM Poste ";
		//+ "where intitule='poste pr?paration 1' or "
		//	+ "intitule='poste pr?paration 2'  " ;
		if(CConnect.Requete(query5, bdd).size()>=1){

			//System.out.println(resultat.size()+"hyh"+(String) CConnect.Requete(query5,bdd).get(0));

			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}


	public String afficher_conteur_composant(String code_article)
	{  ArrayList <String>list_affiche_cont= new ArrayList<String>() ;
		NumberFormat formatter = new DecimalFormat("0000");
		String code = null;
		String code_carton="";

		String  arti_slash= code_article.substring(0,3)+"-"+code_article.substring(3,7); // 004

		String query2 = " SELECT  code_carton  FROM imp_composant  where "
				+ " (Left (code_carton,5) IN (SELECT   max( Left(code_carton,5) ) "+
				" FROM imp_composant where code_article like '%"+code_article+"' or code_article='"+arti_slash+"'  ))";
		list_affiche_cont= CConnect.Requete(query2, bdd);
		if(list_affiche_cont.size()>=1){
			serial=list_affiche_cont.get(0);
			// int n = 5; // nbre de caract?res
			//  int length = list_affiche_cont.get(0).length();
			cont = Integer.parseInt(serial.substring(1, 5));
			System.out.println("bbbbbbbb "+cont);
			cont=cont+1;
			code=formatter.format(cont);
		}
		else {code= "0001";}
		return code;
	}



	public String afficher_conteurportable(String code_article)
	{  ArrayList <String>list_affiche_cont= new ArrayList<String>() ;
		NumberFormat formatter = new DecimalFormat("00000");
		String code = null;
		String serial="";
		String query2 = " SELECT  serial  FROM imp_portable  where "
				+ " (Right (serial,5) IN (SELECT   max( Right(serial,5) ) "+
				" FROM imp_portable where code_article='"+code_article+"'  ))";
		list_affiche_cont= CConnect.Requete(query2, bdd);
		if(list_affiche_cont.size()>=1){
			serial=list_affiche_cont.get(0);
			int n = 5; // nbre de caract?res
			int length = list_affiche_cont.get(0).length();
			cont = Integer.parseInt(list_affiche_cont.get(0).substring(length -n, length));
			cont=cont+1;
			code=formatter.format(cont);
		}
		else {code= "00001";}
		return code;
	}


	public String afficher_conteur(String code_article,String code_chaine,Date date_i,int indic)
	{   String code = null;
		int cz=0;int wek=0;int cont;
		NumberFormat formatter = new DecimalFormat("000");
		ArrayList <String>list_affiche_cont= new ArrayList<String>() ;
		ArrayList <String>list_conteur2= new ArrayList<String>() ;

		final Calendar cal = Calendar.getInstance( );  // date du jour
		cal.setTime(date_i);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = format1.format(cal.getTime());
		System.out.println(date);
		String query2 = " SELECT  serial_number FROM fiche_suiveuse  where  (date_fiche= #"+date_traiter(date)+"#  "
				+ "and MID(serial_number, 8, 1) = '"+indic+"'"
				//   	+ "code_article='"+code_article+"' "
				+ "and code_chaine='"+code_chaine+"' ) and"+
				" (Right (serial_number,3) IN (SELECT   max( Right(serial_number,3) )"+
				"FROM fiche_suiveuse where date_fiche= #"+date_traiter(date)+"# "
				//+ "and code_article='"+code_article+"'  "
				+ "and code_chaine='"+code_chaine+"' and MID(serial_number, 8, 1) = '"+indic+"' ) )";
        System.out.println(query2);
		list_affiche_cont=CConnect.Requete(query2, bdd);
		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		System.out.println(list_affiche_cont);

		if(list_affiche_cont.size()>=1){
			cz=Integer.parseInt(list_affiche_cont.get(0).substring(5,6));
			wek=Integer.parseInt(list_affiche_cont.get(0).substring(3,5));
			cont=Integer.parseInt(list_affiche_cont.get(0).substring(8));

			int jour= cal.get(Calendar.DAY_OF_WEEK);
			int week= cal.get(Calendar.WEEK_OF_YEAR);
			String query="SELECT cont_assembl,cont_assqc,cont_actqc FROM fiche_suiveuse where "
					+ "serial_number='"+list_affiche_cont.get(0)+"'";
			list_conteur2=CConnect.Requete(query, bdd);

			if(list_conteur2.size()>=1){

				count_ass=Integer.parseInt(list_conteur2.get(0));
				count_assqc=Integer.parseInt(list_conteur2.get(1));
				count_actqc=Integer.parseInt(list_conteur2.get(2));


				if(cz==jour&&wek==week){
					if(count_ass<ass)
						count_ass=count_ass+1;
					if(count_actqc<actqc)
						count_actqc=count_actqc+1;

					// if(count_ass==ass/2+1){
					if(count_assqc==0) count_assqc=0;
					else 	   count_assqc=count_assqc+0;


					// }
					//  System.out.println("jjjjjjjjjj"+count_ass);
				}
			}
			else {
				count_ass=1;
				count_assqc=0;
				count_actqc=1;
			}



			if(cz==jour&&wek==week){
				cont=cont+1;
				code=formatter.format(cont);
			}
			else code= "001";
		}
		else {code= "001";
			count_ass=1;
			count_assqc=0;
			count_actqc=1;	 }
		return code;
	}


	public String afficher_conteur_tpe(Date date_i, String chaine, String table,String prefix)
	{   String code = null;
		NumberFormat formatter = new DecimalFormat("0000");
		ArrayList <String>list_affiche_cont= new ArrayList<String>() ;
		ArrayList <String>list_conteur2= new ArrayList<String>() ;

		final Calendar cal = Calendar.getInstance( );  // date du jour
		cal.setTime(date_i);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = format1.format(cal.getTime());
		int position_number=prefix.length()+1;
		String query2 = " SELECT top 1 parcel FROM "+table+"  where date_emb= #"+date_traiter(date)+"#  and  Mid(parcel,"+position_number+",1)= '"+chaine+"' order by parcel desc  ";
		System.out.println("query"+query2);
		list_affiche_cont=CConnect.Requete(query2, bdd);
		System.out.println(list_affiche_cont);
		if(list_affiche_cont.size()>=1){
			cont=Integer.parseInt(list_affiche_cont.get(0).substring(list_affiche_cont.get(0).length()-4));
			cont=cont+1;
			code=formatter.format(cont);
		}
		else {
			code= "0001";
		}
		return code;
	}


	public String afficher_conteur_tpe_3code(Date date_i, String chaine, String table,String prefix)
	{   String code = null;
		NumberFormat formatter2 = new DecimalFormat("000");
		ArrayList <String>list_affiche_cont2= new ArrayList<String>() ;
		ArrayList <String>list_conteur2= new ArrayList<String>() ;

		final Calendar cal2 = Calendar.getInstance( );  // date du jour
		cal2.setTime(date_i);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = format1.format(cal2.getTime());
		int position_number=prefix.length()+1;
		String query2 = " SELECT top 1 code_enie FROM "+table+"  where date_tpe= #"+date_traiter(date)+"#  " +
				"and  Mid(code_enie,"+position_number+",1)= '"+chaine+"' " +
				"order by code_enie desc  ";
		System.out.println("query2"+query2);
		list_affiche_cont2=CConnect.Requete(query2, bdd);
		System.out.println("code"+list_affiche_cont2);
		if(list_affiche_cont2.size()>=1){
			cont=Integer.parseInt(list_affiche_cont2.get(0).substring(list_affiche_cont2.get(0).length()-3));
			cont=cont+1;
			code=formatter2.format(cont);
		}
		else {
			code= "001";
		}
		return code;
	}






	public boolean nettoyer_serial(String debut, String fin){
		String select= "select * from fiche_suiveuse where serial_number BETWEEN '"+debut+"' and '"+fin+"' ";
		ArrayList <String>list_select=CConnect.Requete(select, bdd);
		if(list_select.size()!=0) {
			String update = "delete from fiche_suiveuse where serial_number BETWEEN '" + debut + "' and '" + fin + "' ";
			String update2 = "delete from imp where code BETWEEN '" + debut + "' and '" + fin + "' ";
			CConnect.Insert(update, bdd);
			CConnect.Insert(update2, bdd);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "L'intervalle des etiquettes est introuvable ");
             return false;
		}

	}

	public void select_conteur()
	{   ArrayList <String>list_select_cont= new ArrayList<String>() ;

		String query2 = "SELECT cont_assembl,cont_assqc,cont_actqc,cont_serial FROM conteur";
		list_select_cont=CConnect.Requete(query2, bdd);
		if(list_select_cont.get(0)!=null){
			ass=Integer.parseInt(list_select_cont.get(0));
			assqc=Integer.parseInt(list_select_cont.get(1));
			actqc=Integer.parseInt(list_select_cont.get(2));
		}


	}
	public void select_fiche(String code){
		ArrayList <String>list_select_cont= new ArrayList<String>() ;
		ArrayList <String>list_fiche1= new ArrayList<String>() ;
		ArrayList <String>list_fiche2= new ArrayList<String>() ;
		ArrayList <String>list_fiche3= new ArrayList<String>() ;
		exist="";
		String query2 = "SELECT code_article,code_poste,code_chaine,cont_assembl,cont_assqc,cont_actqc FROM "
				+ "fiche_suiveuse where serial_number='"+code+"'";
		list_select_cont=CConnect.Requete(query2, bdd);
		if(list_select_cont.size()>=1){
			article=list_select_cont.get(0);
			cpost=list_select_cont.get(1);
			chaine=list_select_cont.get(2);
			contass=list_select_cont.get(3);
			contqc=list_select_cont.get(4);
			actqcc=list_select_cont.get(5);

			String query3 = "SELECT designation FROM "
					+ "article  where code_article='"+article+"'";
			list_fiche1=CConnect.Requete(query3, bdd);
			if( list_fiche1.size()!=0){
				designation= list_fiche1.get(0);
			}

			String query4 = "SELECT Intitule FROM "
					+ "Poste  where Code_Poste='"+cpost+"'";
			list_fiche2=CConnect.Requete(query4, bdd);
			if(list_fiche2.size()!=0){
				intitule=list_fiche2.get(0);

			}

			String query5 = "SELECT designation_chaine FROM "
					+ "Chaine  where code_chaine='"+chaine+"'";
			list_fiche3=CConnect.Requete(query5, bdd);
			if(list_fiche3.size()>=1){
				chaine_design=list_fiche3.get(0);

			}
		}
		else{
			exist="non";
		}


	}


	public ArrayList<String> select_nomo(String code){
		ArrayList <String>resultat= new ArrayList<String>() ;
//		   	    String query5 = "SELECT zone.Code_Zone,zone.Designation_Zone,zone.description,Poste.Code_Poste,Poste.Intitule,Zone_Post.Code_Zone,Zone_Post.Code_Poste,Zone_Post.Nbr_Poste,FROM zone,Zone_Post,Poste"
//		   	    		+ "where zone.Code_Zone='"+num+"' and Poste.Code_Poste=Zone_Post.Code_Poste and zone.Code_Zone=Zone_Post.Code_Zone  " ;

		String query5 = "SELECT nomoclature.code_nomo_art,article.designation,nomoclature.quantite FROM article,nomoclature "
				+ "where  nomoclature.code_article='"+code+"' and article.code_article=nomoclature.code_nomo_art " ;
		resultat=CConnect.Requete(query5,bdd);
		//  System.out.println(code+" "+resultat);

		return resultat;

	}
	public int select_count_assqc(){
		int assqc;
		ArrayList <String>list_conteur_assqc= new ArrayList<String>() ;
		String query="select top 1 cont_assqc from fiche_suiveuse order by cle Desc";
		list_conteur_assqc=CConnect.Requete(query, bdd);
		if( list_conteur_assqc.size()>=1){
			assqc= Integer.parseInt(list_conteur_assqc.get(0));
		}else {

			assqc=0;
		}

		return assqc;
	}
	public int count_ligne(){
		int ligne;
		ArrayList <String>list_conteur_assqc2= new ArrayList<String>() ;
		String query2="select count (cont_assqc) from fiche_suiveuse   ";
		list_conteur_assqc2=CConnect.Requete(query2, bdd);
		if( list_conteur_assqc2.size()>=1){
			ligne= Integer.parseInt(list_conteur_assqc2.get(0));
		}else {

			ligne=0;
		}

		return ligne;

	}
	public int count_ligne_imp(){
		int ligne;
		ArrayList <String>list_conteur_assqc2= new ArrayList<String>() ;
		String query2="select count (*) from imp   ";
		list_conteur_assqc2=CConnect.Requete(query2, bdd);
		if( list_conteur_assqc2.size()>=1){
			ligne= Integer.parseInt(list_conteur_assqc2.get(0));
		}else {

			ligne=0;
		}

		return ligne;

	}

	public int  select_count_actqc(){
		int actqc;
		ArrayList <String>conteur= new ArrayList<String>() ;
		String query1="select top 1 cont_actqc from fiche_suiveuse order by cle Desc";
		conteur=CConnect.Requete(query1, bdd);
		if( conteur.size()>=1){
			actqc= Integer.parseInt(conteur.get(0));
		}else{
			actqc=0;
		}
		return actqc;
	}

	public int  select_count_assembl(){
		int assembl;
		ArrayList <String>conteur1= new ArrayList<String>() ;

		String query1="select top 1 cont_assembl from fiche_suiveuse order by cle Desc";
		conteur1= CConnect.Requete(query1, bdd);
		if(conteur1.size()>=1){
			assembl= Integer.parseInt(conteur1.get(0));
		}
		else{

			assembl=0;
		}
		return assembl;

	}

	public boolean select_exist(String sn){
		// boolean val=true;
		ArrayList <String>list= new ArrayList<String>() ;
		String select = "select * from fiche_suiveuse where serial_number='"+sn+"' ";
		list=CConnect.Requete(select, bdd);
		if(list.size()==1){
			return false;
			// JOptionPane.showMessageDialog(null, "Vous devez choisir un seul produit (produit fini+carte mere)par jour ");
		}
		return true;
	}

	public void ajouter_imprimer(String codeart,String codep,String codec,String sn,int astqc,int assembl,int assqc,
								 String code_com, Date date_p){
		final Calendar cal = Calendar.getInstance( );  // date du jour
		cal.setTime(date_p);
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = format1.format(cal.getTime());
		//   String select = "select * from fiche_suiveuse where serial_number='"+sn+"'";
		//   list=f.Requete(select, bdd);

		// String insert = "insert into fiche_suiveuse (serial_number,code_article,code_poste,code_chaine,cont_assembl,cont_assqc,cont_actqc,date) values('"+sn+"','"+codeart+"','"+codep+"','"+codec+"',"+assembln+","+assqc+","+accqc+",'"+date+"')";
		String insert = "insert into fiche_suiveuse (serial_number,code_article,code_poste,code_chaine,cont_assembl,cont_assqc,cont_actqc,date_fiche,code_commercial)"
				+ " values('"+sn+"','"+codeart+"','"+codep+"','"+codec+"',"+assembl+","+assqc+","+astqc+", #"+date_traiter(date)+"# ,'"+code_com+"')";
		CConnect.Insert(insert,bdd);
		//  }else{

		//  }


	}


	public void ajouter_imprim_compo(String codeart,String sn,String cartcom,String snartcom,int qte){
		String insert = "insert into fiches_compo (serial_number,code_article,sn_article,code_art,quantite)"
				+ " values('"+sn+"','"+codeart+"','"+snartcom+"','"+cartcom+"',"+qte+")";
		CConnect.Insert(insert,bdd);
	}

	public void setupdate_etiq(String designation,String code_article,String serial_number,String poste){

		String update= "update fiche_suiveuse set code_article='"+code_article+"',code_poste='"+poste+"' "
				+ " where serial_number='"+serial_number+"'";

		CConnect.Insert(update,bdd);

	}


	public void setupdate(String serial_number){

		String update= "delete from fiches_compo where serial_number='"+serial_number+"' ";
		//"update fiches_compo set sn_article='"+sn_article+"' where serial_number='"+serial_number+"' and "
		//	+ "code_art='"+codeart+"'";

		CConnect.Insert(update,bdd);

	}


	public ArrayList<String> select_fiche_compo(String code){
		ArrayList <String>resultat= new ArrayList<String>() ;

		String query5 = "SELECT fiches_compo.code_art,article.designation,fiches_compo.sn_article,"
				+ "fiches_compo.quantite FROM article,fiches_compo "
				+ "where fiches_compo.serial_number='"+code+"' and fiches_compo.code_art=article.code_article " ;
		resultat= CConnect.Requete(query5, bdd);
		return resultat;

	}
	public String conteur()
	{
		String code = null;
		int cz=0;int wek=0;int anne;
		final Calendar cal = Calendar.getInstance( );  // date du jour
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = format1.format(cal.getTime());
		//***********recuperer les 3 dernier chiffre (001) du dernier serial number   ajouter*************//
		String query2 =" SELECT  serial_number FROM fiche_suiveuse  where  date_fiche= #"+date_traiter(date)+"# and"+
				" (Right (serial_number,3) IN (SELECT   max( Right(serial_number,3) )"+
				"FROM fiche_suiveuse where date_fiche= #"+date_traiter(date)+"# ) )";

		if(CConnect.Requete(query2, bdd).size()>=1){

			//***********recuperer les trois conteur de la dernier ligne ajouter*************//
			//  System.out.println("jjjjjjjjjj"+CConnect.Requete(query2, bdd).get(0));
			String query="SELECT cont_assembl,cont_assqc,cont_actqc FROM fiche_suiveuse where "
					+ "serial_number='"+CConnect.Requete(query2, bdd).get(0)+"'";


			if(CConnect.Requete(query, bdd).size()>=1){

				count_ass=Integer.parseInt(CConnect.Requete(query,bdd).get(0));
				count_assqc=Integer.parseInt(CConnect.Requete(query,bdd).get(1));
				count_actqc=Integer.parseInt(CConnect.Requete(query,bdd).get(2));
				cz=Integer.parseInt(CConnect.Requete(query2,bdd).get(0).substring(5,6));
				wek=Integer.parseInt(CConnect.Requete(query2,bdd).get(0).substring(3,5));
				anne=Integer.parseInt(CConnect.Requete(query2,bdd).get(0).substring(1 ,3));

				int jour= cal.get(Calendar.DAY_OF_WEEK);
				int week= cal.get(Calendar.WEEK_OF_YEAR)-1;
				String[] date_s = date.toString().split("/");
				int annee = Integer.parseInt(date_s[2].substring(2)); // 004
//			    	 System.out.println("hjjjjjh"+cz+" "+jour);
//			    	 System.out.println("hjjjjjh"+wek+" "+week);
//			    	 System.out.println("hjjjjjh"+anne+" "+annee);
//


				if(cz==jour&&wek==week&&anne==annee){
					if(count_ass<ass)
						count_ass=count_ass+1;
					if(count_actqc<actqc)
						count_actqc=count_actqc+1;

					// if(count_ass==ass/2+1){
					if(count_assqc==0) count_assqc=0;
					else 	   count_assqc=count_assqc+0;


					// }
					//  System.out.println("jjjjjjjjjj"+count_ass);
				}
			}}
		else {
			count_ass=1;
			count_assqc=0;
			count_actqc=1;
		}



		return code;
	}


//		//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		public String conteur(Date date_i,String code_article,String code_chaine)
//		{
//			ArrayList <String>list_conteur= new ArrayList<String>() ;
//			ArrayList <String>list_conteur2= new ArrayList<String>() ;
//			String code = null;
//		    int cz=0;int wek=0;int anne;
//		    final Calendar cal = Calendar.getInstance( );
//		    cal.setTime(date_i);
//
//		     cal.setFirstDayOfWeek(Calendar.SUNDAY);
//		     SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
//			 String date = format1.format(cal.getTime());
//			 //System.out.println("ggggggg"+cal.get(Calendar.DAY_OF_WEEK)+ " "+date_a+" "+date_m+" "+date_j+" "+date);
//				  //***********recuperer les 3 dernier chiffre (001) du dernier serial number   ajouter*************//
//
//			 String query2 = " SELECT  serial_number FROM fiche_suiveuse  where  (date_fiche=Format('"+date+"','dd/MM/yyyy') and "
//				   		+ "code_article='"+code_article+"' and code_chaine='"+code_chaine+"' ) and"+
//							" (Right (serial_number,3) IN (SELECT   max( Right(serial_number,3) )"+
//							  "FROM fiche_suiveuse where date_fiche=Format('"+date+"','dd/MM/yyyy') and code_article='"+code_article+"'  and code_chaine='"+code_chaine+"') )";
//
//		 list_conteur=CConnect.Requete(query2, bdd);
//		  if( list_conteur.size()>=1){
//
//			  //***********recuperer les trois conteur de la dernier ligne ajouter*************//
//			 //  System.out.println("jjjjjjjjjj"+CConnect.Requete(query2, bdd).get(0));
//			   String query="SELECT cont_assembl,cont_assqc,cont_actqc FROM fiche_suiveuse where "
//			   		+ "serial_number='"+list_conteur.get(0)+"'";
//			   list_conteur2=CConnect.Requete(query, bdd);
//
//			   if(list_conteur2.size()>=1){
//
//				count_ass=Integer.parseInt(list_conteur2.get(0));
//			    count_assqc=Integer.parseInt(list_conteur2.get(1));
//				count_actqc=Integer.parseInt(list_conteur2.get(2));
//			    cz=Integer.parseInt(list_conteur.get(0).substring(5,6));
//			    wek=Integer.parseInt(list_conteur.get(0).substring(3,5));
//			    anne=Integer.parseInt(list_conteur.get(0).substring(1 ,3));
//
//			      int jour= cal.get(Calendar.DAY_OF_WEEK);
//				  int week= cal.get(Calendar.WEEK_OF_YEAR);
//				  String[] date_s = date.toString().split("/");
//	              int annee = Integer.parseInt(date_s[2].substring(2)); // 004
////			    	 System.out.println("hjjjjjh"+cz+" "+jour);
////			    	 System.out.println("hjjjjjh"+wek+" "+week);
////			    	 System.out.println("hjjjjjh"+anne+" "+annee);
//
//
//
//		      if(cz==jour&&wek==week&&anne==annee){
//		    	  if(count_ass<ass)
//		    	     count_ass=count_ass+1;
//		    	  if(count_actqc<actqc)
//	            	 count_actqc=count_actqc+1;
//
//	            	// if(count_ass==ass/2+1){
//	                  if(count_assqc==0) count_assqc=0;
//	                  else 	   count_assqc=count_assqc+0;
//
//
//	            		// }
//	                //  System.out.println("jjjjjjjjjj"+count_ass);
//		      }
//		      }}
//		      else {
//	            		 count_ass=1;
//	            		 count_assqc=0;
//	            		 count_actqc=1;
//		      }
//
//
//
//		return code;
//		}
//

	public ArrayList<String> select_article_code_etq(String entrepr){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT  distinct article.code_article,designation FROM article,nomoclature"
				+ " where entreprise='"+entrepr+"' "
				+ " and nomoclature.code_article=article.code_article and indice<>0  and indice<>5";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}

	public ArrayList<String> select_color(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct color from imp_portable";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}

	public ArrayList<String> select_color_embalage(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct couleur from imp_emballage";
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}



	public ArrayList<String> select_modele(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct model from etiq_fournisseur";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}

	public ArrayList<String> select_size_tpe(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct carton_size from imp_emballage_tpe";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}



	public ArrayList<String> select_size_palette(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct carton_size from imp_embalage_palette";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}

	public ArrayList<String> select_dimension_etq(String indice){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct dimension from dimension_etq where indice='"+indice+"'";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}






	public ArrayList<String> select_size(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct carton_size from imp_emballage";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}


	public ArrayList<String> select_article_code_etqphone(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT  distinct article.code_article,designation FROM article,nomoclature"
				+ " where  nomoclature.code_article=article.code_article and indice=5";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}



	public ArrayList<String> select_article_tout(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT  distinct article.code_article,designation FROM article"
				+ " where  indice=5 or indice=3 or indice=2";
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}





	public ArrayList<String> select_article_code_tpe(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT  distinct article.code_article,designation FROM article "
				+ " where  indice=3 ";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}

	public ArrayList<String> select_article_composant(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT  distinct article.code_article,designation FROM article "
				+ " where  indice=6 ";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}


	public ArrayList<String> select_article_code(String entrepr){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT  distinct article.code_article,designation FROM article,nomoclature"
				+ " where entreprise='"+entrepr+"' "
				+ " and nomoclature.code_article=article.code_article ";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}

	public ArrayList<String> excel_select(String list){
		ArrayList <String>resultat= new ArrayList<String>() ;

	/*String query5 = "SELECT article.code_article,article.model,serial_number,software, "
		   	 		+ " nom_fourniss,designation_chaine,date_fiche"
		   	 		+ " from article,article_produit, fournisseur,Chaine,fiche_suiveuse "
		  +" where  article.code_article=fiche_suiveuse.code_article and article.code_article=article_produit.code_article "
		  +" and Chaine.code_chaine=fiche_suiveuse.code_chaine and article_produit.codep_fourniss=fournisseur.code_fourniss"
		   	+" and indice in(2) and serial_number='"+list+"'" ;*/

		String query5 = "SELECT article.code_article,article.designation,article.model,serial_number,software, "
				+ " nom_fourniss,designation_chaine,date_fiche"
				+ " from article,article_produit, fournisseur,Chaine,fiche_suiveuse "
				+" where  article.code_article=fiche_suiveuse.code_article and article.code_article=article_produit.code_article "
				+" and Chaine.code_chaine=fiche_suiveuse.code_chaine and article_produit.codep_fourniss=fournisseur.code_fourniss "
				+"  and indice in(2) and serial_number='"+list+"'";
		resultat=CConnect.Requete(query5,bdd);

		if(resultat.size()>1){
		    /* String query1 ="select designation ,article.model from article where  article.code_article   in"
		     		+ " (select code_nomo_art from nomoclature where code_article ='"+resultat.get(0)+"')"
		     		+" and indice in(1,4) order by indice ASC";

		     resultat.addAll(CConnect.Requete(query1,bdd));

		     System.out.println("dfgfdgfdgfg "+resultat);*/

		}

		return resultat;



	}
	/*ouii*/

	public ArrayList<String> select(String filter,String date_d,String date_f){
		ArrayList <String>  resultat= new ArrayList<String>() ;;
		   	 /*   String query5 = "SELECT designation,designation_chaine,Format(date_fiche,'dd/MM/yyyy'), serial_number, code_commercial, fiche_suiveuse.code_article"
		   	    		+ " from article,Chaine,fiche_suiveuse"
		   	    		+ " where article.code_article=fiche_suiveuse.code_article "
		   	    		+ " and Chaine.code_chaine=fiche_suiveuse.code_chaine  and (date_fiche between Format('"+date_d+"', 'dd/MM/yyyy')" +
						"AND Format('"+date_f+"', 'dd/MM/yyyy'))" ;*/


		String query5 = "SELECT designation, designation_chaine, date_fiche, serial_number, code_commercial, fiche_suiveuse.code_article"+
				" FROM article, Chaine, fiche_suiveuse"
				+" WHERE article.code_article=fiche_suiveuse.code_article"
				+" and Chaine.code_chaine=fiche_suiveuse.code_chaine and "+
				" date_fiche BETWEEN  #"+date_traiter(date_d)+"# AND #"+date_traiter(date_f)+"# "
				+"and (fiche_suiveuse.code_article like '%"+filter+"%'or serial_number like '%"+filter+"%' or " +
				"code_commercial like '%"+filter+"%'  or  designation like '%"+filter+"%' or fiche_suiveuse.code_chaine like '%"+filter+"%'  )  ";


		/*	String query5 = "SELECT designation,designation_chaine,Format(date_fiche,'dd/MM/yyyy'), serial_number, code_commercial, code_article "
					+ " from recap_fiche where (date_fiche between Format('"+date_d+"', 'dd/MM/yyyy') AND Format('"+date_f+"', 'dd/MM/yyyy')) order by date_fiche DESC ";*/

		resultat=CConnect.Requete(query5,bdd);
		System.out.println(filter+" code "+date_d+" offf "+resultat);



		return resultat;
	}

	public ArrayList<String> select_portable(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT imp_portable.code_article,designation,model,color,serial,code,num_arpt "
				+ " from article,imp_portable"
				+ " where article.code_article=imp_portable.code_article"
				;
		resultat=CConnect.Requete(query5,bdd);
		return resultat;

	}

	public ArrayList<String> select_etq_tpe(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT etiquette_tpe.code_article,designation,model,code_tpe,arpt, date_tpe "
				+ " from etiquette_tpe,article "
				+ " where article.code_article=etiquette_tpe.code_article"
				;


		resultat=CConnect.Requete(query5,bdd);

		return resultat;

	}


	public ArrayList<String> select_composant(){
		ArrayList <String>resultat= new ArrayList<String>() ;

		String query5 = "SELECT imp_composant.code_carton,article.code_article,designation,reference,serie,qte_bobine "
				+ " from article,imp_composant,imp_composant_bobine"
				+ " where article.code_article=imp_composant.code_article and imp_composant.code_carton=imp_composant_bobine.code_carton "
				;
		resultat=CConnect.Requete(query5,bdd);

		return resultat;

	}


	public ArrayList<String> select_tpe(){
		ArrayList <String>resultat= new ArrayList<String>() ;

		String query5 = "SELECT imp_emballage_tpe.parcel,article.code_article,date_emb,designation,gw,qte,imei,sn, code_enie "
				+ " from article,imp_emballage_tpe,emei_tpe "
				+ " where article.code_article=imp_emballage_tpe.code_article "
				+ "and imp_emballage_tpe.parcel=emei_tpe.parcel "
				;
		resultat=CConnect.Requete(query5,bdd);

		return resultat;

	}


	public ArrayList<String> select_tpe_3code(){
		ArrayList <String>resultat= new ArrayList<String>() ;

		String query5 = " SELECT code_article,article.designation, code_enie, Chaine.code_chaine, designation_chaine, sn, imei, date_tpe "
				+ " from article,etiquette_tpe_3code,chaine "
				+ " where article.code_article=etiquette_tpe_3code.id_article "
				+ " and  Chaine.code_chaine=etiquette_tpe_3code.code_chaine ";
		resultat=CConnect.Requete(query5,bdd);
        System.out.println(resultat);
		return resultat;

	}


	public ArrayList<String> select_etqemballage(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT TOP 20 parcel,imp_emballage.code_article,designation,model,couleur,delivery,gw,nw,carton_size,commentaire,date_emb,"
				+ " imei1,imei2 "
				+ " from article,imp_emballage,emei_table"
				+ " where article.code_article=imp_emballage.code_article"
				+ " and  emei_table.parcel_code=imp_emballage.parcel "
				+ "   order by date_emb DESC"
				;
		resultat=CConnect.Requete(query5,bdd);

		return resultat;

	}



	public ArrayList<String> select_etqpalette(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT TOP 20 parcel,imp_embalage_palette.code_article,designation"
				+ ",model,gw,nw,carton_size,date_emb,"
				+ " parcel_carton "
				+ " from article,imp_embalage_palette,emei_palette"
				+ " where article.code_article=imp_embalage_palette.code_article"
				+ " and  emei_palette.parcel_palette=imp_embalage_palette.parcel "
				+ " order by date_emb DESC"
				;
		resultat=CConnect.Requete(query5,bdd);

		return resultat;

	}


	public int select_indice(String arti){
		int indi = 0;
		String requet="select indice from article where code_article='"+arti+"'";
		if(CConnect.Requete(requet,bdd).size()!=0){
			indi=Integer.parseInt(CConnect.Requete(requet,bdd).get(0));}
		return indi;
	}

	public String select_model(String article){
		String model="";
		String query5 = "SELECT model from article where code_article='"+article+"'";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			model=CConnect.Requete(query5,bdd).get(0);
		}
		return model;

	}

	public void	ajout_etiqphone(String article,String color,String code,String serial,String arpt){
		String query5 = "insert into imp_portable(code_article,color,code,serial,num_arpt) values ('"+article+"','"+color+"','"+code+"','"+serial+"','"+arpt+"')";
		CConnect.Insert(query5,bdd);
	}

	public void setupdate_portable(){
		String update= "update imp_portable set imp_port='TRUE'";
		CConnect.Insert(update,bdd);
	}

	public boolean exist_emballage(String parcel, String table){
		String select = "SELECT * from "+table+" where parcel='"+parcel+"'";

		if(CConnect.Requete(select, bdd).size()>=1){

			return true;
		}
		else return false;
	}



	public boolean exist_emballage_palette(String parcel){
		String select = "SELECT * from imp_embalage_palette where parcel='"+parcel+"'";

		if(CConnect.Requete(select, bdd).size()>=1){

			return true;
		}
		else return false;
	}


	public boolean exist_emballage_tpe(String parcel){
		String select = "SELECT * from imp_emballage_tpe where parcel='"+parcel+"'";

		if(CConnect.Requete(select, bdd).size()>=1){

			return true;
		}
		else return false;
	}


	public boolean exist_codeenie_tpe(String code){
		String select = "SELECT * from etiquette_tpe_3code where code_enie='"+code+"' ";

		if(CConnect.Requete(select, bdd).size()>=1){

			return true;
		}
		else return false;
	}


	public boolean exist_composant(String code_carton){
		String select = "SELECT * from imp_composant where code_carton='"+code_carton+"'";

		if(CConnect.Requete(select, bdd).size()>=1){

			return true;
		}
		else return false;
	}


	public void	ajout_embalage(String parcel,String article,String couleur,String qte,String delivery,String gw,String nw,String dimension,String commentaire
			,String date,String id_pal){
		int id_palette=select_id_palette(id_pal);
		String query5 = "insert into imp_emballage(parcel,code_article,couleur,qte,delivery,gw,carton_size,nw,commentaire,date_emb,id_palette) "
				+ "values ('"+parcel+"','"+article+"','"+couleur+"','"+qte+"','"+delivery+"','"+gw+"','"+dimension+"','"+nw+"','"+commentaire+"',#"+date_traiter(date)+"# ,"
				+ ""+id_palette+")";
		CConnect.Insert(query5,bdd);
	}



	public void	ajout_embalage_palette(String parcel,String article,String qte,String gw,String nw,String dimension,String commentaire,String date){
		String query5 = "insert into imp_embalage_palette   "
				+ "(parcel,code_article,qte,gw,carton_size,nw,commentaire,date_emb) "
				+ "values "
				+ "('"+parcel+"','"+article+"','"+qte+"','"+gw+"','"+dimension+"','"+nw+"','"+commentaire+"',#"+date_traiter(date)+"# )";
		CConnect.Insert(query5,bdd);
	}


	public void	ajout_embalage_tpe(String parcel,String article,String qte,String gw,String dimension,String commentaire,String date, String id_pal){
		int id_palette= select_id_palette(id_pal);
		System.out.println(" bien   "+id_palette);
		String query5 = "insert into imp_emballage_tpe(parcel,code_article,qte,gw,carton_size,commentaire,date_emb,id_palette) "
				 + "values ('"+parcel+"','"+article+"','"+qte+"','"+gw+"','"+dimension+"','"+commentaire+"',#"+date_traiter(date)+"#"
				+ ", "+id_palette+")";
		CConnect.Insert(query5,bdd);
	}

	public void	ajout_emei(String parcel,String emei1,String emei2){
		String query5 = "insert into emei_table(parcel_code,imei1,imei2) "
				+ "values ('"+parcel+"','"+emei1+"','"+emei2+"')";
		CConnect.Insert(query5,bdd);
	}


	public void	ajout_parcel_palette(String parcel,String parcel_carton){
		String query5 = "insert into emei_palette(parcel_palette,parcel_carton) "
				+ "values ('"+parcel+"','"+parcel_carton+"')";
		CConnect.Insert(query5,bdd);
	}

	public void	ajout_emei_tpe(String parcel,String emei1,String sn, String code_enie){
		String query5 = "insert into emei_tpe(parcel,imei,sn,code_enie) "
				+ "values ('"+parcel+"','"+emei1+"','"+sn+"','"+code_enie+"')";
		CConnect.Insert(query5,bdd);
	}


	public void	update_embalage(String parcel,String article,String couleur,String qte
			,String delivery,String gw,String nw,String dimension,String commentaire,String date, String id_pal){
		System.out.println(commentaire+" "+date);
		int id_palette= select_id_palette(id_pal);
		String query5 = "update imp_emballage set code_article='"+article+"',couleur='"+couleur+"'"
				+ ",qte='"+qte+"',delivery='"+delivery+"',gw='"+gw+"',carton_size='"+dimension+"',"
				+ "nw='"+nw+"' , commentaire='"+commentaire+"',date_emb= #"+date_traiter(date)+"#"
				+ " , id_palette="+id_palette+" where "
				+ " parcel='"+parcel+"'";
		CConnect.Insert(query5,bdd);
	}



	public void	update_embalage_palette(String parcel,String article,String qte,
										   String gw,String nw,String dimension,String commentaire,String date){
		System.out.println(commentaire+" "+date);
		String query5 = "update imp_embalage_palette set code_article='"+article+"'"
				+ ",qte='"+qte+"',gw='"+gw+"',carton_size='"+dimension+"',"
				+ "nw='"+nw+"' , commentaire='"+commentaire+"',date_emb= #"+date_traiter(date)+"#  where "
				+ " parcel='"+parcel+"'";
		CConnect.Insert(query5,bdd);
	}



	public void	update_embalage_tpe(String parcel,String article,String qte
			,String gw,String dimension,String commentaire,String date, String id_pal){
		int id_palette= select_id_palette(id_pal);

		String query5 = "update imp_emballage_tpe set code_article='"+article+"'"
				+ ",qte='"+qte+"',gw='"+gw+"',carton_size='"+dimension+"',"
				+ " commentaire='"+commentaire+"',date_emb= #"+date_traiter(date)+"#  "
				+ ", id_palette="+id_palette+" where "
				+ " parcel='"+parcel+"'";
		CConnect.Insert(query5,bdd);
	}


	public void	update_composant(String code_carton,String article,String qte_bobine
			,String nbr_bobine,String reference,String recherche){
		String query5 = "update imp_composant set code_carton='"+code_carton+"',code_article='"+article+"'"
				+ ",qte_bobine='"+qte_bobine+"',nbr_bobine='"+nbr_bobine+"',reference='"+reference+"' "
				+ " where code_carton='"+recherche+"'";
		CConnect.Insert(query5,bdd);
	}


	public void	delete_composant_bobine(String code_carton){
		String query5 = "delete from imp_composant_bobine where code_carton='"+code_carton+"'";
		CConnect.Insert(query5,bdd);
	}


	public String counteur_composant_bobine(String code_article){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String code="";
		        /*  String select=" select top 1 (Right((Left (serie,5) ) ,4))   from imp_composant_bobine ,imp_composant"
		          		+ " where code_article='"+code_article+"' and "
					   		+ " imp_composant_bobine.code_carton=imp_composant.code_carton order by serie desc";*/

		        /*  String select=" select top 1 (Right((Left (serie,5) ) ,4))   from imp_composant_bobine ,imp_composant"
			          		+ " where code_article='"+code_article+"' and "
						   		+ " imp_composant_bobine.code_carton=imp_composant.code_carton order by serie desc";*/


		String  arti_slash= code_article.substring(0,3)+"-"+code_article.substring(3,7); // 004
		String select = " SELECT  count(*)  FROM imp_composant,imp_composant_bobine  where "
				+ "  (code_article like '%"+code_article+"' or code_article='"+arti_slash+"') "
				+ "and imp_composant.code_carton=imp_composant_bobine.code_carton"
				;
		resultat= CConnect.Requete(select,bdd);
		if(resultat.size()!=0){
			code=resultat.get(0);
			System.out.println("eeeee"+code);
		}
		else{
			code="0000";
		}
		return code;
	}



	public void	insert_composant_bobine(String code_carton,String serie){

		String query5 = "insert into imp_composant_bobine (code_carton,serie) values ('"+code_carton+"','"+serie+"') ";
		CConnect.Insert(query5,bdd);
		//	CConnect.close();

	}



	public void	delete_emei(String parcel){
		String delete = "delete from   emei_table "
				+ " where parcel_code='"+parcel+"'";
		CConnect.Insert(delete,bdd);
	}


	public void	delete_emei_palette(String parcel){
		String delete = "delete from   emei_palette "
				+ " where parcel_palette='"+parcel+"'";
		CConnect.Insert(delete,bdd);
	}


	public void	delete_emei_tpe(String parcel){
		String delete = "delete from   emei_tpe "
				+ " where parcel='"+parcel+"'";
		CConnect.Insert(delete,bdd);
	}
	public void	update_emei(String parcel,String emei1,String emei2){


		String insert = "insert into emei_table (parcel_code,imei1,imei2) "
				+ "values ('"+parcel+"','"+emei1+"','"+emei2+"')";
		CConnect.Insert(insert,bdd);
	}


	public void	update_emei_palette(String parcel,String parcel_carton){


		String insert = "insert into emei_palette (parcel_palette,parcel_carton) "
				+ "values ('"+parcel+"','"+parcel_carton+"')";
		CConnect.Insert(insert,bdd);
	}


	public void	update_emei_tpe(String parcel,String emei1,String sn, String code_enie){

		String insert = "insert into emei_tpe (parcel,imei,sn,code_enie) "
				+ "values ('"+parcel+"','"+emei1+"','"+sn+"','"+code_enie+"' )";
		CConnect.Insert(insert,bdd);
	}

	public ArrayList<String> select_nw(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct nw from imp_emballage";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}



	public ArrayList<String> select_nw_palette(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct nw from imp_embalage_palette";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;
	}


	public ArrayList<String> select_gw_tpe(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct gw from imp_emballage_tpe";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}


	public ArrayList<String> select_gw_palette(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct gw from imp_embalage_palette";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}

	public ArrayList<String> select_gw(){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT distinct gw from imp_emballage";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}


	public void select_parcel_palette(String parcel){

		ArrayList <String>list_emai= new ArrayList<String>() ;
		exist="";
		String query2 = "SELECT article.code_article,designation,model,qte,nw,gw,carton_size FROM "
				+ "imp_embalage_palette,article where parcel='"+parcel+"' "
				+ " and article.code_article=imp_embalage_palette.code_article";
		list_emai=CConnect.Requete(query2, bdd);
		if(list_emai.size()>=1){
			article_c=list_emai.get(0)+" "+list_emai.get(1);
			model=list_emai.get(2);
			qte=list_emai.get(3);
			nw=list_emai.get(4);
			gw=list_emai.get(5);
			cartsize=list_emai.get(6);


		}
	}


	public void select_parcel(String parcel){

		ArrayList <String>list_emai= new ArrayList<String>() ;
		ArrayList <String>list_palette= new ArrayList<String>() ;
		exist="";
		String query2 = "SELECT article.code_article,designation,model,couleur,qte,nw,gw,delivery,carton_size,id_palette, date_emb FROM "
				+ "imp_emballage,article where  parcel='"+parcel+"' and article.code_article=imp_emballage.code_article"
				;




		list_emai=CConnect.Requete(query2, bdd);
		if(list_emai.size()>=1){
			article_c=list_emai.get(0)+" "+list_emai.get(1);
			model=list_emai.get(2);
			couleur=list_emai.get(3);
			qte=list_emai.get(4);
			nw=list_emai.get(5);
			gw=list_emai.get(6);
			delivery=list_emai.get(7);
			cartsize=list_emai.get(8);
			date_emb=list_emai.get(10);

			String id_palette=list_emai.get(9);
			System.out.println("id_palette"+id_palette);

			String query = "SELECT parcel_palette FROM "
					+ "palette where  id_pal="+id_palette+" "
					;
			list_palette=CConnect.Requete(query, bdd);

			if(list_palette.size()!=0)
				palette=list_palette.get(0);
			else
				palette="---Selectionner une Palette-----";

		}
	}

	public ArrayList<String> select_emei(String parcel){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT imei1,imei2 from emei_table where parcel_code='"+parcel+"' ";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}



	public ArrayList<String> select_emei_palette(String parcel){
		ArrayList <String>resultat= new ArrayList<String>() ;
		String query5 = "SELECT parcel_carton from emei_palette where parcel_palette='"+parcel+"' ";
		//System.out.println(CConnect.Requete(query5, bdd));
		//	+ " and (code_famille='F02' or code_famille='F03') " ;
		if(CConnect.Requete(query5, bdd).size()>=1){
			resultat=CConnect.Requete(query5,bdd);
		}
		return resultat;

	}
    String code_enie,sn,imei,code_chaine,id_article,date_tpe="";
	public ArrayList<String>  selection_3tpe_champ(String code, String sn, String imei){
		ArrayList<String> list_tpe=new ArrayList<String>();

		String query = "SELECT code_enie,sn,imei,code_chaine,id_article,date_tpe from etiquette_tpe_3code "
				+" where code_enie='"+code+"' or sn='"+sn+"' or imei='"+imei+"'";
		list_tpe=CConnect.Requete(query, bdd);
         System.out.println("eee fffff"+list_tpe);
		if(list_tpe.size()==0){
			JOptionPane
					.showMessageDialog(
							null,
							"Ce TPE n'existe pas",
							"", JOptionPane.INFORMATION_MESSAGE);
		}

		return list_tpe;

	}


	public void  selection_tpe_champ(String code){
		ArrayList<String> list_tpe=new ArrayList<String>();
		ArrayList<String> list_palette=new ArrayList<String>();

		String query = "SELECT designation,parcel,article.code_article,gw,carton_size,date_emb,commentaire,qte,id_palette "
				+" from imp_emballage_tpe,article where parcel='"+code+"' "
				+ "and  article.code_article=imp_emballage_tpe.code_article";

		list_tpe=CConnect.Requete(query, bdd);
		//   System.out.println(code+" "+list_article.size()+" "+list_article);
		if(list_tpe.size()>=1){
			designation_tpe=(String) list_tpe.get(0);
			parcel=(String) list_tpe.get(1);

			code_article=(String) list_tpe.get(2);
			gw_tpe=(String) list_tpe.get(3);

			carton_size=(String) list_tpe.get(4);
			date_emb= list_tpe.get(5);
			commentaire=(String) list_tpe.get(6);
			qte_tpe=(String) list_tpe.get(7);
            System.out.println("jjjjjjj " +qte_tpe);
			String id_pal=(String) list_tpe.get(8);
			System.out.println(" id "+id_pal);
			String query2 = "SELECT parcel_palette"
					+" from palette where id_pal="+id_pal+"";
			list_palette=CConnect.Requete(query2, bdd);
			if(list_palette.size()!=0){
				palette=(String) list_palette.get(0);}
			else
			{
				palette="---Selectionner une Palette-----";
			}

		}

		else {


			JOptionPane
					.showMessageDialog(
							null,
							"Ce TPE n'existe pas",
							"", JOptionPane.INFORMATION_MESSAGE);
		}


	}



	public void  selection_composant_champ(String code_carton){
		ArrayList<String> list_tpe=new ArrayList<String>();

		String query = "SELECT Top 1 designation,imp_composant.code_article,qte_bobine,nbr_bobine,reference, serie"
				+ " from imp_composant,article,imp_composant_bobine where "
				+ " imp_composant.code_carton='"+code_carton+"' and article.code_article=imp_composant.code_article"
				+ " and imp_composant.code_carton=imp_composant_bobine.code_carton order by serie desc ";

		list_tpe=CConnect.Requete(query, bdd);
		if(list_tpe.size()>=1){
			designation_composant=(String) list_tpe.get(0);
			code_article_composant=(String) list_tpe.get(1);

			qte_bobine=(String) list_tpe.get(2);
			nbr_bobine=(String) list_tpe.get(3);

			reference=(String) list_tpe.get(4);

			serie=(String) list_tpe.get(5);


		}

		else {


			JOptionPane
					.showMessageDialog(
							null,
							"Ce carton n'existe pas",
							"", JOptionPane.INFORMATION_MESSAGE);
		}


	}



	public ArrayList<String>   selection_tpe_table(String code){

		ArrayList<String> list_tpe_tab=new ArrayList<String>();

		String query = "SELECT  imei,sn, code_enie "
				+ " from emei_tpe where parcel='"+code+"'";

		list_tpe_tab=CConnect.Requete(query, bdd);

		return    list_tpe_tab;


	}


	public  String   select_count_palette(String table,String palette){
		ArrayList<String> resultat=new ArrayList<>();
		String code="0";
		String insert = "select COUNT(*) from "+table+",palette where id_pal=id_palette  and  parcel_palette='"+palette+"'";
		resultat=CConnect.Requete(insert, bdd);
		if(resultat.size()!=0){
			code=resultat.get(0);
		}
		return code;
	}


	public  int   select_id_palette(String palette){

		String insert = "select id_pal from palette where  parcel_palette='"+palette+"'";
		return Integer.parseInt(CConnect.Requete(insert, bdd).get(0));
	}


	public  ArrayList<String>   select_palette(String prefix){

		ArrayList<String> list_palette=new ArrayList<String>();

		String insert = "select parcel_palette from palette where parcel_palette like '"+prefix+"%' ";
		list_palette=CConnect.Requete(insert, bdd);

		return list_palette;

	}



	public String afficher_conteur_palette(String prefix,String chaine)
	{
		String code = null;
		int count=0;

		NumberFormat formatter = new DecimalFormat("0000");
		NumberFormat forme = new DecimalFormat("00");

		String cont;

		final Calendar cal = Calendar.getInstance( );  // date du jour

		cal.setFirstDayOfWeek(Calendar.SUNDAY);
		int week1 = cal.get(Calendar.WEEK_OF_YEAR) ;
		String week = forme.format(week1);
		int jour1 = cal.get(Calendar.DAY_OF_WEEK);
		String jour = form.format(jour1);
		String anne1 = String.valueOf(cal.get(Calendar.YEAR)).substring(2);
		String code_pal=prefix+chaine+jour+week+anne1;


		String query2 = " SELECT max(parcel_palette) FROM palette  where parcel_palette like '"+code_pal+"%' ";
		cont=CConnect.Requete(query2, bdd).get(0);

		System.out.print(cont);

		if(cont!=null){
			count=Integer.parseInt(cont.substring(cont.length()-4))+1;
			System.out.println(count+" ffgfffffff "+cont.substring(10));
			code= code_pal+formatter.format(count);

		}
		else {
			code= code_pal+"0001";
		}


		String query3 = " insert into palette (parcel_palette) values('"+code+"') ";
		CConnect.Requete(query3, bdd);
		return code;
	}


	public String nbr_palette_portable(String filter,String datedebut,String datefin)
	{
		String resultat="0";
		String insert;
		if(!filter.equals("")){
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT  parcel_palette "
					+ "from palette,article,imp_emballage,emei_table  "
					+ " where palette.id_pal=imp_emballage.id_palette and article.code_article = imp_emballage.code_article and imp_emballage.parcel = emei_table.parcel_code and "
					+ " imp_emballage.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#  and"
					+ " (imei1 = '"+filter+"' or imei2 = '"+filter+"'  or imp_emballage.parcel = '"+filter+"' or"
					+ " article.designation = '"+filter+"' or  article.code_article= '"+filter+"' or couleur = '"+filter+"')";
		}
		else{
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT  parcel_palette "
					+ "from palette,article,imp_emballage,emei_table  "
					+ " where palette.id_pal=imp_emballage.id_palette and article.code_article = imp_emballage.code_article and imp_emballage.parcel = emei_table.parcel_code and "
					+ "imp_emballage.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"# ";
		}

		if(CConnect.Requete(insert,bdd).size()>0)
			resultat=CConnect.Requete(insert,bdd).get(0).toString();
		System.out.println("eeee edfsf\\"+resultat);
		return  resultat;
	}
	public ArrayList<String> recherche_palette_tpe(String filter,String datedebut,String datefin)
	{
		ArrayList <String>resultat= new ArrayList<String>() ;
		String insert="";
		if(!filter.equals("")){
			insert = "select article.code_article, article.designation, parcel_palette, imp_emballage_tpe.parcel, emei_tpe.sn, emei_tpe.imei  "
					+ "from palette,article,imp_emballage_tpe,emei_tpe "
					+ " where palette.id_pal=imp_emballage_tpe.id_palette and article.code_article = imp_emballage_tpe.code_article"
					+ " and imp_emballage_tpe.parcel = emei_tpe.parcel  "
					+ " and imp_emballage_tpe.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#   "
					+ "AND( imei = '"+filter+"' or emei_tpe.sn = '"+filter+"' or  imp_emballage_tpe.parcel  = '"+filter+"' "
					+ " or article.code_article = '"+filter+"' or article.designation= '"+filter+"')";
		}else{
			insert = "select article.code_article, article.designation, parcel_palette, imp_emballage_tpe.parcel, emei_tpe.sn, emei_tpe.imei  "
					+ "from palette,article,imp_emballage_tpe,emei_tpe "
					+ " where palette.id_pal=imp_emballage_tpe.id_palette and article.code_article = imp_emballage_tpe.code_article"
					+ " and imp_emballage_tpe.parcel = emei_tpe.parcel  "
					+ " and imp_emballage_tpe.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#   ";
		}

		resultat=CConnect.Requete(insert,bdd);
		System.out.println("eeee edfsf88888mmmm\\"+resultat);
		return  resultat;
	}


	public String nbr_carton_portable(String filter,String datedebut,String datefin)
	{
		String resultat="0";
		String insert="";
		if(filter.equals("")){
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT imp_emballage.parcel "
					+ "from palette,article,imp_emballage,emei_table  "
					+ " where palette.id_pal=imp_emballage.id_palette and article.code_article = imp_emballage.code_article and imp_emballage.parcel = emei_table.parcel_code and "
					+ "imp_emballage.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#  ";
		}
		else{
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT imp_emballage.parcel "
					+ "from palette,article,imp_emballage,emei_table  "
					+ " where palette.id_pal=imp_emballage.id_palette and article.code_article = imp_emballage.code_article and imp_emballage.parcel = emei_table.parcel_code and "
					+ "imp_emballage.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"# and"
					+ " (imei1 = '"+filter+"' or imei2 = '"+filter+"' or imp_emballage.parcel = '"+filter+"' or"
					+ " article.designation = '"+filter+"' or  article.code_article = '"+filter+"' or couleur = '"+filter+"')";
		}

		if(CConnect.Requete(insert,bdd).size()>0)
			resultat=CConnect.Requete(insert,bdd).get(0).toString();
		// System.out.println("eeee edfsf\\"+resultat);
		return  resultat;
	}

	public ArrayList<String> recherche_palette_portable_vide(String datedebut,String datefin)
	{
		ArrayList <String>resultat= new ArrayList<String>() ;
		String insert = "select article.code_article, article.designation, parcel_palette, imp_emballage.parcel, emei_table.imei1, emei_table.imei2 , imp_emballage.couleur "
				+ "from palette,article,imp_emballage,emei_table  "
				+ " where palette.id_pal=imp_emballage.id_palette and article.code_article = imp_emballage.code_article and imp_emballage.parcel = emei_table.parcel_code and "
				+ "imp_emballage.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#  ";

		resultat=CConnect.Requete(insert,bdd);
		// System.out.println("eeee edfsf\\"+resultat);
		return  resultat;
	}

	public ArrayList<String> recherche_palette_portable(String filter,String datedebut,String datefin)
	{
		ArrayList <String>resultat= new ArrayList<String>() ;
		String insert = "select article.code_article, article.designation, parcel_palette, imp_emballage.parcel, emei_table.imei1, emei_table.imei2 , imp_emballage.couleur "
				+ "from palette,article,imp_emballage,emei_table  "
				+ " where palette.id_pal=imp_emballage.id_palette and article.code_article = imp_emballage.code_article and imp_emballage.parcel = emei_table.parcel_code and "
				+ "imp_emballage.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#  and "
				+ " (imei1 = '"+filter+"' or imei2 = '"+filter+"' or imp_emballage.parcel= '"+filter+"' or"
				+ " article.designation = '"+filter+"' or  article.code_article = '"+filter+"' or couleur = '"+filter+"' or parcel_palette='"+filter+"')";

		resultat=CConnect.Requete(insert,bdd);
		// System.out.println("eeee edfsf\\"+resultat);
		return  resultat;
	}


	public String nbr_carton_palette_potable (String palette_parcel){

		String select ="select count(*) from imp_emballage,palette where id_pal=id_palette and parcel_palette= '"+palette_parcel+"'";
		return  CConnect.Requete(select,bdd).get(0);
	}

	public String nbr_palette_tpe(String filter,String datedebut,String datefin)
	{
		String resultat="0";
		String insert="";
		if(!filter.equals("")){
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT  palette.parcel_palette "
					+ "from palette,article,imp_emballage_tpe,emei_tpe "
					+ " where palette.id_pal=imp_emballage_tpe.id_palette and article.code_article = imp_emballage_tpe.code_article"
					+ " and imp_emballage_tpe.parcel = emei_tpe.parcel  "
					+ " and imp_emballage_tpe.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#   "
					+ "AND ( imei = '"+filter+"' or emei_tpe.sn = '"+filter+"' or  imp_emballage_tpe.parcel  = '"+filter+"'"
					+ " or article.code_article = '"+filter+"' or article.designation = '"+filter+"')";
		}
		else{
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT  palette.parcel_palette "
					+ "from palette,article,imp_emballage_tpe,emei_tpe "
					+ " where palette.id_pal=imp_emballage_tpe.id_palette and article.code_article = imp_emballage_tpe.code_article"
					+ " and imp_emballage_tpe.parcel = emei_tpe.parcel  "
					+ " and imp_emballage_tpe.date_emb BETWEEN  #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"# ";

		}
		if(CConnect.Requete(insert,bdd).size()>0)
			resultat=CConnect.Requete(insert,bdd).get(0).toString();
		System.out.println("pal"+resultat);
		return  resultat;
	}
	public String nbr_carton_tpe(String filter,String datedebut,String datefin)
	{
		String resultat="0";
		String insert="";
		if(!filter.equals("")){
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT imp_emballage_tpe.parcel  "
					+ "from palette,article,imp_emballage_tpe,emei_tpe "
					+ " where palette.id_pal=imp_emballage_tpe.id_palette and article.code_article = imp_emballage_tpe.code_article"
					+ " and imp_emballage_tpe.parcel = emei_tpe.parcel  "
					+ " and imp_emballage_tpe.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#  "
					+ "AND( imei = '"+filter+"' or emei_tpe.sn = '"+filter+"' or  imp_emballage_tpe.parcel  = '"+filter+"'"
					+ " or article.code_article = '"+filter+"' or article.designation = '"+filter+"')";

		}
		else{
			insert = "SELECT Count(*) AS N FROM (SELECT DISTINCT imp_emballage_tpe.parcel  "
					+ "from palette,article,imp_emballage_tpe,emei_tpe "
					+ " where palette.id_pal=imp_emballage_tpe.id_palette and article.code_article = imp_emballage_tpe.code_article"
					+ " and imp_emballage_tpe.parcel = emei_tpe.parcel  "
					+ " and imp_emballage_tpe.date_emb BETWEEN #"+date_traiter(datedebut)+"#  and #"+date_traiter(datefin)+"#  ";

		}
		// System.out.println("eeee edfsf\\"+resultat);
		if(CConnect.Requete(insert,bdd).size()>0)
			resultat=CConnect.Requete(insert,bdd).get(0).toString();
		return  resultat;
	}

	public String nbr_carton_palette_tpe (String palette_parcel){
		String resultat="0";
		String insert ="SELECT Count(*)  from imp_emballage_tpe,palette where id_pal=id_palette and parcel_palette= '"+palette_parcel+"'";
		//System.out.println(insert);
		resultat=CConnect.Requete(insert,bdd).get(0);
		// System.out.println("selcty"+resultat);

		return resultat;
	}

	public ArrayList<String> parcel_numero(){
		ArrayList<String> resultat;
		String insert= "select top 1 parcel from imp_emballage order by date_emb DESC, parcel DESC";
		resultat=CConnect.Requete(insert,bdd);
		return resultat;
	}





	public void supprimer_carton (String table_p, String table_s, String parcel) {
		String resultat = "0";
		String delete_p = "delete from " + table_p + " where parcel= '" + parcel + "'";
		CConnect.Insert(delete_p, bdd);


		String delete_d="delete from " + table_s + " where ";
		if(table_s.trim().equals("emei_table"))
			delete_d+= " parcel_code= '" + parcel + "'";
		else if(table_s.trim().equals("emei_tpe"))
			delete_d+= " parcel= '" + parcel + "'";

		CConnect.Insert(delete_d, bdd);

	}








    public void delete_etq_fournisseur(String emei1, String emei2) {
        String delete = "select * from etq_fournisseur where emei1='" + emei1 + "' or emei1='" + emei2 + "' ";
        CConnect.Insert(delete, bdd);
    }

}
