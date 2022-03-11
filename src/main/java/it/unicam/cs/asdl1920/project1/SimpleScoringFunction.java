/**
 * 
 */
package it.unicam.cs.asdl1920.project1;

/**
 * Funzione di scoring senza con funzione di gap penalty affine.
 * 
 * @author Luca Tesei
 *
 */
public class SimpleScoringFunction implements ScoringFunction {

    @Override
    public double w(char symbol1, char symbol2) {
        
    	//match
    	if(symbol1 == symbol2) 
    		return 0;
    	
    	return 1;
        
    }

    @Override
    public boolean hasGapPenalty() {
        return false;
    }

    @Override
    public double g(int n) {
        throw new UnsupportedOperationException("Questa scoring function non ha la funzione di gap penalty associata");
    }

}
