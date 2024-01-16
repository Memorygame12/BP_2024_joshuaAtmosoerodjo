package entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boeken")

public class Boek {

    public Boek() {
        // Standaard constructor
    }

    public Boek(String titel, String auteur, int aantal) {
        this.titel = titel;
        this.auteur = auteur;
        this.aantal = aantal;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boek_id;

    @Column(name = "titel")
    private String titel;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "aantal")
    private int aantal;

    @ManyToMany(mappedBy = "boeken")
    private Set<Categorie> categorieen = new HashSet<>();

//    private Set<Categorie> categorieen;

//    @ManyToMany(mappedBy = "boeken", cascade =  CascadeType.PERSIST, fetch = FetchType.EAGER )
//    private Set<Categorie> categorieen;

    public int getId() { return boek_id; }
    public void setId(int id) { this.boek_id = id; }
    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public Set<Categorie> getCategorieen() { return categorieen; }
    public void setCategorieen(Set<Categorie> categorieen) { this.categorieen = categorieen; }
    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}
