Niculaescu Oana 
321CB
Tema 2

Clasa AbstractGeneral
Clasa abstracta ce implmenteaza Comparable<AbstractGeneral> si in care sunt definite principalele metode de lucru atat cu Foldere cat si cu Fisiere.Fiecare entitate AbstractGeneral are un nume, un tip, un user owner, un grup al ownerului si permisiuni.
Clasa implementeaza o serie de metode abstracte ce vor fi apoi extinse in clasele Folder si Fisier, aceste metode inlesnind lucrul cu directoare si fisiere. Constructorul AbstractGeneral va initializa atat directoarele cat si fisierele, continutul primelor fiind intotdeauna null, iar al celor din urma depinzand de tipul acestora.Clasa implementeaza o metoda de setare a permisiunilor, toate obiectele de tip AbstractGeneral avand la inceput permisiuni de read, write, execute pentru owner si grupul default al acestuia.Este implementata si o metoda de setare a permisiunilor ce va fi utilizata la executia metodei chmod din clasa Action. Sunt implementate metode de getter si settter pentru numele userului ce detine obiectul, numele grupuil default, tipul obiectului.

Clasa Folder
Clasa ce extinde clasa abstracta AbstractGeneral si implementeaza metodele specifice acesteia.Astfel se realizeaza metodele specifice Folderelor din sistem.Se implementeaza un sistem de fisiere de utilizand TreeSet si fiecare Folder are un continut de acest tip pentru o parcurgere eficienta a acestora.
Clasa implementeaza constructorul abstract gasit in clasa AbstractGeneral ducand astfel la initializarea fiecarui nou Folder nou creat.Sunt implementate si metode de parcurgere a structurii de foldere si de aflare a continutului acestora, precum si de adaugare si eliminare a folderelor din structura de fisiere.

Clasa Fisier
Clasa ce extinde clasa abstracta AbstractGeneral si implementeaza toate metodele acesteia intr-un mod in carelucrul cu fisiere sa fie favorizat.Fiecare fisier are un continut(content) si un tip(type),cel din urma fiind unul dintre tipurile definit in clasa Main.Clasa ce implementeaza constructorul abstract din AbstractGeneral ducand astfel la initializarea fiecarui nou Fisier creat.Sunt implementate si doua metode de afisare a continutului Fisierului precum si de modificare  a acestuia.

Clasa user
Clasa implementeaza modul in care fiecare utilizator din sistem va fi gestionat.Fiecare utilizator are un nume, un grup, o lista de grupuri pe care le detine si o lista de grupuri carora le este membru fara a le detine.Calsa implementeaza un constructor ce initializeaza fiecare nou utilizator din sistem, metode de adaugare si eliminare de men=mbrii din listele de grupuri la care un utilizator este owner sau din care acesta doar fac parte, metoda ce intoarce un anumit user din sistem, metoda ce verifica daca un anumit grup  a fost creat de un anumit user,metoda ce intoarce grupul default al unui utilizator, metoda ce intoarce numele unui utilizator.

Clasa Action
Implementeaza fiecare din comenzile ce ar putea fi trimise de un user, comenzi de creare de fisiere si directoare, de stergere de fisiere si directoare, comenzi de listare a continutului unui director si de citire a continutului unui fisier, metode de schimbare  apermisiunilor, de creare de noi grupuri si de adaugare de noi useri acestor grupuri.

Clasa Main
Clasa Main apeleazaa toate celelalte metode implementate de clasa Action si pentru fiecare comanda executata gasita in lista de comenzi a switch'ului se executa intrusctiunea corespunzatoare. Fiecare comanda este citita de la intrarea standard pana la primirea comenzii quit. Fiecarei comenzi ii corespunde un cod in switchul implementat, daca comanda primita nu se gaseste in acest switch atunci se intoarce eroare.
