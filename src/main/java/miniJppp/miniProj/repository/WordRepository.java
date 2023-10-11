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

    public List<Word> findByChapter(int chapterId) throws SQLException {
        String sql = "select * from word where chapter_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            wordList = new ArrayList<>();
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            System.out.println(chapterId);

            pstmt.setInt(1, chapterId);
            rs = pstmt.executeQuery();


            while (rs.next()) {
                Word word = new Word(rs.getInt("word_id"), rs.getString("word"), rs.getString("answer"));

                wordList.add(word);
            }
            return wordList;

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }
    }

    public ArrayList<Chapter> findAllChapter() {
        chapters = new ArrayList<>();
        String sql = "select * from chapter";

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



    private void close(Connection con, Statement stmt, ResultSet rs) {

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
