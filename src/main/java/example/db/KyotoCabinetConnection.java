/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.db;

import kyotocabinet.DB;

/**
 *
 * @author root
 */
public class KyotoCabinetConnection {
    public static DB getConnection(String dbname, String mode){
        DB db = new DB();
        int dbmode;
        if("r".equals(mode))
            dbmode = DB.OREADER;
        else if("w".equals(mode))
            dbmode = DB.OWRITER | DB.OCREATE;
        else dbmode = DB.OWRITER | DB.OCREATE;
        if (!db.open(dbname, dbmode)) {
            System.err.println("open error: " + db.error());
        }
        return db;
    }
    
    public static void closeConnection(DB db){
        if (!db.close()) {
            System.err.println("close error: " + db.error());
        }
    }
}
