package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.Validita;

import java.sql.Date;
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
    @Column
    private LocalDate data_emissione;
    @Column
    @Enumerated(EnumType.STRING)
    private Validita validita;

    public Abbonamento(Long id, Tessera tessera, Distributore distributore, LocalDate data_emissione, Validita validita) {
        this.id = id;
        this.tessera = tessera;
        this.distributore = distributore;
        this.data_emissione = data_emissione;
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

    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
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
                ", data_emissione=" + data_emissione +
                ", validita=" + validita +
                '}';
    }
}
