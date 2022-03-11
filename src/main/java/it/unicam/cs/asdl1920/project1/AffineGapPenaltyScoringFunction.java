/**
 * 
 */
package it.unicam.cs.asdl1920.project1;

/**
 * Funzione di scoring con gap penalty affine.
 * 
 * @author Luca Tesei
 *
 */
public class AffineGapPenaltyScoringFunction implements ScoringFunction {
    /**
     * Parametro alpha della funzione affine, determina il costo per iniziare
     * una nuova sequenza di gap.
     */
    public static double PARAMETER_ALPHA = 4;

    /**
     * Parametro beta della funzione affine, determina il costo di introduzione
     * di un ulteriore gap in una sequenza già iniziata.
     */
    public static double PARAMETER_BETA = 1;

    @Override
    public double w(char symbol1, char symbol2) {
    	
    	//match
    	if(symbol1 == symbol2) 
    		return 0;
    	
    	//sostituzione
    	return 1;
    }

    @Override
    public boolean hasGapPenalty() {
        return true;
    }

    @Override
    public double g(int n) {
        return PARAMETER_ALPHA + n * PARAMETER_BETA;
    }

}
