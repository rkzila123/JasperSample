package com.example.demo.controller;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.SalesPojo;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@RestController
public class GenerateDifferentSheetBasedOnGroup {
	
	@GetMapping(value = "/differentSheetBasedOnGroupReport", produces = "application/JSON")
	public void generateDifferentSheetBasedOnGroupReport() {
		
		
		try {
			InputStream reportStream=getClass().getResourceAsStream("/reports/differentSheetBasedOnGroupReport.jrxml");
			
			JasperReport jasperReport = JasperCompileManager.
					compileReport(reportStream);												
			
			Map<String, Object> parameters = new HashMap<>();
			
			List<SalesPojo> entries= List.of(
					new SalesPojo("North" ,"Apples",100.00),
					new SalesPojo("South" ,"Oranges",110.00),
					new SalesPojo("North" ,"Banana",120.00),
					new SalesPojo("West" ,"Apple",130.00),
					new SalesPojo("North" ,"Guava",110.00),
					new SalesPojo("West" ,"Cherry",110.00),
					new SalesPojo("East" ,"Coconut",170.00)
					);
			
			//parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
								
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(entries);
			
			JasperPrint jasperPrint =JasperFillManager.fillReport(
					jasperReport, parameters, beanColDataSource);
			
			 File outDir = new File("C:/jasperoutput");
		       outDir.mkdirs();
		       
		       
		       JasperExportManager.exportReportToPdfFile(jasperPrint,
	 					"C:/jasperoutput/differentSheetBasedOnGroupReport.pdf");
				
			/*
			 * JRXlsxExporter exporter= new JRXlsxExporter(); SimpleXlsxReportConfiguration
			 * configuration = new SimpleXlsxReportConfiguration();
			 * configuration.setOnePagePerSheet(true);
			 * configuration.setDetectCellType(true); // Detect cell types (date and etc.)
			 * configuration.setWhitePageBackground(false); // No white background!
			 * configuration.setFontSizeFixEnabled(false);
			 * 
			 * // No spaces between rows and columns
			 * configuration.setRemoveEmptySpaceBetweenRows(true);
			 * configuration.setRemoveEmptySpaceBetweenColumns(true);
			 * 
			 * exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			 * exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
			 * "C:/jasperoutput/differentSheetBasedOnGroupReport.xlsx"));
			 * exporter.setConfiguration(configuration);
			 */
		       System.out.println("Done");
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}

}
