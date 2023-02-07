package logiciel_etiq.Controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static logiciel_etiq.View.generale.date_traiter;

public class gestion_imp_tv extends  gestion_general_imp {


    public String afficher_conteur(String code_chaine, Date date_i, int indic)
    {
        String code="001";

        final Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);

        cal.setTime(date_i);
        int jour= cal.get(Calendar.DAY_OF_WEEK);
        String jour1 = formatter1.format(jour);

        int week= cal.get(Calendar.WEEK_OF_YEAR);
        String week1 = formatter2.format(week);

        String[] date_s = date_traiter(date_i).split("-");
        String anne = date_s[0].substring(2);

        String chaine_num="";
        if(!code_chaine.equals(""))
        chaine_num = code_chaine.split(" ")[0].substring(2);
        String  code_globale = "E" + anne + week1 + jour1 + chaine_num+indic;



        String query2 = " SELECT  serial_number FROM etiquette_tv  where  (date_fiche= '"+date_traiter(date_i)+"' "
                + "and substr(serial_number, 8, 1) = '"+indic+"'"
                + "and UPPER(code_chaine)=UPPER('"+code_chaine+"') ) and"+
                " (substr  (serial_number,-3) IN (SELECT   max( substr (serial_number,-3) )"+
                "FROM etiquette_tv where date_fiche= '"+date_traiter(date_i)+"' "
                + "and UPPER(code_chaine)=UPPER('"+code_chaine+"') and substr(serial_number, 8, 1) = '"+indic+"' ) )";


        ArrayList<String> list_affiche_cont=CConnect.Requete(query2);

        if(list_affiche_cont.size()>=1){
            int cz = Integer.parseInt(list_affiche_cont.get(0).substring(5, 6));
            int wek = Integer.parseInt(list_affiche_cont.get(0).substring(3, 5));
            int cont = Integer.parseInt(list_affiche_cont.get(0).substring(8));


            if(cz==jour&&wek==week){
                cont=cont+1;
                code=formatter3.format(cont);
            }

        }
        code_globale=code_globale+""+code;

        return code_globale;
    }




    public void ajouter_imprimer(String codeart,String codec,String sn,String code_com, Date date_p){
        String insert = "insert into etiquette_tv (serial_number,code_article,code_chaine,date_fiche,code_commercial)"
                + " values('"+sn+"','"+codeart+"','"+codec+"', '"+date_traiter(date_p)+"' ,'"+code_com+"')";
        CConnect.Insert(insert);
    }




    public boolean nettoyer_serial(String debut, String fin){
        String select= "select * from etiquette_tv where serial_number BETWEEN '"+debut+"' and '"+fin+"' ";
        ArrayList <String>list_select=CConnect.Requete(select);
        if(list_select.size()!=0) {
            String update = "delete from etiquette_tv where serial_number BETWEEN '" + debut + "' and '" + fin + "' ";
            CConnect.Insert(update);
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null, "L'intervalle des etiquettes est introuvable ");
            return false;
        }

    }



    public ArrayList<String> select(String filter,Date date_d,Date date_f){
        String query5 = " SELECT   etiquette_tv.code_article||' '||designation,  etiquette_tv.code_chaine||' '||designation_chaine, date_fiche, serial_number, code_commercial"
        +" FROM article, Chaine, etiquette_tv "
        +" WHERE  article.code_article=etiquette_tv.code_article   and Chaine.code_chaine=etiquette_tv.code_chaine"
        +" and  date_fiche BETWEEN '"+date_traiter(date_d)+"' AND '"+date_traiter(date_f)+"' and "
                +"(etiquette_tv.code_article like  '%"+filter+"%' or serial_number like  '%"+filter+"%' or code_commercial like  '%"+filter+"%'" +
                "  or  designation like  '%"+filter+"%' or etiquette_tv.code_chaine like  '%"+filter+"%' or  designation_chaine  like  '%"+filter+"%')";


        return CConnect.Requete(query5);
    }



}
