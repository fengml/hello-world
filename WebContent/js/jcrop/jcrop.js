//定义一个全局api，这样操作起来比较灵活
        var api = null;
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.readAsDataURL(input.files[0]);
                reader.onload = function (e) {
                    $('#cutimg').removeAttr('src');
                    $('#cutimg').attr('src', e.target.result);
                    $("#crop_preview").attr('src',e.target.result)
                    api = $.Jcrop('#cutimg', {
                        //setSelect: [ 20, 20, 200, 200 ],
                        aspectRatio: 1,
                        onSelect: showPreview, //updateCoords,
                        onChange: showPreview
                    });
                };
                if (api != undefined) {
                    api.destroy();
                }
            }
            function updateCoords(obj) {
                $("#x").val(obj.x);
                $("#y").val(obj.y);
                $("#w").val(obj.w);
                $("#h").val(obj.h);
            };
            function showPreview(coords){
            	
            	 $("#x").val(coords.x);
                 $("#y").val(coords.y);
                 $("#w").val(coords.w);
                 $("#h").val(coords.h);
            	
            	if(parseInt(coords.w) > 0){
    				//计算预览区域图片缩放的比例，通过计算显示区域的宽度(与高度)与剪裁的宽度(与高度)之比得到
    				var rx = $("#preview_box").width() / coords.w; 
    				var ry = $("#preview_box").height() / coords.h;
    				console.log(rx+" ; "+ry);
    				//通过比例值控制图片的样式与显示
    				$("#crop_preview").css({
    					width:Math.round(rx *  $("#cutimg").width()) + "px",	//预览图片宽度为计算比例值与原图片宽度的乘积
    					height:Math.round(rx *  $("#cutimg").height()) + "px",	//预览图片高度为计算比例值与原图片高度的乘积
    					marginLeft:"-" + Math.round(rx * coords.x) + "px",
    					marginTop:"-" + Math.round(ry * coords.y) + "px"
    				});
    			}
            }
            
            
        }
        
 /////////////////////////////////////////
        
        
        
        
        
        