
package POO_AgendaDigital.Interface;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import POO_AgendaDigital.Infraestrutura.SQLite;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private ToolbarLeft tbLeft;
	private ToolbarTop tbTop;
	private PanelCreatePessoa pnPessoa;

	private static String pathDb;
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		// Region - MainFrame Config
		
		super("Agenda Digital");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 100, (int) (scrnsize.width / 1.30), (int) (scrnsize.height / 1.15));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.setResizable(false);
		
		//EndRegion
		
		try {
			pathDb = new File("").getCanonicalPath();
			new SQLite(pathDb + "\\AgendaDigitalDb.sqlite");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		tbLeft = new ToolbarLeft();
		tbTop = new ToolbarTop();
		pnPessoa = new PanelCreatePessoa();
		
		tbLeft.setBounds(0, 0, 250, 595);
		tbTop.setBounds(250, 0, 802, 80);
		pnPessoa.setBounds(250, 80, 802, 595);
		
		this.getContentPane().add(tbLeft);
		this.getContentPane().add(tbTop);
		this.getContentPane().add(pnPessoa);
		
		this.revalidate();
		this.repaint();
		
		tbLeft.setLayout(null);
	}

}
