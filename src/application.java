import java.awt.EventQueue;

import javax.swing.JFrame;

public class application extends JFrame {

	private static final long serialVersionUID = -6596113243078458151L;

	public application() {
		initUI();
	}

	private void initUI() {
		add(new main());
		setSize(480, 320); // JFrame size is 480 by 320
		setResizable(false);
		setTitle("PM Dev Build");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				application ex = new application();
				ex.setVisible(true);
			}
		});
	}
}