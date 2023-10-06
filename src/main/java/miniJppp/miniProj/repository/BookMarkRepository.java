package mini.miniProj.repository;

import mini.miniProj.domain.Book_mark;
import mini.miniProj.domain.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookMarkRepository {

    private final List<Book_mark> bookMarks = new ArrayList<>();

    public List<Book_mark> findAll() {
        return bookMarks;
    }

    public void save(Review review, Book_mark bookMark) {

    }

    public void deleteById(Review review, Book_mark bookMark) {

    }
}
