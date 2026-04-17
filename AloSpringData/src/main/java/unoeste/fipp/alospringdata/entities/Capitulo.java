package unoeste.fipp.alospringdata.entities;

import jakarta.persistence.*;
import jdk.jfr.TransitionTo;

@Entity
@Table(name = "capitulo")
public class Capitulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cap_id")
    private Long id;
    @Column(name = "cap_titulo")
    private String titulo;
    @Column(name = "cap_pagina")
    private int pagina;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liv_id")
    private Livro livro;

    public Capitulo() {
    }

    public Capitulo(Long id, String titulo, int pagina) {
        this.id = id;
        this.titulo = titulo;
        this.pagina = pagina;
    }

    public Capitulo(String titulo, int pagina) {
        this.titulo = titulo;
        this.pagina = pagina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }



    @Override
    public String toString() {
        return "Capitulo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", pagina=" + pagina +
                '}';
    }
}
