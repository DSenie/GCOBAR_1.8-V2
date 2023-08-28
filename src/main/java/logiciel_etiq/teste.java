package logiciel_etiq;


import logiciel_etiq.Controller.CConnect;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class teste {


    public static void main(String[] args) {
        NumberFormat formatter4 = new DecimalFormat("000000");
        String code="";
        String  prefix="SKT119E202305";

        String missingCodesQuery = "select distinct sn from tout_sn order by sn asc ";
        ArrayList result= CConnect.Requete(missingCodesQuery);
        System.out.println(result);
        int count=0;
        for(int i=1; i<=44531; i++){
        String sufix=formatter4.format(i);
          code=prefix+sufix;

            if (!result.contains(code)) {
                System.out.println("Missing code: " + code);
                 count=count+1;
                System.out.println(count);

                String requette=" insert into sn_perdu (sn) values('"+code+"')";
                CConnect.Requete(requette);
            }

        }

    }

}
