package com.example.demo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class SampleController {
	
	
	@GetMapping(value = "/generateReport", produces = "application/JSON")
	public void generateSampleReport() {
		
		try {
			
			JasperReport jasperReport = JasperCompileManager.
			compileReport("C:\\Users\\Rohit\\Downloads\\crossTableOrTbleDemo\\crossTableOrTbleDemo\\src\\SubReport\\MainReport.jrxml");
			
			JasperReport jasperReportSub = JasperCompileManager.
			compileReport("C:\\Users\\Rohit\\Downloads\\crossTableOrTbleDemo\\crossTableOrTbleDemo\\src\\SubReport\\SubReport.jrxml");

			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			List<Map<String, ?>> mainList = new ArrayList<>();
			
			List<Map<String, ?>> subList = new ArrayList<>();
			
			mainList=getMainReportData();
			
			subList=getSubReportData();
			
			
			parameters.put("subreportParameter", jasperReportSub);
			
			parameters.put("subList", subList);
						
			JRDataSource dataSource = new JRBeanCollectionDataSource(mainList);
			
			JRBeanCollectionDataSource DS = new JRBeanCollectionDataSource(subList);
			
			parameters.put("dataSourceParam", DS);
			
			 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
		               parameters, dataSource);
			 
			 File outDir = new File("C:/jasperoutput");
		       outDir.mkdirs();
		       
		       JasperExportManager.exportReportToPdfFile(jasperPrint,
   					"C:/jasperoutput/SubReport.pdf");
		       
		       System.out.println("Done");
		
		
		} catch (JRException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
private static List<Map<String, ?>> getSubReportData() {

		
		List<Map<String, ?>> listTrans = new LinkedList<>();
		Map<String, Object> tranMap =new HashMap<>();
				
		tranMap.put("name", "Rohit");

		tranMap.put("occupation", "Engineer");

		tranMap.put("salary",  47000);
		
		listTrans.add(tranMap);
		
		Map<String, Object> tranMap2 =new HashMap<>();
		
		tranMap2.put("name", "Ankit");

		tranMap2.put("occupation", "Government");

		tranMap2.put("salary",  40000);
		
		listTrans.add(tranMap2);
		
		Map<String, Object> tranMap3 =new HashMap<>();
		
		tranMap3.put("name", "Venu");

		tranMap3.put("occupation", "Engineer");

		tranMap3.put("salary",  50000);
		
		listTrans.add(tranMap3);
		
		return listTrans;
		
	
	}

	private static List<Map<String, ?>> getMainReportData() {
		
		List<Map<String, ?>> listTrans = new LinkedList<>();
		Map<String, Object> tranMap =new HashMap<>();
				
		tranMap.put("name", "Rohit");

		tranMap.put("address", "Kolkata");

		tranMap.put("age",  27);
		
		listTrans.add(tranMap);
		
		Map<String, Object> tranMap2 =new HashMap<>();
		
		tranMap2.put("name", "Ankit");

		tranMap2.put("address", "bihar");

		tranMap2.put("age",  23);
		
		listTrans.add(tranMap2);
		
		Map<String, Object> tranMap3 =new HashMap<>();
		
		tranMap3.put("name", "Venu");

		tranMap3.put("address", "Hyderabad");

		tranMap3.put("age",  27);
		
		listTrans.add(tranMap3);
		
		return listTrans;
		
	}


}
