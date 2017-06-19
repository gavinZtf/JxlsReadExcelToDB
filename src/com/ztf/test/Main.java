package com.ztf.test;

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

import com.ztf.entity.Department;

public class Main {
	
	private static String dataFile = "D://SoftWare//Cygwin//install//home//Administrator//JxlsReadExcelToDB//file//departmentdata.xls";
    public static final String xmlConfig = "D://SoftWare//Cygwin//install//home//Administrator//JxlsReadExcelToDB//file//xmlConfig.xml";

	public static void main(String[] args) {
		try {
			InputStream inputXML = new BufferedInputStream(Main.class.getResourceAsStream(xmlConfig));
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream inputXLS = new BufferedInputStream(Main.class.getResourceAsStream(dataFile));
			Department department = new Department();
			Department hrDepartment = new Department();
			List departments = new ArrayList();
			Map beans = new HashMap();
			beans.put("department", department);
			beans.put("hrDepartment", hrDepartment);
			beans.put("departments", departments);
//		    XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
