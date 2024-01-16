package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "boek_details")
public class BoekDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boekDetails_id;

    @Column(name = "beschrijving")
    private String beschrijving;

    @OneToOne
    @JoinColumn(name = "boek_id", unique = true)
    private Boek boek;

    // Standaard constructor
    public BoekDetails() {
    }

    // Constructor met parameters
    public BoekDetails(String beschrijving, Boek boek) {
        this.beschrijving = beschrijving;
        this.boek = boek;
    }

    // Getters en setters
    public int getId() {
        return boekDetails_id;
    }

    public void setId(int id) {
        this.boekDetails_id = id;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Boek getBoek() {
        return boek;
    }

    public void setBoek(Boek boek) {
        this.boek = boek;
    }
}
