package wenc.jasper.hkex;


import java.util.List;

public class LineTools {

	public static final String reportElement = ShortLine.reportElement;
	
	private List<Integer> sizeOfHorizontalLineList;
	private List<Integer> sizeOfVerticalLineList;
	private List<Integer> startPointOfHorizontalLine;
	private List<Integer> startPointOfVerticalLine;
	
	private int sizeOfLine = ShortLine.sizeOfLine;
	private int offsetOfX = ShortLine.offsetOfX;//0, by default
	private int offsetOfY = ShortLine.offsetOfY;//0, by default
	
	//public LineTools() {}
	public LineTools(List<Integer> sizeOfHorizontalLineList,List<Integer> sizeOfVerticalLineList,List<Integer> startPointOfHorizontalLine,List<Integer> startPointOfVerticalLine) {
		this.sizeOfHorizontalLineList = sizeOfHorizontalLineList;
		this.sizeOfVerticalLineList = sizeOfVerticalLineList;
		this.startPointOfHorizontalLine = startPointOfHorizontalLine;
		this.startPointOfVerticalLine = startPointOfVerticalLine;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	//get all lines
	public String getAllLines() {
		StringBuilder sb = new StringBuilder();
		sb.append(initAxisLines())
		.append(generateOtherLines());		
		return sb.toString();
	}
	
	//generate other lines
	public String generateOtherLines() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sizeOfHorizontalLineList.size(); i++) {
			for(int j = 0; j < sizeOfVerticalLineList.size(); j++) {
				sb.append(getLine(startPointOfHorizontalLine.get(i), startPointOfVerticalLine.get(j+1),sizeOfHorizontalLineList.get(i),1));
				sb.append(getLine(startPointOfHorizontalLine.get(i+1),startPointOfVerticalLine.get(j),1,sizeOfVerticalLineList.get(j)));
			}
		}
		
		return sb.toString();
	}
	
	public String getLine(List<Integer> li) {
		String line = String.format("<line>"+reportElement, li.get(0),li.get(1),li.get(2),li.get(3),CommonTool.getUUID());
		line += "</line>";
		return line;
	}
	public String getLine(int x, int y, int w, int h) {
		String line = String.format("<line>"+reportElement, x, y, w, h,CommonTool.getUUID());
		line += "</line>";
		return line;
	}
	
	//initialize Axis
	public String initAxisLines() {
		generateStartPoint();
		StringBuilder sb = new StringBuilder();
		for(int w = 0; w < this.sizeOfHorizontalLineList.size(); w++) {
			sb.append(getLine(startPointOfHorizontalLine.get(w),offsetOfY,sizeOfHorizontalLineList.get(w),sizeOfLine));
		}
		for(int h = 0; h < this.sizeOfVerticalLineList.size(); h++) {
			sb.append(getLine(offsetOfX,startPointOfVerticalLine.get(h),sizeOfLine,sizeOfVerticalLineList.get(h)));
		}
		return sb.toString();
	}
	//generate start point
	public void generateStartPoint() {
		startPointOfHorizontalLine.add(offsetOfX);
		startPointOfVerticalLine.add(offsetOfY);
		int w = 1;
		int h = 1;
		for(w = 1; w < this.sizeOfHorizontalLineList.size()+1; w++) {
			startPointOfHorizontalLine.add(startPointOfHorizontalLine.get(w-1)+sizeOfHorizontalLineList.get(w-1));
		}
		
		for(h = 1; h < this.sizeOfVerticalLineList.size()+1; h++) {
			startPointOfVerticalLine.add(startPointOfVerticalLine.get(h-1)+sizeOfVerticalLineList.get(h-1));
		}

	}

}
