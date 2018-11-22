package jp.co.sss.crud.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sss.crud.util.Encoding;
import jp.co.sss.crud.util.ScreenMenu;

public class DBManager {
//	// ドライバクラス名
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
//	// 接続するDBのURL
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
//	// DB接続するためのユーザー名
	private static final String USER_NAME = "console_crud_user";
//	// DB接続するためのパスワード
	private static final String PASSWORD = "systemsss";

	/**
	 * DBと接続する
	 *
	 * @return DBコネクション
	 * @throws ClassNotFoundException
	 *             ドライバクラスが見つからなかった場合
	 * @throws SQLException
	 *             DB接続に失敗した場合
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		// JDBCドライバクラスをJVMに登録
		Class.forName(DRIVER);
		// DBに接続
		Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);

		return conn;
	}

	/**
	 * DBとの接続を切断する
	 *
	 * @param connection
	 *            DBとの接続情報
	 * @throws SQLException
	 *             クローズ処理に失敗した場合に送出
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
//				System.out.println("DBを切断しました。");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * PreparedStatementをクローズする
	 *
	 * @param preparedStatement
	 *            ステートメント情報
	 * @throws SQLException
	 *             クローズ処理に失敗した場合に送出
	 */
	public static void close(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ResultSetをクローズする
	 *
	 * @param resultSet
	 *            SQL検索結果
	 * @throws SQLException
	 *             クローズ処理に失敗した場合に送出
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 全件表示する
	 *
	 * @param resultSet
	 *            employeeの全件のデータ検索
	 * @throws SQLException
	 *            クローズ処理に失敗した場合に送出
	 */
	public static void AllSearch() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ScreenMenu sc = new ScreenMenu();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT e.emp_id, e.emp_name, e.gender, "
					+ "TO_CHAR(e.birthday, 'YYYY/MM/DD') AS birthday, d.dept_name "
					+ "FROM employee e INNER JOIN department d ON e.dept_id = d.dept_id "
					+ "ORDER BY e.emp_id ASC";

			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs != null) {
				sc.DataColumnRow();
				while (rs.next()) {
					System.out.print(rs.getString("emp_id") + "    " + "\t");
					System.out.print(rs.getString("emp_name") + "  " + "\t");
					String gender = Encoding.GenderEncoding(rs.getString("gender"));
					System.out.print(gender + "    " + "\t");
//					System.out.print(rs.getString("gender") + "\t");
					System.out.print(rs.getString("birthday") + "  " + "\t");
					System.out.println(rs.getString("dept_name") + "\t");
				}
			} else {
				sc.NoData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
			DBManager.close(pst);
			DBManager.close(conn);
		}
	}

	/**
	 * データを登録する
	 *
	 * @param resultSet
	 *            データの新規登録
	 * @throws SQLException
	 *            クローズ処理に失敗した場合に送出
	 */
	public static void InsertData() throws IOException {
		Connection conn = null;
		PreparedStatement pst = null;
		ScreenMenu sc = new ScreenMenu();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			sc.InsertData1();
			String empName = br.readLine();

			sc.InsertData2();
			int gender = Integer.parseInt(br.readLine());

			sc.InsertData3();
			String birth = br.readLine();

			sc.InsertData4();
			int depart = Integer.parseInt(br.readLine());

			conn = DBManager.getConnection();
			String sql = "INSERT INTO employee VALUES (seq_emp.NEXTVAL, ?, ?, ?, ?)";
			pst = conn.prepareStatement(sql);

			pst.setString(1, empName);
			pst.setInt(2, gender);
			pst.setString(3, birth);
			pst.setInt(4, depart);

			pst.executeUpdate();

			sc.InsertData5();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pst);
			DBManager.close(conn);
		}
	}

	/**
	 * データを更新する
	 *
	 * @param resultSet
	 *            データの更新
	 * @throws SQLException
	 *            クローズ処理に失敗した場合に送出
	 */
	public static void UpdateData() throws IOException{
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ScreenMenu sc  = new ScreenMenu();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			sc.UpdateData1();
			int empId = Integer.parseInt(br.readLine());

			conn = DBManager.getConnection();

			String sql = "SELECT * FROM employee WHERE emp_id = ?";
			pst = conn.prepareStatement(sql);

			pst.setInt(1, empId);
			rs = pst.executeQuery();

				if (rs.next() != true) {
					sc.DataNotFound();
				} else {
					DBManager.close(rs);
					DBManager.close(pst);
					DBManager.close(conn);

					sc.InsertData1();
					String empName = br.readLine();

					sc.InsertData2();
					int gender = Integer.parseInt(br.readLine());

					sc.InsertData3();
					String birth = br.readLine();

					sc.InsertData4();
					int depart = Integer.parseInt(br.readLine());

					conn = DBManager.getConnection();

					String sql2 = "UPDATE employee SET emp_name = ?, gender = ?, "
							+ "birthday = ?, dept_id = ? WHERE emp_id = ?";

					pst = conn.prepareStatement(sql2);

					pst.setInt(5, empId);
					pst.setString(1, empName);
					pst.setInt(2, gender);
					pst.setString(3, birth);
					pst.setInt(4, depart);

					pst.executeUpdate();

					sc.UpdateData2();
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pst);
			DBManager.close(conn);
		}
	}

	/**
	 * データを削除する
	 *
	 * @param resultSet
	 *            データの削除
	 * @throws SQLException
	 *            クローズ処理に失敗した場合に送出
	 */
	public static void DeleteData() throws IOException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ScreenMenu sc  = new ScreenMenu();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			sc.DeleteData1();
			int empId = Integer.parseInt(br.readLine());

			conn = DBManager.getConnection();
			String sql = "SELECT * FROM employee WHERE emp_id = ?";
			pst = conn.prepareStatement(sql);

			pst.setInt(1, empId);
			rs = pst.executeQuery();

				if (rs.next() != true) {
					sc.DataNotFound();
				} else {
					DBManager.close(rs);
					DBManager.close(pst);
					DBManager.close(conn);

					conn = DBManager.getConnection();

					String sql2 = "DELETE FROM employee WHERE emp_id = ?";

					pst = conn.prepareStatement(sql2);

					pst.setInt(1, empId);

					pst.executeUpdate();

					sc.DeleteData2();
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pst);
			DBManager.close(conn);
		}
	}

	/**
	 * 社員名で検索する
	 *
	 * @param resultSet
	 *            特定の社員名で検索
	 * @throws SQLException
	 *            クローズ処理に失敗した場合に送出
	 */
	public static void SpecialNameSearch() throws IOException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		ScreenMenu sc = new ScreenMenu();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			sc.SpecialNameSearch1();
			String empName = br.readLine();
			String empNameSimilar = "%" + empName + "%";

			conn = DBManager.getConnection();

			String sql = "SELECT e.emp_id, e.emp_name, e.gender, "
					+ "TO_CHAR(e.birthday, 'YYYY/MM/DD') AS birthday, "
					+ "d.dept_name FROM employee e "
					+ "INNER JOIN department d ON e.dept_id = d.dept_id "
					+ "WHERE e.emp_name LIKE ? "
					+ "ORDER BY e.emp_id ASC";

			pst = conn.prepareStatement(sql);

			pst.setString(1, empNameSimilar);
			rs = pst.executeQuery();

			if (rs != null) {
				sc.DataColumnRow();
				while (rs.next()) {
					System.out.print(rs.getString("emp_id") + "    " + "\t");
					System.out.print(rs.getString("emp_name") + "  " + "\t");
					String gender = Encoding.GenderEncoding(rs.getString("gender"));
					System.out.print(gender + "    " + "\t");
//				System.out.print(rs.getString("gender") + "\t");
					System.out.print(rs.getString("birthday") + "  " + "\t");
					System.out.println(rs.getString("dept_name") + "\t");
				}
			} else {
				sc.SNameNoData();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs);
			DBManager.close(pst);
			DBManager.close(conn);
		}
	}
}
