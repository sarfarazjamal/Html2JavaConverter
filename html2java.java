package htmlToJava;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Node;

public class Html2JavaConverter {
public static void main(String[] args) {
        
		File input=new File("D:\\projects\\workspaces\\devweb\\htmlToJava\\src\\htmlToJava\\NewFile.html");
		try {
			org.jsoup.nodes.Document document=Jsoup.parse(input, "UTF-8", "http://example.com");
			StringBuilder sb=new StringBuilder();
			Html2JavaConverter.callThis(document.getAllElements().get(0), " ", "  ");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	private static void callThis(Node E, String sp1, String sp2) {
        String s1=" ";	
        String s2="   ";
        s1=s1+sp1;
        s2=s2+sp2;
        
        if(E.nodeName().equals("#text")){
        	String text=E.attr("text").replace(" ", "");
        	if(text.isEmpty() ||text.contains("\r\n")){
        		String text2=text.replaceAll("\r\n", "");
        		if(text2.length()>0){
        			System.out.println(s1+ "writer.write(\""+ text2+ "\");");
        		}else{
        			
        		}
        		return;
        	}else{
        		System.out.println(s1+ "writer.write(\""+ E.attr("text")+ "\");");
        	}
        	
        }else{
        	System.out.println(s1+ "writer.startElement(\""+ E.nodeName()+ "\",null);");
        	
        	for (Attribute attr : E.attributes()) {
        		System.out.println(s1+ "writer.writeAttribute(\""
        	      + attr.getKey()+ "\",\""+attr.getValue() + "\",\""
        	      +attr.getKey()+ "\");");
			}
        	
        	if(E.childNodeSize()>0){
        		for (int i = 0; i < E.childNodeSize(); i++) {
					callThis(E.childNode(i),s1,s2);
				}
        	}
        	if(!E.nodeName().equals("#text")){
        		System.out.println(s1+ "writer.endElement(\""+ E.nodeName()+ "\");");
        	}
        }
        
	}
}
