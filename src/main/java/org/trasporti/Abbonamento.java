package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.Validita;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tessera")
    private Tessera tessera;

    @ManyToOne
    @JoinColumn(name = "id_distributore")
    private Distributore distributore;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column
    @Enumerated(EnumType.STRING)
    private Validita validita;

    public Abbonamento(Tessera tessera, Distributore distributore, LocalDate dataEmissione, Validita validita) {
        this.tessera = tessera;
        this.distributore = distributore;
        this.dataEmissione = dataEmissione;
        this.validita = validita;
    }

    public Abbonamento() {

    }

    public Long getId() {
        return id;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
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

    public Validita getValidita() {
        return validita;
    }

    public void setValidita(Validita validita) {
        this.validita = validita;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", tessera=" + tessera +
                ", distributore=" + distributore +
                ", data_emissione=" + dataEmissione +
                ", validita=" + validita +
                '}';
    }
}
