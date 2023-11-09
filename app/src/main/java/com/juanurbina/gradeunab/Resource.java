package com.juanurbina.gradeunab;

public class Resource {
    private String  nameResource;
    private String urlResource;

    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public Resource(String nameResource, String urlResource) {
        this.nameResource = nameResource;
        this.urlResource = urlResource;
    }

    public String getUrlResource() {
        return urlResource;
    }

    public void setUrlResource(String urlResource) {
        this.urlResource = urlResource;
    }
}
