package br.com.contratei.service.impl;

import br.com.contratei.dto.*;
import br.com.contratei.entity.ConsumerUserEntity;
import br.com.contratei.entity.ProviderUserEntity;
import br.com.contratei.repository.ConsumerUserRepository;
import br.com.contratei.repository.ProviderUserRepository;
import br.com.contratei.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private ConsumerUserRepository consumerUserRepository;

    @Autowired
    private ProviderUserRepository providerUserRepository;

    @Lazy
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<ConsumerUserEntity> consumerUserEntityList = consumerUserRepository.findByEmail(email);
        List<ProviderUserEntity> providerUserEntityList = providerUserRepository.findByEmail(email);
        if (consumerUserEntityList.size() == 0 && providerUserEntityList.size() == 0) {
            throw new UsernameNotFoundException("User details not found for the user : " + email);
        } else if (consumerUserEntityList.size() != 0 && providerUserEntityList.size() == 0) {
            return new CustomUserDetails(consumerUserEntityList.get(0), null);
        } else {
            return new CustomUserDetails(null, providerUserEntityList.get(0));
        }
    }

    public ConsumerUserEntity createNewConsumerUser(ConsumerUserDto usuario) {
        usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        return consumerUserRepository.save(new ConsumerUserEntity(usuario));
    }

    public ProviderUserEntity createNewProviderUser(ProviderUserDto usuario) {
        usuario.setPassword(bcryptEncoder.encode(usuario.getPassword()));
        return providerUserRepository.save(new ProviderUserEntity(usuario));
    }

    public AuthenticationResponseDto createAuthenticationToken(AuthenticationRequestDto authenticatioRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticatioRequest.getEmail(), authenticatioRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect e-mail or password", e);
        }
        final UserDetails userDetails = loadUserByUsername(authenticatioRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponseDto(jwt);
    }

    public UserDto findUserByEmail(String email) {
        List<ConsumerUserEntity> consumerUserEntityList = consumerUserRepository.findByEmail(email);
        List<ProviderUserEntity> providerUserEntityList = providerUserRepository.findByEmail(email);
        if (!consumerUserEntityList.isEmpty() && providerUserEntityList.isEmpty()) {
            return new UserDto(consumerUserEntityList.get(0));
        } else {
            return new UserDto(providerUserEntityList.get(0));
        }
    }
}
