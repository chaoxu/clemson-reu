import java.util.*;
//Katy Perry
class Firework implements Comparable<Firework> {
	public ArrayList<Integer> a;
	public int hash; 
	Firework(ArrayList<Integer> b){
		a = new ArrayList<Integer>();
		for(int i=0;i<b.size();i++){
			if(b.get(i)>0){
				a.add(b.get(i));
			}
		}
		if(a.size()==2){
			Firework F = new Firework(a.get(0)+a.get(1));
			a = F.a;
			hash = F.hash;
		}else{
			Collections.sort(a);		
			hash = Arrays.deepHashCode(a.toArray());
		}
	}
	Firework(int t){
		a = new ArrayList<Integer>();
		if(t>0){
			a.add(t);
		}
		hash = Arrays.deepHashCode(a.toArray());
	}
	//r=1 if remove 2, =1 if remove 1
	public ArrayList<Firework> snap(int k, int j, int r){
		int l = a.get(k);
		Firework p = new Firework(l-j-r);
		ArrayList<Integer> c = new ArrayList<Integer>(a);
		c.set(k, j-1);
		if(j-1==0){
			c.remove(k);
		}
		Firework n = new Firework(c);
		ArrayList<Firework> result = new ArrayList<Firework>();
		result.add(p);
		result.add(n);
		return result;
	}
	
	public ArrayList<Firework> blow(ArrayList<Integer> r){
		int c = r.size()-1;
		ArrayList<Firework> f = new ArrayList<Firework>();
		ArrayList<Integer> n = new ArrayList<Integer>();
		for(int i=0;i<a.size();i++){
			if(c>-1 && r.get(c)==i){
				c--;
				f.add(new Firework(a.get(i)-1));
			}else{
				n.add(a.get(i));
			}
		}
		f.add(new Firework(n));
		return f;
	}
	
	public int hashCode() {
		return hash;
	}
	public String toString(){
		return a.toString();
	}
	public boolean equals( Object obj )
	{
		boolean flag = false;
		Firework emp = ( Firework )obj;
		if( this.a.equals(emp.a))
			flag = true;
		return flag;
	}
	@Override
	public int compareTo(Firework f) {
		if(a.size()>f.a.size()){
			return 1;
		}
		if(a.size()<f.a.size()){
			return -1;
		}
		for(int i=0;i<a.size();i++){
			if(a.get(i)<f.a.get(i)){
				return -1;
			}
			if(a.get(i)>f.a.get(i)){
				return 1;
			}
		}
		return 0;
	}
}
public class star {
	public static HashMap<Firework, Integer> h = new HashMap<Firework, Integer>();
	public static int count=0;
	public static void main(String[] args){
		
		//int n = 3;
		ArrayList<Integer> z = new ArrayList<Integer>();
		for(int i=0;i<args.length;i++){
			z.add(Integer.parseInt(args[i]));
		}
		h.put(new Firework(0), 0);
		Firework f = new Firework(z);
		//All the computation is done in this following line
		sg(f); // <------this line, takes 99.999% of the running time
		//all the rest is just process the results, which is stored in h
		
		//System.out.println(h);
		//System.out.println(sg(f));
		Firework[] F = new Firework[h.size()];
		h.keySet().toArray(F);
		Arrays.sort(F);
		for(int i=0;i<F.length;i++){
			System.out.println(F[i]+" "+h.get(F[i]));
		}
		//System.out.println(count);
		//System.out.println(F2);
	}
	//Find the sg number of a graph with many components
	public static int sg(ArrayList<Firework> f){
		int s = 0;
		for(int i=0;i<f.size();i++){
			s^=sg(f.get(i));
		}
		return s;
	}
	//find the sg number of a graph with 1 component.
	public static int sg(Firework f){
		count++;
		if(h.containsKey(f)){
			return h.get(f);
		}
		//The idea is to do every possible move
		HashSet<Integer> s = new HashSet<Integer>();
		//Now start playing with delete 1 or 2 edges...
		//I call it snap... 
		for(int i=0;i<f.a.size();i++){
			for(int j=1;j<=f.a.get(i);j++){
				s.add(sg(f.snap(i, j, 0)));
				s.add(sg(f.snap(i, j, 1)));
			}
		}
		
		//Then try with doing stuff in the center...
		//I call it blow
		//BY TEST EVERY COMBINATION OF COURSE (O(2^n) time T_T)
		//Maybe it can be improved
		blows(new ArrayList<Integer>(), f.a.size()-1, f, s);
		int k = mex(s);
		h.put(f,k);
		return k;
	}
	//blows recursively find the sg number of all possible
	//subgraphs built from remove edges from the center
	public static void blows(ArrayList<Integer> t, int n, Firework f, HashSet<Integer> s){
		if(n==-1){
			if(t.size()>0){
				s.add(sg(f.blow(t)));
			}
			return;
		}	
		t.add(n);
		blows(t,n-1,f,s);
		t.remove(t.size()-1);
		blows(t,n-1,f,s);
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
