
public class Sudoku {

	public static void main(String[] args){ 
		int[][] rightSol = {
				{5,3,4,6,7,8,9,1,2},
				{6,7,2,1,9,5,3,4,8},
				{1,9,8,3,4,2,5,6,7},
				{8,5,9,7,6,1,4,2,3},
				{4,2,6,8,5,3,7,9,1},
				{7,1,3,9,2,4,8,5,6},
				{9,6,1,5,3,7,2,8,4},
				{2,8,7,4,1,9,6,3,5},
				{3,4,5,2,8,6,1,7,9}};
		
		int[]a ={1,5,6,3,4};
		//checks if sort works
		int[]b = sort(a);
		for (int i=0;i<a.length;i++){
			System.out.print(b[i]);
		}
		System.out.println();
		
		System.out.println(uniqueEntries(a));
		
		int[][]x={{1,2,3},{4,5,6},{7,8,9}};
		//checks if column works
		int[]newColumn = getColumn(x,2);
		for (int i=0;i<newColumn.length;i++){
			System.out.print(newColumn[i]);
		}
		
		System.out.println();
		//this checks if method flatten works
		int[]newflatten = flatten(x);
		for(int i=0;i<x.length*x.length;i++){
			System.out.print(newflatten[i]);
		}
		System.out.println();
		
		//method takes a subGrid, flattens it and prints out the 
		//inputs in that subGrid
		int[] newColumnA = flatten(subGrid(rightSol,0,0,3));
		for(int i=0;i<newColumnA.length;i++){
			System.out.print(newColumnA[i]);
		}
		System.out.println();
		//System.out.println(Arrays.deepToString(subGrid(x,1,1,2)));
		//testing subGrid
		int[][] test = subGrid(rightSol,6,0,3);
		for(int i=0;i<test.length;i++){
			for(int j=0;j<test.length;j++){
				System.out.print(test[i][j]);
			}
			System.out.println();
		}
		System.out.println(isSudoku(rightSol));
		
	}
	
	//method verifies whether 2D array represents valid solution
	//return true if valid, else return false
	public static boolean isSudoku(int[][] input){
		boolean answer = true;
		if(input.length!=input[0].length){
			return false;
		}
		//method verifies whether input value is between 1-9
		for(int i=0;i<input.length;i++){
			for(int j=0;j<input[i].length;j++){
				if(input[i][j]<1 || input[i][j] >9){
					return false;
				}
			}
		}
		//method verifies whether the 9 3x3 subgrids have UniqueEntries
		for(int i=0;i<input.length;i=i+3){
			for(int j=0;j<input.length;j=j+3){
				if(uniqueEntries(flatten(subGrid(input,i,j,3)))){
					return true;
			}
		}
		//this checks whether 
		for(int w=0;w<input.length;w++){
		boolean x = uniqueEntries(input[w]);
		boolean y = uniqueEntries(getColumn(input,w));
		if(!x||!y){
		return !answer;
			}
		}
	}
		answer=false;
		return answer;
}
	//this array takes an input int[] and outputs int[], 
	//rearranging all the values in an increasing order
	public static int[] sort(int[] input){
		for(int i=0;i<input.length-1;i++){
			if(input[i]>input[i+1]){
				int temp = input[i];
				input[i]=input[i+1];
				input[i+1]=temp;
				if(i+1==input.length-1){
					i=0;
				}
			}
		}
		return input;
	}
	//method checks an array of integers and returns true if there are no duplicates 
	public static boolean uniqueEntries(int[] input){
		sort(input);
		boolean answer = true;
		for(int i=0;i<input.length-1;i++){
			if(input[i]==input[i+1]){
				answer = false;
			}
			else{
				answer = true;
			}
		}
		return answer;
	}
	//takes input array of integers as well as j,
	//returns an integer corresponding to the jth column in the array of arrays
	public static int[] getColumn(int[][]a, int j){
		int [] b = new int [a.length];
		for(int i=0;i<a.length;i++){
			b[i] = a[i][j];
		}
		return b;
	}
	//method that takes an input a square array(n x n) and of integer arrays, 
	// returns single array of length n x n
	/**this one has a problem*/
	public static int[] flatten(int[][]a){
		int []b = new int[a.length*a.length];
		int k=0;
		while(k<b.length){
			for(int i=0;i<a.length;i++){
				for(int j=0;j<a.length;j++){
					b[k]=a[i][j];
					k++;
				}
			}
		}
		return b;
		}
		
	//method takes input square array of integer arrays, two indices(i,j) 
	//and size m, this method would return array of integer arrays 
	//(m*m) corresponding to section in
	//original array
	public static int[][] subGrid(int[][]a, int i, int j, int m){
		int[][]b = new int[m][m];
		for(int row=0; row<m; row++){
			for(int column=0; column<m; column++){
						b[row][column]=a[row+i][column+j];
					}
				}
		return b;
	}
}
		

