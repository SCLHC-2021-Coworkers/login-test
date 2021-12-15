package internaltest.springsecuritylogin.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import internaltest.springsecuritylogin.common.dto.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserDetailService userDetailsService;


    @Autowired
    public SecurityConfig(AppUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/webjars/**", "/images/**", "/css/**", "/js/**")
                .permitAll()
                .antMatchers("/", "/login")
                .permitAll()
                .antMatchers("/auth/register", "/auth/login", "/auth/registration-permission",
                        "/auth/send-email-verification/*", "/auth/verify-email/**", "/auth/send-password-reset/*",
                        "/auth/reset-password/**")
                .permitAll().anyRequest().authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation().migrateSession().and();


        http.formLogin().disable();

        http.httpBasic().disable();

        http.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    protected JsonUsernamePasswordAuthenticationFilter getAuthenticationFilter() {
        JsonUsernamePasswordAuthenticationFilter authFilter = new JsonUsernamePasswordAuthenticationFilter();

        try {
            authFilter.setFilterProcessesUrl("/auth/login");
            authFilter.setAuthenticationManager(this.authenticationManagerBean());
            authFilter.setUsernameParameter("username");
            authFilter.setPasswordParameter("password");
            authFilter.setAuthenticationSuccessHandler((request, response, auth) -> {
                response.setStatus(HttpStatus.OK.value());

                ResponseWrapper responseWrapper = new ResponseWrapper("OK", "로그인되었습니다");
                responseWrapper.setContents(null);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(responseWrapper));
            });
            authFilter.setAuthenticationFailureHandler((request, response, auth) -> {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());

                ResponseWrapper responseWrapper = new ResponseWrapper("EAUTH401-01", "아이디나 비밀번호가 틀렸습니다");
                responseWrapper.setContents(null);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(responseWrapper));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return authFilter;
    }
}
