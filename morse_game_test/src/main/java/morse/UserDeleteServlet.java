package morse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class DBDeleteServlet
 */
@WebServlet("/DBDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;

		//  ユーザ情報を処理する JavaBean をつくる
		UserBean sb = new UserBean();
		
		 // フォームの入力データを文字列としてサーブレットで受け取る
		 String id = request.getParameter("id");
		
		 // セッターメソッドでデータを JavaBean に入れる
		 //   （引数にあたえるときに型変換も行っている）
		 sb.setId( Integer.parseInt(id) );

		 //  データベースからの削除の実行
		 if ( sb.deleteRecord() ) {
		     dispatcher = request.getRequestDispatcher("UserDeleteSuccess.jsp");
		 } else {
		     dispatcher = request.getRequestDispatcher("studentDB-failed.jsp");
		 }
		 
		 // studentBean のインスタンスを request に詰めて
		 // 次に遷移する JSP に渡す
		 // JSP側 ではキーワード "studentBean" をつかってインスタンスを取り出す
		 //  二重引用符の中の単語はクラス名である必要はなく、自分で自由に決めてよい
		 request.setAttribute("UserBean", sb);

		 // 最後に JSP へ処理を遷移させる
		 dispatcher.forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
