<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/css/shop-homepage.css" rel="stylesheet">	
<script>
$('#choppingCart').attr("hidden", "hidden");
</script>

<input hidden="hidden" value="${itemId}" name="itemId">
<h2>Продукт № ${itemId}</h2>
<form class="form-inline" id="itemForm">
<div class="row">
	<div class= col-md-3>
		<input placeholder="brend" name="brend" class="form-control">
		<input placeholder="model" name="model" class="form-control">
		<input placeholder="price" name="price" class="form-control">	
	</div>
	<div class= col-md-8>
		<div class="row">
			<div class="col-md-5">
				<select id="stringProperty" class="form-control">
						<option>
							str props
						</option>
					</select>
			</div>
			<div class="col-md-3">
				<input placeholder="str value" id="stringValue" class="form-control">
			</div>
			<div class="col-md-1">
				<button type="button" class="btn btn-primary" value="add" id="addString" class="form-control">save</button>
			</div>
			
		</div>
		<div class="row">
			<div class="col-md-5">
				<select id="digitProperty" class="form-control">
						<option value="0">
							digit props
						</option>
					</select>
			</div>
			<div class="col-md-3">
				<input placeholder="digit value" id="digitValue" class="form-control">
			</div>
			<div class="col-md-1">
				<button type="button" class="btn btn-primary" value="add" id="addDigit" class="form-control">save</button>
			</div>
		</div>	
	</div>
</div>
	<div class="row">
		<div class="col-md-6">
			<input name="file" type="file"  id="file" />
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<button type="button" class="btn btn-primary"  id="submit" class="form-control">submit</button>
		</div>
	</div>

</form>

<div class="row" id="conteiner">
</div>


