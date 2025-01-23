public class Mult2n {

    int X;
    int Y;
    StringBuilder steps = new StringBuilder();

    Mult2n(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int m2n1() {
        steps.setLength(0); // Clear steps for m2n1
        return m2n1R(X, Y);
    }

    private int m2n1R(int x, int y) {
        // Si x ou y est un seul chiffre, retourner le produit
        if (x < 10 || y < 10) {
            steps.append("Cas de base: ").append(x).append(" * ").append(y).append(" = ").append(x * y).append("\n");
            return x * y;
        } else {
            // partitionner X en a*10^(n/2)+ b
            int n = (int) Math.log10(x) + 1;
            int n2 = n / 2;
            int a = x / (int) Math.pow(10, n2);
            int b = x % (int) Math.pow(10, n2);

            // partitionner Y en c*10^(n/2)+ d
            int c = y / (int) Math.pow(10, n2);
            int d = y % (int) Math.pow(10, n2);

            steps.append("Décomposition: X = ").append(x).append(" -> a = ").append(a).append(", b = ").append(b).append("\n");
            steps.append("Décomposition: Y = ").append(y).append(" -> c = ").append(c).append(", d = ").append(d).append("\n");

            // Calculer les produits intermédiaires
            int ac = m2n1R(a, c);
            int ad = m2n1R(a, d);
            int bc = m2n1R(b, c);
            int bd = m2n1R(b, d);

            // Calculer le résultat final
            int result = (int) (ac * Math.pow(10, 2 * n2) + (ad + bc) * Math.pow(10, n2) + bd);

            steps.append("Résultat intermédiaire: ( ").append(ac).append(" * 10^").append(2 * n2).append(" ) + ( ").append(ad).append(" + ").append(bc).append(" ) * 10^").append(n2).append(" + ").append(bd).append(" = ").append(result).append("\n");

            return result;
        }
    }

    public int m2n2() {
        steps.setLength(0); // Clear steps for m2n2
        return m2n2R(X, Y);
    }

    public int m2n2R(int x, int y) {
        // Si X et Y sont des nombres à un chiffre alors retourner X*Y
        if (x < 10 || y < 10) {
            steps.append("Cas de base: ").append(x).append(" * ").append(y).append(" = ").append(x * y).append("\n");
            return x * y;
        } else {
            // Partitionner X en a*10^(n/2)+ b
            int n = (int) Math.log10(x) + 1;
            int n2 = n / 2;
            int a = x / (int) Math.pow(10, n2);
            int b = x % (int) Math.pow(10, n2);

            // Partitionner Y en c*10^(n/2)+ d
            int c = y / (int) Math.pow(10, n2);
            int d = y % (int) Math.pow(10, n2);

            steps.append("Décomposition: X = ").append(x).append(" -> a = ").append(a).append(", b = ").append(b).append("\n");
            steps.append("Décomposition: Y = ").append(y).append(" -> c = ").append(c).append(", d = ").append(d).append("\n");

            // Calculer les produits intermédiaires
            int ac = m2n2R(a, c);
            int ad = m2n2R(a, d);
            int bc = m2n2R(b, c);
            int bd = m2n2R(b, d);

            // Calculer le résultat final
            int result = (int) (ac * Math.pow(10, 2 * n2) + (ad + bc) * Math.pow(10, n2) + bd);

            steps.append("Résultat intermédiaire: ( ").append(ac).append(" * 10^").append(2 * n2).append(" ) + ( ").append(ad).append(" + ").append(bc).append(" ) * 10^").append(n2).append(" + ").append(bd).append(" = ").append(result).append("\n");

            return result;
        }
    }

    public void Afficher() {
        System.out.println("Multiplication de " + X + " et " + Y + ":");
        System.out.println("--- Étapes pour m2n1 ---");
        m2n1();
        System.out.println(steps.toString());
        System.out.println("Résultat final m2n1: " + m2n1());

        System.out.println("--- Étapes pour m2n2 ---");
        m2n2();
        System.out.println(steps.toString());
        System.out.println("Résultat final m2n2: " + m2n2());
    }

    public static void main(String[] args) {
        Mult2n XY = new Mult2n(1234, 2139);
        XY.Afficher();
    }
}
