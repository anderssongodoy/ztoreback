package pe.idat.ztore.auth.service;

import lombok.RequiredArgsConstructor;
import pe.idat.ztore.dto.RegisterDTO;
import pe.idat.ztore.dto.RequestDTO;
import pe.idat.ztore.dto.ResponseDTO;
import pe.idat.ztore.exception.UserAlreadyCreatedException;
import pe.idat.ztore.model.UserEntity;
import pe.idat.ztore.model.enums.Role;
import pe.idat.ztore.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Transactional
    public ResponseDTO register(RegisterDTO request) {

        Optional<UserEntity> optUser = userRepository.findByUsername(request.getUsername());

        if (optUser.isPresent())
            throw new UserAlreadyCreatedException("This username has been already taken.");

        var user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Set.of(Role.ROLE_USER))
                .softDelete(false)
                .build();
        user.setSoftDelete(Boolean.FALSE);

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseDTO authenticate(RequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        //It will continue to below lines only if authentication was successful
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User with that email not found"));
        var jwtToken = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

}
