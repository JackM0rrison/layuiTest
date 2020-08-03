layui.use(['element','jquery'],function(){
    var element = layui.element,
        $ = layui.jquery;
    element.on('nav(top_nav)',function(elem){
        var id = $(elem).attr('id');
        console.log(id);
        if(id == 'mailManagement'){
            $('#main_content').attr('src','main');
        }else if(id == 'messageManagement'){
            $('#main_content').attr('src','second');
        }
    })
});