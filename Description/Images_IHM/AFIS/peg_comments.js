var cPreview = (document.getElementById('cpreview')?true:false);
var pegReplyto = $("#peg_replyto").val();
if(pegReplyto) $("#c"+pegReplyto).children("small").find("a.peg_replyto").trigger("click");

function peg_selectComment(discussid,name,target) {
var myParent = document.getElementById('peg_parent');
if(myParent) myParent.removeAttribute('id');
if(target==myParent) {discussid=name='';}
if(discussid) {
	target.setAttribute('id','peg_parent');
	$("ol.comments").find(".comment").addClass("old");
	$(target).parents(".old").removeClass("old");
}
else {
	if(cPreview) $(myParent).parents(".comment").addClass("old");
	else $("ol.comments").find(".comment").removeClass("old");
}
var mySelect = document.getElementById('peg_replyto');
var mySelectFull = document.getElementById('peg_replyto_full');
if(mySelect) mySelect.value = discussid;
if(mySelectFull) {
mySelectFull.value = (discussid?name+' ('+discussid+')':'');
mySelectFull.title = (discussid?$(target).closest("div").children().not(".comments").text():null);
}
return !!discussid;
}