$(function(){	
	$.easy.navigation();
	$.easy.tooltip();
	$.easy.popup();
	$.easy.external();
	$.easy.rotate();
	$.easy.cycle();
	$.easy.forms();
	$.easy.showhide();
	$.easy.jump();
	$.easy.tabs();
	$.easy.accordion();	
});


//code for the edit profile page
$(document).ready(function(){

	$(".datepicker").datepicker({ altFormat: 'yyyy-mm-dd', disabled: true });
	
    if($.cookie('edit_profile_cookie')){
        $("ul.ul_submenu_list li.selected").removeClass("selected");
		$("#" + $.cookie('edit_profile_cookie')).parent().addClass("selected");
    }

	$("a.submenu_list").live("click", function(){
		$('.content').addClass('opaque');
	    $.cookie('edit_profile_cookie', $("ul.ul_submenu_list li.selected a").attr("id"));   
		$("ul.ul_submenu_list li.selected").removeClass("selected");
		$(this).parent().addClass("selected");
		$("div.tab_content").hide();
		$("." + $("ul.ul_submenu_list li.selected a").attr("id")).show();
		$.ajax({
			   type: "POST",
			   url: "/Users/editProfile", 
			   data: "ly=no&form=" + ($("ul.ul_submenu_list li.selected a").attr("id").indexOf("account")>-1?"account":"profile"),
			   success: function(msg){
			   		$('.content').html(msg);
			   		$('.content').removeClass('opaque');
			   		$(".datepicker").datepicker({ altFormat: 'dd-mm-yy', dateFormat: 'dd-mm-yy', disabled: true });
			   }
			 });
	        return false;
	});

	$("." + $("ul.ul_submenu_list li.selected a").attr("id")).show();
	$.cookie('edit_profile_cookie', $("ul.ul_submenu_list li.selected a").attr("id"));
	//alert($.cookie('edit_profile_cookie'));
});