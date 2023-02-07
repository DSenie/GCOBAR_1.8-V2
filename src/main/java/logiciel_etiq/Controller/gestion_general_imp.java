package logiciel_etiq.Controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static logiciel_etiq.View.generale.date_traiter;


public class gestion_general_imp {
     NumberFormat formatter3 = new DecimalFormat("000");
     NumberFormat formatter1 = new DecimalFormat("0");
     NumberFormat formatter2 = new DecimalFormat("00");
     NumberFormat formatter4 = new DecimalFormat("0000");
     private  ArrayList <String>resultat= new ArrayList<>() ;

    public int select_indice(String arti){
        int indi = 0;
        String requet="select indice from article where UPPER(code_article)=UPPER('"+arti+"')";
        if(CConnect.Requete(requet).size()!=0){
            indi=Integer.parseInt(CConnect.Requete(requet).get(0));}
        return indi;
    }

    public ArrayList<String> select_dimension_etq(String indice){
        String query5 = "SELECT distinct dimension from dimension_etq where upper(indice)=UPPER('"+indice+"')";
        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;
    }


    public String select_report(String dimension,String indice)
    {

        String insert = "select nom_report from dimension_etq"
                + " where UPPER(dimension) =UPPER('"+dimension+"') and UPPER(indice)=UPPER('"+indice+"')";
        ArrayList <String>  resultat=CConnect.Requete(insert);
        return  resultat.get(0);
    }






    public ArrayList<String> select_article_code_type(int indice){
        String query5 = "SELECT  distinct article.code_article,designation FROM article "
                + " where  indice="+indice;

        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;
    }





    public ArrayList<String> select_size(String table){
        String query5 = "SELECT distinct carton_size from "+table;
        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }



    public ArrayList<String> select_gw(String table){
        String query5 = "SELECT distinct gw from "+table;

        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }

    public ArrayList<String> select_nw(String table){
        String query5 = "SELECT distinct nw from "+table;

        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;
    }


    int  select_id_palette(String palette){
        String insert = "select id_pal from palette where  UPPER(parcel_palette)=UPPER('"+palette+"')";
        return Integer.parseInt(CConnect.Requete(insert).get(0));
    }


    public  ArrayList<String>   select_palette(String prefix){
        String insert = "select parcel_palette from palette where parcel_palette like '"+prefix+"%' ";
        return CConnect.Requete(insert);

    }


    public  String   select_count_palette(String table,String palette){
        String code="0";
        String insert = "select COUNT(*) from "+table+",palette where id_pal=id_palette  and  UPPER(parcel_palette)=UPPER('"+palette+"')";
        resultat=CConnect.Requete(insert);
        if(resultat.size()!=0){
            code=resultat.get(0);
        }
        return code;
    }




    private String afficher_conteur(Date date_i, String chaine, String prefix)
    {

        final Calendar cal = Calendar.getInstance();
        cal.setTime(date_i);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        int jour= cal.get(Calendar.DAY_OF_WEEK);
        int week= cal.get(Calendar.WEEK_OF_YEAR);

        String[] date_s = date_traiter(date_i).split("-");
        String anne = date_s[0].substring(2);

        String week1 = formatter2.format(week);
        String jour1 = formatter1.format(jour);

        return prefix + chaine+ jour1 + week1 + anne;
    }


    private String codification_generale(Date date_i, String chaine, String prefix, String query2)
    {

        String code="0001";
        int count;

        String  code_globale = afficher_conteur(date_i, chaine, prefix);
        ArrayList list_affiche_cont2=CConnect.Requete(query2);

        if(list_affiche_cont2.get(0)!=null){
            count=Integer.parseInt(list_affiche_cont2.get(0).toString().substring(list_affiche_cont2.get(0).toString().length()-4));
            count=count+1;
            code=formatter4.format(count);
        }
        code_globale=code_globale+""+code;

        return code_globale;

    }



    public String select_model(String article){
        String model="";
        String query5 = "SELECT model from article where UPPER(code_article)=UPPER('"+article+"')";
        if(CConnect.Requete(query5).size()>=1){
            model=CConnect.Requete(query5).get(0);
        }
        return model;

    }




    public ArrayList<String> listCarton(String table, String palette){
        int id;
        String select = "select id_pal from  palette where UPPER(parcel_palette)=UPPER('"+palette+"')";
        if(CConnect.Requete(select).size()!=0) {
            id = Integer.parseInt(CConnect.Requete(select).get(0));

            String insert = "select count(parcel) from  " + table + " where id_palette=" + id;
            resultat = CConnect.Requete(insert);

        }
        return resultat;
    }




    public void supprimer_carton (String table_p, String table_s, String parcel) {

        String delete_p = "delete from " + table_p + " where UPPER(parcel)= UPPER('" + parcel + "')";
        CConnect.Insert(delete_p);


        String delete_d=" update " + table_s + " set parcel_carton='' where UPPER(parcel_carton)= UPPER('" + parcel + "') ";
        CConnect.Insert(delete_d);

    }



    public void	delete_code(String parcel,String table){
        String delete = "update  "+table+" set parcel_carton=''"
                + " where UPPER(parcel_carton)=UPPER('"+parcel+"')";
        CConnect.Insert(delete);
    }







    public ArrayList<String> select_color(String table){
        ArrayList <String>resultat= new ArrayList<>() ;
        String query5 = "SELECT distinct couleur from "+table;
        if(CConnect.Requete(query5).size()>=1){
            resultat=CConnect.Requete(query5);
        }
        return resultat;

    }



    public String afficher_conteur_palette(String prefix,String chaine)
    {

        String  code_globale = afficher_conteur(Calendar.getInstance().getTime(), chaine, prefix);
        String query2 = " SELECT max(parcel_palette) FROM palette  where parcel_palette like '"+code_globale+"%' ";
        String code=codification_generale(Calendar.getInstance().getTime(), chaine, prefix,query2);

        String query3 = " insert into palette (parcel_palette) values('"+code+"') ";
        CConnect.Insert(query3);
        return code;
    }


    public String afficher_conteur(Date date_i, String chaine, String table, String prefix, String codeSelect, String date)
    {
        int position_number=prefix.length()+1;
        String query2 = " SELECT Max("+codeSelect+") FROM "+table+"  where "+date+"= '"+date_traiter(date_i)+"'  " +
                "and  substr("+codeSelect+","+position_number+",1)= '"+chaine+"' " ;
        return codification_generale(date_i, chaine, prefix,query2);
    }


    public boolean exist_emballage(String parcel, String table){
        String select = "SELECT * from " +table+
                " where UPPER(parcel)=UPPER('"+parcel+"')";
        return CConnect.Requete(select).size() >= 1;
    }


}
