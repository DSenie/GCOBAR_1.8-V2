package logiciel_etiq;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.zip.*;

public class Utilitaire {
   static final int BUFFER = 2048;


   // Zip Fichier
   public static void Zip(String file1)  {
      try {
          String bdd = file1.substring(0, 10);
          String FichierS=bdd+".zip";
         BufferedInputStream origin = null;
         FileOutputStream dest = new
           FileOutputStream(FichierS);
         ZipOutputStream out = new ZipOutputStream(new
           BufferedOutputStream(dest));

         byte data[] = new byte[BUFFER];

            //System.out.println("Adding: "+file1+" "+bdd);
            FileInputStream fi = new
              FileInputStream(file1);
            origin = new
              BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(file1);
            out.putNextEntry(entry);
            int count;
            while((count = origin.read(data, 0,
              BUFFER)) != -1) {
               out.write(data, 0, count);
            }
            origin.close();

         out.close();
      } catch(Exception e) {
         e.printStackTrace();
      }

      return ;
    }

   // UNZip Fichier
   public static void UnZip(String file1)  {
          try {
              BufferedOutputStream dest = null;
              FileInputStream fis = new
            FileInputStream(file1);
              ZipInputStream zis = new
            ZipInputStream(new BufferedInputStream(fis));
              ZipEntry entry;
              while((entry = zis.getNextEntry()) != null) {
                 //System.out.println("Extracting: " +entry);
                 int count;
                 byte data[] = new byte[BUFFER];
                 // write the files to the disk
                 FileOutputStream fos = new
               FileOutputStream(entry.getName());
                 dest = new
                   BufferedOutputStream(fos, BUFFER);
                 while ((count = zis.read(data, 0, BUFFER))
                   != -1) {
                    dest.write(data, 0, count);
                 }
                 dest.flush();
                 dest.close();
              }
              zis.close();
           } catch(Exception e) {
              e.printStackTrace();
           }
   }

   // Lire BDFact0000.txt
    public static String InitBdd()  {
            String Init=null;

            BufferedReader IN=null;
            try{
            IN=new BufferedReader(new FileReader("c:\\GCOBAR\\BDD.txt"));
            } catch (Exception e){
            Erreur(e,1);
            }

            try{
                Init=IN.readLine();
                } catch (Exception e){
                Erreur(e,2);
                }


            try{
                IN.close();
                } catch (Exception e){
                Erreur(e,3);
                }


    return Init;
    }

    private static void Erreur(Exception e, int code){
        //System.err.println("Erreur : "+e);
        System.exit(code);
        }

    public static boolean formatdat(String strdate) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
             df.parse(strdate);
            df.setLenient(false);
            return true;
        } catch (ParseException ex) {
           // Logger.getLogger(DateChecker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
   }
}
	
	

