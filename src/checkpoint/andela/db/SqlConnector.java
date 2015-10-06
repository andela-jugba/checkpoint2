package checkpoint.andela.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import checkpoint.andela.parser.Reactant;

public class SqlConnector {

	static String DATABASE_URL = Constants.DATABASEURL;

	private Connection connect;
	private PreparedStatement preparedStatement = null;
	private Statement statement;

	public SqlConnector(){
		connect = null;
		preparedStatement = null;
		statement = null;
		try {
			connect = DriverManager.getConnection(DATABASE_URL, Constants.USERNAME, Constants.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void writeReact(Reactant reaction) throws SQLException {
		preparedStatement = connect
				.prepareStatement("insert into  reactiondb.reactions values (?, ?, ?, ?, ? , ?, ?, ?)");
		preparedStatement.setString(1, reaction.get("UNIQUE-ID"));
		preparedStatement.setString(2, reaction.get("TYPES"));
		preparedStatement.setString(3, reaction.get("ATOM-MAPPINGS"));
		preparedStatement.setString(4, reaction.get("CREDITS"));
		preparedStatement.setString(5, reaction.get("EC-NUMBER"));
		preparedStatement.setString(6, reaction.get("IN-PATHWAY"));
		preparedStatement.setString(7, reaction.get("ORPHAN?"));
		preparedStatement.setString(8, reaction.get("LEFT"));

		preparedStatement.executeUpdate();
		preparedStatement.close();

	}

	public void trucateTable() throws SQLException {
		statement = connect.createStatement();
		String sql = "TRUNCATE reactions";
		statement.executeUpdate(sql);

	}

}
