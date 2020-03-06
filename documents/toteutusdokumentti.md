## Toteutusdokumentti

Ohjelma toteuttaa sekä Huffmanin että Lempel–Ziv–Welchin (LZW) kompressioalgoritmit. Ohjelmaan on toteutettu myös kompressoitujen tiedostojen dekompressointi. Dekompressointia ei kuitenkaan voi tehdä muutoin kuin suoraan kompressoinnin jälkeen, sillä dekompressointia varten tarvittavat tietorakenteet on tallennettu muistiin. Tämän toteuttaminen olisi ollut haastavaa tämän kurssin puitteissa.

Syötteenä sallitaan tekstitiedostot. Ainakin Huffmanin algoritmi toimii teoriassa muillekin tiedostotyypeille, esimerkiksi kuville, mutta ohjelmaa pitäisi hiukan muokata sitä varten (mm. tiedoston lukua).

### Ohjelman rakenne
Ohjelma koostuu kahdesta eri paketista: projektiin liittyvästä sekä tietorakenteita sisältävästä paketista. Main-tiedosto kutsuu projektipakkauksessa olevaa luokkaa Gui, joka aloittaa ohjelman. Gui kutsuu molempaa kompressiota ja dekompressiota, jotka ovat luokissa HuffmanCompression ja LZCompression. Tiedoston lukeminen tapahtuu luokan FileHandler avulla, ja sitä kutsutaan myös Guista.

Datastructure-paketti pitää sisällään kaikki tietorakenneluokat. MyArrayList on itse tehty versio javan ArrayListasta, ja se sisältää ohjelmaa varten tarvittavat metodit. MyArrayListiä käytetään sekä Huffman että LZW:n kompressiossa. Node- ja MyPrioQueue-luokkia käytetään Huffmanin algoritmissa. Node on tietorakenne prioriteettijonoa varten, joka taas on osa Huffmanin algoritmia. MyHashNode ja MyHashSet ovat osa itse tehtyä versiota javan HashSetistä. MyHashSetiä käytetään LZW:n algoritmissa, ja MyHashNode tietorakenne, jota tarvitaan MyHashSetiin.

### Huffmanin algoritmi

Huffmanin algoritmi jakautuu seuraaviin osiin:
1. Frekvenssitaulun alustus. Ohjelma käy läpi tiedoston kaikki rivit ja laskee jokaiselle merkille kyseisen merkin frekvenssin tekstissä. Tämä vie aikaa O(n), missä _n_ on syötteen koko.
2. Uniikit merkit asetetaan pienuusjärjestykseen prioriteettijonoon niin, että pienin merkki on ensimmäisenä jonossa. Merkit tallennetaan jonoon solmuina, joihin on tallennettu merkki sekä merkin frekvenssi. Merkkien läpikäynti vie O(m), missä _m_ on uniikkien merkkien määrä tiedostossa, ja prioriteettijonoon tallentaminen vie järjestämisen vuoksi O(log m), missä _m_ on sama merkkien määrä tiedostossa. Aikaa kuluu siis yhtesnsä O(m log m).
3. Käy prioriteettijono läpi ottamalla pois kaksi pienimmän frekvenssin solmua. Nämä kaksi solmua yhdistetään ja luodaan uusi solmu, jolla on näiden solmujen yhteenlaskettu frekvenssi. Prioriteettijonoa käydään läpi niin pitkään kuin jonossa on solmuja. Tämä vie aikaa O(m), sillä jokainen merkki täytyy käydä läpi.
4. Kun solmut loppuvat, otetaan viimeinen (korkeimman frekvenssin) solmu ja käydään sen molemman puolen solmut rekursiivisesti läpi. Kun solmulla ei ole vasemmalla tai oikealla puolella enää solmuja, tallennetaan taulukkoon merkkiä vastaavan ASCII-koodin mukaiseen indeksiin merkin enkoodaus. Binääripuun vuoksi aikaa kuluu O(log m).
5. Lopuksi käydään läpi syötteen merkit ja otetaan ylös taulukosta enkoodaus kyseiselle merkille. Koska _n_ on syötteen koko, aikavaativuus on O(n).

Lisäksi ennen kompressiota merkit muutetaan bittimuotoisiksi, jotta tiedoston koko olisi oikeasti pienempi, ja vastaavasti bitit muutetaan jälleen takaisin numeroiksi ennen dekompressiota.

Dekompressiossa käydään läpi kompressoidun tiedoston jokainen merkki (jotka vastaavat binääripuussa olevia kaaria) ja muodostetaan niiden avulla alkuperäiset merkit.

Algoritmin aikavaativuus on siis O(n).

#### Algoritmin onnistuminen

Algoritmi toimii testauksen perusteella varsin nopeasti. Kompressointi saa usein aikaan tiedostoja, jotka ovat noin puolet pienempiä. Entropia ei tunnu vaikuttavan kompression tuloksiin yhtä paljon kuin LZW:ssä. Ohjelma toimii siis tasaisen hyvin eikä syöte näyttäisi vaikuttavan siihen erityisesti.


