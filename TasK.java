import java.util.*;
import java.lang.*;
public class TasK {
    int[] tas;
    int k;

    TasK(int k, int[] tab) {
        this.k = k;
        this.tas = tab;
        // On part du dernier nœud parent vers la racine
        for (int i = (this.tas.length - 1) ; i >= 0; i--) {
            entasser(this.tas, i, k);
        }
        
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

    public static void main(String[] args) {
        //int[] tab = { 16,4, 10, 14, 7, 9, 3, 2, 8, 1};
        int[] tab = { 16,14, 9, 11,22, 10, 3, 4, 1, 7,13, 8, 2, 5, 6, 12};
        TasK tas = new TasK(3, tab);

        // Affichage du tableau après la construction du tas
        for (int i = 0; i < tas.tas.length; i++) {
            System.out.print(tas.tas[i] + " ");
        }
        System.out.println();
    }
}
