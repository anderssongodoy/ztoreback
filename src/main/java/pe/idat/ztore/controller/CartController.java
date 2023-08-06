package pe.idat.ztore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pe.idat.ztore.model.Cart;
import pe.idat.ztore.service.CartService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public ResponseEntity<List<Cart>> getListByClient() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userName = userDetails.getUsername();
        return new ResponseEntity<>(this.cartService.getListByCustomer(userName), HttpStatus.OK);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<Long> countByClient(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.cartService.getCountByCustomer(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Cart cart,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        this.cartService.addProduct(cart);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @DeleteMapping("/clean/{item_id}")
    public ResponseEntity<?> removeProduct(@PathVariable("item_id") Long id) {
        this.cartService.removeProduct(id);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
