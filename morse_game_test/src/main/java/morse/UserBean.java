package morse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class UserBean {

	private int id;
	private String name;
	private String pass;
	private String data;
	private String Uid;
	
	public UserBean() {
		;
	}

	// ====================================
	// セッターメソッドとゲッターメソッド
	// ====================================

	// 学籍番号
	public	void setId(int i) {
		id = i;
	}
	public int getId() {
		return id;
	}
	public void sendId(String Ui) {
		Uid=Ui;
	}
	public String getsendId() {
		return Uid;
	}
	// 氏名
	public void setName(String nm) {
		name = nm;
	}
	
	public String getName() {
		return name;
	}
	
	public void setData(String da) {
		data=da;
	}
	
	public String getData() {
		return data;
	}
	// GPA
	public	void setPass(String g) {
		pass = g;
	}
	public String getPass() {
		return pass;
	}
	
	// =====================================================
	// データベースへの追加
	//   setId(), setName(), setGpa() でセットした内容
	// =====================================================
	public boolean insertRecord() {
		
		try {
			// データベースへのコネクションを取得
			Connection con = DBM.getUserConnection();
			ArrayList<UserBean> list = new ArrayList<UserBean>();
			// SQL文 の実行例（１）: じかに　SQL 文を実行させる
			String sql = "SELECT Id FROM User WHERE Name=?";
			//
			// (1-1) SQL発行のためのステートメントを取得
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			// (1-2) SQL の実行
			ResultSet User=ps.executeQuery();
			//ResultSet User = smt.executeQuery("SELECT * FROM User");
			while( User.next() ) {
				UserBean tmpSb = new UserBean();
				tmpSb.setId( User.getInt("Id") );
				// StudentBean インスタンスを配列型リスト（ArrayList）に追加していく
				list.add(tmpSb);
			}
			if(list.size()<1) {
			//
			// SQL文 の実行例（１）: じかに　SQL 文を実行させる
			//
			// (1-1) SQL発行のためのステートメントを取得
			Statement smt = con.createStatement();
			// (1-2) SQL の実行
			int count = smt.executeUpdate(
					"INSERT INTO User(Id,Name,Pass) VALUES"
					+ "(" + id + ",'" + name + "','" + pass + "')" );
			// (1-3)  コネクションを閉じる
			smt.close();
			con.close();
			// (1-4) executeUpdate の返り値には、更新された行数になるので
			// これを利用して、ただしく実行されたか確認
			if (count>0) 
				return true;
			else 
				return false;
			}
			else
				return false;
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace();  // データベース接続の例外エラー表示
			return false;
		}
	}
	
	// ======================================================
	//  データベースからの削除
	//     setId() してから実行する
	// ======================================================
	public boolean deleteRecord() {
		
		try {
			// データベースへのコネクションを取得
			Connection con = DBM.getUserConnection();
			//
			// SQL文 の実行例（２）: 間接的に　SQL 文を実行させる
			//
			// (2-1) SQL文の骨格を用意しておく（？部分が可変になる）
			String sql = "DELETE FROM Student Where Id=?";
			//  (2-2)  プリペアード（あらかじめ準備された）ステートメントを取得する
			PreparedStatement ps = con.prepareStatement(sql);
			//  (2-3) ステートメントの該当位置に値をセットする
			//     文字列の場合には ps.setString() を使う
			ps.setInt(1,id);
			// (2-4) SQLを実行する
			int count = ps.executeUpdate();
			// (2-5) コネクションを閉じる
			ps.close();
			con.close();			
			// (2-6) executeUpdate の返り値には、更新された行数になるので
			// これを利用して、ただしく実行されたか確認
			if (count>0) 
				return true;
			else 
				return false;
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace(); // データベース接続の例外エラー表示
			return false;
		}
	}
	
	// =============================
	//  データベースから一覧を取得
	// =============================
	public ArrayList<UserBean> getUserRecords() {

		ArrayList<UserBean> list = new ArrayList<UserBean>();

		try {
			// データベースへのコネクションを取得
			Connection con = DBM.getUserConnection();
			//
			// SQL文 の実行例（３）: executeQuery による検索
			//
			// (3-1) ステートメントを取得する
			Statement smt = con.createStatement();
			// (3-2) SELECT を実行して、結果を ResultSet に入れる
			ResultSet rs = smt.executeQuery("SELECT * FROM User");
			// (3-3) リストに結果を格納する
			//  かなら以下のループが必要である（少なくとも１回は resultSetクラスの
			//   next メソッドを呼ばないと最初の要素を取り出すことができない）
			//    SELECT 文から返された複数行の結果を１行ずつ取得するループである。
			while( rs.next() ) {
				UserBean tmpSb = new UserBean();
				tmpSb.setId( rs.getInt("Id") );
				tmpSb.setName( rs.getString("Name") );
				tmpSb.setPass( rs.getString("Pass") );
				// StudentBean インスタンスを配列型リスト（ArrayList）に追加していく
				list.add(tmpSb);
			}
			// (3-4) コネクションを閉じる
			rs.close();
			smt.close();
			con.close();
			// (3-5) 最後に、得られた配列型リスト（各要素は StudentBeanインスタンス）を結果を返す
			return list;
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace(); // データベース接続の例外エラー表示
			return null;
		}
	}
	public ArrayList<UserBean> Login() {
		
		try {
			// データベースへのコネクションを取得
			Connection con = DBM.getUserConnection();
			//
			ArrayList<UserBean> list = new ArrayList<UserBean>();
			// SQL文 の実行例（１）: じかに　SQL 文を実行させる
			String sql = "SELECT * FROM User WHERE Name=? AND Pass=?";
			//
			// (1-1) SQL発行のためのステートメントを取得
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,pass);
			// (1-2) SQL の実行
			ResultSet User=ps.executeQuery();
			//ResultSet User = smt.executeQuery("SELECT * FROM User");
			while( User.next() ) {
				UserBean tmpSb = new UserBean();
				tmpSb.setId( User.getInt("Id") );
				tmpSb.setName( User.getString("Name") );
				tmpSb.setPass( User.getString("Pass") );
				// StudentBean インスタンスを配列型リスト（ArrayList）に追加していく
				list.add(tmpSb);
			}
			// (1-3)  コネクションを閉じる
			User.close();
			con.close();
			// (1-4) executeUpdate の返り値には、更新された行数になるので
			// これを利用して、ただしく実行されたか確認
			return list;
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace();  // データベース接続の例外エラー表示
			return null;
		}
	}
	
