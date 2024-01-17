package miniJppp.miniProj.repository;

import miniJppp.miniProj.domain.Book_mark;
import miniJppp.miniProj.domain.Review;
import miniJppp.miniProj.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

//    private final List<Book_mark> bookMarks = new ArrayList<>();

    public List<BookMark> findAll();


}
