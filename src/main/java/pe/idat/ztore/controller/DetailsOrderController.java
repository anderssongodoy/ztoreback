package pe.idat.ztore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.ztore.model.DetailsOrder;
import pe.idat.ztore.service.impl.DetailsOrderServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/details-order")
public class DetailsOrderController {

private final DetailsOrderServiceImpl detailsOrderService;
	
	@Autowired
	public DetailsOrderController(DetailsOrderServiceImpl detailsOrderService) {
		this.detailsOrderService = detailsOrderService;
	}

    @PostMapping("")
    public ResponseEntity<DetailsOrder> saveDetailsOrder(@RequestBody DetailsOrder detailsOrder) {
        try {
            if (detailsOrder == null) {
                return ResponseEntity.badRequest().body(null);
            }

            DetailsOrder savedDetailsOrder = detailsOrderService.save(detailsOrder);
            return ResponseEntity.ok(savedDetailsOrder);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<DetailsOrder> getDetailsOrderById(@PathVariable Long id) {
        try {
            DetailsOrder detailsOrder = detailsOrderService.findById(id);
            return ResponseEntity.ok(detailsOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<DetailsOrder>> getAllDetailsOrders() {
        try {
            List<DetailsOrder> detailsOrders = detailsOrderService.getAll();
            return ResponseEntity.ok(detailsOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
