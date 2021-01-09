<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 10.07.2020
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>

<aside class="col-12 col-sm-6 col-md-6 col-lg-2 col-xl-2">
           <form action="/searchProduct" method="get">
                <div class="d-flex flex-row justify-content-center">
                    <input class="form-control btn_search" type="text" name="fragment" placeholder="Search...">
                    <button type="submit" class="btn btn-secondary btn_search ml-1 "><i class="fas fa-search normal "></i></button>
                </div>
            </form>
    <ul class="list-group mt-4">
        <li class="list-group-item active bg-light sh-b">Filter</li>
        <li class="list-group-item" >
            <div class="form-inline justify-content-start" >
                <div class="polzunok-container-5">
                    <div class="polzunok_inner">
                        <div class="polzunok_label">Length</div>
                        <div class="polzunoky">
                            <input id="polzunok-length-min" type="text" class="polzunok-input-5-left" data-min="${lenMin}" /> -
                            <input id="polzunok-length-max" type="text" class="polzunok-input-5-right" data-max="${lenMax}" />
                        </div>
                    </div>
                    <div class="polzunok-5"></div>
                </div>

                <div class="polzunok-container-4">
                    <div class="polzunok_inner">
                        <div class="polzunok_label">Width</div>
                        <div class="polzunoky">
                            <input id="polzunok-width-min" type="text" class="polzunok-input-4-left" data-min="${widMin}  "/> -
                            <input id="polzunok-width-max" type="text" class="polzunok-input-4-right" data-max="${widMax} "/>
                        </div>
                    </div>
                    <div class="polzunok-4"></div>
                </div>

                <div class="polzunok-container-3">
                    <div class="polzunok_inner">
                        <div class="polzunok_label">Height</div>
                        <div class="polzunoky">
                            <input id="polzunok-height-min" type="text" class="polzunok-input-3-left"  data-min="${heiMin}" /> -
                            <input id="polzunok-height-max" type="text" class="polzunok-input-3-right" data-max="${heiMax}" />
                        </div>
                    </div>
                    <div class="polzunok-3"></div>
                </div>

                <div class="polzunok-container-2">
                    <div class="polzunok_inner">
                        <div class="polzunok_label">Price</div>
                        <div class="polzunoky">
                            <input id="polzunok-price-min" type="text" class="polzunok-input-2-left" data-min="${priceMin}"/> -
                            <input id="polzunok-price-max" type="text" class="polzunok-input-2-right" data-max="${priceMax}"/>
                        </div>
                    </div>
                    <div class="polzunok-2"></div>
                </div>
                <button id="showResult" class="btn btn-secondary btn_search ml-1 btn-block mt-5"><i class="fas fa-search normal"></i> Show result  <span id="filterResult"></span></button>
            </div>
        </li>
    </ul>
</aside>




