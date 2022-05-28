package top.boking;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class TestStater {
    private Map<String, List<Map<String, String>>> valueMap = new HashMap<String, List<Map<String, String>>>();

    public static void main(String[] args) throws UnsupportedEncodingException {

        TestStater testStater = new TestStater();
        String formDataXmlpath = "C:\\Data\\JavaDemoList\\TestMoudle\\src\\main\\resources\\data.xml";
        Element dataRoot = parseXml(formDataXmlpath);
        testStater.fromSetDocument(dataRoot);

        Map<String, List<Map<String, String>>> valueMap = testStater.valueMap;

        int size = valueMap.size();
        System.out.println("size = " + size);

        valueMap.forEach((k, v) -> {
            if (k.equalsIgnoreCase("workflow_bill")) {
                v.forEach(m ->{
                    String tablename = m.get("uuid");
                    String uuid = m.get("tablename");
                    String space = "";
                    for (int i = 0; i < 50 - tablename.length(); i++) {
                        space+=" ";
                    }
                    System.out.println(String.format("tablename:%s%suuid:%s", tablename,space, uuid));
                });
            }
        });
    }
    public static Element parseXml(String filePath){
        Element element = null;
        try{
            InputStream is = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            SAXReader reader = new SAXReader();
            Document document = reader.read(isr);
            element = document.getRootElement();
        }catch (Exception e) {
            System.out.println("catch:");
            return null;
        }
        return element;
    }

    public    void fromSetDocument(Element root) {
        valueMap.clear();
        if (root == null) {
            return;
        }
     root.attributeValue("dbtype");
     root.attributeValue("dataid");
       root.attributeValue("datatype");
        root.attributeValue("uuid");
        List tableElements = root.elements();
        Iterator tableIt = tableElements.iterator();
        while (tableIt.hasNext()) {
            Element tableElement = (Element) tableIt.next();
            List dataElements = tableElement.elements("data");
            Iterator dataIt = dataElements.iterator();
            List<Map<String, String>> values = new ArrayList<Map<String, String>>();
            while (dataIt.hasNext()) {
                Element dataElement = (Element) dataIt.next();
                List columnElements = dataElement.elements();
                Iterator columnIt = columnElements.iterator();
                Map<String, String> value = new HashMap<String, String>();
                while (columnIt.hasNext()) {
                    Element columnElement = (Element) columnIt.next();
                    String columnName = columnElement.attributeValue("name");
                    String columnValue = columnElement.attributeValue("value");
                    value.put(columnName, columnValue);
                    Attribute mappingAttr = columnElement.attribute("mapping");
                }
                values.add(value);
            }
            String tableName = tableElement.getName().toLowerCase();
            valueMap.put(tableName, values);
        }
    }
}
