package exercitiul1;

import java.io.*;

public class Parabola {
    public static void main(String[] args) throws IOException {
        int val_a = 0, val_b = 0, val_c = 0, k = 0;
        String linie;
        Cl_parabola p1 = null;
        Cl_parabola p2 = null;

        BufferedReader add = new BufferedReader(new InputStreamReader(new FileInputStream("src/exercitiul1/in.txt")));

        while ((linie = add.readLine()) != null) {
            k++;
            if (k % 3 == 1)
                val_a = Integer.parseInt(linie);
            else if (k % 3 == 2)
                val_b = Integer.parseInt(linie);
            else {
                val_c = Integer.parseInt(linie);
                if (k <= 3)
                    p1 = new Cl_parabola(val_a, val_b, val_c);
                else
                    p2 = new Cl_parabola(val_a, val_b, val_c);
            }
        }

        add.close();

        System.out.println(p1.toString());
        System.out.println(p2.toString());


        System.out.println("Vârful parabolei 1: (" + p1.Coordonate_varf()[0] + ", " + p1.Coordonate_varf()[1] + ")");
        System.out.println("Vârful parabolei 2: (" + p2.Coordonate_varf()[0] + ", " + p2.Coordonate_varf()[1] + ")");


        double[] mijloc = Cl_parabola.mijloc_static(p1, p2);
        System.out.println("Mijlocul segmentului dintre vârfuri: (" + mijloc[0] + ", " + mijloc[1] + ")");


        double lungime = Cl_parabola.lungime_dreapta_static(p1, p2);
        System.out.println("Lungimea segmentului dintre vârfuri: " + lungime);

        double[] mijloc_non_static = p1.mijloc(p2);
        System.out.println("Mijlocul segmentului: (" + mijloc_non_static[0] + ", " + mijloc_non_static[1] + ")");

        double lungime_non_static = p1.lungime_dreapta(p2);
        System.out.println("Lungimea segmentului: " + lungime_non_static);
    }
}
