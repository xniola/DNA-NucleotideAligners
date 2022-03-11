
package it.unicam.cs.asdl1920.project1;

/**
 * Interfaccia che astrae una funzione di scoring. Può rappresentare una
 * funzione di scoring semplice oppure con gap penalty.
 * 
 * @author Luca Tesei
 *
 */
public interface ScoringFunction {
    /**
     * Simbolo per indicare il gap. 
     */
    public static char GAP_SYMBOL = '-';

    /**
     * Funzione costo per una singola operazione: match (dovrebbe essere 0),
     * sostituzione, cancellazione e inserimento. Qualora ci sia la funzione con
     * gap penalty associata, è necessario solo dare il costo di match e
     * sostituzione. 
     * 
     * @param symbol1
     *                    primo simbolo dell'operazione (può essere il gap)
     * @param symbol2
     *                    secondo simbolo dell'operazione (può essere il gap)
     * @return il costo dell'operazione (symbol1,symbol2).
     */
    public double w(char symbol1, char symbol2);

    /**
     * Determina se questa scoring function ha una funzione di gap penalty.
     * 
     * @return true se questa scoring function ha una funzione di gap penalty,
     *         false altrimenti.
     */
    public boolean hasGapPenalty();

    /**
     * Funzione di gap penalty (opzionalmente presente).
     * 
     * @param n
     *              numero di gap consecutivi di cui valutare il costo
     * @return costo di n gap consecutivi in un allineamento
     * @throws UnsupportedOperationException
     *                                           se questa funzione di scoring
     *                                           non ha la funzione di gap
     *                                           penalty associata, come è
     *                                           possibile determinare con il
     *                                           metodo hasGapPenalty()
     */
    public double g(int n);
}