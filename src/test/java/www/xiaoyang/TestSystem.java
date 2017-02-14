package www.xiaoyang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/** 
* @ClassName: TestSystem 
* @Description: 抽取xml数据体测试
* @author YJ
* @date 2017年2月10日 下午1:14:15 
*  
*/
public class TestSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><流转单统计表 发送方=\"云南中调\" 接收方=\"南网总调\" 生成时间=\"2017-02-09\" 记录数=\"2\" ><数据体><业务ID>00909090</业务ID><局编码>05</局编码></数据体><数据体><业务ID>00909092</业务ID><局编码>05</局编码></数据体></流转单统计表>";
		File file;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();//使用w3c解析数据XML
		Document document = null;
		FileOutputStream output = null; 
		StreamResult xmlResult;
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(xml)));
			
			XPathFactory pathFactory = XPathFactory.newInstance();   //创建xpthFactory  
			XPath xpath = pathFactory.newXPath();
			Object result = xpath.evaluate("/流转单统计表/数据体", document, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;   
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Element rootElement = document.getDocumentElement(); //获取数据XML根节点
				Document documentNow = builder.newDocument(); //创建新XML用于输出到文件中
				Element newRootEmement = documentNow.createElement(rootElement.getTagName()); //创建新XML的根节点
				NamedNodeMap rootAttributes = rootElement.getAttributes(); //获取根节点全部属性添加到新XML的根节点中
				for (int j = 0; j < rootAttributes.getLength(); j++) {
					Node nodeAttribute = rootAttributes.item(j);
					newRootEmement.setAttribute(nodeAttribute.getNodeName(),nodeAttribute.getNodeValue());
				}
				documentNow.appendChild(newRootEmement); 	//获取数据XML根节点名称添加到新XML中
				documentNow.getDocumentElement().appendChild(documentNow.importNode(nodes.item(i), true)); //提取数据体放到新XML中
				
				TransformerFactory transFactory = TransformerFactory.newInstance(); //开始把Document映射到文件
				Transformer transFormer = transFactory.newTransformer();
				transFormer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				
				DOMSource domSource = new DOMSource(documentNow);//设置输出结果
				
				file = new File("d:testSystem"+i+".xml");
				if(!file.exists())
				{
					file.createNewFile();	
				}
				output = new FileOutputStream(file);
				xmlResult = new StreamResult(output);
				transFormer.transform(domSource, xmlResult);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(output != null)
			{
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 打印xml
	 * @param xml
	 * @throws Exception
	 */
	public static final void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());
        out.close();
    }
	
	/**
	 * 打印node xml
	 * @param xml
	 * @throws Exception
	 */
	public static final void prettyPrintNode(Node xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());
        out.close();
    }
	
}
