package miniJppp.miniProj.config.auth;

import lombok.Data;
import miniJppp.miniProj.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인 진행이 완료되면 시큐리티 session을 만들어 준다 (같은 세션인데 시큐리티 자신만의 세션을 저장, Security ContextHolder 라는 키 값에 세션 정보 저장)
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member user;
    private Map<String, Object> attributes;

    //일반 로그인
    public PrincipalDetails(Member user) {
        this.user = user;
    }

    //Oauth 로그인 할 때 사용하는 생성자
    public PrincipalDetails(Member user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
        System.out.println("user = " + user);
        System.out.println("attributes" + attributes);
    }

    //해당 User의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //user.getRole 타입은 String이므로 그래도 반환할 수 없으니까  Collection타입으로 변환해주자
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            public String getAuthority() {
                return "get Authority";
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() { //계정 만료됐니?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        //우리 사이트에서 1년동안 회원이 로그인을 안하면 휴면 계정으로 전환한다? false로 return
        // 현재시간 - 로그인 시간 => 1년을 초과하면 false
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
