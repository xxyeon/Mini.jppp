package mini.miniProj.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mini.miniProj.domain.Word;
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
    private final List<Word> wordList = new ArrayList<>();

    public List<Word> findAll() throws SQLException {
        String sql = "select * from Word";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Word word = new Word();
                word.setWord_id(rs.getInt("word_id"));
                word.setWord(rs.getString("word"));
                word.setAnswer(rs.getString("answer"));

                wordList.add(word);
            }
            return wordList;

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally{
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
