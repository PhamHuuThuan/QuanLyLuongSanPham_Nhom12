package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import Dao.CongNhan_Dao;
import Dao.NhanVien_Dao;
import Dao.TinhLuongNhanVien_Dao;

public class ThongKeUI extends JPanel {
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JSpinner spnThangNam, spnNam;
	private SpinnerModel spnModelThangNam, spnModelNam;
	private Date timeDefault;
	private JLabel lblNV[], lblCN[];
	private NhanVien_Dao nv_Dao = new NhanVien_Dao();
	private CongNhan_Dao cn_Dao = new CongNhan_Dao();
	private TinhLuongNhanVien_Dao tlnv_Dao = new TinhLuongNhanVien_Dao();
	
	public ThongKeUI(MainUI main) {
		this.main = main;
		setLayout(new GridLayout(2, 2, 50, 50));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
		
		//Tạo jpanel chứa biểu đồ lương theo phòng ban
		JPanel pnlLuongPB = new JPanel();
		pnlLuongPB.setLayout(new BoxLayout(pnlLuongPB, BoxLayout.Y_AXIS));
		pnlLuongPB.setBackground(bgColor);
		add(pnlLuongPB);
		
		//Spninner chọn tháng năm cho biểu đồ
		JPanel pnlThangNam = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlThangNam.setBackground(bgColor);
		pnlLuongPB.add(pnlThangNam);
		
		//JLabel lblNgayThang = new JLabel(main.read_file_languages.getString("lblThangNam") + ":");
		JLabel lblNgayThang = new JLabel("Tháng/Năm:");
		pnlThangNam.add(lblNgayThang);
		lblNgayThang.setForeground(textColor);
		lblNgayThang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        timeDefault = calendar.getTime();
        Date latestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date earliestDate = calendar.getTime();
       // Giới hạn trên là ngày hiện tại
       spnModelThangNam = new SpinnerDateModel(timeDefault, earliestDate, latestDate, Calendar.MONTH);
        
     // Tạo một JSpinner với model đã tạo
        spnThangNam = new JSpinner(spnModelThangNam);

        // Đặt định dạng hiển thị cho JSpinner
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnThangNam, "MM/yyyy");
        spnThangNam.setEditor(editor);
        
