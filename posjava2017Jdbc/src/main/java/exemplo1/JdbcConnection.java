package exemplo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

    // jdbc:mysql://localhost:3306/posjava

    public static void main( String[] args ) {

        System.out.println( "Inicio" );
        String url = "jdbc:mysql://localhost:3306/posjava";
        String user = "root";
        String password = "";

        try {

            Connection connection = DriverManager.getConnection( url, user, password );

            Statement st = connection.createStatement();
            st.execute( "create table tb1 ( id integer auto_increment primary key, nome varchar(30) )" );

            st.close();
            connection.close();

        } catch ( SQLException e ) {

            e.printStackTrace();
        }

        System.out.println( "Termino" );
    }

}
