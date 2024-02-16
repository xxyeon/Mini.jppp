package miniJppp.miniProj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniJppp.miniProj.DTO.MemberDto;

import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.repository.InventoryRepository;
import miniJppp.miniProj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final InventoryRepository inventoryRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void saveMember(MemberDto memberDto) {

        Member findMember = memberRepository.findByName(memberDto.getEmail());
        if(findMember != null){
            log.info("이미 존재하는 회원입니다.");
        } else if (memberRepository.findByName(memberDto.getNickname()) != null) {
            log.info("중복된 닉네임 입니다.");
        } else {
            Member member = Member.builder()
                    .email(memberDto.getEmail())
                    .createAt(memberDto.getCreateAt())
                    .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                    .name(memberDto.getNickname())
                    .createAt(LocalDateTime.now())
                    .provider("NATIVE").build();

            memberRepository.save(member);

            inventoryRepository.save(new Inventory(LocalDateTime.now(), member));
        }
    }

    @Transactional
    public MemberDto findMember(String name) {
        Member member = memberRepository.findByName(name);

        return MemberDto.builder()
                .id(member.getId())
                .nickname(member.getName())
                .build();
    }

    public void login(MemberDto user) {
        Member findMember = memberRepository.findByEmail(user.getEmail());
        System.out.println("User 객체: " + user.getEmail());
        System.out.println("findMember: " + findMember.getEmail());
        System.out.println(("user 비번: " + user.getPassword()));
        if (findMember == null) {
            log.info("해당 이메일을 가진 유저가 존재하지 않습니다.");
        } else if (!bCryptPasswordEncoder.matches(user.getPassword(), findMember.getPassword())) {
            System.out.println(findMember.getPassword());
            log.info("비밀번호가 틀립니다.");
        }

    }
}
