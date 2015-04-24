XIDialog = {
	alert : function(arg) {
		if (typeof arg == 'string') {
			arg = {'title' : '提示', 'msg' : arg};
		}
		var ui = this.html();
		ui = ui.replace("{title}",arg.title?arg.title:"");
		ui = ui.replace("{message}",arg.msg);
		ui = ui.replace("{cancel}","");
		var w = arg.width?arg.width:250;
		var h = arg.height?arg.height:100;
		ui = ui.replace("{width}",""+w);
		ui = ui.replace("{height}",""+h);
		ui = ui.replace("{contentHeight}",""+(h-78))
		$.blockUI({message:ui,css:{border:0,background:"none"}});
	},
	confirm : function(arg) {
		this.confirmCallback = arg.confirm;
		var ui = this.html();
		ui = ui.replace("{title}",arg.title?arg.title:"");
		ui = ui.replace("{message}",arg.msg);
		var w = arg.width?arg.width:250;
		var h = arg.height?arg.height:100;
		ui = ui.replace("{cancel}",'<a href="#" onclick="XIDialog._cancel();return false;">取 消</a>');
		ui = ui.replace("{width}",""+w);
		ui = ui.replace("{height}",""+h);
		ui = ui.replace("{contentHeight}",""+(h-78))
		$.blockUI({message:ui,css:{border:0,background:"none"}});
	},
	_confirm : function() {
		$.unblockUI();
		if(this.confirmCallback){
			this.confirmCallback(true);
			this.confirmCallback = null;
		}
	},
	_cancel : function() {
		$.unblockUI();
		if(this.confirmCallback){
			this.confirmCallback(false);
			this.confirmCallback = null;
		}
	},
	html : function() {
	    var linesFun = function(){
		/*<div id="msgShowDiv_global" class="CPM" style="width1:{width}px;height:{height}px">
		  <div class="CPMtitle ov_fl">
		    <div class="CPM-title1"></div>
		    <div class="CPM-titlecn ov_fl">
		      <h5>{title}</h5>
		      <p><a href="#" onclick="XIDialog._cancel()"></a></p>
		    </div>
		    <div class="CPM-title2"></div>
		  </div>
		  <div class="CPM-content1 ov_fl">
		    <div class="CPM-content2">
		      <div class="CPMcn ov_fl">
		        <div class="CPM-cn" style="height:{contentHeight}px">{message}</div>
		        <div class="CPM-cnbutton"><a href="#" onclick="XIDialog._confirm();return false;">确 定</a>&nbsp;{cancel}</div>
		      </div>
		    </div>
		  </div>
		  <div class="CPMbottom">
		    <div class="CPM-bottom1"></div>
		    <div class="CPM-bottom2"></div>
		    <div class="CPM-bottom3"></div>
		  </div>
		</div>*/
	    }
	    var lines = new String(linesFun);
	    return lines.substring(lines.indexOf("/*") + 2,
                lines.lastIndexOf("*/"));
	}
}



