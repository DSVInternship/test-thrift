/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.db;

/**
 *
 * @author root
 */
import example.model.UserDTO;
import example.util.ByteConverter;
import java.io.IOException;
import kyotocabinet.*;

public class KCDBEX1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // create the object
        DB db = new DB();

        // open the database
        if (!db.open("casket.kch", DB.OWRITER | DB.OCREATE)) {
            System.err.println("open error: " + db.error());
        }

        UserDTO dto1 = new UserDTO(1, 2);
        UserDTO dto2 = new UserDTO(2, 2);
        // store records
//        if (!db.set("foo", "hop")
//                || !db.set("bar", "step")
//                || !db.set("baz", "jump")) {
//            System.err.println("set error: " + db.error());
//        }

        if (!db.set("1".getBytes(), ByteConverter.convertToBytes(dto1))
                || !db.set("2".getBytes(), ByteConverter.convertToBytes(dto2))) {
            System.err.println("set error: " + db.error());

        };

        // retrieve records
        String value = db.get("foo");
        if (value != null) {
            System.out.println(value);
        } else {
            System.err.println("set error: " + db.error());
        }
        
        UserDTO vDTO1 = (UserDTO)ByteConverter.convertFromBytes(db.get("2".getBytes()));
        if (vDTO1 != null) {
            System.out.println(vDTO1);
        } else {
            System.err.println("set error: " + db.error());
        }

        // traverse records
        Cursor cur = db.cursor();
        cur.jump();
//        String[] rec;
//        while ((rec = cur.get_str(true)) != null) {
//            System.out.println(rec[0] + ":" + rec[1]);
//        }
        
        byte[][] recByte;
        while((recByte = cur.get(true)) != null){
            System.out.println(new String(recByte[0]) + ":" + ByteConverter.convertFromBytes(recByte[1]));
        }
        cur.disable();
        
        // close the database
        if (!db.close()) {
            System.err.println("close error: " + db.error());
        }

    }
}
