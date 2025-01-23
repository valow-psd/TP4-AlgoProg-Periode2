import java.util.*;
import java.lang.*;
public class TasK {
    int[] tas;
    int k;

    TasK(int k, int[] tab) {
        this.k = k;
        this.tas = tab;
        construireTas(tas);

    }

    private static void entasser(int[] table, int index, int k) {
        int i = index;

        // Tant qu'il existe des enfants
        while (k * i + 1 < table.length) {
            int childrenCount = Math.min(k, table.length - (k * i + 1)); // Nombre d'enfants du nœud
            int[] tmp = new int[childrenCount];

            // Remplissage du tableau temporaire avec les enfants
            for (int j = 0; j < childrenCount; j++) {
                tmp[j] = table[k * i + j + 1];
            }

            // Trouver le maximum parmi les enfants
            int[] m = findMax(tmp);
            int globalIndex = k * i + m[1] + 1; // Index global du maximum

            // Si le maximum des enfants est plus petit ou égal au nœud actuel, on arrête
            if (table[i] >= table[globalIndex]) {
                break;
            }

            // Échange entre le nœud actuel et son enfant maximum
            int temp = table[i];
            table[i] = table[globalIndex];
            table[globalIndex] = temp;

            // Mise à jour de l'indice pour descendre dans l'arbre
            i = globalIndex;
        }
    }

    public   void construireTas(int [] table){
        // On part du dernier nœud parent vers la racine
        for (int i = (table.length - 1) ; i >= 0; i--) {
            entasser(table, i, k);
        }

    }

    public static int[] findMax(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Le tableau ne peut pas être nul ou vide.");
        }

        int max = array[0];
        int index = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }

        return new int[] { max, index };
    }

    public int SuppMax(){
        int res = recupererPremierElement(tas);
        echangerElements(tas, 0, tas.length - 1);
        tas = supprimerDernierElement(tas);
        construireTas(tas);
        return res;
    }

    public void ajouter(int valeur){
        tas = ajouterElementEnTete(tas,valeur);
        entasser(tas,0,k);
    }

    public int[] trier(){
        int[] res = Arrays.copyOf(tas,tas.length);
        // TasK intermédiaire permettant de récupérer le maximum à chaque fois
        TasK tmp = new TasK(k,Arrays.copyOf(tas,tas.length));
        for (int i = (res.length - 1) ; i >= 0; i--) {
            res[i] = tmp.SuppMax();
        }
        return res;
    }


    // fonctions utilitaires pour manipuler facilement les tableaux

    public static int recupererPremierElement(int[] tableau) {
        if (tableau == null || tableau.length == 0) {
            throw new IllegalArgumentException("Le tableau est vide ou null.");
        }
        return tableau[0];
    }

    // Méthode pour supprimer le dernier élément et réduire la taille du tableau
    public static int[] supprimerDernierElement(int[] tableau) {
        if (tableau == null || tableau.length == 0) {
            throw new IllegalArgumentException("Le tableau est vide ou null.");
        }
        return Arrays.copyOf(tableau, tableau.length - 1);
    }

    // Méthode pour ajouter un élément au début du tableau
    public static int[] ajouterElementEnTete(int[] tableau, int nouvelElement) {
        if (tableau == null) {
            throw new IllegalArgumentException("Le tableau est null.");
        }
        int[] nouveauTableau = new int[tableau.length + 1];
        nouveauTableau[0] = nouvelElement;
        System.arraycopy(tableau, 0, nouveauTableau, 1, tableau.length);
        return nouveauTableau;
    }


    // Méthode pour échanger deux éléments dans un tableau
    public static void echangerElements(int[] tableau, int index1, int index2) {
        if (tableau == null || index1 < 0 || index2 < 0 || index1 >= tableau.length || index2 >= tableau.length) {
            throw new IllegalArgumentException("Indices invalides ou tableau null.");
        }
        int temp = tableau[index1];
        tableau[index1] = tableau[index2];
        tableau[index2] = temp;
    }

    public static void main(String[] args) {
        int[] tab = { 16,4, 10, 14, 7, 9, 3, 2, 8, 1};
        TasK tas = new TasK(3, tab);

        // Affichage du tableau après la construction du tas
        for (int i = 0; i < tas.tas.length; i++) {
            System.out.println(tas.tas[i]);
        }

        System.out.println("Le tableau trié obtenu à partir de ce tas est :" + Arrays.toString(tas.trier()));

        for (int i = 0; i < tas.tas.length; i++) {
            System.out.println(tas.tas[i]);
        }
        tas.ajouter(23);
        for (int i = 0; i < tas.tas.length; i++) {
            System.out.println(tas.tas[i]);
        }
    }
}
