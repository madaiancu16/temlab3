package exercitiul2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainAppex1 {

    private static List<Produs> produse = new ArrayList<>();

    public static void main(String[] args) {
        citirefisier("src/exercitiul2/Produs.csv");

        Scanner scanner = new Scanner(System.in);
        int optiune;

        do {
            System.out.println("\nMeniu:");
            System.out.println("1. Afișare toate produsele");
            System.out.println("2. Afișare produse expirate");
            System.out.println("3. Vânzare produs");
            System.out.println("4. Afișare produse cu preț minim");
            System.out.println("5. Salvare produse cu cantitate mică");
            System.out.println("6. Ieșire");
            System.out.print("Alege o opțiune: ");
            optiune = scanner.nextInt();

            switch (optiune) {
                case 1:
                    afisare();
                    break;
                case 2:
                    afisareexp();
                    break;
                case 3:
                    vandut(scanner);
                    break;
                case 4:
                    afisarepretminim();
                    break;
                case 5:
                    salvarecantitatemica(scanner);
                    break;
                case 6:
                    System.out.println("Program încheiat.");
                    break;
                default:
                    System.out.println("Opțiunea dvs nu este valida!!!.");
            }

        } while (optiune != 6);
    }

    private static void citirefisier(String fisier) {
        try (BufferedReader br = new BufferedReader(new FileReader(fisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] valori = linie.split(",");
                String denumire = valori[0];
                double pret = Double.parseDouble(valori[1]);
                int cantitate = Integer.parseInt(valori[2]);
                LocalDate dataExpirarii = LocalDate.parse(valori[3]);

                Produs produs = new Produs(denumire, pret, cantitate, dataExpirarii);
                produse.add(produs);
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }

    private static void afisare() {
        for (Produs produs : produse) {
            System.out.println(produs);
        }
    }

    private static void afisareexp() {
        LocalDate astazi = LocalDate.now();
        for (Produs produs : produse) {
            if (produs.getDataExpirare().isBefore(astazi)) {
                System.out.println(produs);
            }
        }
    }

    private static void vandut(Scanner scanner) {
        System.out.print("Introduceți denumirea produsului: ");
        String denumire = scanner.next();

        System.out.print("Introduceți cantitatea de vândut: ");
        int cantitateDeVandut = scanner.nextInt();

        for (int i = 0; i < produse.size(); i++) {
            Produs produs = produse.get(i);

            if (produs.getDenumire().equals(denumire)) {
                if (produs.getCantitate() >= cantitateDeVandut) {
                    produs.setCantitate(produs.getCantitate() - cantitateDeVandut);
                    Produs.incasari += cantitateDeVandut * produs.getPret();

                    System.out.println("Produs vândut! Încasări actuale: " + Produs.incasari);

                    if (produs.getCantitate() == 0) {
                        produse.remove(i);
                    }
                    return;
                } else {
                    System.out.println("Cantitate insuficientă.");
                    return;
                }
            }
        }

        System.out.println("Produsul nu a fost găsit.");
    }

    private static void afisarepretminim() {
        if (produse.isEmpty()) {
            System.out.println("Nu există produse.");
            return;
        }

        double pretMinim = produse.get(0).getPret();
        for (Produs produs : produse) {
            if (produs.getPret() < pretMinim) {
                pretMinim = produs.getPret();
            }
        }

        for (Produs produs : produse) {
            if (produs.getPret() == pretMinim) {
                System.out.println(produs);
            }
        }
    }

    private static void salvarecantitatemica(Scanner scanner) {
        System.out.print("Introduceți cantitatea maximă: ");
        int cantitateMaxima = scanner.nextInt();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/exercitiul2/salvareProdus.txt"))) {
            for (Produs produs : produse) {
                if (produs.getCantitate() < cantitateMaxima) {
                    bw.write(produs.toString());
                    bw.newLine();
                }
            }
            System.out.println("Produsele au fost salvate în fișier.");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea fișierului: " + e.getMessage());
        }
    }
}
