import java.util.*;
//Starwork doesn't sound nice
//Support say... at most 2 non-1 path
//Because it's easier to implement by just use arrays

public class ones {
	public static int[][] h;
	public static void main(String[] args){
		//A firestar is just a int[3] array :D
		
		int n=Integer.parseInt(args[0]), m=Integer.parseInt(args[1]);
		h = new int[n+1][m+1];
		for(int i=0;i<n+1;i++){
			for(int j=0;j<m+1;j++){
				h[i][j] = -1;
			}
		}
		
		h[0][0] = 0;
		//sg(n,m);
		sg(n,m);
		for(int j=0;j<=m;j++){
			int max = 0;
			for(int i=0;i<=n;i++){
				System.out.print(h[i][j]+" ");
				max = Math.max(h[i][j], max);
			}
			System.out.println();
			//System.out.println(max);
		}
	}
	
	public static int sg(int x, int y){
		if(h[x][y]!=-1){
			return h[x][y];
		}
		//snap
		HashSet<Integer> s = new HashSet<Integer>();
		for(int i=0;i<x;i++){
			s.add(sg(i,y)^p(x-1-i));
		}
		for(int i=0;i<x-1;i++){
			s.add(sg(i,y)^p(x-2-i));
		}
		//blow
		for(int i=0;i<=y;i++){
			if(i!=y){
				s.add(sg(x,i));
			}
			if(x!=0){
				s.add(sg(0,i)^p(x-1));
			}
		}
		int k = mex(s);
		h[x][y] = k;
		return k;
	}
	
	public static int p(int i){
		if(i<0){
			return 0;
		}
		int[] z = {0, 1, 2, 3, 1, 4, 3, 2, 1, 4, 2, 6, 4, 1, 2, 7, 1, 
				4, 3, 2, 1, 4, 6, 7, 4, 1, 2, 8, 5, 4, 7, 2, 1, 8, 6, 7, 
				4, 1, 2, 3, 1, 4, 7, 2, 1, 8, 2, 7, 4, 1, 2, 8, 1, 4, 7,
				2, 1, 4, 2, 7, 4, 1, 2, 8, 1, 4, 7, 2, 1, 8, 6, 7};
		int[] y = {4,1,2,8,1,4,7,2,1,8,2,7};
		if(i>=72){
			return y[i%12];
		}
		return z[i];
	}
	
	public static int mex(HashSet<Integer> s){
		for(int i=0;i<s.size();i++){
			if(!s.contains(i)){
				return i;
			}
		}
		return s.size();
	}
}
