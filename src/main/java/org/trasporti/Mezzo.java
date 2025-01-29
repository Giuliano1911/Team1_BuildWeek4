package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.StatoMezzo;
import org.trasporti.ENUMS.TipoMezzo;

@Entity
@Table(name = "mezzi")
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_mezzo")
    private String numeroMezzo;

    @Column(name = "tipo_mezzo")
    @Enumerated(EnumType.STRING)
    private TipoMezzo tipoMezzo;

    @Column(name = "stato_mezzo")
    @Enumerated(EnumType.STRING)
    private StatoMezzo statoMezzo;

    private Integer nPosti;

    public Mezzo() {
    }

    public Mezzo(String numeroMezzo, TipoMezzo tipoMezzo, StatoMezzo statoMezzo) {
        this.numeroMezzo = numeroMezzo;
        this.tipoMezzo = tipoMezzo;
        this.statoMezzo = statoMezzo;
        this.nPosti = setnPosti();
    }

    public Long getId() {
        return id;
    }

    public String getNumeroMezzo() {
        return numeroMezzo;
    }

    public void setNumeroMezzo(String numeroMezzo) {
        this.numeroMezzo = numeroMezzo;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public Integer setnPosti() {
        if (this.tipoMezzo == TipoMezzo.BUS)
            return nPosti = 30;
        else return nPosti = 60;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", numeroMezzo='" + numeroMezzo + '\'' +
                ", tipoMezzo=" + tipoMezzo +
                ", statoMezzo=" + statoMezzo +
                ", numeroPosti=" + nPosti +
                '}';
    }
}
