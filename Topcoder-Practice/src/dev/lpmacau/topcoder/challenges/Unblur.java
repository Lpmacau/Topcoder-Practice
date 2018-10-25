package dev.lpmacau.topcoder.challenges;

public class Unblur {
	public String[] original(String[] blurred) {
		int nLi = blurred.length;
		int nCol = blurred[0].length();
		int i,j,k;
		
		String[] unblurred = new String[nLi];
		
		System.out.println("Nli: "+ nLi+", Ncol: "+nCol);
		
		for(i = 0; i<nLi ; i++) {
			unblurred[i] = blurred[i];
		}
		

		// Pintar top line
		String blurredLine = blurred[0];
		StringBuilder botLine = new StringBuilder(unblurred[1]);
		StringBuilder add = new StringBuilder();
		for(i = 0; i<nCol-1; i++) {
			if(blurredLine.charAt(i)=='0') {

				botLine.setCharAt(i, '.');
				botLine.setCharAt(i+1, '.');
				botLine.setCharAt(i-1, '.');
			}
			add.append('.');
		}
		unblurred[0] = add.toString();
		unblurred[1] = botLine.toString();
		
		
		
		// Pintar meio
		for(i = 1; i<nLi -1 ; i++) {
			String line = blurred[i];
			StringBuilder topLine = new StringBuilder(unblurred[i-1]);
			botLine = new StringBuilder(unblurred[i+1]);
			StringBuilder resLine = new StringBuilder(unblurred[i]);
			for(j = 0; j<nCol-1 ; j++) {
				if(j==0) {
					resLine.setCharAt(0,'.');
				}
				
				else if(line.charAt(j) == '0') {
					resLine.setCharAt(j, '.');
					
					if(resLine.charAt(j+1) != '0') resLine.setCharAt(j+1, '.');
					if(resLine.charAt(j-1) != '0') resLine.setCharAt(j-1, '.');

					if(topLine.charAt(j+1) != '0' ) topLine.setCharAt(j+1, '.');
					if(topLine.charAt(j-1) != '0') topLine.setCharAt(j-1, '.');
					if(topLine.charAt(j) != '0') topLine.setCharAt(j, '.');

					if(botLine.charAt(j+1) != '0') botLine.setCharAt(j+1, '.');
					if(botLine.charAt(j-1) != '0') botLine.setCharAt(j-1, '.');
					if(botLine.charAt(j) != '0') botLine.setCharAt(j, '.');
				}
					
			}
			
			unblurred[i] = resLine.toString();
			unblurred[i-1] = topLine.toString();
			//unblurred[i+1] = botLine.toString();
		}
		
		
		// Pintar ultima linha
		StringBuilder add2 = new StringBuilder();
		blurredLine = blurred[nLi-1];
		for(i = 0; i<nCol-1; i++) {
			add2.append('.');
		}
		unblurred[nLi-1] = add.toString();
		
		
		//Pintar parede da direita e substituir nums por cardinais
		for(i = 1; i<nLi; i++) {
			StringBuilder nova = new StringBuilder();
			nova.append(unblurred[i]).setCharAt(unblurred[i].length()-1, '.');
			unblurred[i] = nova.toString();
			//unblurred[i] = nova.toString().replaceAll("[0-9]", "#");
			
		}
		
		
		// Criar matriz de impossiveis (-1 = 0 na blurred, 0 = impossivel, 1+ = n possibilidades)
		int[][] oriMatrix = new int[nLi][nCol];
		int[][] possiMatrix = new int[nLi][nCol];
		String[][] resMatrix = new String[nLi][nCol];
		
		for(i = 0; i<nLi ; i++) {
			// Preencher com ints
			String l = blurred[i];
			
			for(k = 0 ; k<l.length(); k++) {
				possiMatrix[i][k] = oriMatrix[i][k] = Integer.parseInt(""+l.charAt(k));
			}
		}
		
		
		// Percorrer matriz original
		// Se encontrar 0 , colocar impossibilidade nesse ponto 
		for(i = 0; i<oriMatrix.length; i++) {
			for(j = 0; j<oriMatrix[i].length; j++) {
				
				if(oriMatrix[i][j]==0) {
					possiMatrix[i][j] = -1;
				}
			}
		}
		
		// Percorrer matriz de possibilidades
		// Se encontrar -1, colocar 0 (impossivel) nas casas adjacentes
		for(i = 0; i<possiMatrix.length; i++) {
			for(j = 0; j<possiMatrix[i].length; j++) {
				
				if(possiMatrix[i][j]==-1) {
					
					// Topo
					if(i==0) {
						// Ponta esquerda
						if(j==0) {
							if(possiMatrix[i][j+1] > 0 ) possiMatrix[i][j+1] = 0;
							if(possiMatrix[i+1][j] > 0 ) possiMatrix[i+1][j] = 0;
							if(possiMatrix[i+1][j+1] > 0) possiMatrix[i+1][j+1] = 0;
						}
						
						// Ponta direita
						else if(j==possiMatrix[i].length-1) {
							if(possiMatrix[i][j-1] > 0) possiMatrix[i][j-1] = 0;
							if(possiMatrix[i+1][j] > 0) possiMatrix[i+1][j] = 0;
							if(possiMatrix[i+1][j-1] > 0 ) possiMatrix[i+1][j-1] = 0;
						}
						
						// Intermédios
						else {
							if(possiMatrix[i][j+1] > 0) possiMatrix[i][j+1] = 0;
							if(possiMatrix[i][j-1] > 0) possiMatrix[i][j-1] = 0;
							if(possiMatrix[i+1][j-1] > 0) possiMatrix[i+1][j-1] = 0;
							if(possiMatrix[i+1][j+1] > 0) possiMatrix[i+1][j+1] = 0;
							if(possiMatrix[i+1][j] > 0) possiMatrix[i+1][j] = 0;
						}
					}
					
					
					// Fundo
					else if(i==possiMatrix.length-1) {
						// Ponta esquerda
						if(j==0) {
							if(possiMatrix[i][j+1] > 0 ) possiMatrix[i][j+1] = 0;
							if(possiMatrix[i-1][j] > 0 ) possiMatrix[i-1][j] = 0;
							if(possiMatrix[i-1][j+1] > 0) possiMatrix[i-1][j+1] = 0;
						}
						
						// Ponta direita
						else if(j==possiMatrix[i].length-1) {
							if(possiMatrix[i][j-1] > 0) possiMatrix[i][j-1] = 0;
							if(possiMatrix[i-1][j] > 0) possiMatrix[i-1][j] = 0;
							if(possiMatrix[i-1][j-1] > 0 ) possiMatrix[i-1][j-1] = 0;
						}
						
						// Intermédios
						else {
							if(possiMatrix[i][j+1] > 0) possiMatrix[i][j+1] = 0;
							if(possiMatrix[i][j-1] > 0) possiMatrix[i][j-1] = 0;
							if(possiMatrix[i-1][j-1] > 0) possiMatrix[i-1][j-1] = 0;
							if(possiMatrix[i-1][j+1] > 0) possiMatrix[i-1][j+1] = 0;
							if(possiMatrix[i-1][j] > 0) possiMatrix[i-1][j] = 0;
						}
					}
					
					
					// Restantes linhas
					else {
						// Ponta esquerda
						if(j==0) {
							if(possiMatrix[i][j+1] > 0 ) possiMatrix[i][j+1] = 0;
							if(possiMatrix[i-1][j] > 0 ) possiMatrix[i-1][j] = 0;
							if(possiMatrix[i-1][j+1] > 0) possiMatrix[i-1][j+1] = 0;
							if(possiMatrix[i+1][j+1] > 0) possiMatrix[i+1][j+1] = 0;
							if(possiMatrix[i+1][j] > 0) possiMatrix[i+1][j] = 0;
							
						}
						
						// Ponta direita
						else if(j==possiMatrix[i].length-1) {
							if(possiMatrix[i][j-1] > 0) possiMatrix[i][j-1] = 0;
							if(possiMatrix[i-1][j] > 0) possiMatrix[i-1][j] = 0;
							if(possiMatrix[i-1][j-1] > 0 ) possiMatrix[i-1][j-1] = 0;
							if(possiMatrix[i+1][j-1] > 0 ) possiMatrix[i+1][j-1] = 0;
							if(possiMatrix[i+1][j] > 0 ) possiMatrix[i+1][j] = 0;
						}
					
						// Intermedios
						else {
							if(possiMatrix[i][j+1] > 0) possiMatrix[i][j+1] = 0;
							if(possiMatrix[i][j-1] > 0) possiMatrix[i][j-1] = 0;
							if(possiMatrix[i-1][j-1] > 0) possiMatrix[i-1][j-1] = 0;
							if(possiMatrix[i-1][j+1] > 0) possiMatrix[i-1][j+1] = 0;
							if(possiMatrix[i-1][j] > 0) possiMatrix[i-1][j] = 0;
							if(possiMatrix[i+1][j+1] > 0 ) possiMatrix[i+1][j+1] = 0;
							if(possiMatrix[i+1][j-1] > 0 ) possiMatrix[i+1][j-1] = 0;
							if(possiMatrix[i+1][j] > 0 ) possiMatrix[i+1][j] = 0;
						}
						
					}
					

					possiMatrix[i][j] = 0;
					
					
				}
			}
		}
		
		// meter 0 nas restantes casas das bordas
		// Top
		for(j=0; j<nCol ;j++) {
			possiMatrix[0][j] = 0;
		}
		// bot
		for(j=0; j<nCol ;j++) {
			possiMatrix[nLi-1][j] = 0;
		}
		// esq
		for(i=0; i<nLi; i++) {
			possiMatrix[i][0] = 0;
		}
		// dir
		for(i=0; i<nLi; i++) {
			possiMatrix[i][nCol-1] = 0;
		}
		
		
		// Copiar matriz de possibilidades para matriz resultado
		for(i=0; i<nLi; i++) {
			for(j=0; j<nCol; j++) {
				resMatrix[i][j] = String.valueOf(possiMatrix[i][j]);
			}
		}
		
		// Percorrer matriz em busca do caso certo
		// Se a casa estiver rodeada de um nº de casas
		// Inferior ao seu valor, essa casa é um #
		//	4 4 4
		//  0 5 0
		//  0 3 0 
		// Sabemos que o 5 é cardinal porque existem 4 casas de possibilidade
		for(i=1; i<nLi; i++) {
			for(j=1; j<nCol; j++) {
				// Verificar se a casa é uma possibilidade
				if(possiMatrix[i][j] > 0 ) {
					int nPossi = 0;
					if(possiMatrix[i][j+1] > 0 ) nPossi++;
					if(possiMatrix[i][j-1] > 0 ) nPossi++;
					
					if(possiMatrix[i+1][j] > 0 ) nPossi++;
					if(possiMatrix[i+1][j+1] > 0 ) nPossi++;
					if(possiMatrix[i+1][j-1] > 0 ) nPossi++;
				
					if(possiMatrix[i-1][j] > 0 ) nPossi++;
					if(possiMatrix[i-1][j+1] > 0 ) nPossi++;
					if(possiMatrix[i-1][j-1] > 0 ) nPossi++;
				
					if(nPossi < possiMatrix[i][j] || nPossi==1) 
						resMatrix[i][j] = "#";//+resMatrix[i][j];
				}
			}
		}
		
		
		// Percorrer novamente a matriz
		// Atraves dos # obrigatorios
		// Identificar casas com obrigacao tambem
		// 0 4 0
		// 0 3# 4#
		// 0 0 0
		// Segundo o 3#, existem 3 # no quadrado
		// Apenas existe uma possiblidade, o 4
		// Logo o 4 tem de ser #
		for(i=1; i<nLi; i++) {
			for(j=1; j<nCol; j++) {
				if(resMatrix[i][j] == "#") {
					int nPossi = 1;
					boolean[] sub = new boolean[8];
					sub[0] = sub[1] = sub[2] = sub[3] = sub[4] = sub[5] = sub[6] = sub[7] = false; 
					
					//posi 0
					if(possiMatrix[i][j+1] > 0 || resMatrix[i][j+1] == "#") {
						nPossi++; 
						sub[0] = true;
					}
					//posi 1
					if(possiMatrix[i][j-1] > 0 || resMatrix[i][j-1] == "#") {
						nPossi++;
						sub[1] = true;
					}
					
					//posi 2
					if(possiMatrix[i+1][j] > 0 || resMatrix[i+1][j] == "#"){
						nPossi++;
						sub[2] = true;
					}
					// posi 3
					if(possiMatrix[i+1][j+1] > 0 || resMatrix[i+1][j+1] == "#") {
						nPossi++;
						sub[3] = true;
					}
					// posi 4
					if(possiMatrix[i+1][j-1] > 0 || resMatrix[i+1][j-1] == "#") {
						nPossi++;
						sub[4] = true;
					}
				
					//posi 5
					if(possiMatrix[i-1][j] > 0 || resMatrix[i-1][j] == "#") {
						nPossi++;
						sub[5] = true;
					}
					//posi 6
					if(possiMatrix[i-1][j+1] > 0 || resMatrix[i-1][j+1] == "#") {
						nPossi++;
						sub[6] = true;
					}
					//posi 7
					if(possiMatrix[i-1][j-1] > 0 || resMatrix[i-1][j-1] == "#") {
						nPossi++;
					}
					
					if(nPossi == possiMatrix[i][j]) {

						// direita
						if(sub[0]) resMatrix[i][j+1] = "#";
						// esq
						if(sub[1]) resMatrix[i][j-1] = "#";
						

						// baixo
						if(sub[2]) resMatrix[i+1][j] = "#";
						// baixo direito
						if(sub[3]) resMatrix[i+1][j+1] = "#";
						// baixo esquerda
						if(sub[4]) resMatrix[i+1][j-1] = "#";

						// cima
						if(sub[5]) resMatrix[i-1][j] = "#";
						// cima esq
						if(sub[6]) resMatrix[i-1][j+1] = "#";
						// cima dir
						if(sub[7]) resMatrix[i-1][j-1] = "#";
					}
				}
			}
		}
		
		// Percorrer novamente a matriz à procura de 1's
		// Se o 1 estiver rodeado de um #, nao pode ser #
		//
		// 2 # 0	2 # 0 
		// 0 1 0 -> 0 0 0
		// 0 3 2    0 3 2
		for(i=1; i<nLi; i++) {
			for(j=1; j<nCol; j++) {
				try {
					if(Integer.parseInt(resMatrix[i][j]) == 1) {
						// Pesquisar # nas redondezas

						if(resMatrix[i][j+1] == "#"
								|| resMatrix[i][j-1] == "#"
								|| resMatrix[i+1][j] == "#"
								|| resMatrix[i+1][j+1] == "#"
								|| resMatrix[i+1][j-1] == "#"
								|| resMatrix[i-1][j] == "#"
								|| resMatrix[i-1][j+1] == "#"
								|| resMatrix[i-1][j-1] == "#")
							resMatrix[i][j] = "0";}
				} catch (Exception e) {
					
				}
				
			}
		}
		
		
		// Percorrer novamente, desta vez calculando a média do ponto
		// Dividir por 9, verificar o mesmo para os pontos adjacentes
		// Se for o valor com a média mais baixa, trocar por #

		for(i=1; i<nLi; i++) {
			double minAvg;
			double avg = 0;
			double a = 0;
			
			for(j=1; j<nCol; j++) {
				
				// Ignorar os 0's e #
				try {
					if(Integer.parseInt(resMatrix[i][j]) > 0) {
						minAvg = 1000;	
						avg = calculateAverage(possiMatrix,i,j);
						
						if((a = calculateAverage(possiMatrix,i,j+1))<minAvg) minAvg = a;
						if((a = calculateAverage(possiMatrix,i,j-1))<minAvg) minAvg = a;
						
						if((a = calculateAverage(possiMatrix,i+1,j+1))<minAvg) minAvg = a;
						if((a = calculateAverage(possiMatrix,i+1,j-1))<minAvg) minAvg = a;
						if((a = calculateAverage(possiMatrix,i+1,j))<minAvg) minAvg = a;
						
						if((a = calculateAverage(possiMatrix,i-1,j))<minAvg) minAvg = a;
						if((a = calculateAverage(possiMatrix,i-1,j+1))<minAvg) minAvg = a;
						if((a = calculateAverage(possiMatrix,i-1,j-1))<minAvg) minAvg = a;
						
						if(avg==minAvg) 
							resMatrix[i][j] = "#";
						//else resMatrix[i][j] = "0";
					}
						 
				} catch (Exception e) {
					
				}
			}	
		}
		
		

		for(i=1; i<nLi; i++) {
			for(j=1; j<nCol; j++) {
				try {
					
					
					if(resMatrix[i][j] != "#" && Integer.parseInt(resMatrix[i][j]) > 0)
						if(Integer.parseInt(resMatrix[i][j]) - Integer.parseInt(resMatrix[i][j-1]) > 2
							|| Integer.parseInt(resMatrix[i][j]) - Integer.parseInt(resMatrix[i][j+1]) > 2
							|| Integer.parseInt(resMatrix[i][j]) - Integer.parseInt(resMatrix[i+1][j]) > 2
							|| Integer.parseInt(resMatrix[i][j]) - Integer.parseInt(resMatrix[i-1][j]) > 2)
								resMatrix[i][j] = "#";
					


//					if(Integer.parseInt(resMatrix[i][j]) == 3) {
//						if(Integer.parseInt(resMatrix[i][j-1]) == 3 && Integer.parseInt(resMatrix[i][j+1]) == 3 )
//							resMatrix[i][j] = "0";
//					}
				}
				catch (Exception e) {
					
				}
			}	
		}
		
		// Preencher restantes obrigatorios
		//  # 0 0
		//  3 4 4
		//  # # 4
		
		
		System.out.println("MATRIX ORI");
		for (i = 0; i < oriMatrix.length; i++) {
			  for (j = 0; j < oriMatrix[i].length; j++) {
			    System.out.print(oriMatrix[i][j] + " ");
			  }
			  System.out.println();	  
		}
		

		
		System.out.println("MATRIX Possiblidades");
		for (i = 0; i < possiMatrix.length; i++) {
			  for (j = 0; j < possiMatrix[i].length; j++) {
			    System.out.print(possiMatrix[i][j] + " ");
			  }
			  System.out.println();	  
		}
		
		System.out.println("MATRIX Resultados");
		for (i = 0; i < resMatrix.length; i++) {
			  for (j = 0; j < resMatrix[i].length; j++) {
			    System.out.print(resMatrix[i][j] + " ");
			  }
			  System.out.println();	  
		}
		
		 
		String[] matrixStringed = new String[nLi];
		for (i = 0; i < resMatrix.length; i++) {
			StringBuilder str = new StringBuilder();
			  for (j = 0; j < resMatrix[i].length; j++) {
				  if(resMatrix[i][j].chars().allMatch(Character::isDigit))  str.append('.'); //  && Integer.valueOf(resMatrix[i][j]) == 0)
				  //if(resMatrix[i][j].chars().allMatch(Character::isDigit)  && Integer.valueOf(resMatrix[i][j]) == 0)  str.append('.'); // 
				  else str.append(resMatrix[i][j]);
			  }
			  matrixStringed[i] = str.toString();
		}
		
		
		
		
		return matrixStringed;
	}


	private double calculateAverage(int[][] possiMatrix, int i, int j) {
		// ignore if it is a 0
		if(possiMatrix[i][j] == 0)
			return 1000;
		
		double total = 0;
		if(possiMatrix[i][j+1] > 0) total+= possiMatrix[i][j+1];
		if(possiMatrix[i][j-1] > 0) total+= possiMatrix[i][j-1];
		if(possiMatrix[i+1][j] > 0) total+= possiMatrix[i+1][j];
		if(possiMatrix[i+1][j+1] > 0) total+= possiMatrix[i+1][j+1];
		if(possiMatrix[i+1][j-1] > 0) total+= possiMatrix[i+1][j-1];
		if(possiMatrix[i-1][j] > 0) total+= possiMatrix[i-1][j];
		if(possiMatrix[i-1][j+1] > 0) total+= possiMatrix[i-1][j+1];
		if(possiMatrix[i-1][j-1] > 0) total+= possiMatrix[i-1][j-1];
	
		return total/9;
	}
}