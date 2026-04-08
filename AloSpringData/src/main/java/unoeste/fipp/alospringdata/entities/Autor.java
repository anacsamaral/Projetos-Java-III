package unoeste.fipp.alospringdata.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
    @Column(name = "aut_id")
    private Long id;
    @Column(name = "aut_nome")
    private String nome;
    @Column(name = "aut_nacionalidade")
    private String nacionalidade;

    public Autor() {
        this(0L,"","");
    }

    public Autor(Long id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public Autor(String nome, String nacionalidade) {
        this(0L,nome, nacionalidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
