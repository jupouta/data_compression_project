## Testausdokumentti

### Ohjelman testaus

Ohjelmaa on testattu yksikkötesteillä. Yksikkötestit testaavat ohjelmassa toteutettuja luokkia ja niiden toimivuutta. Yksikkötesteissä on pyritty mahdollisimman tarkasti testaamaan erilaisia tapauksia, jotta nähdään, toimiiko ohjelma oikein ja tarpeeksi nopeasti. Alla olevassa kuvassa näkyvät rivikattavuus ja mutaatiotestien kattavuus. Kattavuus on automaattisten raporttien perusteella varsin hyvä.

![Kuva mutaatiotestiraportista](picReport.png)

Testauksessa on lisäksi verrattu alkuperäisiä tiedostoja ohjelman dekompressoituihin versioihin. Tähän on käytetty unixin diff-komentoa, jolla saa tarkastettua, onko tiedostoilla mitään eroa.

- Mitä ei testata:
  - Pohjoismaisia merkkejä on testattu yhdellä yksikkötestillä (joka toimii). Oletettavaa kuitenkin on, että monet erikoismerkit, mukaan lukien ääkköset, saattavat hajottaa ohjelman, erityisesti Huffmanin algoritmin, joka perustuu merkkien ASCII-koodauksessa vastaavaan kokonaislukuun.

  - Erilaisia tiedostotyyppejä ei ole testattu tai otettu huomioon. Ohjelmaa on testattu merkkijonoilla ja tekstitiedostoilla.

  - Tiedoston avaamista ja sen toimintaa ei testata, koska voidaan olettaa, että Javan omat funktiot toimivat oikein.

### Testitapaukset
Testauksessa on otettu huomioon erityisesti alla olevia testitapauksia. Molemman algoritmin kohdalla on listattu tiedosto, tiedoston kompressointiin kulunut aika, tiedoston alkuperäinen koko ja kompressoidun tiedoston koko. Kompressioon kulunutta aikaa kuvaava yksikkö msek tarkoittaa millisekuntia.

lorem.txt: 6908 b
toisto.txt: 43559b
bible.txt: 4332557b

#### Huffman
lorem.txt: 8 millisekuntia / 0.008 sek, 3690 b
toisto.txt: 23 millisekuntia / 0.024 sek, 15435 b
bible.txt: 393 millisekuntia / 0.414 sek, 2480356b

| Tiedostonimi | Kulunut aika | Alkuperäinen koko | Kompressoitu koko |
| ------------- | ------------- | ------------- |------------- |
| lorem.txt | 8 msek / 0.008 sek | 6908b / 6.7K | 3690b / 3,6K |
| toisto.txt | 23 msek / 0.02 sek | 43559b / 43K | 15435b / 15K |
| bible.txt | 393 msek / 0.39 sek | 4332557b / 4.1M | 2480356b / 2,4M |

- Saman kirjaimen/ilmauksen toisto: toisto.txt

Kyseisessä tiedostossa toistetaan samaa sanaa 7200 kertaa. Alkuperäiseen tiedostoon nähden koko on pienentynyt kolmasosaan, joten algoritmi toimii varsin hyvin. Lisäksi aikaa kului todella vähän, erityisesti verrattuna LZW:hen.

- Satunnainen generoitu teksti: lorem.txt

Tekstiin on koottu kymmenen kappaletta generoitua lorem ipsum -tekstiä. Tiedosto on erittäin lyhyt, joten kompressio tapahtuu nopeasti. Lisäksi tiedoston koko on noin puolet pienempi, joten ohjelma toimii hyvin. 

- Raamattu: bible.txt

Tiedostoon on kirjattu koko Raamattu. Ohjelma toimii varsin nopeasti huolimatta siitä, että tiedosto on jo aikaisempiin verrattuna isompi. Lisäksi tiedoston koko on kompressoituna lähes puolet alkuperäistä pienempi.

#### LZW

Seuraavaan taulukkoon on koottu käytetyimmät testitapaukset:

| Tiedostonimi | Kulunut aika | Alkuperäinen koko | Kompressoitu koko |
| ------------- | ------------- | ------------- |------------- |
| lorem.txt  | 341 msek / 0.34 sek | 6908b / 6.7K | 9920b / 9.7K |
| toisto.txt  | 752 msek / 0.75 sek  | 43559b / 43K | 4036b / 3,9K |
| bible.txt | 130672 msek / 130.8 sek | 4332557b / 4.1M | 2463672b / 2.3M |

- Saman kirjaimen/ilmauksen toisto: toisto.txt

Kyseisessä tiedostossa toistetaan samaa sanaa 7200 kertaa. Nähdään, että tässä algoritmi toimii parhaiten, erityisesti mahdollisimman pitkillä syötteillä, sillä koko on pienentynyt kymmenkertaisesti. Aikaa kuluu silti suhteellisen paljon, varsinkin verrattuna Huffmaniin.

- Satunnainen generoitu teksti: lorem.txt

Tekstiin on koottu kymmenen kappaletta generoitua lorem ipsum -tekstiä. Koska teksti on niin lyhyt, kompressiota ei ehdi tapahtua vähäisen toiston vuoksi, joka taas johtuu isosta entropiasta. Tämän vuoksi tiedoston koko on lopulta alkuperäistä isompi.

- Raamattu: bible.txt

LZW tuottaa lähes samanlaisen tuloksen kuin Huffman, sillä kompressoidun tiedoston koko on suurin piirtein puolet alkuperäisestä. Suoritus on kuitenkin erittäin huono, sillä kompressioon kuluu noin 2 minuuttia.

#### Yhteenveto

Kun katsotaan tiedostojen kokoja, Huffman toimii tasaisesti saaden aikaan alkuperäisestä tiedostosta noin puolet pienemmän kompressoidun version. LZW taas toimii erityisen hyvin silloin, kun tiedosto on iso ja siinä on erityisen paljon toistoa eli vähän entropiaa. LZW:n tehokkuus on kuitenkin varsin huono isoilla syötteillä, mutta sitä voitaisiin parantaa esimerkiksi hashaystä parantamalla.