public ArrayList<UserBean> GetExamFoods() {
		
		try {
			Random rnd=new Random();
			// データベースへのコネクションを取得
			Connection con = DBM.getUserConnection();
			//
			ArrayList<UserBean> list = new ArrayList<UserBean>();
			// SQL文 の実行例（１）: じかに　SQL 文を実行させる
			String sql = "SELECT * FROM Foods WHERE Id=? or Id=? or Id=? or Id=? or Id=? or Id=? or Id=? or Id=? or Id=? or Id=?";
			//
			// (1-1) SQL発行のためのステートメントを取得
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,rnd.nextInt(14));
			ps.setInt(2,rnd.nextInt(14));
			ps.setInt(3,rnd.nextInt(14));
			ps.setInt(4,rnd.nextInt(14));
			ps.setInt(5,rnd.nextInt(14));
			ps.setInt(6,rnd.nextInt(14));
			ps.setInt(7,rnd.nextInt(14));
			ps.setInt(8,rnd.nextInt(14));
			ps.setInt(9,rnd.nextInt(14));
			ps.setInt(10,rnd.nextInt(14));
			
			ResultSet User=ps.executeQuery();
			// (1-2) SQL の実行
			//ResultSet User = smt.executeQuery("SELECT * FROM User");
			while( User.next() ) {
				UserBean tmpSb = new UserBean();
				tmpSb.setName( User.getString("Name") );
				tmpSb.setPass( User.getString("Signals") );
				System.out.println("a\n");
				// StudentBean インスタンスを配列型リスト（ArrayList）に追加していく
				list.add(tmpSb);
			}
			// (1-3)  コネクションを閉じる
			User.close();
			con.close();
			// (1-4) executeUpdate の返り値には、更新された行数になるので
			// これを利用して、ただしく実行されたか確認
			return list;
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace();  // データベース接続の例外エラー表示
			return null;
		}
	}
	
public ArrayList<UserBean> DataAdd() {
		
		try {
			// データベースへのコネクションを取得
			Connection con = DBM.getUserConnection();
			//
			ArrayList<UserBean> list = new ArrayList<UserBean>();
			// SQL文 の実行例（１）: じかに　SQL 文を実行させる
			String sql = "INSERT INTO DATA(Id,Exp) VALUES(?,?)";
			//
			// (1-1) SQL発行のためのステートメントを取得
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,pass);
			// (1-2) SQL の実行
			ResultSet User=ps.executeQuery();
			//ResultSet User = smt.executeQuery("SELECT * FROM User");
			while( User.next() ) {
				UserBean tmpSb = new UserBean();
				tmpSb.setId( User.getInt("Id") );
				// StudentBean インスタンスを配列型リスト（ArrayList）に追加していく
				list.add(tmpSb);
			}
			// (1-3)  コネクションを閉じる
			User.close();
			con.close();
			// (1-4) executeUpdate の返り値には、更新された行数になるので
			// これを利用して、ただしく実行されたか確認
			return list;
		} catch (Exception e) {
			// もし、上の try ブロックで例外などのエラーが起きた場合
			// ここに自動的にジャンプしてくる
			e.printStackTrace();  // データベース接続の例外エラー表示
			return null;
		}
	}
}
