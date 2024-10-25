package exercitiul1;

public class Cl_parabola {
    private final int a;
    private final int b;
    private final int c;

    public Cl_parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Cl_parabola(Cl_parabola p) {
        this(p.a, p.b, p.c);
    }

    @Override
    public String toString() {
        return "f(x) = " + a + "x^2 + " + b + "x + " + c;
    }
    public double[] Coordonate_varf() {
        double x_varf = -b / (2.0 * a);
        double y_varf = (a * x_varf * x_varf) + (b * x_varf) + c;
        return new double[]{x_varf, y_varf};
    }
    public static double[] mijloc_static(Cl_parabola p1, Cl_parabola p2) {
        double[] varf1 = p1.Coordonate_varf();
        double[] varf2 = p2.Coordonate_varf();

        double mijloc_x = (varf1[0] + varf2[0]) / 2;
        double mijloc_y = (varf1[1] + varf2[1]) / 2;

        return new double[]{mijloc_x, mijloc_y};
    }
    public static double lungime_dreapta_static(Cl_parabola p1, Cl_parabola p2) {
        double[] varf1 = p1.Coordonate_varf();
        double[] varf2 = p2.Coordonate_varf();

        return Math.hypot(varf1[0] - varf2[0], varf1[1] - varf2[1]);
    }
    public double[] mijloc(Cl_parabola p) {
        double[] varf_curent = this.Coordonate_varf();
        double[] varf_alt = p.Coordonate_varf();

        double mijloc_x = (varf_curent[0] + varf_alt[0]) / 2;
        double mijloc_y = (varf_curent[1] + varf_alt[1]) / 2;

        return new double[]{mijloc_x, mijloc_y};
    }
    public double lungime_dreapta(Cl_parabola p) {
        double[] varf_curent = this.Coordonate_varf();
        double[] varf_alt = p.Coordonate_varf();

        return Math.hypot(varf_curent[0] - varf_alt[0], varf_curent[1] - varf_alt[1]);
    }
}