### Lempel–Ziv–Welchin (LZW) algoritmi
Lempel-Ziv-Welchin algoritmi jakautuu seuraaviin osiin:
1. HashSetin alustus: Käydään jokainen syötteen merkki läpi ja lisätään se HashSetiin. Tämä vie aikaa O(n), jossa _n_ on syötteen koko.
2. Toisto-osio, jossa käydään läpi syötettä. Etsitään mahdollisimman pitkää merkkijonoa, joka on jo kohdattu tekstissä (eli joka löytyy HashSetistä). Kun pidempää merkkijonoa ei enää löydetä, se lisätään HashSetiin ja aletaan tarkastella merkkijonoa, joka alkaa viimeisimmän indeksin kohdalta. Sekä lisääminen että tarkistus HashSetistä vie teoriassa aikaa O(1):n verran. Koska HashSet on linkitetty lista, se joutuu kuitenkin käymään tietyssä indeksissä olevat oliot läpi ja katsomaan, onko listassa oliota, jolla olisi kysytty merkkijono. Jos oliot ovat jakautuneet epätasaisesti listaan niin, että yhdessä indeksissä on useampi olio, aikavaativuus voi nousta jossain määrin lähelle O(n). Tämä saattaisi pahimassa tapauksessa nostaa aikavaativuuden O(n^2), koska jokaisen merkkijonon kohdalla pitäisi tarkastella vielä edeltäviä jo kohdattuja merkkijonoja. Koska tämä riippuu hyvin paljon syötteestä, aikavaativuus voidaan määrittää tässä tapauksessa O(n).
3. Enkoodaus koostuu mahdollisimman pitkien merkkijonojen numeraalisista esityksistä: yhden merkin tapauksessa tämä on merkin ASCII-koodaus, pidempien merkkijonojen tapauksessa juoksevasta indeksinumerosta. Enkoodatut merkit tallennetaan itse tehtyyn ArrayListaan.
4. Näiden lukujen avulla dekompressoidaan kompressoitu versio. Yhtä pidempien merkkijonojen tapauksessa tarkastetaan ArrayListasta (luku-256) vastaavan indeksin paikalta, mikä merkkijono on kyseessä. Aikavaativuus on tässäkin O(n), missä _n_ on kompressoidun syötteen koko.

Algoritmin aikavaativuus on siis ainakin teoriassa O(n).

Ennen kompressiota merkit muutetaan bittimuotoisiksi, jotta tiedoston koko olisi oikeasti pienempi, ja vastaavasti bitit muutetaan jälleen takaisin numeroiksi ennen dekompressiota.


#### Algoritmin onnistuminen
Mitä enemmän entropiaa, sitä enemmän kompressoitu tiedosto vie tilaa. Vastavuoroisesti mitä pidempi tiedosto ja mitä enemmän toistoa tiedostossa on, sitä paremmin kompressio toimii, sillä algoritmi perustuu aina mahdollisimman pitkän merkkijonon löytämiseen syötteen sisällä. Tämän vuoksi usein lyhyillä syötteillä kompressiota ei tapahdu ollenkaan tai kompressoitu tiedosto on jopa isompi kuin alkuperäinen tiedosto. Koska luonnollisessa kielessä on rajoitettu määrä merkkejä, toistoa tapahtuu joka tapauksessa jossain vaiheessa, ja siksi pidemmillä syötteillä saadaan mahdollisimman hyvä kompressio. Tämä nähdään testauksessa käytetystä toisto-tiedostosta, jossa kompressoitu versio on hyvin paljon pienempi kuin alkuperäinen. Toisaalta jos katsotaan algoritmin nopeutta, itse tehdyllä HashSetillä toteutettu ohjelma ei toimi nopeasti isoilla syötteillä.


### Työn mahdolliset puutteet ja parannusehdotukset
- Dekompressoinnin mahdollisuus aiemmin kompressoidusta tiedostosta. Tämän voisi toteuttaa (esimerkiksi) tallentamalla tarvittavat tietorakenteet kompressoidun tiedoston loppuun.
- Syöte, jossa on vain yhtä yhtä merkkiä (esim. 'aaa'), ei onnistu kompressoitumaan Huffmanin kompressiossa. Tämä johtuu algoritmin toiminnasta binääripuuta muodostettaessa, sillä prioriteettijonosta otetaan pois kaksi pienintä solmua, ja jos jonossa on vain yksi solmu, tätä ei tapahdu. Tämän voisi korjata muuttamalla tarkastuksen erilaiseksi (niin että tällaista ongelmaa ei tapahtuisi), mutta aikapuutteen vuoksi se jäi puuttumaan.
- Itse toteutettu HashSet on varsin hidas, kuten Raamatun tapauksessa nähdään selvästi. Tämä johtuu (ainakin osittain) uudelleenhashayksestä, joka pitää tehdä taulukon tullessa täyteen. Uudelleenhäshäys on kallis operaatio, koska jokainen taulukossa oleva olio (HashNode) pitää hashata uudelleen. Tätä saattaa tapahtua isoissa teksteissä useamminkin, vaikka alkurajan (taulukon koon) asettaisikin mahdollisimman suureksi. HashSetin voisi mahdollisesti vaihtaa joksikin muuksi tietorakenteeksi, joka on nopeampi, tai sitten yrittää parantaa hashaystä tehokkaammaksi. Lisäksi muistin kasvattaminen voi auttaa.


### Lähteet
- https://en.wikipedia.org/wiki/Lempel–Ziv–Welch
- http://www2.cs.duke.edu/csed/curious/compression/lzw.html
- https://en.wikipedia.org/wiki/Huffman_coding
- https://www.cs.auckland.ac.nz/software/AlgAnim/huffman.html