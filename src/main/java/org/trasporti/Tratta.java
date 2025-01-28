package org.trasporti;
import jakarta.persistence.*;
import org.trasporti.ENUMS.StatoMezzo;
import org.postgresql.util.PGInterval;

@Entity
@Table ( name = "tratte")
public class Tratta {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "zona_partenza")
    private String zonaPartenza;

    @Column(name = "stato_mezzo")
    private StatoMezzo statoMezzo;

    @Column(name = "tempo_percorrenza")
    private PGInterval tempoPercorrenza;

    public Tratta() {
    }

    public Tratta(String zonaPartenza, StatoMezzo statoMezzo, PGInterval tempoPercorrenza) {
        this.zonaPartenza = zonaPartenza;
        this.statoMezzo = statoMezzo;
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

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public PGInterval getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(PGInterval tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", statoMezzo=" + statoMezzo +
                ", tempoPercorrenza=" + tempoPercorrenza +
                '}';
    }
}