package com.xiaoyang.util.system;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyang.entity.system.JsonTreeData;

/** 
* @ClassName: TreeNodeUtil 
* 构建tree Util
*/
public class TreeNodeUtil {

	/**
	 * 父节点
	 * @param treeDataList
	 * @return
	 */
	public final static List<JsonTreeData> getfatherNode(List<JsonTreeData> treeDataList) {
        List<JsonTreeData> newTreeDataList = new ArrayList<JsonTreeData>();
        for (JsonTreeData jsonTreeData : treeDataList) {
            if("0".equals(jsonTreeData.getPid())) {
                //获取父节点下的子节点
                jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId(),treeDataList));
                jsonTreeData.setState("open");
                newTreeDataList.add(jsonTreeData);
            }
        }
        return newTreeDataList;
    }
	
	/**
	 * 获取子节点
	 * @param pid
	 * @param treeDataList
	 * @return
	 */
	 private final static List<JsonTreeData> getChildrenNode(String pid , List<JsonTreeData> treeDataList) {
	        List<JsonTreeData> newTreeDataList = new ArrayList<JsonTreeData>();
	        for (JsonTreeData jsonTreeData : treeDataList) {
	            if("0".equals(jsonTreeData.getPid()))  continue;
	            //这是一个子节点
	            if(jsonTreeData.getPid().equals(pid)){
	                //递归获取子节点下的子节点
	                jsonTreeData.setChildren(getChildrenNode(jsonTreeData.getId() , treeDataList));
	                newTreeDataList.add(jsonTreeData);
	            }
	        }
	        return newTreeDataList;
	    }
}
