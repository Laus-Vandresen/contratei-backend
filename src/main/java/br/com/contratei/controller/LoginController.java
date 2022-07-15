package br.com.contratei.controller;

import br.com.contratei.dto.AuthenticationRequestDto;
import br.com.contratei.dto.AuthenticationResponseDto;
import br.com.contratei.dto.ConsumerUserDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticatioRequest) throws Exception {
        AuthenticationResponseDto authenticationResponse = userDetailsService.createAuthenticationToken(authenticatioRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/create-consumer-user")
    public void createNewConsumerUser(@RequestBody ConsumerUserDto user) {
        this.userDetailsService.createNewConsumerUser(user);
    }

    @PostMapping("/create-provider-user")
    public void createNewProviderUser(@RequestBody ProviderUserDto user) {
        this.userDetailsService.createNewProviderUser(user);
    }
}
