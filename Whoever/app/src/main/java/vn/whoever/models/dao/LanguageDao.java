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
 * Created by spider man on 3/2/2016.
 */
public class LanguageDao {

    private static String DB_LANGUAGE = "language/languages.xml";
    private Context context;
    private HashMap<String, String> listLanguage;
    private DocumentBuilderFactory factory;

    private static LanguageDao languageDao = new LanguageDao();

    public static LanguageDao getInstance(Context context) {
        languageDao.context = context;
        return languageDao;
    }

    public LanguageDao() {
        // create new instance for document
        factory = DocumentBuilderFactory.newInstance();
    }

    /**
     * Func used fetch an XML file from assets folder
     *
     * @return a list language supported by application
     */

    public synchronized HashMap<String, String> getArrayLanguageSupport() {
        listLanguage = new LinkedHashMap<>();
        listLanguage.put("en","English");
        listLanguage.put("vi","Tiếng Việt");

        try {
            InputStream inputStream = context.getAssets().open(DB_LANGUAGE);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();
            //get first element
            Element element = document.getDocumentElement();

            Node nodeLanguageSupport = element.getChildNodes().item(1);

            NodeList nodeList = nodeLanguageSupport.getChildNodes();
            for(int i = 0 ; i < nodeList.getLength(); ++i) {
                if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element element_val = (Element) nodeList.item(i);
                    listLanguage.put(element_val.getAttribute("id"), element_val.getTextContent());
                    //Log.d("Attribute: ", element_val.getAttribute("id") + " " + element_val.getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listLanguage;
    }

}
