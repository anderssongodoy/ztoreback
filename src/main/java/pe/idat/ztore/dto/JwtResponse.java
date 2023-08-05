package pe.idat.ztore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.idat.ztore.model.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private Customer customer;
	
	private String jwtToken;
}