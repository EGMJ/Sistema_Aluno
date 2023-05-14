package br.edu.unicid.dao;

import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
import br.edu.unicid.model.Boletim;
import br.edu.unicid.util.ConnectionFactory;

public class BoletimDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs; 
	/**
	 *
	 */
	private Boletim boletim;

	public BoletimDAO() throws Exception {
		// chama a classe ConnectionFactory e estabele uma conexão
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}

	// método de salvar
	public void salvar(Boletim boletim) throws Exception {
		if (boletim == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try{
			String SQL = "INSERT INTO " + "testeboletim (rgm, disciplina, semestre, nota, faltas) values (?,?,?,?,?)";

			ps = conn.prepareStatement(SQL);

			ps.setInt(1, boletim.getRgm());
			ps.setString(2, boletim.getDisciplina());
			ps.setString(3, boletim.getSemestre());
			ps.setDouble(4, boletim.getNota());
			ps.setInt(5, boletim.getFaltas());
				
			ps.executeUpdate();
		}catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// método de atualizar
// 	public void atualizar(Boletim boletim) throws Exception {
// 		if (boletim == null)
// 			throw new Exception("O valor passado nao pode ser nulo");
// 		try {
// 			String SQL = "UPDATE tb_notas_faltas set disciplina=?, "
// 					+ "semestre=?, nota=?, "
// 					+ "faltas=?"
// 					+ "WHERE rgm = ?";
// 			ps = conn.prepareStatement(SQL);
// 			ps.setString(1, aluno.getDisciplina());
// 			ps.setString(2, aluno.getsemestre());
// 			ps.setString(3, aluno.getnota());
// 			ps.setInt(4, aluno.getfaltas());
// 			ps.setInt(5, aluno.getRgm());
// 			ps.executeUpdate();
// 		} catch (SQLException sqle) {
// 			throw new Exception("Erro ao alterar dados " + sqle);
// 		} finally {
// 			ConnectionFactory.closeConnection(conn, ps);
// 		}
// 	}

// 	// método de excluir
// 	public void excluir(Aluno aluno) throws Exception {
// 		if (aluno == null)
// 			throw new Exception("O valor passado nao pode ser nulo");
// 		try {
// 			String SQL = "DELETE FROM aluno"
// 					+ " WHERE rgm = ?";
// 			conn = this.conn;
// 			ps = conn.prepareStatement(SQL);
// 			ps.setInt(1, aluno.getRgm());
// 			ps.executeUpdate();
// 		} catch (SQLException sqle) {
// 			throw new Exception("Erro ao excluir dados " + sqle);
// 		} finally {
// 			ConnectionFactory.closeConnection(conn, ps);
// 		}
// 	}

// 	// Procurar Aluno

// 	public Aluno procurarAluno(int rgm) throws Exception {

// 		try {
// 			String SQL = "SELECT  * FROM tbAluno WHERE caAluno=?";
// 			conn = this.conn;
// 			ps = conn.prepareStatement(SQL);
// 			ps.setInt(1, rgm);			
// 			rs = ps.executeQuery();
// 			if (rs.next()) {
// 				int ca = rs.getInt(1);
// 				String nome = rs.getString(2);
// 				String email = rs.getString(3);
// 				String nascimento = rs.getString(4);
// 				int idade = rs.getInt(5);
// 				String endereco = rs.getString(6);
// 				aluno = new Aluno(ca, nome, email, nascimento, idade, endereco);
// 			}
// 			return aluno;
// 		} catch (SQLException sqle) {
// 			throw new Exception(sqle);
// 		} finally {
// 			ConnectionFactory.closeConnection(conn, ps, rs);
// 		}
// 	}
// // Listar todos os alunos

// 	public List todosAlunos() throws Exception {
// 		try {
// 			conn = this.conn;
// 			ps = conn.prepareStatement("SELECT * FROM tbAluno");
// 			rs = ps.executeQuery();
// 			List<Aluno> list = new ArrayList<Aluno>();
// 			while (rs.next()) {
// 				int ca = rs.getInt(1);
// 				String nome = rs.getString(2);
// 				String email = rs.getString(3);
// 				String nascimento = rs.getString(4);
// 				int idade = rs.getInt(5);
// 				String endereco = rs.getString(6);
// 				list.add(new Aluno(ca, nome, email, nascimento, idade, endereco));
// 			}
// 			return list;
// 		} catch (SQLException sqle) {
// 			throw new Exception(sqle);
// 		} finally {
// 			ConnectionFactory.closeConnection(conn, ps, rs);
// 		}
// 	}
}


