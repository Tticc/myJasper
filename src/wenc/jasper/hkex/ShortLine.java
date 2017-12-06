package wenc.jasper.hkex;

import java.util.ArrayList;
import java.util.List;

public class ShortLine {

	private static List<Integer> sizeOfHorizontalLineList = new ArrayList<Integer>();
	private static List<Integer> sizeOfVerticalLineList = new ArrayList<Integer>();
	private static List<Integer> startPointOfHorizontalLine = new ArrayList<Integer>();//it's size will be sizeOfHorizontalLineList.size()+1
	private static List<Integer> startPointOfVerticalLine = new ArrayList<Integer>();//it's size will be sizeOfVerticalLineList.size()+1
	
	public static final int numInHorizontal = 4;
	public static final int numInVertical = 9;	
	public static final int normalSizeOfHorizontalLine = 130;
	public static final int normalSizeOfVerticalLine = 30;	
	public static final int bandHeight = 315+1;
	public static final int offsetOfX = 0;//offset of X
	public static final int offsetOfY = 0;//offset of Y
	public static final int tableWidth = 555;
	public static final int tableHeight = bandHeight;	
	public static final int sizeOfLine = 1;
	
	public static final String defaultFontName = "Cambria Math";
	public static final int defaultFontSize = 14;
	public static final String textAlignment = "Left";//Left,Center,Right,Justified
	public static final String verticalAlignment = "Top";//Top,Middle,Bottom,Justified
	public static final String isBold = "false";
	
	public static final String reportElement = "<reportElement x='%d' y='%d' width='%d' height='%d' uuid='%s'/>";
	
	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();
		ShortLine sl = new ShortLine();
		//define lines' parameters.
		sl.initHorizontalList(sizeOfHorizontalLineList, numInHorizontal);
		sl.initVerticalList(sizeOfVerticalLineList, numInVertical);
		sl.modifySizeList(sizeOfVerticalLineList, 2, normalSizeOfVerticalLine*1);
		sl.modifySizeList(sizeOfVerticalLineList, 4, (int)(normalSizeOfVerticalLine*0.5));
		sl.modifySizeList(sizeOfHorizontalLineList, 0, tableWidth-normalSizeOfHorizontalLine*4);
		//end of defining lines' parameters.		
		
		//get lines' code
		LineTools lt = new LineTools(sizeOfHorizontalLineList, sizeOfVerticalLineList,startPointOfHorizontalLine,startPointOfVerticalLine);
		sb.append(lt.getAllLines());
		sb.append("\n\n");
		
		String[][] textLoc = new String[numInVertical][numInHorizontal];
		sl.modifyArray(textLoc);
		//get text's code after get line.
		TextTools tt = new TextTools(sizeOfHorizontalLineList, sizeOfVerticalLineList,startPointOfHorizontalLine,startPointOfVerticalLine);
		sb.append(tt.getStaticTextCode(textLoc));
		sb.append("");
		
		System.out.println(sb.toString());
		
	}
	
	
	
	
	
	public void modifyArray(String[][] textLoc) {
		textLoc[0][0] = "TextOne";
		textLoc[0][textLoc[0].length-1] = "TextTwo";
		textLoc[textLoc.length-1][0] = "TextThree";
		textLoc[textLoc.length-1][textLoc[0].length-1] = "TextFour";
	}
	
	
	
	
	//initialize size list
	public void initHorizontalList(List<Integer> sizeInWidthList,int numInHorizontal) {
		for(int i = 0; i <  numInHorizontal; i++) {
			sizeInWidthList.add(normalSizeOfHorizontalLine);
		}
	}
	public void initVerticalList(List<Integer> sizeInHeightList,int numInVertical) {
		for(int i = 0; i <  numInVertical; i++) {
			sizeInHeightList.add(normalSizeOfVerticalLine);
		}
	}
	private void modifySizeList(List<Integer> sizeList, int index, int plusNumber) {
		sizeList.set(index, sizeList.get(index)+plusNumber);
	}

}
