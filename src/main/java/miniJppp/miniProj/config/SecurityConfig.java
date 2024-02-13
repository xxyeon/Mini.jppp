package  miniJppp.miniProj.config;

import miniJppp.miniProj.config.auth.CustomAuthenticationFailureHandler;
import miniJppp.miniProj.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration
@EnableWebSecurity //스프링 시큐리티 필터(SecurityConfig)가 스프링 필터체인에 등록된다.
@EnableMethodSecurity(securedEnabled=true, prePostEnabled = true)
public class SecurityConfig {


    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    //패스워드 암호화


    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrfConfig) ->
                csrfConfig.disable()
        );
        http
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/main/**")).authenticated()

                .anyRequest().permitAll()
                                .and()

                                .formLogin()
                                        .loginPage("/main-page")//-> 권한이 없는 페이지에 접근했을 때 /login으로 이동
                                        .loginProcessingUrl("/login") // /login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행 controller에 /login을 따로 만들지 않아도됨
                                        .defaultSuccessUrl("/") // 로그인 성공 후 이동 페이지
                                        .usernameParameter("name")
                                        .passwordParameter("password") // 패스워드 파라미터명 설정, default: password
                                        .failureHandler(authenticationFailureHandler())
                .and()

                .oauth2Login()
                .loginPage("/main-page")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);


        return http.build();
    }
}
