package com.inf.base.system.aop;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.socket.WebSocketSession;

public class SystemContent {

	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	
	private static Map<String, WebSocketSession> wssMap = new Hashtable<String, WebSocketSession>();
	
	public static Map<String, WebSocketSession> getWssMap() {
		return wssMap;
	}
	public static void putWebSocketSession(String sessionId, WebSocketSession webSocketSession) {
		wssMap.put(sessionId, webSocketSession);
	}
	public static void removeWebSocketSession(String sessionId) {
		wssMap.remove(sessionId);
	}
	
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) requestLocal.get();
	}
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) responseLocal.get();
	}
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
	public static HttpSession getSession() {
		return (HttpSession) ((HttpServletRequest) requestLocal.get()).getSession();
	}
	
}
