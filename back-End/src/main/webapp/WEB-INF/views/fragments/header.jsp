<header>
    <nav class="navbar navbar-light bg-light">
        <div class="logo">
            <a class="navbar-brand" href="<c:url value="/" />">
                Wardrobes - (${countProduct}) <i class="fas fa-campground dark"></i>
            </a>
        </div>
        <div class="userPanel">
            <c:if test="${user != null}">
                <a href="/user" class="nav_link">${user.name} (${user.email})</a>
            </c:if>
                <span id="shoppingCartPanel" class="${(shoppingCart == null || shoppingCart.shoppingCartIsEmpty()) ? 'hidden' : ''}">
                         <a id="shoppingCart" href="/shoppingCart" class="nav_link "><i class="fas fa-shopping-cart dark"></i> Cart (<span class="total-count">${shoppingCart.totalCount}</span>) </a>
                </span>
            <c:choose>
                <c:when test="${user == null}" >
                    <a href="/login" class="nav_link">LogIn</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${orderList != null}" >
                        <a href="/orders" class="nav_link">My orders</a>
                    </c:if>
                    <spring:form action="/logout" method="post" class="userlogout">
                        <button type="submit" class="nav_link btn-link ">LogOut</button>
                    </spring:form>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</header>
