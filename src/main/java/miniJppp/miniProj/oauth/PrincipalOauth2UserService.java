package miniJppp.miniProj.oauth;

import lombok.extern.slf4j.Slf4j;
import miniJppp.miniProj.config.auth.PrincipalDetails;
import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.oauth.provider.GoogleUserInfo;
import miniJppp.miniProj.oauth.provider.OAuth2UserInfo;
import miniJppp.miniProj.repository.MemberRepository;
import miniJppp.miniProj.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    //여기서 구글 로그인 후처리가 된다.


    @Autowired
    private MemberRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    //loadUser에서 후처리가 된다.
    //함수 종료시 @Authentication 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest = " + userRequest);
        System.out.println("userRequest.getClientRegistration = " + userRequest.getClientRegistration());
        System.out.println("userRequest.getAccessToken = " + userRequest.getAccessToken());

        OAuth2User oauth2User = super.loadUser(userRequest);
        System.out.println("super.loadUser(userRequest).getAttributes() = " + oauth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
        }
        //구글에서 받아온 유저 정보로 회원가입 진행
//        String provider = userRequest.getClientRegistration().getClientId(); //google
//        String providerId = oauth2User.getAttribute("sub"); //115888940972002825838
//        String username = provider + "_" + providerId; //google_115888940972002825838 이렇게 해두면 username이 충돌 날 일은 없다.
//        String password = bcryptPasswordEncoder.encode("겟인데어");
//        String email = oauth2User.getAttribute("email");
//        String role = "ROLE_USER";

        Optional<Member> userOptional = userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());
        //자체 회원가입한 회원과 중복되는지 확인

//        Member findMember = userRepository.findByProviderAndEmail("NATIVE", oAuth2UserInfo.getEmail()).orElseThrow(); //provider와 이메일로 회원찾기. 만약 native로 구글 계정을 사용하여 회원가입 하는데 이미 디비에 구글로 가입된 계정이 있다면 provider가 google인 사용자에게 본인인지 확인하라는 확인 메일을 전송하는게 좋을듯

        //이미 존재하는 회원인지 확인해서 없으면 회원가입 진행
        Member user;
        if (userOptional.isPresent()) { //구글을 통해서 가입한 적이 있다면
            user = userOptional.get();
            //user가 존재하면 update 해주기
            user.setEmail(oAuth2UserInfo.getEmail());
        } else {
            // user의 패스워트가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            //임시 비밀번호 발급
            String tempPw;
            try {
                tempPw = emailService.sendEmail(oAuth2UserInfo.getEmail());
                log.info("temp password: {}", tempPw);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            user = Member.builder()
                    .name(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId())
                    .password(bCryptPasswordEncoder.encode(tempPw)) //임시 비밀번호 발급
                    .email(oAuth2UserInfo.getEmail())
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .build();
        }
        userRepository.save(user);
        return new PrincipalDetails(user, oauth2User.getAttributes()); //이 객체가 Authentication에 위치


//        if(userEntity == null) {
//            System.out.println("OAuth 로그인이 최초입니다.");
//            userEntity = User.builder() //lombok의 @Builder를 이용해서 회원가입
//                    .username(username)
//                    .password(password)
//                    .email(email)
//                    .role(role)
//                    .provider(provider)
//                    .providerId(providerId)
//                    .build();
//            userRepository.save(userEntity);
//        }else{
//            System.out.println("로그인을 이미 한적이 있습니다.");
//        }

//    }
    }
}
