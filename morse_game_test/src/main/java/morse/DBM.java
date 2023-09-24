package morse;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBM {
	// 重要： 以下の部分を各自の MySQL の設定にあわせて変更する
		// 設定例）仮想マシンで動作する MySQL データベースの場合
		private static String url = "jdbc:mysql://localhost:3306/db_dev";
		private static String user = "dev";
		private static String password = "dev";
		//
		// 設定例）コンテナで動作する MySQL データベースの場合
		//private static String url = "jdbc:mysql://db-mysql:3306/db_dev";
		//private static String user = "dev";
		//private static String password = "dev";
		
		//  データベースへのコネクションを作成して返すメソッド
		public static Connection getUserConnection() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベース接続時のデバッグ表示
				System.out.println("Connecting to " + url +
						" using user=" + user + " and password=" + password);
				Connection con = DriverManager.getConnection(url, user, password);
				return con;
			} catch (Exception e) {
				e.printStackTrace(); // データベース接続の例外エラー表示
				throw new IllegalStateException(e);
			}
		}
		
	}

