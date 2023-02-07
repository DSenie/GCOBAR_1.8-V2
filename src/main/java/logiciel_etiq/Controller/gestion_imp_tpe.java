package logiciel_etiq.Controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

import static logiciel_etiq.View.generale.date_traiter;
public class gestion_imp_tpe extends  gestion_general_imp {



    public boolean exist_imei_sn_code(String sn,String imei, String code_enie) {

        if(!sn.trim().equals("")){
            String existe_sn="select sn from etiquette_tpe where (UPPER(sn) =UPPER('"+sn+"') or UPPER(imei) =UPPER('"+sn+"')  ) and  UPPER(code_enie)!=UPPER('"+code_enie+"') ";
            if(CConnect.Requete(existe_sn).size()!=0){
                JOptionPane.showMessageDialog(
                        null,
                        "Le SN exist deja" ,
                        "", JOptionPane.ERROR_MESSAGE);
               return true;
            }
        }

        if(!imei.trim().equals("")) {
            String existe_imei = "select imei from etiquette_tpe where (UPPER(imei) =UPPER('" + imei + "') or UPPER(sn) =UPPER('"+imei+"'))  and  UPPER(code_enie)!=UPPER('"+code_enie+"') ";
        if (CConnect.Requete(existe_imei).size() != 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "L'imei exist deja",
                        "", JOptionPane.ERROR_MESSAGE);
               return true;
            }
        }

