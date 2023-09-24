package morse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class DBSelectServlet
 */
@WebServlet("/DBSelectServlet")
public class UserReferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReferenceServlet() {
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

		//  データベースで SELECT を実行して結果を受け取る
		// 結果は Java のリスト型で返される（studentBean のリストになっている）　
		ArrayList<UserBean> list = sb.getUserRecords();		
		
		// 結果のページをディスパッチする
		 if ( list != null && ! list.isEmpty() ) {
		     dispatcher = request.getRequestDispatcher("UserDataResult.jsp");
		 } else {
		     dispatcher = request.getRequestDispatcher("studentDB-failed.jsp");
		 }
		 
		 // 配列型リスト（ArrayList）のインスタンスを request に詰めて
		 // 次に遷移する JSP に渡す
		 // JSP側 ではキーワード "studentBeanList" をつかってインスタンスを取り出す
		 //  二重引用符の中の単語はクラス名である必要はなく、自分で自由に決めてよい
		 request.setAttribute("UserBeanList", list);

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
