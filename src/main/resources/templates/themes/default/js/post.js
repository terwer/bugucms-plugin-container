$(document).ready(function() {
    // 代码高亮
    $('pre code').each(function(i, block) {
        hljs.highlightBlock(block);
    });
});