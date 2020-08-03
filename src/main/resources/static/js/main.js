var layerOriginWidth;
var layerOriginHeight;
var iframeIndex;
var divOriginWidth = 100;
var divOriginHeight = 200;

layui.use(['jquery','layer','element'],function () {
    var $ = layui.jquery,
        layer = layui.layer,
        element = layui.element ;
        $('#show_layer').click(function () {
        layer.open({
            type: 2,
            area: ['70%','50%'],
            scrollbar: 'false',
            title: "test",
            content: "openPage",
            success: function(layero,index){
                // console.log(index);
                layerOriginWidth = layero.width();
                layerOriginHeight = layero.height();
                // console.log(layerOriginHeight);
                // console.log(layerOriginWidth);
                iframeIndex = index;
                layer.iframeAuto(iframeIndex);
            },
            resizing: function (layero) {
                layer.iframeAuto(iframeIndex);
                // layer.iframeAuto(iframeIndex);
                // console.log(layero.style.height);
                var iframeDiv = layer.getChildFrame('div',iframeIndex);
                var contentIframe = layer.getChildFrame('iframe',iframeIndex);
                var contentDiv = iframeDiv[0];
                var contentDiv1 = iframeDiv[1];
                var currentHeigth = layero.height();
                var currentWidth = layero.width();
                //contentDiv.style.width = parseFloat(divOriginWidth) * currentWidth / layerOriginWidth + "%";
                contentDiv.style.height = parseFloat(divOriginHeight) * currentHeigth / layerOriginHeight + 'px';
                //contentDiv1.style.width = contentDiv.style.width;
                contentDiv1.style.height = contentDiv.style.height + 'px';
                // console.log(contentDiv.style.width);
                // console.log(contentDiv.style.height);
                // console.log(contentDiv1.style.width);
                // console.log(contentDiv1.style.height);
            }
        })
    });
})