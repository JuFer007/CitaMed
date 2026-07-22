package com.app.CitaMed.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            var rs = stmt.executeQuery("SELECT COUNT(*) FROM usuarios");
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                log.info("Base de datos vacia, ejecutando seed...");
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("data.sql"));
                log.info("Seed completado.");
            } else {
                log.info("Base de datos ya tiene datos, seed omitido.");
            }
        }
    }
}
