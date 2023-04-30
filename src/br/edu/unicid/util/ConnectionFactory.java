package br.edu.unicid.util;

import java.sql.*;

import javax.swing.JOptionPane;

	public class ConnectionFactory {
			// private static Connection connection;

		public static Connection getConnection() throws Exception { 
			try {

				// indica qual é o banco de dados que estou utilizando e seu driver
				Class.forName("com.mysql.jdbc.Driver");

				// estabelece a conexao e retorna uma conexao
				String url = "jdbc:mysql://localhost:3306/dbaluno?characterEncoding=utf8";
				String login = "root";
				String senha = "";		
				
				System.out.println("Está conectando");

				return DriverManager.getConnection(url,login,senha);
			} catch (Exception e) {
				System.out.println("Esta com erro");
				throw new Exception(e.getMessage());
			}
		}
		
		//testando a coneccao com o db
		
		// public static void main(String[] args) {
		// 	try {
		// 		Connection connection = (Connection) ConnectionFactory.getConnection();
		// 		JOptionPane.showMessageDialog(null, "Banco conectado com sucesso");
					
		// 	} catch (Exception e) {
		// 		e.printStackTrace();
		// 	}			
		// }

		// fecha uma conexão de três formas: conn, stmt, rs
		public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
			close(conn, stmt, rs);
		}

		public static void closeConnection(Connection conn, Statement stmt) throws Exception {
			close(conn, stmt, null);
		}

		public static void closeConnection(Connection conn) throws Exception {
			close(conn, null, null);
		}

		private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}

