package org.trasporti;

import jakarta.persistence.*;
import org.trasporti.ENUMS.Disponibilita;
@Entity
@Table(name="distributori")
public class Distributore {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private Disponibilita t_disponibilita;

    // Costruttore
    public Distributore(int id, String nome, Disponibilita t_disponibilita) {
        this.id = (long) id;
        this.nome = nome;
        this.t_disponibilita = t_disponibilita;
    }

    public Distributore() {

    }

    // Getter e Setter
    public int getId() {
        return Math.toIntExact(id);
    }

    public void setId(int id) {
        this.id = (long) id;
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
