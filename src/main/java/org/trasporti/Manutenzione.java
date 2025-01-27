package org.trasporti;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "manutenzioni")
public class Manutenzione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mezzo")
    private Integer idMezzo;

    private String descrizione;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    public Manutenzione() {
    }

    public Manutenzione(Integer mezzoId, String descrizione, LocalDate dataInizio, LocalDate dataFine) {
        this.idMezzo = mezzoId;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Manutenzione(Integer mezzoId, String descrizione, LocalDate dataInizio) {
        this.idMezzo = mezzoId;
        this.descrizione = descrizione;
        this.dataInizio = dataInizio;
    }

    public Long getId() {
        return id;
    }

    public Integer getMezzoId() {
        return idMezzo;
    }

    public void setMezzoId(Integer mezzoId) {
        this.idMezzo = mezzoId;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "Manutenzione{" +
                "id=" + id +
                ", mezzoId=" + idMezzo +
                ", descrizione='" + descrizione + '\'' +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
