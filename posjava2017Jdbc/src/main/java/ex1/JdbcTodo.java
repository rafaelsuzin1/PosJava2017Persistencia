package ex1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo1.factory.ConnectionFactory;

public class JdbcTodo {

    /**
     * Problema ocorrido com reuso de statement
     * 
     * @param args
     */
    public static void main( String[] args ) {

        System.out.println( "Incio" );

        
        try {

            // Obter uma conexão
            Connection connection = ConnectionFactory.getConnection( ConnectionFactory.POSTGRES );

            System.out.println("Remove tabela");
            Statement stDelete = connection.createStatement();
            stDelete.execute( "drop table todo" );
            stDelete.close();
            
            /**
             * criar uma tabela TODO onde iremos colocar uma lista de tarefas Campos: id descricao resumo
             */
            System.out.println("Cria tabela");
            Statement stCreate = connection.createStatement();
            // sacrificou o 'auto_increment' no mysql
            // que no postgres é 'serial'
            stCreate.execute( "create table todo ( id integer primary key, descricao varchar(30) , resumo varchar(30) )" );
            stCreate.close();

            
            // Adicionar 10 itens a lista
            System.out.println("Adiciona tarefas");
            for ( int i = 1; i <= 10; i++ ) {
                Statement stInsert = connection.createStatement();
                String insertCommand = String.format( "insert into todo ( id,  descricao, resumo ) values (%d, 'Descricao %d', 'Resumo %d' )", i, i, i );
                System.out.println( insertCommand );
                stInsert.execute( insertCommand );
                stInsert.close();
            }

            // Recuperare os iten da lista
            System.out.println("Mostra tarefas");
            
            List< Todo > todoList = new ArrayList<Todo>();
            
            Statement stSelect = connection.createStatement();
            ResultSet rs = stSelect.executeQuery( "select * from todo" );

            while ( rs.next() ) {
                // Mapeamento 'Objeto Relacional'
                Todo todo = new Todo();
                todo.setId( rs.getInt( "id" ) );
                todo.setDescricao( rs.getString( "descricao" ) );
                todo.setResumo( rs.getString( "resumo" ) );
                todoList.add( todo );
            }
            stSelect.close();
            System.out.println( todoList );

        } catch ( SQLException e ) {

            e.printStackTrace();
        }

    }
}
