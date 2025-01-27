package org.trasporti;
import org.trasporti.ENUMS.
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table (name = "tessere")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name="nome_utente")
    private String nomeUtente;

    @Column(name= "data_emissione")
    private LocalDate dataEmissione;

    public Tessera() {
    }
    public Tessera(String nomeUtente , LocalDate dataEmissione) {
        this.nomeUtente = nomeUtente ;
        this.dataEmissione = dataEmissione ;
    }

    public Long getId() {
        return id;
    }


    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}