<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	
	<style>
	#items{
		border-collapse:collapse; 
	}
	#items td{ 
		width:120px;
		padding:7px; border:#4e95f4 1px solid;
	}
	#items tr{
		background: #b8d1f3;
	}
	#items tr:nth-child(odd){ 
		background: #b8d1f3;
	}
	#items tr:nth-child(even){
		background: #dae5f4;
	}
	
</style>
</head>
<body>
<script>
$(function() {
	var itemId = 0;
	var stringValueId = 0;
	var digitValueId = 0;
	var digitProperty = $('#digitProperty');
	var stringProperty = $('#stringProperty');
	var stringArray = [];
	var digitArray = [];
	var files;
	var filename = $('input[type=file]');
	var currentPage = 0;
	var pageNambers;
	var pageSize = 6;
	var pageSort = "asc";
	
	function digitValue(id,value, digitProperty){
		return {
			id:id,
			value:value,
			digitProperty:digitProperty
		}
	}
		function stringValue(id, name, stringProperty){
		return {
			id:id,
			name:name,
			stringProperty:stringProperty
		}
	};
	function pageInfo(currentPage,pageNambers,pageSize,pageSort){
		return{
			currentPage:currentPage,
			pageNambers:pageNambers,
			pageSize:pageSize,
			pageSort:pageSort
		}
	};
	
	
	
	findAll();
	function findAll(){			
		if(sessionStorage.getItem("pagination") != null){
			var page = JSON.parse(sessionStorage.getItem("pagination"));
			console.log(window.location.pathname);
			currentPage = page.currentPage;
			pageNambers = page.pageNambers;
			pageSize = page.pageSize;
			pageSort = page.pageSort;
		}
		
		
		
		$.ajax({
			url:'/item/all/' + currentPage +'/' + pageSize + '/' + pageSort,
			type:'GET',
			success:function(itemsJson){
				
			 	currentPage = itemsJson.carentPage;
				pageNambers = itemsJson.pageNamber;
				pageSize = itemsJson.pageSize;
				
				var items = itemsJson.items;
				var div = $('#content');
				div.empty();
					var divRow = $('<div>',{
						class:"col-md-12"
					}		
					);
					var id = $('<div>',{
						class:"col-md-1"
					});
					var h4 = $('<h4>',{
						text:'Id'
					});
					id.append(h4);
					
					var img = $('<div>',{
						class:"col-md-2"
					});
					var h4 = $('<h4>',{
						text:'img'
					});
					img.append(h4);
					
					var brend = $('<div>',{
					class:"col-md-2"
					});
					var h4 = $('<h4>',{
						text:'brend'
					});
					brend.append(h4);
					
					var model = $('<div>',{
						class:"col-md-1"
					});
					var h4 = $('<h4>',{
						text:'model'
					});
					
					model.append(h4);
					
					var price = $('<div>',{
					class:"col-md-1"
					});
					var h4 = $('<h4>',{
						text:'price'
					});	
					price.append(h4);
					divRow.append(id,img,brend,model,price);
					div.append(divRow);
				
				for(var i = 0; i < items.length; i++){
				var div1 = $('<div>',{
					id:items[i].itemId,
					class:'col-md-12'
				});
					var id = $('<div>',{
						text:items[i].itemId,
						class:"col-md-1"
					});
					div1.append(id);
					
					var imgDiv = $('<div>',{
						class:"col-md-2",
					});
					var img = $('<img>',{
						src:'/images/item/' + items[i].path,
						class:'img-thumbnail',
						width:'100'
					});
					
					imgDiv.append(img);
					div1.append(imgDiv);
					
					var divBrend = $('<div>',{
						text:items[i].brend,
						class:"col-md-2"
					})
					div1.append(divBrend);
					
					var divModel = $('<div>',{
						text:items[i].model,
						class:'col-md-1'
					})
					div1.append(divModel);
				
					var divPrice = $('<div>',{
						text:items[i].price,
						class:'col-md-1'
					})
					
					div1.append(divPrice);
					
					var divDelUpdete = $('<div>',{
						class:'col-md-4'
					});
					
						var updateButton = $('<button>',{
							id:items[i].itemId,
							text:'update',
							value:'update',
							class:'updateDelete btn btn-primary',
							style:'margin-right:10px'
					});
						var deleteButton = $('<button>',{
							id:items[i].itemId,
							text:'delete',
							value:'delete',
							class:'updateDelete btn btn-primary',
							style:'margin-right:10px'
					});
						
						var a = $('<a>',{
							href:"adminProductPage/"+ items[i].itemId
						})
						
						var showButton = $('<button>',{
							name:"item_" + items[i].itemId,
							text:'show pege',
							class:'btn btn-primary',
					
							
					});	
						a.append(showButton);
						
						divDelUpdete.append(updateButton);
						divDelUpdete.append(deleteButton);
						divDelUpdete.append(a);
						
					div1.append(divDelUpdete);					
					div.append(div1);				
				}
				pagination();
				getPage();
				updateDelete();
			}
		});
	}
	
	
	function savePage(){
		var page = new pageInfo(currentPage,pageNambers,pageSize,pageSort);
		sessionStorage.setItem("pagination",JSON.stringify(page));	
	}
	
	function pagination(){
		var ul = $('#pagination');
		ul.empty();
		
		var li = $('<li>');
		var aBegin = $('<a>',{
			text:'<<',
			id:'page_'+ 0
		});
		if(currentPage != 0){
			var aBackOne = $('<a>',{
				text:'<',
				id:'page_'+ (currentPage - 1)
			});
		}else{
			var aBackOne = $('<a>',{
				text:'<',
				id:'page_'+ (currentPage)
			});
		}
		li.append(aBegin,aBackOne);
		ul.append(li);
		
		var from = 0;
		var to = 5;
		var offset = 0;
		
		if(pageNambers < 5){
			to = pageNambers;
		}
					
		if(currentPage > 2 && pageNambers > 5){
			if(currentPage > pageNambers - 3){
				offset = pageNambers - 2;
				from = pageNambers - 5;
				to = pageNambers;
			}else{
				offset = currentPage - 2;	
				from +=offset;
				to+=offset;
			}
		}
		
		for (var from; from < to; from++) {
			if(from == currentPage){
				var li = $('<li>',{
					class:'active'
				});
			}else{					
				var li = $('<li>');
			}
			
			var a = $('<a>',{
				text:from+1,
				id:'page_'+from
			});
			li.append(a);
			ul.append(li);
		}
		
		var li = $('<li>');
		var aEnd = $('<a>',{
			text:'>>',
			id:'page_'+(pageNambers-1)
		});
		if(currentPage < (pageNambers-1)){
			var aForwardOne = $('<a>',{
				text:'>',
				id:'page_'+ (currentPage + 1)
			});
		}else{
			var aForwardOne = $('<a>',{
				text:'>',
				id:'page_'+ (currentPage)
			});
		}
		li.append(aForwardOne,aEnd);
		ul.append(li);
	}
	

	
	function getPage(){
		$('#pagination li').click(function(event){
			var str = event.target.id;
			var id = str.substring(str.lastIndexOf('_')+1);
			currentPage = id;
			savePage();
			findAll();
			window.scrollTo(0,0);
		});
	}
		
	
	
	function updateDelete() {
		$('.updateDelete').click(function(event) {
		$('#category').children().each(function(){
			   $(this).removeAttr('selected');
		});
			var target = $(event.target);
			var id = target.attr('id');
			var text = target.val();
			
			if(text == "delete"){
				$.ajax({
					url:'/item/'+id,
					type:'DELETE',
					success:function(){
						target.parent().parent().remove();
					}
				});
			}else{
				
				$.ajax({
					url:'/item/'+id,
					type:'GET',
					success:function(item){
						$('input[name=brend]').val(item.brend);
						$('input[name=model]').val(item.model);
						$('input[name=price]').val(item.price);
						$('#category option[value=' +item.categoryId  +']').attr('selected','selected');
						
						stringProperty.empty();
						$.ajax({
							url:'/item/stringProperty/' + item.categoryId,
							type:'GET',
							success:function(stringProperty){
								var select = $('#stringProperty');
								for (var i = 0; i < stringProperty.length; i++) {
									var option = $('<option>',{
										value:stringProperty[i].id,
										text:stringProperty[i].name
									});
									select.append(option);
								
								$.ajax({
									url:'/item/stringValue/' +item.itemId +'/'+ $('#stringProperty').first().val(),
									type:'GET',
									success:function(value){
											$('#stringValue').val(value.name);
											stringValueId = value.id;
									}
								})
							}
							}
						})
										
						digitProperty.empty();
						$.ajax({
							url:'/item/digitProperty/' + item.categoryId,
							type:'GET',
							success:function(digitProperty){
								var select = $('#digitProperty');
								for (var i = 0; i < digitProperty.length; i++) {
									var option = $('<option>',{
										value:digitProperty[i].id,
										text:digitProperty[i].name
									});
									select.append(option);
									
									$.ajax({
										url:'/item/digitValue/' +item.itemId +'/'+ $('#digitProperty').first().val(),
										type:'GET',
										success:function(value){
												$('#digitValue').val(value.value);
												digitValueId = value.id;
										}
									})
								}
								
							}
						});
						
						itemId = item.itemId;
					}
				});
				
			}
		});
	}
	
	
	$.ajax({
		url:'/item/category',
		type:'GET',
		success:function(categores){
			var select = $('#category');
			for (var i = 0; i < categores.length; i++) {
				var option = $('<option>',{
					value:categores[i].id,
					text:categores[i].name
				})
				select.append(option);
			}
			
		}
	});
	
	$('#stringProperty').change(function(){
		if(itemId!=0){
		$.ajax({
			url:'/item/stringValue/' +itemId +'/'+ $(this).val(),
			type:'GET',
			success:function(value){
					$('#stringValue').val(value.name);
					stringValueId = value.id;
			},
			error:function(jqXHR, exception){
				stringValueId = 0;
		}
		
		})
		}
	})
	
	
	$('.sort').click(function(event){
		$("#sort li").removeClass("active");
		$(event.target.parentElement).attr("class","active");
		pageSort = event.target.text;
		
		savePage();
		findAll();
		
	})

	$('.size').click(function(event){
		$("#size li").removeClass("active");
		$(event.target.parentElement).attr("class","active");
		pageSize = event.target.text;
		savePage();
		findAll();
	})
	
	$('#digitProperty').change(function(){
		if(itemId!=0){
		$.ajax({
			url:'/item/digitValue/' +itemId +'/'+ $(this).val(),
			type:'GET',
			success:function(value){
					$('#digitValue').val(value.value);
					digitValueId = value.id;
			},
				error:function(jqXHR, exception){
					digitValueId = 0;
			}
		})
		}
	})
	
	
	
	
	$('#category').change(function(){
		stringProperty.empty();
		digitProperty.empty();
		stringProperty.trigger("chosen:updated");
		digitProperty.trigger("chosen:updated");
		var categoryId = $(this).val();
		stringProperty.trigger("chosen:updated");
		digitProperty.trigger("chosen:updated");
		$.ajax({
			url:'/item/stringProperty/' + categoryId,
			type:'GET',
			success:function(stringProperty){
				var select = $('#stringProperty');
				for (var i = 0; i < stringProperty.length; i++) {
					var option = $('<option>',{
						value:stringProperty[i].id,
						text:stringProperty[i].name
					});
					select.append(option);
				}
			}
		})
		
		$.ajax({
			url:'/item/digitProperty/' + categoryId,
			type:'GET',
			success:function(digitProperty){
				var select = $('#digitProperty');
				for (var i = 0; i < digitProperty.length; i++) {
					var option = $('<option>',{
						value:digitProperty[i].id,
						text:digitProperty[i].name
					});
					select.append(option);
				}
			}
		});
		
	});
	
	$('#addString').click(function(){
		var input = $('#stringValue');
	
			$('#stringProperty').removeAttr("style");
			var select = $('#stringProperty');
			var selectedOption = $('#stringProperty option[value="' + select.val() + '"]');
			stringArray.push(stringValue(stringValueId,input.val(),selectedOption.text()));
			selectedOption.remove();
			if(itemId !=0 && select.first().val() !=null){
				$.ajax({
					url:'/item/stringValue/' +itemId +'/'+ select.first().val(),
					type:'GET',
					success:function(value){
						input.val(value.name);
						stringValueId = value.id;
					},
					error: function (jqXHR, exception) {
				  		input.val('');
				  	}
				})
			}else{
				input.val('');				
			}
	})
	
	
	$('#addDigit').click(function(){
		var input = $('#digitValue');
		var regExp = /^(?:[1-9]\d*|\d)$/;
		
		if(regExp.test(input.val())){
			$('#digitProperty').removeAttr("style");
			var select = $('#digitProperty');
			var selectedOption = $('#digitProperty option[value="' + select.val() + '"]');
			digitArray.push(digitValue(digitValueId,input.val(), selectedOption.text()));
			selectedOption.remove();
			if(itemId !=0 && select.first().val() !=null){
			$.ajax({
				url:'/item/digitValue/' +itemId +'/'+ $('#digitProperty').first().val(),
				type:'GET',
				success:function(value){
						$('#digitValue').val(value.value);
						digitValueId = value.id;
				},
			  	error: function (jqXHR, exception) {
			  		input.val('');
			  	}
			})
			}else{
				input.val('');				
			}
		}else{
			input.attr("style","border:1px solid #f00;");
		}
	});
	

	// подія на збереження фото
	$('input[type=file]').change(function(event){
	  files = event.target.files;
	});

	// Catch the form submit and upload the files
	function uploadFiles(event){
	  event.stopPropagation(); // Stop stuff happening
	  event.preventDefault(); // Totally stop stuff happening

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

	function validation(){
		var isValid = true;
		var a = $('input[name=brend]');
			if(a.val().length == 0){
				isValid = false;
				a.attr("style","border:1px solid #f00;")
			}
		
		var b = $('input[name=model]');
			if(b.val().length == 0){
				isValid = false;
				b.attr("style","border:1px solid #f00;")
			}
		
		var c = $('input[name=price]');
			if(c.val().length == 0){
				isValid = false;
				c.attr("style","border:1px solid #f00;")
			}else{
				var regExp = /^(?:[1-9]\d*|\d)$/;
				console.log(regExp.test(c.val()));
				if(!regExp.test(c.val())){
					isValid = false;	
					c.attr("style","border:1px solid #f00;")
				}
			}	
		var d = $('#category');
			if(parseInt(d.val()) < 1){
				isValid = false;
				d.attr("style","border:1px solid #f00;")
		}
		 
		var e = stringArray;
			if(e.length < 1 ){
				isValid = false;
				$('#stringProperty').attr("style","border:1px solid #f00;");
			}
			
		var f = digitArray;
			if(f.length < 1 ){
				isValid = false;
				$('#digitProperty').attr("style","border:1px solid #f00;");
			}		
				
		var g = $('#file');
		if(!(g.val().length > 1)){
			g.attr("style","border:1px solid #f00;");
			isValid = false;
		}	
			
		return isValid;
	} 
	
	$('input, select').click(function(event){
		$(event.target).removeAttr("style");
	});

	$('#submit').click(function(event) {
		
		
		
			uploadFiles(event);
			filename = $('input[type=file]').val();
			 if (filename.substring(3,11) == 'fakepath') {
		            filename = filename.substring(12);
			 }
			event.preventDefault();
			console.log($('#category option:selected').val());
			var json = JSON.stringify({
				itemId:itemId,
				brend:$('input[name=brend]').val(),
				model:$('input[name=model]').val(),
				price:$('input[name=price]').val(),
				path:filename,
				categoryId:$('#category option:selected').val(),
				stringValue:stringArray,				
				digitValue:digitArray					
				
			});

			if(itemId == 0){
				
				var isValid = validation();
				if(isValid){
					
					
				
				$.ajax({
					url:'/item',
					type:'POST',
					data:json,
					contentType:"application/json",
					success:function(){
						
						brend:$('input[name=brend]').val('');
						model:$('input[name=model]').val('');
						price:$('input[name=price]').val('');
						$('#digitValue').val('');
						$('#stringValue').val('');
						$('input[type=file]').val('');
						stringProperty.empty();
						digitProperty.empty();
						
						path = '';
						categoryId = '';
						stringArray = [];				
						digitArray	= [];	
						itemId = 0;
						findAll();					
					}
				});
				}else{
					console.log("NOT Valid");
				}
			}else{
				$.ajax({
					url:'/item',
					type:'PUT',
					data:json,
					contentType:"application/json",
					success:function(){
						
						brend:$('input[name=brend]').val('');
						model:$('input[name=model]').val('');
						price:$('input[name=price]').val('');
						$('input[type=file]').val('');
						$('#digitValue').val('');
						$('#stringValue').val('');
						stringProperty.empty();
						digitProperty.empty();
						
						path = '';
						categoryId = '';
						stringArray = [];				
						digitArray	= [];	
						itemId = 0;
						findAll();
					}
				});
			}
	});
});
</script>

<div class="row-fluid">
				<nav class="navbar navbar-default" style="margin-top: 80px;">
					<div class="container-fluid">
						<div class="collapse navbar-collapse" id="">
							<ul class="nav navbar-nav">
								<li  ><a href="/admin/category">Category</a></li>
								<li><a href="/admin/stringProperty">String Prperty</a></li>
								<li><a href="/admin/stringValue">String value</a></li>
								<li ><a href="/admin/digitValue">Digit value</a></li>
								<li><a href="/admin/digitProperty">Digit property</a></li>
								<li class = "active"><a href="/admin/item">Item</a><span
										class="sr-only">(current)</span></li>
							</ul>
						</div>
					</div>
				</nav>
		</div>

<h1>Item</h1>
<div class="row"> 
	<div class="col-md-2 col-md-offset-9">
		<div class="col-md-6 ">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" >Sort
				 <span class="caret"></span></button>
				<ul class="dropdown-menu" id="sort">
					<li><a class="sort">model asc</a></li>
					<li><a class="sort">model desc</a></li>
				</ul>
			</div>
		</div>
				<div class="col-md-6 ">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Page size
				 <span class="caret"></span></button>
				<ul class="dropdown-menu" id="size">
					<li><a class="size">6</a></li>
					<li><a class="size">12</a></li>
					<li><a class="size">24</a></li>
				</ul>
			</div>
		</div>
	</div>	
</div>

<form class="form-inline" id="itemForm">
<div class="row">
	<div class="col-md-3">
		<input placeholder="brend" name="brend" class="form-control">
		<input placeholder="model" name="model" class="form-control">
		<input placeholder="prise" name="price" class="form-control">	
	</div>
	
	<div class="col-md-9">
		<div class="row">
			<div class="col-md-3">
					<select id="category" class="form-control">
					<option value="0">
						category
					</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4">
				<select id="stringProperty" class="form-control">
						<option>
							str props
						</option>
					</select>
			</div>
			<div class="col-md-5">
				<input placeholder="str value" id="stringValue" class="form-control">
			</div>
			<div class="col-md-3">
				<button type="button" class="btn btn-primary" value="add" id="addString" class="form-control">add</button>
			</div>
			
		</div>
		<div class="row">
			<div class="col-md-4">
				<select id="digitProperty" class="form-control">
						<option value="0">
							digit props
						</option>
					</select>
			</div>
			<div class="col-md-5">
				<input placeholder="digit value" id="digitValue" class="form-control">
			</div>
			<div class="col-md-3">
				<button type="button" class="btn btn-primary" value="add" id="addDigit" class="form-control">add</button>
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
		<div class="row" id="content">

		</div>
		<div class="row">
			<div class="col-md-12 col-xs-12 text-center">
				<ul class='pagination' id="pagination">
				</ul>
			</div>
		</div>
</body>
</html>