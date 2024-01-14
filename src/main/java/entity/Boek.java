package entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "boeken")

public class Boek {

    public Boek() {
        // Standaard constructor
    }

    public Boek(String titel, String auteur, String genre) {
        this.titel = titel;
        this.auteur = auteur;
        this.genre = genre;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titel")
    private String titel;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "genre")
    private String genre;

    @ManyToMany(mappedBy = "boeken")
    private Set<Categorie> categorieen;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Set<Categorie> getCategorieen() { return categorieen; }
    public void setCategorieen(Set<Categorie> categorieen) { this.categorieen = categorieen; }
}
