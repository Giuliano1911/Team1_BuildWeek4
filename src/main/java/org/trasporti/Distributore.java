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

    @Column(name = "disponibilita")
    @Enumerated(EnumType.STRING)
    private Disponibilita disponibilita;

    // Costruttore
    public Distributore(Long id, String nome, Disponibilita disponibilita) {
        this.id = (long) id;
        this.nome = nome;
        this.disponibilita = disponibilita;
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
        return disponibilita;
    }

    public void setDisponibilita(Disponibilita disponibilita) {
        this.disponibilita = disponibilita;
    }

    @Override
    public String toString() {
        return "Distributore{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", disponibilita=" + disponibilita +
                '}';
    }
}
