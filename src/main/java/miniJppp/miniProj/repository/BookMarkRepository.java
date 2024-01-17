package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

//    private final List<Book_mark> bookMarks = new ArrayList<>();

    public List<BookMark> findAll();


}
