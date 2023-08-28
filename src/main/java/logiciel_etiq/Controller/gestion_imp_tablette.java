package logiciel_etiq.Controller;

import java.util.ArrayList;
import java.util.Date;

import static logiciel_etiq.View.generale.date_traiter;

public class gestion_imp_tablette extends gestion_general_imp {




    public ArrayList<String> select_tablette_fourn(String sn){
        ArrayList <String>resultat= new ArrayList<>() ;
        String query5 = "SELECT etiquette_tablette.model,designation, couleur, sn, parcel_carton, key_licence " +
                "FROM etiquette_tablette,article " +
                "where code_article=etiquette_tablette.model and  UPPER(sn)=UPPER('"+sn+"')  ";
        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }


    public ArrayList<String> tablette_licence_exist(String licence,String sn){
        ArrayList <String>resultat= new ArrayList<>() ;
        String query5 = "SELECT key_licence " +
                "FROM etiquette_tablette " +
                "where UPPER(key_licence)=UPPER('"+licence+"') and UPPER(sn)<> UPPER('"+sn+"')  ";
        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }



    public void insert_tablette_fournisseur(String model, String couleur, String sn, Date date,String parcel_carton,String key_licence)
    {
        String insert = "insert into etiquette_tablette (model,couleur,sn,date_imp,parcel_carton, key_licence)"
                + " values('"+model+"','"+couleur+"','"+sn+"','"+date_traiter(date)+"', '"+parcel_carton+"','"+key_licence+"')";
        CConnect.Insert(insert);

    }

    public void delete_tablette_fournisseur(String  sn){
        String delete= "delete from etiquette_tablette where UPPER(sn)=UPPER('"+sn+"')  ";


        System.out.println(delete);
        CConnect.Insert(delete);
    }




    public ArrayList<String>  selection_tablette_champ(String code){
        String query = "SELECT article.code_article,designation,parcel,gw,carton_size,date_emb,commentaire,qte,parcel_palette "
                +" from etiquette_emballage_tablette,article,palette where UPPER(parcel)=UPPER('"+code+"') "
                + "and  article.code_article=etiquette_emballage_tablette.code_article" +
                " and id_pal = id_palette";

            return CConnect.Requete(query);
        }





    public ArrayList<String>   selection_tablette_table(String code){
        String query = "SELECT  sn  from etiquette_tablette where parcel_carton='"+code+"'";
        return    CConnect.Requete(query);
    }




    public void	update_insert_embalage_tablette(String parcel,String article,String qte ,String gw,String dimension,String commentaire, String id_pal, Date date, String update_insert){
        int id_palette= select_id_palette(id_pal);
        String query;
        if(update_insert.equals("U")) {
             query= "update etiquette_emballage_tablette set code_article='"+article+"'"
                    + ",qte='"+qte+"',gw='"+gw+"',carton_size='"+dimension+"',"
                    + " commentaire='"+commentaire+"',date_emb= '"+date_traiter(date)+"'  "
                    + ", id_palette="+id_palette+" where "
                    + " UPPER(parcel)=UPPER('"+parcel+"')";

        }else {

             query = "insert into etiquette_emballage_tablette(parcel,code_article,qte,gw,carton_size,commentaire,date_emb,id_palette) "
                    + "values ('"+parcel+"','"+article+"','"+qte+"','"+gw+"','"+dimension+"','"+commentaire+"','"+date_traiter(date)+"'"
                    + ", "+id_palette+")";

        }
        CConnect.Insert(query);
    }




    public String sn_deja_associer(String sn, String parcel){
        String msg="";
        String query;

        query = "SELECT  parcel_carton from etiquette_tablette where  parcel_carton<>'' and parcel_carton<>'" + parcel + "' and  UPPER(sn)=UPPER('"+sn+"') " ;
        if(CConnect.Requete(query).size()!=0) {

            msg+="Le code " + sn + "  est deja associer a un autre carton veiller verifier le carton numero " + CConnect.Requete(query).get(0)+"\n";

        }

        return msg;

    }



