package com.tyilack.assist.util;/*
package com.giant.gre.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import com.core.UDefinedGet;
import com.core.UDefinedPost;
import com.giant.g1.gbot.aiml.service.Service;
import com.google.gson.Gson;
import com.utils.HttpUtil;

public class ServiceUtil {

	public static String postForm(String url, Map<String, String> params) {

		UDefinedPost post = new UDefinedPost(url);

		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		Set<Entry<String, String>> entrySet = params.entrySet();
		for (Entry<String, String> entry : params.entrySet()) {
			multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue());
		}
		// .setContentType(ContentType.MULTIPART_FORM_DATA);
		post.setEntity(multipartEntityBuilder.build());
		String result = "";
		try {
			result = new HttpUtil().execute(post);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
*/
