package entity;

import jakarta.persistence.*;
import java.util.Set;



@Entity
@Table(name = "leden")

public class Lid {

    public Lid() {
        // Standaard constructor
    }

    public Lid(String naam, String adres, String telefoonnummer) {
        this.naam = naam;
        this.adres = adres;
        this.telefoonnummer = telefoonnummer;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "adres")
    private String adres;

    @Column(name = "telefoonnummer")
    private String telefoonnummer;

    @OneToMany(mappedBy = "lid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Uitlening> uitleningen;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNaam() { return naam; }
    public void setNaam(String naam) { this.naam = naam; }
    public String getAdres() { return adres; }
    public void setAdres(String adres) { this.adres = adres; }
    public String getTelefoonnummer() { return telefoonnummer; }
    public void setTelefoonnummer(String telefoonnummer) { this.telefoonnummer = telefoonnummer; }
    public Set<Uitlening> getUitleningen() { return uitleningen; }
    public void setUitleningen(Set<Uitlening> uitleningen) { this.uitleningen = uitleningen; }
}
