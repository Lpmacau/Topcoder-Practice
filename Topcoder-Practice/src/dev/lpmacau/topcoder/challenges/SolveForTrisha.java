package dev.lpmacau.topcoder.challenges;

public class SolveForTrisha{
    public static void main(String args[]){

//    	System.out.println(findSolution(1));
//    	System.out.println("----------------------");
//   	System.out.println(findSolution(10));
//    	System.out.println("----------------------");
    	System.out.println(findSolution(3));
//    	System.out.println("----------------------");
//    	S0ystem.out.println(findSolution(4));
    }
    
    public static int findSolution(int n){
    	double res = 1; 
        double alfa, alfaSq;
    	double quo = Math.toRadians(360/n);
        for(int k = 0 ; k<n ; k++){
        	alfa = unityRoot(k,quo);
        	alfaSq = Math.pow(alfa, 2);
        	if(alfaSq == 1) res *= alfaSq;
        	else res *= (1- alfaSq);
        }
        
        if(res == Math.floor(res)) return (int) res;
        return -1;
    }
    
    private static double unityRoot(int k, double quo) {
    	double cos = Math.cos(k*quo);
    	double sin = Math.sin(k*quo);
    	double res = cos + sin;
    	
    	int rounded = (int) Math.round(res);
    	

    	if(Math.abs(Math.round(res) - res) < 0.00001) return Math.round(res);
		return res;
    }

//	private static double unityRoot(int k) {
//		double graus = 360/k;
//		
//		
//		double cos = Math.cos(Math.toRadians(graus));
//		double sin = Math.sin(Math.toRadians(graus));
//		
//		double res = cos + sin;
//		res = Math.abs(res);
//		System.out.println("Rounded "+Math.round(res));
//		System.out.println(k+" -> "+res);
//		
//		
//		if(Math.abs(Math.round(res) - res) < 0.00001) return Math.round(res);
//		return res;
//	}
    
    
    

    
}