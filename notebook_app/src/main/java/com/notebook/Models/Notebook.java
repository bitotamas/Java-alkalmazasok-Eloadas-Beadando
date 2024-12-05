package com.notebook.Models;


import javax.persistence.*;

@Entity
@Table(name = "gep")
public class Notebook {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)      // Elsődleges kulcs, Auto_Increment
    @Column(name = "id")
    private Integer Id;

    @Column(name = "gyarto")
    private String Gyarto;

    @Column(name = "tipus")
    private String Tipus;

    @Column(name = "kijelzo")
    private float Kijelzo;

    @Column(name = "memoria")
    private Integer Memoria;

    @Column(name = "merevlemez")
    private Integer Merevlemez;

    @Column(name = "videovezerlo")
    private String Videovezerlo;

    @Column(name = "ar")
    private Integer Ar;

    @Column(name = "db")
    private Integer Db;

    public Notebook() {
    }

    @ManyToOne
    @JoinColumn(name = "processzorid")
    private CPU processzor;  // CPU objektum (tartalmazza a processzor nevét)

    @ManyToOne
    @JoinColumn(name = "oprendszerid")
    private OS operaciosRendszer;  // OS objektum (tartalmazza az oprendszer nevét)


    public String getProcesszorNev() {
        return processzor != null ? processzor.getGyarto()+" "+processzor.getTipus() : "N/A";  // Ha létezik processzor, akkor annak nevét adja vissza
    }

    public String getOperaciosRendszerNev() {
        return operaciosRendszer != null ? operaciosRendszer.getNev() : "N/A";  // Ha létezik oprendszer, akkor annak nevét adja vissza
    }

    public Notebook(String gyarto, String tipus, float kijelzo, Integer memoria, Integer merevlemez, String videovezerlo, Integer ar, Integer db, CPU processzor, OS operaciosRendszer) {
        Gyarto = gyarto;
        Tipus = tipus;
        Kijelzo = kijelzo;
        Memoria = memoria;
        Merevlemez = merevlemez;
        Videovezerlo = videovezerlo;
        Ar = ar;
        Db = db;
        this.processzor = processzor;
        this.operaciosRendszer = operaciosRendszer;
    }

//   public Notebook(String gyarto, String tipus, float kijelzo, Integer memoria, Integer merevlemez, String videovezerlo, Integer ar, /*Integer processzorId, Integer oprendszerId,*/ Integer db) {
 //       Gyarto = gyarto;
  //      Tipus = tipus;
  //      Kijelzo = kijelzo;
  //      Memoria = memoria;
//        Merevlemez = merevlemez;
 //       Videovezerlo = videovezerlo;
   //     Ar = ar;
     //   Db = db;
    //}


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getGyarto() {
        return Gyarto;
    }

    public void setGyarto(String gyarto) {
        Gyarto = gyarto;
    }

    public String getTipus() {
        return Tipus;
    }

    public void setTipus(String tipus) {
        Tipus = tipus;
    }

    public float getKijelzo() {
        return Kijelzo;
    }

    public void setKijelzo(float kijelzo) {
        Kijelzo = kijelzo;
    }

    public Integer getMemoria() {
        return Memoria;
    }

    public void setMemoria(Integer memoria) {
        Memoria = memoria;
    }

    public Integer getMerevlemez() {
        return Merevlemez;
    }

    public void setMerevlemez(Integer merevlemez) {
        Merevlemez = merevlemez;
    }

    public String getVideovezerlo() {
        return Videovezerlo;
    }

    public void setVideovezerlo(String videovezerlo) {
        Videovezerlo = videovezerlo;
    }

    public Integer getAr() {
        return Ar;
    }

    public void setAr(Integer ar) {
        Ar = ar;
    }

    public Integer getDb() {
        return Db;
    }

    public void setDb(Integer db) {
        Db = db;
    }
}
