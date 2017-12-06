package Test.Test.Test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	private static List<Integer> horizontal = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		//System.out.println("sjioj");
		Test tt = new Test();
		List<ArrayList<Integer>> tlist = tt.tlist();
		tlist.get(0).set(1, 111);
		tlist.get(0).set(0, 11);
		System.out.println(tlist.get(1).get(1));
		for(int i=0;i<tlist.size();i++) {
			for(int j = 0; j < tlist.get(i).size(); j++) {
				System.out.print(tlist.get(i).get(j));
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}
	
	public void test() {
		horizontal.add(1);
		horizontal.add(2);
		horizontal.add(3);
		horizontal.add(4);
		horizontal.add(1,10);
		for(int item : horizontal) {
			System.out.println(item);
		}
	}
	
	public List<ArrayList<Integer>> tlist() {
		List<ArrayList<Integer>> llist = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer>li = new ArrayList<Integer>();
		li.add(1);
		li.add(2);
		li.add(5);
		li.add(10);
		llist.add(li);
		llist.add(copy(llist.get(0)));
		return llist;
	}
	public ArrayList<Integer> copy(List<Integer> list) {
		ArrayList<Integer> li = new ArrayList<Integer>();
		for(int item : list) {
			li.add(item);
		}
		return li;
	}
	
}
