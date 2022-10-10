package br.com.contratei.controller;

import br.com.contratei.dto.*;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.ProviderService;
import br.com.contratei.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginServiceImpl userDetailsService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private CommentService commentService;

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

    @GetMapping("/find-photos-provider")
    public List<PhotoDto> findPhotosProvider(@RequestParam int providerId) {
        return providerService.findPhotosProvider(providerId);
    }

    @GetMapping("/find-provider")
    public Page<ProviderUserDto> find(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) ServiceTypeEnum serviceType, @RequestParam(required = false) Integer consumerId) {
        return providerService.findPageable(page, size, serviceType, consumerId);
    }

    @GetMapping("/find-provider-by-id")
    public ProviderUserDto findById(@RequestParam int id) {
        return providerService.findById(id);
    }

    @GetMapping("/find-comment-by-provider")
    public Page<CommentDto> findByProviderId(@RequestParam int page, @RequestParam int size, @RequestParam int providerId) {
        return commentService.findByProviderId(page, size, providerId);
    }
}
