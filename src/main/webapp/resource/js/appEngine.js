var appEngine = {
	loadBusClass : function(dom){
		$(dom).combotree('loadData', $("#dataMoudleTree").tree("getRoots"));
	},
	selectData : function(node,dom){
		if(node.children != undefined && typeof node.children == "object")$(dom).combotree("clear");
	}
}