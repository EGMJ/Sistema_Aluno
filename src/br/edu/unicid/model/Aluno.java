package br.edu.unicid.model;

public class Aluno {
	private int rgm;
	private String nome;
	private String email;
	private String dtaNascimento;
	private String cpf;
	private String celular;
	private String uf;
	private String municipio;
	private String endereco;
	private String curso;
	private String campus;
	private String periodo;

	// construtor vazio
	public Aluno() {}

	// construtor com campos
	public Aluno(int rgm, String nome, String email, String dtaNascimento, String cpf, String uf, String celular, String municipio, String endereco, String curso, String campus, String periodo) {
		this.rgm = rgm;
		this.nome = nome;
		this.email = email;
		this.dtaNascimento = dtaNascimento;
		this.cpf = cpf;
		this.celular = celular;
		this.uf = uf;
		this.municipio = municipio;
		this.endereco = endereco;
		this.curso = curso;
		this.campus = campus;
		this.periodo = periodo;
	}

	// getters e setters
	public int getRgm() {
		return rgm;
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
