package com.example.demo.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class HorizontalReportController {

	
	@GetMapping(value = "/generateHorizontalReport", produces = "application/JSON")
	public void generateSampleHorizontalReport() {
		
		try {
			
			//JasperReport jasperReportold = JasperCompileManager.
			//compileReport("C:\\Users\\Rohit.Kumar012\\Downloads\\MultipleChildDemo\\MultipleChildDemo\\src\\main\\resources\\Blank_A4.jrxml");
			
			InputStream reportStream=getClass().getResourceAsStream("/reports/HorizontalPrint.jrxml");
			
			JasperReport jasperReport = JasperCompileManager.
					compileReport(reportStream);
					
			
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			List<Map<String, ?>> mainList = new ArrayList<>();
			
			mainList=getMainReportData();
			
			//JRDataSource dataSource = new JRBeanCollectionDataSource(mainList);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(mainList);
			
			JasperPrint jasperPrint =JasperFillManager.fillReport(
					jasperReport, parameters, beanColDataSource);
			
			 File outDir = new File("C:/jasperoutput");
		       outDir.mkdirs();
		       
		       JasperExportManager.exportReportToPdfFile(jasperPrint,
 					"C:/jasperoutput/HorizontalDemo.pdf");
		       
		       System.out.println("Done");
		       
			} catch (JRException e) {
			
			e.printStackTrace();
		}
		
	}
		
		
		private static List<Map<String, ?>> getMainReportData() {
			
			List<Map<String, ?>> listTrans = new LinkedList<>();
			Map<String, Object> tranMap =new HashMap<>();
			tranMap.put("id", 1);
					
			tranMap.put("name", "Rohit");

			tranMap.put("department", "IT");

			tranMap.put("salary",  60000.00);
			
			listTrans.add(tranMap);
			
			Map<String, Object> tranMap2 =new HashMap<>();
			
			tranMap2.put("id", 2);
			
			tranMap2.put("name", "Ankit");

			tranMap2.put("department", "Gov");

			tranMap2.put("salary",  70000.00);
			
			listTrans.add(tranMap2);
			
			Map<String, Object> tranMap3 =new HashMap<>();
			
			tranMap3.put("id", 3);
			
			tranMap3.put("name", "Ram");

			tranMap3.put("department", "HR");

			tranMap3.put("salary",  40000.00);
			
			listTrans.add(tranMap3);
			
			Map<String, Object> tranMap4 =new HashMap<>();
			
			tranMap4.put("id", 4);
			
			tranMap4.put("name", "Mohan");

			tranMap4.put("department", "IS");

			tranMap4.put("salary",  55000.00);
			
			listTrans.add(tranMap4);
			
			return listTrans;
			
		}

}
