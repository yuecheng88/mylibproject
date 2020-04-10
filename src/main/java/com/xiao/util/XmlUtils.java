package com.xiao.util;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*import com.yunji.constant.YJConstants;*/

public class XmlUtils {

	@SuppressWarnings("rawtypes")
	public static Map<String, String> parseXML(String strxml) throws JDOMException,
			IOException {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		Map<String,String>  m = new HashMap<String,String>();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			m.put(k, v);
		}
		// 关闭流
		in.close();
		return m;
	}
	
	@SuppressWarnings("rawtypes")
	private static  String getChildrenText(List children) {
		StringBuilder sb = new StringBuilder();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}
	/**
	 * 生成xml文件
	 * @param map
	 * @return
	 */
	public static String buildXml(Map<String, String> map) {
		if(map==null || map.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder("<xml>");
		for(String key : map.keySet()) {
			if(!StringUtils.isBlank(key)) {
				String value = map.get(key);//<![CDATA[" 开始，由 "]]>
				value = value==null ? "" : value.trim();
				sb.append("<"+key+"><![CDATA["+value+"]]></"+key+">");
			}
		}
		return sb.append("</xml>").toString();
	}

}
