/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SensorHandlerAgent.Guards;

/**
 *
 * @author juans
 */
public enum SensorDataType {
    ACTIVITY("act"),EMOTIONS("emo"),INTERACTION("int"),ROBOT("rob"),PROFILE("prof");
    private final String identif;
    private SensorDataType(String i)
    {
        identif=i;
    }
    
    public static SensorDataType getFromId(String ident)
    {
        SensorDataType ret=null;
        for (SensorDataType sdt : values()) {
            if(sdt.identif.equals(ident))
            {
                ret=sdt;
                break;
            }
        }
        return ret;
    }
}
