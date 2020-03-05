## Määrittelydokumentti

Projektin tarkoituksena on luoda ohjelma, joka tiivistää tietoa. Ohjelma saa syötteenä tekstitiedoston, joka tiivistetään pienempään muotoon. Projektissa toteutetaan sekä tiedoston kompressointi että dekompressointi. Käytän kielenä Javaa.

Toteutukseen käytän Huffmanin algoritmia, johon tarvitaan tietorakenteena prioriteettijonoa. Huffmanin algoritmissa aikavaativuus on O(n).

Vertailen projektissa Huffmanin algoritmia Lempel–Ziv–Welchin (LZW) algoritmiin. LZW:ssä tarvitaan tietorakenteena taulukkolistojen lisäksi hajautustaulua. LZW:n aikavaativuus on O(n).

Huffmanin algoritmi perustuu merkkien kokonaisfrekvenssiin, kun taas LZW tarkastelee lokaalia toisteisuutta tekstissä. Vertailussa keskitytään sekä nopeuteen että tehokkuuteen (tiivistäminen). Käytän vertailuun jotakin vapaasti saatavaa kirjaa sekä itse luotuja merkkirivejä.

### Lähteet
- https://en.wikipedia.org/wiki/Huffman_coding
- https://en.wikipedia.org/wiki/Lempel–Ziv–Welch

### Mahdollisia lähteitä
- https://csfieldguide.org.nz/en/chapters/coding-compression/huffman-coding/
- https://www.cs.auckland.ac.nz/software/AlgAnim/huffman.html
- https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pearson/04Huffman.pdf