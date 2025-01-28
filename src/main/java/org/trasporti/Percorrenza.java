package org.trasporti;

import jakarta.persistence.*;

@Entity
@Table(name = "percorrenze")
public class Percorrenza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Tratta tratta;

    @Column(name = "tempo_effettivo")
    private String tempoEffettivo;

    public Percorrenza() {
    }

    public Percorrenza(Mezzo mezzo, Tratta tratta, String tempoEffettivo) {
        this.mezzo = mezzo;
        this.tratta = tratta;
        this.tempoEffettivo = tempoEffettivo;
    }

    public Long getId() {
        return id;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public String getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(String tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id=" + id +
                ", mezzo=" + mezzo.getId() +
                ", tratta=" + tratta.getId() +
                ", tempoEffettivo='" + tempoEffettivo + '\'' +
                '}';
    }
}
