package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.Timbrato;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Distributore distributore;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column
    @Enumerated(EnumType.STRING)
    private Timbrato timbrato;


    public Biglietto(Distributore distributore, LocalDate dataEmissione) {
        this.distributore = distributore;
        this.dataEmissione = dataEmissione;
        this.timbrato = Timbrato.DA_TIMBRARE;
    }

    public Biglietto() {

    }

    public Long getId() {
        return id;
    }

    public Distributore getDistributore() {
        return distributore;
    }

    public void setDistributore(Distributore distributore) {
        this.distributore = distributore;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public Timbrato getTimbrato() {
        return timbrato;
    }

    public void setTimbrato(Timbrato timbrato) {
        this.timbrato = timbrato;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", distributore=" + distributore +
                ", data_emissione=" + dataEmissione +
                ", timbrato=" + timbrato +
                '}';
    }
}
