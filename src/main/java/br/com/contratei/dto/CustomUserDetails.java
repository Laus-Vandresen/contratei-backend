package br.com.contratei.dto;

import br.com.contratei.entity.ConsumerUserEntity;
import br.com.contratei.entity.ProviderUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {

    private final ConsumerUserEntity consumerUserEntity;

    private final ProviderUserEntity providerUserEntity;

    public CustomUserDetails(ConsumerUserEntity consumerUserEntity, ProviderUserEntity providerUserEntity) {
        this.consumerUserEntity = consumerUserEntity;
        this.providerUserEntity = providerUserEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        if (Objects.nonNull(consumerUserEntity)) {
            return consumerUserEntity.getPassword();
        }
        return providerUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        if (Objects.nonNull(consumerUserEntity)) {
            return consumerUserEntity.getEmail();
        }
        return providerUserEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
