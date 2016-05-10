package FrameGen;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by 40095 on 5/8/16.
 */
public class ReadCompanyTest {
    public static void main(String[] args) {
        new ReadCompanyTest("longshot generals.xml");
    }

    ReadCompanyTest(String xmlFileName) {
        File f = new File(xmlFileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = db.parse(f);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.normalizeDocument();
        String companyName = document.getElementsByTagName("cname").item(0).getTextContent();
        System.out.println(companyName);
        
    }

    String parseDocument(Node root) {
        if(!root.hasChildNodes()) {
            return "";
        }
        String value = root.getNodeValue();
        for (int i = 0; i < root.getChildNodes().getLength(); i++) {
            value += root.getChildNodes().item(i).getNodeValue();
        }
        return value;
    }

}
