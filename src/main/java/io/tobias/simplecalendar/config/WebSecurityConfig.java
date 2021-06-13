package io.tobias.simplecalendar.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@PropertySource(value = { "classpath:application.properties" })
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${calendarUser.adminusername}")
    private String USER;
    @Value("${calendarUser.adminpassword}")
    private String PASSWORD;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**createAppointment**/", "/**createRoom**/", "/**deleteRoom**/").authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
    }

}
