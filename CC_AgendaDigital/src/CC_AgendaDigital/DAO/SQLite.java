package CC_AgendaDigital.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CC_AgendaDigital.Core.Pessoa;

public class SQLite {
	private static Connection conn;
	private static Statement stm;

	public SQLite(String arquivo) throws ClassNotFoundException, SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + arquivo);
			stm = conn.createStatement();
			System.out.println("test");
		} catch (Exception e) {
			e.getMessage();
		}
		stm = conn.createStatement();
		stm.executeUpdate(
				"CREATE TABLE IF NOT EXISTS Pessoa (PessoaId INTEGER PRIMARY KEY ,Nome VARCHAR(30), DataNasc VARCHAR(10), Idade INTEGER)");
	}

	public static void insertPessoa(Pessoa pessoa) {
		try {
			stm = conn.createStatement();
			stm.executeUpdate("INSERT INTO Pessoa(Nome, DataNasc, Idade) " + "VALUES ('" + pessoa.getNome() + "', '"
					+ pessoa.getDataNascimento() + "', " + pessoa.getIdade() + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Pessoa> getPessoas() {
		ArrayList<Pessoa> auxPessoas = new ArrayList<Pessoa>();
		ResultSet rs;
		try {
			rs = stm.executeQuery("SELECT * FROM Pessoa");
			while (rs.next()) {
				auxPessoas.add(new Pessoa(rs.getString("Nome"), rs.getString("DataNasc"), rs.getInt("Idade")));
			}
			rs.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return auxPessoas;
	}
}
