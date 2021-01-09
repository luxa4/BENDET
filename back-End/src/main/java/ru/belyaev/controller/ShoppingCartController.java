package ru.belyaev.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.belyaev.entity.ShoppingCart;
import ru.belyaev.entity.User;
import ru.belyaev.model.JsonCart;
import ru.belyaev.service.ShoppingCartService;

@RestController
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/shoppingCart")
    public ShoppingCart showShoppingCart(@RequestParam("user") User user) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartByUser(user);
        return shoppingCart;
    }

    @PutMapping("/addToShoppingCart")
    public JsonCart addToShoppingCart(@RequestParam("productId") int productId, @RequestParam("count") int count,  @RequestParam("user") User user)  {
        ShoppingCart shoppingCart = shoppingCartService.addToShoppingCart(productId, count, user);
        JsonCart jsonCart = new JsonCart(shoppingCart.getTotalCount(), shoppingCart.getTotalCost());
        return jsonCart;
    }

    @DeleteMapping(value = "/removeFromShoppingCart")
    public JsonCart deleteProductFromShoppingCart(@RequestParam("productId") int productId, @RequestParam("count") int count, @RequestParam("user") User user) {
        ShoppingCart shoppingCart = shoppingCartService.removeFromShoppingCart(productId, count, user);
        JsonCart jsonCart = new JsonCart(shoppingCart.getTotalCount(), shoppingCart.getTotalCost());
        return jsonCart;
    }
}
