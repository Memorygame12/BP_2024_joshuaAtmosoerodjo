package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "uitleningen")
public class Uitlening {

    public Uitlening() {
        // Standaard constructor
    }

    public Uitlening(Lid lid, Boek boek, Date uitgeleendOp) {
        this.lid = lid;
        this.boek = boek;
        this.uitgeleendOp = uitgeleendOp;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lid_id")
    private Lid lid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boek_id")
    private Boek boek;

    @Column(name = "uitgeleend_op")
    @Temporal(TemporalType.DATE)
    private Date uitgeleendOp;

    @Column(name = "teruggebracht_op")
    @Temporal(TemporalType.DATE)
    private Date teruggebrachtOp;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Lid getLid() { return lid; }
    public void setLid(Lid lid) { this.lid = lid; }
    public Boek getBoek() { return boek; }
    public void setBoek(Boek boek) { this.boek = boek; }
    public Date getUitgeleendOp() { return uitgeleendOp; }
    public void setUitgeleendOp(Date uitgeleendOp) { this.uitgeleendOp = uitgeleendOp; }
    public Date getTeruggebrachtOp() { return teruggebrachtOp; }
    public void setTeruggebrachtOp(Date teruggebrachtOp) { this.teruggebrachtOp = teruggebrachtOp; }
}
