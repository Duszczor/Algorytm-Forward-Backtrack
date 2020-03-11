public class Main {

    public static void main(String[] args) {



        ForwardKrolowe n_queens = new ForwardKrolowe(28);

        long start=System.currentTimeMillis();
        System.out.println("Forward Krolowe:");
        n_queens.uruchomForward();
        //n_queens.wyswietl();
        System.out.println(n_queens.liczbaPetli);
        long stop=System.currentTimeMillis();
        System.out.println("Czas wykonania (w milisekundach): "+(stop-start));


//
//        ForwardKwadrat kwadrat = new ForwardKwadrat(19);
//        start=System.currentTimeMillis();
//        kwadrat.uruchomForward();
//        System.out.println("Forward Kwadrat");
//        //kwadrat.wyswietl();
//        stop=System.currentTimeMillis();
//        System.out.println(kwadrat.liczbaPetli);
//        System.out.println("Czas wykonania (w milisekundach): "+(stop-start));

    }
}
