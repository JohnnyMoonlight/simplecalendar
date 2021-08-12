package io.tobias.simplecalendar.config;
import io.tobias.simplecalendar.service.CalendarUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CalendarUserDetailsService userDetailsService;
    @Value("${calendarUser.adminusername}")
    private String USER;
    @Value("${calendarUser.adminpassword}")
    private String PASSWORD;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.driver-class-name}")
    private String dbDriver;

    DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url(dbUrl);
        builder.driverClassName(dbDriver);
        builder.username("");
        builder.password("");
        return builder.build();

    }

    @Autowired
    protected void initialize(final AuthenticationManagerBuilder authenticationManagerBuilder, DataSource dataSource) throws Exception {
        authenticationManagerBuilder.jdbcAuthentication().passwordEncoder(encoder()).dataSource(dataSource)
                .usersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username=?")
                .authoritiesByUsernameQuery(
                "SELECT username, roles FROM users WHERE username=?");;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        return users;
    }




    @Bean
    DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        final int passwordStrength = 10;
        return new BCryptPasswordEncoder(passwordStrength);
    }

}
