package br.edu.unicid.model;

public class Boletim {
	private int rgm;
	private String disciplina;
	private String semestre;
	private double nota;
	private int faltas;

	// construtor vazio
	public Boletim() {}

	// construtor com campos
	public Boletim(int rgm, String disciplina,  String semestre, double nota, int faltas) {
		this.rgm = rgm;
		this.disciplina = disciplina;
		this.semestre= semestre;
		this.nota = nota;
		this.faltas = faltas;
	}

	public int getRgm() {
		return rgm;
	}

	public void setRgm(int rgm) {
		this.rgm = rgm;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
}