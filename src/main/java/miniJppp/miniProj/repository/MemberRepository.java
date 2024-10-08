package miniJppp.miniProj.repository;


import miniJppp.miniProj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByName(String name);

    public List<Member> findAll();

    Optional<Member> findByProviderAndProviderId(String provider, String providerId);

    public List<Member> findByEmail(String email);

    Member findByProviderAndEmail(String provider, String email);
}
