/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.tasks;

import java.util.logging.Level;
import java.util.logging.Logger;
import rational.mapping.Believes;
import rational.mapping.Task;

/**
 *
 * @author Andres
 */
public class VoidTask extends Task{

    @Override
    public void executeTask(Believes parameters) {
        this.setTaskFinalized();
    }

    @Override
    public void interruptTask(Believes believes) {}

    @Override
    public void cancelTask(Believes believes) {}

    @Override
    public boolean checkFinish(Believes believes) {
        return true;
    }
    
}
