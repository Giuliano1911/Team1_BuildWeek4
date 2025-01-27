package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.Disponibilita;

@Entity
@Table(name = "distributori")
public class Distributore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    @Enumerated(EnumType.STRING)
    private Disponibilita t_disponibilita;

    // Costruttore
    public Distributore(Long id, String nome, Disponibilita t_disponibilita) {
        this.id = (long) id;
        this.nome = nome;
        this.t_disponibilita = t_disponibilita;
    }

    public Distributore() {

    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Disponibilita getDisponibilita() {
        return t_disponibilita;
    }

    public void setDisponibilita(Disponibilita t_disponibilita) {
        this.t_disponibilita = t_disponibilita;
    }

    @Override
    public String toString() {
        return "Distributore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", t_disponibilita=" + t_disponibilita +
                '}';
    }
}
