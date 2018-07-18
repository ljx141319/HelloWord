/**  
* @Title:  dom4j.java
* @Package test
* @Description: TODO
* @author 利军
* @date  2015年12月31日 上午11:18:52
* @version V1.0  
* Update Logs:
* ****************************************************
* Name:
* Date:
* Description:
******************************************************
*/
package demo;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Writer;
import java.io.FileWriter;   
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.IOException;     
     




import org.dom4j.Document;     
import org.dom4j.DocumentException;     
import org.dom4j.DocumentHelper;     
import org.dom4j.Element;     
import org.dom4j.io.SAXReader;     
import org.dom4j.io.XMLWriter;



/**
 * @ClassName: dom4j
 * @Description: TODO
 * @author 利军
 * @date 2015年12月31日 上午11:18:52
 *
 */
public class Dom4j {
	
	
	public static void main(String[] args) throws Exception {
		String  aa="<?xml version='1.0' encoding='utf-8'?><items_get_response><total_count>1</total_count><items><item><producttype>普通</producttype><isfreeshipping>不包邮</isfreeshipping><sku_id>1122420390</sku_id><sku_no>1000410380</sku_no><sku_name>爱易思（Easeyes）E5000蓝牙耳机（金色）</sku_name><category_id>cat10000078</category_id><product_id>9133640340</product_id><operating_model>1</operating_model><brand>爱易思（Easeyes）</brand><list_price>93.00</list_price><sale_price>93.00</sale_price><on_sale_start_date>0001-01-01T00:00:00</on_sale_start_date><on_sale_end_date>0001-01-01T00:00:00</on_sale_end_date><promo_desc>无</promo_desc><packing_list>耳机*1</packing_list><service_desc>此产品享受国家三包，请您收货后拆箱验货。    如无质量问题无法办理退货手续。未使用未拆包可退货。    保护类周边小配件质保3个月，线材类非人为损坏质保6个月，蓝牙类商品质保1年。</service_desc><picture_url>http://img1.gomein.net.cn/image/prodimg/production_image/201507/01/1122420390/1000795904_800.jpg</picture_url><stock_status>1</stock_status><product_url>http://item.gome.com.cn/9133640340-1122420390.html</product_url><product_url_wap>http://m.gome.com.cn/product-9133640340-1122420390.html</product_url_wap><update_date>2016/3/15 17:16:42</update_date></item></items></items_get_response>";
		Dom4j d=new Dom4j();
		Document docuemnt=d.xmlString2Xml(aa);
	}
	
	

	
	/**
	 * 
	* @Title: createXml
	* @Description: dom4j 创建一下xml 字符串
	* @author 利军
	* @param rootnode 根节点名称
	* @param map 子节点
	* @return String字符串
	* @throws IOException
	* @throws
	 */
	public String createXml(String rootnode,Map map) throws IOException {  
		Document doc = DocumentHelper.createDocument();
		Element employees=doc.addElement(rootnode);
		for (Object obj : map.keySet()) {
			employees.addElement(obj.toString()).setText(Utils.toString(map.get(obj))); 
		}
		return doc.asXML();
	}
	
	
	/**
	 * 
	* @Title: dom4jparsexml
	* @Description: dom4j 解析字符串 xml
	* @author 利军
	* @param protocolXML xml字符串
	* @return map集合
	* @throws DocumentException
	* @throws
	 */
	public Map<String,String> dom4jparsexml(String protocolXML) throws DocumentException {   
		Map<String,String> map = new HashMap<String,String>();
			Document doc=(Document)DocumentHelper.parseText(protocolXML);   
			Element books = doc.getRootElement();   
			//System.out.println("根节点 "+books.getName());   
			Iterator Elements = books.elementIterator();   
			while(Elements.hasNext()){   
				Element user = (Element)Elements.next();   
				map.put(user.getName(),user.getText());
				//System.out.println("节点"+user.getName()+" "+user.getText());      
			}
		return map;           
	}
	
	
	/**
	 * 将字符串转换为 xml 文档
	 * @param xmlString
	 * @return
	 */
	public  Document xmlString2Xml(String xmlString){
		SAXReader saxReader = new SAXReader();   
		try {
			
			Document document = saxReader.read(new ByteArrayInputStream(xmlString.getBytes()));
			return document;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
