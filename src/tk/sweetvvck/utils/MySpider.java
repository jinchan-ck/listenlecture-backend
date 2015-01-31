/**
 * 
 */
package tk.sweetvvck.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.baidu.bae.api.fetchurl.BaeFetchurl;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 * @author 程科
 * 
 */
public class MySpider {

  	private static Logger logger = Logger. getLogger("name");
	public static List<String> getUrl(String baseUrl) throws IOException {
      	URL url2 = new URL(baseUrl);
		HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		Document doc = null;
		if(conn.getResponseCode() == 200){
			InputStream ins = conn.getInputStream();
			doc = Jsoup.parse(ins, "UTF-8", "http://gaoyong.v6.jspzg.com/");
		}
		//Document doc = Jsoup.connect(baseUrl).get();
		List<String> urls = new ArrayList<String>();
		Elements eles = doc.select("td a");
		for (Element element : eles) {
			String url = element.attr("href");
          if (url.contains("lectureInfo.action?lectureId")){
				logger.log(Level.INFO, " 抓取到的讲座地址： " + url);
				urls.add("http://gaoyong.v6.jspzg.com/" + url);
          }
		}
		return urls;
	}
	
	public static List<InputStream> getInputs(String baseUrl){
		List<InputStream> ins = new ArrayList<InputStream>();
		
		List<String> urls = null;
		try {
			urls = getUrl(baseUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String str : urls) {
			URL url;
			try {
				url = new URL(str);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setConnectTimeout(5000);
				if(conn.getResponseCode() == 200){
					ins.add(conn.getInputStream());
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
		return ins;
	}

}
