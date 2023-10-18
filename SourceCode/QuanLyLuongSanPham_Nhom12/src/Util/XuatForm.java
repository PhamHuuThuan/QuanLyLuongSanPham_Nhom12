package Util;

import java.io.InputStream;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class XuatForm {
	public void xuatHD(XuatHopDongForm hd) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/HopDong-TPT.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		ArrayList<XuatHopDongForm> list = new ArrayList<>();
		
		list.add(hd);
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(list));
		
		JasperViewer.viewReport(print, false);
	}
}
