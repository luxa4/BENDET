;$(function() {

    var init = function() {
        $('#addToCart').click(addProductToCart);
        $('.removeFromProductToCart').click(removeFromProductToCart);
        $('#showResult').click(showResult);

        //Sliders
        var minLen  = $('#polzunok-length-min').attr('data-min');
        var maxLen  = $('#polzunok-length-max').attr('data-max');
        var minWid  = $('#polzunok-width-min').attr('data-min');
        var maxWid  = $('#polzunok-width-max').attr('data-max');
        var minHei  = $('#polzunok-height-min').attr('data-min');
        var maxHei  = $('#polzunok-height-max').attr('data-max');
        var minPrice = $('#polzunok-price-min').attr('data-min');
        var maxPrice = $('#polzunok-price-max').attr('data-max');

        sliderFilter(5, minLen, maxLen);
        sliderFilter(4, minWid, maxWid);
        sliderFilter(3, minHei, maxHei);
        sliderFilter(2, minPrice, maxPrice);

        var i;
        for (i = 2; i < 6; i++) {
            $(".polzunok-input-" + i + "-left").val($(".polzunok-" + i).slider("values", 0));
            $(".polzunok-input-" + i + "-right").val($(".polzunok-" + i).slider("values", 1));
            $(".polzunok-input-" + i + "-left").attr("data-value", $(".polzunok-" + i).slider("values", 0));
            $(".polzunok-input-" + i + "-right").attr("data-value", $(".polzunok-" + i).slider("values", 1));
        }
    };

    function sliderFilter(number, minV, maxV) {
        $(".polzunok-"+number).slider({
            min: 0,
            max: parseFloat(maxV) + 6.00,
            values: [minV, parseFloat(maxV) + 1.00], // Default значения ползунков
            range: true,  // если было бы FALSE - был бы один полузунок
            animate: "fast",
            slide : function(event, ui) {
                $(".polzunok-input-" + number + "-left").val(ui.values[0]);
                $(".polzunok-input-" + number + "-right").val(ui.values[1]);
                $(".polzunok-input-" + number + "-left").attr("data-value", ui.values[0]);
                $(".polzunok-input-" + number + "-right").attr("data-value", ui.values[1]);
            },
            stop: function(event, ui){
                var minLen  = $('#polzunok-length-min').attr('data-value');
                var maxLen  = $('#polzunok-length-max').attr('data-value');
                var minWidth  = $('#polzunok-width-min').attr('data-value');
                var maxWidth  = $('#polzunok-width-max').attr('data-value');
                var minHeight  = $('#polzunok-height-min').attr('data-value');
                var maxHeight  = $('#polzunok-height-max').attr('data-value');
                var minPrice = $('#polzunok-price-min').attr('data-value');
                var maxPrice = $('#polzunok-price-max').attr('data-value');

                $.ajax({
                    url:'/searchButtonCount',
                    type:'GET',
                    data: {
                        minLen: minLen,
                        maxLen: maxLen,
                        minWidth: minWidth,
                        maxWidth: maxWidth,
                        minHeight: minHeight,
                        maxHeight: maxHeight,
                        minPrice: minPrice,
                        maxPrice: maxPrice,
                    },
                    success: function(jsonCart) {
                        $('#filterResult').html(' (' + jsonCart.totalCount + ' шт)');
                    },
                });
            }
         })
    };

    var addProductToCart = function(){
        var productId = $('#productId').val();
        var count = $('#count').val();

        $.ajax({
            url:'/addToShoppingCart',
            type:'GET',

            data: {
                productId: productId,
                count: count
            },
            success: function(shoppingCart) {
                $('#shoppingCartPanel').removeClass('hidden');
                $('#shoppingCart .total-count').text(shoppingCart.totalCount);
            },
        });
    };

    var removeFromProductToCart = function(){
        var btn = $(this);
        var productId = btn.attr('data-id-product');
        var count = btn.attr('data-count');


        $.ajax({
            url:'/removeFromShoppingCart',
            type:'GET',
            data: {
                productId: productId,
                count: count
            },
            success: function(shoppingCart) {
                if (shoppingCart.totalCount == 0) {
                    window.location.href = '/';
                } else {
                    $('#shoppingCart .total-count').text(shoppingCart.totalCount);
                    $('#tableTotalCost').text(shoppingCart.totalCost);
                    $('#' + productId).remove();
                }
            },
        });
    };

    var showResult = function() {
        var minLen  = $('#polzunok-length-min').attr('data-value');
        var maxLen  = $('#polzunok-length-max').attr('data-value');
        var minWidth  = $('#polzunok-width-min').attr('data-value');
        var maxWidth  = $('#polzunok-width-max').attr('data-value');
        var minHeight  = $('#polzunok-height-min').attr('data-value');
        var maxHeight  = $('#polzunok-height-max').attr('data-value');
        var minPrice = $('#polzunok-price-min').attr('data-value');
        var maxPrice = $('#polzunok-price-max').attr('data-value');

        $.ajax({
            url:'/search',
            type:'GET',
            data: {
                minLen: minLen,
                maxLen: maxLen,
                minWidth: minWidth,
                maxWidth: maxWidth,
                minHeight: minHeight,
                maxHeight: maxHeight,
                minPrice: minPrice,
                maxPrice: maxPrice,
            },
            success: function(html) {
                $('#content').html(html);
                $('#'+productId).remove();
            },
        });
    };
   
    init();
});