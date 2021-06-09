/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.Utils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author juans
 */

@JsonPropertyOrder({ "id", "proxyName","methodName", "params" })
public class PepperSendable implements Serializable{
    
    
    private int id; 
    private String proxyName;
    private String methodName;
    private HashMap<String,Object> params;

    public PepperSendable( int id ,String proxyName ,String methodName, HashMap<String, Object> params) {
        this.proxyName=proxyName;
        this.methodName = methodName;
        this.id = id;
        this.params = params;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String,Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String,Object> params) {
        this.params = params;
    }

    public String getProxyName() {
        return proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }
    
    
    
    
}
