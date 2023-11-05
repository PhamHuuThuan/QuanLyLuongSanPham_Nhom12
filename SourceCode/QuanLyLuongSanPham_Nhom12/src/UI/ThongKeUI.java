package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdesktop.swingx.JXDatePicker;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import CustomUI.CustomComboBoxUI;
import CustomUI.CustomListCellRenderer;
import CustomUI.ImageScaler;
import CustomUI.RoundedButton;

public class ThongKeUI extends JPanel implements ActionListener, MouseListener {
	private static final Toolkit s = null;
	private MainUI main;
	private Color bgColor = Color.WHITE;
	private Color componentColor = Color.decode("#424242");
	private Color textColor = Color.BLACK;
	private JTextField btnThang;
	private RoundedButton btnNam;
	private JXDatePicker dtpThang, dtpNam;
	private JTextField txtNgay;
	private JComponent chartPanel;
	private JFreeChart pieChart;
	
	/**
	 * Create the panel.
	 */
	public ThongKeUI(MainUI main) {
		this.main = main;
		setLayout(new BorderLayout());

        // Tiêu đề "Thống kê"
        JPanel pnlTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle = new JLabel("THỐNG KÊ");
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        pnlTitle.add(lblTitle);
        add(pnlTitle, BorderLayout.NORTH);

        // Phần chọn tháng và năm
        JPanel pnlPBvaTime = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlPBvaTime.setBackground(Color.WHITE);
        
        JLabel lblNgayThang = new JLabel("Tháng năm :");
		pnlPBvaTime.add(lblNgayThang);
		lblNgayThang.setForeground(textColor);
		lblNgayThang.setFont(main.roboto_regular.deriveFont(Font.PLAIN, 25F));

        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -2);
        Date earliestDate = calendar.getTime();
        Date latestDate = new Date(); // Ngày hiện tại

        SpinnerDateModel model = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.MONTH);
        JSpinner spnThangNam = new JSpinner(model);

        // Định dạng hiển thị cho JSpinner
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spnThangNam, "MM/yyyy");
        spnThangNam.setEditor(editor);

        // Tăng kích thước ô chọn tháng và năm
        Dimension largerSize = new Dimension(editor.getPreferredSize());
        largerSize.width += 50; // Tăng kích thước theo chiều rộng
        largerSize.height += 20; 
        JSpinner.DefaultEditor editor1 = (JSpinner.DefaultEditor) spnThangNam.getEditor();
        editor1.getTextField().setFont(new Font("Arial", Font.PLAIN, 20)); // Đổi kích thước chữ tại đây
        spnThangNam.setPreferredSize(largerSize);

        pnlPBvaTime.add(spnThangNam);
        pnlPBvaTime.add(Box.createHorizontalStrut(30));

        add(pnlPBvaTime, BorderLayout.CENTER);

        setLayout(new BorderLayout());

        // Tạo dữ liệu mẫu (tên phòng ban và bình quân lương)
        String[] departments = {"năm 2018", "năm 2019", "năm 2020", "năm 2021", "năm 2022"};
        double[] salaries = {3000, 3500, 3200, 2800, 4000}; // Bình quân lương tương ứng

        // Tạo dataset cho biểu đồ cột
        DefaultCategoryDataset barDataset = createDataset(departments, salaries);
        JFreeChart barChart = ChartFactory.createBarChart(
                "Bình Quân Lương theo Phòng Ban",
                "Phòng Ban",
                "Bình Quân Lương",
                barDataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        // Tạo dataset cho biểu đồ cột thứ hai (mẫu)
        DefaultCategoryDataset barDataset1 = createDataset(departments, salaries);
        JFreeChart barChart1 = ChartFactory.createBarChart(
                "Biểu Đồ giá trị hợp đồng theo tháng ",
                null, null, barDataset1,
                PlotOrientation.VERTICAL,
                false, true, false);

        // Tạo biểu đồ cột panel và thiết lập kích thước
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setPreferredSize(new Dimension(400, 400));

        // Tạo biểu đồ cột thứ hai panel và thiết lập kích thước
        ChartPanel barChartPanel1 = new ChartPanel(barChart1);
        barChartPanel1.setPreferredSize(new Dimension(400, 400));

        // Panel chứa biểu đồ cột
        JPanel barChartContainer = new JPanel(new FlowLayout());
        barChartContainer.add(barChartPanel);
        barChartContainer.add(barChartPanel1);

        // Thêm panel chứa biểu đồ cột vào giao diện
        add(barChartContainer, BorderLayout.CENTER);

        // Tạo dataset cho biểu đồ hình tròn
        DefaultPieDataset pieDataset = createPieDataset(salaries);
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Thống Kê Số Nhân Viên và Công Nhân",
                pieDataset,
                true,
                true,
                false);

        // Tạo biểu đồ hình tròn panel và thiết lập kích thước
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(400, 400));

        // Thêm panel chứa biểu đồ hình tròn vào giao diện
        add(pieChartPanel, BorderLayout.EAST);
    }

    private DefaultCategoryDataset createDataset(String[] departments, double[] salaries) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < departments.length; i++) {
            dataset.addValue(salaries[i], "Bình Quân Lương", departments[i]);
        }
        return dataset;
    }

    private DefaultPieDataset createPieDataset(double[] salaries) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Nhân Viên", salaries[0] + salaries[1] + salaries[2]); // Tổng lương của nhân viên
        dataset.setValue("Công Nhân", salaries[3] + salaries[4]); // Tổng lương của công nhân
        return dataset;
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	}