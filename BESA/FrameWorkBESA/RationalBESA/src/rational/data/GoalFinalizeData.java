/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.data;

/**
 *
 * @author fjroldan
 */
public class GoalFinalizeData extends InfoData {

    private String command;

    public GoalFinalizeData(String command) {
        super(command);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
