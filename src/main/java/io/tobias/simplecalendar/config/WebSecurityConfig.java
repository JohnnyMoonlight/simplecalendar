package io.tobias.simplecalendar.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String DEFAULT_BASE_URL = "/#/";
    public static final String API_URL_MATCHER  = "/api/**";


    @Value("${calendarUser.adminusername}")
    private String USER;
    @Value("${calendarUser.adminpassword}")
    private String PASSWORD;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    DataSource dataSource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url(dbUrl);
        builder.driverClassName(dbDriver);
        builder.username("");
        builder.password("");
        return builder.build();

    }

    @Autowired
    protected void initialize(final AuthenticationManagerBuilder authenticationManagerBuilder, DataSource dataSource) throws Exception {

        logger.info("Initializing AuthenticationManagerBuilder with jdbc authentication.");

        authenticationManagerBuilder
        .jdbcAuthentication()
        .passwordEncoder(encoder()).dataSource(dataSource)
        .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
        .authoritiesByUsernameQuery("SELECT username, roles FROM users WHERE username=?");
    }

    @Override
    protected void configure(HttpSecurity securityConfigBuilder) throws Exception {
        securityConfigBuilder
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, API_URL_MATCHER).permitAll()
        .antMatchers(API_URL_MATCHER).authenticated()
        .and()
        .formLogin().loginPage("/login.html")
        .loginProcessingUrl("/login").permitAll()
        .defaultSuccessUrl(DEFAULT_BASE_URL, true)
        .and()
        .logout().logoutSuccessUrl(DEFAULT_BASE_URL).logoutUrl("/logout").permitAll().and().csrf().disable();

        securityConfigBuilder.authorizeRequests()
        .antMatchers("/css/", "/js/").permitAll()        ;
    }


    @Bean
    public PasswordEncoder encoder() {
        final int passwordStrength = 12;
        return new BCryptPasswordEncoder(passwordStrength);
    }

}
