package exemplo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import exemplo1.factory.ConnectionFactory;

public class JdbcInsert {

    public static void main( String[] args ) {

        System.out.println( "Inicio" );
        // String url = "jdbc:mysql://localhost:3306/posjava";
        // String user = "root";
        // String password = "";

        try {

            // Connection connection = DriverManager.getConnection(url, user, password);
            Connection connection = ConnectionFactory.getConnection( ConnectionFactory.MYSQL );

            Statement st = connection.createStatement();
            st.execute( "insert into tb1 ( id,  nome ) values (1, 'Juca')" );

            st.close();
            connection.close();

        } catch ( SQLException e ) {

            e.printStackTrace();
        }

        System.out.println( "Termino" );
    }
}
