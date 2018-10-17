package cn.zhyonk.common.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SetNgrokConfig {

	public static JSONObject get(String url) throws Exception {
		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpGet httpPost = new HttpGet(url);
		httpPost.setHeader("Content-type", "application/json; charset=utf-8");
		CloseableHttpResponse response = client.execute(httpPost);
		// 获取结果实体
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "UTF-8");
		JSONObject jsonObject = JSONObject.parseObject(string);
		return jsonObject;
	}

	public static void main(String[] args) throws Exception {
		String url83 = "";
		String url84 = "";
		String url = "http://127.0.0.1:4040/api/tunnels";
		JSONObject object = get(url);
		JSONArray array = object.getJSONArray("tunnels");
		for (Object tunnel : array) {
			JSONObject jo = (JSONObject) tunnel;
			String name = jo.getString("name");
			String proto = jo.getString("proto");
			String public_url = jo.getString("public_url");
			if (name.contains("8083") && proto.equals("http")) {
				url83 = public_url;
			}
			if (name.contains("8084") && proto.equals("http")) {
				url84 = public_url;
			}
		}
		
		System.out.println("<<8083>>: "+url83);
		System.out.println("<<8084>>: "+url84);
		System.out.println("<<index>>: "+url83+"/wechat/index");
		System.out.println("<<接口URL>>: "+url83+"/xia-wechat/wechat/portal");
		System.out.println("<<网页授权>>: "+url83.replaceAll("http://", ""));
		
		String path = "C:\\eclips\\zhyonk\\xia\\xia-wechat\\src\\main\\resources\\xia.properties";
		Properties properties = new Properties();
		properties.load(new FileReader(path));
		properties.setProperty("xia.baseURL", url83 + "/xia-wechat");
		properties.setProperty("vue.index", url84);
		properties.setProperty("vue.url", url84);
		properties.store(new FileWriter(new File(path)), "Update data");
		String readText = IOUtils.readText(path);
		IOUtils.writeText(path, readText.replaceAll("\\\\", ""), false);
		String authjs = IOUtils.readText("C:\\Users\\Administrator\\Desktop\\1\\xia\\src\\aouth\\auth.js");
		String[] split = authjs.split("\n");
		for (int i = 0; i < split.length; i++) {
			if (split[i].contains("const SERVER_URL")) {
				split[i] = "const SERVER_URL = '"+url83+"/xia-wechat'";
			}
		}
		IOUtils.fileWriteMultiLine(split, "C:\\Users\\Administrator\\Desktop\\1\\xia\\src\\aouth\\auth.js");
		String httpjs = IOUtils.readText("C:\\Users\\Administrator\\Desktop\\1\\xia\\src\\axios\\https.js");
		String[] split2 = httpjs.split("\n");
		for (int i = 0; i < split2.length; i++) {
			if (split2[i].contains("axios.defaults.baseURL")) {
				split2[i] = "axios.defaults.baseURL = '"+url83+"/xia-wechat'";
			}
		}
		IOUtils.fileWriteMultiLine(split2, "C:\\Users\\Administrator\\Desktop\\1\\xia\\src\\axios\\https.js");
	}

}
