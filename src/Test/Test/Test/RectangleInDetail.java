package Test.Test.Test;

import java.util.ArrayList;
import java.util.List;

import wenc.jasper.hkex.CommonTool;

public class RectangleInDetail {

	private static List<ArrayList<Integer>> horizontalList = new ArrayList<ArrayList<Integer>>();
	private static List<ArrayList<Integer>> verticalList = new ArrayList<ArrayList<Integer>>();
	public static final String reportElement = "<reportElement x='%d' y='%d' width='%d' height='%d' uuid='%s'/>";
	
	public static final int bandHeight = 315+1;
	public static final int rectangleX = 0;
	public static final int rectangleY = 0;
	public static final int rectangleW = 555;
	public static final int rectangleH = bandHeight;
	
	public static final int horizontalLineNumber = 10;
	public static final int verticalLineNumber = 5;
	public static final int heightStep = 30;
	public static final int widthStep = 138;
	
	public static final String defaultFontName = "";
	public static final int defaultFontSize = 14;
	public static final String horizontalAlign = "LeftCenter";//Left,Center,Right,Justified
	public static final String verticalAlign = "Middle";//Top,Middle,Bottom,Justified
	//parameter P{gg1};
	public static void main(String[] args) {
		//System.out.println("sjioj");
		RectangleInDetail rd = new RectangleInDetail();
		
		//generate rectangle
		//String rectangle = rd.generateRectangle();
		
		//generate lines' list
		ListTool.generateHorizontalList(rectangleW,rectangleH-1,horizontalLineNumber,heightStep,horizontalList);
		ListTool.generateVerticalList(rectangleW,rectangleH-1,verticalLineNumber,widthStep,verticalList);
		//modify lines' list here
		for(int i = 1; i< horizontalList.size()-1; i++) {
			ArrayList<Integer> li = horizontalList.get(i);
			if(i >= 3) {
				li.set(1,li.get(1)+(int)(heightStep*1));
				if(i >= 5) {
					li.set(1,li.get(1)+(int)(heightStep*0.5));
				}
			}
		}
		ListTool.addList(2, 2, verticalList);
		for(int j = 0; j< verticalList.size(); j++) {
			
		}
		for(ArrayList<Integer> it1 : verticalList) {
			for(int it2 : it1) {
				System.out.print(it2);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
		
		//get all lines
		String allLines = rd.getAllLine();
		
		
		//join elements
		StringBuilder bandStr = new StringBuilder();
		bandStr.append(rd.getBandStart(bandHeight))
		//inside band here
		
		.append(allLines)
		.append(rd.getLabelEnd("band"));
		
		System.out.println(bandStr.toString());
		
	}
	
	
	
	public String getDetail() {
		
		StringBuilder detail = new StringBuilder();
		detail.append(getLabelStart("detail"))
		.append(getBandStart(bandHeight))
		
		//rectangle start here,inside band
		.append(generateRectangle())
		//end, inside band
		
		.append(getLabelEnd("band"))
		.append(getLabelEnd("detail"));
		return detail.toString();
	}
	
	public String getLabelStart(String section) {
		return "<"+section+">";
	}
	public String getBandStart(int height) {
		String bandStr = String.format("<band height='%d' splitType='Stretch'>",height);
		return bandStr;
	}
	public String getLabelEnd(String label) {
		return "</" + label + ">";
	}
	
	
	
	
	//rectangle section.
	public String generateRectangle() {
		StringBuilder sb = new StringBuilder();
		sb.append(getRectangle(rectangleX,rectangleY,rectangleW,rectangleH));
		return sb.toString();
	}
	public String getRectangle(int x,int y,int w,int h) {
		String re = String.format("<rectangle><reportElement x='%d' y='%d' width='%d' height='%d' uuid='%s'/></rectangle>", x,y,w,h,CommonTool.getUUID());
		return re;
	}
	
	//get all line
	public String getAllLine() {
		//ListTool.generateHorizontalList(rectangleW,rectangleH,horizontalLineNumber,heightStep,horizontalList);
		//ListTool.generateVerticalList(rectangleW,rectangleH,verticalLineNumber,widthStep,verticalList);
		return horizontalLines()+verticalLines();
	}
	public String horizontalLines() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < horizontalList.size(); i++) {
			sb.append(getLine(horizontalList.get(i))).append("\n");
		}
		return sb.toString();
	}
	public String verticalLines() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < verticalList.size(); i++) {
			sb.append(getLine(verticalList.get(i))).append("\n");
		}
		return sb.toString();
	}
	public String getLine(List<Integer> li) {
		String line = String.format("<line>"+reportElement, li.get(0),li.get(1),li.get(2),li.get(3),CommonTool.getUUID());
		line += "</line>";
		return line;
	}
	public String getLine(List<Integer> li,int size) {		
		return null;
	}
	
	//get <textField>
	public String getTextField(List<Integer> loc,String horizontalAlign,String verticalAlign,String variable,String defaultFontName,int defaultFontSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("<textField>")
		.append(String.format(reportElement, loc.get(0),loc.get(1),loc.get(2),loc.get(3),CommonTool.getUUID()))
		.append(String.format("<textElement textAlignment='%s' verticalAlignment='%s'>", horizontalAlign,verticalAlign))
		.append(String.format("<font fontName='%s' size='%d'/>",defaultFontName,defaultFontSize))
		.append("</textElement>")
		.append(String.format("<textFieldExpression><![CDATA[$%s]]></textFieldExpression>",variable))
		.append("</textField>");
		
		return sb.toString();
	}
	//get <staticText>
	public String getStaticText(List<Integer> loc,String horizontalAlign,String verticalAlign,String staticText,String defaultFontName,int defaultFontSize) {
		StringBuilder sb = new StringBuilder();
		sb.append("<staticText>")
		.append(String.format(reportElement, loc.get(0),loc.get(1),loc.get(2),loc.get(3),CommonTool.getUUID()))
		.append(String.format("<textElement textAlignment='%s' verticalAlignment='%s'>", horizontalAlign,verticalAlign))
		.append(String.format("<font fontName='%s' size='%d'/>",defaultFontName,defaultFontSize))
		.append("</textElement>")
		.append(String.format("<text><![CDATA[%s]]></text>",staticText))
		.append("</staticText>");
		
		return sb.toString();
	}
	
	
	
	
}
