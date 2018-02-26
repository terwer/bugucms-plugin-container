package front.login;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.aspectj.util.FileUtil;
import org.junit.Test;

import front.util.HttpRequestUtil;

public class LoginControllerTest {
	// 请求服务项目地址
	private final String baseUrl = "http://127.0.0.1:8080/bugucms";

	@Test
	public void testGetLoanAgreementInfoList() {
		// 请求服务
		String requestType = "/hello.do";
		// 请求参数
		Map<String, String> requestMap = new HashMap<String, String>();
		String result = HttpRequestUtil.javaPost(baseUrl + requestType,
				requestMap);
		// System.out.println("result=" + result);
		// String expected = "ok";
		// Assert.assertEquals(expected, result);
		Assert.assertNotNull(result);
	}

	public static void main(String[] args) {
		// 读取
		String res = "";
		try {
			res = FileUtil.readAsString(new File("D:\\data.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(res);
	}
}
