/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent;

import java.util.HashMap;
import rational.data.InfoData;

/**
 *
 * @author juans
 */
public class SensorData extends InfoData{
    
    private SensorDataType dataType;
    private byte[] infoReceived=null;
    private HashMap<String, Object> dataP=null;
    private HashMap<String, Object> dataPE=null;

    public SensorData(String message) {
        super(message);
    }

    public SensorDataType getDataType() {
        return dataType;
    }

    public void setDataType(SensorDataType dataType) {
        this.dataType = dataType;
    }

    public byte[] getInfoReceived() {
        return infoReceived;
    }

    public void setInfoReceived(byte[] infoReceived) {
        this.infoReceived = infoReceived;
    }

    public HashMap<String, Object> getDataP() {
        return dataP;
    }

    public void setDataP(HashMap<String, Object> dataP) {
        this.dataP = dataP;
    }

    public HashMap<String, Object> getDataPE() {
        return dataPE;
    }

    public void setDataPE(HashMap<String, Object> dataPE) {
        this.dataPE = dataPE;
    }
    
    
}
