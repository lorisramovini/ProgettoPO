# Progetto OOP - Dropbox

## **Autori**
- Piccioni Lorenzo - s1078409
- Ramovini Loris - s1077147

## **Introduzione**

La seguente applicazione, partendo dalla lista degli shared links di un utente Dropbox, permette di andare ad analizzarne le caratteristiche come il numero di file per cartella o il numero di file condivisi con i vari utenti. Inoltre è possibile anche andare a filtrare i risultati ottenuti in base al tipo di file.
Per il corretto funzionamento tutti gli elementi all'interno di una cartella, che ha uno shared link, devono avere anch'essi un loro shared link altrimenti il contenuto della cartella non risulterebbe condiviso.


## Dataset

All'avvio dell'applicazione viene salvata la lista di tutti gli shared links di un utente predefinito ottenendo un dataset con la seguente struttura:
- _Tag_: indica se il link considerato è riferito ad un file o ad un folder
- _Url_: link corrispondente alla risorsa
- _Id_: codice univoco identificativo della risorsa
- _Nome_: nome dell'elemento condiviso
- _Percorso_: path dell'elemento all'interno di Dropbox
- _Tipo_: tipo del file
- _Dimensione_: dimensione del file

Con gli ultimi due campi settati a **null** se il _Tag_ è folder
## Rotte dell'applicazione
Tramite Postman, dopo aver avviato l'applicazione sono disponibili le seguenti richieste GET o POST

|    TIPO        |rotta                          |descrizione                                |
|----------------|-------------------------------|-------------------------------------------|
|GET             |/data                     |restituisce l'intero dataset           |
|GET             |/refresh                       |permette di aggiornare il dataset               |
|GET             |/refresh/{token}      |permette di utilizzare un dataset diverso dal precedente     |
|GET            |/stats                        |restituisce la lista degli shared links suddivisi per cartella                                    |
|GET           |/statsMembri      |restituisce la lista degli shared links suddivisi per utente |
|POST          |/stats                        |restituisce la lista degli shared links suddivisi per cartella e filtrati per il tipo richiesto                                  |
|POST           |/statsMembri      |restituisce la lista degli shared links suddivisi per utente e filtrati per il tipo richiesto |

*  **GET /data**
    * Restituisce l'intero dataset; 
    Se effettuata come prima chiamata permette di vedere quali sono gli shared links dell'utente predefinito, altrimenti se il dataset è stato aggiornato tramite una delle due chiamate /refresh mostrerà il nuovo dataset.
* **GET /refresh**
   * Aggiorna il dataset, va chiamata se sono stati modificati gli shared links presenti nell'ultimo profilo Dropbox considerato
* **GET /refresh/{token}**
   * Effettua la chiamata API Dropbox per ottenere la lista degli shared links con il token inserito nella chiamata, quindi permette di utilizzare un nuovo dataset diverso da quello dato di default. Se il token inserito non è valido l'applicazione continuerà a segnalarlo finché non ne verrà inserito uno giusto.
* **GET /stats**
   * Restituisce una statistica sugli shared links presenti in ogni cartella, indicando per ognuna il numero totale dei link presenti in essa, quanti di questi corrispondono a delle sottocartelle, e per i restanti restituisce una lista che indica quanti file di ogni tipo sono presenti.
 * **GET /statsMembri**
   * Restituisce una statistica sui file condivisi con altri utenti Dropbox, indicando il numero totale di utenti con cui si condivide almeno un file.
      Per ogni utente sono specificati email, nome, numero di file che si condividono, dimensione media dei file condivisi ed è mostrata una statistica sulla quantità di file condivisi in base al tipo.
* **POST /stats**
   * Restituisce la stessa statistica data da **GET /stats** mostrando nella lista dei file solamente quelli del tipo specificato nel filtro.
 * **POST /statsMembri**
   * Restituisce la stessa statistica data da **GET /statsMembri** mostrando nella lista dei file di ogni utente solamente quelli del tipo specificato nel filtro, eliminando dalla lista degli utenti quelli con cui non si condivide nessun file del tipo richiesto.
## Filtri 

La richiesta di filtraggio avviene tramite delle chiamate POST, passando all'interno del body un filtro JSON formattato in questo modo: 
```
{
    "operatore": "$eq",
    "parametro": "photo"
}
```
* **operatore**: può corrispondere solamente ai seguenti valori, se inserito in maniera errata viene lanciata l'eccezione OperatoreErratoException
    * **"$eq"** (uguale a)
    * **"$not"** (diverso da)
* **parametro**: può corrispondere solamente a uno dei valori che sono presenti nel dataset

## **UML**

### Diagramma dei casi d'uso
![](CaseDiagram.jpg)
### Diagramma delle classi
![](ClassDiagram2.png)
### Diagramma delle sequenze
Rappresenta la richiesta in POST /statsMembri
![](SequenceDiagram.png)




