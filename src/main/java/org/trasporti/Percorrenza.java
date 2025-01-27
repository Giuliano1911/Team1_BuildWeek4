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
    private Integer idMezzo;

    @ManyToOne
    @JoinColumn(name = "id_tratta")
    private Integer idTratta;

    @Column(name = "tempo_effettivo")
    private Integer tempoEffettivo;

    public Percorrenza() {
    }

    public Percorrenza(Long id, Integer idMezzo, Integer idTratta, Integer tempoEffettivo) {
        this.id = id;
        this.idMezzo = idMezzo;
        this.idTratta = idTratta;
        this.tempoEffettivo = tempoEffettivo;
    }

    public Long getId() {
        return id;
    }

    public Integer getIdMezzo() {
        return idMezzo;
    }

    public void setIdMezzo(Integer idMezzo) {
        this.idMezzo = idMezzo;
    }

    public Integer getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(Integer idTratta) {
        this.idTratta = idTratta;
    }

    public Integer getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(Integer tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    @Override
    public String toString() {
        return "Percorrenza{" +
                "id=" + id +
                ", idMezzo=" + idMezzo +
                ", idTratta=" + idTratta +
                ", tempoEffettivo=" + tempoEffettivo +
                '}';
    }
}
