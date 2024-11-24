package grafikus.notebook.Models;


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

    @Column(name = "processzorid")
    private Integer ProcesszorId;

    @Column(name = "oprendszerid")
    private Integer OprendszerId;

    @Column(name = "db")
    private Integer Db;

    public Notebook() {
    }

    public Notebook(String gyarto, String tipus, float kijelzo, Integer memoria, Integer merevlemez, String videovezerlo, Integer ar, Integer processzorId, Integer oprendszerId, Integer db) {
        Gyarto = gyarto;
        Tipus = tipus;
        Kijelzo = kijelzo;
        Memoria = memoria;
        Merevlemez = merevlemez;
        Videovezerlo = videovezerlo;
        Ar = ar;
        ProcesszorId = processzorId;
        OprendszerId = oprendszerId;
        Db = db;
    }

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

    public Integer getProcesszorId() {
        return ProcesszorId;
    }

    public void setProcesszorId(Integer processzorId) {
        ProcesszorId = processzorId;
    }

    public Integer getOprendszerId() {
        return OprendszerId;
    }

    public void setOprendszerId(Integer oprendszerId) {
        OprendszerId = oprendszerId;
    }

    public Integer getDb() {
        return Db;
    }

    public void setDb(Integer db) {
        Db = db;
    }
}
