package br.edu.unicid.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import br.edu.unicid.model.Aluno;
import br.edu.unicid.util.ConnectionFactory;

public class AlunoDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs; 
	private Aluno aluno;

	public AlunoDAO() throws Exception {
		// chama a classe ConnectionFactory e estabele uma conexão
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}

	// método de salvar

	public void salvar(Aluno aluno) throws Exception {
		if (aluno == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO "
					+ "aluno (rgm, "
					+ "nome, dtaNascimento, "
					+ "cpf,endereco) "
					+ "municipio,uf, "
					+"telefone,email"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?,?)";
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, aluno.getRgm());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getDtaNascimento());
			ps.setString(4, aluno.getCpf());
			ps.setString(5, aluno.getEndereco());
			ps.setString(6, aluno.getMunicipio());
			ps.setString(7, aluno.getUf());
			ps.setString(8, aluno.getTelefone());
			ps.setString(9, aluno.getEmail());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// método de atualizar

	public void atualizar(Aluno aluno) throws Exception {
		if (aluno == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "UPDATE aluno set nome=?, "
					+ "dtaNascimento=?, cpf=?"
					+ "endereco=?, municipio=? "
					+ "uf=?, telefone=?"
					+"email=?"
					+ "WHERE rgm = ?";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getDtaNascimento());
			ps.setString(3, aluno.getCpf());
			ps.setString(4, aluno.getEndereco());
			ps.setString(5, aluno.getMunicipio());
			ps.setString(6, aluno.getUf());
			ps.setString(7, aluno.getTelefone());
			ps.setString(8, aluno.getEmail());
			ps.setInt(9, aluno.getRgm());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao alterar dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// método de excluir

	public void excluir(Aluno aluno) throws Exception {
		if (aluno == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "DELETE FROM aluno"
					+ " WHERE rgm = ?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, aluno.getRgm());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// Procurar Aluno

	public Aluno procurarAluno(int rgm) throws Exception {

		try {
			String SQL = "SELECT  * FROM aluno WHERE rgm=?";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, rgm);			
			rs = ps.executeQuery();
			if (rs.next()) {
				int ra = rs.getInt(1);
				String nome = rs.getString(2);
				String nascimento = rs.getString(3);
				String cpf = rs.getString(4);
				String endereco = rs.getString(5);
				String municipio = rs.getString(6);
				String uf = rs.getString(7);
				String telefone = rs.getString(8);
				String email = rs.getString(9);
				aluno = new Aluno(ra, nome, nascimento, cpf, endereco,municipio,uf,telefone,email);
			}
			return aluno;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
// Listar todos os alunos

	public List todosAlunos() throws Exception {
		try {
			conn = this.conn;
			ps = conn.prepareStatement("SELECT * FROM tbAluno");
			rs = ps.executeQuery();
			List<Aluno> list = new ArrayList<Aluno>();
			while (rs.next()) {
				int ca = rs.getInt(1);
				String nome = rs.getString(2);
				String email = rs.getString(3);
				String nascimento = rs.getString(4);
				int idade = rs.getInt(5);
				String endereco = rs.getString(6);
				list.add(new Aluno(ca, nome, email, nascimento, idade, endereco));
			}
			return list;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
}

