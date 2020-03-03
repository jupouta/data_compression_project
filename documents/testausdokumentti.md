## Testausdokumentti

#### Ohjelman testaus
- Kuinka kauan suorittaa:
- Onnistuuko tekemään:
- Mitä ei testata:
Pohjoismaisia merkkejä on testattu yhdellä yksikkötestillä (joka toimii). Oletettavaa kuitenkin on, että monet erikoismerkit, mukaan lukien ääkköset, saattavat hajottaa ohjelman, erityisesti Huffmanin algoritmin, joka perustuu merkkien ASCII-koodauksessa vastaavaan kokonaislukuun.
Tiedoston avaamista ja sen toimintaa ei testata, koska voidaan olettaa, että Javan omat funktiot toimivat oikein.

Testitapaukset


#### Huffman
##### Saman kirjaimen toisto

##### Täysin satunnainen

##### Raamattu


#### LZW
##### Saman kirjaimen toisto

##### Täysin satunnainen

##### Raamattu


#### Automaattiset raportit


#### Tilastoja testauksesta


Ohjelmaa on testattu yksikkötesteillä. Yksikkötestaavat ohjelmassa toteutettuja luokkia ja niiden toimivuutta. Yksikkötesteissä on pyritty mahdollisimman tarkasti testaamaan erilaisia tapauksia, jotta nähdään, toimiiko ohjelma oikein ja tarpeeksi nopeasti.