package dev.lpmacau.topcoder.challenges;

public class Hex {
	
	
	public String[] picture(int n, String[] marks) {

		int li = n*3;
		int col = n*2 +1;
		String[][] m = drawCleanBoard(n);
		markSpots(m,marks);
		String[] res = new String[n*3];
		
		for(int i = 0; i<li;i++) {
			String oneLine = new String();
			
			for(int j = 0; j<col; j++) {
				oneLine = oneLine.concat(m[i][j]);
			}
			
			oneLine.trim();
			res[i] = oneLine;
		}
		
		res[0] = " "+res[0];
		
		int firstLine = li - (n-1) ;
		int k = 4;
		
		for(int i = li-1; i>=firstLine ; i--, k+=2) {
			res[i] = " "+res[i];
		
		}
		
		return res;	
	}
	
	private void markSpots(String[][] m, String[] marks) {
		for(String s : marks) {
			int li = Integer.parseInt(""+s.charAt(0));
			int col = Integer.parseInt(""+s.charAt(1));
			char marca = s.charAt(2);
			
			int nLi = li*2 +1;
			int nCol = col*2 +1+ li;
			
			m[nCol][nLi] = ""+marca;
		}
		
	}
	
	public String[][] drawCleanBoard(int n){
		int li = n*3;
		int col = n*2 +1;
		String [][] m = new String[li][col];
		
		//init with empty strings
		for(int i = 0; i<li; i++) {
			for(int j = 0; j<col; j++) {
				m[i][j] = "";
			}
		}
		
		// Top underscore diagonal
		for(int i = 1, j=0; i<col; i+=2, j++) {
			m[j][i] = "_";
		}
		
		
		String lastUsed = "";
		int lastUnderscore = 3;
		
		// linhas impares comecam com /
		for(int i = 1; i<li ;i+=2) {
			lastUsed = m[i][0] = "/";
			
			for(int j =1; j<col && j<lastUnderscore; j+=1) {
				//Colunas pares = / e \\
				if(j % 2 == 0) {
						if(lastUsed == "/") 
							lastUsed = m[i][j] = "\\";
						else if(lastUsed == "\\") 
							lastUsed = m[i][j] = "/";
				}
				
				//colunas impares = " " e _
				else if(j % 2 == 1) {
					if(m[i][j-1]=="\\") m[i][j] = "_";
					else m[i][j] = " ";
				}
			}
			
			if(lastUnderscore + 2 <= col) {
				lastUnderscore += 4;
			}
		}
		
		
		lastUnderscore = 1;
		// linhas pares comecam com \\
		for(int i = 2; i<li ;i+=2) {
			lastUsed = "/";

			if(lastUnderscore + 2 <= col) {
				lastUnderscore += 4;
			}
			
			for(int j = 0; j<col && j<lastUnderscore; j+=1) {

				//Colunas pares = /
				if(j % 2 == 0) {
					if(lastUsed=="/") {
						lastUsed = m[i][j] = "\\";
					}
					else if(lastUsed=="\\") {
						lastUsed = m[i][j] = "/";
					}
				}
				
				//colunas impares = \\
				else if(j % 2 == 1) {
					// se a pos anterior tiver \\, coloca se _ 
					if(m[i][j-1] == "\\") m[i][j] = "_";
					else m[i][j] = " ";
				}
			}
		}
		
		int firstLine = li - (n-1) ;
		int k = 4;
		
		for(int i = li-1; i>=firstLine ; i--, k+=2) {
			for(int j = col - k; j>=0 ;j--) {
				m[i][j] = "";
			}
		}
		return m;
	}
}
