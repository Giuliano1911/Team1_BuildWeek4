package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.StatoMezzo;

@Entity
@Table(name = "tratte")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zona_partenza")
    private String zonaPartenza;

    @Column(name = "capolinea")
    private String capolinea;

    @Column(name = "tempo_percorrenza")
    private String tempoPercorrenza;

    public Tratta() {
    }

    public Tratta(String zonaPartenza, String capolinea, String tempoPercorrenza) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public Long getId() {
        return id;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public String getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(String tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", capolinea=" + capolinea +
                ", tempoPercorrenza=" + tempoPercorrenza +
                '}';
    }
}