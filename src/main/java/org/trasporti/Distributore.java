package org.trasporti;

public class Distributore {
    private int id;
    private String nome;
    private Disponibilita t_disponibilita;

    // Costruttore
    public Distributore(int id, String nome, Disponibilita t_disponibilita) {
        this.id = id;
        this.nome = nome;
        this.t_disponibilita = t_disponibilita;
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setDisponibilita(t_disponibilita) {
        this.t_disponibilita = t_disponibilita;
    }
}
