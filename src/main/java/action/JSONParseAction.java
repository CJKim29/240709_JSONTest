package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class JSONParseAction
 */
@WebServlet("/json_parse.do")
public class JSONParseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// {"name":"일길동","age":30,"hobby":["독서","낚시"]}
		
		/*
		String json_str = "";
		
		JSONObject json = new JSONObject("{\"name\":\"일길동\",\"age\":30,\"hobby\":[\"독서\",\"낚시\"]}");
		
		String	name = json.getString("name");
		int		age  = json.getInt("age");
		
		System.out.printf("이름:%s 나이:%d\n", name, age);
		
		JSONArray hobbyArray = json.getJSONArray("hobby");
		for(int i = 0; i < hobbyArray.length(); i++) { // i = 0 1
			String hobby = hobbyArray.getString(i);
			System.out.printf("취미 %d : %s\n", i+1, hobby);
		}
		*/
		
		
		try {
			String str_url = "http://localhost:8080/2024_0709_JSONTest/person.jsp";
			URL url = new URL(str_url);
			
			//이용하는 이유 : 요청시 헤더정보 전달(인증키)
			URLConnection urlConn = url.openConnection();
			
			InputStream is = urlConn.getInputStream();
			
			// line단위로 읽어온다
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				
				String data = br.readLine(); //line단위로 읽어온다
				if(data==null) break;
				
				sb.append(data);
			}//end:while()
			
			System.out.println(sb.toString());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//Dispatcher형식으로 호출
		String forward_page = "result_json.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}