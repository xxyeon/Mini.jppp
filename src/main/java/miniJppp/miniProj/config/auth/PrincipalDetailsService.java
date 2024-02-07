package miniJppp.miniProj.config.auth;

import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository userRepository;

    //함수 종료시 @Authentication 어노테이션이 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("username = " + name);
        Member userEntity = userRepository.findByName(name);
        if (userEntity != null) {
            return new PrincipalDetails(userEntity); //userEntity를 넣어줘야 UserDetails에서 우리의 User 객체를 사용할 수 있음
        }
        return null;
    }
}
