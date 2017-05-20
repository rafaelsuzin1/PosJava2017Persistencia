package exemploDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import ex1.Todo;
import exemplo1.factory.ConnectionFactory;

/**
 * 
 * Ao termino exporte o projeto e coloque seu nome no arquivo.
 * envie para
 * 
 *    emerson.morgado@gmail.com
 *
 */
public class DAOApplication {

    public static void main( String[] args ) throws SQLException {

        Connection connection = ConnectionFactory.getConnection( ConnectionFactory.MYSQL );
        
        /**
         * Deixando o delete aqui somente por conveniencia, não é banana.
         */
        System.out.println("Remove tabela");
        Statement stDelete = connection.createStatement();
        stDelete.execute( "drop table todo" );
        stDelete.close();
        
        System.out.println("Cria tabela");
        Statement stCreate = connection.createStatement();
        // sacrificou o 'auto_increment' no mysql
        // que no postgres é 'serial'
        stCreate.execute( "create table todo ( id integer primary key, descricao varchar(255) , resumo varchar(255) )" );
        stCreate.close();
        
        /**
         * Usando o Dao a partir daqui
         */
        TodoDAO todoDao = new TodoDAO( connection );
        
        Todo todo1 = new Todo(1, "Todo dao 1", "Esta tarefa foi criada com um dao");
        Todo todo2 = new Todo(2, "Todo dao 2", "Esta tarefa foi criada com um dao 2");
        Todo todo3 = new Todo(3, "Todo dao 3", "Esta tarefa foi criada com um dao 3");
        
        todoDao.insert( todo1 );
        todoDao.insert( todo2 );
        todoDao.insert( todo3 );
        
        System.out.println("Busca: "+ todoDao.findById( 1 ));
        
        todo1.setDescricao( "Descrição editada" );        
        todoDao.update( todo1 );
        
        System.out.println( " Todos " + todoDao.list() );
        
        System.out.println( " Todos " + todoDao.list().size() );
        
        todoDao.delete( todo2 );
        
        System.out.println( " Todos " + todoDao.list().size() );
    }
    
}
