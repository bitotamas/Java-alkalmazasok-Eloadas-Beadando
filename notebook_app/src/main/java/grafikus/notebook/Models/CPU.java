package grafikus.notebook.Models;

import javax.persistence.*;

@Entity
@Table(name = "processzor")
public class CPU {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)      // Elsődleges kulcs, Auto_Increment
    @Column(name = "id")
    private Integer Id;
    @Column(name = "gyarto")
    private String Gyarto;
    @Column(name = "tipus")
    private String Tipus;

    public CPU() {
    }

    public CPU(Integer id, String gyarto, String tipus) {
        Id = id;
        Gyarto = gyarto;
        Tipus = tipus;
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
}
