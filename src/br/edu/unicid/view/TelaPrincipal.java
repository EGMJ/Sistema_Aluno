package br.edu.unicid.view;

import java.awt.EventQueue;
import java.awt.Font;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.awt.event.*;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JTextField;

import br.edu.unicid.dao.AlunoDAO;
import br.edu.unicid.model.Aluno;

import br.edu.unicid.dao.BoletimDAO;
import br.edu.unicid.model.Boletim;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel dados_pessoais;
	private JTable table;
	
	private JTextField txtRgm;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtEndereco;
	private JTextField txtMunicipio;
	private JTextField txtRgmNotas;
	private JTextField txtCursoNotas;
	private JTextField txtNomeNotas;
	private JTextField txtRgmBoletim;
	private JTextField txtNomeBoletim;

	private JFormattedTextField fmtdData;
	private JFormattedTextField fmtdCelular;
	private JFormattedTextField fmtdCpf;
	
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnConsultar;
	private JButton btnListar;
	
	private JComboBox ComboBoxUf;
	
	private MaskFormatter dataMask,celularMask,cpfMask;

	private JMenuBar menuBar;

	private JMenu mnAluno;
	private JMenuItem mntmSalvar;
	private JMenuItem mntmAlterar;
	private JMenuItem mntmConsultar;
	private JMenuItem mntmExcluir;
	private JMenuItem mntmSair;
	
	private JMenu mnNotasEFaltas;
	private JMenuItem mntmConsultar_1;
	private JMenuItem mntmAlterar_1;
	private JMenuItem mntmExcluir_1;
	private JMenuItem mntmConsultar_2;
	
	private JMenu mnAjuda;
	private JMenuItem mntmSobre;
	
	private ButtonGroup radioGrupo;
	private JRadioButton rdbtnMatutino;
	private JRadioButton rdbtnVespertino;
	private JRadioButton rdbtnNoturno;
	
	private JPanel curso;
	private JLabel lblCurso;
	private JLabel lblCampus;
	private JLabel lblPeriodo;
	private JComboBox comboBoxCurso;
	private JComboBox comboBoxCampus;
	private JTextField txtFaltasNotas;
	private JTextField txtNotaBoletim;
	private JTextField txtFaltaBoletim;
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
		setBounds(100, 100, 799, 478);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnAluno = new JMenu("Aluno");
		menuBar.add(mnAluno);
		
		mntmSalvar = new JMenuItem("Salvar");
		
		mntmSalvar.setMnemonic(KeyEvent.CTRL_DOWN_MASK);
		mntmSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			

				System.out.println("teste de salvar");
			}
		});
		mnAluno.add(mntmSalvar);
		
		mntmAlterar = new JMenuItem("Alterar");
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		mnAluno.add(mntmAlterar);
		
		mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		
		mnAluno.add(mntmConsultar);
		
		mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		mnAluno.add(mntmExcluir);
		
		mntmSair = new JMenuItem("Sair");
		mntmSair.setMnemonic(KeyEvent.SHIFT_DOWN_MASK);
		mntmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		mnAluno.add(mntmSair);
		
		mnNotasEFaltas = new JMenu("Notas e Faltas");
		menuBar.add(mnNotasEFaltas);
		
		mntmConsultar_1 = new JMenuItem("Salvar");
		mntmConsultar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		mnNotasEFaltas.add(mntmConsultar_1);
		
		mntmAlterar_1 = new JMenuItem("Alterar");
		mntmAlterar_1.setMnemonic(KeyEvent.CTRL_DOWN_MASK);
		mntmAlterar_1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mntmAlterar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		mnNotasEFaltas.add(mntmAlterar_1);
		
		mntmExcluir_1 = new JMenuItem("Excluir");
		mntmExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		mnNotasEFaltas.add(mntmExcluir_1);
		
		mntmConsultar_2 = new JMenuItem("Consultar");
		mntmConsultar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			
		//	rdbtnNewRadioButton_2.getText();
			}
		});
		mnNotasEFaltas.add(mntmConsultar_2);
		
		// Menu ajuda 
		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
			System.out.println("teste de salvar");
			}
		});
		mnAjuda.add(mntmSobre);
		
		// Fim do menu
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 763, 395);
		contentPane.add(tabbedPane);
		
		dados_pessoais = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, dados_pessoais, null);
		dados_pessoais.setLayout(null);
		
		JLabel lblRgmDadosPessoais = new JLabel("RGM");
		lblRgmDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRgmDadosPessoais.setBounds(10, 35, 40, 25);
		dados_pessoais.add(lblRgmDadosPessoais);
		
		JLabel lblDataNasDadosPessoais = new JLabel("Data de Nascimento");
		lblDataNasDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDataNasDadosPessoais.setBounds(10, 89, 181, 25);
		dados_pessoais.add(lblDataNasDadosPessoais);
		
		JLabel lblEmailDadosPessoais = new JLabel("Email");
		lblEmailDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailDadosPessoais.setBounds(10, 137, 49, 25);
		dados_pessoais.add(lblEmailDadosPessoais);

		JLabel lblEnderecoDadosPessoais = new JLabel("Endereço");
		lblEnderecoDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnderecoDadosPessoais.setBounds(10, 191, 82, 25);
		dados_pessoais.add(lblEnderecoDadosPessoais);
		
		JLabel lblMunicipioDadosPessoais = new JLabel("Municipio");
		lblMunicipioDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMunicipioDadosPessoais.setBounds(10, 252, 83, 25);
		dados_pessoais.add(lblMunicipioDadosPessoais);
		
		JLabel lblUfDadosPessoais = new JLabel("UF");
		lblUfDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUfDadosPessoais.setBounds(289, 268, 23, 25);
		dados_pessoais.add(lblUfDadosPessoais);
		
		JLabel lblNomeDadosPessoais = new JLabel("Nome");
		lblNomeDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNomeDadosPessoais.setBounds(231, 35, 52, 25);
		dados_pessoais.add(lblNomeDadosPessoais);
		
		JLabel lblCpfDadosPessoais = new JLabel("CPF");
		lblCpfDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpfDadosPessoais.setBounds(371, 89, 33, 25);
		dados_pessoais.add(lblCpfDadosPessoais);
		
		JLabel lblCelularDadosPessoais = new JLabel("Celular");
		lblCelularDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCelularDadosPessoais.setBounds(368, 137, 62, 25);
		dados_pessoais.add(lblCelularDadosPessoais);
		
		ComboBoxUf = new JComboBox();
		ComboBoxUf.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		ComboBoxUf.setBounds(324, 257, 54, 40);
		dados_pessoais.add(ComboBoxUf);
				
		txtRgm = new JTextField();
		txtRgm.setBounds(81, 31, 120, 40);
		dados_pessoais.add(txtRgm);
		txtRgm.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setBounds(303, 31, 407, 40);
		dados_pessoais.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(81, 140, 267, 40);
		dados_pessoais.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(102, 197, 267, 40);
		dados_pessoais.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setBounds(103, 258, 157, 40);
		dados_pessoais.add(txtMunicipio);
		txtMunicipio.setColumns(10);
		