    public ArrayList<String> select_tablette(String filter, Date date_debut, Date date_fin){

        String query5 = "SELECT parcel_palette,etiquette_emballage_tablette.parcel,article.code_article||' '||designation, date_emb,gw,qte,sn, commentaire "
                + " from article,etiquette_emballage_tablette,etiquette_tablette, palette  "
                + " where article.code_article=etiquette_emballage_tablette.code_article "
                + "and etiquette_emballage_tablette.parcel=etiquette_tablette.parcel_carton "
                + "and id_palette=id_pal  "
                +"  and  date_emb BETWEEN '"+date_traiter(date_debut)+"' AND '"+date_traiter(date_fin)+"' and " +
                " (etiquette_emballage_tablette.code_article like  '%"+filter+"%' or sn like  '%"+filter+"%'  " +
                "  or  designation like  '%"+filter+"%' or parcel like  '%"+filter+"%' " +
                "or gw like  '%"+filter+"%' or carton_size like  '%"+filter+"%' or commentaire like  '%"+filter+"%'" +
                "  or parcel_palette like  '%"+filter+"%'   )";

        return CConnect.Requete(query5);

    }

    public void	ajout_emei_tablette(String parcel,String sn, String couleur, Date date, String code_article){

        String query1 = "SELECT   parcel_carton from etiquette_tablette where parcel_carton='' and UPPER(sn)=UPPER('"+sn+"')  " ;
        ArrayList<String> list_tpe_tab=CConnect.Requete(query1);

        if( list_tpe_tab.size()==0) {

            String query5 = "insert into etiquette_tablette(parcel_carton,sn,model,couleur,date_imp) "
                    + "values ('" + parcel + "','" + sn + "','" +code_article+"','" +couleur+"','" +date_traiter(date)+ "')";
            CConnect.Insert(query5);
        }

        else {

            String query5 = "update etiquette_tablette set parcel_carton='" + parcel + "'  "
                    + " where  UPPER(sn)=UPPER('" + sn + "') and parcel_carton='' ";
            CConnect.Insert(query5);
        }

    }




    public void ajouter_chariot(String code,String couleur,Date date){
        String insert = "insert into etiquette_chariot (code,date_chariot,couleur)"
                + " values('"+code+"','"+date_traiter(date)+"','"+couleur+"')";
        CConnect.Insert(insert);
    }







    public String nbr_palette_tablette(String filter,Date datedebut,Date datefin, String champ)
    {
        String resultat="0";
        String insert= "SELECT Count(*) AS N FROM "
                + " (SELECT DISTINCT "+ champ
                + " from palette,article,  etiquette_emballage_tablette as emb, etiquette_tablette "
                + " where id_pal=id_palette and article.code_article = emb.code_article"
                + " and parcel = parcel_carton  "
                +" and  date_emb BETWEEN '"+date_traiter(datedebut)+"' AND '"+date_traiter(datefin)+"' and "
                +"(emb.code_article like  '%"+filter+"%' or sn like  '%"+filter+"%' " +
                "  or  designation like  '%"+filter+"%' or parcel like  '%"+filter+"%' or gw like  '%"+filter+"%' or carton_size like  '%"+filter+"%' or commentaire like  '%"+filter+"%' " +
                "or parcel_palette like  '%"+filter+"%'   ))";

        if(CConnect.Requete(insert).size()>0)
            resultat=CConnect.Requete(insert).get(0);
        return  resultat;
    }


    public ArrayList<String> recherche_chariot(String filter,Date datedebut,Date datefin)
    {
          String  insert = "select distinct code,date_chariot,couleur "
                    + "from etiquette_chariot" +
                  "  where  "
                    + "   date_chariot BETWEEN '"+date_traiter(datedebut)+"'  and '"+date_traiter(datefin)+"'   "
                    + "AND(  code like '%"+filter+"%' or  couleur  like '%"+filter+"%' )";


        return  CConnect.Requete(insert);
    }




    public ArrayList<String> select_etq_tablette(String filter,Date datedebut,Date datefin){
        String query5 = "SELECT article.code_article||' '||designation,couleur,sn, key_licence, date_imp "
                + " from etiquette_tablette ,article "
                + " where article.code_article=etiquette_tablette.model"
               + "  and  date_imp BETWEEN '"+date_traiter(datedebut)+"'  and '"+date_traiter(datefin)+"'   "
                + "AND(  couleur  like '%"+filter+"%' " +
                "  or   designation  like '%"+filter+"%' or etiquette_tablette.model  like '%"+filter+"%'" +
                " or sn  like '%"+filter+"%' or key_licence  like '%"+filter+"%')";
        System.out.println(query5);
        return CConnect.Requete(query5);

    }

}
