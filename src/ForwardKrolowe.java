import java.util.ArrayList;
import java.util.Random;

public class ForwardKrolowe {


    private int n; //number of queens
    private ArrayList<Integer> pozycjeKrolowej;      //index = kolumna, wartosc = wiersz
    private int aktualnaKolumna;
    private ArrayList<ArrayList<Integer>> dziedziny;
    private boolean czyKoniec;
    int liczbaPetli = 0;


    public ForwardKrolowe(int n) {
        this.n = n;
        start();
    }

    public void start() {        //Zresetuj zmienne
        pozycjeKrolowej = new ArrayList<>(n);
        czyKoniec = false;
        aktualnaKolumna = 0;
    }

    private void stworzDziedziny() { //buildDomains              //stworz liste n list o wartosciach od 0 do n
        dziedziny = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            dziedziny.add(getPelnaDziedzina());
        }
    }

    private ArrayList<Integer> getPelnaDziedzina() { //getFullDomain         // stworz liste o wartosciach od 0 do n
        ArrayList<Integer> pelnaDziedzina = new ArrayList<>(n); //fullDomain
        for (int i = 0; i < n; i++) {
            pelnaDziedzina.add(i);
        }
        return pelnaDziedzina;
    }

    public boolean czyJakasDziedzinaPusta(ArrayList<ArrayList<Integer>> dziedziny) { //heurystyka        //Czy dzidzina ktoregos wiersza/kolumny jest pusta
        for (ArrayList dziedzina : dziedziny) {
            if (dziedzina.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<ArrayList<Integer>> getKopiaDziedzin(ArrayList<ArrayList<Integer>> dziedziny) {
        ArrayList<ArrayList<Integer>> noweDziedziny = new ArrayList<>(n);
        for (ArrayList<Integer> dziedzina : dziedziny) {
            ArrayList<Integer> nowaDziedzina = new ArrayList<>(n);
            nowaDziedzina.addAll(dziedzina);
            noweDziedziny.add(nowaDziedzina);
        }
        return noweDziedziny;
    }

    private ArrayList<Integer> getKopiaPozycjiKrolowych(ArrayList<Integer> pozycjeKrolowych) {
        ArrayList nowePozycjeKrolowych = new ArrayList<>(pozycjeKrolowych.size());
        nowePozycjeKrolowych.addAll(pozycjeKrolowych);
        return nowePozycjeKrolowych;
    }

    private void usunZDziedzin(ArrayList<ArrayList<Integer>> dziedziny, int kolumna, int wiersz) {
        Integer numerWiersza = wiersz;
        int zmiennaPrzekatna = 1;
        ArrayList<Integer> aktualnaKolumna;

        for (int i = kolumna + 1; i < n; i++) { //skip current column
            aktualnaKolumna = dziedziny.get(i);
            aktualnaKolumna.remove(numerWiersza);

            if (wiersz + zmiennaPrzekatna < n) {
                Integer przekatna = wiersz + zmiennaPrzekatna;
                aktualnaKolumna.remove(przekatna);
            }
            if (wiersz - zmiennaPrzekatna >= 0) {
                Integer przekatna = wiersz - zmiennaPrzekatna;
                aktualnaKolumna.remove(przekatna);
            }
            zmiennaPrzekatna++;
        }
    }

    public void uruchomForward() {
        start();
        stworzDziedziny();
        forward(dziedziny, new ArrayList<>(n), 0);
    }

    public void forward(ArrayList<ArrayList<Integer>> dziedziny, ArrayList<Integer> krolowe, int kolumna) {
        if (krolowe.size() == n) {
            czyKoniec = true;
            pozycjeKrolowej = krolowe;
        } else {
            Random generator = new Random();

            Integer wiersz;
            ArrayList<Integer> wartosciDoUzycia = dziedziny.get(kolumna);
            for (int i = 0; i < wartosciDoUzycia.size() && !czyKoniec && !czyJakasDziedzinaPusta(dziedziny); i++) {
                liczbaPetli++;
                //wiersz = wartosciDoUzycia.get(i);    //HEURYSTYKA
                wiersz = wartosciDoUzycia.get(generator.nextInt(wartosciDoUzycia.size()));
                ArrayList<ArrayList<Integer>> noweDziedziny = getKopiaDziedzin(dziedziny);
                ArrayList<Integer> noweKrolowe = getKopiaPozycjiKrolowych(krolowe);
                usunZDziedzin(noweDziedziny, kolumna, wiersz);
                noweKrolowe.add(wiersz);
                forward(noweDziedziny, noweKrolowe, kolumna + 1);
            }
        }
    }

    public void wyswietl() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == pozycjeKrolowej.get(i)) {
                    System.out.print("X ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println("");
        }
    }


}
