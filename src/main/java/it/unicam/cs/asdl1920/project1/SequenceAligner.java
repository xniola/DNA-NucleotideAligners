package it.unicam.cs.asdl1920.project1;

/**
 * Interfaccia per algoritmi di allineamento di sequenze.
 * 
 * @author Luca Tesei
 *
 */
public interface SequenceAligner {

    /**
     * Restituisce il nome dell'algoritmo di allineamento di questo aligner.
     * 
     * @return il nome dell'algoritmo
     */
    public String getName();

    /**
     * Restituisce la prima sequenza associata a questo aligner.
     * 
     * @return la prima stringa oggetto di allineamento 
     */
    public String getSequence1();
    
    /**
     * Restituisce la seconda sequenza associata a questo aligner.
     * 
     * @return la seconda stringa oggetto di allineamento 
     */
    public String getSequence2();
    
    /**
     * Restituisce la funzione di costo associata a questo aligner. 
     * 
     * @return la funzione di costo associata a questo aliner.
     */
    public ScoringFunction getScoringFunction();

    /**
     * Esegue l'allineamento delle sequenze.
     */
    public void align();

    /**
     * Determina se l'allineamento è stato eseguito.
     * 
     * @return true se l'allineamento è stato eseguito, false
     *         altrimenti
     */
    public boolean isAligned();
    
    /**
     * Restituisce la prima sequenza allineata, ovvero la prima sequenza di
     * partenza che contiene eventualmente dei gap.
     * 
     * @return la prima sequenza allineata
     * 
     * @throws IllegalStateException
     *                                   se l'allineamento non è
     *                                   ancora stato eseguito
     */
    public String getAlignedSequence1();

    /**
     * Restituisce la seconda sequenza allineata, ovvero la seconda sequenza di
     * partenza che contiene eventualmente dei gap.
     * 
     * @return la seconda sequenza allineata
     * 
     * @throws IllegalStateException
     *                                   se l'allineamento non è
     *                                   ancora stato eseguito
     */
    public String getAlignedSequence2();

    /**
     * Restituisce la distanza di allineamento calcolata.
     * 
     * @return il costo minimo per le operazioni di allineamento.
     * 
     * @throws IllegalStateException
     *                                   se l'allineamento non è
     *                                   ancora stato eseguito
     */
    public double getDistance();

}