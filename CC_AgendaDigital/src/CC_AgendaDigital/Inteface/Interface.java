package CC_AgendaDigital.Inteface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import CC_AgendaDigital.Core.Pessoa;
import CC_AgendaDigital.DAO.SQLite;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface implements IButtonController {

	private JFrame frame;
	ArrayList<Pessoa> ListaPessoas;
	JLabel lblNewLabel;
	JList<Pessoa> JListPessoas;
	JPanel panelNewPessoa;
	DefaultListModel<Pessoa> model;
	static String s;

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		s = new File("").getCanonicalPath();
		new SQLite(s + "\\AgendaDigitalDb.sqlite");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public Interface() throws IOException {
		initialize();

		createOrUpdateListPessoas();
		// panelNewPessoa = ButtonController.newPessoa();
	}

	private void initialize() throws IOException {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();

		frame = new JFrame("AgendaDigital");
		frame.setBounds(200, 200, scrnsize.width - 20, scrnsize.height - 40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
	}

	private void createOrUpdateListPessoas() {
		lblNewLabel = new JLabel("Pessoas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 195, 38);
		frame.getContentPane().add(lblNewLabel);

		ListaPessoas = SQLite.getPessoas();
		model = new DefaultListModel<Pessoa>();
		JListPessoas = new JList<Pessoa>(model);
		JListPessoas.setBounds(10, 41, 185, 587);

		for (int i = 0; i < ListaPessoas.size(); i++) {
			model.addElement(ListaPessoas.get(i));
		}
		frame.getContentPane().add(JListPessoas);

		JButton btnNovaPessoa = new JButton("Nova");
		btnNovaPessoa.setBounds(10, 639, 185, 39);
		frame.getContentPane().add(btnNovaPessoa);

		btnNovaPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					newPessoa();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.revalidate();
				frame.repaint();
			}
		});

	}

	public void newPessoa() throws IOException {
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setForeground(Color.WHITE);
		panel.setBounds(205, 0, 1166, 707);
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel_1 = new JLabel(s);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 1116, 39);
		panel.add(lblNewLabel_1);
		// JLabel lblNewLabel_1 = new JLabel("Cadastrar");

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(371, 117, 147, 28);
		panel.add(lblNome);

		JTextField txtDigiteONome = new JTextField();
		txtDigiteONome.setToolTipText("");
		txtDigiteONome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtDigiteONome.setForeground(Color.BLACK);
		txtDigiteONome.setBounds(588, 125, 198, 20);
		panel.add(txtDigiteONome);
		txtDigiteONome.setColumns(10);

		JLabel lblIdade = new JLabel("Idade: ");
		lblIdade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdade.setBounds(371, 156, 147, 28);
		panel.add(lblIdade);

		JTextField txtDigiteAIdade = new JTextField();
		txtDigiteAIdade.setForeground(Color.BLACK);
		txtDigiteAIdade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtDigiteAIdade.setColumns(10);
		txtDigiteAIdade.setBounds(588, 164, 198, 20);
		panel.add(txtDigiteAIdade);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento: ");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataDeNascimento.setBounds(371, 195, 147, 28);
		panel.add(lblDataDeNascimento);

		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("00/00/0000");
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textField.setColumns(10);
		textField.setBounds(588, 201, 198, 20);
		panel.add(textField);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SQLite.insertPessoa(new Pessoa(txtDigiteONome.getText(), textField.getText(),
						Integer.parseInt(txtDigiteAIdade.getText())));
				ListaPessoas = SQLite.getPessoas();
				// JListPessoas = new JList<Pessoa>(model);
				/*
				 * for (int i = 0; i < ListaPessoas.size(); i++) {
				 * model.addElement(ListaPessoas.get(i)); }
				 */
				model.addElement(ListaPessoas.get(ListaPessoas.size() - 1));
				frame.getContentPane().add(JListPessoas);
				frame.revalidate();
				frame.repaint();
			}
		});
		btnCadastrar.setBounds(958, 657, 198, 39);
		panel.add(btnCadastrar);
	}

}