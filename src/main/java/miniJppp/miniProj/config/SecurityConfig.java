package  miniJppp.miniProj.config;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터(SecurityConfig)가 스프링 필터체인에 등록된다.
@EnableMethodSecurity(securedEnabled=true, prePostEnabled = true) //강의에서는 @EnableGlobalMethodSecurity를 사용했으나 지금은 Deprecated여서 EnableMethodSecurity로 변경
public class SecurityConfig{

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    //패스워드 암호화
    //해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
//    @Bean
//    public BCryptPasswordEncoder encodePwd(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrfConfig) ->
                csrfConfig.disable()
        );
        http
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/test/**")).authenticated() //이런 주소로 들어오면 인증이 필요함
                .requestMatchers(new AntPathRequestMatcher("/main/**")).authenticated() //이런 주소로 들어오면 인증이 필요함

                .anyRequest().permitAll()
                                .and()

                                .formLogin(formLogin -> formLogin
                                        .loginPage("/main-page")//-> 권한이 없는 페이지에 접근했을 때 /login으로 이동
                                        .loginProcessingUrl("/login") // /login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행 controller에 /login을 따로 만들지 않아도됨


                                );
        http
                .oauth2Login(Customizer.withDefaults())
//                .userDetailsService((UserDetailsService) principalOauth2UserService)
                .formLogin(formLogin -> formLogin
                        .loginPage("/main-page"));


        return http.build();
    }
}
