package exemplo1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exemplo1.factory.ConnectionFactory;

public class JdbcSelect {

    public static void main( String[] args ) {

        Connection connection;
        try {
            connection = ConnectionFactory.getConnection( ConnectionFactory.MYSQL );

            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery( "select * from tb1" );
            while ( rs.next() ) {
                int id = rs.getInt( "id" );
                String nome = rs.getString( "nome" );
                System.out.println( String.format( "Encontrado: \n %10s: %s \n%10s: %s\n ", "id", "nome", id, nome ) );
            }

        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println( "termino" );
    }
}
