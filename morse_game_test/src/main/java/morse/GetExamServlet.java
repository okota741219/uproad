package morse;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetExamServlet
 */
@WebServlet("/GetExamServlet")
public class GetExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetExamServlet() {
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
		Random rnd=new Random();
		
		// 文字コードを UTF-8 として扱う
		request.setCharacterEncoding("UTF-8");
		// int id = Integer.parseInt(request.getParameter("id"));
		int a=rnd.nextInt(15);
		//int id=2;
		sb.setId(a);
		
		 // フォームの入力データを文字列としてサーブレットで受け取る
		ArrayList<UserBean> list = sb.GetExamFoods();	
		
		// 結果のページをディスパッチする
		     dispatcher = request.getRequestDispatcher("game.jsp");
		 
		 // 配列型リスト（ArrayList）のインスタンスを request に詰めて
		 // 次に遷移する JSP に渡す
		 // JSP側 ではキーワード "studentBeanList" をつかってインスタンスを取り出す
		 //  二重引用符の中の単語はクラス名である必要はなく、自分で自由に決めてよい
		 request.setAttribute("FoodsList", list);

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
