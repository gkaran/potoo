package gk.potoo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private StatelessAuthenticationFilter statelessAuthenticationFilter;

    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    public SecurityConfiguration() {
        super(true);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/accounts/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/api/accounts/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/wows/**").permitAll()
            .anyRequest().authenticated()
            .and().headers()
            .and().anonymous()
            .and().servletApi()
            .and().exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint)
            .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(statelessAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .httpBasic().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }

}
