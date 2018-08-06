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
import example.thrift.ErrorType;
import example.thrift.Profile;
import example.thrift.TGetProfileResult;
import example.util.ByteConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kyotocabinet.*;
import org.apache.thrift.TException;

public class ProfileDAO {

    public boolean insert(Profile profile) throws IOException {
        //System.out.println("profile insert " + profile);
        DB db = KyotoCabinetConnection.getConnection("profile.kch");
        db.set(ByteConverter.convertToBytes(profile.getId()), ByteConverter.convertToBytes(profile));
        KyotoCabinetConnection.closeConnection(db);
        return true;
    }

    public TGetProfileResult get(long id) throws IOException, ClassNotFoundException {
        //System.out.println("Profile get " + id);
        DB db = KyotoCabinetConnection.getConnection("profile.kch");
        byte[] byteProfile = db.get(ByteConverter.convertToBytes(id));
        TGetProfileResult result ;
        if(byteProfile == null)
        	return new TGetProfileResult(ErrorType.NOT_FOUND.getValue());
        Profile profile = (Profile) ByteConverter.convertFromBytes(byteProfile);
        KyotoCabinetConnection.closeConnection(db);
        result = new TGetProfileResult();
        result.setErr(ErrorType.SUCCESS.getValue());
        result.setProfile(Arrays.asList(profile));
        
        return result;
    }

    public boolean update(Profile profile) throws TException, IOException {
       // System.err.println("profile update " + profile);
        DB db = KyotoCabinetConnection.getConnection("profile.kch");
        boolean result = db.replace(ByteConverter.convertToBytes(profile.getId()), ByteConverter.convertToBytes(profile));
        KyotoCabinetConnection.closeConnection(db);
        return result;
    }

    public boolean remove(long id) throws TException, IOException {
       // System.out.println("profile remove");
        DB db = KyotoCabinetConnection.getConnection("profile.kch");
        boolean result = db.remove(ByteConverter.convertToBytes(id));
        KyotoCabinetConnection.closeConnection(db);
        return result;
    }

    public TGetProfileResult getAll() throws IOException, ClassNotFoundException {
        List<Profile> profiles = new ArrayList<>();
        Profile tmp;
        DB db = KyotoCabinetConnection.getConnection("profile.kch");
        Cursor cur = db.cursor();
        cur.jump();
        
        byte[][] recByte;
        
        while ((recByte = cur.get(true)) != null) {
        	tmp = (Profile)ByteConverter.convertFromBytes(recByte[1]);
        	profiles.add(tmp);
            //System.out.println((ByteConverter.convertFromBytes(recByte[0])) + ":" + ByteConverter.convertFromBytes(recByte[1]));
        }
        cur.disable();
        KyotoCabinetConnection.closeConnection(db);
        
        TGetProfileResult result = new TGetProfileResult();
        result.setErr(ErrorType.SUCCESS.getValue());
        result.setProfile(profiles);
        return result;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, TException {
        ProfileDAO dao = new ProfileDAO();
        Profile dto1 = new Profile(1, "Ac", 1);
        Profile dto2 = new Profile(2, "a", 2);
        Profile dto3 = new Profile(3, "b", 3);
        Profile dto4 = new Profile(4, "d", 4);
        Profile dto5 = new Profile(5, "a", 5);
        
        dao.insert(dto1);
        dao.insert(dto2);
        dao.insert(dto3);
        dao.insert(dto4);
        dao.insert(dto5);
        
        System.out.println("get id 1: " + dao.get(1l));
        System.out.println("get id 2: " + dao.get(2l));
        System.out.println("\nget all\n");
        for(Profile xProfile: dao.getAll().getProfile()){
            System.out.println(xProfile);
        };
        
        // delete 
        dao.remove(5l);
        dao.remove(3l);
        
        // update 
        dto1.setAge(200);
        dao.update(dto1);
         System.out.println("\nget all after remove and update\n");
        for(Profile xProfile: dao.getAll().getProfile()){
            System.out.println(xProfile);
        };
    }
}
