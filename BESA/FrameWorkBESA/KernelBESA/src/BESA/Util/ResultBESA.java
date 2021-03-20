/*//TODO Hacer Ec¿xception.
 * @(#)ResultBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ResultBESA {

    /**
     *
     */
    private int numAgentes;
    /**
     * 
     */
    private int protocolCounter = 0;
    /**
     * 
     */
    private int goals = 0;
    /**
     * 
     */
    private int microsocietyTries = 0;
    /**
     * 
     */
    private int microsocietyCreated = 0;
    /**
     * 
     */
    private int successMicrosociety = 0;
    /**
     * 
     */
    private int passTries = 0;
    /**
     * 
     */
    private int passCreated = 0;
    /**
     * 
     */
    private int successPass = 0;
    /**
     * 
     */
    private int midPassTries = 0;
    /**
     * 
     */
    private int midPassCreated = 0;
    /**
     * 
     */
    private int successMidPass = 0;
    /**
     * 
     */
    private int defenseTries = 0;
    /**
     * 
     */
    private int defenseCreated = 0;
    /**
     * 
     */
    private int successDefense = 0;
    /**
     * 
     */
    private int ofPlayerTries = 0;
    /**
     * 
     */
    private int ofPlayerCreated = 0;
    /**
     * 
     */
    private int successOfPlayer = 0;
    /**
     * 
     */
    private int defPlayerTries = 0;
    /**
     * 
     */
    private int defPlayerCreated = 0;
    /**
     * 
     */
    private int successDefPlayer = 0;
    /**
     * 
     */
    private int kickAgentTries = 0;
    /**
     * 
     */
    private int kickAgentCreated = 0;
    /**
     * 
     */
    private int successKickAgent = 0;
    /**
     * 
     */
    private double passMatchingSum = 0;
    /**
     * 
     */
    private double midPassMatchingSum = 0;
    /**
     * 
     */
    private double defenseMatchingSum = 0;
    /**
     * 
     */
    private double ofPlayerMatchingSum = 0;
    /**
     * 
     */
    private double defMatchingSum = 0;
    /**
     * 
     */
    private double kickAgentMatchingSum = 0;
    /**
     * 
     */
    private double playedTime;
    /**
     * 
     */
    private int structuralDefense = 0;
    /**
     * 
     */
    private int structuralOffensive = 0;
    /**
     * 
     */
    private int internalNegotiation = 0;
    /**
     * 
     */
    private int externalNegotiation = 0;
    /**
     * 
     */
    private int successinternalNegotiation = 0;
    /**
     * 
     */
    private int successexternalNegotiation = 0;
    /**
     * 
     */
    public static ResultBESA instance;

    /**
     *
     */
    private ResultBESA() {
        playedTime = System.currentTimeMillis();
    }

    /**
     *
     * @return
     */
    public static ResultBESA createInstance() {
        return new ResultBESA();

    }

    /**
     *
     * @return
     */
    public static ResultBESA getInstance() {

        if (instance == null) {
            instance = new ResultBESA();
        }
        return instance;
    }

    /**
     * @return Returns the protocolCounter.
     */
    public int getProtocolCounter() {
        return protocolCounter;
    }

    /**
     *
     */
    public void increaseCounter() {
        this.protocolCounter++;
    }

    /**
     *
     * @param fileName
     */
    public void save(String fileName) {

        ////******ARCHIVO DONDE SE ALMACENA EL RESULTADO DE LAS PRUEBAS**********///////
        //int numArch=1;
        String arch = fileName + "39.csv";

        try {
            FileWriter xw = new FileWriter(arch);
            BufferedWriter bw = new BufferedWriter(xw);
            PrintWriter salida = new PrintWriter(bw);


            salida.println(getMicrosocietyTries() + "|" + getMicrosocietyCreated() + "|" + getSuccessMicrosociety() + "|" +
                    getPassTries() + "|" + getPassCreated() + "|" + getSuccessPass() + "|" +
                    getMidPassTries() + "|" + getMidPassCreated() + "|" + getSuccessMidPass() + "|" +
                    getDefenseTries() + "|" + getDefenseCreated() + "|" + getSuccessDefense() + "|" +
                    getOfPlayerTries() + "|" + getOfPlayerCreated() + "|" + getSuccessOfPlayer() + "|" +
                    getGoals() + "|" + getPassMatchingSum() + "|" + getMidPassMatchingSum() + "|" +
                    getDefenseMatchingSum() + "|" + getOfPlayerMatchingSum() + "|" + getDefenseMatchingSum() + "|" + (System.currentTimeMillis() - this.playedTime));

            salida.println("Goles;" + getGoals());

            salida.println("Intentos de Microsociedades;" + getMicrosocietyTries());
            salida.println("Microsociedades creadas;" + getMicrosocietyCreated());
            salida.println("Microsociedades exitosas;" + getSuccessMicrosociety());

            salida.println("Pases evaluados;" + getPassTries());
            salida.println("Pases;" + getPassCreated());
            salida.println("Pases exitosos;" + getSuccessPass());

            salida.println("Centros evaluados;" + getMidPassTries());
            salida.println("Centros;" + getMidPassCreated());
            salida.println("Centros exitosos;" + getSuccessMidPass());

            salida.println("Defensas evaluadas;" + getDefenseTries());
            salida.println("Defensas: " + getDefenseCreated());
            salida.println("Defensas exitosas;" + getSuccessDefense());

            salida.println("Monoagente evaluadas;" + getOfPlayerTries());
            salida.println("Monoagente creadas;" + getOfPlayerCreated());
            salida.println("Monoagente exitosas;" + getSuccessOfPlayer());

            salida.println("Defensive Player evaluados;" + getDefPlayerTries());
            salida.println("Defensive Player;" + getDefPlayerCreated());
            salida.println("Defensive Player exitosos;" + getSuccessDefPlayer());

            salida.println("Kicker evaluados;" + getKickAgentTries());
            salida.println("Kicker;" + getKickAgentCreated());
            salida.println("Kicker exitosos;" + getSuccessKickAgent());

            salida.println("Matching Pase;" + getPassMatchingSum());
            salida.println("Matching Centro;" + getMidPassMatchingSum());
            salida.println("Matching Defensa;" + getDefenseMatchingSum());
            salida.println("Matching Monoagente;" + getOfPlayerMatchingSum());
            salida.println("Matching Total;" + getDefenseMatchingSum());
            salida.println("Matching Kicker;" + getKickAgentMatchingSum());

            salida.println("Tiempo Jugado (milis);" + (System.currentTimeMillis() - this.playedTime));
            salida.println(" --- STRUCTURAL VARIABLES --- ");
            salida.println("structural Defense;" + getStructuralDefense());
            salida.println("structural Offensive;" + getStructuralOffensive());
            salida.println("internal Negotiation;" + getinternalNegotiation());
            salida.println("external Negotiation;" + getexternalNegotiation());
            salida.println("success internal Negotiation;" + getsuccessinternalNegotiation());
            salida.println("success external Negotiation;" + getsuccessexternalNegotiation());

            salida.flush();
            salida.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //////////////*********HASTA AQUI**************///////////
    }

    /**
     * @return
     */
    private int getsuccessexternalNegotiation() {
        // TODO Auto-generated method stub
        return this.successexternalNegotiation;
    }

    /**
     *
     */
    public void increaseSuccessExternalNegotiation() {
        this.successexternalNegotiation++;
    }

    /**
     *
     * @return
     */
    private int getsuccessinternalNegotiation() {
        // TODO Auto-generated method stub
        return this.successinternalNegotiation;
    }

    /**
     *
     */
    public void increaseSuccessInternalNegotiation() {
        this.successinternalNegotiation++;
    }

    /**
     *
     * @return
     */
    private int getexternalNegotiation() {
        // TODO Auto-generated method stub
        return this.externalNegotiation;
    }

    /**
     *
     */
    public void increaseExternalNegotiation() {
        this.externalNegotiation++;
    }

    /**
     *
     * @return
     */
    private int getinternalNegotiation() {
        // TODO Auto-generated method stub
        return this.internalNegotiation;
    }

    /**
     *
     */
    public void increaseInternalNegotiation() {
        this.internalNegotiation++;
    }

    /**
     *
     * @return
     */
    private int getStructuralOffensive() {
        // TODO Auto-generated method stub
        return this.structuralOffensive;
    }

    /**
     *
     */
    public void increaseStructuralOffensive() {
        this.structuralOffensive++;
    }

    /**
     *
     * @return
     */
    private int getStructuralDefense() {
        // TODO Auto-generated method stub
        return this.structuralDefense;
    }

    /**
     *
     */
    public void increaseStructuralDefense() {
        this.structuralDefense++;
    }

    /**
     * @return Returns the numAgentes.
     */
    public int getNumAgentes() {
        return numAgentes;
    }

    /**
     * @param numAgentes The numAgentes to set.
     */
    public void setNumAgentes(int numAgentes) {
        this.numAgentes = numAgentes;
    }

    /**
     * @return Returns the defenseCreated.
     */
    public int getDefenseCreated() {
        return defenseCreated;
    }

    /**
     *
     */
    public void increaseDefenseCreated() {
        this.defenseCreated++;
    }

    /**
     * @return Returns the defenseMatchingSum.
     */
    public double getDefenseMatchingSum() {
        return defenseMatchingSum;
    }

    /**
     *
     * @param matching
     */
    public void increaseDefenseMatchingSum(double matching) {
        this.defenseMatchingSum += matching;
    }

    /**
     * @return Returns the defenseTries.
     */
    public int getDefenseTries() {
        return defenseTries;
    }

    /**
     *
     */
    public void increaseDefenseTries() {
        this.defenseTries++;
    }

    /**
     * @return Returns the defMatchingSum.
     */
    public double getDefMatchingSum() {
        return defMatchingSum;
    }

    /**
     *
     * @param matching
     */
    public void increaseDefMatchingSum(double matching) {
        this.defMatchingSum += matching;
    }

    /**
     * @return Returns the defPlayerCreated.
     */
    public int getDefPlayerCreated() {
        return defPlayerCreated;
    }

    /**
     *
     */
    public void increaseDefPlayerCreated() {
        this.defPlayerCreated++;
    }

    /**
     * @return Returns the defPlayerTries.
     */
    public int getDefPlayerTries() {
        return defPlayerTries;
    }

    /**
     *
     */
    public void increaseDefPlayerTries() {
        this.defPlayerTries += this.defPlayerTries;
    }

    /**
     * @return Returns the goals.
     */
    public int getGoals() {
        return goals;
    }

    /**
     *
     */
    public void increaseGoals() {
        this.goals++;
    }

    /**
     * @return Returns the kickAgentCreated.
     */
    public int getKickAgentCreated() {
        return kickAgentCreated;
    }

    /**
     *
     */
    public void increaseKickAgentCreated() {
        this.kickAgentCreated++;
    }

    /**
     * @return Returns the kickAgentMatchingSum.
     */
    public double getKickAgentMatchingSum() {
        return kickAgentMatchingSum;
    }

    /**
     *
     * @param matching
     */
    public void increaseKickAgentMatchingSum(double matching) {
        this.kickAgentMatchingSum += matching;
    }

    /**
     * @return Returns the kickAgentTries.
     */
    public int getKickAgentTries() {
        return kickAgentTries;
    }

    /**
     *
     */
    public void increaseKickAgentTries() {
        this.kickAgentTries++;
    }

    /**
     * @return Returns the microsocietyCreated.
     */
    public int getMicrosocietyCreated() {
        return microsocietyCreated;
    }

    /**
     *
     */
    public void increaseMicrosocietyCreated() {
        this.microsocietyCreated++;
    }

    /**
     * @return Returns the microsocietyTries.
     */
    public int getMicrosocietyTries() {
        return microsocietyTries;
    }

    /**
     *
     */
    public void increaseMicrosocietyTries() {
        this.microsocietyTries++;
    }

    /**
     * @return Returns the midPassCreated.
     */
    public int getMidPassCreated() {
        return midPassCreated;
    }

    /**
     *
     */
    public void increaseMidPassCreated() {
        this.midPassCreated++;
    }

    /**
     * @return Returns the midPassMatchingSum.
     */
    public double getMidPassMatchingSum() {
        return midPassMatchingSum;
    }

    /**
     *
     * @param matching
     */
    public void increaseMidPassMatchingSum(double matching) {
        this.midPassMatchingSum += matching;
    }

    /**
     * @return Returns the midPassTries.
     */
    public int getMidPassTries() {
        return midPassTries;
    }

    /**
     *
     */
    public void increaseMidPassTries() {
        this.midPassTries++;
    }

    /**
     * @return Returns the ofPlayerCreated.
     */
    public int getOfPlayerCreated() {
        return ofPlayerCreated;
    }

    /**
     *
     */
    public void increaseOfPlayerCreated() {
        this.ofPlayerCreated++;
    }

    /**
     * @return Returns the ofPlayerMatchingSum.
     */
    public double getOfPlayerMatchingSum() {
        return ofPlayerMatchingSum;
    }

    /**
     *
     * @param matching
     */
    public void increaseOfPlayerMatchingSum(double matching) {
        this.ofPlayerMatchingSum += matching;
    }

    /**
     * @return Returns the ofPlayerTries.
     */
    public int getOfPlayerTries() {
        return ofPlayerTries;
    }

    /**
     *
     */
    public void increaseOfPlayerTries() {
        this.ofPlayerTries++;
    }

    /**
     * @return Returns the passCreated.
     */
    public int getPassCreated() {
        return passCreated;
    }

    /**
     *
     */
    public void increasePassCreated() {
        this.passCreated++;
    }

    /**
     * @return Returns the passMatchingSum.
     */
    public double getPassMatchingSum() {
        return passMatchingSum;
    }

    public void increasePassMatchingSum(double matching) {
        this.passMatchingSum += matching;
    }

    /**
     * @return Returns the passTries.
     */
    public int getPassTries() {
        return passTries;
    }

    /**
     *
     */
    public void increasePassTries() {
        this.passTries++;
    }

    /**
     * @return Returns the successDefense.
     */
    public int getSuccessDefense() {
        return successDefense;
    }

    /**
     *
     */
    public void increaseSuccessDefense() {
        this.successDefense++;
    }

    /**
     * @return Returns the successDefPlayer.
     */
    public int getSuccessDefPlayer() {
        return successDefPlayer;
    }

    /**
     *
     */
    public void increaseSuccessDefPlayer() {
        this.successDefPlayer++;
    }

    /**
     * @return Returns the successKickAgent.
     */
    public int getSuccessKickAgent() {
        return successKickAgent;
    }

    /**
     *
     */
    public void increaseSuccessKickAgent() {
        this.successKickAgent++;
    }

    /**
     * @return Returns the successMicrosociety.
     */
    public int getSuccessMicrosociety() {
        return successMicrosociety;
    }

    /**
     *
     */
    public void increaseSuccessMicrosociety() {
        this.successMicrosociety++;
    }

    /**
     * @return Returns the successMidPass.
     */
    public int getSuccessMidPass() {
        return successMidPass;
    }

    /**
     *
     */
    public void increaseSuccessMidPass() {
        this.successMidPass++;
    }

    /**
     * @return Returns the successOfPlayer.
     */
    public int getSuccessOfPlayer() {
        return successOfPlayer;
    }

    /**
     *
     */
    public void increaseSuccessOfPlayer() {
        this.successOfPlayer++;
    }

    /**
     * @return Returns the successPass.
     */
    public int getSuccessPass() {
        return successPass;
    }

    /**
     *
     */
    public void increaseSuccessPass() {
        this.successPass++;
    }

    /**
     *
     */
    public void resStructuralDefense() {
        // TODO Auto-generated method stub
        this.structuralDefense--;
    }

    /**
     *
     */
    public void resStructuralOffensive() {
        // TODO Auto-generated method stub
        this.structuralOffensive--;
    }
}
