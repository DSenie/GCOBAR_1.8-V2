package logiciel_etiq.Controller;


import java.util.ArrayList;
import java.util.Calendar;

public class gestion_composant extends gestion_general_imp{


    public void	update_composant(String code_carton,String article,String qte_bobine
            ,String nbr_bobine,String reference,String recherche){
        String query5 = "update etiquette_composant set code_carton='"+code_carton+"',code_article='"+article+"'"
                + ",qte_bobine='"+qte_bobine+"',nbr_bobine='"+nbr_bobine+"',reference='"+reference+"' "
                + " where UPPER(code_carton)=UPPER('"+recherche+"')";
        CConnect.Insert(query5);
    }


    public void	delete_composant_bobine(String code_carton){
        String query5 = "delete from etiquette_composant_bobine where UPPER(code_carton)=UPPER('"+code_carton+"')";
        CConnect.Insert(query5);
    }




    public int counteur_composant_bobine(String code_article){

        String code="0000";
        String  arti_slash=code_article;
        if(code_article.contains("/"))
         arti_slash= code_article.substring(0,3)+"-"+code_article.substring(3,7); // 004
        String select = " SELECT  count(*)  FROM etiquette_composant,etiquette_composant_bobine  where "
                + "  (code_article like '%"+code_article+"' or UPPER(code_article)=UPPER('"+arti_slash+"')) "
                + "and etiquette_composant.code_carton=etiquette_composant_bobine.code_carton"
                ;
         ArrayList<String> resultat= CConnect.Requete(select);
        if(resultat.size()!=0){
            code=resultat.get(0);
        }

        return Integer.parseInt(code);
    }



    public void	insert_composant_bobine(String code_carton,String serie){

        String query5 = "insert into etiquette_composant_bobine (code_carton,serie) values ('"+code_carton+"','"+serie+"') ";
        CConnect.Insert(query5);
    }




    public void ajouter_composant(String code_carton,String code_art,String qte_bobine,String nbr_bobine,String reference){
        String insert = " insert into etiquette_composant (code_carton,code_article,qte_bobine,nbr_bobine,reference) "
                + " values('"+code_carton+"','"+code_art+"','"+qte_bobine+"','"+nbr_bobine+"','"+reference+"')";
        CConnect.Insert(insert);
    }



    public boolean exist_composant(String code_carton){
        String select = "SELECT * from etiquette_composant where UPPER(code_carton)=UPPER('"+code_carton+"')";
        return CConnect.Requete(select).size()>=1;
    }



    public String afficher_conteur_composant(String code_article,String nbr_bobine)
    {
        String cont= "0001";
        String  arti_slash=code_article;
        if(code_article.contains("/"))
         arti_slash= code_article.substring(0,3)+"-"+code_article.substring(3,7); // 004

        String query2 = " SELECT  code_carton  FROM etiquette_composant  where "
                + " (substr (code_carton,2,4) IN (SELECT   max( substr(code_carton,2,4) ) "+
                " FROM etiquette_composant where code_article like '%"+code_article+"' or UPPER(code_article)=UPPER('"+arti_slash+"')  ))";

        ArrayList<String> list_affiche_cont= CConnect.Requete(query2);
        if(list_affiche_cont.size()>=1){
            cont = formatter4.format(Integer.parseInt(list_affiche_cont.get(0).substring(1, 5))+1);
        }

        String year =String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2,4) ;
        return "L" + cont + arti_slash + nbr_bobine +year;
    }



    public ArrayList<String>  selection_composant_champ(String code_carton){

        String query = "SELECT  designation,etiquette_composant.code_article,qte_bobine,nbr_bobine,reference, serie"
                + " from etiquette_composant,article,etiquette_composant_bobine where "
                + " UPPER(etiquette_composant.code_carton)=UPPER('"+code_carton+"') and article.code_article=etiquette_composant.code_article"
                + " and etiquette_composant.code_carton=etiquette_composant_bobine.code_carton order by serie desc limit 1";

        return CConnect.Requete(query);

    }



    public ArrayList<String> select_composant(){

        String query5 = "SELECT etiquette_composant.code_carton,article.code_article||' '||designation,reference,serie,qte_bobine "
                + " from article,etiquette_composant,etiquette_composant_bobine"
                + " where article.code_article=etiquette_composant.code_article and etiquette_composant.code_carton=etiquette_composant_bobine.code_carton "
                ;

        return CConnect.Requete(query5);

    }


}
