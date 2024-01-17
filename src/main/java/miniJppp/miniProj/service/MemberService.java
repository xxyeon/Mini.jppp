package miniJppp.miniProj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniJppp.miniProj.DTO.MemberDto;

import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Member;
import miniJppp.miniProj.repository.InventoryRepository;
import miniJppp.miniProj.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional
    public MemberDto saveMember(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        Member newMember = null;

        if(memberRepository.findByName(memberDto.getName()) != null){
            log.info("이미 존재하는 회원입니다.");
            return MemberDto.builder()
                    .name(memberRepository.findByName(memberDto.getName()).getName()).build();
        } else{
            memberRepository.save(member);
            newMember = memberRepository.findByName(memberDto.getName());
            Long member_id = newMember.getId();
            inventoryRepository.save(new Inventory(LocalDateTime.now(), newMember));
        }
        memberDto.setName(memberDto.getName());
        return memberDto;
    }

    @Transactional
    public MemberDto findMember(String name) {
        Member member = memberRepository.findByName(name);

        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }
}