// Formatação dos campos
		try {
			celularMask = new MaskFormatter("(##)#####-####");
			dataMask = new MaskFormatter("##/##/####");
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			System.out.println("Erro com as mascaras");
			e.printStackTrace();
		}

		fmtdData = new JFormattedTextField(dataMask);
		fmtdData.setBounds(219, 85, 129, 40);
		dados_pessoais.add(fmtdData);
				
		fmtdCelular = new JFormattedTextField(celularMask);
		fmtdCelular.setBounds(453, 146, 96, 40);
		dados_pessoais.add(fmtdCelular);
		
		fmtdCpf = new JFormattedTextField(cpfMask);
		fmtdCpf.setBounds(430, 85, 101, 40);
		dados_pessoais.add(fmtdCpf);
		
		curso = new JPanel();
		tabbedPane.addTab("Curso", null, curso, null);
		curso.setLayout(null);
		
		lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurso.setBounds(10, 42, 50, 25);
		curso.add(lblCurso);
		
		lblCampus = new JLabel("Campus");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCampus.setBounds(10, 96, 71, 25);
		curso.add(lblCampus);
		
		lblPeriodo = new JLabel("Período");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPeriodo.setBounds(10, 159, 67, 25);
		curso.add(lblPeriodo);
		
		 //Grupo de botoes do tipo radio.
		rdbtnMatutino = new JRadioButton("Matutino");
		rdbtnMatutino.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnMatutino.setActionCommand("Matutino");
		rdbtnMatutino.setBounds(106, 155, 103, 33);
		curso.add(rdbtnMatutino);
		
		rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnVespertino.setBounds(228, 157, 121, 33);
		rdbtnVespertino.setActionCommand("Vespertino");
		curso.add(rdbtnVespertino);
		
		rdbtnNoturno = new JRadioButton("Noturno");
		rdbtnNoturno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnNoturno.setActionCommand("Noturno");
		rdbtnNoturno.setBounds(367, 157, 97, 33);
		curso.add(rdbtnNoturno);
		
		radioGrupo = new ButtonGroup();
		radioGrupo.add(rdbtnMatutino);
		radioGrupo.add(rdbtnVespertino);
		radioGrupo.add(rdbtnNoturno);
		
		comboBoxCurso = new JComboBox();
		comboBoxCurso.setModel(new DefaultComboBoxModel(new String[] {"Selecione o curso", "Ciência da Computação", "Análise e Desenvolvimento de Sistemas", "Engenharia Macânica", "Engenharia Mecatrônica"}));
		comboBoxCurso.setBounds(89, 38, 659, 40);
		curso.add(comboBoxCurso);
		
		comboBoxCampus = new JComboBox();
		comboBoxCampus.setModel(new DefaultComboBoxModel(new String[] {"Selecione o Campus", "Pinheiros", "Tatuapé", "Villa-Lobos", "Paulista"}));
		comboBoxCampus.setBounds(91, 92, 657, 40);
		curso.add(comboBoxCampus);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(24, 271, 89, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// inicializando o aluno
				Aluno aluno = new Aluno();

				// atribuindo o valor dos atributos
				aluno.setRgm(Integer.parseInt(txtRgm.getText()));
				aluno.setNome(txtNome.getText());
				aluno.setEmail(txtEmail.getText());
				aluno.setDtaNascimento(fmtdData.getText());
				aluno.setCpf(fmtdCpf.getText());
				aluno.setCelular(fmtdCelular.getText());
				aluno.setUf(ComboBoxUf.getSelectedItem().toString());
				aluno.setMunicipio(txtMunicipio.getText());
				aluno.setEndereco(txtEndereco.getText());
				aluno.setCurso(comboBoxCurso.getSelectedItem().toString());
				aluno.setCampus(comboBoxCampus.getSelectedItem().toString());
				aluno.setPeriodo(radioGrupo.getSelection().getActionCommand());
						
				// abrir o BD
				try {
					AlunoDAO dao = new AlunoDAO();
					// salvar
					dao.salvar(aluno);
					
					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");				
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		});
		curso.add(btnSalvar);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(120, 271, 89, 23);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}});
		curso.add(btnConsultar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(219, 271, 89, 23);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}});
		curso.add(btnAlterar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(318, 271, 89, 23);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}});
		curso.add(btnExcluir);
		
