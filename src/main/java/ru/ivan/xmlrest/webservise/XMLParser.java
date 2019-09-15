package ru.ivan.xmlrest.webservise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.ivan.xmlrest.dataservise.DataServise;
import ru.ivan.xmlrest.model.Box;
import ru.ivan.xmlrest.model.Item;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@Component
public class XMLParser {

    @Autowired
    DataServise dataServise;

    Logger logger = LogManager.getLogger(XMLParser.class);
    private static final String BOX = "Box";
    private static final String Item = "Item";

    public void parseFileXML(String filePath) {
        try {
            logger.debug("FilePath: " + filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));

            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            parseXML(nodeList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void parseXML (NodeList nodeList) {
        for (int i=0; i<nodeList.getLength();i++) {
            Node node = nodeList.item(i);

            if (node.getNodeName().equals(BOX)){
                dataServise.addBox(getBox(node));
            }
            if (node.getNodeName().equals(Item)){
                dataServise.addItem(getItem(node));
            }
            if (node.getChildNodes()!=null) {
                parseXML(node.getChildNodes());
            }
        }
    }

    private Box getBox (Node node) {
        Box box = new Box ();
        if (node.getAttributes()!=null && node.getAttributes().getNamedItem("id")!=null) {
            box.setId(Integer.valueOf(node.getAttributes().getNamedItem("id").getNodeValue()));
        }
        if (node.getParentNode()!=null && node.getParentNode().getAttributes()!=null && node.getParentNode().getAttributes().getNamedItem("id")!=null) {
            box.setParentId(Integer.valueOf(node.getParentNode().getAttributes().getNamedItem("id").getNodeValue()));
        } else {box.setParentId(null);}
        logger.debug("getBox return: "+ box);
        return box;
    }

    private Item getItem (Node node) {
        Item item = new Item();

        if (node.getAttributes()!=null && node.getAttributes().getNamedItem("id")!=null) {
            item.setId(Integer.valueOf(node.getAttributes().getNamedItem("id").getNodeValue()));
        }
        if (node.getParentNode()!=null && node.getParentNode().getAttributes()!=null && node.getParentNode().getAttributes().getNamedItem("id")!=null) {
            item.setCONTAINED_IN(Integer.valueOf(node.getParentNode().getAttributes().getNamedItem("id").getNodeValue()));
        } else {item.setCONTAINED_IN(null);}
        if (node.getAttributes().getNamedItem("color")!=null) {
            item.setColor(node.getAttributes().getNamedItem("color").getNodeValue());
        } else {item.setColor(null);}
        logger.debug("getItem return: "+ item);
        return item;
    }

}
