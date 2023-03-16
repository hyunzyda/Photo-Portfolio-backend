(function(win, $){
	var $html = $("html");
	//반응형 웹의 중단점에 사용할 값을 객체 속성으로 등록
	var deviceSize = {
		pc:1009,
		tablet:801,
		mobile:800
	};

	function scrollShowHide(status){
		$html.css({"overflow-y":status});
		return $html.width();
	}

	var sc_w1 = scrollShowHide("hidden");
	var sc_w2 = scrollShowHide("scroll");
	var sc_w3 = sc_w1-sc_w2;
	if(sc_w3>0){
		deviceSize.pc = deviceSize.pc-sc_w3;
		deviceSize.tablet = deviceSize.tablet-sc_w3;
		deviceSize.mobile = deviceSize.mobile-sc_w3;
	}
	
	$(win).on("resize",function(){
		var w_size = $(win).width();

		if(w_size >= deviceSize.pc && !$("html").hasClass("pc")){
			$html.removeClass("mobile tablet").addClass("pc");
		}else if(w_size < deviceSize.pc  && !$("html").hasClass("tablet") && w_size >= deviceSize.tablet){
			$html.removeClass("mobile pc").addClass("tablet");
		}else if(w_size <= deviceSize.mobile && !$("html").hasClass("mobile")){
			$html.removeClass("tablet pc").addClass("mobile");
		}
	});
	//문서가 로딩될 때 resize이벤트 발생, 이때 설정한 중단점에 맞는 class값을 생성
	$(function(){
		$(win).trigger("resize");

		$(document).on("mouseover focus", ".pc #gnb>ul>li>a, .tablet #gnb>ul>li>a", gnbPlay);
		
		$(document).on("click", ".mobile #gnb>ul>li>a", gnbPlay);

		function gnbPlay(){
			var $ts = $(this);
			if ($("html").hasClass("mobile")){
				$("#gnb ul ul:visible").slideUp(300);
				$ts.next().stop(true, true).slideDown(300);
			}else{
				$("#gnb ul ul:visible").slideUp(300);
				$ts.next().stop(true, true).slideDown(300);
			}
		}
	});
}(window, jQuery));
