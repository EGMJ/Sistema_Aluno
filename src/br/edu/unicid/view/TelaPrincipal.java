package br.edu.unicid.view;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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
	private JButton btnSair;

	private JButton btnSalvarNotas;
	private JButton btnExcluirNotas;
	private JButton btnAlterarNotas;
	private JButton btnConsultarNotas;
	private JButton btnSairNotas;

	private JComboBox ComboBoxUf;

	private MaskFormatter dataMask, celularMask, cpfMask;

	private JMenuBar menuBar;

	private JMenu mnAluno;
	private JMenuItem mntmSalvar;
	private JMenuItem mntmAlterar;
	private JMenuItem mntmConsultar;
	private JMenuItem mntmExcluir;
	private JMenuItem mntmSair;

	private JMenu mnNotasEFaltas;
	private JMenuItem mntmSalvarNotas;
	private JMenuItem mntmAlterarNotas;
	private JMenuItem mntmExcluirNotas;
	private JMenuItem mntmConsultarNotas;

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
	private JComboBox comboBoxDisciplina;
	private JComboBox comboBoxSemestre;
	private JComboBox comboBoxNota;

	private JComboBox comboBoxCursoNotas;

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

	private boolean contemNumeros(String texto) {
		for (char c : texto.toCharArray()) {
			if (Character.isDigit(c)) {
				return true; // Retorna true se encontrar algum número no texto
			}
		}
		return false; // Retorna false se não encontrar números no texto
	}

	public TelaPrincipal() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 478);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnAluno = new JMenu("Aluno");
		menuBar.add(mnAluno);

		mntmSalvar = new JMenuItem("Salvar");

		mntmSalvar.setMnemonic(KeyEvent.CTRL_DOWN_MASK);
		mntmSalvar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					// inicializando o aluno
					Aluno aluno = new Aluno();

					// setando o valor dos atributos e testando
					if (txtRgm.getText().length() != 8) {
						JOptionPane.showMessageDialog(null, "O RGM deve ter 8 dígitos.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return; // Encerra o método caso o RGM exceda o limite de 8 dígitos
					} else {
						aluno.setRgm(Integer.parseInt(txtRgm.getText().trim()));
					}

					if (contemNumeros(txtNome.getText())) {
						JOptionPane.showMessageDialog(null, "O campo nome não pode conter números.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return; // Encerra o método caso o campo nome contenha números
					} else {
						aluno.setNome(txtNome.getText());
					}
					if (contemNumeros(txtMunicipio.getText())) {
						JOptionPane.showMessageDialog(null, "O campo nome não pode conter números.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return; // Encerra o método caso o campo municipio contenha números
					} else {
						aluno.setMunicipio(txtMunicipio.getText());
					}

					aluno.setEmail(txtEmail.getText().trim());
					aluno.setDtaNascimento(fmtdData.getText().trim());
					aluno.setCpf(fmtdCpf.getText().trim());
					aluno.setCelular(fmtdCelular.getText().trim());
					aluno.setUf(ComboBoxUf.getSelectedItem().toString());

					aluno.setEndereco(txtEndereco.getText());
					aluno.setCurso(comboBoxCurso.getSelectedItem().toString());
					aluno.setCampus(comboBoxCampus.getSelectedItem().toString());
					aluno.setPeriodo(radioGrupo.getSelection().getActionCommand());

					// abrir o BD
					AlunoDAO dao = new AlunoDAO();
					// salvar
					dao.salvar(aluno);

					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "O RGM deve ser um valor inteiro.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		});
		mnAluno.add(mntmSalvar);

		mntmAlterar = new JMenuItem("Alterar");
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

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
					dao.atualizar(aluno);

					JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		});
		mnAluno.add(mntmAlterar);

		mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					// abrir o banco de dados
					AlunoDAO dao = new AlunoDAO();

					// criando a variavel rgm
					int rgm = Integer.parseInt(txtRgm.getText());

					// consultando um aluno
					Aluno aluno = dao.procurarAluno(rgm);

					// populando na tela
					txtNome.setText(aluno.getNome());
					txtEmail.setText(aluno.getEmail());
					fmtdData.setText(aluno.getDtaNascimento());
					fmtdCpf.setText(aluno.getCpf());
					fmtdCelular.setText(aluno.getCelular());
					txtMunicipio.setText(aluno.getMunicipio());
					txtEndereco.setText(aluno.getEndereco());
					ComboBoxUf.setSelectedItem(aluno.getUf());
					comboBoxCurso.setSelectedItem(aluno.getCurso());
					comboBoxCampus.setSelectedItem(aluno.getCampus());

					String opcaoSelecionada = aluno.getPeriodo();

					if (opcaoSelecionada.equals("Matutino")) {
						rdbtnMatutino.setSelected(true);
					} else if (opcaoSelecionada.equals("Vespertino")) {
						rdbtnVespertino.setSelected(true);
					} else if (opcaoSelecionada.equals("Noturno")) {
						rdbtnNoturno.setSelected(true);
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "O RGM deve ser um valor inteiro e não pode ser nulo.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao consultar");
				}

			}
		});

		mnAluno.add(mntmConsultar);

		mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// excluir aluno
				Aluno aluno = new Aluno();
				// popular o meu projeto
				aluno.setRgm(Integer.parseInt(txtRgm.getText()));
				try {
					AlunoDAO dao = new AlunoDAO();
					// excluir aluno
					dao.excluir(aluno);
					JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		mnAluno.add(mntmExcluir);

		mntmSair = new JMenuItem("Sair");
		mntmSair.setMnemonic(KeyEvent.SHIFT_DOWN_MASK);
		mntmSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R,
				java.awt.event.InputEvent.SHIFT_DOWN_MASK));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		mnAluno.add(mntmSair);

		mnNotasEFaltas = new JMenu("Notas e Faltas");
		menuBar.add(mnNotasEFaltas);

		mntmSalvarNotas = new JMenuItem("Salvar");
		mntmSalvarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				// inicializando o aluno
				Boletim boletim = new Boletim();

				boletim.setRgm(Integer.parseInt(txtRgmNotas.getText()));
				boletim.setDisciplina(comboBoxDisciplina.getSelectedItem().toString());
				boletim.setNota(Double.parseDouble(comboBoxNota.getSelectedItem().toString()));
				boletim.setFaltas(Integer.parseInt(txtFaltaBoletim.getText()));
				boletim.setSemestre(comboBoxSemestre.getSelectedItem().toString());

				try {
					// abrir o BD
					BoletimDAO dao = new BoletimDAO();
					// salvar
					dao.salvar(boletim);

					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		});
		mnNotasEFaltas.add(mntmSalvarNotas);

		mntmAlterarNotas = new JMenuItem("Alterar");
		mntmAlterarNotas.setMnemonic(KeyEvent.CTRL_DOWN_MASK);
		mntmAlterarNotas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A,
				java.awt.event.InputEvent.CTRL_DOWN_MASK));
		mntmAlterarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("teste de alterar as notas");
			}
		});
		mnNotasEFaltas.add(mntmAlterarNotas);

		mntmExcluirNotas = new JMenuItem("Excluir");
		mntmExcluirNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// excluir boletim
				Boletim boletim = new Boletim();
				// popular o meu projeto
				boletim.setRgm(Integer.parseInt(txtRgm.getText()));
				try {
					BoletimDAO dao = new BoletimDAO();
					// excluir boletim
					dao.excluir(boletim);
					JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		mnNotasEFaltas.add(mntmExcluirNotas);

		mntmConsultarNotas = new JMenuItem("Consultar");
		mntmConsultarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("teste de consultar as notas");

				// rdbtnNewRadioButton_2.getText();
			}
		});
		mnNotasEFaltas.add(mntmConsultarNotas);

		// Menu ajuda
		mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("");
			}
		});
		mnAjuda.add(mntmSobre);

		// Fim do menu
		contentPane = new JPanel();
		contentPane.setBackground(new Color(14, 14, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(248, 248, 242));
		tabbedPane.setBackground(new Color(30, 30, 30));
		tabbedPane.setBounds(10, 11, 763, 395);
		contentPane.add(tabbedPane);

		// Formatação dos campos
		try {
			celularMask = new MaskFormatter("(##)#####-####");
			dataMask = new MaskFormatter("##/##/####");
			cpfMask = new MaskFormatter("###.###.###-##");
		} catch (ParseException e) {
			System.out.println("Erro com as mascaras");
			e.printStackTrace();
		}

		dados_pessoais = new JPanel();
		dados_pessoais.setBackground(new Color(40, 41, 53));
		tabbedPane.addTab("Dados Pessoais", null, dados_pessoais, null);
		dados_pessoais.setLayout(null);

		JLabel lblRgmDadosPessoais = new JLabel("RGM");
		lblRgmDadosPessoais.setForeground(new Color(248, 248, 242));
		lblRgmDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRgmDadosPessoais.setBounds(12, 35, 53, 25);
		dados_pessoais.add(lblRgmDadosPessoais);

		JLabel lblDataNasDadosPessoais = new JLabel("Data de Nascimento");
		lblDataNasDadosPessoais.setForeground(new Color(248, 248, 242));
		lblDataNasDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDataNasDadosPessoais.setBounds(12, 107, 217, 25);
		dados_pessoais.add(lblDataNasDadosPessoais);

		JLabel lblEmailDadosPessoais = new JLabel("Email");
		lblEmailDadosPessoais.setForeground(new Color(248, 248, 242));
		lblEmailDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmailDadosPessoais.setBounds(12, 185, 99, 25);
		dados_pessoais.add(lblEmailDadosPessoais);

		JLabel lblEnderecoDadosPessoais = new JLabel("Endereço");
		lblEnderecoDadosPessoais.setForeground(new Color(248, 248, 242));
		lblEnderecoDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEnderecoDadosPessoais.setBounds(12, 254, 101, 25);
		dados_pessoais.add(lblEnderecoDadosPessoais);

		JLabel lblMunicipioDadosPessoais = new JLabel("Municipio");
		lblMunicipioDadosPessoais.setForeground(new Color(248, 248, 242));
		lblMunicipioDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMunicipioDadosPessoais.setBounds(12, 321, 120, 25);
		dados_pessoais.add(lblMunicipioDadosPessoais);

		JLabel lblUfDadosPessoais = new JLabel("UF");
		lblUfDadosPessoais.setForeground(new Color(248, 248, 242));
		lblUfDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUfDadosPessoais.setBounds(303, 316, 41, 34);
		dados_pessoais.add(lblUfDadosPessoais);

		JLabel lblNomeDadosPessoais = new JLabel("Nome");
		lblNomeDadosPessoais.setForeground(new Color(248, 248, 242));
		lblNomeDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNomeDadosPessoais.setBounds(219, 35, 81, 25);
		dados_pessoais.add(lblNomeDadosPessoais);

		JLabel lblCpfDadosPessoais = new JLabel("CPF");
		lblCpfDadosPessoais.setForeground(new Color(248, 248, 242));
		lblCpfDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCpfDadosPessoais.setBounds(550, 107, 59, 25);
		dados_pessoais.add(lblCpfDadosPessoais);

		JLabel lblCelularDadosPessoais = new JLabel("Celular");
		lblCelularDadosPessoais.setForeground(new Color(248, 248, 242));
		lblCelularDadosPessoais.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCelularDadosPessoais.setBounds(448, 321, 82, 25);
		dados_pessoais.add(lblCelularDadosPessoais);

		ComboBoxUf = new JComboBox();
		ComboBoxUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		ComboBoxUf.setBounds(350, 316, 54, 40);
		dados_pessoais.add(ComboBoxUf);

		txtRgm = new JTextField();
		txtRgm.setForeground(new Color(14, 14, 14));
		txtRgm.setBackground(new Color(246, 245, 244));
		txtRgm.setBounds(81, 31, 120, 40);
		dados_pessoais.add(txtRgm);
		txtRgm.setColumns(10);

		txtNome = new JTextField();
		txtNome.setForeground(new Color(14, 14, 14));
		txtNome.setBackground(new Color(246, 245, 244));
		txtNome.setBounds(303, 31, 407, 40);
		dados_pessoais.add(txtNome);
		txtNome.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(14, 14, 14));
		txtEmail.setBackground(new Color(246, 245, 244));
		txtEmail.setBounds(129, 170, 581, 40);
		dados_pessoais.add(txtEmail);
		txtEmail.setColumns(10);

		txtEndereco = new JTextField();
		txtEndereco.setForeground(new Color(14, 14, 14));
		txtEndereco.setBackground(new Color(246, 245, 244));
		txtEndereco.setBounds(131, 249, 579, 40);
		dados_pessoais.add(txtEndereco);
		txtEndereco.setColumns(10);

		txtMunicipio = new JTextField();
		txtMunicipio.setForeground(new Color(14, 14, 14));
		txtMunicipio.setBackground(new Color(246, 245, 244));
		txtMunicipio.setBounds(131, 316, 157, 40);
		dados_pessoais.add(txtMunicipio);
		txtMunicipio.setColumns(10);

		fmtdData = new JFormattedTextField(dataMask);
		fmtdData.setForeground(new Color(14, 14, 14));
		fmtdData.setBackground(new Color(246, 245, 244));
		fmtdData.setBounds(247, 102, 81, 40);
		dados_pessoais.add(fmtdData);

		fmtdCelular = new JFormattedTextField(celularMask);
		fmtdCelular.setForeground(new Color(14, 14, 14));
		fmtdCelular.setBackground(new Color(246, 245, 244));
		fmtdCelular.setBounds(530, 316, 180, 40);
		dados_pessoais.add(fmtdCelular);

		fmtdCpf = new JFormattedTextField(cpfMask);
		fmtdCpf.setForeground(new Color(14, 14, 14));
		fmtdCpf.setBackground(new Color(246, 245, 244));
		fmtdCpf.setBounds(609, 102, 101, 40);
		dados_pessoais.add(fmtdCpf);

		curso = new JPanel();
		curso.setBackground(new Color(40, 41, 54));
		tabbedPane.addTab("Curso", null, curso, null);
		curso.setLayout(null);

		lblCurso = new JLabel("Curso");
		lblCurso.setForeground(new Color(248, 248, 242));
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurso.setBounds(31, 44, 82, 25);
		curso.add(lblCurso);

		lblCampus = new JLabel("Campus");
		lblCampus.setForeground(new Color(248, 248, 242));
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCampus.setBounds(31, 98, 103, 25);
		curso.add(lblCampus);

		lblPeriodo = new JLabel("Período");
		lblPeriodo.setForeground(new Color(248, 248, 242));
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPeriodo.setBounds(31, 170, 103, 25);
		curso.add(lblPeriodo);

		// Grupo de botoes do tipo radio.
		rdbtnMatutino = new JRadioButton("Matutino");
		rdbtnMatutino.setForeground(new Color(255, 255, 255));
		rdbtnMatutino.setBackground(new Color(40, 41, 54));
		rdbtnMatutino.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnMatutino.setActionCommand("Matutino");
		rdbtnMatutino.setBounds(152, 170, 118, 33);
		curso.add(rdbtnMatutino);

		rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.setForeground(new Color(255, 255, 255));
		rdbtnVespertino.setBackground(new Color(40, 41, 54));
		rdbtnVespertino.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnVespertino.setBounds(375, 170, 133, 33);
		rdbtnVespertino.setActionCommand("Vespertino");
		curso.add(rdbtnVespertino);

		rdbtnNoturno = new JRadioButton("Noturno");
		rdbtnNoturno.setForeground(new Color(255, 255, 255));
		rdbtnNoturno.setBackground(new Color(40, 41, 54));
		rdbtnNoturno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnNoturno.setActionCommand("Noturno");
		rdbtnNoturno.setBounds(624, 170, 118, 33);
		curso.add(rdbtnNoturno);

		radioGrupo = new ButtonGroup();
		radioGrupo.add(rdbtnMatutino);
		radioGrupo.add(rdbtnVespertino);
		radioGrupo.add(rdbtnNoturno);

		comboBoxCurso = new JComboBox();
		comboBoxCurso.setModel(new DefaultComboBoxModel(new String[] { "Selecione o curso", "Ciência da Computação",
				"Análise e Desenvolvimento de Sistemas", "Engenharia Mecânica", "Engenharia Mecatrônica" }));
		comboBoxCurso.setBounds(152, 38, 596, 40);
		curso.add(comboBoxCurso);

		comboBoxCampus = new JComboBox();
		comboBoxCampus.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione o Campus", "Pinheiros", "Tatuapé", "Villa-Lobos", "Paulista" }));
		comboBoxCampus.setBounds(152, 92, 596, 40);
		curso.add(comboBoxCampus);

		btnSalvar = new JButton("");
		btnSalvar.setBackground(new Color(246, 245, 244));
		// ImageIcon save = new ImageIcon(this.getClass().getResource("/save.png"));
		btnSalvar.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/save.png"));
		btnSalvar.setBounds(31, 285, 80, 80);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// inicializando o aluno
					Aluno aluno = new Aluno();

					// setando o valor dos atributos e testando
					if (txtRgm.getText().length() != 8) {
						JOptionPane.showMessageDialog(null, "O RGM deve ser um valor valido.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return; // Encerra o método caso o RGM exceda o limite de 8 dígitos
					} else {
						aluno.setRgm(Integer.parseInt(txtRgm.getText().trim()));
					}

					if (contemNumeros(txtNome.getText())) {
						JOptionPane.showMessageDialog(null, "O campo nome não pode conter números.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return; // Encerra o método caso o campo nome contenha números
					} else {
						aluno.setNome(txtNome.getText());
					}
					if (contemNumeros(txtMunicipio.getText())) {
						JOptionPane.showMessageDialog(null, "O campo nome não pode conter números.", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return; // Encerra o método caso o campo municipio contenha números
					} else {
						aluno.setMunicipio(txtMunicipio.getText());
					}

					aluno.setEmail(txtEmail.getText().trim());
					aluno.setDtaNascimento(fmtdData.getText().trim());
					aluno.setCpf(fmtdCpf.getText().trim());
					aluno.setCelular(fmtdCelular.getText().trim());
					aluno.setUf(ComboBoxUf.getSelectedItem().toString());

					aluno.setEndereco(txtEndereco.getText());
					aluno.setCurso(comboBoxCurso.getSelectedItem().toString());
					aluno.setCampus(comboBoxCampus.getSelectedItem().toString());
					aluno.setPeriodo(radioGrupo.getSelection().getActionCommand());

					// abrir o BD
					AlunoDAO dao = new AlunoDAO();
					// salvar
					dao.salvar(aluno);

					JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "O RGM deve ser um valor valido.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		});
		curso.add(btnSalvar);

		btnConsultar = new JButton("");
		btnConsultar.setBackground(new Color(246, 245, 244));
		// ImageIcon search = new ImageIcon(this.getClass().getResource("/search.png"));
		btnConsultar.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/search.png"));
		btnConsultar.setBounds(190, 285, 80, 80);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// abrir o banco de dados
					AlunoDAO dao = new AlunoDAO();

					// criando a variavel rgm
					int rgm = Integer.parseInt(txtRgm.getText());

					// consultando um aluno
					Aluno aluno = dao.procurarAluno(rgm);

					// populando na tela
					txtNome.setText(aluno.getNome());
					txtEmail.setText(aluno.getEmail());
					fmtdData.setText(aluno.getDtaNascimento());
					fmtdCpf.setText(aluno.getCpf());
					fmtdCelular.setText(aluno.getCelular());
					txtMunicipio.setText(aluno.getMunicipio());
					txtEndereco.setText(aluno.getEndereco());
					ComboBoxUf.setSelectedItem(aluno.getUf());
					comboBoxCurso.setSelectedItem(aluno.getCurso());
					comboBoxCampus.setSelectedItem(aluno.getCampus());

					String opcaoSelecionada = aluno.getPeriodo();

					if (opcaoSelecionada.equals("Matutino")) {
						rdbtnMatutino.setSelected(true);
					} else if (opcaoSelecionada.equals("Vespertino")) {
						rdbtnVespertino.setSelected(true);
					} else if (opcaoSelecionada.equals("Noturno")) {
						rdbtnNoturno.setSelected(true);
					}

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "O RGM deve ser um valor valido.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao consultar");
				}
			}
		});
		curso.add(btnConsultar);

		btnAlterar = new JButton("");
		btnAlterar.setBackground(new Color(246, 245, 244));
		//ImageIcon refresh = new ImageIcon(this.getClass().getResource("/refresh.png"));
		btnAlterar.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/refresh.png"));
		btnAlterar.setBounds(339, 285, 80, 80);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// criando o objeto aluno
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
					dao.atualizar(aluno);

					JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}

		});
		curso.add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setBackground(new Color(246, 245, 244));
		//ImageIcon delete = new ImageIcon(this.getClass().getResource("/delete.png"));
		btnExcluir.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/delete.png"));
		btnExcluir.setBounds(511, 285, 80, 80);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// excluir aluno
				Aluno aluno = new Aluno();
				// popular o meu projeto
				aluno.setRgm(Integer.parseInt(txtRgm.getText()));
				try {
					AlunoDAO dao = new AlunoDAO();
					// excluir aluno
					dao.excluir(aluno);
					JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		curso.add(btnExcluir);

		btnSair = new JButton("");
		btnSair.setBackground(new Color(246, 245, 244));
		//ImageIcon list = new ImageIcon(this.getClass().getResource("/list.png"));
		btnSair.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/list.png"));
		btnSair.setBounds(651, 285, 80, 80);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		curso.add(btnSair);

		JPanel notas_faltas = new JPanel();
		notas_faltas.setForeground(new Color(248, 248, 242));
		notas_faltas.setBackground(new Color(40, 41, 54));
		tabbedPane.addTab("Notas e Faltas", null, notas_faltas, null);
		notas_faltas.setLayout(null);

		JLabel lblRgmNotas = new JLabel("RGM");
		lblRgmNotas.setForeground(new Color(248, 248, 242));
		lblRgmNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRgmNotas.setBounds(10, 41, 101, 25);
		notas_faltas.add(lblRgmNotas);

		JLabel lblDisciplinaNotas = new JLabel("Disciplina");
		lblDisciplinaNotas.setForeground(new Color(248, 248, 242));
		lblDisciplinaNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisciplinaNotas.setBounds(10, 146, 99, 25);
		notas_faltas.add(lblDisciplinaNotas);

		JLabel lblSemestreNotas = new JLabel("Semestre");
		lblSemestreNotas.setForeground(new Color(248, 248, 242));
		lblSemestreNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSemestreNotas.setBounds(12, 193, 101, 25);
		notas_faltas.add(lblSemestreNotas);

		JLabel lblNotas = new JLabel("Nota");
		lblNotas.setForeground(new Color(248, 248, 242));
		lblNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNotas.setBounds(323, 193, 42, 25);
		notas_faltas.add(lblNotas);

		JLabel lbFaltasNotas = new JLabel("Faltas");
		lbFaltasNotas.setForeground(new Color(248, 248, 242));
		lbFaltasNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbFaltasNotas.setBounds(516, 193, 72, 25);
		notas_faltas.add(lbFaltasNotas);

		JLabel lblNomeNotas = new JLabel("Nome");
		lblNomeNotas.setForeground(new Color(248, 248, 242));
		lblNomeNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNomeNotas.setBounds(343, 41, 72, 25);
		notas_faltas.add(lblNomeNotas);

		JLabel lblCursoNotas = new JLabel("Curso");
		lblCursoNotas.setForeground(new Color(248, 248, 242));
		lblCursoNotas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCursoNotas.setBounds(10, 96, 101, 25);
		notas_faltas.add(lblCursoNotas);

		comboBoxCursoNotas = new JComboBox();
		comboBoxCursoNotas
				.setModel(new DefaultComboBoxModel(new String[] { "Selecione o curso", "Ciência da Computação",
						"Análise e Desenvolvimento de Sistemas", "Engenharia Mecânica", "Engenharia Mecatrônica" }));
		comboBoxCursoNotas.setBounds(131, 98, 550, 20);
		notas_faltas.add(comboBoxCursoNotas);

		comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setModel(new DefaultComboBoxModel(new String[] { "Selecionar", "1 Semestre", "2 Semestre",
				"3 Semestre", "4 Semestre", "5 Semestre", "6 Semestre", "7 Semestre", "8 Semestre" }));
		comboBoxSemestre.setBounds(127, 196, 128, 22);
		notas_faltas.add(comboBoxSemestre);

		comboBoxNota = new JComboBox();
		comboBoxNota.setModel(new DefaultComboBoxModel(new String[] { "Selecionar", "0.5", "1", "1.5", "2", "2.5", "3",
				"3.5", "4", "4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10" }));
		comboBoxNota.setBounds(385, 196, 66, 22);
		notas_faltas.add(comboBoxNota);

		comboBoxDisciplina = new JComboBox();
		comboBoxDisciplina.setBounds(127, 149, 554, 22);
		comboBoxDisciplina.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione a Disciplina",
						"Algoritmos e Estruturas de Dados",
						"Arquitetura de Computadores",
						"Banco de Dados", "Compiladores",
						"Inteligência Artificial",
						"Linguagens de Programação",
						"Redes de Computadores",
						"Sistemas Operacionais",
						"Análise de Sistemas", "Banco de Dados",
						"Engenharia de Software",
						"Programação Orientada a Objetos",
						"Sistemas Distribuídos",
						"Testes de Software",
				}));
		notas_faltas.add(comboBoxDisciplina);

		txtRgmNotas = new JTextField();
		txtRgmNotas.setBounds(131, 41, 86, 20);
		notas_faltas.add(txtRgmNotas);
		txtRgmNotas.setColumns(10);

		txtCursoNotas = new JTextField();
		txtCursoNotas.setBounds(131, 98, 550, 20);
		txtCursoNotas.setColumns(10);

		txtNomeNotas = new JTextField();
		txtNomeNotas.setBounds(421, 41, 260, 20);
		notas_faltas.add(txtNomeNotas);
		txtNomeNotas.setColumns(10);

		btnSalvarNotas = new JButton("");
		btnSalvarNotas.setBackground(new Color(248, 248, 242));
		btnSalvarNotas.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/save.png"));

		btnSalvarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// iniciando o boletim
					Aluno aluno = new Aluno();
					AlunoDAO alunoDAO = new AlunoDAO(); 

					Boletim boletim = new Boletim();
					BoletimDAO boletimDAO = new BoletimDAO();

					String rgmaprocurar = txtRgmNotas.getText().trim();
					
					int rgmInt = Integer.parseInt(rgmaprocurar);					;
					
					
					boletim.setRgm(rgmInt);
					boletim.setDisciplina(comboBoxDisciplina.getSelectedItem().toString());
					boletim.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					boletim.setNota(Double.parseDouble(comboBoxNota.getSelectedItem().toString()));
					boletim.setFaltas(Integer.parseInt(txtFaltaBoletim.getText()));
					
					boletimDAO.salvar(boletim);
				

				JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "O RGM deve ser um valor valido.", "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					System.out.println(e1.getMessage());
				}
				

			}
		});
		btnSalvarNotas.setBounds(31, 282, 80, 80);
		notas_faltas.add(btnSalvarNotas);

		btnConsultarNotas = new JButton("");
		btnConsultarNotas.setBackground(new Color(248, 248, 242));
		btnConsultarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// abrir o banco de dados
					// AlunoDAO dao = new AlunoDAO();
					BoletimDAO daoboletim = new BoletimDAO();

					// criando a variavel rgm
					int rgm = Integer.parseInt(txtRgm.getText());
					//
					// consultando um aluno
					// Aluno aluno = dao.procurarAluno(rgm);
					Boletim boletim = daoboletim.procurarBoletim(rgm); // por enquanto

					// populando na tela
					// txtNomeNotas.setText(aluno.getNome());
					// txtCursoNotas.setText(aluno.getCurso());

					// if (txtCursoNotas.getText().equals("Ciência da Computação")) {
					// comboBoxDisciplina.setModel(new DefaultComboBoxModel(
					// new String[] { "Selecione a Disciplina",
					// "Algoritmos e Estruturas de Dados",
					// "Arquitetura de Computadores",
					// "Banco de Dados", "Compiladores",
					// "Inteligência Artificial",
					// "Linguagens de Programação",
					// "Redes de Computadores",
					// "Sistemas Operacionais"
					// }));
					// } else if (txtCursoNotas.getText().equals("Análise e Desenvolvimento de
					// Sistemas")) {
					// comboBoxDisciplina.setModel(new DefaultComboBoxModel(
					// new String[] { "Selecione a Disciplina",
					// "Análise de Sistemas", "Banco de Dados",
					// "Engenharia de Software",
					// "Programação Orientada a Objetos",
					// "Sistemas Distribuídos",
					// "Testes de Software",
					// "Web Design" }));
					// } else if (txtCursoNotas.getText().equals("Engenharia Mecânica")) {
					// comboBoxDisciplina.setModel(new DefaultComboBoxModel(new String[] {
					// "Selecione a Disciplina",
					// "Cálculo Diferencial e Integral", "Dinâmica dos Sólidos", "Fenômenos de
					// Transporte",
					// "Mecânica dos Fluidos", "Mecânica dos Sólidos", "Termodinâmica" }));
					// } else if (txtCursoNotas.getText().equals("Engenharia Mecatrônica")) {
					// comboBoxDisciplina.setModel(new DefaultComboBoxModel(new String[] {
					// "Selecione a Disciplina",
					// "Controle Automático", "Eletrônica Digital", "Eletrônica Analógica",
					// "Mecânica dos Sistemas Mecatrônicos", "Microcontroladores e
					// Microprocessadores",
					// "Sistemas Embarcados", "Sistemas Hidráulicos e Pneumáticos" }));
					// }
					// ;
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Erro ao procurar");
				}

			}
		});
		btnConsultarNotas.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/search.png"));
		btnConsultarNotas.setBounds(208, 282, 80, 80);
		notas_faltas.add(btnConsultarNotas);

		btnAlterarNotas = new JButton("");
		btnAlterarNotas.setBackground(new Color(248, 248, 242));
		btnAlterarNotas.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/refresh.png"));
		btnAlterarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnAlterarNotas.setBounds(371, 282, 80, 80);
		notas_faltas.add(btnAlterarNotas);

		btnExcluirNotas = new JButton("");
		btnExcluirNotas.setBackground(new Color(248, 248, 242));
		btnExcluirNotas.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/delete.png"));
		btnExcluirNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnExcluirNotas.setBounds(511, 282, 80, 80);
		notas_faltas.add(btnExcluirNotas);

		btnSairNotas = new JButton("");
		btnSairNotas.setBackground(new Color(248, 248, 242));
		btnSairNotas.setIcon(new ImageIcon("/home/egmj/Projects/Java_Projects/Sistema_Aluno/img/list.png"));
		btnSairNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnSairNotas.setBounds(651, 282, 80, 80);
		notas_faltas.add(btnSairNotas);

		txtFaltasNotas = new JTextField();
		txtFaltasNotas.setColumns(10);
		txtFaltasNotas.setBounds(595, 195, 86, 20);
		notas_faltas.add(txtFaltasNotas);

		JPanel boletim = new JPanel();
		boletim.setBackground(new Color(40, 41, 54));
		tabbedPane.addTab("Boletim", null, boletim, null);
		boletim.setLayout(null);

		JLabel lblRgmBoletim = new JLabel("RGM");
		lblRgmBoletim.setForeground(new Color(248, 248, 242));
		lblRgmBoletim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRgmBoletim.setBounds(38, 12, 50, 25);
		boletim.add(lblRgmBoletim);

		JLabel lblSemestreBoletim = new JLabel("Semestre");
		lblSemestreBoletim.setForeground(new Color(248, 248, 242));
		lblSemestreBoletim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSemestreBoletim.setBounds(31, 117, 119, 25);
		boletim.add(lblSemestreBoletim);

		JLabel lblNotasBoletim = new JLabel("Notas");
		lblNotasBoletim.setForeground(new Color(248, 248, 242));
		lblNotasBoletim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNotasBoletim.setBounds(322, 117, 78, 25);
		boletim.add(lblNotasBoletim);

		JLabel lblFaltasBoletim = new JLabel("Faltas");
		lblFaltasBoletim.setForeground(new Color(248, 248, 242));
		lblFaltasBoletim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFaltasBoletim.setBounds(548, 117, 84, 25);
		boletim.add(lblFaltasBoletim);

		JLabel lblNomeBoletim = new JLabel("Nome");
		lblNomeBoletim.setForeground(new Color(248, 248, 242));
		lblNomeBoletim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNomeBoletim.setBounds(403, 12, 71, 25);
		boletim.add(lblNomeBoletim);

		JLabel lblCursoBoletim = new JLabel("Curso");
		lblCursoBoletim.setForeground(new Color(248, 248, 242));
		lblCursoBoletim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCursoBoletim.setBounds(38, 61, 84, 25);
		boletim.add(lblCursoBoletim);

		table = new JTable();
		table.setForeground(new Color(248, 248, 242));
		table.setBackground(new Color(40, 41, 54));
		table.setModel(new DefaultTableModel(
				new Object[][] { { "Disciplina", "Notas", "Faltas" }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, },
				new String[] { "Diciplina", "Faltas", "Notas" }));
		table.setBounds(31, 164, 700, 170);
		boletim.add(table);

		JComboBox comboBoxCursosFeitos = new JComboBox();
		comboBoxCursosFeitos.setBounds(106, 64, 214, 22);
		boletim.add(comboBoxCursosFeitos);

		JComboBox comboBoxSemestreFeito = new JComboBox();
		comboBoxSemestreFeito.setBounds(154, 120, 108, 22);
		boletim.add(comboBoxSemestreFeito);

		txtRgmBoletim = new JTextField();
		txtRgmBoletim.setBounds(106, 17, 220, 20);
		boletim.add(txtRgmBoletim);
		txtRgmBoletim.setColumns(10);

		txtNomeBoletim = new JTextField();
		txtNomeBoletim.setBounds(472, 17, 259, 20);
		boletim.add(txtNomeBoletim);
		txtNomeBoletim.setColumns(10);

		txtNotaBoletim = new JTextField();
		txtNotaBoletim.setColumns(10);
		txtNotaBoletim.setBounds(403, 122, 86, 20);
		boletim.add(txtNotaBoletim);

		txtFaltaBoletim = new JTextField();
		txtFaltaBoletim.setColumns(10);
		txtFaltaBoletim.setBounds(630, 122, 86, 20);
		boletim.add(txtFaltaBoletim);
	}
}