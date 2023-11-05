package Util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Entity.BangChamCongNhanVien;
import Entity.BangLuongNhanVien;
import Entity.BangPhanCongNhanVien;
import Entity.NhanVien;

public class XuatExcel {
	public final int COLUMN_INDEX_STT = 0;
	public final int COLUMN_INDEX_MA_NV = 1;
	public final int COLUMN_INDEX_HO_TEN = 2;
	public final int COLUMN_INDEX_GIOI_TINH = 3;
	public final int COLUMN_INDEX_SDT = 4;
	public final int COLUMN_INDEX_DIA_CHI = 5;
	public final int COLUMN_INDEX_PHONG_BAN = 6;
	public final int COLUMN_INDEX_CHUC_VU = 7;
	public final int COLUMN_INDEX_NGAY_CONG_TAC = 8;
	
	// xuất phân công nhân viên
	public void writeTTPC(BangPhanCongNhanVien pcnv, NhanVien nv, Row row, int stt) {
		Cell cell = row.createCell(COLUMN_INDEX_STT);
		cell.setCellValue(stt);

		cell = row.createCell(COLUMN_INDEX_MA_NV);
		cell.setCellValue(nv.getMaNV() != null ? nv.getMaNV() : "");

		cell = row.createCell(COLUMN_INDEX_HO_TEN);
		cell.setCellValue(nv.getHoTen() != null ? nv.getHoTen() : "");

		cell = row.createCell(COLUMN_INDEX_GIOI_TINH);
		cell.setCellValue(nv.isGioiTinh() ? "Nam" : "Nữ");

		cell = row.createCell(COLUMN_INDEX_SDT);
		cell.setCellValue(nv.getSdt() != null ? nv.getSdt() : "");

		cell = row.createCell(COLUMN_INDEX_DIA_CHI);
		cell.setCellValue(nv.getDiaChi() != null ? nv.getDiaChi() : "");

		if(pcnv.getPhongBan() != null) {
		    cell = row.createCell(COLUMN_INDEX_PHONG_BAN);
		    cell.setCellValue(pcnv.getPhongBan().getTenPhongBan() != null ? pcnv.getPhongBan().getTenPhongBan() : "");

		    cell = row.createCell(COLUMN_INDEX_CHUC_VU);
		    cell.setCellValue(pcnv.getChucVu() != null ? pcnv.getChucVu() : "");

		    cell = row.createCell(COLUMN_INDEX_NGAY_CONG_TAC);
		    cell.setCellValue(pcnv.getNgayCongTac() != null ?new SimpleDateFormat("dd-MM-yyyy").format(pcnv.getNgayCongTac()):"");
		} else {
		    cell = row.createCell(COLUMN_INDEX_PHONG_BAN);
		    cell.setCellValue("");

		    cell = row.createCell(COLUMN_INDEX_CHUC_VU);
		    cell.setCellValue("");

		    cell = row.createCell(COLUMN_INDEX_NGAY_CONG_TAC);
		    cell.setCellValue("");
		}

	}
	// xuất chấm công nhân viên
	public void writeTTCC(BangChamCongNhanVien ccnv, Row row, int stt) {
		Cell cell = row.createCell(COLUMN_INDEX_STT);
		cell.setCellValue(stt);

		cell = row.createCell(COLUMN_INDEX_MA_NV);
		String maNV = ccnv.getPhanCong().getNhanVien().getMaNV();
		cell.setCellValue(maNV != null ? maNV : "");

		cell = row.createCell(COLUMN_INDEX_HO_TEN);
		String hoTen = ccnv.getPhanCong().getNhanVien().getHoTen();
		cell.setCellValue(hoTen != null ? hoTen : "");

		cell = row.createCell(3);
		String phongBan = ccnv.getPhanCong().getPhongBan().getTenPhongBan();
		cell.setCellValue(phongBan != null ? phongBan : "");

		cell = row.createCell(4);
		String ngayCham = new SimpleDateFormat("dd-MM-yyyy").format(ccnv.getNgayChamCong());
		cell.setCellValue(ngayCham != null ? ngayCham : "");

		cell = row.createCell(5);
		cell.setCellValue( ccnv.getCaLam() == 0 ? "Nửa ngày(4h)" : "Cả ngày (8h)");
		
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

		cell = row.createCell(6);
		cell.setCellValue(trangThai);

		cell = row.createCell(7);
		cell.setCellValue(ccnv.getGioDen());
		 
		cell = row.createCell(8);
		cell.setCellValue(ccnv.getGioTangCa());
		
		cell = row.createCell(9);
		cell.setCellValue(ccnv.getGhiChu());
	}
	// xuất lương nhân viên
	public void writeTTLuong(BangLuongNhanVien blnv, Row row, int stt) {
		Cell cell = row.createCell(COLUMN_INDEX_STT);
		cell.setCellValue(stt);

		cell = row.createCell(COLUMN_INDEX_MA_NV);
		String maNV = blnv.getNhanVien().getMaNV();
		cell.setCellValue(maNV != null ? maNV : "");

		cell = row.createCell(COLUMN_INDEX_HO_TEN);
		String hoTen = blnv.getNhanVien().getHoTen();
		cell.setCellValue(hoTen != null ? hoTen : "");

		cell = row.createCell(3);
		cell.setCellValue(blnv.getNgayLam() >= 0 ? blnv.getNgayLam() : 0);

		cell = row.createCell(4);
		cell.setCellValue(blnv.getNgayNghi() >= 0 ? blnv.getNgayNghi() : 0);

		cell = row.createCell(5);
		cell.setCellValue(blnv.getNgayNghiPhep() >= 0 ? blnv.getNgayNghiPhep() : 0);

		cell = row.createCell(6);
		cell.setCellValue(blnv.getLuongThang() >= 0 ? blnv.getLuongThang() : 0);

		cell = row.createCell(7);
		cell.setCellValue(blnv.getLuongTangCa() >= 0 ? blnv.getLuongTangCa() : 0);
		 
		cell = row.createCell(8);
		cell.setCellValue(blnv.getPhuCap() >= 0 ? blnv.getPhuCap() : 0);
		
		cell = row.createCell(9);
		cell.setCellValue(blnv.getThucLanh() >= 0 ? blnv.getThucLanh() : 0);
	}
	//xuất danh sách lương ra excel
	public void writeExcelTTLuong(List<BangLuongNhanVien> dsbl, String excelFilePath) throws IOException {
	    // Create Workbook
	    Workbook workbook = getWorkbook(excelFilePath);

	    // Create sheet
	    org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Bảng lương tháng " +  DateTimeFormatter.ofPattern("MM-yyyy").format(dsbl.get(0).getThangNam())); // Create sheet with sheet name

	    int rowIndex = 0;

	    // Write header
	    writeHeaderTTLuong(sheet, rowIndex);

	    // Write data
	    rowIndex++;
	    for (int i = 0; i< dsbl.size(); i++) {
	        // Create row
	        Row row = sheet.createRow(rowIndex);
	        //Write data on row
	        writeTTLuong(dsbl.get(i), row, rowIndex);
	        rowIndex++;
	    }
	    
	    writeFooterTTLuong(sheet, rowIndex);

	    // Auto resize column width
	    int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
	    autosizeColumn(sheet, numberOfColumn);

	    // Create file excel
	    createOutputFile(workbook, excelFilePath);
		new SoundPlay().playSE(1);
	    int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có muốn mở tệp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	    if(dialogResult == JOptionPane.YES_OPTION){
	      // Mở tệp
	      Desktop desktop = Desktop.getDesktop();
	      File file = new File(excelFilePath);
	      if(file.exists()) desktop.open(file);
	    }
	}
	//xuất danh sách chấm công ra excel
	public void writeExcelTTCC(List<BangChamCongNhanVien> dscc, String excelFilePath) throws IOException {
	    // Create Workbook
	    Workbook workbook = getWorkbook(excelFilePath);

	    // Create sheet
	    org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Chấm công ngày " + new SimpleDateFormat("dd-MM-yyyy").format(dscc.get(0).getNgayChamCong())); // Create sheet with sheet name

	    int rowIndex = 0;

	    // Write header
	    writeHeaderTTCC(sheet, rowIndex);

	    // Write data
	    rowIndex++;
	    for (int i = 0; i< dscc.size(); i++) {
	        // Create row
	        Row row = sheet.createRow(rowIndex);
	        //Write data on row
	        writeTTCC(dscc.get(i), row, rowIndex);
	        rowIndex++;
	    }
	    
	    writeFooterTTCC(sheet, rowIndex);

	    // Auto resize column width
	    int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
	    autosizeColumn(sheet, numberOfColumn);

	    // Create file excel
	    createOutputFile(workbook, excelFilePath);
		new SoundPlay().playSE(1);
	    int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có muốn mở tệp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	    if(dialogResult == JOptionPane.YES_OPTION){
	      // Mở tệp
	      Desktop desktop = Desktop.getDesktop();
	      File file = new File(excelFilePath);
	      if(file.exists()) desktop.open(file);
	    }
	}
	
