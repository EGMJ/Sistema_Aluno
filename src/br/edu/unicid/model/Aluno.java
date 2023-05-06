package br.edu.unicid.model;

public class Aluno {
	private int rgm;
	private String nome;
	private String email;
	private String dtaNascimento;
	private int idade ;
	private String endereco;
	private String cpf;
	private String municipio;
	private String uf;
	private String telefone;

	// construtor vazio
	public Aluno() {
		// TODO Auto-generated constructor stub
	}
	// construtor com campos
	public Aluno(int rgm, String nome, String dtaNascimento, String cpf, String endereco, String municipio, String uf, String telefone, String email) {
		this.rgm = rgm;
		this.nome = nome;
		this.dtaNascimento = dtaNascimento;
		this.cpf =cpf;
		this.municipio = municipio;
		this.uf = uf;
		this.endereco =endereco;
		this.email = email;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	// getters e setters
	
	public Aluno(int ra, String nome2, String email2, String nascimento, int idade2, String endereco2) {
		// TODO Auto-generated constructor stub
	}
	public int getRgm() {
		return rgm;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public void setRgm(int rgm) {
		this.rgm = rgm;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDtaNascimento() {
		return dtaNascimento;
	}
	public void setDtaNascimento(String dtaNascimento) {
		this.dtaNascimento = dtaNascimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
