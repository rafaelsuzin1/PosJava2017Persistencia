package exemplo1.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static final int MYSQL    = 1;
    public static final int POSTGRES = 2;

    public static Connection getConnection( int connectionType ) throws SQLException {

        System.out.println( "Cria conexão" );

        Properties props = new Properties();
        String url;
        switch ( connectionType ) {
            case MYSQL:
                props.setProperty( "user", "root" );
                props.setProperty( "password", "" );
                url = "jdbc:mysql://localhost:3306/posjava";
            break;
            case POSTGRES:
                props.setProperty( "user", "postgres" );
                props.setProperty( "password", "unicesumar" );
                url = "jdbc:postgresql://localhost/posjava";
            break;
            default:
                throw new SQLException( "Connector not found" );
        }

        return DriverManager.getConnection( url, props );
    }
}
