/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;

/**
 *
 * @author juans
 */
public class Adapter extends AdapterBESA{
    
    public Adapter(String eventToSendType, AgHandlerBESA agh) {
        super(eventToSendType, agh);
    }
    
}
