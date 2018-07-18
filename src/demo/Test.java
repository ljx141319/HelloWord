package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {
	
	
	 public static String getAuth(String ak, String sk) {
	        // 获取token地址
	        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
	        String getAccessTokenUrl = authHost
	                // 1. grant_type为固定参数
	                + "grant_type=client_credentials"
	                // 2. 官网获取的 API Key
	                + "&client_id=" + ak
	                // 3. 官网获取的 Secret Key
	                + "&client_secret=" + sk;
	        try {
	            URL realUrl = new URL(getAccessTokenUrl);
	            // 打开和URL之间的连接
	            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.err.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String result = "";
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	            /**
	             * 返回结果示例
	             */
	            System.err.println("result:" + result);
	            JSONObject json = JSONObject.parseObject(result);
	            String access_token = json.getString("access_token");
	            return access_token;
	        } catch (Exception e) {
	            System.err.printf("获取token失败！");
	            e.printStackTrace(System.err);
	        }
	        return null;
	    }

	public static String match() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
		try {

			byte[] bytes1 = FileUtil.readFileByBytes("F:\\zj1.jpg");
			byte[] bytes2 = FileUtil.readFileByBytes("F:\\zj2.jpg");
			String image1 = Base64Util.encode(bytes1);
			String image2 = Base64Util.encode(bytes2);

			List<Map<String, Object>> images = new ArrayList<>();

			Map<String, Object> map1 = new HashMap<>();
			map1.put("image", image1);
			map1.put("image_type", "BASE64");
			map1.put("face_type", "LIVE");
			map1.put("quality_control", "LOW");
			map1.put("liveness_control", "NONE");

			Map<String, Object> map2 = new HashMap<>();
			map2.put("image", image2);
			map2.put("image_type", "BASE64");
			map2.put("face_type", "LIVE");
			map2.put("quality_control", "LOW");
			map2.put("liveness_control", "NONE");

			images.add(map1);
			images.add(map2);

			String param = JSON.toJSONString(images);

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = getAuth("phOMdy1NWzZDY8u2rMKoflC0", "HCm7AUPsFGIXrS1k3DbpyOAo90VFk6FB ");

			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Test.match();
	}

}
