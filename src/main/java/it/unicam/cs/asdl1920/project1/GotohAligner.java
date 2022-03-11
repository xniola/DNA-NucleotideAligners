/**
 * 
 */
package it.unicam.cs.asdl1920.project1;

/**
 * Algoritmo di allineamento di sequenze di Gotoh. Usa una funzione di scoring
 * con gap penalty affine.
 * 
 * @author Luca Tesei
 *
 */
public class GotohAligner implements SequenceAligner {

    private String sequence1;

    private String sequence2;

    private ScoringFunction sf;
    
    private boolean isAligned;
    
    // TODO mettere private
    public double[][] D;
    public double[][] P;
    public double[][] Q;

    /**
     * Costruisce un aligner che usa l'algoritmo di Gotoh.
     * 
     * @param sequence1
     *                      la prima sequenza da allineare
     * @param sequence2
     *                      la seconda sequenza da allineare
     * @param sf
     *                      la funzione costo, deve avere una funzione di gap
     *                      penalty affine
     * 
     * @throws IllegalArgumentException
     *                                      se la funzione di scoring passata
     *                                      non ha incorporata una funzione di
     *                                      gap penalty
     * @throws NullPointerException
     *                                      se uno qualsiasi degli argomenti è
     *                                      null
     */
    public GotohAligner(String sequence1, String sequence2,ScoringFunction sf) {
    	if(!sf.hasGapPenalty())
    		throw new IllegalArgumentException("La funzione di scoring non ha la gap penalty function");
    		
    	if(sequence1 == null || sequence2 == null || sf == null)
         	throw new NullPointerException("Almeno uno degli argomenti è null");        
    	
    	this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.sf = sf;
        
        this.isAligned = false;
        this.D = new double[sequence1.length()+1][sequence2.length()+1];
        this.P = new double[sequence1.length()+1][sequence2.length()+1];
        this.Q = new double[sequence1.length()+1][sequence2.length()+1];
    }

    @Override
    public String getName() {
        return "Gotoh Aligner";
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
    
    
    private void traceback() {
    	String alignedSeq1 = "";
		String alignedSeq2 = "";

		int x = this.sequence1.length();
		int y = this.sequence2.length();

		int vincitore = 1;
		
		while(x > 0 && y > 0) {
			System.out.println(vincitore);
			
			switch(vincitore) {
    		case 1:
    			// da D
    			if(this.D[x][y] == this.D[x-1][y-1] + this.sf.w(this.sequence1.charAt(x-1),this.sequence2.charAt(y-1)) ){
    				alignedSeq1 = this.sequence1.charAt(x-1) + alignedSeq1;
    				alignedSeq2 = this.sequence2.charAt(y-1) + alignedSeq2;
    				x--;
    				y--;
    				vincitore = 1;
    			}
    		
    			// da Q
    			else if(this.D[x][y] == this.Q[x][y])
    				vincitore = 3;
    			
    			// da P
    			else //(this.D[x][y] == this.P[x][y]){
    				vincitore = 2;
    			break;
    		case 2:
    			// da D
    			if(this.P[x][y] == this.D[x-1][y] + this.sf.g(1)){
    				alignedSeq1 = this.sequence1.charAt(x-1) + alignedSeq1;
    				alignedSeq2 = ScoringFunction.GAP_SYMBOL + alignedSeq2;
    				x--;
    				vincitore = 1;
    			}
    		
    			// da P
    			else //(this.P[x][y] == this.P[x-1][y] + AffineGapPenaltyScoringFunction.PARAMETER_BETA)
    				{
    				alignedSeq1 = this.sequence1.charAt(x-1) + alignedSeq1;
    				alignedSeq2 = ScoringFunction.GAP_SYMBOL + alignedSeq2;
    				x--;
    				vincitore = 2;
    			}
    			break;
    		case 3:
    			// da D
    			if(this.Q[x][y] == this.D[x][y-1] + this.sf.g(1)){
    				alignedSeq1 = ScoringFunction.GAP_SYMBOL + alignedSeq1;
    				alignedSeq2 = this.sequence1.charAt(y-1) + alignedSeq2;
    				
    			
    				y--;
    				vincitore = 1;
    			}
    		
    			
    			// da Q
    			else //(this.Q[x][y] == this.Q[x][y-1] + AffineGapPenaltyScoringFunction.PARAMETER_BETA)
    				{
    				alignedSeq1 = ScoringFunction.GAP_SYMBOL + alignedSeq1;
    				alignedSeq2 = this.sequence1.charAt(y-1) + alignedSeq2;
    				y--;
    				vincitore = 3;
    			}
    			break;
    		default:
    			return;
    	}
			System.out.println(x+" "+y);
		}
		
		
		while(x > 0) {
			alignedSeq1 = this.sequence1.charAt(x-1) + alignedSeq1;
			alignedSeq2 = ScoringFunction.GAP_SYMBOL + alignedSeq2;
			x--;
		}
		
		while (y > 0) {		 
			alignedSeq2 = this.sequence2.charAt(y-1) + alignedSeq2;
			alignedSeq1 = ScoringFunction.GAP_SYMBOL + alignedSeq1;
			y--;
		}
		
		
		this.sequence1 = alignedSeq1;
		this.sequence2 = alignedSeq2;
    }
    
    
    @Override
    public void align() {
    	
    	if(this.sequence1 == this.sequence2) {
    		this.isAligned = true;
    		return;
    	}
    	
    	
    	int n = this.sequence1.length();
    	int m = this.sequence2.length();
    	
    	// inizializzo D
    	for(int i = 1; i < n+1; i++)
    		this.D[i][0] = this.sf.g(i);
    			
    	for(int j = 1; j < m+1; j++)
    		this.D[0][j] = this.sf.g(j);
    	
    	// inizializzo P
    	for(int i = 1; i < m+1; i++) 
            P[0][i] = Integer.MAX_VALUE;
        
    	
    	// inizializzo Q
    	for(int i = 1; i < n+1; i++) 
            Q[i][0] = Integer.MAX_VALUE;

    	
    	// costruisco le matrici
    	   for(int i = 1; i < n+1; i++) {
               for(int j = 1; j < m+1; j++) {	 
                   P[i][j] = Math.min(D[i-1][j]+this.sf.g(1),P[i-1][j]+AffineGapPenaltyScoringFunction.PARAMETER_BETA);
          
                   Q[i][j] = Math.min(D[i][j-1]+this.sf.g(1),Q[i][j-1]+AffineGapPenaltyScoringFunction.PARAMETER_BETA);
              
                   D[i][j] = Math.min(Math.min(
                		   D[i-1][j-1]+this.sf.w(this.sequence1.charAt(i-1), this.sequence2.charAt(j-1)),
                		   P[i][j]),
                		   Q[i][j]
                		   );
               }
           }
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
    		throw new IllegalStateException("Non è ancora stato eseguito l'allineamento");
    	
        return this.D[this.D.length-1][this.D[0].length-1];
    }

}
