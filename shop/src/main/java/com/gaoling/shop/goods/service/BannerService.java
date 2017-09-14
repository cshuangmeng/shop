package com.gaoling.shop.goods.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

import net.sf.json.JSONObject;

@Service
public class BannerService extends CommonService {
	public Result loadWKBanners(Integer index) {
		List<JSONObject> result = getSonDicts("wk" + index + "_top_banner").stream().map(r -> {
			JSONObject json = JSONObject.fromObject(r.get("value"));
			json.put("img", AppConstant.OSS_CDN_SERVER + json.get("img"));
			return json;
		}).collect(Collectors.toList());
		return putResult(result);
	}

}
