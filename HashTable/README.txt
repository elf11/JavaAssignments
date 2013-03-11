Niculaescu Oana
321CB

Tema 4

public class MyHashMapImpl<K, V> implements MyHashMap<K,V>

Clasa ce implementeaza MyHashMap impreuna cu metodele si subclasele aferente acesteia. Declaram o tabela de bucketuri de tipul ArrayList, unde vom introduce un numar maxim de bucketuri stabilit prin constructorul implicit MyHashMapImpl(int numBuckets). Daca numarul de bucketuri, numBuckets, este diferit de zero atunci adaugam in tabela de bucketuri atatea cate sunt cerute.
Implementarea clasei Bucket<K,V> so a metodei ce intoarce intrarile dintr-un bucket si faciliteaza accesul la acestea. Tot aici am implementat si o alta metoda  void add(MyHashMap.Entry<K, V> aux), care adauga o noua intrare intr-un bucket.
Implementarea clasei Entry<K,V> din clasa MyHashMap, constructorul implicit initializeaza un obiect de tip Entry cu atributele key si value. Metodele getKey() si getValue() ce intorc cheia si valoarea asociate unui Entry din tabela de bucketuri.Suprascriere a metodei boolean equals(Object o), din clasa Object, prin care se verifica validitatea unei intrari.

Implementarea metodei ce intoarce o anumita valoare asociata unei key date din map, daca tabela este nula atunci cheia nu exista in map, altfel aflam indicele din tabela la care se gaseste cheia cautata, si pentru fiecare intrare din bucketul cu cheia asociata comparam valoarea cheii cautate cu ce valoarea cheii intrarii si intoarcem valoarea de la aceea cheie.

Implementarea metodei ce introduce la o anumita cheie o valoare asociata, aflam indicele din tabela unde vrem sa introducem noua intrare, daca la indicele respectiv nu avem niciun bucket atunci initializam un nou bucket si il introducem in tabela si crestem dimensiunea acesteia.Altfel parcurgem lista, gasim cheia pe care o cautam si introducem valoarea la cheia corespunzatoare si intoarcem vechea valoare corespunzatoare cheii.Crestem dimensiunea tabelei.

Implementarea metodei de indepartare a unei intrari din tabela, daca tabela este goala atunci intoarcem null, altfelparcurgem entry'urile din bucketul la care se gasete cheia cautata si comparam cheia fiecarui bucket cu cheia cautata, pana o gasim, salvam valoare de la aceasta intrare, scoatem intrarea din bucket si intoarcem valoare, scazand dimensiune atabelei.

Implementarea metodei ce intoarce tabela de bucketuri impreuna cu implementarea acesteia, daca tabela este goala atunci intoarcem null, altfel intoarcem tabela.

Implementarea metodei ce intoarce functia de translatare asociata unei anumite chei, implementata asa cum s-a sugerat in cerinta  translate(hashCode) = |hashCode| % numBuckets, daca numarul rezultat este negativ atunci acesta se neaga si se aproximeaza prin adaos prin adunarea cu 1. 


ANALIZA

Implementarea clasei Student, crearea unui constructor implicit care instantiaza obiecte de tipul Student, cu atibutele nume si varsta primite ca parametru. Suprascrierea metodei de hashCode asa cum a fost sugerata in implementare, folosing valoarea nume a studentului ca si variabila, suprascrierea metodei boolean equals(Object o) care intoarce true daca numele si varsta studentului corespund, altfel intoarce false.

Implementarea clasei LazyStudent ce implementeaza clasa Student, crearea unui constructor implicit ce apeleaza constructorul din clasa Student si initiaza un obiect de tipul LazyStudent, precum si suprascrierea metodei de translatare, a metodei hashCode, care intoarce in orice situatie zero, adica tabela noastra de bucketuri va avea o singura intrare.

MainClass.java

Implementarea clasei principale, declaram o tabela de bucketuri de tipul ArrayList<Student> si o tabela de bucketuri de tipuri ArrayList<LazyStudent> care vor fi populate cu obiecte de tip Student si LazyStudent.
Se genereaza random un numar intre 10 si 35 care sa reprezinte lungimea numelui studentului, numele studentului este din maxim r litere, unde r este un numar generat aleator intre 10-35, fiecare litera este generata aleator si concatenata la un string. Varsta studentului este generata aleator, fiind un numar intre 17 si 33. Dupa geneararea acestor numere se introduce in lista Studentul impreuna cu numele si varsta sa. Generam aleator o nota intre 0-10 si nota impreuna cu un student sunt introdusi in map.
Acelasi algoritm este urmat si pentru LazyStudent si mapLazy.

Facem un numar foarte mare de accesari atat pentru Student cat si pentru LazyStudent si observam ca timpul de acces pentru Student este mult mai mic decat cel pentru LazyStudent, deoarece metoda hashCode() este mult mai bine definita in clasa Student decat in LazyStudent, pentru student fiecare noua intrare este introdusa intr-un bucket aferent, pentru LazyStudent codul de translatare intoars de hashCode() este 0 pentru orice noua intrare din map, deci nu vom avea decat un singur bucket unde se introduc toate intrarile una dupa cealalta si este ca si cum am cauta o valoare intr-o lista, fara a ne mai folosi de chei. Timpul pentru LazyStudent trebuie sa fie aproximativ egal cu numBuckets*timpul accesarii Student.