package service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import configuration.JPAConfiguration;
import entity.*;
import jakarta.persistence.EntityManagerFactory;
import repository.*;

public class BibliotheekApp {
    private static Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory emf = JPAConfiguration.getEntityManagerFactory();
    private static BoekService boekService = new BoekService(new BoekDAO(emf));
    private static LidService lidService = new LidService(new LidDAO(emf));
    private static UitleningService uitleningService = new UitleningService(new UitleningDAO(emf));
    private static CategorieDAO categorieDAO = new CategorieDAO(emf);
    private static BoekDetailsDAO boekDetailsDAO = new BoekDetailsDAO(emf);

    public static void main(String[] args) {

        while (true) {
            System.out.println("Welkom bij de Bibliotheek Beheer Applicatie");
            System.out.println("1. Leden beheren");
            System.out.println("2. Boeken beheren");
            System.out.println("3. Uitleningen beheren");
            System.out.println("4. Rapporten genereren");
            System.out.println("5. Categorieën beheren");
            System.out.println("6. Afsluiten");

            System.out.print("Kies een optie: ");
            int keuze = scanner.nextInt();

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
        }
    }

    private static void beheerLeden() {
        while (true) {
            System.out.println("\nLeden Beheer:");
            System.out.println("1. Lid toevoegen");
            System.out.println("2. Lid bijwerken");
            System.out.println("3. Lid verwijderen");
            System.out.println("4. Lid zoeken");
            System.out.println("5. Terug naar hoofdmenu");

            System.out.print("Kies een optie: ");
            int keuze = scanner.nextInt();

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
                    return;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        }
    }

