//帮助中心
function show_help(obj) {
	var d = dialog({
		align: 'left',
		content: '这里是帮助中心',
		quickClose: true
	});
	d.show(obj);
	// 	showTopContentWindow("show_help", "http://www.baidu.com", "帮助中心", 375 / 2, 667 / 2);
}