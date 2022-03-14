# Needleman-Wunsch e Gotoh aligners.
Implementazione di algoritmi di allineamento delle sequenze di DNA. 

Progetto svolto in occasione del corso di Algoritmi e Strutture Dati in Unicam con il prof. Luca Tesei.

Di seguito una breve descrizione del progetto. Per ulteriori informazioni consultare il file .doc nel repository.


**Allineamento di Sequenze**

Una sequenza è una successione finita di simboli presi da un certo alfabeto. In Java si può tranquillamente equiparare a una stringa di caratteri. In biologia sequenze di lettere specifiche vengono usate per rappresentare filamenti di DNA o RNA. Dopo la scoperta di questi, c’è stata una forte spinta a sviluppare metodi automatici (algoritmi) per il riconoscimento e il confronto di sequenze che ha portato alla nascita della bioinformatica, un ambito di ricerca a cavallo tra l’informatica e la biologia. 
In bioinformatica, uno dei problemi su sequenze che risulta utile risolvere in maniera efficiente è quello della similarità. Ciò è giustificato dal fatto che sequenze simili probabilmente hanno avuto una evoluzione simile o si sono “evolute” da antenati comuni. Ma di preciso cosa significa “similarità”? Le risposte possono essere diverse, anche in base al tipo di “relazione biologica” che si cerca. Una delle nozioni di similarità più usate è basata sulla nozione di allineamento di sequenze.
Supponiamo di avere un simbolo speciale, chiamato gap, e rappresentato solitamente da un trattino: “-”. Un allineamento di due sequenze date è una coppia di sequenze che contengono potenzialmente dei gap e che indica come trasformare una sequenza nell’altra e viceversa. 

Naturalmente possono esistere diversi allineamenti fra le stesse stringhe e tra l'altro potenzialmente infiniti!

Ma allora qual è l’allineamento “giusto”? L’idea di base è che ad ogni operazione presente nell’allineamento, tranne ovviamente la match, si può assegnare un certo costo. Quindi si può prendere come allineamento di due sequenze uno qualsiasi per cui il costo totale delle operazioni (la somma) sia minimo. Ne risulta quindi che il problema di trovare l’allineamento tra due sequenze è un problema di ottimizzazione. 
    
Indichiamo con  una funzione che assegna i costi alle operazioni, detta funzione di costo o funzione di scoring. Indichiamo quindi con il costo associato all’operazione e assumiamo che il costo di ogni tipo di operazione sia, in generale, maggiore o uguale di zero tranne che nel caso di match, per cui il costo è uguale a zero.

Come possiamo procedere per calcolare in maniera efficiente il minimo, cioè la distanza di allineamento? Ci viene in aiuto la programmazione dinamica! Possiamo far vedere infatti che è possibile scomporre il problema di allineamento ottimo in sottoproblemi analoghi e che tali sottoproblemi godono della proprietà della sottostruttura ottima.
