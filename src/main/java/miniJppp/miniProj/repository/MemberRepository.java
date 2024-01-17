package miniJppp.miniProj.repository;

import lombok.RequiredArgsConstructor;
import miniJppp.miniProj.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final DataSource dataSource;
    public int member_id = 0;

    public Member findUser(String name) {
        String nickname;
        String sql = "select member_id, name, profileimgurl from MEMBER where name = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            Member member = new Member();
            while (rs.next()) {
                member.setMember_id(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setProfileImgUrl(rs.getString("profileimgurl"));

            }
            return member;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }
    public List<Member> findAll() {
        return null;
    }

    public void save(String name) {
        String sql = "insert into MEMBER (name, create_at) values (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        int rs = 0;

        try{

            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();

            Member member = new Member();

            member.setName(name);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt);
        }
    }

//    public void updateMemberInventoroy(int inventoryId){
//        String sql = "update Member set inventory_id = ?";
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        try{
//            con = getConnection();
//            pstmt = con.prepareStatement(sql);
//
//            pstmt.setInt(1, inventoryId);
//
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            close(con, pstmt);
//        }
//    }

    public void close(Connection con, Statement stmt, ResultSet rs) {

        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
        DataSourceUtils.releaseConnection(con, dataSource);


    }
    public void close(Connection con, Statement stmt) {

        JdbcUtils.closeStatement(stmt);
        // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
        DataSourceUtils.releaseConnection(con, dataSource);


    }

    public Connection getConnection() {
        Connection con = DataSourceUtils.getConnection(dataSource);
        return con;
    }

}
