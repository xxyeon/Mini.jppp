package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterRespository extends JpaRepository<Chapter, Long> {

    public List<Chapter> findAll();

}
