package com.juanurbina.gradeunab;

import java.io.Serializable;

public class Materia implements Serializable {
    private String nombreMateria;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int credMateria;

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getCredMateria() {
        return credMateria;
    }

    public void setCredMateria(int credMateria) {
        this.credMateria = credMateria;
    }

    public Materia(String nombreMateria, int credMateria) {
        this.nombreMateria = nombreMateria;
        this.credMateria = credMateria;
    }

    public Materia() {
    }
}
