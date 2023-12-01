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
import Entity.CongNhan;
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
		
		ttHD.put("time", "TP HCM, ngày " + hd.getNgayBatDau().getDate() + " tháng " + (hd.getNgayBatDau().getMonth() + 1) + " năm " + (hd.getNgayBatDau().getYear() + 1900));
		
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
		new SoundPlay().playSE(1);
	}
	public void xuatChiTietLuong(ArrayList<BangChamCongNhanVien> dscc, BangLuongNhanVien blnv, BangPhanCongNhanVien pcnv) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/chitietluong.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		Map<String, Object> ttLuong = new HashMap<String, Object>();
		
		ttLuong.put("maNV", blnv.getNhanVien().getMaNV());
		
		ttLuong.put("hoTen", blnv.getNhanVien().getHoTen());
		
		ttLuong.put("chucVu", pcnv.getChucVu());
		
		String thangNam = blnv.getThangNam().toString();
		String[] parts = thangNam.split("-");
		String result = parts[1] + "-" + parts[0];
		
		ttLuong.put("thangNam", result);
		
		ttLuong.put("ngayLam", blnv.getNgayLam() + " ngày");
		
		ttLuong.put("luongCoBan", new DecimalFormat("#,###").format(blnv.getLuongThang()) + "VNĐ");
		
		ttLuong.put("luongTangCa", new DecimalFormat("#,###").format(blnv.getLuongTangCa()) + " VNĐ");
		
		ttLuong.put("phuCap", new DecimalFormat("#,###").format(blnv.getPhuCap()) + " VNĐ");
		
		ttLuong.put("thucLanh", new DecimalFormat("#,###").format(blnv.getThucLanh()) + " VNĐ");
		
		ArrayList<XuatChamCongForm> ds = new ArrayList<>();
		if(dscc.size()<=0) {
			ds.add(null);
		}
		
		for(BangChamCongNhanVien ccnv : dscc) {
			String trangThai;
			if (ccnv.isDiLam()) {
			    trangThai = "Đi làm";
			} else if (ccnv.isCoPhep()) {
			    trangThai = "Nghỉ có phép";
			} else {
			    trangThai = "Nghỉ không phép";
			}

			XuatChamCongForm xcc = new XuatChamCongForm(new SimpleDateFormat("dd-MM-yyyy").format(ccnv.getNgayChamCong()), 
					ccnv.getCaLam()==1?"Nửa ngày":"Cả ngày", 
							trangThai, ccnv.getGioDen(), ccnv.getGioTangCa() +" giờ", ccnv.getGhiChu()+" ");
			ds.add(xcc);
		}
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ds);
		
		JasperPrint print = JasperFillManager.fillReport(report, ttLuong, dataSource);
		
		JasperViewer.viewReport(print, false);
		new SoundPlay().playSE(1);
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
		new SoundPlay().playSE(1);
	}
	// HÀM XUẤT THÔNG TIN CÔNG NHÂN
	public void xuatThongTinCongNhan(CongNhan cn) throws JRException {
		InputStream arq = getClass().getResourceAsStream("/export_template/ThongTinCN.jrxml");
		
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		Map<String, Object> ttcn = new HashMap<String, Object>();
		
		ttcn.put("maCN", cn.getMaCN());
		ttcn.put("hoTen", cn.getHoTen());
		ttcn.put("gioiTinh", cn.getGioiTinh()?"Nam":"Nữ");
		ttcn.put("ngaySinh", new SimpleDateFormat("dd-MM-yyyy").format(cn.getNgaySinh()));
		ttcn.put("sdt", cn.getSDT());
		ttcn.put("email", cn.getEmail());
		ttcn.put("cccd", cn.getSoCCCD());
		ttcn.put("diaChi", cn.getDiaChi());
		ttcn.put("ngayVaoLam", new SimpleDateFormat("dd-MM-yyyy").format(cn.getNgayVaoLam()));
		ttcn.put("ghiChu", cn.getGhiChu());
		
		ArrayList<String> content = new ArrayList<>();
		
		content.add(null);
		
		JasperPrint print = JasperFillManager.fillReport(report, ttcn, new JRBeanCollectionDataSource(content));
		
		JasperViewer.viewReport(print, false);
		new SoundPlay().playSE(1);
	}
	
	
	
}
