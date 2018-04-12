$(document).ready(function() {
	$("#registerForm").validate({
		rules: {
			account: {
				required: true,
				minlength: 2,
				maxlength: 20,
				mobile: true
			},
			password: {
				required: true,
				rangelength: [8, 20]
			},
			captcha: {
				required: true
			}
		},
		messages: {
			account: {
				required: "请输入要注册的手机号",
				length: "手机号必须为11位",
				mobile: "手机号不合法"
			},
			password: {
				required: "请输入密码"
			},
			captcha: {
				required: "请输入验证码"
			}
		}
	});

	$("#btnNext").on("click", function() {
		var flag = $("#registerForm").valid();
		if(!flag) {
			// 没有通过验证
			return;
		}
		alert("短信验证码已经发送到尾号为" + $("#account").val().substring(7, 11) + "的手机，请查收");
		//验证短信验证码
		$.ajax({
			type: "POST",
			dataType: 'json',
			data: data,
			url: "common/sendSMS",
			success: function(json) {
				alert(json.message);
				$("#registerForm input[name='account']").val("");
				$("#registerForm input[name='password']").val("");
				$("#registerForm input[name='captcha']").val("");
				if(json.success) {
					alert("ok");
				}
			}
		});
	});

	$("#btnRegister").on("click", function() {
		//验证短信验证码
		var smsData = {
			smsCode: $("#smsCode").val()
		};
		alert(smsData);
		$.ajax({
			type: "POST",
			dataType: 'json',
			data: smsData,
			url: "/common/verifySMS",
			success: function(json) {
				alert(json.message);

				if(json.success) {
					alert("ok");
				}
			}
		});

		//提交注册信息
		var formData = $("#registerForm").serializeObject();
		alert(formData);
		return false;
		$.ajax({
			type: "POST",
			dataType: 'json',
			data: formData,
			url: "user/register",
			success: function(json) {
				alert(json.message);
				$("#registerForm input[name='account']").val("");
				$("#registerForm input[name='password']").val("");
				$("#registerForm input[name='captcha']").val("");
				if(json.success) {
					alert("ok");
				}
			}
		});
	});
});