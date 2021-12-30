package logiciel_etiq;

import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import net.ucanaccess.jdbc.JackcessOpenerInterface;

import java.io.File;
import java.io.IOException;

public class CryptCodecOpener implements JackcessOpenerInterface {
    public Database open(File fl,String pwd) throws IOException {
        DatabaseBuilder dbd =new DatabaseBuilder(fl);
        dbd.setAutoSync(false);
        dbd.setCodecProvider(new CryptCodecProvider(pwd));
        dbd.setReadOnly(false);
        return dbd.open();

    }
    //Notice that the parameter setting autosync =true is recommended with UCanAccess for performance reasons.
    //UCanAccess flushes the updates to disk at transaction end.
    //For more details about autosync parameter (and related tradeoff), see the Jackcess documentation.
}