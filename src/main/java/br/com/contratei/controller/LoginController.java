package br.com.contratei.controller;

import br.com.contratei.dto.*;
import br.com.contratei.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticatioRequest) throws Exception {
        AuthenticationResponseDto authenticationResponse = userDetailsService.createAuthenticationToken(authenticatioRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/create-consumer-user")
    public void createNewConsumerUser(@RequestBody ConsumerUserDto user) {
        userDetailsService.createNewConsumerUser(user);
    }

    @PostMapping("/create-provider-user")
    public void createNewProviderUser(@RequestBody ProviderUserDto user) {
        userDetailsService.createNewProviderUser(user);
    }

    @GetMapping("/find-user")
    public UserDto findUserByEmail(@RequestParam String email) {
        return userDetailsService.findUserByEmail(email);
    }
}
