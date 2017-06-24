/**
 * 
 */
package com.ztf.test2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.xml.sax.SAXException;

import com.ztf.entity.TscH3cGlJournalsInterface;

/**
 * @author 张腾飞
 *
 */
public class Main3 {
	
	private static String dataFile = "OM.xlsx";
    public static final String xmlConfig = "xmlConfigOM.xml";

	/**
	 * @param args
	 */
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		try {
			InputStream inputXML = new BufferedInputStream(Main3.class.getResourceAsStream(xmlConfig));
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream inputXLS = new BufferedInputStream(Main3.class.getResourceAsStream(dataFile));
			
			List staff = new ArrayList();
			Map beans = new HashMap();
			beans.put("staff", staff);
		    XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
			
		    System.out.println(staff.size());
		    
//		    for (Object object : staff) {
//		    	TscH3cGlJournalsInterface tscH3cGlJournalsInterface = (TscH3cGlJournalsInterface) object;
//		    	System.out.println(tscH3cGlJournalsInterface.getProjectCode());
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

}
