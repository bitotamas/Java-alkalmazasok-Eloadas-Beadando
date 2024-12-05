package com.notebook.Models;


import javax.persistence.*;

@Entity
@Table(name = "oprendszer")
public class OS {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)      // Elsődleges kulcs, Auto_Increment
    @Column(name = "id")
    private int Id;
    @Column(name = "nev")
    private String Nev;

    public OS() {}
    public OS(String nev) {
        Nev = nev;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNev() {
        return Nev;
    }

    public void setNev(String nev) {
        Nev = nev;
    }
}
