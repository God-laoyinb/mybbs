<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/static/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<style type="text/css">
.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
</style>
<title>产品分类</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 版块管理 <span class="c-gray en">&gt;</span> 版块列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<table class="table">
	<tr>
		<td width="200" class="va-t"><ul id="treeDemo" class="ztree"></ul></td>
	</tr>
</table>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/page/admin/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript">
var tmp;
var setting = {
	view: {
		addHoverDom:addHoverDom,
		removeHoverDom:removeHoverDom,
		dblClickExpand: false,
		showLine: true,
		//fontCss:{'color':'black','font-weight':'bold'},//字体
		selectedMulti: false
	},
	check:{
		chkStyle:'checkbox',
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId:-1
		}
	},
	
	
	
	edit: {
        enable: true,
        showRemoveBtn:showRemoveBtn,
        showRenameBtn:showRenameBtn,
        
        removeTitle:"删除",
        renameTitle:"编辑"
    },
	callback: {
		beforeRename:function(treeId,treeNode,newName,isCancel){   //重命名前的 操作
				if(newName.length < 1){
		            layer.msg('名称不能少于1个字符！',{icon:0,time:1500});
		            return false;
		        }
		        tmp = treeNode.name;
		        if(sortIsExist(treeNode.name)){ //如果节点已经存在
		        	return false;
		        } 
		        return true;
		},
		onRename:function(e,treeId,treeNode,isCancel){   //重命名
			//treeNode 要重命名的节点
			//tmp 修改前的节点名称
			//treeNode.name 修改后的节点名称
			if(treeNode.name==tmp){ //如果名称点击编辑后未修改
				return false;
			}
			$.ajax({
				url:"${pageContext.request.contextPath}/BlockManage/renameBlock",
				type:"get",
				dataType:"json",
				contentType:"json/application",
				data:{"blockName":tmp,"newName":treeNode.name},
				success:function(data){
					if(data.res==1) {
						 layer.msg('重命名成功',{icon:1,time:1500});
					} else {
						 layer.msg('重命名失败，该名称已存在',{icon:0,time:1500});
						 location.reload();
					}
				},
				error:function(){
					alert('error');	
				}
			});
		},
		beforeRemove:function(treeId, treeNode) {  //删除前的操作
			if(confirm('确定删除吗?')){
				return true;
			} else {
				return false;	
			}
		},
		onRemove:function(e,treeId,treeNode) {  //删除节点
			$.ajax({
				url:"${pageContext.request.contextPath}/BlockManage/deleteBlock",
				type:"get",
				dataType:"json",
				contentType:"json/application",
				async:false,
				data:{"blockName":treeNode.name},
				success:function(data){
					if(data.res==1) {
						layer.msg('删除成功',{icon:1,time:1500});
					} else {
						layer.msg('删除失败',{icon:1,time:1500});
					}
				},
				error:function(){
					alert('error');	
				}
			});
		}
	}
};

var zNodes =[
	{ id:1, pId:0, name:"所有分类", open:true, noRemoveBtn:true,noRenameBtn: true},
];
		
var code;
		
function showCode(str) {
	if (!code) code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}
		
$(document).ready(function(){
	init(); // 数据初始化
	var t = $("#treeDemo");
	t = $.fn.zTree.init(t, setting, zNodes);
	demoIframe = $("#testIframe");
	var zTree = $.fn.zTree.getZTreeObj("tree");
});
function init() { //分类列表数据初始化 
	var blockList = ${blockList};
	$.each(blockList,function(index,block){
		var id = 1;
		var pId = 1;
		id = id*10+index+1;
		pId = pId;
		zNodes[zNodes.length] = { id:id, pId:pId, name:block.blockName,open:true,noAddBtn:true}; //添加一级节点
	});
}
function addHoverDom(treeId,treeNode){ //加号图标点击事件
	if(treeNode.noAddBtn != undefined && treeNode.noAddBtn){ //如果该节点的noAddBtn属性不为空或者false
		return false;
	} else {
		var newCount = findNodes(treeNode);  // 子节点数
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='添加' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);  
		////////////////////
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"); //zTree整个树对象
			var newNode; //新增节点
			if(treeNode.id.toString().length==1){ //如果是添加的是一级分类
				var newCount1 = findNodes(treeNode);
				newNode={id:(10 + newCount1),name:'新版块',noAddBtn:true};	
				var newNodeName = treeNode.name;	
				//ajax操作,把数据写入数据库
				$.ajax({   
					url:"${pageContext.request.contextPath}/BlockManage/addBlock",
					type:"get",
					data:{"blockName":"新版块"},
					dataType:"json",
					
					success:function(data){
						if(data.res==1){
							//新增操作应该写在这里
							zTree.addNodes(treeNode,newNode);  //添加节点        treeNode根节点   newNode新增的节点
							layer.msg('新增分类成功',{icon:1,time:1500});	
						} else {
							layer.msg('新增版块失败!请先完善上一个新版块',{icon:0,time:2000});
						}
					},
					error:function(){
						alert('error');
					}
				});
			
		}
		}); 
			
		
		///////////////
		return true;
	}
}
function removeHoverDom(treeId, treeNode) { //删除按钮事件
	$("#addBtn_"+treeNode.tId).unbind().remove(); 
};
function findNodes(treeNode) { //子节点数
	    var count;
	    /*判断是不是父节点,是的话找出子节点个数，加一是为了给新增节点*/
	    if(treeNode.isParent) {
	        count = treeNode.children.length+1;
	    } else {
	        /*如果不是父节点,说明没有子节点,设置为1*/
	        count = 1;
	    }
	    return count;
}

function showRemoveBtn(treeId, treeNode){ //显示删除按钮
	//获取节点所配置的noRemoveBtn属性值
	if(treeNode.noRemoveBtn != undefined && treeNode.noRemoveBtn){
		return false;
	} else {
		return true;
	}
}
function showRenameBtn(treeId, treeNode){ //显示重命名按钮
	//获取节点所配置的noRemoveBtn属性值
	if(treeNode.noRenameBtn != undefined && treeNode.noRenameBtn){
		return false;
	} else {
		return true;
	}
}
function NodeIsExist(name){  //判断节点是否已经存在
	var res = true;
	$.each(zNodes,function(index,value){
		if(index<3) {
			alert(value.name);	
		}
		
		if(value.name==name) {
			res = false;
		}
	});
	return res;
}
function sortIsExist(sortName){ //查找是否存在同名的节点
	var res = false;
	$.each(zNodes,function(index,value){
		if(value.id.toString().length==3){
			if(value.name==sortName) {
				res = res;
			}	
		}
	});
	return res;
}
</script>
</body>
</html>