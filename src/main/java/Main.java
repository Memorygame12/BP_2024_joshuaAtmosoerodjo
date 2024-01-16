import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import configuration.JPAConfiguration;
import entity.*;
import jakarta.persistence.EntityManagerFactory;
import repository.*;
import service.BoekService;
import service.LidService;
import service.UitleningService;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory emf = JPAConfiguration.getEntityManagerFactory();
    private static BoekService boekService = new BoekService(new BoekDAO(emf));
    private static LidService lidService = new LidService(new LidDAO(emf));
    private static UitleningService uitleningService = new UitleningService(new UitleningDAO(emf));
    private static CategorieDAO categorieDAO = new CategorieDAO(emf);
    private static BoekDetailsDAO boekDetailsDAO = new BoekDetailsDAO(emf);

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("Welkom bij de Bibliotheek Beheer Applicatie");
                System.out.println("1. Leden beheren");
                System.out.println("2. Boeken beheren");
                System.out.println("3. Uitleningen beheren");
                System.out.println("4. Rapporten genereren");
                System.out.println("5. Categorieën beheren");
                System.out.println("6. Afsluiten");

                System.out.print("Kies een optie: ");
                int keuze = Integer.parseInt(scanner.nextLine());

                switch (keuze) {
                    case 1:
                        beheerLeden();
                        break;
                    case 2:
                        beheerBoeken();
                        break;
                    case 3:
                        beheerUitleningen();
                        break;
                    case 4:
                        genereerRapporten();
                        break;
                    case 5:
                        beheerCategorieen();
                        break;
                    case 6:
                        System.out.println("Applicatie afsluiten...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer alstublieft een geldig nummer in.");
            } catch (Exception e) {
                System.out.println("Er is een fout opgetreden: " + e.getMessage());
            }
        }
    }


    private static void beheerLeden() {
        while (true) {
            try {
                System.out.println("\nLeden Beheer:");
                System.out.println("1. Lid toevoegen");
                System.out.println("2. Lid bijwerken");
                System.out.println("3. Lid verwijderen");
                System.out.println("4. Lid zoeken");
                System.out.println("5. Terug naar hoofdmenu");

                System.out.print("Kies een optie: ");
                int keuze = Integer.parseInt(scanner.nextLine());

                switch (keuze) {
                    case 1:
                        voegLidToe();
                        break;
                    case 2:
                        updateLid();
                        break;
                    case 3:
                        verwijderLid();
                        break;
                    case 4:
                        zoekLid();
                        break;
                    case 5:
                        return; // Gaat terug naar het hoofdmenu
                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer alstublieft een geldig nummer in.");
            } catch (Exception e) {
                System.out.println("Er is een fout opgetreden: " + e.getMessage());
                // Optioneel kunt u hier besluiten om ook terug te gaan naar het hoofdmenu
                // door bijvoorbeeld 'return;' toe te voegen als u niet wilt dat het huidige menu herhaald wordt na een algemene uitzondering.
            }
        }
    }



    private static void voegLidToe() {
        try {
            System.out.print("Naam: ");
            String naam = scanner.nextLine
                    (); // Gebruik nextLine() voor consistente input handling
            System.out.print("Adres: ");
            String adres = scanner.nextLine();
            System.out.print("Telefoonnummer: ");
            String telefoonnummer = scanner.nextLine();


            Lid nieuwLid = new Lid(naam, adres, telefoonnummer);
            lidService.addLid(nieuwLid);
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }


    private static void updateLid() {
        try {
            System.out.print("Lid ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nieuwe naam: ");
            String naam = scanner.nextLine();
            System.out.print("Nieuw adres: ");
            String adres = scanner.nextLine();
            System.out.print("Nieuw telefoonnummer: ");
            String telefoonnummer = scanner.nextLine();

            Lid lid = lidService.getLid(id);
            if (lid != null) {
                lid.setNaam(naam);
                lid.setAdres(adres);
                lid.setTelefoonnummer(telefoonnummer);
                lidService.updateLid(lid);
                System.out.println("Lidgegevens bijgewerkt.");
            } else {
                System.out.println("Lid niet gevonden.");
            }
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }

    private static void verwijderLid() {
        try {
            System.out.print("Lid ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            Lid lid = lidService.getLid(id);
            if (lid != null) {
                lidService.deleteLid(id);
                System.out.println("Lid verwijderd.");
            } else {
                System.out.println("Lid niet gevonden.");
            }
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }

    private static void zoekLid() {
        try {
            System.out.print("Lid ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            Lid lid = lidService.getLid(id);
            if (lid != null) {
                System.out.println("Lid gevonden: " + lid.getNaam() + ", " + lid.getAdres() + ", " + lid.getTelefoonnummer());
            } else {
                System.out.println("Lid niet gevonden.");
            }
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }

    private static void beheerBoeken() {
        while (true) {
            try {
                System.out.println("\nBoeken Beheer:");
                System.out.println("1. Boek toevoegen");
                System.out.println("2. Boek bijwerken");
                System.out.println("3. Boek verwijderen");
                System.out.println("4. Boek zoeken");
                System.out.println("5. Terug naar hoofdmenu");

                System.out.print("Kies een optie: ");
                int keuze = Integer.parseInt(scanner.nextLine());

                switch (keuze) {
                    case 1:
                        voegBoekToe();
                        break;
                    case 2:
                        updateBoek();
                        break;
                    case 3:
                        verwijderBoek();
                        break;
                    case 4:
                        zoekBoek();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer alstublieft een geldig nummer in.");
            } catch (Exception e) {
                System.out.println("Er is een fout opgetreden: " + e.getMessage());
            }
        }
    }


    private static void voegBoekToe() {
        System.out.print("Titel: ");
        String titel = scanner.nextLine();
        System.out.print("Auteur: ");
        String auteur = scanner.nextLine();


        // Toon beschikbare categorieën
        List<Categorie> alleCategorieen = categorieDAO.getAllCategorieen();
        for (Categorie c : alleCategorieen) {
            System.out.println("Categorie ID: " + c.getId() + ", Naam: " + c.getNaam());
        }

        // Laat de gebruiker categorieën kiezen
        System.out.print("Selecteer categorieën (IDs gescheiden door komma's): ");
        String[] gekozenCategorieIds = scanner.nextLine().split(",");
        Set<Categorie> geselecteerdeCategorieen = new HashSet<>();
        for (String idString : gekozenCategorieIds) {
            try {
                int id = Integer.parseInt(idString.trim());
                Categorie categorie = categorieDAO.getCategorie(id);
                if (categorie != null) {
                    geselecteerdeCategorieen.add(categorie);
                } else {
                    System.out.println("Categorie met ID " + id + " bestaat niet.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ongeldige invoer voor categorie ID: " + idString);
            }
        }

        System.out.print("Aantal exemplaren: ");
        int aantal;
        try {
            aantal = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer voor aantal. Standaardwaarde 0 wordt gebruikt.");
            aantal = 0;
        }

        Boek nieuwBoek = new Boek(titel, auteur, aantal);
        nieuwBoek.setCategorieen(geselecteerdeCategorieen);
        boekService.addBoek(nieuwBoek);

        System.out.print("Beschrijving: ");
        String beschrijving = scanner.nextLine();
        BoekDetails boekDetails = new BoekDetails(beschrijving, nieuwBoek);
        boekDetailsDAO.saveBoekDetails(boekDetails);

        System.out.println("Boek '" + titel + "' succesvol toegevoegd met details en categorieën.");
    }




    private static void updateBoek() {
        System.out.print("Boek ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nieuwe titel: ");
        String titel = scanner.nextLine();
        System.out.print("Nieuwe auteur: ");
        String auteur = scanner.nextLine();
        System.out.print("Nieuw aantal exemplaren: ");
        int aantal;
        try {
            aantal = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer voor aantal. Standaardwaarde 0 wordt gebruikt.");
            aantal = 0;
        }

        Boek boek = boekService.getBoek(id);
        if (boek != null) {
            boek.setTitel(titel);
            boek.setAuteur(auteur);
            boek.setAantal(aantal);
            boekService.updateBoek(boek);
            System.out.println("Boekgegevens bijgewerkt.");
        } else {
            System.out.println("Boek niet gevonden.");
        }
    }



    private static void verwijderBoek() {
        System.out.print("Boek ID: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
            boekService.deleteBoek(id);
            System.out.println("Boek verwijderd.");
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer. Voer alstublieft een geldig boek ID in.");
        }
    }


    private static void zoekBoek() {
        System.out.print("Boek ID: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
            Boek boek = boekService.getBoek(id);
            if (boek != null) {
                BoekDetails boekDetails = boekDetailsDAO.getBoekDetails(boek.getId());
                System.out.println("Boek gevonden: " + boek.getTitel() + ", " + boek.getAuteur());
                if (boekDetails != null) {
                    System.out.println("Beschrijving: " + boekDetails.getBeschrijving());
                } else {
                    System.out.println("Geen details beschikbaar voor dit boek.");
                }
            } else {
                System.out.println("Boek niet gevonden.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer voor Boek ID.");
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }



    private static void beheerUitleningen() {
        while (true) {
            try {
                System.out.println("\nUitleningen Beheer:");
                System.out.println("1. Boek uitlenen");
                System.out.println("2. Boek terugbrengen");
                System.out.println("3. Terug naar hoofdmenu");

                System.out.print("Kies een optie: ");
                int keuze = Integer.parseInt(scanner.nextLine());

                switch (keuze) {
                    case 1:
                        leenBoekUit();
                        break;
                    case 2:
                        brengBoekTerug();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer alstublieft een geldig nummer in.");
            } catch (Exception e) {
                System.out.println("Er is een fout opgetreden: " + e.getMessage());
            }
        }
    }


    private static void leenBoekUit() {
        int lidId, boekId;
        try {
            System.out.print("Lid ID: ");
            lidId = Integer.parseInt(scanner.nextLine());
            System.out.print("Boek ID: ");
            boekId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer. Voer alstublieft geldige nummers in.");
            return;
        }

        Lid lid = lidService.getLid(lidId);
        Boek boek = boekService.getBoek(boekId);

        if (lid != null && boek != null && boekService.isBoekBeschikbaar(boekId) && boek.getAantal() > 0) {
            boek.setAantal(boek.getAantal() - 1);
            boekService.updateBoek(boek);

            Uitlening nieuweUitlening = new Uitlening(lid, boek, new Date());
            uitleningService.addUitlening(nieuweUitlening);
            System.out.println("Boek uitgeleend.");
        } else {
            System.out.println("Boek of lid niet gevonden, of boek is niet beschikbaar.");
        }
    }



    private static void brengBoekTerug() {
        int uitleningId;
        try {
            System.out.print("Uitlening ID: ");
            uitleningId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige invoer. Voer een geldig uitlening ID in.");
            return;
        }

        Uitlening uitlening = uitleningService.getUitlening(uitleningId);
        if (uitlening != null) {
            uitlening.setTeruggebrachtOp(new Date());
            uitleningService.updateUitlening(uitlening);
            System.out.println("Boek teruggebracht.");

            // Verhoog de voorraad van het boek
            Boek boek = uitlening.getBoek();
            boek.setAantal(boek.getAantal() + 1);
            boekService.updateBoek(boek);
        } else {
            System.out.println("Uitlening niet gevonden.");
        }
    }


    private static void genereerRapporten() {
        while (true) {
            try {
                System.out.println("\nRapporten Genereren:");
                System.out.println("1. Rapport van uitgeleende boeken");
                System.out.println("2. Rapport van late retouren");
                System.out.println("3. Rapport van BoekenRapport");
                System.out.println("4. Terug naar hoofdmenu");

                System.out.print("Kies een optie: ");
                int keuze = Integer.parseInt(scanner.nextLine());

                switch (keuze) {
                    case 1:
                        genereerUitgeleendeBoekenRapport();
                        break;
                    case 2:
                        genereerLateRetourenRapport();
                        break;
                    case 3:
                        genereerBoekenRapport();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer alstublieft een geldig nummer in.");
            } catch (Exception e) {
                System.out.println("Er is een fout opgetreden: " + e.getMessage());
            }
        }
    }


    private static void genereerUitgeleendeBoekenRapport() {
        List<Uitlening> uitleningen = uitleningService.getAllUitleningen();
        for (Uitlening u : uitleningen) {
            if (u.getTeruggebrachtOp() == null) {
                System.out.println("Uitlening ID: " + u.getId() +
                        ", Boek ID: " + u.getBoek().getId() +
                        ", Uitgeleend op: " + u.getUitgeleendOp());
            }
        }
        // Terug naar hoofdmenu
        return;
    }



    private static void genereerLateRetourenRapport() {
        List<Uitlening> uitleningen = uitleningService.getAllUitleningen();
        for (Uitlening u : uitleningen) {
            if (u.getTeruggebrachtOp() != null) {
                long dagenTeLaat = ChronoUnit.DAYS.between(u.getUitgeleendOp().toInstant(), u.getTeruggebrachtOp().toInstant()) - 30;
                if (dagenTeLaat > 0) {
                    System.out.println("Late uitlening - Uitlening ID: " + u.getId() +
                            ", Boek ID: " + u.getBoek().getId() +
                            ", Uitgeleend op: " + u.getUitgeleendOp() +
                            ", Teruggebracht op: " + u.getTeruggebrachtOp() +
                            ", Dagen te laat: " + dagenTeLaat +
                            ", Boete: " + (dagenTeLaat * 5) + " SRD");
                }
            }
        }
// Terug naar hoofdmenu
        return;
    }

    private static void genereerBoekenRapport() {
        List<Boek> alleBoeken = boekService.getAllBoeken();
        if (alleBoeken.isEmpty()) {
            System.out.println("Er zijn momenteel geen boeken in de bibliotheek.");
        } else {
            System.out.println("\nAlle Boeken in de Bibliotheek:");
            for (Boek boek : alleBoeken) {
                System.out.println("ID: " + boek.getId() + ", Titel: " + boek.getTitel() +
                        ", Auteur: " + boek.getAuteur()  +
                        ", Aantal: " + boek.getAantal());
                // Hier kan je meer informatie toevoegen indien nodig
            }
        }
    }


    private static void beheerCategorieen() {
        CategorieDAO categorieDAO = new CategorieDAO(emf);

        while (true) {
            try {
                System.out.println("\nCategorie Beheer:");
                System.out.println("1. Categorie toevoegen");
                System.out.println("2. Categorieën weergeven");
                System.out.println("3. Categorie bijwerken");
                System.out.println("4. Categorie verwijderen");
                System.out.println("5. Zoek Categorie");
                System.out.println("6. Terug naar hoofdmenu");

                System.out.print("Kies een optie: ");
                int keuze = Integer.parseInt(scanner.nextLine());

                switch (keuze) {
                    case 1:
                        voegCategorieToe(categorieDAO);
                        break;
                    case 2:
                        toonCategorieen(categorieDAO);
                        break;
                    case 3:
                        updateCategorie(categorieDAO);
                        break;
                    case 4:
                        verwijderCategorie(categorieDAO);
                        break;
                    case 5:
                        zoekCategorie();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Ongeldige keuze, probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Voer alstublieft een geldig nummer in.");
            } catch (Exception e) {
                System.out.println("Er is een fout opgetreden: " + e.getMessage());
            }
        }
    }


    private static void voegCategorieToe(CategorieDAO categorieDAO) {
        try {
            System.out.print("Naam van de categorie: ");
            String naam = scanner.nextLine();
            Categorie categorie = new Categorie(naam);
            categorieDAO.saveCategorie(categorie);
            System.out.println("Categorie toegevoegd.");
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }

    private static void toonCategorieen(CategorieDAO categorieDAO) {
        List<Categorie> categorieen = categorieDAO.getAllCategorieen();
        for (Categorie c : categorieen) {
            System.out.println("ID: " + c.getId() + ", Naam: " + c.getNaam());
        }
    }

    private static void updateCategorie(CategorieDAO categorieDAO) {
        try {
            System.out.print("Categorie ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Nieuwe naam voor categorie: ");
            String naam = scanner.nextLine();

            Categorie categorie = new Categorie(naam);
            categorie.setId(id);
            categorieDAO.updateCategorie(categorie);
            System.out.println("Categorie bijgewerkt.");
        } catch (NumberFormatException e) {
            System.out.println("Voer alstublieft een geldig nummer in.");
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }

    private static void verwijderCategorie(CategorieDAO categorieDAO) {
        try {
            System.out.print("Categorie ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            categorieDAO.deleteCategorie(id);
            System.out.println("Categorie verwijderd.");
        } catch (NumberFormatException e) {
            System.out.println("Voer alstublieft een geldig nummer in.");
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }

    private static void zoekCategorie() {
        try {
            System.out.print("Voer Categorie ID in: ");
            int id = Integer.parseInt(scanner.nextLine());
            Categorie categorie = categorieDAO.getCategorie(id);
            if (categorie != null) {
                System.out.println("Categorie gevonden: ID = " + categorie.getId() + ", Naam = " + categorie.getNaam());
            } else {
                System.out.println("Geen categorie gevonden met ID " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Voer alstublieft een geldig nummer in.");
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }

    }
}
