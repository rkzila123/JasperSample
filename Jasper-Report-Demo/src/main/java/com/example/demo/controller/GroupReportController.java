
	package com.example.demo.controller;

	import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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
	public class GroupReportController {

		
		@GetMapping(value = "/generateGroupReport", produces = "application/JSON")
		public void generateSampleHorizontalReport() {
			
			try {
				
				InputStream reportStream=getClass().getResourceAsStream("/reports/GroupExample.jrxml");
				
				JasperReport jasperReport = JasperCompileManager.
						compileReport(reportStream);
						
				
				
				Map<String, Object> parameters = new HashMap<String, Object>();
				
				List<Map<String, ?>> mainList = new ArrayList<>();
				
				mainList=getMainReportData();
				
				parameters.put("InitialBalance", 25000.00);
				
				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(mainList);
				
				JasperPrint jasperPrint =JasperFillManager.fillReport(
						jasperReport, parameters, beanColDataSource);
				
				 File outDir = new File("C:/jasperoutput");
			       outDir.mkdirs();
			       
			       JasperExportManager.exportReportToPdfFile(jasperPrint,
	 					"C:/jasperoutput/GroupExampleDemo.pdf");
			       
			       System.out.println("Done");
			       
				} catch (JRException e) {
				
				e.printStackTrace();
			}
			
		}
			
			
			private static List<Map<String, ?>> getMainReportData() {
				
				List<Map<String, ?>> listTrans = new LinkedList<>();
				Map<String, Object> tranMap =new HashMap<>();
				tranMap.put("transactionId", 123422);
						
				tranMap.put("accountNumber", "Rohit12345");

				tranMap.put("date", new Date());

				tranMap.put("type",  "DEBIT");
				
				tranMap.put("amount",  10000.00);
				
				listTrans.add(tranMap);
				
				Map<String, Object> tranMap2 =new HashMap<>();
				
				tranMap2.put("transactionId", 252323);
				
				tranMap2.put("accountNumber", "Rohit12345");

				tranMap2.put("date", new Date());

				tranMap2.put("type",  "CREDIT");
				
				tranMap2.put("amount",  30000.00);
				
				listTrans.add(tranMap2);
				
				Map<String, Object> tranMap3 =new HashMap<>();
				
				tranMap3.put("transactionId", 3453545);
				
				tranMap3.put("accountNumber", "Ankit12345");

				tranMap3.put("date", new Date());

				tranMap3.put("type",  "CREDIT");
				
				tranMap3.put("amount",  15000.00);
				
				listTrans.add(tranMap3);
				
				Map<String, Object> tranMap4 =new HashMap<>();
				
				tranMap4.put("transactionId", 453445);
				
				tranMap4.put("accountNumber", "Rohit12345");

				tranMap4.put("date", new Date());

				tranMap4.put("type",  "DEBIT");
				
				tranMap4.put("amount",  5000.00);
				
				listTrans.add(tranMap4);
				
				Map<String, Object> tranMap5 =new HashMap<>();
				
				tranMap5.put("transactionId", 5453545);
				
				tranMap5.put("accountNumber", "Ankit12345");

				tranMap5.put("date", new Date());

				tranMap5.put("type",  "DEBIT");
				
				tranMap5.put("amount",  20000.00);
				
				listTrans.add(tranMap5);
				
				Map<String, Object> tranMap6 =new HashMap<>();
				
				tranMap6.put("transactionId", 6453545);
				
				tranMap6.put("accountNumber", "Ankit12345");

				tranMap6.put("date", new Date());

				tranMap6.put("type",  "CREDIT");
				
				tranMap6.put("amount",  7000.00);
				
				listTrans.add(tranMap6);
				
				return listTrans;
				
			}

	}

