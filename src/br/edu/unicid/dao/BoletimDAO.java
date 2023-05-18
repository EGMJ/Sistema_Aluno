package br.edu.unicid.dao;

import java.sql.*;

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
	public void atualizar(Boletim boletim) throws Exception {
		if (boletim == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "UPDATE testeboletim set disciplina=? semestre=?, nota=?, faltas=? WHERE rgm = ?";

			ps = conn.prepareStatement(SQL);
			
			ps.setString(1, boletim.getDisciplina());
			ps.setString(2, boletim.getSemestre());
			ps.setDouble(3, boletim.getNota());
			ps.setInt(4, boletim.getFaltas());
			ps.setInt(5, boletim.getRgm());
			
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao alterar dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// Função para excluir
	public void excluir(Boletim boletim) throws Exception {
		if (boletim == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "DELETE FROM testeboletim WHERE rgm = ?";
			
			ps = conn.prepareStatement(SQL);
			
			ps.setInt(1, boletim.getRgm());
			
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao excluir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
	}

	// Função para procurar notas no boletim
	public Boletim procurarBoletim(int rgm) throws Exception {

		try {
			String SQL = "SELECT  * FROM testeboletim WHERE rgm=?";
			// conn = this.conn; // sem efeito
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, rgm);			
			rs = ps.executeQuery();
			if (rs.next()) {
				int ca = rs.getInt(1);
				String disciplina = rs.getString(2);
				String semestre = rs.getString(3);
				double nota = rs.getDouble(4);
				int faltas = rs.getInt(5);

				boletim = new Boletim(ca, disciplina, semestre, nota, faltas);
			}
			return boletim;
		} catch (SQLException sqle) {
			throw new Exception(sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps, rs);
		}
	}
}
