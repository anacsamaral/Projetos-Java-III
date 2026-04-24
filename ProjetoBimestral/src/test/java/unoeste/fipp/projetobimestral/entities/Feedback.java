package unoeste.fipp.projetobimestral.entities;

public class Feedback {
    private Long id;
    private String texto;
    // one to one
    private Denuncia denuncia;
}
