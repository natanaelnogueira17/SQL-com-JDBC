package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String user = "jdbc:postgresql://localhost:5433/posjava";
	private static String url = "postgres";
	private static String password = "12345678";
	private static Connection connection = null;

	static {
		conectar();

	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(user, url, password);
				connection.setAutoCommit(false);
				System.out.println("conectou!");
			}
			
		} catch (Exception e) {
			 e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		return connection;
	}
}
