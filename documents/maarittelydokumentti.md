## Määrittelydokumentti

Projektin tarkoituksena on luoda ohjelma, joka tiivistää tietoa. Ohjelma saa syötteenä tekstitiedoston, joka tiivistetään pienempään muotoon. Projektissa toteutetaan sekä tiedoston kompressointi että dekompressointi. Käytän kielenä Javaa.

Toteutukseen käytän Huffmanin algoritmia, johon tarvitaan binääripuuta ja prioriteettijonoa. Huffmanin algoritmissa aikavaativuus on O(n log n).

Vertailen projektissa Huffmanin algoritmia [tässä artikkelissa](https://www.cs.brandeis.edu/~dilant/cs175/%5BSiying-Dong%5D.pdf) esitettyyn algoritmiin. Kyseinen algoritmi eroaa Huffmanin algoritmista esimerkiksi siinä, että se toimii hyvin vain silloin, kun tekstissä on paljon toistoa; muulloin kompressio ei pienennä tiedostoa merkittävästi. Vertailussa keskitytään sekä nopeuteen että tehokkuuteen (tiivistäminen). Käytän vertailuun jotakin vapaasti saatavaa kirjaa sekä itse luotuja merkkirivejä.

### Lähteet
- https://en.wikipedia.org/wiki/Huffman_coding

### Mahdollisia lähteitä
- https://csfieldguide.org.nz/en/chapters/coding-compression/huffman-coding/
- https://www.cs.auckland.ac.nz/software/AlgAnim/huffman.html
- https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pearson/04Huffman.pdf