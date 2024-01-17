package miniJppp.miniProj.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.JdbcUtils;
import miniJppp.miniProj.entity.*;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {

//    private final DataSource dataSource;
//    private List<Word> wordList;
//    private ArrayList<Chapter> chapters;

    // 총 단어 수
//    int wordCount;
    public List<Word> findAllByChapter_Id(Long chapterId);

  /*  public List<Word> findByChapter(int chapterId) throws SQLException {
        String sql = "select * from WORD where chapter_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            wordCount = 0;
            wordList = new ArrayList<>();
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            System.out.println(chapterId);

            pstmt.setInt(1, chapterId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Word word = new Word(rs.getInt("word_id"), rs.getString("word"), rs.getString("answer"));
                wordList.add(word);
                wordCount++;
            }
            return wordList;

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }

    public int wordTotal(){
        return wordCount;
    }

    public ArrayList<Chapter> findAllChapter() {
        chapters = new ArrayList<>();
        String sql = "select * from CHAPTER";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Chapter chapter = new Chapter(rs.getInt("chapter_id"), rs.getString("title"), rs.getString("number"));
                chapters.add(chapter);
            }
            log.info("chapters={}", chapters);
            return chapters;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }

    }

    public Word findById(int wordId) {
        String sql = "select * from WORD where word_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            ArrayList<Word> wordList = new ArrayList<>();
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, wordId); // 쿼리 배열 검색 해결하기
            System.out.println("wordList = " + wordList);
            rs = pstmt.executeQuery();

            Word word = null;
            while (rs.next()) {
                word = new Word(rs.getInt("word_id"), rs.getString("word"), rs.getString("answer"), rs.getInt("chapter_id"));
            }
            System.out.println("wordList = " + wordList);
            return word;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }


    public void close(Connection con, Statement stmt, ResultSet rs) {

        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
        DataSourceUtils.releaseConnection(con, dataSource);


    }

    public Connection getConnection() {
        Connection con = DataSourceUtils.getConnection(dataSource);
        return con;
    }
*/

}
