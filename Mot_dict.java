// Fichier : Mot_dict.java
public class Mot_dict {
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
