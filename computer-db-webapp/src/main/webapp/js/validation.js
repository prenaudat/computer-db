/**
 * JQuery plugin for Input validation on add or edit Computer page
 */
function checkName() {
	if ($("#name").val().length > 0 && $("#name").val().length < 256) {
		$("#name").parent().removeClass("has-error ");
		$("#name").parent().addClass("has-success");
		$("#name").next().remove();
		$("#name")
				.after(
						'<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
	} else {
		$("#name").parent().removeClass("has-success");
		$("#name").parent().addClass("has-error");
		$("#name").next().remove();
		$("#name")
				.after(
						'<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
	}
}

function checkDate(argument, locale) {
	if (locale == "fr") {
		console.log("FRENCH")
		var reg = new RegExp("^[0-9]{2}[-]{1}[0-9]{2}[-]{1}[0-9]{4}$");
		if ($("#" + argument).val().length == 0) {
			$("#" + argument).parent().removeClass("has-success");
			$("#" + argument).parent().removeClass("has-error");
			$("#" + argument).parent().addClass("has-warning");
			$("#" + argument).next().remove();
			$("#" + argument)
					.after(
							'<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>');
		} else if (reg.test($("#" + argument).val())
				&& parseInt($("#" + argument).val().split("-")[2]) > 1970
				&& parseInt($("#" + argument).val().split("-")[2]) < 2038
				&& $("#" + argument).val().length == 10) {
			$("#" + argument).parent().removeClass("has-warning");
			$("#" + argument).parent().removeClass("has-error");
			$("#" + argument).parent().addClass("has-success");
			$("#" + argument).next().remove();
			$("#" + argument)
					.after(
							'<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
		} else {
			$("#" + argument).parent().removeClass("has-warning");
			$("#" + argument).parent().removeClass("has-success");
			$("#" + argument).parent().addClass("has-error");
			$("#" + argument).next().remove();
			$("#" + argument)
					.after(
							'<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
		}
	} else {
		var reg = new RegExp("^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$");
		if ($("#" + argument).val().length == 0) {
			$("#" + argument).parent().removeClass("has-success");
			$("#" + argument).parent().removeClass("has-error");
			$("#" + argument).parent().addClass("has-warning");
			$("#" + argument).next().remove();
			$("#" + argument)
					.after(
							'<span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>');
		} else if (reg.test($("#" + argument).val())
				&& parseInt($("#" + argument).val().split("-")[0]) > 1970
				&& parseInt($("#" + argument).val().split("-")[0]) < 2038
				&& $("#" + argument).val().length == 10) {
			$("#" + argument).parent().removeClass("has-warning");
			$("#" + argument).parent().removeClass("has-error");
			$("#" + argument).parent().addClass("has-success");
			$("#" + argument).next().remove();
			$("#" + argument)
					.after(
							'<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
		} else {
			$("#" + argument).parent().removeClass("has-warning");
			$("#" + argument).parent().removeClass("has-success");
			$("#" + argument).parent().addClass("has-error");
			$("#" + argument).next().remove();
			$("#" + argument)
					.after(
							'<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
		}
	}
}

function checkCompany() {
	if ($("#companyId").val() != "null" && $("#companyId").val() != "0" ) {
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

function checkButton(){
	console.log('checked button');
	if($("#name").parent().hasClass("has-success") && ($("#introduced").parent().hasClass("has-success") || $("#introduced").parent().hasClass("has-warning") ) &&( $("#discontinued").parent().hasClass("has-success") || $("#discontinued").parent().hasClass("has-warning"))&& ($("#companyId").parent().hasClass("has-success")|| $("#companyId").parent().hasClass("has-warning"))){
		$('#submit').prop('disabled', false);
	}else{
		$('#submit').prop('disabled', true);

	}
	
}