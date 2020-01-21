# mi2-ajanlo_rendszerek

Mesterséges inteligencia 1. házifeladat, 2019/20 őszi félév.

## Feladatleírás

### Bevezetés
Legyen adott egy reptéri hosszú távú parkoló, ahol akár hetekig parkolhatnak
tetszőleges méretű gépjárművek, azaz autók, buszok, motorkerékpárok. A célunk az,
hogy minden járműnek legyen helye a parkolóban, a járművek egyedi
hozzáférhetőségtől most eltekintünk.

<img src="pics/leiras.jpg" width="800">

Célunk, hogy az összes V-beli járművet elhelyezzük a P mátrixban. A járművek
elforgatása megengedett, sőt, a legtöbb esetben szükséges. A problémáról egyébként
belátható, hogy NP-nehéz, ám nagy méret esetén is léteznek hatékony algoritmusok,
amelyek megoldást találnak.


### Feladat
Valósítsa meg a Parkoló feltöltését Java vagy Python nyelven! Az algoritmus
tetszőlegesen megválasztható, kódot viszont nem emelhet át külső forrásból. 

### Java
A megoldás tartalmazzon egy Main osztályt, ezen belül pedig egy main() függvényt. A
bemenetet a standard inputon várja, a kimenetet a standard outputra írja. A program
forráskódját zip fájlba tömörítve töltse fel a HF portálon

### Bemenet
A bemenet tabulátorokkal tagolt szöveges adat, amely első sorában a parkoló hosszát
és szélességét, második sorában a járművek számát, utána soronként egy-egy jármű
hosszát és szélességét tartalmazza. A fenti esetben:  
5 7  
7  
4 2  
3 2  
1 2  
2 5  
2 2  
2 1  
3 1  

### Kimenet

Kimenetként írja a P mátrixot a standard outputra, tabulátorokkal tagolt formátumban.
(Típushiba szokott lenni, hogy a sorok végén felesleges tabulátor van, a kiértékelés
során az ilyen megoldásokat nem fogadjuk el).  
1 1 1 1 4 4 6  
1 1 1 1 4 4 6  
2 2 3 3 4 4 7  
2 2 5 5 4 4 7  
2 2 5 5 4 4 7  

## bemenet.txt

A bemenet.txt-ben van néhány példa bemenet, a legnehezebbre egy megoldása a programnak: 

<img src="pics/45x45_megoldva.jpg" width="800">


