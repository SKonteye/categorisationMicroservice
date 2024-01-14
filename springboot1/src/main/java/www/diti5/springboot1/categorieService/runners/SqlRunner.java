package www.diti5.springboot1.categorieService.runners;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Component
public class SqlRunner implements ApplicationRunner
{
    private final JdbcTemplate jdbcTemplate;
    // TODO: Methode 2
    //private final ResourcePatternResolver resourcePatternResolver;

    public SqlRunner(JdbcTemplate jdbcTemplate, ResourcePatternResolver resourcePatternResolver)
    {
        this.jdbcTemplate = jdbcTemplate;
        // TODO: Methode 2
        // this.resourcePatternResolver = resourcePatternResolver;
    }

    @Value("classpath:scripts.sql")
    private Resource sqlScript;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        // TODO: Methode 2
        // Resource sqlScript = resourcePatternResolver.getResource("classpath:data.sql");

        // Code SQL après la création des models/entités ici
        String sql = readScriptContent(sqlScript);
        jdbcTemplate.execute(sql);
    }

    // Permet de lire le contenu du fichier sql fichier sql
    private String readScriptContent(Resource resource) throws Exception
    {
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
