package com.example.alospringdata.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liv_id")
    private Long id;

    @Column(name = "liv_titulo")
    private String titulo;

    @ManyToOne
    @JoinColumn(name="edi_id")
    private Editora editora;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "livro_autor", joinColumns =  @JoinColumn(name = "liv_id"),
            inverseJoinColumns = @JoinColumn(name = "aut_id"))
    private List<Autor> autores=new ArrayList<>();

    @OneToMany(mappedBy = "livro", fetch = FetchType.EAGER)
    private List<Capitulo> capitulos=new ArrayList<>();

    public Livro() {
    }

    public Livro(Long id, String titulo, Editora editora) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
    }

    public Livro(String titulo, Editora editora) {
        this.titulo = titulo;
        this.editora = editora;
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

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Capitulo> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<Capitulo> capitulos) {
        this.capitulos = capitulos;
    }
    public void addAutor(Autor autor){
        this.autores.add(autor);
    }
    public void addCapitulo(Capitulo capitulo){
        this.capitulos.add(capitulo);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", editora=" + editora +
                ", autores=" + autores +
                ", capitulos=" + capitulos +
                '}';
    }
}
