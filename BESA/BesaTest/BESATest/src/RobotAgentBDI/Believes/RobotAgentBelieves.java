/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import rational.data.InfoData;
import rational.mapping.Believes;

/**
 *
 * @author juans
 */
public class RobotAgentBelieves implements Believes {

    private char letra;
    private int factor;
    private double resultado;
    private List<String> lista;
    private int cont;
    private int contL;

    public RobotAgentBelieves(String cedula) {
        letra = 'A';
        factor = 2;
        resultado = 1;
        lista = new ArrayList<>();
        cont = 0;
        contL = 0;

        lista.add("Cosa 1");
        lista.add("Cosa 2");
        lista.add("Cosa 3");
        lista.add("Cosa 4");
        lista.add("Cosa 5");
        lista.add("Cosa 6");
        lista.add("Cosa 7");
        lista.add("Cosa 8");
        lista.add("Cosa 9");
        lista.add("Cosa 10");
        lista.add("Cosa 11");
        lista.add("Cosa 12");

    }

    @Override
    public boolean update(InfoData si) {
        return true;
    }

    @Override
    public Believes clone() throws CloneNotSupportedException {
        return this;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public int getContL() {
        return contL;
    }

    public void setContL(int contL) {
        this.contL = contL;
    }

    public boolean isTestDone() {
        return getResultado()>10000;
    }

}
