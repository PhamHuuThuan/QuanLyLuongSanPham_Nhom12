package Util;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Entity.BangChamCongNhanVien;
import Entity.BangLuongNhanVien;
import Entity.BangPhanCongNhanVien;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class XuatPDF {
	public void xuatHD(HopDongForm hd) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/HopDong-TPT.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		ArrayList<HopDongForm> list = new ArrayList<>();
		
		list.add(hd);
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(list));
		
		JasperViewer.viewReport(print, false);
	}
	public void xuatChiTietLuong(ArrayList<BangChamCongNhanVien> dscc, BangLuongNhanVien blnv, BangPhanCongNhanVien pcnv) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/chitietluong.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		Map<String, Object> ttLuong = new HashMap<String, Object>();
		
		ttLuong.put("maNV", blnv.getNhanVien().getMaNV());
		
		ttLuong.put("hoTen", blnv.getNhanVien().getHoTen());
		
		ttLuong.put("chucVu", pcnv.getChucVu());
		
		ttLuong.put("thangNam", blnv.getThangNam().toString());
		
		ttLuong.put("ngayLam", blnv.getNgayLam() + " ngày");
		
		ttLuong.put("luongCoBan", new DecimalFormat("#,###").format(blnv.getLuongThang()) + "VNĐ");
		
		ttLuong.put("tangCa", new DecimalFormat("#,###").format(blnv.getLuongTangCa()) + " VNĐ");
		
		ttLuong.put("phuCap", new DecimalFormat("#,###").format(blnv.getPhuCap()) + " VNĐ");
		
		ttLuong.put("thucLanh", new DecimalFormat("#,###").format(blnv.getThucLanh()) + " VNĐ");
		
		ArrayList<XuatChamCongForm> ds = new ArrayList<>();
		
		for(BangChamCongNhanVien ccnv : dscc) {
			String trangThai;
			if (ccnv.getTrangThai() == 0) {
			    trangThai = "Đúng giờ";
			} else if (ccnv.getTrangThai() == 1) {
			    trangThai = "Trễ";
			} else if (ccnv.getTrangThai() == 2) {
			    trangThai = "Nghỉ k phép";
			} else {
			    trangThai = "Nghỉ có phép";
			}

			XuatChamCongForm xcc = new XuatChamCongForm(new SimpleDateFormat("dd-MM-yyyy").format(ccnv.getNgayChamCong()), 
					ccnv.getCaLam()==1?"Nửa ngày":"Cả ngày", 
							trangThai, ccnv.getGioDen(), ccnv.getGioTangCa() +" giờ", ccnv.getGhiChu()+" ");
			ds.add(xcc);
		}
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ds);
		
		JasperPrint print = JasperFillManager.fillReport(report, ttLuong, dataSource);
		
		JasperViewer.viewReport(print, false);
	}
}