//		btnListar = new JButton("Listar");
//		btnListar.setBounds(417, 271, 89, 23);
//		btnListar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}});
//		curso.add(btnListar);
		
		JPanel notas_faltas = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, notas_faltas, null);
		notas_faltas.setLayout(null);
		
		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRgm.setBounds(10, 41, 40, 25);
		notas_faltas.add(lblRgm);
		
		JLabel lblNewLabel_1_5 = new JLabel("Disciplina");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(10, 114, 85, 25);
		notas_faltas.add(lblNewLabel_1_5);
		
		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSemestre.setBounds(10, 154, 84, 25);
		notas_faltas.add(lblSemestre);
		
		JLabel lblNotas = new JLabel("Nota");
		lblNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNotas.setBounds(198, 154, 42, 25);
		notas_faltas.add(lblNotas);
		
		JLabel lblNewLabel_1_7 = new JLabel("Faltas");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_7.setBounds(357, 154, 53, 25);
		notas_faltas.add(lblNewLabel_1_7);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1.setBounds(186, 41, 52, 25);
		notas_faltas.add(lblNome_1);
		
		JLabel lblNewLabel_1_6 = new JLabel("Curso");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_6.setBounds(10, 75, 50, 25);
		notas_faltas.add(lblNewLabel_1_6);
		
		JComboBox comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setBounds(101, 159, 30, 22);
		notas_faltas.add(comboBoxSemestre);
		
		JComboBox comboBoxNota = new JComboBox();
		comboBoxNota.setBounds(258, 159, 30, 22);
		notas_faltas.add(comboBoxNota);
		
		JComboBox comboBoxDisciplina = new JComboBox();
		comboBoxDisciplina.setBounds(101, 119, 30, 22);
		notas_faltas.add(comboBoxDisciplina);
		
		txtRgmNotas = new JTextField();
		txtRgmNotas.setBounds(69, 47, 86, 20);
		notas_faltas.add(txtRgmNotas);
		txtRgmNotas.setColumns(10);
		
		txtCursoNotas = new JTextField();
		txtCursoNotas.setBounds(69, 83, 86, 20);
		notas_faltas.add(txtCursoNotas);
		txtCursoNotas.setColumns(10);
		
		txtNomeNotas = new JTextField();
		txtNomeNotas.setBounds(252, 47, 86, 20);
		notas_faltas.add(txtNomeNotas);
		txtNomeNotas.setColumns(10);
		
		JButton btnSalvar_1_5 = new JButton("Salvar");
		btnSalvar_1_5.setBounds(66, 282, 89, 23);
		notas_faltas.add(btnSalvar_1_5);
		
		JButton btnSalvar_1_1_1 = new JButton("Consultar");
		btnSalvar_1_1_1.setBounds(162, 282, 89, 23);
		notas_faltas.add(btnSalvar_1_1_1);
		
		JButton btnSalvar_1_2_1 = new JButton("Alterar");
		btnSalvar_1_2_1.setBounds(261, 282, 89, 23);
		notas_faltas.add(btnSalvar_1_2_1);
		
		JButton btnSalvar_1_3_1 = new JButton("Excluir");
		btnSalvar_1_3_1.setBounds(360, 282, 89, 23);
		notas_faltas.add(btnSalvar_1_3_1);
		
		JButton btnSalvar_1_4_1 = new JButton("Listar");
		btnSalvar_1_4_1.setBounds(459, 282, 89, 23);
		notas_faltas.add(btnSalvar_1_4_1);
		
		txtFaltasNotas = new JTextField();
		txtFaltasNotas.setColumns(10);
		txtFaltasNotas.setBounds(420, 160, 86, 20);
		notas_faltas.add(txtFaltasNotas);
		
		JPanel boletim = new JPanel();
		tabbedPane.addTab("Boletim", null, boletim, null);
		boletim.setLayout(null);
		
		JLabel lblRgm_1 = new JLabel("RGM");
		lblRgm_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRgm_1.setBounds(10, 44, 40, 25);
		boletim.add(lblRgm_1);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Semestre");
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_5_1.setBounds(10, 117, 84, 25);
		boletim.add(lblNewLabel_1_5_1);
		
		JLabel lblNotas_2 = new JLabel("Notas");
		lblNotas_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNotas_2.setBounds(186, 117, 51, 25);
		boletim.add(lblNotas_2);
		
		JLabel lblNewLabel_1_7_1 = new JLabel("Faltas");
		lblNewLabel_1_7_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_7_1.setBounds(358, 117, 53, 25);
		boletim.add(lblNewLabel_1_7_1);
		
		JLabel lblNome_1_1 = new JLabel("Nome");
		lblNome_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome_1_1.setBounds(186, 44, 52, 25);
		boletim.add(lblNome_1_1);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Curso");
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_6_1.setBounds(10, 78, 50, 25);
		boletim.add(lblNewLabel_1_6_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Disciplina", "Notas", "Faltas"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Diciplina", "Faltas", "Notas"
			}
		));
		table.setBounds(20, 164, 468, 166);
		boletim.add(table);
		
		JComboBox comboBoxCursosFeitos = new JComboBox();
		comboBoxCursosFeitos.setBounds(70, 80, 214, 22);
		boletim.add(comboBoxCursosFeitos);
		
		JComboBox comboBoxSemestreFeito = new JComboBox();
		comboBoxSemestreFeito.setBounds(104, 117, 46, 22);
		boletim.add(comboBoxSemestreFeito);
		
		txtRgmBoletim = new JTextField();
		txtRgmBoletim.setBounds(64, 50, 86, 20);
		boletim.add(txtRgmBoletim);
		txtRgmBoletim.setColumns(10);
		
		txtNomeBoletim = new JTextField();
		txtNomeBoletim.setBounds(237, 41, 86, 20);
		boletim.add(txtNomeBoletim);
		txtNomeBoletim.setColumns(10);
		
		txtNotaBoletim = new JTextField();
		txtNotaBoletim.setColumns(10);
		txtNotaBoletim.setBounds(247, 123, 86, 20);
		boletim.add(txtNotaBoletim);
		
		txtFaltaBoletim = new JTextField();
		txtFaltaBoletim.setColumns(10);
		txtFaltaBoletim.setBounds(421, 123, 86, 20);
		boletim.add(txtFaltaBoletim);
	}
}
