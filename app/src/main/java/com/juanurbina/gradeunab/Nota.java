package com.juanurbina.gradeunab;

public class Nota {
    private String nombreNota;
    private String idNota;
    private double porcNota;
    private double califNota;
    private String idMateria;

    public Nota(String nombreNota, double porcNota, double califNota, String idMateria) {
        this.nombreNota = nombreNota;
        this.porcNota = porcNota;
        this.califNota = califNota;
        this.idMateria = idMateria;
    }

    public String getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(String idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreNota() {
        return nombreNota;
    }

    public void setNombreNota(String nombreNota) {
        this.nombreNota = nombreNota;
    }

    public String getIdNota() {
        return idNota;
    }

    public void setIdNota(String idNota) {
        this.idNota = idNota;
    }

    public double getPorcNota() {
        return porcNota;
    }

    public void setPorcNota(double porcNota) {
        this.porcNota = porcNota;
    }

    public double getCalifNota() {
        return califNota;
    }

    public void setCalifNota(double califNota) {
        this.califNota = califNota;
    }

    public Nota(String nombreNota, double porcNota, double califNota) {
        this.nombreNota = nombreNota;
        this.porcNota = porcNota;
        this.califNota = califNota;
    }

    public Nota() {
    }
}
