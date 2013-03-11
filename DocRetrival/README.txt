Clasa Node<N>

Clasa implementeaza nodurile arborelui, construindu-le. Initial avem constructori cu doi parametri si cu un parametru. Nodurile arborelui contin un camp ce retine caracterul codificat si un altul care retine frecventa. Aceste campuri sunt initializate pe rand in cadrul celor doi constructori.Setam metodele setter/getter pentru campurile caracter si frecventa, precum si pentru nodurile drept si stang, nedorind sa acordam privilegii, altor utilizatori, asupra obiectelor. Utilizam getter si setter pentru a impiedica userul sa schimbe starea Obiectului in orice alt mod decat prin intermediul metodelor.


Clasa Memorare


Clasa implementeaza un obiect cu doua campuri, caracter si cod, in care vom retine mai  tarziu codul binar al caracterului precum si simbolul acestuia. Avem si aici doua tipuri de constructori, cu si fara parametrii, prin intermediul carora initializam campurile obiectului: caracter si cod.


Class ListaSortata

Se implementeaza lista sortata si metodele ei pe care le vom folosi la construirea arborelui Huffman. Am ales sa folosesc clasa generica ArrayList deoarece are deja implementate unele metode si este mai usor de creat o lista sortata. Construim o lista sortata. Avem o metoda ce ne spune daca lista este goala sau nu si ne intoarce rezultat boolean,1/0. Avem metoda de adaugare a unui nou nod in lista: aflam lungimea listei, daca lista este goala, atunci adaugam un nod la capul de lista, altfel inseram noul nod acolo unde ii este locul, facand intr-un fel o sortare(ineficienta) prin insertie; decidem unde trebuie sa inseram noul nod in functie de frecventele fiecarui nod aflat deja in lista. Implementem o metoda de scoatere a nodurilor din lista, metoda facilitata de clasa generica ArrayList pe care am utilizat-o la declararea listei sortate. Implementam o metoda de afisare a intregii liste si apoi intoarcem lista sortata.


Class HuffmanT

Clasa implementeaza un arbore Huffman; avem initial un connstructor fara parametrii care creeaza un nou arbore Huffman pornind de la un nod. Metoda Arbore construieste 
un nou arbore huffman pornind de la lista sortata. Atat timp cat lista sortata nu este 
goala cream un nou nod drept si un nou nod stang, extragem din lista capul de lista, apoi setam referintle de la nodul parinte la nodurile copii in arbore.

Implementam o metoda care gaseste un caracter in arbore, metoda primeste ca parametri caracterul cautat si nodul de unde se incepe cautarea in arbore, cat timp nu s-a ajuns inca la un nod fara fii, comparam caracterul cautat cu symbolul(caracterul) retinut in  arbore la nodul curent. Daca a fost gasit caracterul in arbore atunci intoarcem adevarat, altfel intoarcem fals.

Metoda care returneaza codul binar al caracterului primeste ca parametrii nodul de unde se incepe salvarea codului binar, caracterul ce se doreste codificat si un sir cod ce va reprezenta codul final al caracterului codificat. Metoda este implementata recursiv: cat timp nu am ajuns inca la un nod terminal, cautam caracterul pe ramura dreapta si atunci codul binar al acestuia se concateneaza cu '1', altfel il cautam pe ramura stanga si atunci codul binar al caracterului se concateneaza cu '0'. Cand ajungem la un nod final intoarcem codul binar ce doream sa-l aflam.


Class Main

Clasa Main realizeaza cele doua functii principale ale programului cea de codificare si cea de decodificare. Implementeaza o singura metoda "public static void main", metoda ce primeste un argument de la intrarea standard si in functie de acest argument va executa portiunea de cod necesara codificarii sau decodificarii. Citirea datelor de intrare se va face de la intrarea standard, de aceea utilizam clasa Scanner.

Codificare se realizeaza daca parametrul trimis ca argument este 'c'. Citim fiecare noua linie introdusa la intrarea standard pana cand intalnim o linie ".". Creeam un obiect de tipul ArrayList temporar la care adaugam sirul citit de la intrarea standard. Calculam frecventele fiecarui element din aceast ArrayList si apoi in functie de acestea cream o lista sortata. Construim un nou arbore Huffman pe baza listei sortate, apoi parcurgem arborele si printam codul ascii al fiecarui caracter precum si codificarea binara a acestuia, rezultata in urma parcurgerii arborelui. In urma aflarii codificarii binare a fiecarui caracter, textul citit initial la intrarea standard va fi acum codificat in binar si apoi afisat pe ecran.

Procesul de decodificare incepe numai daca argumentul primit la executarea programului este 'd'. Citim fiecare linie primita de la intrarea standard si retinem simbolul(caracterul) precum si codificarea binara a acestuia. Citirea se realizeaza pana la intalnirea unei linii punct "." . Citim apoi textul de decodificat si cu ajutorul codificarilor memorate anterior il decodificam, salvandu-l intr-un string care mai apoi va fi afisat la iesirea standard.