<script>
$(function() {
	var id = $('input[name="itemId"]').val();
	var brend = $('input[name="brend"]');
	var model = $('input[name="model"]');
	var price = $('input[name="price"]');
	var digitProperty = $('#digitProperty');
	var stringProperty = $('#stringProperty');
	var stringValue = $('#stringValue');
	var digitValue = $('#digitValue');
	var stringValueArray;
	var digitValueArray;
	var categoryId;
	var files;
	var filename = $('input[type=file]');
	
	findOne();
function findOne(){
	$.ajax({
		url:'/adminProductPageGetOne/'+ id, 
		type:'GET',
		success:function(itemsJson){
			
			
			
			brend.val(itemsJson.brend);
			model.val(itemsJson.model);
			price.val(itemsJson.price);
			categoryId = itemsJson.categoryId;
			stringValueArray = itemsJson.stringValue;
			digitValueArray = itemsJson.digitValue;
			var div = $('#conteiner');
			div.empty();
			
			var div1 = $('<div>',{
				class:'col-md-4'
			});
			
			var divImg = $('<div>',{
				class:'img-thumbnail'
			});
			
			var img =$('<img>',{
				src:"/images/item/" + itemsJson.path,
				width:"250px"
			})
			
			divImg.append(img);
			div1.append(divImg);
			
			var div2 = $('<div>',{
				class:"col-md-6"
			})
			
			var pDiscription = $('<p>',{
				text: itemsJson.brend + " "+ itemsJson.model
			})
			
			var pPrice = $('<p>',{
				text: "ціна - "+itemsJson.price 
			})
			
			div2.append(pDiscription,pPrice);
			
			
			var div3 = $('<div>',{
				class:'col-md-12'
			});
			
			var h3 =$('<h3>',{
				text:"характеристики"
			})
			
			div3.append(h3);			
			
			stringProperty.empty()
			for (var i = 0; i < itemsJson.stringValue.length; i++) {
				var option = $('<option>',{
					text:itemsJson.stringValue[i].stringProperty,
					value:itemsJson.stringValue[i].id
				})
				
				stringProperty.append(option);
				
				var div4 = $('<div>',{
					class:"row"
				});
				
				var divName = $('<div>',{
					class:'col-md-2',
					text:itemsJson.stringValue[i].stringProperty
				})
				
				var divValue = $('<div>',{
					class:'col-md-2',
					text:itemsJson.stringValue[i].name
				})
				
				div4.append(divName,divValue);
				div3.append(div4);
				}
			
			for (var i = 0; i < stringValueArray.length; i++) {
				if(stringProperty.val() == stringValueArray[i].id){
					stringValue.val(stringValueArray[i].name);				
				}
			}
			
			
			
			digitProperty.empty();
			for (var i = 0; i < itemsJson.digitValue.length; i++) {
				var option = $('<option>',{
					text:itemsJson.digitValue[i].digitProperty,
					value:itemsJson.digitValue[i].id
				})
				
				digitProperty.append(option);
				
				var div4 = $('<div>',{
					class:"row"
				});
				
				var divName = $('<div>',{
					class:'col-md-2',
					text:itemsJson.digitValue[i].digitProperty
				})
				
				var divValue = $('<div>',{
					class:'col-md-2',
					text:itemsJson.digitValue[i].value
				})
				
				for (var i = 0; i < digitValueArray.length; i++) {
					if(digitProperty.val() == digitValueArray[i].id){
						digitValue.val(digitValueArray[i].value);				
					}
				}		
				div4.append(divName,divValue);
				div3.append(div4);
				
			}
				div.append(div1,div2,div3);
		}
	})
}
	
	$('#stringProperty').change(function(){		
		for (var i = 0; i < stringValueArray.length; i++) {
			if(stringProperty.val() == stringValueArray[i].id){
				stringValue.val(stringValueArray[i].name);				
			}
		}
	});

	$('#digitProperty').change(function(){		
		for (var i = 0; i < digitValueArray.length; i++) {
			if(digitProperty.val() == digitValueArray[i].id){
				digitValue.val(digitValueArray[i].value);				
			}
		}
	});
	
	$('#addString').click(function(){
		for (var i = 0; i < stringValueArray.length; i++) {
			if(stringProperty.val() == stringValueArray[i].id){
				stringValueArray[i].name = stringValue.val();
			}
		}
	});
	
	$('#addDigit').click(function(){
		for (var i = 0; i < digitValueArray.length; i++) {
			if(digitProperty.val() == digitValueArray[i].id){
				digitValueArray[i].value = digitValue.val();
			}
		}
	});
	
	$('input[type=file]').change(function(event){
		  files = event.target.files;
		});
	
	
	function uploadFiles(event){
		  event.stopPropagation(); // Stop stuff happening
		    event.preventDefault(); // Totally stop stuff happening
		// START A LOADING SPINNER HERE

		// Create a formdata object and add the files
		var data = new FormData();
		$.each(files, function(key, value){
		    data.append(key, value);
		});
		
		$.ajax({
		    url: '/item/image',
		    type: 'POST',
		    data: data,
		    cache: false,
		    processData: false, // Don't process the files
		    contentType: false, // Set content type to false as jQuery will tell the server its a query string request
		    success: function(data, textStatus, jqXHR){     
		      			console.log("  Success !!!");
		    },
		    error: function(jqXHR, textStatus, errorThrown){
		        console.log('ERRORS: ' + textStatus);
		    }
		})
		};
		
		
		$('#submit').click(function(event) {			
			uploadFiles(event);
		
			filename = $('input[type=file]').val();
			 if (filename.substring(3,11) == 'fakepath') {
		            filename = filename.substring(12);
			 }
			
			event.preventDefault();
	
			var json = JSON.stringify({
				itemId:id,
				brend:brend.val(),
				model:model.val(),
				price:price.val(),
				path:filename,
				categoryId:categoryId,
				stringValue:stringValueArray,				
				digitValue:digitValueArray					
				
			});	
				$.ajax({
					url:'/item',
					type:'PUT',
					data:json,
					contentType:"application/json",
					success:function(){
						findOne();
					}
				});
			
		});
	
	
	
});
</script>