	//xuất danh sách thông tin phân công ra excel
	public void writeExcelTTPC(List<BangPhanCongNhanVien> dspc, List<NhanVien> dsnv, String excelFilePath) throws IOException {
	    // Create Workbook
	    Workbook workbook = getWorkbook(excelFilePath);

	    // Create sheet
	    org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Nhân Viên"); // Create sheet with sheet name

	    int rowIndex = 0;

	    // Write header
	    writeHeaderTTPC(sheet, rowIndex);

	    // Write data
	    rowIndex++;
	    for (int i = 0; i< dsnv.size(); i++) {
	        // Create row
	        Row row = sheet.createRow(rowIndex);
	        // Write data on row
	        writeTTPC(dspc.get(i), dsnv.get(i), row, rowIndex);
	        rowIndex++;
	    }
	    
	    writeFooterTTPC(sheet, rowIndex);

	    // Auto resize column width
	    int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
	    autosizeColumn(sheet, numberOfColumn);

	    // Create file excel
	    createOutputFile(workbook, excelFilePath);
		new SoundPlay().playSE(1);
	    int dialogResult = JOptionPane.showConfirmDialog (null, "Bạn có muốn mở tệp này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	    if(dialogResult == JOptionPane.YES_OPTION){
	      // Mở tệp
	      Desktop desktop = Desktop.getDesktop();
	      File file = new File(excelFilePath);
	      if(file.exists()) desktop.open(file);
	    }
	}

