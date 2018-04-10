var consoleCSS = "color:red;-webkit-background-clip: text;font-size:1rem;";
console.log('%c ☺ 感谢您访问远方的灯塔！', consoleCSS);

$(function() {
	$(".collapseButton").click(function() {
		var obj = $(this);
		var parent = obj.parent().parent();
		var childs = parent.find(".xContent");
		childs.toggle(200);
	})

	var is_showSidbar = false;
	$(".r-hide").click(function() {
		if(is_showSidbar) {
			$(this).find("a").html("隐藏侧栏");
			is_showSidbar = false;
		} else {
			$(this).find("a").html("显示侧栏");
			is_showSidbar = true;
		}
	});

	function secondToDate(second) {
		if(!second) {
			return 0;
		}
		var time = new Array(0, 0, 0, 0, 0);
		if(second >= 365 * 24 * 3600) {
			time[0] = parseInt(second / (365 * 24 * 3600));
			second %= 365 * 24 * 3600;
		}
		if(second >= 24 * 3600) {
			time[1] = parseInt(second / (24 * 3600));
			second %= 24 * 3600;
		}
		if(second >= 3600) {
			time[2] = parseInt(second / 3600);
			second %= 3600;
		}
		if(second >= 60) {
			time[3] = parseInt(second / 60);
			second %= 60;
		}
		if(second > 0) {
			time[4] = second;
		}
		return time;
	}

	function setTime() {
		var create_time = Math.round(new Date(Date.UTC(2011, 9, 10, 0, 0, 0))
			.getTime() / 1000);
		var timestamp = Math.round((new Date().getTime() + 8 * 60 * 60 * 1000) / 1000);
		currentTime = secondToDate((timestamp - create_time));
		currentTimeHtml = currentTime[0] + '年' + currentTime[1] + '天' +
			currentTime[2] + '时' + currentTime[3] + '分' + currentTime[4] +
			'秒';
		document.getElementById("timeShow").innerHTML = currentTimeHtml;
	}
	setInterval(setTime, 1000);
	$("#search-close").click(function() {
		$("#search-main").attr("style", "display:none");
	})

	// 评论小工具
	$('.comment-form-comment').click(function() {
		$(".editor_tools").show(400);
	});
})

jQuery(function() {
	jQuery("#smiley").hide(200);
	jQuery("#comment-smiley").click(function() {
		jQuery("#smiley").toggle(200);
	});
});
jQuery(function() {
	jQuery("#fontcolor").hide(200);
	jQuery("#font-color").click(function() {
		jQuery("#fontcolor").toggle(200);
	});
});
jQuery(function() {
	jQuery("#smiley").hide();
	jQuery("#comment").click(function() {});
});
jQuery(function() {
	jQuery("#fontcolor").hide();
	jQuery("#comment").click(function() {});
});
jQuery(function() {
	function addEditor(a, b, c) {
		if(document.selection) {
			a.focus();
			sel = document.selection.createRange();
			c ? sel.text = b + sel.text + c : sel.text = b;
			a.focus()
		} else if(a.selectionStart || a.selectionStart == '0') {
			var d = a.selectionStart;
			var e = a.selectionEnd;
			var f = e;
			c ? a.value = a.value.substring(0, d) + b + a.value.substring(d, e) + c + a.value.substring(e, a.value.length) : a.value = a.value.substring(0, d) + b + a.value.substring(e, a.value.length);
			c ? f += b.length + c.length : f += b.length - e + d;
			if(d == e && c) f -= c.length;
			a.focus();
			a.selectionStart = f;
			a.selectionEnd = f
		} else {
			a.value += b + c;
			a.focus()
		}
	}

	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if(month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if(strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate +
			" " + date.getHours() + seperator2 + date.getMinutes() +
			seperator2 + date.getSeconds();
		return currentdate;
	}

	editorDates = getNowFormatDate();
	var g = document.getElementById('comment') || 0;
	var h = {
		strong: function() {
			addEditor(g, '<b>', '</b>')
		},
		em: function() {
			addEditor(g, '<i>', '</i>')
		},
		del: function() {
			addEditor(g, '<del>', '</del>')
		},
		underline: function() {
			addEditor(g, '<u>', '</u>')
		},
		quote: function() {
			addEditor(g, '<blockquote>', '</blockquote>')
		},
		ahref: function() {
			var a = prompt('请输入链接地址', 'http://');
			var b = prompt('请输入链接内容', '');
			if(a) {
				addEditor(g, '<a href="' + a + '">' + b + '</a>', '')
			}
		},
		sign: function() {
			addEditor(g, '每日签到，生活更精彩 ~：' + getNowFormatDate(), '')
		},
		code: function() {
			addEditor(g, '<pre>', '</pre>')
		},
	};
	window['SIMPALED'] = {};
	window['SIMPALED']['Editor'] = h
});

function lazy_load_avatar() {
	$("img.avatar").lazyload({
		effect: "fadeIn",
		failure_limit: 70
	});
}

function go_anchor() {
	$('html,body').animate({
		scrollTop: $("#ajax_comment_goto_box").offset().top
	}, 800);
}

function comment_ajax_pushState(href) {
	var stateObj = {
		foo: "bar"
	};
	history.pushState(stateObj, "page 2", href);
}
$(document).on('click', '.comment-navigation a.page-numbers', function() {
	var href = $(this).attr("href");
	comment_ajax_pushState(href);
	$(".comment-list").html(" ");
	$(".pagination").html(" ");
	$(".comment-loading-box").show(50);
	$.post(href, {}, function(data) {
		$(".comment-list").html($(data).find(".comment-list"));
		$('.pagination').html($(data).find(".pagination"));
		$(".comment-loading-box").hide(50);
		lazy_load_avatar();
		go_anchor();
	}).error(function() {
		layer.msg("评论加载失败，请联系博主修复~", {
			offset: 'b'
		})
	});
	return false;
});

$(document).keydown(function() {
	if(event.ctrlKey == true && event.keyCode == 83) {
		console.log("You enter Ctrl+S!");
		return false;
	}
});

$(document).on("click", "#click_author_qq_info", function() {
	var qq = $("#author_qq").val();
	if($.trim(qq) != "") {
		$.post("https://www.terwergreen.com/go/api/qq_info.php", {
			qq: qq
		}, function(data) {
			$("#author").val(data[qq][6]);
			$("#email").val(qq + "@qq.com");
		}, 'json').error(function(e) {
			layer.msg('QQ信息请求错误');
		});
	} else {
		layer.msg('QQ号码不能为空');
	}
});