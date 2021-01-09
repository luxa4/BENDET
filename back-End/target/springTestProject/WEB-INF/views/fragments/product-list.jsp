
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${countProductBySearchForm != null}" >
    <div class="alert alert-dark container-fluid" role="alert">
        Found : ${countProductBySearchForm} products.
    </div>
</c:if>

<c:forEach var="product" items="${productList}">
    <div class="col-12 col-sm-12 col-md-12 col-lg-3 col-xlg-1">
        <div class="card bg-light mb-3" >
            <div class="card-header"><img src="<c:url value="${product.imageUrl}" />" class="card-img-top" alt="..."></div>
            <div class="card-body">
                <h5 class="card-title"><a href="/product/${product.id}">${product.name}</a></h5>
                <p class="card-text mb-0"> Price: ${product.price}</p>
                <p class="card-text mb-0"> Size: ${product.length}x${product.width}x${product.height} cm</p>
                <p class="card-text mb-0"> Status: ${product.status}</p>
            </div>
        </div>
    </div>
</c:forEach>