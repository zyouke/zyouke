var $, tab, skyconsWeather;
layui.config({
	base : "/html/js/"
}).use(["bodyTab",'form', 'element', 'layer', 'jquery' ], function() {
	var form = layui.form(), layer = layui.layer, element = layui.element();
	$ = layui.jquery;
	tab = layui.bodyTab({
		openTabNum : "10", //最大可打开窗口数量
		url : "/html/lib/json/navs.json" //获取菜单json地址
	});
	//渲染左侧菜单
	tab.render();
	//隐藏左侧导航
	$(".hideMenu").click(function(){
		$(".layui-layout-admin").toggleClass("showMenu");
	});
	// 添加新窗口
	$("body").on("click",".layui-nav .layui-nav-item a",function(){
		//如果不存在子级
		if($(this).siblings().length == 0){
			addTab($(this));
			$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
		}
		$(this).parent("li").siblings().removeClass("layui-nav-itemed");
	})
})

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}
