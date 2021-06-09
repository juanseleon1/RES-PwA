/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RobotAgentBDI.Believes.ModeloEmocional;

/**
 *
 * @author juans
 */
public class EmotionalConfig {

    public static enum People {
        Enemigo(-1f),
        NoAmigable(-0.3f),
        Desconocido(0f),
        Amigo(0.7f),
        Cercano(0.8f);

        private final float value;

        People(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }

    public static enum Events {
        Indeseable(-1),
        AlgoIndeseable(-0.4f),
        Indiferente(0f),
        AlgoDeseable(0.4f),
        Deseable(0.1f);

        private final float value;

        Events(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }

    }

    public static enum Objects {
        Repulsivo(-1f),
        NoValioso(-0.2f),
        Indiferente(0),
        Valioso(0.6f),
        Importante(0.8f);

        private final float value;

        Objects(float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }
}
