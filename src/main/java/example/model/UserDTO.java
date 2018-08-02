/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.model;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class UserDTO implements Serializable{

    private int a;
    private int b;

    public UserDTO(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "a=" + a + ", b=" + b + '}';
    }
    
}
