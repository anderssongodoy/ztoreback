package pe.idat.ztore.auth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.idat.ztore.dto.JwtRequest;
import pe.idat.ztore.dto.JwtResponse;
import pe.idat.ztore.model.Customer;
import pe.idat.ztore.repository.CustomerRepository;
import pe.idat.ztore.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService{

	@Autowired
	private CustomerRepository userReop;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception{
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);
		final UserDetails userDetaisl = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetaisl);
		Customer user = userReop.findByEmail(userName).get();
		return new JwtResponse(user, newGeneratedToken);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = userReop.findByEmail(username).get();
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
		} else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(Customer user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		});
		return authorities;
	}
	
	private void authenticate(String userName, String userPassword) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("User is disabled");
		} catch(BadCredentialsException e) {
			throw new Exception("Bad credentials from user");
		}
	}
	
}