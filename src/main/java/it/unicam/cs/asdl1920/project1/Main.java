package it.unicam.cs.asdl1920.project1;

public class Main {
	
	private static void stampa(double[][] D) {
	 for (int i = 0; i < D.length; i++) {
    		System.out.println();
            for (int j = 0; j < D[i].length; j++) {
                System.out.print("["+D[i][j] + "]");
            }
    	}
	 System.out.println();
	}
	

	public static void main(String[] args) {	
		
		/*
		NeedlemanWunschAligner aligner = new NeedlemanWunschAligner("AATACTGCATGCATGCATCG", "AAATCGTACGTACGTACGAC", new SimpleScoringFunction());
		aligner.align();
		System.out.println(aligner.getAlignedSequence1());
		System.out.println(aligner.getAlignedSequence2());
		System.out.println("Costo di allineamento: "+aligner.getDistance());
		*/
		
		
		GotohAligner gotoh = new GotohAligner("CCCATCGATGCCACGACCACAGCTAGCT", "CGCATCACAGTCAGCTACGATCATCG", new AffineGapPenaltyScoringFunction());
		gotoh.align();
		stampa(gotoh.D);
		stampa(gotoh.P);
		stampa(gotoh.Q);
		System.out.println(gotoh.getAlignedSequence1());
		System.out.println(gotoh.getAlignedSequence2());
		System.out.println("Costo di allineamento: "+gotoh.getDistance());
		
		
		
		
		
		
	}
}
