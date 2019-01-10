$(function(){
     $.ajax({
             url:"getTree.action",
             type:"post",
             dataType:"json",
             success:function(data){
   var ul="";
  
    for(var i=0;i<data.menus.length;i++){
    if(i==0){
     ul+="<li class='start active ' > <a href='' ><i class='icon-home'></i>"+data.menus[i].menuname+"</a><ul class='sub-menu'>"
   }else{
     ul+="<li> <a href='' ><i class='icon-home'></i>"+data.menus[i].menuname+"</a><ul class='sub-menu'>"
    }
       for(var j=0;j<data.menus[i].menus.length;j++){
       ul+="<li onmouseover = 'func(this)'  onmouseout='func1(this)'  onclick='lii(this)' class='aa' ><a target='main' href='http://localhost:8080/MN_HouseFund_OA/"+data.menus[i].menus[j].url.url+"'>"+data.menus[i].menus[j].menuname+"</a></li>"  
       };
       ul+="</ul></li>";
    }
  
    $(".page-sidebar-menu").append(ul);
             }
             })
});

function lii(obj){
	$(".aa").css("background","#292929");
	$(".aa").children().css("color","#bdbdbd");
  $(obj).css("background","#e02222");
  $(obj).children().css("color","white");
}
/*
function func(oo){
	$(oo).css("background","#e02222");
}*/
