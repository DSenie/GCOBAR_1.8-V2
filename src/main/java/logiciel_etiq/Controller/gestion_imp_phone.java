package logiciel_etiq.Controller;


import java.util.ArrayList;
import java.util.Date;

import static logiciel_etiq.View.generale.date_traiter;

public class gestion_imp_phone extends gestion_general_imp {

    public  ArrayList <String> rech_imprim_fourn(String imei){
        ArrayList <String>resultat= new ArrayList<>() ;
        String query5 = "SELECT model, couleur, imei1,imei2,date_imp FROM etiquette_portable "
                + "where  imei1='"+imei+"' or imei2='"+imei+"' ";

        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }


    public boolean exist_phone_fournisseur(String imei1,String imei2) {
        String query5 = "SELECT * FROM etiquette_portable "
                + "where  imei1='" + imei1 + "' or imei2='" + imei2 + "' or imei1='" + imei2 + "' or imei2='" + imei1 + "'   ";
        boolean exist=false;

        if (CConnect.Requete(query5).size() >= 1) {

            exist=true;
        }

        return exist;
    }


    public void insert_phone_fournisseur(String model, String couleur, String imei1, String imei2, Date date)
    {

        String insert = "insert into etiquette_portable (model,couleur,imei1,imei2,date_imp)"
                + " values('" + model + "','" + couleur + "','" + imei1 + "','" + imei2 + "', '" + date_traiter(date) + "' )";
        CConnect.Insert(insert);
    }


    public void delete_phone_fournisseur(String  imei1, String imei2 ){
        String delete= "delete from etiquette_portable where imei1='"+imei1+"' or  imei1='"+imei2+"' or imei2='"+imei1+"' or  imei2='"+imei2+"' ";

        CConnect.Insert(delete);

    }







    public String imei_deja_associer(String imei1,String imei2, String parcel){
        String msg="";
        String query;

        query = "SELECT  parcel_carton from etiquette_portable where  parcel_carton<>'' and parcel_carton<>'" + parcel + "' " +
                " and  UPPER(imei1)=UPPER('"+imei1+"') " ;
        if(CConnect.Requete(query).size()!=0) {
            msg+="Le code " +  imei1 +" est deja associer a un autre carton veiller verifier le carton numero " + CConnect.Requete(query).get(0)+"\n";
        }

        query = "SELECT  parcel_carton from etiquette_portable where  parcel_carton<>'' and parcel_carton<>'" + parcel + "' " +
                " and    UPPER(imei2)=UPPER('"+imei2+"') " ;
        if(CConnect.Requete(query).size()!=0) {
            msg+="Le code " +  imei2+" est deja associer a un autre carton veiller verifier le carton numero " + CConnect.Requete(query).get(0)+"\n";
        }

        return msg;

    }

    public void	ajout_emei_portable(String parcel,String imei1,String imei2, String couleur, Date date, String code_article){

        String query1 = "SELECT  parcel_carton from etiquette_portable where parcel_carton='' and " +
                " (UPPER(imei1)=UPPER('"+imei1+"') or UPPER(imei2)=UPPER('"+imei2+"') )  " ;

        ArrayList<String> list_tab=CConnect.Requete(query1);

        if( list_tab.size()==0) {
            String query5 = "insert into etiquette_portable(parcel_carton,imei1,imei2,couleur,date_imp,model) "
                    + "values ('"+parcel+"','"+imei1+"','"+imei2+"','" +couleur+"','" +date_traiter(date)+ "','" +code_article+"')";
            CConnect.Insert(query5);

        }

        else {

            String query5 = "update etiquette_portable set parcel_carton='" + parcel + "' , imei1='" + imei1 + "' , imei2='" + imei2 + "' "
                    + " where  (UPPER(imei1)=UPPER('" + imei1 + "') or UPPER(imei2)=UPPER('" + imei2+"'))" +
                    " and parcel_carton='' ";
            CConnect.Insert(query5);

        }

    }



    public void	update_insert_embalage_tablette(String parcel,String article,String couleur,String qte
            ,String delivery,String gw,String nw,String dimension,String commentaire,Date date, String id_pal, String update_insert){
        int id_palette= select_id_palette(id_pal);
        String query;
        if(update_insert.equals("U")) {

            query = "update etiquette_emballage_portable set code_article='"+article+"',couleur='"+couleur+"'"
                    + ",qte='"+qte+"',delivery='"+delivery+"',gw='"+gw+"',carton_size='"+dimension+"',"
                    + "nw='"+nw+"' , commentaire='"+commentaire+"',date_emb= '"+date_traiter(date)+"'"
                    + " , id_palette="+id_palette+" where "
                    + " UPPER(parcel)=UPPER('"+parcel+"')";

        }else {

            query = "insert into etiquette_emballage_portable(parcel,code_article,couleur,qte,delivery,gw,carton_size,nw,commentaire,date_emb,id_palette) "
                    + "values ('"+parcel+"','"+article+"','"+couleur+"','"+qte+"','"+delivery+"','"+gw+"','"+dimension+"','"+nw+"','"+commentaire+"','"+date_traiter(date)+"' ,"
                    + ""+id_palette+")";


        }
        CConnect.Insert(query);
    }




