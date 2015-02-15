package com.xr.xuritask.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ILoginService {
	Map<String, String> LoginAuthen(String merchant_No,String pwd,HttpServletRequest request);

	Map<String, String> fristPage(String operType, String merchanId, String userid);

}
