package com.ztf.test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;

import com.ztf.common.Common;
import com.ztf.entity.Department;
import com.ztf.entity.Employee;

public class Main {
	
	private static String dataFile = "departmentdata.xls";
    public static final String xmlConfig = "xmlConfig.xml";

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
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
		    XLSReadStatus readStatus = mainReader.read(inputXLS, beans);
		    System.out.println(department.getName());
		    
		    
		    //get the data object
		    List<Employee> empList = department.getStaff();
		    
		    String sql = "insert into employee (name, age, payment, bonus) values (?, ?, ?, ?)";
		    Connection connection = getConnection();
		    connection.setAutoCommit(false);
		    PreparedStatement ps = connection.prepareStatement(sql);
		    final int batchSize = 1000;
		    int count = 0;
		    for (Employee employee: empList) {
		        ps.setString(1, employee.getName());
		        ps.setInt(2, employee.getAge());
		        ps.setDouble(3, employee.getPayment());
		        ps.setDouble(4, employee.getBonus());
		        ps.addBatch();
//		        if(++count % batchSize == 0) {
//		            ps.executeBatch();
//		        }
		    }
		    ps.executeBatch(); // insert remaining records
		    connection.commit();
		    ps.close();
		    connection.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Connection getConnection() {
		try {
			Class.forName(Common.DRIVER);
			Connection conn = DriverManager.getConnection(Common.URL, Common.USERNAME, Common.PASSWORD);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}

}
