TEMA 2 - APD
MAP-REDUCE
NICULAESCU OANA 331CB


-> compilare:
javac Main.java

-> rulare:
java Main numarThreaduri fisin fisout

Pentru TheadPool-uri am folosit Executors.newFixedThreadPool(nrOfThreads),
am folosit un singur thread de acest tip, Executor, deoarece atunci cand trec
de la o etapa la alta astept deja ca munca din etapa precedenta sa se fi
incheiat deja. 
Threadul master imparte fiecare document in fragmente de fragmentSize dimensiune
si le trimite workerilor. In interiorul unui fragment am realizat parsarea
cuvintelor cu ajutorul Regexurilor, clasa implementata deja in Java. In urma
operatiei de mapping ne este intors un Map<String, Map<String, Integer>>, 
prin intermediul obiectelor de tip Future<...>.
Prima operatie de reduce : ReduceFirst - master thread ofera taskuri de reducere - se aduna frecventele cuvintelor din vectorii obtinuti pentru fiecare fragment
dintr-un document, se pastreaza primele N cuvinte cu cele mai mari frecvente pentru fiecare document
Map<String, Map<String, Float>> topWords. Avem o operatie de transformare a mapului
in lista, pe care mai apoi o sortam in ordinea frecventei cuvintelor si salvam numai
primele mostFreq cuvinte cautate in topWords.
A doua operatie de reducere(ReduceSecond) ajuta la determinarea documentelor care 
contin in vectorul de cuv cu frecvente maxime de aparitie, toate cele NC cuvinte primite
dupa care se face cautarea. Documentele disponibile sunt trimise workerilor si rezultatele
sunt puse intr-un futureMap. In SecondReduce parcurgem pentru fiecare document mapul
corespondent al celor mai frecvente cuvinte cautate si daca toate cuvintele cautate
se gasesc aici atunci afisam numele fisierului precum si frecventa pentru fiecare 
dintre cuvintele cautate din acest fisier.

NOTA : Pentru exemplul oferit de voi nu am reusit sa obtin rezultatele din fisierul de iesire,
deoarece cel putin pentru primul fisier doc1.txt cuvantul cautat "data" nu se gasea
in primele 12 cuvinte ca frecventa. Am adaugat in arhiva uploadata pe site si un fisier
txt (doc1_word_freq.txt) cu frecventele cuvintelor in ordine crescatoare asa cum imi sunt ele intoarse mie.
"data" s-ar afla in primele 12 cuvinte daca am ignora cuvintele cu lungime mai mica 
de 3 caractere, dar cum enuntul temei spune ca un cuvant poate avea orice lungime acest
lucru nu este posibil.