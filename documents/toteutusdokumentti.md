## Toteutusdokumentti

Ohjelma toteuttaa sekä Huffmanin että Lempel–Ziv–Welchin (LZW) kompressioalgoritmit. Ohjelmaan on toteutettu myös kompressoitujen tiedostojen dekompressointi. Dekompressointia ei kuitenkaan voi tehdä muutoin kuin suoraan kompressoinnin jälkeen, sillä dekompressointia varten tarvittavat tietorakenteet on tallennettu muistiin. Tämän toteuttaminen olisi ollut haastavaa tämän kurssin puitteissa.

Syötteenä sallitaan tekstitiedostot. Ainakin Huffmanin algoritmi toimii teoriassa muillekin tiedostotyypeille, esimerkiksi kuville, mutta oletettavaa on, että ohjelmaa pitäisi muokata sitä varten.

#### Huffmanin algoritmi
Huffmanin algoritmi jakautuu seuraaviin osiin:
- 
- 
- 

##### Aikavaativuus
- Aikavaativuus O(n)
- Pahin syöte, entropia

##### Algoritmin onnistuminen

#### Lempel–Ziv–Welchin (LZW) algoritmi
Lempel-Ziv-Welchin algoritmi jakautuu seuraaviin osiin:
- HashSetin alustus: Käydään jokainen syötteen merkki läpi ja lisätään se HashSetiin. Tämä vie aikaa O(n), jossa _n_ on syötteen koko.
- Toisto-osio, jossa käydään läpi syötettä. Etsitään mahdollisimman pitkää merkkijonoa, joka on jo kohdattu tekstissä (eli joka löytyy HashSetistä). Kun pidempää merkkijonoa ei enää löydetä, se lisätään HashSetiin ja aletaan tarkastella merkkijonoa, joka alkaa viimeisimmän indeksin kohdalta. Sekä lisääminen että tarkistus HashSetistä vie aikaa O(1):n verran. Koska syöte käydään tässäkin läpi merkki kerrallaan, aikavaativuus on O(n).
- Enkoodaus koostuu mahdollisimman pitkien merkkijonojen numeraalisista esityksistä: yhden merkin tapauksessa tämä on merkin ASCII-koodaus, pidempien merkkijonojen tapauksessa juoksevasta indeksinumerosta. Enkoodatut merkit tallennetaan itse tehtyyn ArrayListaan.
- Näiden lukujen avulla dekompressoidaan kompressoitu versio. Yhtä pidempien merkkijonojen tapauksessa tarkastetaan ArrayListasta (luku-256) vastaavan indeksin paikalta, mikä merkkijono on kyseessä. Aikavaativuus on tässäkin O(n), missä _n_ on kompressoidun syötteen koko.
- 

##### Aikavaativuus
- Aikavaativuus O(n)
- Pahin syöte, entropia

##### Algoritmin onnistuminen
Koska algoritmi perustuu aina mahdollisimman pitkän merkkijonon löytämiseen syötteen sisällä, se toimii parhaiten silloin, kun syötteessä on paljon toistoa ja mahdollisimman vähän entropiaa.


#### Työn mahdolliset puutteet ja parannusehdotukset
- Dekompressoinnin mahdollisuus aiemmin kompressoidusta tiedostosta. Tämän voisi toteuttaa tallentamalla tarvittavat tietorakenteet kompressoidun tiedoston loppuun.
- Huffmanin algoritmissa dekompressoitu syöte on bitteinä ja niiden lukeminen tapahtuu kahdeksan bitin jonoissa. Jos luettavan tiedoston pituus ei ole jaollinen kahdeksalla, viimeisestä dekompressoidusta rivistä tulee vääränlainen.
- HashCode on liian hidas HashSetissä LZW:ssä. Tätä voisi optimoida etsimällä paremman tavan tuottaa hashcode.
- Syöte, jossa on yksi merkki tai jossa on vain yhtä yhtä merkkiä, ei kompressoidu Huffmanin kompressiossa. 


##### Lähteet
- https://en.wikipedia.org/wiki/Lempel–Ziv–Welch
- http://www2.cs.duke.edu/csed/curious/compression/lzw.html
- https://en.wikipedia.org/wiki/Huffman_coding