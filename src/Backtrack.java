import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Backtrack {

    static int liczbaPetliHetmany = 0;
    static int liczbaPetliKwadrat = 0;


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        setHetmansBacktrack(5);
        System.out.println(liczbaPetliHetmany);
        long stop = System.currentTimeMillis();
        System.out.println("Czas wykonania (w milisekundach): " + (stop - start));

        start = System.currentTimeMillis();
        //setKwadratBacktrack(4);
        System.out.println(liczbaPetliKwadrat);
        stop = System.currentTimeMillis();
        System.out.println("Czas wykonania (w milisekundach): " + (stop - start));
    }


    public static boolean hetmany(int tab[][], int size) {

        List<Para> hetmani = new ArrayList<Para>();
        boolean wynik = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tab[i][j] != 0) {
                    Para para = new Para();
                    para.numer1 = i;
                    para.numer2 = j;
                    hetmani.add(para);
                }
            }
        }

        for (int i = 0; i < hetmani.size(); i++) {
            for (int j = 0; j < hetmani.size(); j++) {
                if (i != j) {
                    if (hetmani.get(i).numer1 == hetmani.get(j).numer1 || hetmani.get(i).numer2 == hetmani.get(j).numer2 ||
                            ((hetmani.get(i).numer1 + hetmani.get(i).numer2)) == ((hetmani.get(j).numer1 + hetmani.get(j).numer2)) || (hetmani.get(i).numer1 - hetmani.get(i).numer2 ==
                            hetmani.get(j).numer1 - hetmani.get(j).numer2)) {
                        System.out.println("Hetman: (" + hetmani.get(i).numer1 + ", " + hetmani.get(i).numer2 + " ) oraz Hetman: (" + hetmani.get(j).numer1 + ", " + hetmani.get(j).numer2 + " )" + " sie bija");
                        wynik = true;
                    }
                }
            }
        }

        return wynik;
    } //Tablica zer i hetmany na jedynkach

    public static boolean kwadrat(int tab[][], int size) {

        List<Integer> lista = new ArrayList<Integer>();
        boolean jest = true;

        for (int i = 0; i < size; i++) {
            lista.clear();          //Poziom
            for (int j = 0; j < size; j++) {
                if (!lista.contains(tab[i][j])) {
                    if (tab[i][j] != 0) {
                        lista.add(tab[i][j]);
                    }
                } else {
                    jest = false;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            lista.clear();          //Pion
            for (int j = 0; j < size; j++) {
                if (!lista.contains(tab[j][i])) {
                    if (tab[j][i] != 0) {
                        lista.add(tab[j][i]);
                    }
                } else {
                    jest = false;
                }
            }
        }


        return jest;
    }  //Tablica zer i liczby na miejscach

    public static void setHetmansBacktrack(int size) {

        int[][] wyniki = new int[size][size];
        List<Integer> zajeteKolumny = new ArrayList<Integer>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                wyniki[i][j] = 0;
            }
        }
        wyniki[0][0] = 1;  // PIERWSZY HETMAN
        zajeteKolumny.add(0);


        for (int i = 1; i < size; i++) { // PO KOLEI WIERSZE
            boolean dodany = false;
            for (int j = 0; j < size && !dodany; j++) {     //SPRAWDZENIE CZY HETMANY SIE BIJA

//                Random generator = new Random();
//                j = generator.nextInt(size); //HEURYSTYKA
                liczbaPetliHetmany++;
                if (zajeteKolumny.size() > i) {             // JESLI JUZ BYL HETMAN TO PRZESTAWIAMY NA POLE DALEJ
                    wyniki[i][zajeteKolumny.get(i)] = 0;
                    j = zajeteKolumny.get(i) + 1;
                    zajeteKolumny.remove(zajeteKolumny.size() - 1);
                }
                if (j < size) {
                    wyniki[i][j] = 1;
                    if (hetmany(wyniki, size)) { //TRUE == ZLE
                        wyniki[i][j] = 0;
                    } else {
                        dodany = true;
                        zajeteKolumny.add(j);
                        //System.out.println("PETLA "+zajeteKolumny);
                    }
                }
            }
            if (!dodany) {
                i = i - 2;
            }

        }
        System.out.println(zajeteKolumny);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (wyniki[i][j] == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println("");
        }
    }

    public static void setKwadratBacktrack(int size) {

        int[][] wyniki = new int[size][size];

        for (int i = 0; i < size; i++) { //UZUPELNIANIE KWADRATU ZERAMI
            for (int j = 0; j < size; j++) {
                wyniki[i][j] = 0;
            }
        }

        wyniki[0][0] = 1;
        boolean dodane = false;
        int staraWartosc = 0;


        for (int i = 0; i < size; i++) {  //LECIMY WIERSZAMI

            for (int j = 0; j < size; j++) {

                for (int t = 1; t <= size && !dodane; t++) {
//                    Random generator = new Random();
//                    t = generator.nextInt(size+1)+1; //HEURYSTYKA
                    liczbaPetliKwadrat++;
                    if (staraWartosc != 0) {
                        t = staraWartosc + 1;
                        staraWartosc = 0;
                    }

                    if (t <= size) {
                        wyniki[i][j] = t;
                        if (!kwadrat(wyniki, size)) {
                            wyniki[i][j] = 0;
                        } else {
                            dodane = true;
                        }
                    }
                }   // for - sprawdzanie czy moze dodac liczbe jak tak to dodane = true

                if (!dodane) {
                    staraWartosc = wyniki[i][j - 1];
                    j = j - 2;
                }
                dodane = false;
            }
        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(wyniki[i][j] + " ");
            }
            System.out.println("");
        }

    }


}
