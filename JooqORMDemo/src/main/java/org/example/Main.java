package org.example;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.impl.DSL;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        String userName = "root";
        String password = "cide2050";
        String url = "jdbc:mysql://localhost:3306";

        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            DSLContext context = DSL.using(conn, SQLDialect.MYSQL);
            GenerationTool.generate(
                    Files.readString(
                            Path.of("target/classes/jooqconfig.xml")
                    )
            );
        } catch (SQLException e) {
            System.err.println("SQL Exception:");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Exception:");
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Exception Exception:");
            System.err.println(e.getMessage());
        }


    }
}