package com.juanurbina.gradeunab;

public class Reminder {
    private String nombreReminder;
    private String txtReminder;

    private String idReminder;

    public String getNombreReminder() {
        return nombreReminder;
    }

    public void setNombreReminder(String nombreReminder) {
        this.nombreReminder = nombreReminder;
    }

    public String getTxtReminder() {
        return txtReminder;
    }

    public void setTxtReminder(String txtReminder) {
        this.txtReminder = txtReminder;
    }

    public Reminder(String nombreReminder, String txtReminder) {
        this.nombreReminder = nombreReminder;
        this.txtReminder = txtReminder;
    }

    public Reminder() {
    }

    public String getIdReminder() {
        return idReminder;
    }

    public void setIdReminder(String idReminder) {
        this.idReminder = idReminder;
    }
}