    private Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }

	// tạo kiểu cho cột header
	private static CellStyle createStyleForHeader(org.apache.poi.ss.usermodel.Sheet sheet) {
	    // Create font
	    Font font = sheet.getWorkbook().createFont();
	    font.setFontName("Times New Roman"); 
	    font.setBold(true);
	    font.setFontHeightInPoints((short) 12); // font size
	    font.setColor(IndexedColors.WHITE.getIndex()); // text color

	    // Create CellStyle
	    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    cellStyle.setFont(font);
	    cellStyle.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
	    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    cellStyle.setBorderBottom(BorderStyle.THIN);
	    return cellStyle;
	}
	// Write header with format
	private void writeHeaderTTLuong(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex) {
	    // create CellStyle
	    CellStyle cellStyle = createStyleForHeader(sheet);
	     
	    // Create row
	    Row row = sheet.createRow(rowIndex);
	     
	    // Create cells
	    Cell cell = row.createCell(COLUMN_INDEX_STT);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("STT");

	    cell = row.createCell(COLUMN_INDEX_MA_NV);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Mã NV");

	    cell = row.createCell(COLUMN_INDEX_HO_TEN);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Họ Tên");

	    cell = row.createCell(3);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Số ngày làm");

	    cell = row.createCell(4);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Nghỉ k phép");

	    cell = row.createCell(5);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Nghỉ có phép");

	    cell = row.createCell(6);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Lương tháng (VNĐ)");

	    cell = row.createCell(7);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Tăng ca (VNĐ)");

	    cell = row.createCell(8);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Phụ cấp (VNĐ)");
	    
	    cell = row.createCell(9);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Thực lãnh (VNĐ)");
	}
	// Write header with format: các tiêu đề cột bảng chấm công
	private void writeHeaderTTCC(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex) {
	    // create CellStyle
	    CellStyle cellStyle = createStyleForHeader(sheet);
	     
	    // Create row
	    Row row = sheet.createRow(rowIndex);
	     
	    // Create cells
	    Cell cell = row.createCell(COLUMN_INDEX_STT);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("STT");

	    cell = row.createCell(COLUMN_INDEX_MA_NV);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Mã NV");

	    cell = row.createCell(COLUMN_INDEX_HO_TEN);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Họ Tên");

	    cell = row.createCell(3);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Phòng ban");

	    cell = row.createCell(4);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Ngày chấm");

	    cell = row.createCell(5);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Ca Làm");

	    cell = row.createCell(6);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Trạng thái");

	    cell = row.createCell(7);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Giờ đến");

	    cell = row.createCell(8);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Tăng ca (h)");
	    
	    cell = row.createCell(9);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Ghi chú");
	}
	// Write header with format
	private void writeHeaderTTPC(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex) {
	    // create CellStyle
	    CellStyle cellStyle = createStyleForHeader(sheet);
	     
	    // Create row
	    Row row = sheet.createRow(rowIndex);
	     
	    // Create cells
	    Cell cell = row.createCell(COLUMN_INDEX_STT);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("STT");

	    cell = row.createCell(COLUMN_INDEX_MA_NV);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Mã NV");

	    cell = row.createCell(COLUMN_INDEX_HO_TEN);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Họ Tên");

	    cell = row.createCell(COLUMN_INDEX_GIOI_TINH);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Giới Tính");

	    cell = row.createCell(COLUMN_INDEX_SDT);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("SĐT");

	    cell = row.createCell(COLUMN_INDEX_DIA_CHI);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Địa Chỉ");

	    cell = row.createCell(COLUMN_INDEX_PHONG_BAN);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Phòng Ban");

	    cell = row.createCell(COLUMN_INDEX_CHUC_VU);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Chức Vụ");

	    cell = row.createCell(COLUMN_INDEX_NGAY_CONG_TAC);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Ngày Công Tác");
	}
	// Write footer: tổng số số ngày, số lương, thực lãnh
	private void writeFooterTTLuong(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex) {
	    // Create row
	    Row row = sheet.createRow(rowIndex);
	    
	    CellStyle cellStyle = createStyleForHeader(sheet);
	    
	    Cell cell = row.createCell(COLUMN_INDEX_STT);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Tổng cộng:");
	    
	 // Gộp các cột từ COLUMN_INDEX_STT đến COLUMN_INDEX_HO_TEN
	    sheet.addMergedRegion(new CellRangeAddress(
	            rowIndex, // first row (0-based)
	            rowIndex, // last row  (0-based)
	            COLUMN_INDEX_STT, // first column (0-based)
	            COLUMN_INDEX_HO_TEN  // last column  (0-based)
	    ));
	    //tổng ngày làm
	    cell = row.createCell(3, CellType.FORMULA);
	    cell.setCellFormula("SUM(D2:D" + rowIndex + ")");
	    
	    cell = row.createCell(4, CellType.FORMULA);
	    cell.setCellFormula("SUM(E2:E" + rowIndex + ")");

	    cell = row.createCell(5, CellType.FORMULA);
	    cell.setCellFormula("SUM(F2:F" + rowIndex + ")");

	    cell = row.createCell(6, CellType.FORMULA);
	    cell.setCellFormula("SUM(G2:G" + rowIndex + ")");
	    
	    cell = row.createCell(7, CellType.FORMULA);
	    cell.setCellFormula("SUM(H2:H" + rowIndex + ")");
	    
	    cell = row.createCell(8, CellType.FORMULA);
	    cell.setCellFormula("SUM(I2:I" + rowIndex + ")");
	    
	    cell = row.createCell(9, CellType.FORMULA);
	    cell.setCellFormula("SUM(J2:J" + rowIndex + ")");
	    
	}
	// Write footer: tổng số nhân viên, số giờ tăng ca
	private void writeFooterTTCC(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex) {
	    // Create row
	    Row row = sheet.createRow(rowIndex);
	    
	    CellStyle cellStyle = createStyleForHeader(sheet);
	    
	    Cell cell = row.createCell(COLUMN_INDEX_STT);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Tổng cộng:");
	    
	 // Gộp các cột từ COLUMN_INDEX_STT đến COLUMN_INDEX_HO_TEN
	    sheet.addMergedRegion(new CellRangeAddress(
	            rowIndex, // first row (0-based)
	            rowIndex, // last row  (0-based)
	            COLUMN_INDEX_STT, // first column (0-based)
	            COLUMN_INDEX_HO_TEN  // last column  (0-based)
	    ));
	    
	    cell = row.createCell(8, CellType.FORMULA);
	    cell.setCellFormula("SUM(I2:I" + rowIndex + ")");
	    
	}
	// Write footer: tổng số nhân viên trong ds
	private void writeFooterTTPC(org.apache.poi.ss.usermodel.Sheet sheet, int rowIndex) {
	    // Create row
	    Row row = sheet.createRow(rowIndex);
	    
	    CellStyle cellStyle = createStyleForHeader(sheet);
	    
	    Cell cell = row.createCell(COLUMN_INDEX_STT);
	    cell.setCellStyle(cellStyle);
	    cell.setCellValue("Tổng số nhân viên là:");
	    
	 // Gộp các cột từ COLUMN_INDEX_STT đến COLUMN_INDEX_HO_TEN
	    sheet.addMergedRegion(new CellRangeAddress(
	            rowIndex, // first row (0-based)
	            rowIndex, // last row  (0-based)
	            COLUMN_INDEX_STT, // first column (0-based)
	            COLUMN_INDEX_HO_TEN  // last column  (0-based)
	    ));
	    
	    cell = row.createCell(COLUMN_INDEX_GIOI_TINH, CellType.FORMULA);
	    cell.setCellFormula("COUNTA(A2:A" + rowIndex + ")");
	    
	    cell = row.createCell(COLUMN_INDEX_SDT);
	    cell.setCellValue("nhân viên");
	}

	// Auto resize column width
	private void autosizeColumn(org.apache.poi.ss.usermodel.Sheet sheet, int lastColumn) {
	    for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
	        sheet.autoSizeColumn(columnIndex);
	    }
	}

	// Create output file
	private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
	    try (OutputStream os = new FileOutputStream(excelFilePath)) {
	        workbook.write(os);
	    }
	}

}