    public ArrayList<String> select_parcel_portable(String parcel){

        String query2 = "SELECT article.code_article,designation,model,couleur,qte,nw,gw,delivery,carton_size, date_emb,parcel_palette FROM "
                + "etiquette_emballage_portable,article,palette where  UPPER(parcel)=UPPER('"+parcel+"')" +
                "  and article.code_article=etiquette_emballage_portable.code_article and id_pal = id_palette"
                ;
            return CConnect.Requete(query2);

    }



    public ArrayList<String> select_imei(String parcel){
        ArrayList <String>resultat= new ArrayList<>() ;
        String query5 = "SELECT imei1,imei2 from etiquette_portable where UPPER(parcel_carton)=UPPER('"+parcel+"') ";
        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }





    public  ArrayList<String>  select_portable(String filter, Date date_debut, Date date_fin) {



        String query5 = "SELECT parcel,parcel_palette,etiquette_emballage_portable.code_article||' '||designation,etiquette_portable.model,etiquette_emballage_portable.couleur,delivery,gw,nw,carton_size,commentaire,date_emb," +
                "                 imei1,imei2 "
                + " from article,etiquette_emballage_portable,etiquette_portable, palette  "
                + " where article.code_article=etiquette_emballage_portable.code_article "
                + "and parcel=parcel_carton "
                + "and id_palette=id_pal  "
                +"  and  date_emb BETWEEN '"+date_traiter(date_debut)+"' AND '"+date_traiter(date_fin)+"' and " +
                " ( parcel like '%"+filter+"%' or parcel_palette like '%"+filter+"%' " +
                "or etiquette_emballage_portable.code_article like  '%"+filter+"%'  " +
                "  or  designation like  '%"+filter+"%' " +
                " or gw like  '%"+filter+"%' or nw like  '%"+filter+"%'  or " +
                " carton_size like  '%"+filter+"%' or commentaire like  '%"+filter+"%'" +
                "  or etiquette_emballage_portable.couleur like  '%"+filter+"%'   )";


        return CConnect.Requete(query5);

    }






    public String nbr_palette_portable(String filter,Date datedebut,Date datefin, String champ)
    {
        String resultat="0";
        String insert= "SELECT Count(*) AS N FROM "
                + " (SELECT DISTINCT "+ champ
                + " from palette,article,  etiquette_emballage_portable as emb, etiquette_portable "
                + " where id_pal=id_palette and article.code_article = emb.code_article"
                + " and parcel = parcel_carton  "
                +"  and  date_emb BETWEEN '"+date_traiter(datedebut)+"' AND '"+date_traiter(datefin)+"' and " +
                " ( parcel like '%"+filter+"%' or parcel_palette like '%"+filter+"%' " +
                "or emb.code_article like  '%"+filter+"%'  " +
                "  or  designation like  '%"+filter+"%' " +
                " or gw like  '%"+filter+"%' or nw like  '%"+filter+"%'  or " +
                " carton_size like  '%"+filter+"%' or commentaire like  '%"+filter+"%'" +
                "  or emb.couleur like  '%"+filter+"%'   ))";

        if(CConnect.Requete(insert).size()>0)
            resultat=CConnect.Requete(insert).get(0);
        return  resultat;
    }




    public  ArrayList <String> select_etq_portable(String filter,Date datedebut,Date datefin){

        String query5 = "SELECT code_article||' '||designation, couleur, imei1,imei2,date_imp FROM etiquette_portable, article" +
                " where code_article = etiquette_portable.model and " +
                " date_imp BETWEEN '"+date_traiter(datedebut)+"' AND '"+date_traiter(datefin)+"' and  "
                + " ( code_article like '%"+filter+"%' or designation like '%"+filter+"%' " +
                "or couleur like  '%"+filter+"%'  " +
                "  or  imei1 like  '%"+filter+"%' " +
                " or imei2 like  '%"+filter+"%' )"   ;



        return CConnect.Requete(query5);


    }



}



