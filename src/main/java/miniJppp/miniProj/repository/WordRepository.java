package miniJppp.miniProj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import miniJppp.miniProj.entity.*;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    public List<Word> findAllByChapter_Id(Long chapterId);

}
