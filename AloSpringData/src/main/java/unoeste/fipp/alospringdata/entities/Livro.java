package unoeste.fipp.alospringdata.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;

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
    @JoinColumn(name = "ed_id",nullable = false)
    private Editora editora;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "livro_autor",
            joinColumns = @JoinColumn(name = "liv_id"),
            inverseJoinColumns = @JoinColumn(name = "aut_id"))
    private List<Autor> autores;

    @OneToMany(mappedBy = "livro", fetch = FetchType.EAGER)
    private List<Capitulo> capitulos;

    public Livro() {
    }

    public Livro(Long id, String titulo, Editora editora) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
    }

    public Livro(String titulo, Editora editora) {
        this(0L,titulo,editora);
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

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", editora=" + editora +
                ", autores=" + autores +
                '}';
    }
}
