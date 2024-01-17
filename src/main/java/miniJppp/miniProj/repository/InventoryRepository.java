package miniJppp.miniProj.repository;

import miniJppp.miniProj.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
/*    public static final String INVENTORY = "인벤토리";
    private final DataSource dataSource;
    private final List<Inventory> inventories = new ArrayList<>();*/

    public List<Inventory> findAll();

    public Inventory findByMember_id(Long memberId);

    public Inventory save(Inventory inventory);
    /*public List<Integer> findById(int member_id) {
        String sql = "select word_id from learn where inventory_id = select inventory_id from INVENTORY where member_id = ?";


        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{

            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, member_id);
            rs = pstmt.executeQuery();
            ArrayList<Integer> wordIdList = new ArrayList<>();
            int wordIdArray[];
            while (rs.next()) {
                wordIdList.add(rs.getInt("word_id"));
            }

            wordIdArray = new int[wordIdList.size()];
            for (int i = 0; i < wordIdList.size(); i++) {
                wordIdArray[i] = wordIdList.get(i);
            }

            System.out.println("wordIdList = " + wordIdList);
            for (int i=0; i<wordIdList.size(); i++){
                System.out.println("wordArray = " + wordIdArray[i]);
            }


            return wordIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }

    }


    public void create(int member_id) {
        String sql = "insert into INVENTORY (member_id, create_at) values (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, member_id);
            pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
//            pstmt.setString(2, "rla");

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt);
        }

    }

    public void save(int wordIdx, int member_id) {
        String sql = "insert into LEARN (word_id, inventory_id) values (?, select inventory_id from INVENTORY where member_id = ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, wordIdx);

            pstmt.setInt(2, member_id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt);
        }

    }

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

    }*/
}
