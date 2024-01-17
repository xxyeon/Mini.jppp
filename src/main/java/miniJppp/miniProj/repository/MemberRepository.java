package miniJppp.miniProj.repository;


import miniJppp.miniProj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByName(String name);

    public Member save(Member member);

    public List<Member> findAll();

}
