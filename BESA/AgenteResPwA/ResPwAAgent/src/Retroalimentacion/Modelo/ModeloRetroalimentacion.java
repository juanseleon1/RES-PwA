/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Retroalimentacion.Modelo;

import BDInterface.RESPwABDInterface;
import ResPwAEntities.Antecedente;
import ResPwAEntities.Baile;
import ResPwAEntities.Cancion;
import ResPwAEntities.Cuento;
import ResPwAEntities.Regla;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ModeloRetroalimentacion<T> {

    private T activity;
    private List<Regla> reglas;

    public ModeloRetroalimentacion(T activity) {
        this.activity = activity;
        this.reglas = RESPwABDInterface.getRules();
    }

    public void activityFeedback(List<Antecedente> antecedentes) {
        Regla reglaAplicada = findRule(antecedentes);
        if (activity instanceof Cancion) {
            Cancion c = (Cancion) activity;
            if (c.getGusto() + reglaAplicada.getFeedback() > 1.0f) {
                c.setGusto(1.0f);
            } else {
                if (c.getGusto() + reglaAplicada.getFeedback() < 0.0f) {
                    c.setGusto(0.0f);
                } else {
                    c.setGusto(c.getGusto() + reglaAplicada.getFeedback());
                }
            }

        } else if (activity instanceof Cuento) {
            Cuento c = (Cuento) activity;
            if (c.getGusto() + reglaAplicada.getFeedback() > 1.0f) {
                c.setGusto(1.0f);
            } else {
                if (c.getGusto() + reglaAplicada.getFeedback() < 0.0f) {
                    c.setGusto(0.0f);
                } else {
                    c.setGusto(c.getGusto() + reglaAplicada.getFeedback());
                }

            }
        } else if (activity instanceof Baile) {
            Baile b = (Baile) activity;
            if (b.getGusto() + reglaAplicada.getFeedback() > 1.0f) {
                b.setGusto(1.0f);
            } else {
                if (b.getGusto() + reglaAplicada.getFeedback() < 0.0f) {
                    b.setGusto(0.0f);
                } else {
                    b.setGusto(b.getGusto() + reglaAplicada.getFeedback());
                }
            }
        }
    }

    private Regla findRule(List<Antecedente> antecedentes) {
        boolean encontrado = false;
        boolean antecedenteEncontrado = true;
        List<Antecedente> antecedentesXRegla = null;
        Regla findRule = null;

        for (int i = 0; i < reglas.size() && !encontrado; i++) {
            encontrado = true;
            for (int j = 0; j < antecedentes.size() && encontrado; j++) {
                antecedentesXRegla = reglas.get(i).getAntecedenteList();
                antecedenteEncontrado = false;

                for (int k = 0; k < antecedentesXRegla.size() && !antecedenteEncontrado; k++) {
                    if (antecedentes.get(j).equals(antecedentesXRegla.get(k))) {
                        antecedenteEncontrado = true;
                    }
                }

                if (!antecedenteEncontrado) {
                    encontrado = false;
                }
            }

            if (encontrado) {
                findRule = reglas.get(i);
            }
        }
        return findRule;
    }
}
