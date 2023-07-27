package logiciel_etiq;


import logiciel_etiq.Controller.CConnect;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class teste {


    public static void main(String[] args) {
        NumberFormat formatter4 = new DecimalFormat("000000");
        String code="";
        String  prefix="SKT119E202305";
        for(int i=23404; i<=47520; i++){
        String sufix=formatter4.format(i);
          code=prefix+sufix;



           String requette=" insert into etiquette_tablette (sn,model,couleur,date_imp) values('"+code+"','9513258','Black','2023-07-25')";
           CConnect.Requete(requette);
            System.out.println(code);


        }

        System.out.println(code);

    }

}
