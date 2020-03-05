## Toteutusdokumentti

Ohjelma toteuttaa sekä Huffmanin että Lempel–Ziv–Welchin (LZW) kompressioalgoritmit. Ohjelmaan on toteutettu myös kompressoitujen tiedostojen dekompressointi. Dekompressointia ei kuitenkaan voi tehdä muutoin kuin suoraan kompressoinnin jälkeen, sillä dekompressointia varten tarvittavat tietorakenteet on tallennettu muistiin. Tämän toteuttaminen olisi ollut haastavaa tämän kurssin puitteissa.

#### Ohjelman rakenne


Syötteenä sallitaan tekstitiedostot. Ainakin Huffmanin algoritmi toimii teoriassa muillekin tiedostotyypeille, esimerkiksi kuville, mutta oletettavaa on, että ohjelmaa pitäisi muokata sitä varten.

#### Huffmanin algoritmi
Huffmanin algoritmi jakautuu seuraaviin osiin:
- 
- 
- 

Ennen kompressiota merkit muutetaan bittimuotoisiksi, jotta tiedoston koko olisi oikeasti pienempi, ja vastaavasti bitit muutetaan jälleen takaisin numeroiksi ennen dekompressiota.

##### Aikavaativuus
- Aikavaativuus O(n)
- Pahin syöte, entropia
Pahin syöte on ainakin prioriteettijonossa sellainen, että merkit eivät jakaudu tasaisesti molemmille puolille.

##### Algoritmin onnistuminen

#### Lempel–Ziv–Welchin (LZW) algoritmi
Lempel-Ziv-Welchin algoritmi jakautuu seuraaviin osiin:
1. HashSetin alustus: Käydään jokainen syötteen merkki läpi ja lisätään se HashSetiin. Tämä vie aikaa O(n), jossa _n_ on syötteen koko.
2. Toisto-osio, jossa käydään läpi syötettä. Etsitään mahdollisimman pitkää merkkijonoa, joka on jo kohdattu tekstissä (eli joka löytyy HashSetistä). Kun pidempää merkkijonoa ei enää löydetä, se lisätään HashSetiin ja aletaan tarkastella merkkijonoa, joka alkaa viimeisimmän indeksin kohdalta. Sekä lisääminen että tarkistus HashSetistä vie aikaa O(1):n verran. Koska syöte käydään tässäkin läpi merkki kerrallaan, aikavaativuus on O(n).
3. Enkoodaus koostuu mahdollisimman pitkien merkkijonojen numeraalisista esityksistä: yhden merkin tapauksessa tämä on merkin ASCII-koodaus, pidempien merkkijonojen tapauksessa juoksevasta indeksinumerosta. Enkoodatut merkit tallennetaan itse tehtyyn ArrayListaan.
4. Näiden lukujen avulla dekompressoidaan kompressoitu versio. Yhtä pidempien merkkijonojen tapauksessa tarkastetaan ArrayListasta (luku-256) vastaavan indeksin paikalta, mikä merkkijono on kyseessä. Aikavaativuus on tässäkin O(n), missä _n_ on kompressoidun syötteen koko.

Ennen kompressiota merkit muutetaan bittimuotoisiksi, jotta tiedoston koko olisi oikeasti pienempi, ja vastaavasti bitit muutetaan jälleen takaisin numeroiksi ennen dekompressiota.

##### Aikavaativuus
- Aikavaativuus O(n)
- Mitä enemmän entropiaa, sitä enemmän kompressoitu tiedosto vie tilaa. Vastavuoroisesti mitä pidempi tiedosto ja mitä enemmän toistoa tiedostossa on, sitä paremmin kompressio toimii. Tämän vuoksi usein lyhyillä syötteillä kompressiota ei tapahdu ollenkaan tai kompressoitu tiedosto on jopa isompi kuin alkuperäinen tiedosto. Koska luonnollisessa kielessä on rajoitettu määrä merkkejä, toistoa tapahtuu joka tapauksessa jossain vaiheessa, ja siksi pidemmillä syötteillä saadaan mahdollisimman hyvä kompressio.

##### Algoritmin onnistuminen
Koska algoritmi perustuu aina mahdollisimman pitkän merkkijonon löytämiseen syötteen sisällä, se toimii parhaiten silloin, kun syötteessä on paljon toistoa ja mahdollisimman vähän entropiaa.


#### Työn mahdolliset puutteet ja parannusehdotukset
- Dekompressoinnin mahdollisuus aiemmin kompressoidusta tiedostosta. Tämän voisi toteuttaa (esimerkiksi) tallentamalla tarvittavat tietorakenteet kompressoidun tiedoston loppuun.
- Huffmanin algoritmissa dekompressoitu syöte on bitteinä ja niiden lukeminen tapahtuu kahdeksan bitin jonoissa. Jos luettavan tiedoston pituus ei ole jaollinen kahdeksalla, viimeisestä dekompressoidusta rivistä tulee vääränlainen.
- Syöte, jossa on yksi merkki tai jossa on vain yhtä yhtä merkkiä, ei kompressoidu Huffmanin kompressiossa. 


##### Lähteet
- https://en.wikipedia.org/wiki/Lempel–Ziv–Welch
- http://www2.cs.duke.edu/csed/curious/compression/lzw.html
- https://en.wikipedia.org/wiki/Huffman_coding