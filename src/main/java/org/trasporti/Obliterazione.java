package org.trasporti;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "obliterazioni")
public class Obliterazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "id_biglietto")
    private Biglietto biglietto;

    @Column(name = "data_obliterazione")
    private LocalDate dataObliterazione;

    public Obliterazione() {
    }

    public Obliterazione(Mezzo mezzo, Biglietto biglietto, LocalDate dataObliterazione) {
        this.mezzo = mezzo;
        this.biglietto = biglietto;
        this.dataObliterazione = dataObliterazione;
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

    public Biglietto getBiglietto() {
        return biglietto;
    }

    public void setBiglietto(Biglietto biglietto) {
        this.biglietto = biglietto;
    }

    public LocalDate getDataObliterazione() {
        return dataObliterazione;
    }

    public void setDataObliterazione(LocalDate dataObliterazione) {
        this.dataObliterazione = dataObliterazione;
    }

    @Override
    public String toString() {
        return "Obliterazione{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", biglietto=" + biglietto +
                ", dataObliterazione=" + dataObliterazione +
                '}';
    }
}
