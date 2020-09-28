/*
 * @(#)ConvertClass.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Util;

import java.util.Collection;
import java.util.Iterator;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ConvertClass {

    /**
     * 
     * @param type 
     * @param raw 
     * @param cooked 
     */
    public static <T> void convertCollection(Class<T> type, Collection raw, Collection<T> cooked) {
        for (Iterator i = raw.iterator(); i.hasNext();) {
            cooked.add(type.cast(i.next()));
        }
    }
}
