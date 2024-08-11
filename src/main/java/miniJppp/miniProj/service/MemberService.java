package miniJppp.miniProj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniJppp.miniProj.DTO.MemberDto;

import miniJppp.miniProj.DTO.UserInfo;
import miniJppp.miniProj.entity.BookMark;
import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.repository.BookMarkRepository;
import miniJppp.miniProj.repository.InventoryRepository;
import miniJppp.miniProj.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final InventoryRepository inventoryRepository;
    private final BookMarkRepository bookMarkRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void saveMember(Member memberDto) {

        Member findMember = memberRepository.findByName(memberDto.getEmail());
        if(findMember != null){
            log.info("이미 존재하는 회원입니다.");
        } else if (memberRepository.findByName(memberDto.getName()) != null) {
            log.info("중복된 닉네임 입니다.");
        } else {
            Member member = Member.builder()
                    .email(memberDto.getEmail())
                    .createAt(memberDto.getCreateAt())
                    .password(bCryptPasswordEncoder.encode(memberDto.getPassword()))
                    .name(memberDto.getName())
                    .createAt(LocalDateTime.now())
                    .provider("NATIVE").build();

            memberRepository.save(member);
            bookMarkRepository.save(new BookMark(LocalDateTime.now(), member));
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
        Member findMember = memberRepository.findByName(user.getNickname());
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

    @Transactional
    public void updateUserInfo(UserInfo userInfo) {
        log.info("UserInfo.getNickname: {}", userInfo.getNickname());
        log.info("UserInfo.getProvider: {}, UserInfo.getEmail: {}", userInfo.getProvider(), userInfo.getEmail());
        Member member = memberRepository.findByProviderAndEmail(userInfo.getProvider(), userInfo.getEmail());
        log.info("Member: {}", member);
        MemberDto memberDto;
        if (!bCryptPasswordEncoder.matches(userInfo.getPassword(), member.getPassword())) {
            member.setPw(false);


        } else {
            LocalDateTime updateTime = LocalDateTime.now();
            member.setName(userInfo.getNickname());
            member.setUpdateAt(updateTime);
            member.setPw(true);

        }
    }

}