        javax.swing.border.Border cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10));
		spnThangNam.setBorder(cboBorder);
		spnThangNam.setBackground(bgColor);
		spnThangNam.setForeground(textColor);
		spnThangNam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlThangNam.add(spnThangNam);
		
        // Tạo tập dữ liệu
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(15000, "Lương", "Kỹ thuật");
        dataset.addValue(18000, "Lương", "Kinh doanh");
        dataset.addValue(17000, "Lương", "Nhân sự");
        dataset.addValue(16000, "Lương", "Phát triển");
        dataset.addValue(19000, "Lương", "Tiếp thị");
        dataset.addValue(17500, "Lương", "Hỗ trợ");
        
        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ lương trung bình theo phòng ban", 
                "Phòng ban", "Lương", dataset);
        
        // Tùy chỉnh màu sắc
        chart.setBackgroundPaint(Color.lightGray);
        chart.getPlot().setBackgroundPaint(Color.white);
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.CYAN);

        // Tạo panel chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(bgColor);
        pnlLuongPB.add(chartPanel);
        
        //Danh sách nhân viên và công nhân có lương cao nhất
        JPanel pnlTopLuong = new JPanel(new GridLayout(1, 2, 20, 0));
        TitledBorder titleBorderTTNVPC = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), "Top lương cao nhất");
		titleBorderTTNVPC.setTitleFont(main.roboto_regular.deriveFont(Font.ITALIC, 18F));
		pnlTopLuong.setBorder(BorderFactory.createCompoundBorder(titleBorderTTNVPC, BorderFactory.createEmptyBorder(0, 40, 0, 0)));
        pnlTopLuong.setBackground(bgColor);
        add(pnlTopLuong);
        
        //Nhân viên
        JPanel pnlNhanVien = new JPanel();
        pnlNhanVien.setLayout(new BoxLayout(pnlNhanVien, BoxLayout.Y_AXIS));
        pnlNhanVien.setBackground(bgColor);
        pnlTopLuong.add(pnlNhanVien);
        
		JLabel lblNhanVien = new JLabel("Nhân Viên");
		pnlNhanVien.add(lblNhanVien);
		pnlNhanVien.add(Box.createVerticalStrut(25));
		lblNhanVien.setForeground(textColor);
		lblNhanVien.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		
		lblNV = new JLabel[5];
		
		for(int i = 0; i < 5; i++) {
			lblNV[i] = new JLabel();
			pnlNhanVien.add(lblNV[i]);
			pnlNhanVien.add(Box.createVerticalStrut(25));
			lblNV[i].setForeground(textColor);
			lblNV[i].setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		}
        
        //Công nhân
        JPanel pnlCongNhan = new JPanel();
        pnlCongNhan.setLayout(new BoxLayout(pnlCongNhan, BoxLayout.Y_AXIS));
        pnlCongNhan.setBackground(bgColor);
        pnlTopLuong.add(pnlCongNhan);
        
		JLabel lblCongNhan = new JLabel("Công Nhân");
		pnlCongNhan.add(lblCongNhan);
		lblCongNhan.setForeground(textColor);
		lblCongNhan.setFont(main.roboto_regular.deriveFont(Font.BOLD, 18F));
		
		lblCN = new JLabel[5];
		
		for(int i = 0; i < 5; i++) {
			lblCN[i] = new JLabel();
			pnlNhanVien.add(lblCN[i]);
			pnlNhanVien.add(Box.createVerticalStrut(25));
			lblCN[i].setForeground(textColor);
			lblCN[i].setFont(main.roboto_regular.deriveFont(Font.PLAIN, 14F));
		}
        
        // Tạo tập dữ liệu
        DefaultPieDataset dataset1 = new DefaultPieDataset();
        dataset1.setValue("Công nhân", cn_Dao.laySoLuongCongNhan());
        dataset1.setValue("Nhân viên", nv_Dao.laySoLuongNhanVien());
        
        // Tạo biểu đồ
        JFreeChart chart1 = ChartFactory.createPieChart(
                "Biểu đồ số lượng công nhân và nhân viên", 
                dataset1, true, true, false);

        // Tạo panel chứa biểu đồ
        ChartPanel chartPnlCNNV = new ChartPanel(chart1);
        add(chartPnlCNNV);
        
		//Tạo jpanel chứa biểu đồ lương theo phòng ban
		JPanel pnlHopDong = new JPanel();
		pnlHopDong.setLayout(new BoxLayout(pnlHopDong, BoxLayout.Y_AXIS));
		pnlHopDong.setBackground(bgColor);
		add(pnlHopDong);
		
		//Spninner chọn tháng năm cho biểu đồ
		JPanel pnlNamHD = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlNamHD.setBackground(bgColor);
		pnlHopDong.add(pnlNamHD);
		
		//JLabel lblNgayThang = new JLabel(main.read_file_languages.getString("lblThangNam") + ":");
		JLabel lblNamHD = new JLabel("Năm:");
		pnlNamHD.add(lblNamHD);
		lblNamHD.setForeground(textColor);
		lblNamHD.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		
		Calendar calendar1 = Calendar.getInstance();
		Date timeDefault1 = calendar1.getTime();
		Date latestDate1 = calendar1.getTime();
		calendar1.add(Calendar.YEAR, -2);
		Date earliestDate1 = calendar1.getTime();
		// Giới hạn trên là ngày hiện tại
		SpinnerModel spnModelNam = new SpinnerDateModel(timeDefault1, earliestDate1, latestDate1, Calendar.YEAR);
		    
		// Tạo một JSpinner với model đã tạo
		spnNam = new JSpinner(spnModelNam);

		// Đặt định dạng hiển thị cho JSpinner
		JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spnNam, "yyyy");
		spnNam.setEditor(editor1);
        
        cboBorder = BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, componentColor), 
				BorderFactory.createEmptyBorder(5, 10, 5, 10));
		spnNam.setBorder(cboBorder);
		spnNam.setBackground(bgColor);
		spnNam.setForeground(textColor);
		spnNam.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 16F));
		pnlNamHD.add(spnNam);

        // Tạo tập dữ liệu
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        dataset2.addValue(10000, "Tổng giá trị", "1");
        dataset2.addValue(15000, "Tổng giá trị", "2");
        dataset2.addValue(17000, "Tổng giá trị", "3");
        dataset2.addValue(20000, "Tổng giá trị", "4");
        dataset2.addValue(21000, "Tổng giá trị", "5");
        dataset2.addValue(22000, "Tổng giá trị", "6");
        dataset2.addValue(23000, "Tổng giá trị", "7");
        dataset2.addValue(24000, "Tổng giá trị", "8");
        dataset2.addValue(25000, "Tổng giá trị", "9");
        dataset2.addValue(26000, "Tổng giá trị", "10");
        dataset2.addValue(27000, "Tổng giá trị", "11");
        dataset2.addValue(28000, "Tổng giá trị", "12");

        // Tạo biểu đồ
        JFreeChart chart2 = ChartFactory.createLineChart(
                "Biểu đồ tổng giá trị hợp đồng theo tháng", 
                "Tháng", "Tổng giá trị", dataset2);

        // Tạo panel chứa biểu đồ
        ChartPanel chartPnlHD = new ChartPanel(chart2);
        pnlHopDong.add(chartPnlHD);
        
        dsLuongCaoNhat(tlnv_Dao.layTop3NhanVienLuongCaoNhat(), null);
	}
	public void dsLuongCaoNhat(List<String[]> dsNV, List<String[]> dsCN) {
		int i = 0;
		if(dsNV != null && dsNV.size()>0) {
			for (String[] nhanVien : dsNV) {
			    String hoTen = nhanVien[0];
			    String thucLanh = nhanVien[1];
			    lblNV[i++].setText(i + ". " +hoTen + "   -   " + new DecimalFormat("#,###").format(Double.parseDouble(thucLanh)) + " VNĐ");
			}
		}
		if(dsCN != null && dsCN.size()>0) {
			i = 0;
			for (String[] congNhan : dsCN) {
			    String hoTen = congNhan[0];
			    String thucLanh = congNhan[1];
			    lblCN[i++].setText(hoTen + thucLanh + " VNĐ");
			}
		}
	}
}
