package exemploDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ex1.Todo;

public class TodoDAO {

	private Connection connection;

	private final String insertSql = "insert into todo ( id,  descricao, resumo ) values (?, ?, ? )";
	private final String findByIdSql = "select id, descricao, resumo from todo where id = ?";
	private final String updateSql = "update todo set descricao = ?, resumo = ? where id = ?";
	private final String deleteSql = "delete from todo where id = ?";
	private final String listSql = "select id, descricao, resumo from todo order by id desc";

	public TodoDAO(Connection connection) {

		super();
		this.connection = connection;
	}

	public void insert(Todo todo) throws SQLException {

		// presistir
		PreparedStatement stInsert = connection.prepareStatement(insertSql);
		stInsert.setInt(1, todo.getId());
		stInsert.setString(2, todo.getDescricao());
		stInsert.setString(3, todo.getResumo());
		stInsert.execute();
		stInsert.close();
	}

	public Todo findById(int id) throws SQLException {

		// buscar
		PreparedStatement stFind = connection.prepareStatement(findByIdSql);
		stFind.setInt(1, 1);
		ResultSet rs = stFind.executeQuery();
		Todo todo = null;
		if (rs.next()) {
			todo = new Todo(rs.getInt("id"), rs.getString("descricao"), rs.getString("resumo"));
		}
		return todo;
	}

	public void update(Todo todo) throws SQLException {

		// atualizar
		PreparedStatement stUpdate = connection.prepareStatement(updateSql);
		stUpdate.setString(1, todo.getDescricao());
		stUpdate.setString(2, todo.getResumo());
		stUpdate.setInt(3, todo.getId());
		stUpdate.execute();
		stUpdate.close();
	}

	public void delete(Todo todo) throws SQLException {

		// remover o objeto
		PreparedStatement stDelete = connection.prepareStatement(deleteSql);
		stDelete.setInt(1, todo.getId());
		stDelete.execute();
		stDelete.close();
	}

	public List<Todo> list() throws SQLException {

		List<Todo> todos = new ArrayList<Todo>();

		PreparedStatement stList = connection.prepareStatement(listSql);
		ResultSet rs = stList.executeQuery();

		while (rs.next()) {
			// Mapeamento 'Objeto Relacional'
			Todo todo = new Todo();
			todo.setId(rs.getInt("id"));
			todo.setDescricao(rs.getString("descricao"));
			todo.setResumo(rs.getString("resumo"));
			todos.add(todo);
		}
		return todos;
	}
}
