package api.model.connectdb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectDB {
    private static Connection connection;
    private static ConnectDB instance = new ConnectDB();

    private ConnectDB() {
        if (this.connection == null) {
            try {
                Scanner in = new Scanner(new File("E:\\crdbconfig\\dbconfig.txt"));
                List<String> configInfos = new ArrayList<>();
                while (in.hasNext()) {
                    configInfos.add(in.nextLine());
                }

                Class.forName(configInfos.get(1));
                this.connection = DriverManager.getConnection(configInfos.get(0), configInfos.get(2), configInfos.get(3));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ConnectDB getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
