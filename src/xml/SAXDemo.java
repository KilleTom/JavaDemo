package xml;

import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX文档解析
 * 为解决DOM的问题，出现了SAX。SAX ，事件驱动。
 * 当解析器发现元素开始、元素结束、文本、文档的开始或结束等时，发送事件，程序员编写响应这些事件的代码，保存数据。
 * 优点：不用事先调入整个文档，占用资源少；SAX解析器代码比DOM解析器代码小，适于Applet，下载。
 * 缺点：不是持久的；事件过后，若没保存数据，那么数据就丢了；
 * 无状态性；从事件中只能得到文本，但不知该文本属于哪个元素；
 * 使用场合：Applet;只需XML文档的少量内容，很少回头访问；机器内存少；
 */
public class SAXDemo {

    public void createXml(String fileName) {
        //
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        try {
             /*
            1、创建SAXTransformerFactory实例
            2、创建TransformerHandler实例
            3、创建Transformer实例
            4、设置输出的xml属性，encoding为编码，indent是确保输出的xml文件能够自动换行
            5、创建Result对象，将Result对象加载到TransHandler中
            注意：
            1、这一步必须在Transformer.setOutputProperty()之后，不然设置的xml属性将不生效
            2、这一步也必须在TransformerHandler.startDocument()之前，不然会报错。
            分析源码后发现，startDocument()会先判断result是否为空，为空则报错
            6、创建属性Attribute对象
            7、开始写文件
            8、写入根节点bookstore
            9、清空属性，每次新增一个节点都需要先清空一下属性，防止在设置节点属性时发生错误
            10、设置属性
            11、写入根节点的子节点book
            12、分别写入book节点的子节点
            13、写入子节点内容
            14、写入子节点末尾
            15、反复写入直到结束
            */
            TransformerHandler handler = factory.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            Result result = new StreamResult(pw);
            handler.setResult(result);
            AttributesImpl attr = new AttributesImpl();
            handler.startDocument();
            handler.startElement("", "", "bookstore", attr);
            attr.clear();
            attr.addAttribute("", "", "id", "", "1");
            handler.startElement("", "", "book", attr);
            attr.clear();
            handler.startElement("", "", "name", attr);
            handler.characters("冰与火之歌".toCharArray(), 0, "冰与火之歌".toCharArray().length);
            handler.endElement("", "", "name");
            attr.clear();
            handler.startElement("", "", "author", attr);
            handler.characters("乔治马丁".toCharArray(), 0, "乔治马丁".toCharArray().length);
            handler.endElement("", "", "author");
            attr.clear();
            handler.startElement("", "", "time", attr);
            handler.characters("2014".toCharArray(), 0, "2014".toCharArray().length);
            handler.endElement("", "", "time");
            attr.clear();
            handler.startElement("", "", "price", attr);
            handler.characters("60".toCharArray(), 0, "60".toCharArray().length);
            handler.endElement("", "", "price");
            handler.endElement("", "", "book");
            handler.endElement("", "", "bookstore");
            handler.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parserXml(String fileName) {
        SAXParserFactory saxfac = SAXParserFactory.newInstance();
        try {
            SAXParser saxparser = saxfac.newSAXParser();
            InputStream is = new FileInputStream(fileName);
            saxparser.parse(is, new MySAXHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MySAXHandler extends DefaultHandler {
    boolean hasAttribute = false;
    Attributes attributes = null;
    public void startDocument() throws SAXException {
        System.out.println("文档开始打印了");
    }

    public void endDocument() throws SAXException {
        System.out.println("文档打印结束了");

    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("employees")) return;
        if (qName.equals("employee")) System.out.println(qName);
        if (attributes.getLength() > 0) {
            this.attributes = attributes;
            this.hasAttribute = true;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (hasAttribute && (attributes != null))
            for (int i = 0; i < attributes.getLength(); i++)
                System.out.println(attributes.getQName(0) + attributes.getValue(0));
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println(new String(ch, start, length));
    }
}


