package www.xiaoyang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EntityTest {
	public static void main(String[] args) {
		Map<String,Object> dataMap = new HashMap<String, Object>();
		String tableInfo = "{\"fnMenuName\":\"lcfq\",\"subclass\":\"流程测试表\",\"entityName\":\"FLOW_START\",\"remark\":\"个人-流程发起\",\"create\":\"1\",\"createtime\":\"2017-01-22 23:04:40.0\",\"aem_entityId\":\"1DEDER\",\"package_id\":\"11B1FB\"}";
		
		JSONObject tableObject = JSONObject.fromObject(tableInfo);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tableObject.put("nowDate", sf.format(new Date()));

		dataMap.put("ent", tableObject);
		
		System.out.println("实体信息:"+tableObject);
		
		String data = "[{\"TABLE_NAME\":\"FLOW_START\",\"PRIMARYKEYS\":\"YES\",\"REMARKS\":\"主键id\",\"TYPE_NAME\":\"INT\",";
		data += "\"IS_AUTOINCREMENT\":\"YES\",\"COLUMN_NAME\":\"id\",\"SOURCE_DATA_TYPE\":\"java.lang.Integer\"},{"; 
		data += "\"TABLE_NAME\":\"FLOW_START\",\"PRIMARYKEYS\":\"NO\",\"REMARKS\":\"学生姓名\",\"TYPE_NAME\":\"VARCHAR\",";
		data += "\"IS_AUTOINCREMENT\":\"NO\",\"COLUMN_NAME\":\"name\",\"SOURCE_DATA_TYPE\":\"java.lang.String\"}]";
		
		JSONArray dataArray = JSONArray.fromObject(data);
		
		for (int i = 0; i < dataArray.size(); i++) {
			System.out.println("属性列表："+ dataArray.get(i));
//			JSONObject dataObject = dataArray.getJSONObject(i);
//			System.out.println(dataObject.get("SOURCE_DATA_TYPE"));
		}
		
		System.out.println(dataMap.get("ent"));
		
		
		
	}

}
