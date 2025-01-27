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
    private Integer idMezzo;

    @ManyToOne
    @Column(name = "id_biglietto")
    private Integer idBiglietto;

    @Column(name = "data_obliterazione")
    private LocalDate dataObliterazione;

    public Obliterazione() {
    }

    public Obliterazione(Integer idMezzo, Integer idBiglietto, LocalDate dataObliterazione) {
        this.idMezzo = idMezzo;
        this.idBiglietto = idBiglietto;
        this.dataObliterazione = dataObliterazione;
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

    public Integer getIdBiglietto() {
        return idBiglietto;
    }

    public void setIdBiglietto(Integer idBiglietto) {
        this.idBiglietto = idBiglietto;
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
                ", idMezzo=" + idMezzo +
                ", idBiglietto=" + idBiglietto +
                ", dataObliterazione=" + dataObliterazione +
                '}';
    }
}
