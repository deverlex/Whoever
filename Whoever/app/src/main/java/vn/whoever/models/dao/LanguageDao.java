package vn.whoever.models.dao;

import android.content.Context;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Nguyen Van Do on 3/2/2016.
 * Class load data list languages support by application from assets (XML file)
 */
public class LanguageDao {

    private Context context;
    private HashMap<String, String> listLanguage;
    private DocumentBuilderFactory factory;

    public LanguageDao(Context context) {
        this.context = context;
        factory = DocumentBuilderFactory.newInstance();
    }

    public synchronized HashMap<String, String> getArrayLanguageSupport() {
        listLanguage = new LinkedHashMap<>();
        try {
            InputStream inputStream = context.getAssets().open("language/languages.xml");
            // Read XML file using by DOM
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();
            Element element = document.getDocumentElement();
            Node nodeLanguageSupport = element.getChildNodes().item(1);
            NodeList nodeList = nodeLanguageSupport.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); ++i) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element_val = (Element) nodeList.item(i);
                    listLanguage.put(element_val.getAttribute("id"), element_val.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // set default for list language if load data failed
        if (listLanguage.size() == 0) {
            listLanguage.put("en", "English");
            listLanguage.put("vi", "Tiếng Việt");
        }
        return listLanguage;
    }
}
