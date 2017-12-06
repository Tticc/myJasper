package Test.Test.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTool {

	public static void main(String[] args) {
		
	}
	public static void generateHorizontalList(int w,int h,int count,int step, List<ArrayList<Integer>> horizontal) {
		ArrayList<Integer> li;		
		for(int i = 0; i < count-1; i++) {
			li = new ArrayList<Integer>();
			li.add(0);
			li.add(i*step);
			li.add(w);
			li.add(1);			
			horizontal.add(li);
		}
		//last line
		li = new ArrayList<Integer>();
		li.add(0);
		li.add(h);
		li.add(w);
		li.add(1);
		horizontal.add(li);
	}
	public static void generateVerticalList(int w,int h,int count,int step, List<ArrayList<Integer>> vertical) {
		ArrayList<Integer> li;
		for(int i = 0; i < count-1; i++) {
			li = new ArrayList<Integer>();
			li.add(i*step);
			li.add(0);
			li.add(1);
			li.add(h);			
			vertical.add(li);
		}
		//last line
		li = new ArrayList<Integer>();
		li.add(w);
		li.add(0);
		li.add(1);
		li.add(h);
		vertical.add(li);
	}
	public static void addNewElement(int x,int y,int w,int h,List<ArrayList<Integer>> list,int index) {
		ArrayList<Integer> li = new ArrayList<Integer>();
		li.add(x);
		li.add(y);
		li.add(w);
		li.add(h);
		list.add(index, li);
	}
	public static ArrayList<Integer> copyList(ArrayList<Integer> list){
		ArrayList<Integer> li = new ArrayList<Integer>();
		for(int item : list) {
			li.add(item);
		}
		return li;
	}
	public static void addList(int index,int count,List<ArrayList<Integer>> list) {
		for(int i = 0; i < count; i++) {
			list.add(copyList(list.get(index)));
		}
	}
	
	//generate lines' list when use rectangle--
	/*public static void generateHorizontalList(int w,int h,int count,int step, List<ArrayList<Integer>> horizontal) {
		ArrayList<Integer> li;		
		for(int i = 0; i < count; i++) {
			li = new ArrayList<Integer>();
			li.add(0);
			li.add((i+1)*step);
			li.add(w);
			li.add(1);			
			horizontal.add(li);
		}
	}
	public static void generateVerticalList(int w,int h,int count,int step, List<ArrayList<Integer>> vertical) {
		ArrayList<Integer> li;
		for(int i = 0; i < count; i++) {
			li = new ArrayList<Integer>();
			li.add((i+1)*step);
			li.add(0);
			li.add(1);
			li.add(h);			
			vertical.add(li);
		}
	}*/
	
}
