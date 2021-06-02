package joueurs;

import composants.Objet;
import composants.Utils;
import grafix.interfaceGraphique.IG;
import partie.ElementsPartie;

/**
 * Cette classe abstraite reprÃ©sente un joueur du jeu. Ce joueur peut Ãªtre un joueur humain ou un joueur ordinateur.
 */
public abstract class Joueur {

    private int numJoueur; // le numÃ©ro du jouer
    private String nomJoueur; // Le nom du joueur
    private int numeroImagePersonnage; // Le numÃ©ro de l'image reprÃ©sentant le joueur
    private Objet[] objetsJoueur; // Les objets qui sont attribuÃ©s au joueur et qui devront Ãªtre rÃ©cupÃ©rÃ©s dans l'ordre
    private int nombreObjetsRecuperes; // le nombre d'objets rÃ©cupÃ©rÃ©s
    private int posLigne; // La ligne correspondant Ã  la position du joueur sur le plateau
    private int posColonne; // La colonne correspondant Ã  la position du joueur sur le plateau

    /**
     * A Faire (23/05/2021 LG,LI Finalisée)
     * <p>
     * Constructeur permettant de crÃ©er un joueur Ã  partir de son nom, son type et
     * le numÃ©ro de l'image le reprÃ©sentant. La position du joueur sur le plateau doit Ãªtre
     * Ã©galement indiquÃ©e. Aucun objet n'est attribuÃ© au joueur (l'attribut objetsJoueur vaudra null aprÃ¨s la construction du joueur).
     *
     * @param numJoueur             Le numÃ©ro du joueur.
     * @param nomJoueur             Le nom du joueur.
     * @param numeroImagePersonnage Le numÃ©ro de l'image reprÃ©sentant le joueur.
     * @param posLignePlateau       La ligne du plateau sur laquelle est positionnÃ©e le joueur.
     * @param posColonnePlateau     La colonne du plateau sur laquelle est positionnÃ©s le joueur.
     */
    public Joueur(int numJoueur, String nomJoueur, int numeroImagePersonnage, int posLignePlateau, int posColonnePlateau) {
        this.numJoueur = numJoueur;
        this.nomJoueur = nomJoueur;
        this.numeroImagePersonnage = numeroImagePersonnage;
        this.posLigne = posLignePlateau;
        this.posColonne = posColonnePlateau;
        this.objetsJoueur = null;
    }

    /**
     * MÃ©thode permettant de crÃ©er des joueurs Ã  partir de paramÃ¨tres obtenus Ã  partir des fenÃªtres de paramÃ©trages.
     * Les joueurs sont positionnÃ©s dans les diffÃ©rents coins du plateau en fonction de leur rang
     * (le premier au coin en haut Ã  gauche, le deuxiÃ¨me au coin en haut Ã  droite, le troisiÃ¨me en bas Ã  droite).
     * Le joueur de numÃ©ro 0 est le premier Ã©lÃ©ment du tableau retournÃ©, le joueur de numÃ©ro 1 est le deuxiÃ¨me Ã©lÃ©ment du tableau retournÃ© et
     * l'Ã©ventuel joueur de numÃ©ro 2 est l'Ã©ventuel troisiÃ¨me Ã©lÃ©ment du tableau retournÃ©.
     *
     * @param parametresJeu Les paramÃ¨tres du jeu.
     * @return Un tableau de joueurs initialisÃ©s.
     */
    public static Joueur[] nouveauxJoueurs(Object parametresJeu[]) {
        int nombreJoueurs = ((Integer) parametresJeu[0]).intValue();
        Joueur joueurs[] = null;
        String nomJoueur, typeJoueur;
        int posJoueurs[][] = {{0, 0}, {0, 6}, {6, 6}};
        int numImageJoueur;
        joueurs = new Joueur[nombreJoueurs];
        for (int i = 0; i < nombreJoueurs; i++) {
            nomJoueur = (String) parametresJeu[1 + (3 * i)];
            typeJoueur = (String) parametresJeu[2 + (3 * i)];
            numImageJoueur = ((Integer) parametresJeu[3 + (3 * i)]).intValue();
            if (typeJoueur.equals("Humain"))
                joueurs[i] = new JoueurHumain(i, nomJoueur, numImageJoueur, posJoueurs[i][0], posJoueurs[i][1]);
            else if (typeJoueur.equals("OrdiType0"))
                joueurs[i] = new JoueurOrdinateurT0(i, nomJoueur, numImageJoueur, posJoueurs[i][0], posJoueurs[i][1]);
            else if (typeJoueur.equals("OrdiType1"))
                joueurs[i] = new JoueurOrdinateurT1(i, nomJoueur, numImageJoueur, posJoueurs[i][0], posJoueurs[i][1]);
            else if (typeJoueur.equals("OrdiType2"))
                joueurs[i] = new JoueurOrdinateurT2(i, nomJoueur, numImageJoueur, posJoueurs[i][0], posJoueurs[i][1]);
            else if (typeJoueur.equals("OrdiType3"))
                joueurs[i] = new JoueurOrdinateurT3(i, nomJoueur, numImageJoueur, posJoueurs[i][0], posJoueurs[i][1]);
            else {
                System.err.println("Type de joueur non gÃ©rÃ© : " + typeJoueur + " crÃ©ation d'un joueur du type par dÃ©faut (Humain) ...");
                joueurs[i] = new JoueurHumain(i, nomJoueur, numImageJoueur, posJoueurs[i][0], posJoueurs[i][1]);
            }
        }
        return joueurs;
    }

