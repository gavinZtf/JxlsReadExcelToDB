package com.ztf.test2;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;

import com.ztf.entity.Employee;
import com.ztf.entity.TscH3cGlJournalsInterface;
import com.ztf.test.Main;

public class Main2 {
	
	private static String dataFile = "departmentdata.xlsx";
    public static final String xmlConfig = "xmlConfig.xml";

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		try {
			InputStream inputXML = new BufferedInputStream(Main2.class.getResourceAsStream(xmlConfig));
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream inputXLS = new BufferedInputStream(Main2.class.getResourceAsStream(dataFile));
			
			List staff = new ArrayList();
			Map beans = new HashMap();
			beans.put("staff", staff);
		    XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
		    
		    System.out.println(staff.size());
		    
		    for (Object object : staff) {
				Employee emp = (Employee) object;
				System.out.print(emp.getName());
				System.out.print("\t");
				System.out.print(emp.getAge());
				System.out.print("\t");
				System.out.print(emp.getBirthDate());
				System.out.print("\t");
				System.out.print(emp.getBonus());
				System.out.print("\t");
				System.out.print(emp.getPayment());
				System.out.print("\t");
				System.out.println("");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
