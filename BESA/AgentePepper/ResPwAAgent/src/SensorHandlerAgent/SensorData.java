/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent;

import java.util.Map;
import rational.data.InfoData;

/**
 *
 * @author juans
 */
public class SensorData extends InfoData{
    
    private SensorDataType dataType;
    private String infoReceived=null;
    private Map<String, Object> dataP=null;
    private Map<String, Object> dataPE=null;
    private int ack;

    public SensorData() {
        super(null);
    }

    public SensorDataType getDataType() {
        return dataType;
    }

    public void setDataType(SensorDataType dataType) {
        this.dataType = dataType;
    }

    public Map<String, Object> getDataP() {
        return dataP;
    }

    public void setDataP(Map<String, Object> dataP) {
        this.dataP = dataP;
    }

    public Map<String, Object> getDataPE() {
        return dataPE;
    }

    public void setDataPE(Map<String, Object> dataPE) {
        this.dataPE = dataPE;
    }

    public String getInfoReceived() {
        return infoReceived;
    }

    public void setInfoReceived(String infoReceived) {
        this.infoReceived = infoReceived;
    }

    public int getAck() {
        return ack;
    }

    public void setAck(int ack) {
        this.ack = ack;
    }

    @Override
    public String toString() {
        return "SensorData{" + "dataType=" + dataType + ", infoReceived=" + infoReceived + ", dataP=" + dataP + ", dataPE=" + dataPE + ", ack=" + ack + '}';
    }


    
}
