package miniJppp.miniProj.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniJppp.miniProj.domain.Chapter;
import miniJppp.miniProj.domain.Word;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Repository
public class WordRepository {

    private final DataSource dataSource;
    private List<Word> wordList;
    private ArrayList<Chapter> chapters;

    // 총 단어 수
    int wordCount;

    public List<Word> findByChapter(int chapterId) throws SQLException {
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

    public ArrayList<Word> findById(List<Integer> wordIdList) {
        String sql = "select * from WORD where word_id in (?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            ArrayList<Word> wordList = new ArrayList<>();
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setArray(1, getConnection().createArrayOf("word", new List[]{wordIdList})); // 보류
            System.out.println("wordList = " + wordList);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Word word = new Word(rs.getInt("word_id"), rs.getString("word"), rs.getString("answer"));

                wordList.add(word);
            }
            System.out.println("wordList = " + wordList);
            return wordList;

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


}
