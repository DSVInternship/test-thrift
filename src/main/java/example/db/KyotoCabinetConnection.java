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
    public static DB getConnection(String dbname){
        DB db = new DB();
        // open the database
        if (!db.open(dbname, DB.OWRITER | DB.OCREATE)) {
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
