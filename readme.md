#
# Programowanie z ograniczeniami

### 1.Problem N hetmanów

Należy ustawić N hetmanów na tablicy w ten sposób, by każdy z hetmanów nie atakował innego hetmana. Hetman atakuje inną figurę, jeżeli figura znajduje się na przekątnej, bądź na prostej, na której znajduje się hetman.

### 2.Kwadrat łaciński

Kwadrat łaciński rzędu n jest to macierz kwadratowa L: NxN o elementach pochodzących z n-elementowego zbioru liczb S: {1, 2, …, n}, dla której zachodzi własność, taka że w każdym wierszu oraz kolumnie takiej macierzy dany element występuje wyłącznie raz. Kwadrat łaciński można również przedstawić w formie kolorowej kraty o wymiarze NxN, której kolory odpowiadają elementom macierzy spełniając przy tym narzucone ograniczenia. Zadanie sprowadza się do rozwiązania problemu wyznaczenia kwadratu łacińskiego rzędu N: {2, 3, …, k}, rozpoczynając od macierzy zerowej (pustej kraty):

Algorytmy Backtracking implementowałem interacyjnie, natomiast Forward Checking rekurencyjnie. Zastosowałem do tych algorytmów 2 heurystyki wyboru zmiennej : jedna to normalna, czyli po kolei kolejne możliwe wartości a druga to losowa, czyli losuje kolejną możliwą wartość. Warunkiem poprawnego skończenia metody jest znalezienie pierwszego napotkanego dobrego rozwiązania.

#
# Wnoski

Widać, że wybór heurystyki jest bardzo istotny w powyższych algorytmach. Wybierając heurystykę losową zamiast normalnej można otrzymać co najmniej kilkukrotnie lepsze wartości czasów wykonywania metody. Następną rzeczą jaką widać to, że algorytm Forward checking jest wydajniejszy i szybszy od algorytmu Backtracking. Widać, że w większości przypadków wyniki dla heurystyki losowej rosną analogicznie do wyników dla heurystyki normalnej. Wyniki musiałem mierzyć od jakiegoś konkretnego N, ponieważ dla zbyt małej wartości, wyniki dla czasów wychodziły mi zerowe. Na wykresach musiałem użyć skali logarytmicznej, ponieważ w innym wypadku nie dokładnie były by widoczne wyniki.