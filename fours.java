import java.util.*;
//Every the path length is at most 4.
public class fours {
	//number of 1,2,3,4's
	public static int[][][][] h;
	public static void main(String[] args){
		
		int n1=Integer.parseInt(args[0]);
		int n2=Integer.parseInt(args[1]);
		int n3=Integer.parseInt(args[2]);
		int n4=Integer.parseInt(args[3]);
		h = new int[n1+n2+n3+n4+1][n2+n3+n4+1][n3+n4+1][n4+1];
		for(int a4=0;a4<n4+1;a4++){
			for(int a3=0;a3<n3+n4+1;a3++){
				for(int a2=0;a2<n2+n3+n4+1;a2++){
					for(int a1=0;a1<n1+n2+n3+n4+1;a1++){
						h[a1][a2][a3][a4] = -1;
					}
				}
			}
		}
		h[0][0][0][0] = 0;
		sg(n1,n2,n3,n4);
		//output k,n2,n3,n4, 0<=k<=n1
		for(int i1=0;i1<=n1;i1++){
			System.out.println(h[i1][n2][n3][n4]);
		}
	}
	
	public static int sg(int n1, int n2, int n3, int n4){
		//System.out.println(n1+" "+n2+" "+n3+" "+n4);
		if(n4<0||n3<0||n2<0||n1<0){
			return -1;
		}
		if(h[n1][n2][n3][n4]!=-1){

			//System.out.println("orz");
			return h[n1][n2][n3][n4];
		}
		HashSet<Integer> s = new HashSet<Integer>();
		//snap 4
		if(n4>0){
			s.add(sg(n1,n2,n3+1,n4-1)^p(0));
			s.add(sg(n1,n2+1,n3,n4-1)^p(0));
			
			s.add(sg(n1,n2+1,n3,n4-1)^p(1));
			s.add(sg(n1+1,n2,n3,n4-1)^p(1));
			
			s.add(sg(n1+1,n2,n3,n4-1)^p(2));
			s.add(sg(n1,n2,n3,n4-1)^p(2));
		}
		//snap 3
		if(n3>0){
			s.add(sg(n1,n2+1,n3-1,n4)^p(0));
			s.add(sg(n1+1,n2,n3-1,n4)^p(0));
			
			s.add(sg(n1+1,n2,n3-1,n4)^p(1));
			s.add(sg(n1,n2,n3-1,n4)^p(1));
		}
		//snap 2
		if(n2>0){
			s.add(sg(n1+1,n2-1,n3,n4)^p(0));
			s.add(sg(n1,n2-1,n3,n4)^p(0));
		}
		//blow
		for(int i4=0;i4<=n4;i4++){
			for(int i3=0;i3<=n3;i3++){
				for(int i2=0;i2<=n2;i2++){
					for(int i1=0;i1<=n1;i1++){
						if(!(i1==0&&i2==0&&i3==0&&i4==0)){
							//blow it up yo
							//System.out.println((n1-i1)+" "+(n2-i2)+" "+(n3-i3)+" "+(n4-i4));
							s.add(sg(n1-i1,n2-i2,n3-i3,n4-i4)^(((i2)%2)*p(1))^(((i3)%2)*p(2))^(((i4)%2)*p(3)));
						}
					}
				}
			}
		}
		//System.out.println(s);
		int k = mex(s);
		h[n1][n2][n3][n4] = k;
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
