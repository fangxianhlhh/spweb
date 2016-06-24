package com.inf.system.utiles.weixin;

import java.util.HashMap;
import java.util.Map;

public class WxinTokenSingleton {
    //缓存accessToken 的Map  ,map中包含 一个accessToken 和 缓存的时间戳
    //当然也可以分开成两个属性咯
    private Map<String, String> map = new HashMap<>();
 
    private WxinTokenSingleton() {
    }
 
    private static WxinTokenSingleton single = null;
 
    // 静态工厂方法
    public static WxinTokenSingleton getInstance() {
        if (single == null) {
            single = new WxinTokenSingleton();
        }
        return single;
    }
 
    public Map<String, String> getMap() {
        return map;
    }
 
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    
    public static WxinTokenSingleton getSingle() {
		return single;
	}

	public static void setSingle(WxinTokenSingleton single) {
		WxinTokenSingleton.single = single;
	}
 
    
    
    
 
}