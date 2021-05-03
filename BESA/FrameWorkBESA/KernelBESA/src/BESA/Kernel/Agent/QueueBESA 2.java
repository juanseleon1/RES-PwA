/*
 * @(#)QueueBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import java.util.ArrayList;

/**
 * This class is used to represent the handling of events in a messages queue.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
class QueueBESA {

    /**
     *
     */
    protected ArrayList<Object> queue;

    /**
     * Builds a new instance of the Queue class.
     */
    public QueueBESA() {
        queue = new ArrayList<Object>();
    }

    /**
     * Obtains the first element in the queue. After read the first element in
     * the queue, this is eliminated from the list of messages.
     * 
     * @return The first Object in the queue. 
     */
    public Object first() {
        // Sacar y eliminar el primer elemento de la cola
        if (queue.size() > 0) {
            return queue.remove(0);
        } else {
            return null;
        }
    }

    /**
     * Indicates if the queue is empty or not.
     * 
     * @return true if the queue is empty; false otherwise. 
     */
    public boolean isEmpty() {
        // Verdadero si cola vacia
        if (queue.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds an element to end of the queue.
     * 
     * @param element Element to be added. 
     */
    public void add(Object element) {
        // Adicionar el elemento al final
        queue.add(element);
    }

    /**
     * Returns the state of the queue.
     * 
     * @return A String with information of the Queue object.
     */
    public String toString() {
        return queue.toString();
    }
}
