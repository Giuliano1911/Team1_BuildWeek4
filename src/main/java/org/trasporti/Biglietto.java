package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.Timbrato;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name="biglietti")
public class Biglietto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Distributore distributore;
    @Column
    private LocalDate data_emissione;
    @Column
    private Timbrato timbrato;


    public Biglietto(Long id, Distributore distributore, LocalDate data_emissione, Timbrato timbrato) {
        this.id = id;
        this.distributore = distributore;
        this.data_emissione = data_emissione;
        this.timbrato = timbrato;
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
    public LocalDate getData_emissione() {
        return data_emissione;
    }
    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
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
                ", data_emissione=" + data_emissione +
                ", timbrato=" + timbrato +
                '}';
    }
}
