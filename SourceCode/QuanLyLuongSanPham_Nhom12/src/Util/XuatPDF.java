package Util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import CustomUI.ImageScaler;
import Entity.BangChamCongNhanVien;
import Entity.BangLuongNhanVien;
import Entity.BangPhanCongNhanVien;
import Entity.HopDong;
import Entity.NhanVien;
import Entity.SanPham;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class XuatPDF {
	public void xuatHD(HopDong hd, ArrayList<SanPham> dsSP) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/HopDongForm.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		Map<String, Object> ttHD = new HashMap<String, Object>();
		
		ttHD.put("time", "TP HCM, ngày " + new Date().getDay() + " tháng " + new Date().getMonth() + " năm " + new Date().getYear());
		
		ttHD.put("maHD", hd.getMaHD());
		
		ttHD.put("tenHD", hd.getTenHD());
		
		ttHD.put("tenKH", hd.getTenKhachHang());
		
		ttHD.put("ngayBD", new SimpleDateFormat("dd-MM-yyyy").format(hd.getNgayBatDau()));
		
		ttHD.put("ngayHoanThanh", new SimpleDateFormat("dd-MM-yyyy").format(hd.getNgayKetThuc()));
		
		ttHD.put("giaTri", new DecimalFormat("#,###").format(hd.getGiaTriHD()) + " VNĐ");
		
		ttHD.put("tienCoc", new DecimalFormat("#,###").format(hd.getTienCoc()) + " VNĐ");
		
		ttHD.put("thoaThuan", hd.getThoaThuan());
		
		ttHD.put("nguoiDaiDien", hd.getNguoiDaiDien().getHoTen());
		
		ArrayList<SanPhamForm> list = new ArrayList<>();
		
		for(int i = 0; i < dsSP.size(); i++) {
			list.add(new SanPhamForm(
					String.valueOf(i), 
					dsSP.get(i).getMaSP(), 
					dsSP.get(i).getTenSP(), 
					dsSP.get(i).getDonViTinh(), 
					String.valueOf(dsSP.get(i).getSoLuong()), 
					 new DecimalFormat("#,###").format(dsSP.get(i).getDonGia())));
		}
		
		JasperPrint print = JasperFillManager.fillReport(report, ttHD, new JRBeanCollectionDataSource(list));
		
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
		
		ttLuong.put("luongTangCa", new DecimalFormat("#,###").format(blnv.getLuongTangCa()) + " VNĐ");
		
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
	public void xuatThongTinNhanVien(NhanVien nv ,BangPhanCongNhanVien pcnv) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/ThongTinNV.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		Map<String, Object> ttnv = new HashMap<String, Object>();
		
		ttnv.put("maNV", nv.getMaNV());
		ttnv.put("hoTen", nv.getHoTen());
		ttnv.put("gioiTinh", nv.isGioiTinh()?"Nam":"Nữ");
		ttnv.put("ngaySinh", new SimpleDateFormat("dd-MM-yyyy").format(nv.getNgaySinh()));
		ttnv.put("sdt", nv.getSdt());
		ttnv.put("email", nv.getEmail());
		ttnv.put("cccd", nv.getcCCD());
		ttnv.put("diaChi", nv.getDiaChi());
		if(pcnv.getPhongBan()!=null) {
			ttnv.put("phongBan", pcnv.getPhongBan().getTenPhongBan());
			ttnv.put("chucVu", pcnv.getChucVu());
			ttnv.put("ngayVL", new SimpleDateFormat("dd-MM-yyyy").format(pcnv.getNgayCongTac()));
			ttnv.put("ghiChu", pcnv.getGhiChu());
		}else {
			ttnv.put("phongBan", "Chưa phân công");
			ttnv.put("chucVu", "null");
			ttnv.put("ngayVL", "null");
			ttnv.put("ghiChu", "null");
		}
		
		ArrayList<String> content = new ArrayList<>();
		
		content.add(null);
		
		JasperPrint print = JasperFillManager.fillReport(report, ttnv, new JRBeanCollectionDataSource(content));
		
		JasperViewer.viewReport(print, false);
	}
}
