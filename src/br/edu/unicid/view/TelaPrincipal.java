package br.edu.unicid.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.unicid.dao.AlunoDAO;
import br.edu.unicid.model.Aluno;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JLabel lblRgm;
	private JTextField txtRgm;
	private JLabel lblNome;
	private JLabel lblEndereo;
	private JLabel lblIdade;
	private JLabel lblEmail;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JLabel lblNewLabel_1;
	private JTextField txtIdade;
	private JTextField txtData;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnListar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblRgm = new JLabel("RGM");
		lblRgm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRgm.setBounds(10, 10, 67, 42);
		contentPane.add(lblRgm);
		
		txtRgm = new JTextField();
		txtRgm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRgm.setBounds(118, 10, 126, 42);
		contentPane.add(txtRgm);
		txtRgm.setColumns(10);
		
		lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(319, 10, 67, 42);
		contentPane.add(lblNome);
		
		lblEndereo = new JLabel("Endereço");
		lblEndereo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEndereo.setBounds(10, 114, 93, 42);
		contentPane.add(lblEndereo);
		
		lblIdade = new JLabel("Idade");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIdade.setBounds(10, 166, 67, 42);
		contentPane.add(lblIdade);
		
		lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(10, 62, 67, 42);
		contentPane.add(lblEmail);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNome.setColumns(10);
		txtNome.setBounds(396, 10, 383, 42);
		contentPane.add(txtNome);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(117, 62, 662, 42);
		contentPane.add(txtEmail);
		
		txtEndereco = new JTextField();
		txtEndereco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(117, 114, 662, 42);
		contentPane.add(txtEndereco);
		
		lblNewLabel_1 = new JLabel("Data Nascimento");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(296, 166, 173, 42);
		contentPane.add(lblNewLabel_1);
		
		txtIdade = new JTextField();
		txtIdade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtIdade.setColumns(10);
		txtIdade.setBounds(117, 166, 157, 42);
		contentPane.add(txtIdade);
		
		txtData = new JTextField();
		txtData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtData.setColumns(10);
		txtData.setBounds(457, 166, 322, 42);
		contentPane.add(txtData);
		
		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRgm.setText(null);
				txtNome.setText(null);
				txtEmail.setText(null);
				txtData.setText(null);
				txtEndereco.setText(null);
				txtIdade.setText(null);
			}
		});
		
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNovo.setBounds(30, 261, 108, 42);
		contentPane.add(btnNovo);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// salva aluno
				Aluno aluno = new Aluno();
				// popular o meu objeto
				aluno.setRgm(Integer.parseInt(txtRgm.getText()));
				aluno.setNome(txtNome.getText());
				aluno.setEmail(txtEmail.getText());
				aluno.setDtaNascimento(txtData.getText());
				aluno.setEndereco(txtEndereco.getText());
				aluno.setIdade(Integer.parseInt(txtIdade.getText()));
				// abrir o BD
				try {
					AlunoDAO dao = new AlunoDAO();
					// salvar
					dao.salvar(aluno);
					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");				
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalvar.setBounds(164, 261, 108, 42);
		contentPane.add(btnSalvar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConsultar.setBounds(297, 261, 108, 42);
		contentPane.add(btnConsultar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAlterar.setBounds(30, 327, 108, 42);
		contentPane.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExcluir.setBounds(166, 327, 108, 42);
		contentPane.add(btnExcluir);
		
		btnListar = new JButton("Listar");
		btnListar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListar.setBounds(297, 327, 108, 42);
		contentPane.add(btnListar);
		
		JLabel lblMensagem = new JLabel("");
		lblMensagem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblMensagem.setBounds(457, 279, 322, 65);
		contentPane.add(lblMensagem);
	}
}
