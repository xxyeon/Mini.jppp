package miniJppp.miniProj.config.auth;

import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//config설정에서 .loginProcessingUrl("/login") 요청이오면
//요청이오면 userDtatilsService 타입으로 ioc되어있는 loadUserByUsername함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository userRepository;

    //함수 종료시 @Authentication 어노테이션이 만들어진다.
    //loadUserByUsername 인자에서 email이라는 변수명이 /login 요청하는 폼 input 태그의 name과 동일해야함. 동일하지않으면 매칭이 안됨
    //다르면 config설정에서 .usernameParameter("..") 설정해줘야함
    // /login요청이오면 스프링은 ioc 컨테이너에서 userDetailsService를 찾는다. -> loadUserByUsername 호출
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        Member userEntity = userRepository.findByName(username);
        if (userEntity != null) {
            return new PrincipalDetails(userEntity); //userEntity를 넣어줘야 UserDetails에서 우리의 User 객체를 사용할 수 있음
        }
        return null;
    }
}
