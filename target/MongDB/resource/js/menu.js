(function($,text){
	$.fn.menuCom = function( options ){
		/*text = {"个人":[{"name":"流程发起","child":"","imgpath":"psnStartFlow.png"},
			           {"name":"待办流程","child":"","imgpath":"myTask_db.png"},
			           {"name":"我的申请","child":"","imgpath":"psnMyApplication.png"},
			           {"name":"待办任务","child":"","imgpath":"handleTask_db.png"},
			           {"name":"我的日程","child":"","imgpath":"myMagSchedule.png"},
			           {"name":"我的去向","child":"","imgpath":"myGoOut.png"},
			           {"name":"我的考勤","child":[{"name":"考勤统计","imgpath":"myAttendReports.png"},{"name":"考勤记录","imgpath":"myAttendInfo.png"},{"name":"我的定位","imgpath":"attendLocation.png"}],"imgpath":"psnMyApplication.png"},
			           {"name":"我的邮件","child":[{"name":"我的邮件","imgpath":"imailManage.png"}],"imgpath":"imailManage.png"},
			           {"name":"我的借款","child":"","imgpath":"myLoanList.png"},
			           {"name":"我的证件","child":"","imgpath":"myCertificate.png"},
			           {"name":"我的资产","child":"","imgpath":"myAssets.png"}],
			  "通知":[{"name":"新闻通知","child":[{"name":"通知公告","imgpath":"inform.png"},{"name":"会议纪要","imgpath":"meetingInform.png"},{"name":"图片新闻","imgpath":"picNews.png"},{"name":"行业新闻","imgpath":"industryNews.png"}],"imgpath":"newsInform.png"},
					   {"name":"知识管理","child":[{"name":"产品知识","imgpath":"productKnow.png"},{"name":"技术知识","imgpath":"technology.png"},{"name":"质量知识","imgpath":"qualityKnow.png"},{"name":"营销知识","imgpath":"saleKnow.png"},{"name":"管理知识","imgpath":"manageKnow.png"},{"name":"培训资料","imgpath":"trainMaterial.png"}],"imgpath":"knowledge.png"},
					   {"name":"企业文化","child":[{"name":"优秀员工","imgpath":"excellentEmp.png"},{"name":"员工情怀","imgpath":"empFeelings.png"},{"name":"规章制度","imgpath":"rules.png"},{"name":"大事记","imgpath":"bigEvents.png"}],"imgpath":"culture.png"},
					   {"name":"会议管理","child":[{"name":"我的会议","imgpath":"myMeetingList.png"},{"name":"会议一栏","imgpath":"meetingView.png"},{"name":"新增会议","imgpath":"meetingAdd.png"},{"name":"会议管理","imgpath":"meetingManage.png"},{"name":"会议室","imgpath":"meetingRoomMag.png"}],"imgpath":"meetingMag.png"},
					   {"name":"员工去向","child":[{"name":"员工去向","imgpath":"goOutList.png"},{"name":"去向管理","imgpath":"goOutAdmin.png"}],"imgpath":"goOut.png"},
					   {"name":"通讯录","child":[{"name":"内部通讯录","imgpath":"corpContact.png"},{"name":"公共通讯录","imgpath":"publicContact.png"},{"name":"通讯录管理","imgpath":"contactMag.png"}],"imgpath":"contact.png"},
					   {"name":"投票调查","child":[{"name":"投票调查","imgpath":"myVotePorject.png"},{"name":"投票管理","imgpath":"votePorject.png"}],"imgpath":"vote.png"},
					   {"name":"互动论坛","child":[{"name":"BBS论坛","imgpath":"forumItemList.png"},{"name":"我的发帖","imgpath":"myBBSPostsOfReplu.png"}],"imgpath":"bbsManager.png"}
					 ],
			     "流程":[{"name":"流程审批","imgpath":"workflowMenu.png","child":[{"name":"流程发起","imgpath":"startFlow.png"},{"name":"待办流程","imgpath":"handleTask.png"},{"name":"已办流程","imgpath":"listFlow.png"},{"name":"我的申请","imgpath":"myApplication.png"},{"name":"我的关注","imgpath":"myProcessAttention.png"},{"name":"我的收藏","imgpath":"myProcessCollection.png"},{"name":"知会流程","imgpath":"listFlowCc.png"},{"name":"流程委托","imgpath":"commissionFlow.png"}]},
			    	 {"name":"公文管理","imgpath":"document.png","child":[{"name":"代收公文","imgpath":"toRecvDocument.png"},{"name":"已收公文","imgpath":"recvedDocument.png"},{"name":"待办公文","imgpath":"toHandleDocument.png"},{"name":"已办公文","imgpath":"handledDocument.png"},{"name":"发起公文","imgpath":"startDocument.png"},{"name":"公文查询","imgpath":"queryDocument.png"}]},
			    	 {"name":"统计报表","imgpath":"reports.png","child":[{"name":"统计报表","imgpath":"reportsSelect.png"}]}],
			     "文档":[{"name":"文档中心","imgpath":"docMag.png","child":""}],
			     "报告":[{"name":"报告管理","imgpath":"planReportMag.png","child":[{"name":"我的报告","imgpath":"myWorkReport.png"},{"name":"报告查询","imgpath":"selectUserWorkReport.png"},{"name":"待阅报告","imgpath":"workReportCheck.png"}]},
			    	 {"name":"任务管理","imgpath":"taskMag.png","child":[{"name":"发起任务","imgpath":"startTask.png"},{"name":"待办任务","imgpath":"myTask.png"},{"name":"已办任务","imgpath":"completeTask.png"},{"name":"知会任务","imgpath":"viewTask.png"}]},
			    	 { "name":"日程安排","imgpath":"scheduleMag.png","child":[{"name":"工作日程","imgpath":"mySchedule.png"},{"name":"部门日程","imgpath":"orgSchedule.png"}]}
			         ],
			     "项目":[{"name":"合同管理","imgpath":"proContract.png","child":[{"name":"合同信息","imgpath":"proContractApprove.png"}]},
				        {"name":"项目管理","imgpath":"promanage.png","child":[{"name":"我的项目","imgpath":"myProManage.png"},{"name":"项目信息","imgpath":"projectInfo.png"},{"name":"项目一览","imgpath":"projectView.png"}]},
		           		{"name":"财务管理","imgpath":"proFinance.png","child":[{"name":"收款计划","imgpath":"proReceivable.png"},{"name":"付款计划","imgpath":"proReceivable.png"},{"name":"收款单","imgpath":"proReceipt.png"},{"name":"付款单","imgpath":"proPayment.png"},{"name":"开票单","imgpath":"proSaleInvoice.png"},{"name":"收票单","imgpath":"proPurInvoice.png"}]},
				        {"name":"仓库管理","imgpath":"proWarehouse.png","child":[{"name":"出入库","imgpath":"proInventoryRecord.png"},{"name":"库存报表","imgpath":"proInventoryReport.png"},{"name":"库存盘点","imgpath":"proInventoryCheck.png"}]}
						],
			     "资产":[{"name":"物品管理","imgpath":"commodityMg.png","child":[{"imgpath":"inventoryRecord.png","name":"出入库"},{"imgpath":"inventoryReport.png","name":"库存报表"},{"imgpath":"inventoryCheck.png","name":"库存盘点"}]},
			           {"name":"资产管理","imgpath":"asset.png","child":[{"imgpath":"qryAssets.png","name":"资产查询"},{"imgpath":"assetsAdmin.png","name":"资产管理"}]},
			           {"name":"车辆管理","imgpath":"carManage.png","child":[{"imgpath":"carBooking.png","name":"车辆预定"},{"imgpath":"carAppliction.png","name":"我的申请"},{"imgpath":"carUseManage.png","name":"使用管理"},{"imgpath":"carInfo.png","name":"车辆信息"},{"imgpath":"carMaintain.png","name":"车辆保养"},{"imgpath":"carAccident.png","name":"车辆事故"},{"imgpath":"carAnnualServey.png","name":"车辆年检"},{"imgpath":"carFilling.png","name":"车辆加油"},{"imgpath":"carInsurance.png","name":"车辆投保"},{"imgpath":"carPeccancy.png","name":"车辆违章"},{"imgpath":"expiredCar.png","name":"到期车辆"}]},
			           {"name":"图书管理","imgpath":"book.png","child":[{"imgpath":"bookQry.png","name":"图书查询"},{"imgpath":"bookBorrow.png","name":"借阅查询"},{"imgpath":"bookAdmin.png","name":"图书管理"}]}
					],
			     "人事":[{
			     		"name":"人事管理","imgpath":"psFilesManage.png","child":[{"name":"人事档案","imgpath":"psFiles.png"},{"name":"合同管理","imgpath":"contractMag.png"},{"name":"转正管理","imgpath":"turnPositive.png"},{"name":"离职管理","imgpath":"dismissions.png"},{"name":"人事调动","imgpath":"staffTransferMag.png"},{"name":"奖惩管理","imgpath":"rewardPenaltyMag.png"},{"name":"员工关怀","imgpath":"staffcareMag.png"},{"name":"证件管理","imgpath":"certificateMag.png"}]},
			           {"name":"考勤管理","imgpath":"attendAdmin.png","child":[{"name":"考勤统计","imgpath":"attendReports.png"},{"name":"考勤记录","imgpath":"attendInfoMag.png"},{"name":"考勤定位","imgpath":"attendLocation.png"},{"name":"请假管理","imgpath":"attendOutMag.png"},{"name":"加班结余","imgpath":"overTimeAdmin.png"},{"name":"年假管理","imgpath":"annualLeaveTree.png"}]},
			           {"name":"薪资社保","imgpath":"hrSalary.png","child":[{"name":"我的薪资","imgpath":"hrMySalary.png"},{"name":"薪资查询","imgpath":"hrSalaryQry.png"},{"name":"薪资报表","imgpath":"hrSalaryReport.png"},{"name":"薪资发放","imgpath":"hrSalaryPay.png"},{"name":"社保查询","imgpath":"hrSecurityQry.png"}]},
			           {"name":"绩效考核","imgpath":"assess2.png","child":[{"name":"我的绩效","imgpath":"myAssess.png"},{"name":"绩效查询","imgpath":"assessQuery.png"},{"name":"考核评分","imgpath":"assessScore.png"},{"name":"考核任务","imgpath":"assess2Task.png"}]},
			           {"name":"培训管理","imgpath":"train.png","child":[{"name":"培训计划","imgpath":"trainPlan.png"},{"name":"培训项目","imgpath":"trainProject.png"},{"name":"培训资源","imgpath":"trainResource.png"},{"name":"采课记录","imgpath":"trainPick.png"}]},
			           {"name":"人力分析","imgpath":"personnelAnalysis.png","child":[{"name":"人力结构","imgpath":"structureAnalysis.png"},{"name":"流动分析","imgpath":"transferAnalysis.png"},{"name":"离职分析","imgpath":"dismissionAnalysis.png"}]}
					],
			     "客户":[{"name":"客户信息","child":"","imgpath":"custManage.png"},
			     		{"name":"联系人","child":"","imgpath":"crmContactManage.png"},
			     		{"name":"销售机会","child":"","imgpath":"saleProject.png"},
			     		{"name":"联系记录","child":"","imgpath":"visitHis.png"},
			     		{"name":"合同信息","child":"","imgpath":"crmContractManage.png"},
			     		{"name":"收款计划","child":"","imgpath":"proReceivable.png"},
			     		{"name":"收款信息","child":"","imgpath":"crmReceiptManage.png"},
			     		{"name":"发票信息","child":"","imgpath":"crmInvoiceManage.png"}],
			     "采购":[{"name":"供应商","imgpath":"purProduct.png","child":""},
			           {"name":"采购合同","imgpath":"purOrder.png","child":""},
			           {"name":"收款计划","imgpath":"proPayable.png","child":""},
			           {"name":"收款信息","imgpath":"purPayment.png","child":""},
			           {"name":"收票信息","imgpath":"purInvoice.png","child":""}]
			};*/
		
		if(options.text == undefined){
			return null;
		}
		console.log(text);
		for ( var a in text) {
			if(a == options.text){
				text = text[a];
				break;
			}
		}
		var _h = "<ul>";
		for ( var a in text) {//a 0,1,2,3,4,5,6
			var b = false;
			if(text[a].child instanceof Array)
			{
				_h += "<li class=\"show-child\">";
				b = true;
			}else{
				_h += "<li>";
			}	
			_h += "<a><div><img alt='' src="+text[a].imgpath+">"+text[a].name;
			if(b)
			{
				_h += "<span class=\"accordion-img\"></span>";
			}	
			_h += "</div></a>";
			if(b)
			{
				_h += "<ul style='display:none;'>";
				for ( var v in text[a].child) {
					_h += "<li class='child-no-display'><a><div>";
					_h += "<img alt='' src="+text[a].child[v].imgpath+" />"+text[a].child[v].name;
					_h += "</div></a></li>";
				}
				_h += "</ul>";
			}
		}
		_h +="</li></ul>";
		
		options = $.extend({
			callback : function(s){
				return s;
			}
		},options);
		//把得到的结果回调
		options.callback({_h:_h,text:options.text});
		text = _da;
	}
})(jQuery,_da);