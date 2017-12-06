package wenc.jasper.hkex;

import java.util.ArrayList;
import java.util.List;

public class TextTools {


	private List<Integer> sizeOfHorizontalLineList;
	private List<Integer> sizeOfVerticalLineList;
	private List<Integer> startPointInX;
	private List<Integer> startPointInY;
	
	private final String textAlignment = ShortLine.textAlignment;
	private final String verticalAlignment = ShortLine.verticalAlignment;
	private final String defaultFontName = ShortLine.defaultFontName;
	private final int defaultFontSize = ShortLine.defaultFontSize;
	private final String isBold = ShortLine.isBold;
	
	public static final String myReportElement = "<reportElement x='%s' y='%s' width='%s' height='%s' uuid='%s'/>";;
	
	public List<ArrayList<String>> textParaList;
	
	//public TextTools() {}
	public TextTools(List<Integer> sizeOfHorizontalLineList,List<Integer> sizeOfVerticalLineList,List<Integer> startPointOfHorizontalLine,List<Integer> startPointOfVerticalLine) {
		this.sizeOfHorizontalLineList = sizeOfHorizontalLineList;
		this.sizeOfVerticalLineList = sizeOfVerticalLineList;
		this.startPointInX = startPointOfHorizontalLine;
		this.startPointInY = startPointOfVerticalLine;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[][] textLoc = new String[ShortLine.numInVertical][ShortLine.numInHorizontal];
		textLoc[0][0] = "TextOne";
		textLoc[textLoc.length-1][0] = "TextTwo";
		textLoc[0][textLoc[0].length-1] = "TextThree";
		textLoc[textLoc.length-1][textLoc[0].length-1] = "TextFour";
		for(int i = 0; i < textLoc.length; i++) {
			for(int j = 0; j < textLoc[i].length; j++) {
				if(textLoc[i][j]!=null) {
					System.out.print(textLoc[i][j]+" ");
				}else {
					System.out.print("NU"+" ");
				}
			}
			System.out.println("\n");
		}

	}
	
	
	public String getStaticTextCode(String[][] textLoc) {
		textParaList = new ArrayList<ArrayList<String>>();
		StringBuilder sb = new StringBuilder();
		generateLocationList(textParaList,textLoc);
		for(ArrayList<String> item : textParaList) {
			sb.append(generateStatic(item.get(0),item.get(1),item.get(2),item.get(3),item.get(4)));
		}
		return sb.toString();
	}
	
	public String getStaticTextCode(String[][] textLoc,String varType) {
		textParaList = new ArrayList<ArrayList<String>>();
		StringBuilder sb = new StringBuilder();
		generateLocationList(textParaList,textLoc,varType);
		for(ArrayList<String> item : textParaList) {
			sb.append(generateVarField(item.get(0),item.get(1),item.get(2),item.get(3),item.get(4)));
		}
		return sb.toString();
	}
	
	
	
	public void generateLocationList(List<ArrayList<String>> textParaList,String[][] textLoc) {
		ArrayList<String> li;
		for(int i = 0; i < textLoc.length; i++) {
			for(int j = 0; j < textLoc[i].length; j++) {
				if(textLoc[i][j] != null) {
					li = new ArrayList<String>();
					li.add(""+startPointInX.get(j));
					li.add(""+startPointInY.get(i));
					li.add(""+sizeOfHorizontalLineList.get(j));
					li.add(""+sizeOfVerticalLineList.get(i));					
					li.add(textLoc[i][j]);					
					textParaList.add(li);
				}
			}
		}
	}
	
	public String generateStatic(String startX,String startY,String width,String height,String text) {
		StringBuilder sb = new StringBuilder();
		sb.append("<staticText>")
		.append(String.format(myReportElement, startX,startY,width,height,CommonTool.getUUID()))
		.append(generateTextElement())
		.append(String.format("<text><![CDATA[%s]]></text>",text))
		.append("</staticText>");
		return sb.toString();
	}
	
	
	/**
	 * parameter 'varType' should be 'P', 'V' or 'F'
	 * @param textLoc
	 * @param varType
	 */
	public void generateLocationList(List<ArrayList<String>> textParaList,String[][] textLoc, String varType) {
		ArrayList<String> li;
		for(int i = 0; i < textLoc.length; i++) {
			for(int j = 0; j < textLoc[i].length; j++) {
				if(textLoc[i][j] != null) {
					li = new ArrayList<String>();
					System.out.print(textLoc[i][j]+" ");
					li.add(""+startPointInX.get(j));
					li.add(""+startPointInY.get(i));
					li.add(""+sizeOfHorizontalLineList.get(j));
					li.add(""+sizeOfVerticalLineList.get(i));					
					li.add(String.format("%s{%s}", varType,textLoc[i][j]));					
					textParaList.add(li);
				}
			}
		}
	}
	/**
	 * the parameter 'text' should be $P{var}, $F{var} or $V{var}
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @param text
	 * @return
	 */
	public String generateVarField(String startX,String startY,String width,String height,String text) {
		StringBuilder sb = new StringBuilder();
		sb.append("<textField>")
		.append(String.format(myReportElement, startX,startY,width,height,CommonTool.getUUID()))
		.append(generateTextElement())
		.append(String.format("<textFieldExpression><![CDATA[%s]]></textFieldExpression>",text))
		.append("</textField>");
		return sb.toString();
	}
	
	
	
	
	
	private String generateTextElement() {
		String textElement = String.format("<textElement textAlignment='%s' verticalAlignment='%s'><font fontName='%s' size='%d' isBold='%s'/></textElement>"
				, textAlignment,verticalAlignment,defaultFontName,defaultFontSize,isBold);		
		return textElement;
	}

}
