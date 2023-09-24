package morse;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataSubmitServlet
 */
@WebServlet("/DataSubmitServlet")
public class DataSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataSubmitServlet() {
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
		
		// 文字コードを UTF-8 として扱う
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("UserName");
		sb.sendId(id);	
		dispatcher = request.getRequestDispatcher("index.jsp");
		 
		 // 配列型リスト（ArrayList）のインスタンスを request に詰めて
		 // 次に遷移する JSP に渡す
		 // JSP側 ではキーワード "studentBeanList" をつかってインスタンスを取り出す
		 //  二重引用符の中の単語はクラス名である必要はなく、自分で自由に決めてよい
		 request.setAttribute("UserId", id);

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