        return false;
    }



    public ArrayList<String>  selection_tpe_champ(String code ){

        String query = "SELECT code_enie,sn,imei,code_chaine,id_article,date_tpe from etiquette_tpe "
                    + " where  UPPER(imei)=UPPER('"+code+"') or  UPPER(code_enie)=UPPER('" + code + "')";

        if(!code.trim().equals(""))
             query += " or UPPER(sn)=UPPER('"+code+"') ";

        return CConnect.Requete(query);

    }




    public void insert_Tpe(String code_enie,String sn,String imei,String code_chaine,String code_article,Date date)
    {
        String insert = "insert into etiquette_tpe" +
                " (code_enie" +
                ",sn,imei,code_chaine,id_article,date_tpe)"
                + " values('" + code_enie + "'" +
                ",'" + sn + "','" + imei + "','" + code_chaine + "','" + code_article + "','" + date_traiter(date) + "')";
        CConnect.Insert(insert);


    }

    public void update_tpe(String sn,String imei,String code_article, String code_enie)
    {

        String insert = "update etiquette_tpe set sn='" + sn + "', imei='" + imei + "' , id_article='" + code_article + "' "
                + " where UPPER(code_enie)=UPPER('" + code_enie + "')"
                + "";

        System.out.println(insert);
        CConnect.Insert(insert);

    }





    public ArrayList  selection_tpe_emballage(String code){
        String query = "SELECT article.code_article,designation,parcel,gw,carton_size,date_emb,commentaire,qte,parcel_palette "
                +" from etiquette_emballage_tpe,article, palette where UPPER(parcel)=UPPER('"+code+"') "
                + "and  article.code_article=etiquette_emballage_tpe.code_article" +
                " and id_pal=id_palette ";

        return CConnect.Requete(query);


    }



    public ArrayList<String>   selection_tpe_table(String code){


        String query = "SELECT  imei,sn, code_enie "
                + " from etiquette_tpe where UPPER(parcel_carton)=UPPER('"+code+"')";

        return    CConnect.Requete(query);


    }












    public void	update_insert_embalage_tpe(String parcel,String article,String qte ,String gw,String dimension,String commentaire, String id_pal, Date date, String update_insert){
        int id_palette= select_id_palette(id_pal);
        String query;
        if(update_insert.equals("U")) {
             query = "update etiquette_emballage_tpe set code_article='" + article + "'"
                    + ",qte='" + qte + "',gw='" + gw + "',carton_size='" + dimension + "',"
                    + " commentaire='" + commentaire + "'  "
                    + ", id_palette=" + id_palette + " where "
                    + " UPPER(parcel)=UPPER('" + parcel + "')";
        }else {


             query = "insert into etiquette_emballage_tpe(parcel,code_article,qte,gw,carton_size,commentaire,date_emb,id_palette) "
                    + "values ('" + parcel + "','" + article + "','" + qte + "','" + gw + "','" + dimension + "','" + commentaire + "','" + date_traiter(date) + "'"
                    + ", " + id_palette + ")";
        }
        CConnect.Insert(query);
    }



    public String emai_deja_associer(String emei,String code_enie,String sn, String parcel){
        String msg="";
        String query;

        query = "SELECT  parcel_carton from etiquette_tpe where  parcel_carton<>'' and parcel_carton<>'" + parcel + "' and  UPPER(imei)=UPPER('"+emei+"') " ;
        if(CConnect.Requete(query).size()!=0) {
            msg+="Le code " + emei + "  est deja associer a un autre carton veiller verifier le carton numero " + CConnect.Requete(query).get(0)+"\n";
        }
        else {
            query = "SELECT  parcel_carton from etiquette_tpe where  parcel_carton<>'' and parcel_carton<>'" + parcel + "' and  UPPER(code_enie)=UPPER('" + code_enie + "')   and code_enie<>'' ";
            if (CConnect.Requete(query).size() != 0) {
                msg += "Le code Enie " + code_enie + "  est deja associer a un autre carton veiller verifier le carton numero " + CConnect.Requete(query).get(0) + "\n";
            }
            else {
                query = "SELECT  parcel_carton from etiquette_tpe where  parcel_carton<>'' and parcel_carton<>'" + parcel + "' and   UPPER(sn)=UPPER('" + sn + "')  and sn<>''   ";
                if (CConnect.Requete(query).size() != 0) {
                    msg += "Le Serial Number " + sn + "  est deja associer a un autre carton veiller verifier le carton numero " + CConnect.Requete(query).get(0) + "\n";
                }
                else {
                    String existe_enie = "SELECT  imei from etiquette_tpe where (UPPER(code_enie)=UPPER('" + code_enie + "') or  UPPER(imei)=UPPER('" + code_enie + "')" +
                            " or (UPPER(code_enie)=UPPER('" + sn + "') and UPPER(code_enie)<>''  ) ) and  UPPER(imei)<>UPPER('" + emei + "')  ";
                    if (CConnect.Requete(existe_enie).size() != 0) {
                        msg += "Le code " + code_enie + " est deja associer a un autre  imei " + CConnect.Requete(existe_enie).get(0) + "\n";
                    }
                    else {
                        String existe_sn = "SELECT  code_enie from etiquette_tpe where ((UPPER(sn)=UPPER('" + sn + "') and sn<>'' ) or UPPER(imei)=UPPER('" + sn + "') or UPPER(code_enie)=UPPER('" + sn + "')) " +
                                "  and  UPPER(imei)<>UPPER('" + emei + "')   ";
                        if (CConnect.Requete(existe_sn).size() != 0) {
                            msg += "Le code " + sn + " est deja associer a un autre enregistrement ";

                        }
                    }
            }

        }

            }

        return msg;

    }

    public void	ajout_emei_tpe(String parcel,String emei1,String sn, String code_enie, String code_chaine, Date date, String code_article){

        String code=code_enie;
        String query_sn="";
        String query_code="";

        String query1 = "SELECT  code_enie, parcel_carton from etiquette_tpe where parcel_carton='' and ( UPPER(imei)=UPPER('"+emei1+"')  " ;
       if(!code_enie.equals(""))
            query_code = "or UPPER(code_enie)=UPPER('"+code_enie+"')   " ;
        if(!sn.equals(""))
            query_sn = "or UPPER(sn)=UPPER('"+sn+"')   " ;
        query1=query1+query_code+query_sn+")";
        ArrayList<String> list_tpe_tab=CConnect.Requete(query1);
        if( list_tpe_tab.size()==0) {

            if(code_enie.equals("")) {
                code=afficher_conteur(date,code_chaine.substring(2).trim(),"etiquette_tpe","TPF","code_enie","date_tpe");
            }

            String query5 = "insert into etiquette_tpe(parcel_carton,imei,sn,code_enie,date_tpe,code_chaine,id_article) "
                    + "values ('" + parcel + "','" + emei1 + "','" + sn + "','" + code + "','" +date_traiter(date)+ "','" +code_chaine+"' ,'" +code_article+"')";

            CConnect.Insert(query5);

        }

        else {


            String query5 = "update etiquette_tpe set parcel_carton='" + parcel + "', sn='" + sn + "', imei='" + emei1 + "'   "
                        + " where  UPPER(imei)=UPPER('" + emei1 + "')  ";
                query5=query5+query_code+query_sn +"and parcel_carton=''" ;
                CConnect.Insert(query5);

        }

    }


    public ArrayList<String> select_etq_tpe(String filter, Date date_debut, Date date_fin){
        String query5 = " SELECT  etiquette_tpe.id_article||' '||designation,etiquette_tpe.code_chaine||' '||designation_chaine,date_tpe, code_enie, sn, imei  "
                + " from etiquette_tpe,article, Chaine "
                + " where article.code_article=etiquette_tpe.id_article and  Chaine.code_chaine=etiquette_tpe.code_chaine "
                +" and  date_tpe BETWEEN '"+date_traiter(date_debut)+"' AND '"+date_traiter(date_fin)+"' and "
                +"(etiquette_tpe.id_article like  '%"+filter+"%' or sn like  '%"+filter+"%' or code_enie like  '%"+filter+"%' or imei like  '%"+filter+"%'" +
                "  or  designation like  '%"+filter+"%' or etiquette_tpe.code_chaine like  '%"+filter+"%' or  designation_chaine  like  '%"+filter+"%')";

        return CConnect.Requete(query5);

    }





    public ArrayList<String> select_tpe_emballage(String filter, Date date_debut, Date date_fin){

        String query5 = "SELECT parcel_palette,etiquette_emballage_tpe.parcel,article.code_article||' '||designation, date_emb,gw,qte,code_enie,imei,sn, commentaire "
                + " from article,etiquette_emballage_tpe,etiquette_tpe, palette "
                + " where article.code_article=etiquette_emballage_tpe.code_article "
                + "and etiquette_emballage_tpe.parcel=parcel_carton  "
                + "and etiquette_emballage_tpe.id_palette=palette.id_pal  "

                +" and  date_emb BETWEEN '"+date_traiter(date_debut)+"' AND '"+date_traiter(date_fin)+"' and "
                +"(etiquette_emballage_tpe.code_article like  '%"+filter+"%' or sn like  '%"+filter+"%' or code_enie like  '%"+filter+"%' or imei like  '%"+filter+"%'" +
                "  or  designation like  '%"+filter+"%' or parcel like  '%"+filter+"%' or gw like  '%"+filter+"%' or carton_size like  '%"+filter+"%' or commentaire like  '%"+filter+"%' " +
                "or parcel_palette like  '%"+filter+"%'   )";

        return CConnect.Requete(query5);

    }








    public String nbr_palette_tpe(String filter,Date datedebut,Date datefin, String champ)
    {
        String resultat="0";
        String insert= "SELECT Count(*) AS N FROM "
                    + " (SELECT DISTINCT "+ champ
                    + " from palette,article,etiquette_emballage_tpe,etiquette_tpe  "
                    + " where id_pal=id_palette and article.code_article = etiquette_emballage_tpe.code_article"
                    + " and parcel = parcel_carton  "
                     +" and  date_emb BETWEEN '"+date_traiter(datedebut)+"' AND '"+date_traiter(datefin)+"' and "
                +"(etiquette_emballage_tpe.code_article like  '%"+filter+"%' or sn like  '%"+filter+"%' or code_enie like  '%"+filter+"%' or imei like  '%"+filter+"%'" +
                "  or  designation like  '%"+filter+"%' or parcel like  '%"+filter+"%' or gw like  '%"+filter+"%' or carton_size like  '%"+filter+"%' or commentaire like  '%"+filter+"%' " +
                "or parcel_palette like  '%"+filter+"%'   ))";

        if(CConnect.Requete(insert).size()>0)
            resultat=CConnect.Requete(insert).get(0);
        return  resultat;
    }



}
