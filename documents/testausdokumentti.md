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
Testauksessa on otettu huomioon erityisesti alla olevia testitapauksia.

ELI MUKAAN SIIS
- TIEDOSTON KOKO
- KULUNUT AIKA
- MILLAINEN TESTATTAVA TIEDOSTO ON

lorem.txt: 6908 b
toisto.txt: 43559b
bible.txt: 4332557b

#### Huffman
lorem.txt: 8 millisekuntia / 0.008 sek, 3690 b
toisto.txt: 23 millisekuntia / 0.024 sek, 15435 b
bible.txt: 393 millisekuntia / 0.414 sek, 2480356b

- Saman kirjaimen/ilmauksen toisto: toisto.txt, 43559b = n. 43K


- Täysin satunnainen / Raamattu

- Raamattu


#### LZW

| Tiedostonimi | Kulunut aika | Tiedoston koko |
| ------------- | ------------- | ------------- |
| lorem.txt  | 341 msek / 0.332 sek | 9920b / 9.7K |
| toisto.txt  | 752 msek / 0.705 sek  | 4036b / 3,9K |
| bible.txt | 130672 msek / 128.813 sek | 2463672b / 2.3M |

- Saman kirjaimen/ilmauksen toisto: toisto.txt

Nähdään, että tässä algoritmi toimii parhaiten, erityisesti mahdollisimman pitkillä syötteillä.

- Täysin satunnainen / Raamattu

LZW tuottaa lähes saman kuin Huffman. Pituus vaikuttaa kuitenkin asiaan: jos syöte on lyhyt, kompressio on jopa isompi kuin alkuperäinen tiedosto. Raamatun kohdalla toistoa on tapahtunut (jos otetaan huomioon myös Raamatun muutenkin jossain määrin toisteinen kieli), ja täten myös kompressio on hyvin onnistunut.


### Tilastoja testauksesta
