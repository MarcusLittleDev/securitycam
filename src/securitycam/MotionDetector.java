package securitycam;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class MotionDetector extends JFrame implements WebcamPanel.Painter{
	
	private static final long serialVersionUID = 1L;

	private final Webcam webcam;
	private final WebcamPanel panel;
	private final WebcamMotionDetector detector;
	private boolean detectedMotion = false;
	
	public MotionDetector() {

		setTitle("Security Camera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		webcam.open(true);

		panel = new WebcamPanel(webcam, false);
		panel.setPainter(this);
		panel.start();

		detector = new WebcamMotionDetector(webcam);
		detector.setInterval(500); // one check per 500 ms
		detector.setPixelThreshold(20);
		detector.start();

		add(panel);

		pack();
		setVisible(true);
	}
	
	@Override
	public void paintPanel(WebcamPanel panel, Graphics2D g2) {
		panel.getDefaultPainter().paintPanel(panel, g2);
	}

	@Override
	public void paintImage(WebcamPanel panel, BufferedImage image, Graphics2D g2) {

		double s = detector.getMotionArea();
		Point cog = detector.getMotionCog();

		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.drawString(String.format("Area: %.2f%%", s), 10, 20);

		if (detector.isMotion()) {
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.RED);
			g.drawOval(cog.x - 5, cog.y - 5, 10, 10);
			if(detectedMotion == false){
				final NotificationSystem alert = new NotificationSystem("9852223196@tmomail.net", "security alert!!", "movement has been seen!! security breach!!"); 
				Email.SendEmail(alert);
				detectedMotion = true;
			}
		} else {
			detectedMotion = false;
			g.setColor(Color.GREEN);
			g.drawRect(cog.x - 5, cog.y - 5, 10, 10);
		}

		g.dispose();

		panel.getDefaultPainter().paintImage(panel, image, g2);
	}

}
