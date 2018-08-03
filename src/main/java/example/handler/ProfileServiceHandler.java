/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.handler;

import example.db.ProfileDAO;
import example.thrift.Profile;
import example.thrift.ProfileService;
import example.thrift.TGetProfileResult;

import java.io.IOException;
import java.util.List;

import org.apache.thrift.TException;

/**
 *
 * @author root
 */
public class ProfileServiceHandler implements ProfileService.Iface {

    private ProfileDAO dao = new ProfileDAO();

    @Override
    public TGetProfileResult get(long id) throws TException {
        System.out.println("invoke get hanlder");
        try {
            return dao.get(id);
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Profile profile) throws TException {
        try {
            return dao.insert(profile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Profile profile) throws TException {
        try {
            return dao.update(profile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean remove(long id) throws TException {
        try {
            return dao.remove(id);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public TGetProfileResult getAll() throws TException {
        try {
            return dao.getAll();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
