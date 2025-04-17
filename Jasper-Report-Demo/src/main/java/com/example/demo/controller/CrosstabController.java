package com.example.demo.controller;

	
	import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.CrossTabReportEntry;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

		@RestController
		public class CrosstabController {

			
			@GetMapping(value = "/generateCrossTabReport", produces = "application/JSON")
			public void generateSampleHorizontalReport() {
				
				try {
					
					InputStream reportStream=getClass().getResourceAsStream("/reports/CrossTableExample.jrxml");
					
					JasperReport jasperReport = JasperCompileManager.
							compileReport(reportStream);												
					
					Map<String, Object> parameters = new HashMap<>();
					
					List<CrossTabReportEntry> entries= List.of(
							new CrossTabReportEntry("Rohit" ,"January",100),
							new CrossTabReportEntry("Ankit" ,"February",110),
							new CrossTabReportEntry("Mohan" ,"January",120),
							new CrossTabReportEntry("Rohan" ,"March",130),
							new CrossTabReportEntry("Soham" ,"January",110),
							new CrossTabReportEntry("Rohit" ,"January",110),
							new CrossTabReportEntry("Rohit" ,"February",170)
							);
					
					
										
					JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(entries);
					
					JasperPrint jasperPrint =JasperFillManager.fillReport(
							jasperReport, parameters, beanColDataSource);
					
					 File outDir = new File("C:/jasperoutput");
				       outDir.mkdirs();
				       
				       JasperExportManager.exportReportToPdfFile(jasperPrint,
		 					"C:/jasperoutput/CrossTabExampleDemo.pdf");
				       
				       System.out.println("Done");
				       
					} catch (JRException e) {
					
					e.printStackTrace();
				}
				
			}
				
				
				

		}



