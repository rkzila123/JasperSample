package com.example.demo.controller;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

@RestController
public class DynamicColumnReportGenerator {
	
	@GetMapping(value = "/generateDynamicColumnReport", produces = "application/JSON")
	public void generateDynamicColumnReport() {
		
		List<Map<String, Object>> data;
		JasperReport jasperReport;
		try {
			List<String> allPossibleColumns=List.of("Name","Age","Hobby","Salary","Address");
			
			Map<String, Object> parameters = new HashMap<>();
			
			boolean showAboveAge18=false;
			
			List<String> selectedColumns= new ArrayList<>();
			
			for(String col :allPossibleColumns) {
				//if("Salary".equals(col) && !showAboveAge18) continue;
				selectedColumns.add(col);
			}
			
			data = List.of(
					new HashMap<>(Map.of("Name","Rohit","Age",19,"Hobby","Play Cricket","Salary",20000,"Address" ,"Kolkata")),
					new HashMap<>(Map.of("Name","Ankit","Age",20,"Hobby","Play Music","Salary",30000,"Address" ,"Bihar")),
					new HashMap<>(Map.of("Name","Mohit","Age",21,"Hobby","Dance","Salary",25000,"Address" ,"Hyd")),
					new HashMap<>(Map.of("Name","Surender","Age",22,"Hobby","Singing","Salary",29000,"Address" ,"Punjab"))
					);
			
			jasperReport = buildReport(selectedColumns);
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
			
			JasperPrint jasperPrint =JasperFillManager.fillReport(
					jasperReport, parameters, beanColDataSource);
			
			 File outDir = new File("C:/jasperoutput");
			   outDir.mkdirs();
			   
			   JasperExportManager.exportReportToPdfFile(jasperPrint,
						"C:/jasperoutput/DynamicReportExampleDemo.pdf");
			   
			   System.out.println("Done");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//JasperPrint print = JasperFillManager.fillReport(report, new HashMap<>() , new JRMapCollectionDataSource(data));
	}

	private JasperReport buildReport(List<String> selectedColumns) throws JRException{
		
		JasperDesign design = new JasperDesign();
		design.setName("DynamicReport");
		design.setPageWidth(595);
		design.setPageHeight(842);
		design.setColumnWidth(555);
		design.setLeftMargin(20);
		design.setRightMargin(20);
		design.setTopMargin(20);
		design.setBottomMargin(20);
		
		for(String col :selectedColumns) {
			
			JRDesignField field = new JRDesignField();
			field.setName(col);
			field.setValueClass(Object.class);
			design.addField(field);						
			
		}
		
		int colWidth= 555/selectedColumns.size();
		int x=0;
		
		JRDesignBand titleBand= new JRDesignBand();
		titleBand.setHeight(40);
		
		JRDesignStaticText staticTitleText = new JRDesignStaticText();
		
		staticTitleText.setX(0);
		staticTitleText.setY(0);
		staticTitleText.setWidth(555);
		staticTitleText.setHeight(30);
		staticTitleText.setText("Dynamic Report Generated From JAVA");
		staticTitleText.setMode(ModeEnum.OPAQUE);
		staticTitleText.setBackcolor(Color.LIGHT_GRAY);
		staticTitleText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
		staticTitleText.getLineBox().getPen().setLineWidth(0.5f);
		staticTitleText.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
		staticTitleText.setFontName("Arial");
		staticTitleText.setFontSize(16f);
		staticTitleText.setBold(true);
		titleBand.addElement(staticTitleText);
		
		design.setTitle(titleBand);
		
		JRDesignBand columnHeaderBand= new JRDesignBand();
		columnHeaderBand.setHeight(20);
		
		for( String col:selectedColumns) {
			
			JRDesignStaticText staticText = new JRDesignStaticText();
			
			staticText.setX(x);
			staticText.setY(0);
			staticText.setWidth(colWidth);
			staticText.setHeight(20);
			staticText.setText(col);
			staticText.setMode(ModeEnum.OPAQUE);
			staticText.setBackcolor(Color.LIGHT_GRAY);
			staticText.getLineBox().getPen().setLineWidth(0.5f);
			staticText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
			staticText.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
			columnHeaderBand.addElement(staticText);
			x +=colWidth;
		}
		
		design.setColumnHeader(columnHeaderBand);
		
		JRDesignBand detailsBand = new JRDesignBand();
		
		detailsBand.setHeight(20);
		x=0;
		
		for( String col:selectedColumns) {
			
			JRDesignTextField textField = new JRDesignTextField();
			textField.setX(x);
			textField.setY(0);
			textField.setWidth(colWidth);
			textField.setHeight(20);
			textField.setMode(ModeEnum.OPAQUE);
			textField.setBackcolor(Color.LIGHT_GRAY);
			textField.getLineBox().getPen().setLineWidth(0.5f);
			textField.setExpression(new JRDesignExpression("$F{"+col +"}"));
			textField.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
			textField.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
			detailsBand.addElement(textField);
			x +=colWidth;
		}
		
		((JRDesignSection) design.getDetailSection()).addBand(detailsBand);
		
		return JasperCompileManager.compileReport(design);
	}
	
	

}
