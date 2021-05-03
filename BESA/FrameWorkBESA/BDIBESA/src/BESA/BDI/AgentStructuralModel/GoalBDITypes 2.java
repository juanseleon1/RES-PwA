/*
 * @(#)GoalBDITypes.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

/**
 * <p>Enum that represents the types of goals</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.2, 11/01/11
 * @since   JDK1.0
 */
public enum GoalBDITypes {

    SURVIVAL(1, "Survival"),
    DUTY(2, "Duty"),
    OPORTUNITY(3, "Oportunity"),
    REQUIREMENT(4, "Requirement"),
    NEED(5, "Need"),
    ATTENTION_CYCLE(6,"AttentionCycle");
    private int id;
    private String name;

    private GoalBDITypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}