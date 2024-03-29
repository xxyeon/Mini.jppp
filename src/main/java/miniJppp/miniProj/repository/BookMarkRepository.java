package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.BookMark;
import miniJppp.miniProj.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

    public List<BookMark> findAll();

    public BookMark findBookMarkByMember(Member member);
}