    /**
     * A Faire (23/05/2021 LG Finalisée)
     * <p>
     * MÃ©thode retournant le nombre d'objets rÃ©cupÃ©rÃ©s par le joueur.
     *
     * @return Le nombre d'objets rÃ©cupÃ©rÃ©s par le joueur.
     */
    public int getNombreObjetsRecuperes() {
        return this.nombreObjetsRecuperes;
    }


    /**
     * A Faire (23/05/2021 LG Finalisée)
     * <p>
     * MÃ©thode retournant la ligne du plateau sur laquelle se trouve le joueur.
     *
     * @return La ligne du plateau sur laquelle se trouve le joueur.
     */
    public int getPosLigne() {
        return this.posLigne;
    }


    /**
     * A Faire (23/05/2021 LG Finalisée)
     * <p>
     * MÃ©thode retournant la colonne du plateau sur laquelle se trouve le joueur.
     *
     * @return La colonne du plateau sur laquelle se trouve le joueur.
     */
    public int getPosColonne() {
        return this.posColonne;
    }


    /**
     * A Faire (23/05/2021 LG Finalisée)
     * <p>
     * MÃ©thode retournant le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getNomJoueur() {
        return this.nomJoueur;
    }

    /**
     * A Faire (23/05/2021 LG Finalisée)
     * <p>
     * MÃ©thode retournant le numÃ©ro de l'image reprÃ©sentant le joueur.
     *
     * @return Le numÃ©ro de l'image reprÃ©sentant le joueur.
     */
    public int getNumeroImagePersonnage() {
        return this.numeroImagePersonnage;
    }

    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * MÃ©thode permettant d'affecter au joueur les objets qu'il devra rÃ©cupÃ©rer durant le jeu.
     * Attention : cette mÃ©thode devra crÃ©er un nouveau tableau pour l'attribut this.objetsARecuperer.
     *
     * @param objetsARecuperer Un tableau contenant les objets Ã  rÃ©cupÃ©rer dans l'ordre.
     */
    public void setObjetsJoueur(Objet[] objetsARecuperer) {
        this.objetsJoueur = new Objet[objetsARecuperer.length];
        for (int i = 0; i < objetsARecuperer.length; i++) {
            this.objetsJoueur[i] = objetsARecuperer[i];
        }
    }

    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * MÃ©thode retournant un nouveau tableau contenant les objets attribuÃ©s au joueur. Des objets Ã  rÃ©cupÃ©rer devront Ãªtre
     * affectÃ©s au joueur avant tout appel de cette mÃ©thode (on suppose donc que l'attribut objetsJoueur est non null).
     *
     * @return Un tableau d'Objet correspondant aux objets Ã  rÃ©cupÃ©rer du joueur.
     */
    public Objet[] getObjetsJoueur() {
        Objet[] resultat = new Objet[this.objetsJoueur.length];

        for (int i = 0; i < this.objetsJoueur.length; i++) {
            resultat[i] = this.objetsJoueur[i];
        }

        return resultat;
    }


    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * MÃ©thode retournant le prochain objet Ã  rÃ©cupÃ©rer par le joueur.
     * Avant d'appeler cette mÃ©thode il est nÃ©cessaire de s'assurer qu'il existe encore des objets Ã  rÃ©cupÃ©rer.
     *
     * @return Le prochain objet Ã  rÃ©cupÃ©rer par le joueur.
     */
    public Objet getProchainObjet() {
        if (this.nombreObjetsRecuperes < this.objetsJoueur.length) {
            return this.objetsJoueur[this.nombreObjetsRecuperes];
        }
        return null;
    }

    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * MÃ©thode permettant de rÃ©cupÃ©rer un nouvel objet. Cette mÃ©thode incrÃ©mente simplement de 1 le nombre d'objets qui ont Ã©tÃ© rÃ©cupÃ©rÃ©s.
     */
    public void recupererObjet() {
        this.nombreObjetsRecuperes++;
    }


    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * MÃ©thode retournant le numÃ©ro du joueur.
     *
     * @return Le numÃ©ro du joueur.
     */
    public int getNumJoueur() {
        return this.numJoueur; // A Modifier
    }

    /**
     * A Faire (Quand Qui Statut)
     * <p>
     * MÃ©thode permettant le changement de position du joueur.
     *
     * @param posLigne   La ligne de la nouvelle position.
     * @param posColonne La colonne de la nouvelle position.
     */
    public void setPosition(int posLigne, int posColonne) {
        if ((posLigne >= 0 && posLigne <= 6) && (posColonne >= 0 && posColonne <= 6)) {
            this.posLigne = posLigne;
            this.posColonne = posColonne;
        }
    }

    /**
     * MÃ©thode retournant un String reprÃ©sentant la catÃ©gorie du joueur. Par dÃ©faut retourne
     * le nom de la classe du joueur.
     *
     * @return Un String reprÃ©sentant la catÃ©gorie du joueur.
     */
    public String getCategorie() {
        return this.getClass().getName();
    }

    /**
     * Cette mÃ©thode est appelÃ©e lorsque le joueur doit jouer en dÃ©but de son tour. Il doit choisir une orientation de la piÃ¨ce qui est hors plateau
     * et une entrÃ©e (une flÃªche) dans le plateau. Ce choix se fera Ã  travers l'interface graphique pour un joueur humain et par calcul pour un joueur
     * ordinateur. Les Ã©lÃ©ments de la partie sont passÃ©s en paramÃ¨tre pour qu'on joueur ordinateur puisse faire ses calculs. Dans le cas d'un joueur humain,
     * ce paramÃ¨tre n'est pas utile.
     *
     * @param elementsPartie Les Ã©lÃ©ments de la partie.
     * @return Un tableau contenant deux entiers, le premier correspond Ã  l'orientation choisie de la piÃ¨ce hors plateau (un nombre entre 0 et 3) et le second Ã  l'entrÃ©e du plateau (un nombre entre 0 et 27).
     */

    abstract public int[] choisirOrientationEntree(ElementsPartie elementsPartie);


    /**
     * Cette mÃ©thode est appelÃ©e lorsque le joueur doit se dÃ©placer et donc choisir une case sur le plateau. Pour un joueur humain ce choix se fera Ã  l'aide
     * de l'interface graphique tandis que pour un ordinateur elle se fera totalement par calcul. Il n'existe pas forcÃ©ment un chemin entre la case du joueur et la case choisie.
     *
     * @param elementsPartie Les Ã©lÃ©ments de la partie.
     * @return Un tableau contenant deux entiers, le premier correspond Ã  la ligne de la case choisie, le second Ã  la colonne de la case choisie.
     */
    abstract public int[] choisirCaseArrivee(ElementsPartie elementsPartie);


    /**
     * MÃ©thode retournant un nouveau tableau contenant les Ã©lÃ©ments du tableau objets donnÃ© en paramÃ¨tre qui ont mÃªme numÃ©ro qu'un objet appartenant Ã  l'attribut
     * objetsJoueur. L'ordre des Ã©lÃ©ments du tableau retournÃ© doit suivre l'ordre des objets se trouvant dans objetsJoueur.
     *
     * @param objets L'ensemble de tous les objets du jeu dans un ordre indÃ©fini.
     * @return Un nouveau tableau contenant les Ã©lÃ©ments du tableau objets donnÃ© en paramÃ¨tre qui ont mÃªme numÃ©ro qu'un objet appartenant Ã  l'attribut
     * objetsARecuperer (ordonnÃ© de la mÃªme maniÃ¨re que les objets attribuÃ©s au joueur).
     */
    public Objet[] getObjetsJoueurGeneral(Objet objets[]) {
        Objet resultat[] = new Objet[objetsJoueur.length];
        for (int i = 0; i < objetsJoueur.length; i++)
            for (int j = 0; j < objets.length; j++)
                if (objets[j].getNumeroObjet() == objetsJoueur[i].getNumeroObjet())
                    resultat[i] = objets[j];
        return resultat;
    }

    /**
     * MÃ©thode retournant une copie du joueur.
     *
     * @param objets Les objets du jeu.
     * @return Une copie du joueur.
     */
    public abstract Joueur copy(Objet objets[]);


}