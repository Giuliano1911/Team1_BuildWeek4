package org.trasporti;
import jakarta.persistence.*;
import org.trasporti.ENUMS.

import java.time.LocalDate;
import java.util.List;
@Entity
@Table ( name = "tratte")
public class Tratta {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column( name= "Nome_Utente")
    private String nomeUtente;

    @Column ( name = "data_emissione")
    private LocalDate dataEmissione ;

    public Tratta() {}
    public Tratta( String nomeUtente, LocalDate dataEmissione) {
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
        return "Tratta{" +
                "id=" + id +
                ", nomeUtente='" + nomeUtente + '\'' +
                ", dataEmissione=" + dataEmissione +
                '}';
    }
}