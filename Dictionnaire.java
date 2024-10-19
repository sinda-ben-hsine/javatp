import java.util.Scanner;

class Mot_dict {
    private String mot;
    private String definition;

    public Mot_dict(String mot, String definition) {
        this.mot = mot;
        this.definition = definition;
    }

    public String getMot() {
        return mot;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public boolean synonyme(String ch) {
        return mot.equalsIgnoreCase(ch);
    }

    public boolean synonyme(Mot_dict autreMot) {
        return mot.equalsIgnoreCase(autreMot.getMot());
    }
}

public class Dictionnaire {
    private Mot_dict[] dict;
    private String nom;
    private int nb_Mots;
    private int taille;
    private Scanner scanner;

    public Dictionnaire(String nom, int taille) {
        this.dict = new Mot_dict[taille];
        this.nom = nom;
        this.nb_Mots = 0;
        this.taille = taille;
        this.scanner = new Scanner(System.in);
    }

    public int getNbMots() {
        return nb_Mots;
    }

    public void Ajouter_Mot() {
        if (nb_Mots >= taille) {
            System.out.println("Dictionnaire plein, impossible d'ajouter un nouveau mot.");
            return;
        }

        System.out.print("Entrez le mot à ajouter : ");
        String mot = scanner.nextLine();
        System.out.print("Entrez la définition du mot : ");
        String definition = scanner.nextLine();

        Mot_dict nouveauMot = new Mot_dict(mot, definition);
        int i = nb_Mots - 1;
        while (i >= 0 && dict[i].getMot().compareToIgnoreCase(nouveauMot.getMot()) > 0) {
            dict[i + 1] = dict[i];
            i--;
        }
        dict[i + 1] = nouveauMot;
        nb_Mots++;
        System.out.println("Mot ajouté : " + mot);
    }

    public void Trier() {
        for (int i = 0; i < nb_Mots - 1; i++) {
            for (int j = 0; j < nb_Mots - i - 1; j++) {
                if (dict[j].getMot().compareToIgnoreCase(dict[j + 1].getMot()) > 0) {
                    Mot_dict temp = dict[j];
                    dict[j] = dict[j + 1];
                    dict[j + 1] = temp;
                }
            }
        }
    }

    public void Supprimer_Mot() {
        System.out.print("Entrez le mot à supprimer : ");
        String motASupprimer = scanner.nextLine();
        int index = -1;
        for (int i = 0; i < nb_Mots; i++) {
            if (dict[i].getMot().equalsIgnoreCase(motASupprimer)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < nb_Mots - 1; i++) {
                dict[i] = dict[i + 1];
            }
            dict[nb_Mots - 1] = null;
            nb_Mots--;
            System.out.println("Mot supprimé : " + motASupprimer);
        } else {
            System.out.println("Mot non trouvé : " + motASupprimer);
        }
    }

    public void Recherche_dicho() {
        System.out.print("Entrez le mot à rechercher : ");
        String motCherche = scanner.nextLine();
        for (int i = 0; i < nb_Mots; i++) {
            if (dict[i].getMot().equalsIgnoreCase(motCherche)) {
                System.out.println("Définition de " + motCherche + ": " + dict[i].getDefinition());
                return;
            }
        }
        System.out.println("Mot non trouvé : " + motCherche);
    }

    public void Lister_dictionnaire() {
        System.out.println("Contenu du dictionnaire '" + nom + "' :");
        for (int i = 0; i < nb_Mots; i++) {
            System.out.println(dict[i].getMot() + ": " + dict[i].getDefinition());
        }
    }

    public void afficherMenu() {
        int choix;
        do {
            System.out.println("\nMenu :");
            System.out.println("1. Ajouter un mot");
            System.out.println("2. Supprimer un mot");
            System.out.println("3. Rechercher un mot");
            System.out.println("4. Lister le dictionnaire");
            System.out.println("5. Quitter");
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    Ajouter_Mot();
                    break;
                case 2:
                    Supprimer_Mot();
                    break;
                case 3:
                    Recherche_dicho();
                    break;
                case 4:
                    Lister_dictionnaire();
                    break;
                case 5:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 5);
    }

    public static void main(String[] args) {
        Dictionnaire dict = new Dictionnaire("Mon Dictionnaire", 10);
        dict.afficherMenu();
    }
}
