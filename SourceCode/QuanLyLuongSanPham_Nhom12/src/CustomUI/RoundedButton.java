package CustomUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class RoundedButton extends JButton {
	private Color color;
    private int gap;
    private int thickness;
    private float alpha;

    public RoundedButton(String label, Color color, int gap, int thickness, float alpha) {
        super(label);
        this.color = color;
        this.gap = gap;
        this.thickness = thickness;
        this.alpha = alpha;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (!this.isEnabled()) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        }
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), gap, gap));
        super.paintComponent(g2d);
        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
        g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, gap, gap));
        g2d.dispose();
    }
}
