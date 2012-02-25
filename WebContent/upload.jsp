<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.vol.entity.*" %>
<% 
	User user = (User)session.getAttribute("user");
	if(user == null) {
		response.sendRedirect(request.getContextPath() + "/welcome.jsp");
	}
%>	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>file upload</title>
<link rel=stylesheet href="/vol/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css">
<link rel=stylesheet href="/vol/css/login.css" type="text/css">
<script type="text/javascript" src="/vol/js/jquery-1.6.2.min.js"></script> 
<script type="text/javascript" src="/vol/js/jquery-1.6.js"></script> 
<script type="text/javascript" src="/vol/js/jquery.timers.js"></script> 
<script type="text/javascript" src="/vol/plupload/js/plupload.full.js"></script>
<script type="text/javascript" src="/vol/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript">
// Convert divs to queue widgets when the DOM is ready
	$(function() {
	function log() {
		var str = "";

		plupload.each(arguments, function(arg) {
			var row = "";

			if (typeof(arg) != "string") {
				plupload.each(arg, function(value, key) {
					// Convert items in File objects to human readable form
					if (arg instanceof plupload.File) {
						// Convert status to human readable
						switch (value) {
							case plupload.QUEUED:
								value = 'QUEUED';
								break;

							case plupload.UPLOADING:
								value = 'UPLOADING';
								break;

							case plupload.FAILED:
								value = 'FAILED';
								break;

							case plupload.DONE:
								value = 'DONE';
								break;
						}
					}

					if (typeof(value) != "function") {
						row += (row ? ', ': '') + key + '=' + value;
					}
				});

				str += row + " ";
			} else { 
				str += arg + " ";
			}
		});
	}

	$("#uploader").pluploadQueue({
		// General settings
		runtimes: 'html5,gears,browserplus,silverlight,flash,html4',
		url: 'fileUpload.action',
		max_file_size: '500mb',
		chunk_size: '1mb',
		unique_names: true,

		// Resize images on clientside if we can
		resize: {width: 320, height: 240, quality: 90},

		// Specify what files to browse for
		filters: [
/* 			{title: "Image files", extensions: "jpg,gif,png"},
			{title: "Zip files", extensions: "zip"}, */
			{title: "Dat files", extensions: "dat"}
		],

		// Flash/Silverlight paths
		flash_swf_url: '/vol/plupload/js/plupload.flash.swf',
		silverlight_xap_url: '/vol/plupload/js/plupload.silverlight.xap',

		// PreInit events, bound before any internal events
		preinit: {
			Init: function(up, info) {
				log('[Init]', 'Info:', info, 'Features:', up.features);
			},

			UploadFile: function(up, file) {
				log('[UploadFile]', file);

				// You can override settings before the file is uploaded
				// up.settings.url = 'upload.php?id=' + file.id;
				// up.settings.multipart_params = {param1: 'value1', param2: 'value2'};
			}
		},

		// Post init events, bound after the internal events
		init: {
			Refresh: function(up) {
				// Called when upload shim is moved
				log('[Refresh]');
			},

			StateChanged: function(up) {
				// Called when the state of the queue is changed
				log('[StateChanged]', up.state == plupload.STARTED ? "STARTED": "STOPPED");
			},

			QueueChanged: function(up) {
				// Called when the files in queue are changed by adding/removing files
				log('[QueueChanged]');
			},

			UploadProgress: function(up, file) {
				// Called while a file is being uploaded
				log('[UploadProgress]', 'File:', file, "Total:", up.total);
			},

			FilesAdded: function(up, files) {
				// Callced when files are added to queue
				log('[FilesAdded]');

				plupload.each(files, function(file) {
					log('  File:', file);
				});
			},

			FilesRemoved: function(up, files) {
				// Called when files where removed from queue
				log('[FilesRemoved]');

				plupload.each(files, function(file) {
					log('  File:', file);
				});
			},

			FileUploaded: function(up, file, info) {
				// Called when a file has finished uploading
				log('[FileUploaded] File:', file, "Info:", info);
			},

			ChunkUploaded: function(up, file, info) {
				// Called when a file chunk has finished uploading
				log('[ChunkUploaded] File:', file, "Info:", info);
			},

			Error: function(up, args) {
				// Called when a error has occured

				// Handle file specific error and general error
				if (args.file) {
					log('[error]', args, "File:", args.file);
				} else {
					log('[error]', args);
				}
			}
		}
	});

	function getLog() {
		$.post("getValidator.action",function(data){
			if(data != "fail"){
				$('#result').val(data);
			}
    	});
	}
	
	$('body').everyTime('1s',getLog);
});


</script>
</head>
<body>
<div class="wrapper">
  <div class="main content clearfix">
  <div class="product-info mail">
  	<div class="product-headers">
  	<h1 class="greytext">&nbsp;Upload data and run validator</h1>
	<h2>&nbsp;&nbsp;Shows how to bind and use all available events.</h2>
	</div>
	</div>
	<div>
	<form id="uploadForm" method="post" action="submit.action">
		<div id="uploader" style="width: 665px; height: 330px;">You browser doesn't support upload.</div>
		<div>
		&nbsp;&nbsp;<input id="runVal" type="submit" value="run" class="g-button">
		&nbsp;&nbsp;<a style="font-size:12px" target="_top" href="<%=request.getContextPath()%>/download.jsp" id="link-forgot-passwd">
  		download output files
  		</a>
  		</div>
	</form>
	</div>
	<br/>
	<div>
	&nbsp;&nbsp;<strong>Notice:</strong>it is the last log before you run the validator.
	</div>
	<div>
		&nbsp;&nbsp;<textarea id="result" style="width: 650px; height: 550px; font-size: 11px" spellcheck="false"></textarea>
		<!--  <div id="log" class="test_box" contenteditable="true" style="width: 650px; height: 350px; font-size: 11px" spellcheck="false"><br /></div> 
		-->
	</div>
	</div>
	</div>
</body>
</html>