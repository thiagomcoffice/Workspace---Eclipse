package CC_AgendaDigital.Inteface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

import CC_AgendaDigital.Core.IButtonController;
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
	private ArrayList<Pessoa> ListaPessoas;
	private JLabel lblPessoas;
	private JList<Pessoa> JListPessoas;
	private DefaultListModel<Pessoa> model;
	private JPanel panelNovaPessoa;
	private JPanel panelPessoa;
	static String s;

	public static void main(String[] args) {

		// Region - getters
		System.out.println("");
				// EndRegion
		
		try {
			s = new File("").getCanonicalPath();
			new SQLite(s + "\\AgendaDigitalDb.sqlite");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		panelNovaPessoa = new JPanel();
		panelPessoa = new JPanel();
		initialize();
		criarJListaPessoas();
	}

	private void initialize() {

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();

		frame = new JFrame("AgendaDigital");
		frame.setBounds(200, 200, scrnsize.width - 20, scrnsize.height - 40);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
	}

	private void criarJListaPessoas() {

		lblPessoas = new JLabel("Pessoas");
		lblPessoas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPessoas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPessoas.setBounds(0, 0, 195, 38);

		frame.getContentPane().add(lblPessoas);

		ListaPessoas = SQLite.getPessoas();
		model = new DefaultListModel<Pessoa>();
		JListPessoas = new JList<Pessoa>(model);
		JListPessoas.setBounds(10, 41, 185, 587);
		
		for (int i = 0; i < ListaPessoas.size(); i++) {
			model.addElement(ListaPessoas.get(i));
		}

		frame.getContentPane().add(JListPessoas);
		JListPessoas.setSelectedIndex(0);
		
		JButton btnNovaPessoa = new JButton("Nova");
		btnNovaPessoa.setBounds(10, 639, 185, 39);
		frame.getContentPane().add(btnNovaPessoa);

		btnNovaPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					newPanelCreatePessoa();
					frame.revalidate();
					frame.repaint();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

	}

	public void newPanelCreatePessoa() throws IOException {
		
		panelPessoa.setVisible(false);
		panelNovaPessoa.setVisible(true);
		
		panelNovaPessoa.setToolTipText("");
		panelNovaPessoa.setBackground(Color.LIGHT_GRAY);
		panelNovaPessoa.setForeground(Color.WHITE);
		panelNovaPessoa.setBounds(205, 0, 1166, 707);
		panelNovaPessoa.setLayout(null);

		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setBounds(10, 11, 1116, 39);

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(371, 117, 147, 28);

		JTextField inputNome = new JTextField();
		inputNome.setToolTipText("");
		inputNome.setFont(new Font("Tahoma", Font.ITALIC, 11));
		inputNome.setForeground(Color.BLACK);
		inputNome.setBounds(588, 125, 198, 20);
		inputNome.setColumns(10);

		JLabel lblIdade = new JLabel("Idade: ");
		lblIdade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIdade.setBounds(371, 156, 147, 28);

		JTextField inputIdade = new JTextField();
		inputIdade.setForeground(Color.BLACK);
		inputIdade.setFont(new Font("Tahoma", Font.ITALIC, 11));
		inputIdade.setColumns(10);
		inputIdade.setBounds(588, 164, 198, 20);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento: ");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataDeNascimento.setBounds(371, 195, 147, 28);

		JTextField inputDataNascimento = new JTextField();
		inputDataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		inputDataNascimento.setText("00/00/0000");
		inputDataNascimento.setForeground(Color.BLACK);
		inputDataNascimento.setFont(new Font("Tahoma", Font.ITALIC, 11));
		inputDataNascimento.setColumns(10);
		inputDataNascimento.setBounds(588, 201, 198, 20);

		frame.getContentPane().add(panelNovaPessoa);
		panelNovaPessoa.add(lblCadastrar);
		panelNovaPessoa.add(lblNome);
		panelNovaPessoa.add(inputNome);
		panelNovaPessoa.add(lblIdade);
		panelNovaPessoa.add(inputIdade);
		panelNovaPessoa.add(lblDataDeNascimento);
		panelNovaPessoa.add(inputDataNascimento);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(958, 657, 198, 39);
		panelNovaPessoa.add(btnCadastrar);
		
		btnCadastrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				SQLite.insertPessoa(new Pessoa(inputNome.getText(), inputDataNascimento.getText(),
						Integer.parseInt(inputIdade.getText())));
				updateJListPessoas();
				JListPessoas.setSelectedIndex(ListaPessoas.size() - 1);
				newPanelPessoaLogada();
			}
		});
	}

	public void newPanelPessoaLogada(){
		panelNovaPessoa.setVisible(false);
		panelPessoa.setVisible(true);
		
		panelPessoa.setToolTipText("");
		panelPessoa.setBackground(Color.LIGHT_GRAY);
		panelPessoa.setForeground(Color.WHITE);
		panelPessoa.setBounds(205, 0, 1166, 707);
		panelPessoa.setLayout(null);
		
		
		Pessoa pessoaSelecionada = JListPessoas.getSelectedValue();
		
		JLabel lblCadastrar = new JLabel(pessoaSelecionada.getNome());
		lblCadastrar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setBounds(10, 11, 1116, 39);
		
		frame.getContentPane().add(panelPessoa);
		panelPessoa.add(lblCadastrar);
		
		frame.revalidate();
		frame.repaint();
	}
	
	public void updateJListPessoas() {
		ListaPessoas = SQLite.getPessoas();
		model.addElement(ListaPessoas.get(ListaPessoas.size() - 1));
		frame.revalidate();
		frame.repaint();

	}

	
}
