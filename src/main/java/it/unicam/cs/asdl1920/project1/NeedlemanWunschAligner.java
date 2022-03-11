/**
 * 
 */
package it.unicam.cs.asdl1920.project1;



/**
 * Algoritmo di allineamento di sequenze di Needleman-Wunsch. Usa una funzione
 * di scoring semplice senza gap penalty.
 * 
 * @author Luca Tesei
 *
 */
public class NeedlemanWunschAligner implements SequenceAligner {

    private String sequence1;

    private String sequence2;

    private ScoringFunction sf;

    // inserire eventuali altre variabili istanza e relativi costruttori
    private double[][] D;
    private boolean isAligned;
   
    
    private void traceback() {
    	String new_seq1 = "";
		String new_seq2 = "";
		
		int i = this.sequence1.length();
		int x = this.sequence2.length();
		
		while (i > 0 && x > 0) {
			if(this.D[i][x] == this.D[i-1][x-1] +  this.sf.w(this.sequence1.charAt(i-1),this.sequence2.charAt(x-1))) {
				new_seq1 = this.sequence1.charAt(i-1) + new_seq1;
	            new_seq2 = this.sequence2.charAt(x-1) + new_seq2;
	            i--;
	            x--;
			} else if(this.D[i][x] == this.D[i-1][x] + 1) {
				new_seq1 = this.sequence1.charAt(i-1) + new_seq1;
				new_seq2 = ScoringFunction.GAP_SYMBOL + new_seq2;
				i--;
			}else if(this.D[i][x] == this.D[i][x-1] + 1){
				new_seq1 = ScoringFunction.GAP_SYMBOL + new_seq1;
				new_seq2 = this.sequence2.charAt(x-1) + new_seq2;
				x--;
			}
		}
		
		while(i > 0) {
			new_seq1 = this.sequence1.charAt(i-1) + new_seq1;
			new_seq2 = ScoringFunction.GAP_SYMBOL + new_seq2;
			i--;
		}
		
		while (x > 0) {		 
			new_seq2 = this.sequence2.charAt(x-1) + new_seq2;
			new_seq1 = ScoringFunction.GAP_SYMBOL + new_seq1;
			x--;
		}
		
		this.sequence1 = new_seq1;
		this.sequence2 = new_seq2;
    }
    

    /**
     * Costruisce un aligner che usa l'algoritmo di Needleman-Wunsch.
     * 
     * @param sequence1
     *                      la prima sequenza da allineare
     * @param sequence2
     *                      la seconda sequenza da allineare
     * @param sf
     *                      la funzione costo, ne basta una semplice senza gap
     *                      penalty
     * @throws NullPointerException
     *                                  se uno qualsiasi degli argomenti è null
     */
    public NeedlemanWunschAligner(String sequence1, String sequence2,ScoringFunction sf) {
    	
    	// controlli null
        if(sequence1 == null || sequence2 == null || sf == null)
        	throw new NullPointerException("Almeno uno degli argomenti è null");
        
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.sf = sf;
        
        // eventualmente completare
        
        // +1 per il carattere vuoto
        this.D = new double[this.sequence1.length()+1][this.sequence2.length()+1];
        this.isAligned = false;
    }
    
    @Override
    public String getName() {
    	return "Needleman-Wunsch Aligner";
    }

    @Override
    public String getSequence1() {
    	return this.sequence1;
    }

    @Override
    public String getSequence2() {
        return this.sequence2;
    }

    @Override
    public ScoringFunction getScoringFunction() {
        return this.sf;
    }
	
    @Override
    public void align() {
    	
    	if (this.sequence1 == this.sequence2) {
    		this.isAligned = true;
    		return;
    	}
    	
    	int n = this.sequence1.length();
    	int m = this.sequence2.length();
    		
    	// costruzione della matrice di allineamento D
    	
    	// costruisco la prima colonna (distanze fra a[i][j] con sequenza vuota)
    	for(int i = 0; i < n+1; i++)
    		this.D[i][0] = i;
    			
    	// costruisco la prima riga (distanze fra sequenza vuota e b[i][j]
    	for(int j = 0; j < m+1; j++)
    		this.D[0][j] = j;
    		
    	// costruisco il resto della matrice
    	for (int i = 1; i < n+1; i++) {
    		for(int j = 1; j < m+1; j++) {
    			D[i][j] = Math.min(
    					Math.min(
    					D[i-1][j-1] + this.sf.w(this.sequence1.charAt(i-1),this.sequence2.charAt(j-1)) ,
    					D[i-1][j] + 1),
    					D[i][j-1] + 1
    					);
    		}
    	}
    
    	//allineamento
        traceback();
        	
    	this.isAligned = true;
    }

    @Override
    public boolean isAligned() {
        return this.isAligned;
    }

    @Override
    public String getAlignedSequence1() {
        if(!isAligned())
        	throw new IllegalStateException("Non è ancora stato eseguito l'allineamento");
        
        return this.sequence1;
    }

    @Override
    public String getAlignedSequence2() {
    	if(!isAligned())
        	throw new IllegalStateException("Non è ancora stato eseguito l'allineamento");
        
        return this.sequence2;
    }
    
    @Override
    public double getDistance() {
    	if(!isAligned())
    		throw new IllegalStateException("Non è ancora stato esetuiro l'allineamento");
    	
    	if(this.sequence1 == this.sequence2)
    		return 0;
    	
    	double accumulatore = 0;
        for(int i = 0; i < this.sequence1.length(); i++)
        	accumulatore += this.sf.w(this.sequence1.charAt(i), this.sequence2.charAt(i));
    	
        return accumulatore;
    }
}
