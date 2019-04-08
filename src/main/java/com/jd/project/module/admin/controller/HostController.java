package com.jd.project.module.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jd.framework.config.ZabbixConfig;
import com.jd.framework.web.controller.BaseController;
import com.jd.framework.web.domain.Result;
import com.jd.project.module.admin.entity.HostEntity;
import com.jd.project.utils.HostGroupUtils;
import com.jd.project.utils.HostInterfaceUtils;
import com.jd.project.utils.HostUtils;
import com.jd.project.utils.ItemUtils;

@Controller
@CrossOrigin
@RequestMapping("/host")
@SuppressWarnings("all")
public class HostController extends BaseController {

	/**
	 * 查询物理服务器信息
	 */
	@PostMapping("/queryHost")
	@ResponseBody
	public Result queryHost(HostEntity hostEntity) {
		try {
			// 主机群主（物理服务器群组）
			Map<String, Object> gruopMap = new HashMap<>();
			JSONObject object = new JSONObject();
			object.put("name", ZabbixConfig.getPhysicalServer());
			gruopMap.put("filter", object);
			List<Map> groupList = HostGroupUtils.getHostGroup(gruopMap);
			// 调用物理主机群组下的主机信息
			Map<String, Object> map = new HashMap<>();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("ip", hostEntity.getIp());
			map.put("output", new String[] { "host", "hostid", "status", "name", "description", "available" });
			map.put("limit", 10);
			map.put("filter", jsonObject);
			map.put("search", hostEntity.getHost());
			map.put("groupids", groupList.get(0).get("groupid"));
			List<Map> list = HostUtils.getHost(map);
			if (null != list && list.size() > 0) {
				for (Map map2 : list) {
					Map<String, Object> paramsMap = new HashMap<>();
					paramsMap.put("hostids", map2.get("hostid"));
					List<Map> maps = HostInterfaceUtils.getHostInterface(paramsMap);
					map2.put("ip", maps != null ? maps.get(0).get("ip") : null);
				}
			}
			return success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return error("获取主机信息失败");
		}
	}

	/**
	 * 查询主机详情
	 * 
	 * @param hostId
	 * @return
	 */
	@GetMapping("/detail/{hostId}")
	@ResponseBody
	public Result getHostDetailByHostId(@PathVariable("hostId") String hostId) {
		try {
			Map<String, Object> map = new HashMap<>();
			JSONObject jsonObject = new JSONObject();
			map.put("output", "extend");
			map.put("hostids", hostId);
			return success(ItemUtils.getItem(map));
		} catch (Exception e) {
			e.printStackTrace();
			return error("获取主机详情信息失败");
		}
	}
}
