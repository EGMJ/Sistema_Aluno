package br.edu.unicid.dao;

import java.sql.*;

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
		try{
			String SQL = "INSERT INTO " + "testee (rgm, nome, email, dtaNascimento, cpf, uf, celular, municipio, endereco, curso, campus, periodo) values (?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = conn.prepareStatement(SQL);

			ps.setInt(1, aluno.getRgm());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			ps.setString(4, aluno.getDtaNascimento());
			ps.setString(5, aluno.getCpf());
			ps.setString(6, aluno.getUf());
			ps.setString(7, aluno.getCelular());
			ps.setString(8, aluno.getMunicipio());
			ps.setString(9, aluno.getEndereco());
			ps.setString(10, aluno.getCurso());
			ps.setString(11, aluno.getCampus());
			ps.setString(12, aluno.getPeriodo());
				
			ps.executeUpdate();
		}catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		}finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// método de atualizar
 	public void atualizar(Aluno aluno) throws Exception {
 		if (aluno == null)
 			throw new Exception("O valor passado nao pode ser nulo");
 		try {
 			String SQL = "UPDATE testee set nome=?, email=?, dtaNascimento=?, cpf=?, uf=?, celular=?, municipio=?, endereco=?, curso=?, campus=?, periodo=? " + "WHERE rgm=?";
		
 			ps = conn.prepareStatement(SQL);
 			 			
			ps.setString(1, aluno.getNome());
			ps.setString(2, aluno.getEmail());
			ps.setString(3, aluno.getDtaNascimento());
			ps.setString(4, aluno.getCpf());
			ps.setString(5, aluno.getUf());
			ps.setString(6, aluno.getCelular());
			ps.setString(7, aluno.getMunicipio());
			ps.setString(8, aluno.getEndereco());
			ps.setString(9, aluno.getCurso());
			ps.setString(10, aluno.getCampus());
			ps.setString(11, aluno.getPeriodo());
			ps.setInt(12, aluno.getRgm());
 			
 			ps.executeUpdate();
 		} catch (SQLException sqle) {
 			throw new Exception("Erro ao alterar dados " + sqle);
 		} finally {
 			ConnectionFactory.closeConnection(conn, ps);
 		}
 	}

// 	// método de excluir
 	public void excluir(Aluno aluno) throws Exception {
 		if (aluno == null)
 			throw new Exception("O valor passado nao pode ser nulo");
 		try {
 			String SQL = "DELETE FROM testee WHERE rgm = ?";
 			// conn = this.conn; // sem efeito
 			
 			ps = conn.prepareStatement(SQL);
 			
 			ps.setInt(1, aluno.getRgm());
 			
 			ps.executeUpdate();
 		} catch (SQLException sqle) {
 			throw new Exception("Erro ao excluir dados " + sqle);
 		} finally {
 			ConnectionFactory.closeConnection(conn, ps);
 		}
 	}

// 	// Procurar Aluno
 	public Aluno procurarAluno(int rgm) throws Exception {

 		try {
 			String SQL = "SELECT * FROM testee WHERE rgm=?";
 			// conn = this.conn; // sem efeito
 			ps = conn.prepareStatement(SQL);
 			ps.setInt(1, rgm);			
 			rs = ps.executeQuery();
 	
      	if (rs.next()) {
 				int ca = rs.getInt(1);
 				String nome = rs.getString(2);	
 				String email = rs.getString(3);
 				String nascimento = rs.getString(4);
 				String cpf = rs.getString(5);
 				String uf = rs.getString(6);
 				String celular = rs.getString(7);
 				String municipio = rs.getString(8);
 				String endereco = rs.getString(9);
 				String curso = rs.getString(10);
 				String campus = rs.getString(11);
 				String periodo = rs.getString(12);
					
 				aluno = new Aluno(ca, nome, email, nascimento, cpf, uf, celular, municipio, endereco, curso, campus, periodo);
 			}
 			return aluno;
 		} catch (SQLException sqle) {
 			throw new Exception(sqle);
 		} finally {
 			ConnectionFactory.closeConnection(conn, ps, rs);
 		}
 	}
 	
}