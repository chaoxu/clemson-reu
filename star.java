import java.util.*;
import java.io.*;
class Firework implements Comparable<Firework> , Serializable {
	public HashMap<Integer, Integer> h;
	public int hash; 
	public ArrayList<Integer> a;
	public static int get(HashMap<Integer, Integer> h,int k){
		if(h.containsKey(k)){
			return h.get(k);
		}
		return 0;
	}
	public static void put(HashMap<Integer, Integer> h, int k, int n){
		if(k<=0){
			return;
		}
		if(n<=0){
			h.remove(k);
			return;
		}
		h.put(k, n);
	}
	Firework(ArrayList<Integer> a){
		h = new HashMap<Integer,Integer>();
		if(a.size() == 2){
			put(h,a.get(0)+a.get(1),1);
		}else{
			for(int i=0;i<a.size();i++){
				put(h, a.get(i), get(h,a.get(i))+1);
			}
		}
		hash = h.hashCode();
	}
	Firework(int t){
		h = new HashMap<Integer,Integer>();
		put(h,t,1);
		hash = h.hashCode();
	}
	Firework(HashMap<Integer, Integer> hm){
		h = hm;
		if(h.size()<=2){
			Integer[] key = new Integer[h.size()];
			h.keySet().toArray(key);
			int c = 0;
			int s = 0;
			for(int i=0;i<h.size();i++){
				c+=h.get(key[i]);
				s+=key[i]*h.get(key[i]);
			}
			if(c==2){
				h = new HashMap<Integer, Integer>();
				h.put(s, 1);
			}
		}
		hash = h.hashCode();
	}
	//r=1 if remove 2, =1 if remove 1
	public ArrayList<Firework> snap(int k, int j, int r){
		HashMap<Integer,Integer> h2 = new HashMap<Integer, Integer>(h);
		//System.out.println(h2);
		put(h2, k, h.get(k)-1);
		//System.out.println(h2);
		//System.out.println(j-1+ " "+(get(h,j-1)+1));
		put(h2, j-1, get(h,j-1)+1);
		//System.out.println(k+" "+j+" "+r+" "+h+" "+h2+" h2");
		Firework p = new Firework(k-j-r);
		Firework n = new Firework(h2);
		ArrayList<Firework> result = new ArrayList<Firework>();
		result.add(p);
		result.add(n);
		//System.out.println(result);
		return result;
	}
	
	public ArrayList<Firework> blow(HashMap<Integer, Integer> r){
		Integer[] key = new Integer[r.size()];
		r.keySet().toArray(key);
		
		ArrayList<Firework> f = new ArrayList<Firework>();
		HashMap<Integer, Integer> h2 = new HashMap<Integer, Integer>(h);
		
		for(int i=0;i<r.size();i++){
			int k = key[i];
			put(h2, k, h.get(k)-r.get(k));
			if(r.get(k)%2==1){
				f.add(new Firework(k-1));
			}
		}
		f.add(new Firework(h2));
		//System.out.println(f + "blow");
		return f;
	}
	
	public int hashCode() {
		return hash;
	}
	public String toString(){
		//return h.toString();
		return toArrayList().toString();
	}
	public boolean equals( Object obj )
	{
		boolean flag = false;
		Firework emp = ( Firework )obj;
		if( this.h.equals(emp.h))
			flag = true;
		return flag;
	}
	
	public ArrayList<Integer> toArrayList(){
		if(a!=null){
			return a;
		}
		Integer[] key = new Integer[h.size()];
		h.keySet().toArray(key);
		Arrays.sort(key,Collections.reverseOrder());
		a = new ArrayList<Integer>();
		for(int i=0;i<key.length;i++){
			for(int j=0;j<h.get(key[i]);j++){
				a.add(key[i]);
			}
		}
		return a;
	}
	@Override
	public int compareTo(Firework f) {
		ArrayList<Integer> a = toArrayList();
		ArrayList<Integer> b = f.toArrayList();
		if(a.size()>b.size()){
			return 1;
		}
		if(a.size()<b.size()){
			return -1;
		}
		for(int i=0;i<a.size();i++){
			if(a.get(i)<b.get(i)){
				return -1;
			}
			if(a.get(i)>b.get(i)){
				return 1;
			}
		}
		return 0;
	}
}
public class star {
	public static HashMap<Firework, Integer> h = new HashMap<Firework, Integer>();
	public static int count=0;

	public static void load(){
		String filename = "data.db";
		if(!(new File(filename)).exists()){
			return;
		}
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			h = (HashMap<Firework, Integer>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	public static void save(){
		String filename = "data.db";
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(h);
			out.close();
			//System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args){
		/*
		
		h.put(new Firework(0), 0);*/
		
		//Get data
		System.out.println("Loading...");
		load();
		System.out.println("Done!");
		//All the computation is done in this following line
		//System.out.println(f);
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()){
			Scanner s = new Scanner(in.nextLine());
			ArrayList<Integer> z = new ArrayList<Integer>();
			boolean stop = false;
			while(s.hasNextInt()){
				int t = s.nextInt();
				if(t<0){
					stop = true;
				}
				z.add(t);
			}
			if(stop){
				break;
			}
			if(z.size()==0){
				continue;
			}
			Firework f = new Firework(z);
			sg(f); // <------this line, takes 99.999% of the running time
			System.out.println("> "+h.get(f));
		}
		System.out.println("Saving...");
		//all the rest is just process the results, which is stored in h
		save();
		System.out.println("Done!");
		
		//System.out.println(sg(f));
		/* Firework[] F = new Firework[h.size()];
		h.keySet().toArray(F);
		Arrays.sort(F);
		for(int i=0;i<F.length;i++){
			System.out.println(F[i]+" "+h.get(F[i]));
		}*/
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
		Integer[] key = new Integer[f.h.size()];
		f.h.keySet().toArray(key);
		for(int i=0;i<key.length;i++){
			for(int j=1;j<=key[i];j++){
				s.add(sg(f.snap(key[i], j, 0)));
				s.add(sg(f.snap(key[i], j, 1)));
			}
		}
		
		//Then try with doing stuff in the center...
		//I call it blow
		//It take time around the product of the number of lengths of each size
		blows(0,new ArrayList<Integer>(), key, f, s);
		int k = mex(s);
		h.put(f,k);
		return k;
	}
	//blows recursively find the sg number of all possible
	//subgraphs built from remove edges from the center
	public static void blows(int n, ArrayList<Integer> t, Integer[] key, Firework f, HashSet<Integer> s){
		if(n==key.length){
			HashMap<Integer, Integer> d = new HashMap<Integer, Integer>();
			boolean zero = true;
			for(int i=0;i<t.size();i++){
				if(t.get(i)>0){
					zero = false;
				}
				d.put(key[i], t.get(i));
			}
			if(zero){
				return;
			}
			s.add(sg(f.blow(d)));
			return;
		}
		for(int i=0;i<=f.h.get(key[n]);i++){
			t.add(i);
			blows(n+1, t, key, f, s);
			t.remove(t.size()-1);
		}
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
