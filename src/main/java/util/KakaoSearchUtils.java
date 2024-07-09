package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import json.vo.KakaoLocalVo;

public class KakaoSearchUtils {
	
	public static List<KakaoLocalVo> searchJson(String query, String y, String x, int page, int size, int radius){
		
		List<KakaoLocalVo> list = new ArrayList<KakaoLocalVo>();
		
		try {
			//query Encoding
			query = URLEncoder.encode(query, "utf-8");
			
			String str_url = String.format("https://dapi.kakao.com/v2/local/search/keyword.json?query=%s&y=%s&x=%s&page=%d&size=%d&radius=%d", query, y, x, page, size, radius);
			
			String KAKAO_APIKEY = MyOpenAPIKey.Kakao.API_KEY;
			
			URL url = new URL(str_url);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();			
			urlConn.setRequestProperty("Authorization", KAKAO_APIKEY);
			urlConn.setRequestProperty("Content-Type", "application/json");
			urlConn.connect();
			
			InputStreamReader isr = new InputStreamReader(urlConn.getInputStream(), "utf-8");
			BufferedReader    br  = new BufferedReader(isr);
			
			StringBuilder     sb  = new StringBuilder();
			
			while(true) {
				
				String data = br.readLine();
				if(data==null)break;
				
				sb.append(data);
				
			}//end:while()
			
			//System.out.println(sb.toString());
			
			JSONObject json = new JSONObject(sb.toString());
			
			JSONArray localArray = json.getJSONArray("documents");
			
			for(int i = 0; i < localArray.length(); i++) {
				
				JSONObject local = localArray.getJSONObject(i);
				
				String	place_name			= local.getString("place_name");
				String	place_url			= local.getString("place_url");
				String	address_name		= local.getString("address_name");
				String	road_address_name	= local.getString("road_address_name");
				String	phone				= local.getString("phone");
				String	xx					= local.getString("x");
				String	yy					= local.getString("y");
				int		distance			= 0;
				
				try {
					distance = local.getInt("distance");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				//KakaoLocalVo포장
				KakaoLocalVo vo = new KakaoLocalVo();
				vo.setPlace_name(place_name);
				vo.setPlace_url(place_url);
				vo.setAddress_name(address_name);
				vo.setRoad_address_name(road_address_name);
				vo.setPhone(phone);
				vo.setX(xx);
				vo.setY(yy);
				vo.setDistance(distance);
				
				//ArrayList에 추가
				list.add(vo);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}//end:getJson()
	
	
	public static List<KakaoLocalVo> searchXml(String query, String y, String x, int page, int size, int radius){
		
		List<KakaoLocalVo> list = new ArrayList<KakaoLocalVo>();
	
		try {
			//query Encoding
			query = URLEncoder.encode(query, "utf-8");
			
			String str_url = String.format("https://dapi.kakao.com/v2/local/search/keyword.xml?query=%s&y=%s&x=%s&page=%d&size=%d&radius=%d", query, y, x, page, size, radius);
			
			String KAKAO_APIKEY = MyOpenAPIKey.Kakao.API_KEY;
			
			URL url = new URL(str_url);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();			
			urlConn.setRequestProperty("Authorization", KAKAO_APIKEY);
			urlConn.setRequestProperty("Content-Type", "application/xml");
			urlConn.connect();
			
			
			//XML Parsing
			SAXBuilder	builder = new SAXBuilder();
			Document	doc		= builder.build(urlConn.getInputStream());
			
			Element		root	= doc.getRootElement(); //<result>
			
			List<Element> document_list = root.getChildren("documents");
			
			for(Element document : document_list) {
				String place_name			= document.getChildText("place_name");
				String place_url			= document.getChildText("place_url");
				String address_name			= document.getChildText("address_name");
				String road_address_name	= document.getChildText("road_address_name");
				String phone				= document.getChildText("phone");
				String xx					= document.getChildText("x");
				String yy					= document.getChildText("y");
				
				int distance = 0;
				try {
					distance = Integer.parseInt(document.getChildText("distance"));
				} catch (Exception e) {
					// TODO: handle exception
				}
								
				KakaoLocalVo vo = new KakaoLocalVo();
				vo.setPlace_name(place_name);
				vo.setPlace_url(place_url);
				vo.setAddress_name(address_name);
				vo.setRoad_address_name(road_address_name);
				vo.setPhone(phone);
				vo.setX(xx);
				vo.setY(yy);
				vo.setDistance(distance);
				
				list.add(vo);
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	
	
}