    private static void voegLidToe() {
        try {
            System.out.print("Naam: ");
            String naam = scanner.next();
            System.out.print("Adres: ");
            String adres = scanner.next();
            System.out.print("Telefoonnummer: ");
            String telefoonnummer = scanner.next();

            Lid nieuwLid = new Lid(naam, adres, telefoonnummer);
            lidService.addLid(nieuwLid);
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }

    private static void updateLid() {
        System.out.print("Lid ID: ");
        int id = scanner.nextInt();
        System.out.print("Nieuwe naam: ");
        String naam = scanner.next();
        System.out.print("Nieuw adres: ");
        String adres = scanner.next();
        System.out.print("Nieuw telefoonnummer: ");
        String telefoonnummer = scanner.next();

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
    }

    private static void verwijderLid() {
        System.out.print("Lid ID: ");
        int id = scanner.nextInt();
        Lid lid = lidService.getLid(id);
        if (lid != null) {
            lidService.deleteLid(id);
            System.out.println("Lid verwijderd.");
        } else {
            System.out.println("Lid niet gevonden.");
        }
    }

    private static void zoekLid() {
        System.out.print("Lid ID: ");
        int id = scanner.nextInt();
        Lid lid = lidService.getLid(id);
        if (lid != null) {
            System.out.println("Lid gevonden: " + lid.getNaam() + ", " + lid.getAdres() + ", " + lid.getTelefoonnummer());
        } else {
            System.out.println("Lid niet gevonden.");
        }
    }

    private static void beheerBoeken() {
        while (true) {
            System.out.println("\nBoeken Beheer:");
            System.out.println("1. Boek toevoegen");
            System.out.println("2. Boek bijwerken");
            System.out.println("3. Boek verwijderen");
            System.out.println("4. Boek zoeken");
            System.out.println("5. Terug naar hoofdmenu");

            System.out.print("Kies een optie: ");
            int keuze = scanner.nextInt();

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
        }
    }

    private static void voegBoekToe() {
        System.out.print("Titel: ");
        scanner.nextLine(); // Consume the leftover newline
        String titel = scanner.nextLine();
        System.out.print("Auteur: ");
        String auteur = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Aantal exemplaren: ");
        int aantal = scanner.nextInt(); // Voeg deze regel toe om het aantal te vragen

        Boek nieuwBoek = new Boek(titel, auteur, genre, aantal); // Gebruik 'aantal' hier
        boekService.addBoek(nieuwBoek);

        scanner.nextLine(); // Consume the leftover newline na het lezen van een int
        System.out.print("Beschrijving: ");
        String beschrijving = scanner.nextLine();
        BoekDetails boekDetails = new BoekDetails(beschrijving, nieuwBoek);

        boekDetailsDAO.saveBoekDetails(boekDetails);

        System.out.println("Boek '" + titel + "' is succesvol toegevoegd met details.");
    }


    private static void updateBoek() {
        System.out.print("Boek ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline
        System.out.print("Nieuwe titel: ");
        String titel = scanner.nextLine();
        System.out.print("Nieuwe auteur: ");
        String auteur = scanner.nextLine();
        System.out.print("Nieuw genre: ");
        String genre = scanner.nextLine();
        System.out.print("Nieuw aantal exemplaren: ");
        int aantal = scanner.nextInt(); // Vraag naar het nieuwe aantal

        Boek boek = new Boek(titel, auteur, genre, aantal);
        boek.setId(id);
        boekService.updateBoek(boek);
    }



    private static void verwijderBoek() {
        System.out.print("Boek ID: ");
        int id = scanner.nextInt();
        boekService.deleteBoek(id); // Gebruik boekService in plaats van boekDAO
    }


    private static void zoekBoek() {
        try{
        System.out.print("Boek ID: ");
        int id = scanner.nextInt();
        Boek boek = boekService.getBoek(id);
        if (boek != null) {
            BoekDetails boekDetails = boekDetailsDAO.getBoekDetails(boek.getId());
            System.out.println("Boek gevonden: " + boek.getTitel() + ", " + boek.getAuteur() + ", " + boek.getGenre());
            if (boekDetails != null) {
                System.out.println("Beschrijving: " + boekDetails.getBeschrijving());
            } else {
                System.out.println("Geen details beschikbaar voor dit boek.");
            }
        } else {
            System.out.println("Boek niet gevonden.");
        }
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        }
    }


    private static void beheerUitleningen() {
        while (true) {
            System.out.println("\nUitleningen Beheer:");
            System.out.println("1. Boek uitlenen");
            System.out.println("2. Boek terugbrengen");
            System.out.println("3. Terug naar hoofdmenu");

            System.out.print("Kies een optie: ");
            int keuze = scanner.nextInt();

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
        }
    }

    private static void leenBoekUit() {
        System.out.print("Lid ID: ");
        int lidId = scanner.nextInt();
        System.out.print("Boek ID: ");
        int boekId = scanner.nextInt();

        Lid lid = lidService.getLid(lidId);
        Boek boek = boekService.getBoek(boekId);

        if (lid != null && boek != null && boekService.isBoekBeschikbaar(boekId)) {
            Uitlening nieuweUitlening = new Uitlening(lid, boek, new Date());
            uitleningService.addUitlening(nieuweUitlening);
            System.out.println("Boek uitgeleend.");
        } else {
            System.out.println("Boek of lid niet gevonden, of boek is niet beschikbaar.");
        }

        if (boek != null && boek.getAantal() > 0) {
            // Verminder de voorraad
            boek.setAantal(boek.getAantal() - 1);
            boekService.updateBoek(boek);

            // Creëer en sla de uitlening op
            Uitlening nieuweUitlening = new Uitlening(lid, boek, new Date());
            uitleningService.addUitlening(nieuweUitlening);
            System.out.println("Boek uitgeleend.");
        } else {
            System.out.println("Boek is niet beschikbaar.");
        }
    }


    private static void brengBoekTerug() {
        System.out.print("Uitlening ID: ");
        int uitleningId = scanner.nextInt();
        Uitlening uitlening = uitleningService.getUitlening(uitleningId);
        if (uitlening != null) {
            uitlening.setTeruggebrachtOp(new Date());
            uitleningService.updateUitlening(uitlening);
            System.out.println("Boek teruggebracht.");
        } else {
            System.out.println("Uitlening niet gevonden.");
        }
    }

    private static void genereerRapporten() {
        while (true) {
            System.out.println("\nRapporten Genereren:");
            System.out.println("1. Rapport van uitgeleende boeken");
            System.out.println("2. Rapport van late retouren");
            System.out.println("3. Terug naar hoofdmenu");

            System.out.print("Kies een optie: ");
            int keuze = scanner.nextInt();

            switch (keuze) {
                case 1:
                    genereerUitgeleendeBoekenRapport();
                    break;
                case 2:
                    genereerLateRetourenRapport();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
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
    }


    private static void beheerCategorieen() {
        CategorieDAO categorieDAO = new CategorieDAO(emf);

        while (true) {
            System.out.println("\nCategorie Beheer:");
            System.out.println("1. Categorie toevoegen");
            System.out.println("2. Categorieën weergeven");
            System.out.println("3. Categorie bijwerken");
            System.out.println("4. Categorie verwijderen");
            System.out.println("5. Zoek Categorie");
            System.out.println("6. Terug naar hoofdmenu");


            System.out.print("Kies een optie: ");
            int keuze = scanner.nextInt();

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
        }
    }

    private static void voegCategorieToe(CategorieDAO categorieDAO) {
        System.out.print("Naam van de categorie: ");
        scanner.nextLine(); // Consume newline left-over
        String naam = scanner.nextLine();
        Categorie categorie = new Categorie(naam);
        categorieDAO.saveCategorie(categorie);
        System.out.println("Categorie toegevoegd.");
    }

    private static void toonCategorieen(CategorieDAO categorieDAO) {
        List<Categorie> categorieen = categorieDAO.getAllCategorieen();
        for (Categorie c : categorieen) {
            System.out.println("ID: " + c.getId() + ", Naam: " + c.getNaam());
        }
    }

    private static void updateCategorie(CategorieDAO categorieDAO) {
        System.out.print("Categorie ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Nieuwe naam voor categorie: ");
        String naam = scanner.nextLine();

        Categorie categorie = new Categorie(naam);
        categorie.setId(id);
        categorieDAO.updateCategorie(categorie);
        System.out.println("Categorie bijgewerkt.");
    }

    private static void verwijderCategorie(CategorieDAO categorieDAO) {
        System.out.print("Categorie ID: ");
        int id = scanner.nextInt();
        categorieDAO.deleteCategorie(id);
        System.out.println("Categorie verwijderd.");
    }

    private static void zoekCategorie() {
        System.out.print("Voer Categorie ID in: ");
        int id = scanner.nextInt();
        CategorieDAO categorieDAO = new CategorieDAO(emf);
        Categorie categorie = categorieDAO.getCategorie(id);

        if (categorie != null) {
            System.out.println("Categorie gevonden: ID = " + categorie.getId() + ", Naam = " + categorie.getNaam());
        } else {
            System.out.println("Geen categorie gevonden met ID " + id);
        }
    }

}

