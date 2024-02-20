package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.BookMark;
import miniJppp.miniProj.entity.Review;
import miniJppp.miniProj.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    public List<Review> findAllByBookMark(BookMark bookMark);

    public Review findByBookMarkAndWord(BookMark bookMark, Word word);
}
