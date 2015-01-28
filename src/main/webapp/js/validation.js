/**
 * JQuery plugin for Input validation on addComputer page
 */
function checkName() {
	if ($("#name").val().length > 0 && $("#name").val().length < 256) {
		$("#name").parent().removeClass("has-error ");
		$("#name").parent().addClass("has-success");
		$("#name").next().remove();
		$("#name")
		.after(
		'<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
		$('#submit').prop('disabled', false);
	} else {
		$("#name").parent().removeClass("has-success");
		$("#name").parent().addClass("has-error");
		$("#name").next().remove();
		$("#name")
		.after(
		'<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
		$('#submit').prop('disabled', true);
	}
}

function checkDate(argument) {
	var reg = new RegExp("^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$");
	if ($("#" + argument).val().length > 0 && reg.test($("#" + argument).val())) {
		console.log("length positive and val complies with regex");
		$("#" + argument).parent().removeClass("has-warning ");
		$("#" + argument).parent().addClass("has-success");
		$("#" + argument).parent().removeClass("has-error");
		$("#" + argument).next().remove();
		$("#" + argument)
		.after(
		'<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
		$('#submit').prop('disabled', false);
		
	if(!reg.test($("#" + argument).val()) || parseInt($("#" + argument).val().split("-")[0])<1970 || parseInt($("#" + argument).val().split("-")[0])>2038){
		console.log(parseInt($("#" + argument).val().split("-")[0]));
		$("#" + argument).parent().removeClass("has-success");
		$("#" + argument).parent().removeClass("has-warning");
		$("#" + argument).parent().addClass("has-error");
		$("#" + argument).next().remove();
		$("#" + argument)
		.after(
		'<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
		$('#submit').prop('disabled', true);
	}	}
	else {
	$("#" + argument).parent().removeClass("has-success");
	$("#" + argument).parent().addClass("has-warning");
	$("#" + argument).parent().removeClass("has-error");
	$("#" + argument).next().remove();
	$("#" + argument)
	.after(
	'<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>');
	$('#submit').prop('disabled', false);
}
}

function checkCompany() {
	if ($("#companyId").val() != "null") {
		$("#companyId").parent().removeClass("has-warning ");
		$("#companyId").parent().addClass("has-success");
		$("#companyId").next().remove();
		$("#companyId")
		.after(
		'<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
	} else {
		$("#companyId").parent().removeClass("has-success");
		$("#companyId").parent().addClass("has-warning");
		$("#companyId").next().remove();
		$("#companyId")
		.after(
		'<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>');
	}
}
