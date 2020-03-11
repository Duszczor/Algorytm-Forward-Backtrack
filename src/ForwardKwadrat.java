import java.util.ArrayList;
import java.util.Random;

public class ForwardKwadrat {


    private int n;
    private int[][] kwadrat;
    private ArrayList<Integer> dziedzina;
    private int aktualnyWiersz;
    private int aktualnaKolumna;
    private boolean czyKoniec;
    int liczbaPetli = 0;


    public ForwardKwadrat(int n) {
        this.n = n;
        start();
    }

    public void start() {
        kwadrat = new int[n][n];
        stworzDziedzine();
        aktualnaKolumna = 0;
        aktualnyWiersz = 0;
        czyKoniec = false;
    }

    private void stworzDziedzine() {   //buildDomain         // lista od 1 do n
        dziedzina = new ArrayList<>(n);
        for (int i = 1; i < n + 1; i++) {
            dziedzina.add(i);
        }
    }

    public boolean czyUnikalneWWierszuIKolumnie(int kolumna, int wiersz, int wartosc, int[][] kwadrat) {  //isUniqueInRowNadColumn
        boolean czyUnikalne = true;
        for (int i = 0; i < n && czyUnikalne; i++) { //sprawdz wiersz
            if (i != kolumna)
                czyUnikalne = kwadrat[i][wiersz] != wartosc;
        }
        for (int i = 0; i < n && czyUnikalne; i++) { //sprawdz kolumne
            if (i != wiersz)
                czyUnikalne = kwadrat[kolumna][i] != wartosc;
        }
        return czyUnikalne;
    }

    private int[][] skopiujKwadrat(int[][] kwadrat) {     //copySquare
        int[][] nowyKwadrat = new int[kwadrat.length][kwadrat.length];
        for (int i = 0; i < kwadrat.length; i++) {
            for (int j = 0; j < kwadrat.length; j++) {
                nowyKwadrat[i][j] = kwadrat[i][j];
            }
        }
        return nowyKwadrat;
    }

    public void wyswietl() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(kwadrat[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void uruchomForward() {      //runForwardChecking
        start();
        ArrayList<Integer> dziedzina = new ArrayList<>(n);
        for (int i = 1; i < n + 1; i++) {
            dziedzina.add(i);
        }
        forward(0, 0, dziedzina, new int[n][n]);
    }

    private void forward(int wewAktualnyWiersz, int wewAktualnaKolumna, ArrayList<Integer> wewDziedzina, int[][] kwadrat) {
        if (!czyKoniec) {
            int wartoscZDziedziny;
            for (int j = 0; j < wewDziedzina.size() && !czyKoniec; j++) {
                Random generator = new Random();
                liczbaPetli++;
                //wartoscZDziedziny = wewDziedzina.get(j);  //HEURYSTYKA
                wartoscZDziedziny = wewDziedzina.get(generator.nextInt(wewDziedzina.size()));
                if (czyUnikalneWWierszuIKolumnie(wewAktualnaKolumna, wewAktualnyWiersz, wartoscZDziedziny, kwadrat) && !czyKoniec) {

                    ArrayList<Integer> nowaDziedzina = new ArrayList<>();
                    int[][] noweKwadrat = skopiujKwadrat(kwadrat);
                    int nowyAktualnyWiersz = wewAktualnyWiersz;
                    int nowaAktualnaKolumna = wewAktualnaKolumna;

                    noweKwadrat[wewAktualnaKolumna][wewAktualnyWiersz] = wartoscZDziedziny;

                    if (nowyAktualnyWiersz == n - 1) {
                        if (nowaAktualnaKolumna == n - 1) {
                            czyKoniec = true;
                            this.kwadrat = noweKwadrat;
                        } else {
                            nowaAktualnaKolumna++;
                            nowyAktualnyWiersz = 0;
                        }
                    } else {
                        nowyAktualnyWiersz++;
                    }

                    for (int i = 1; i < n + 1; i++) {
                        nowaDziedzina.add(i);
                    }
                    for (int i = 0; i < nowyAktualnyWiersz; i++) { //usun z wiersza
                        nowaDziedzina.remove(new Integer(noweKwadrat[nowaAktualnaKolumna][i]));
                    }
                    for (int i = 0; i < nowaAktualnaKolumna; i++) { //usun z kolumny
                        nowaDziedzina.remove(new Integer(noweKwadrat[i][nowyAktualnyWiersz]));
                    }

                    if (!nowaDziedzina.isEmpty()) {
                        forward(nowyAktualnyWiersz, nowaAktualnaKolumna, nowaDziedzina, noweKwadrat);
                    }
                }
            }
        }

    }
}
