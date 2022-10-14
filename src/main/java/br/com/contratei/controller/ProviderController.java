package br.com.contratei.controller;

import br.com.contratei.dto.*;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.service.AddressService;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/name")
    public List<ProviderUserDto> findByName(@RequestParam String prefix) {
        return service.findByName(prefix);
    }

    @GetMapping("/service-type")
    public List<ProviderUserDto> findByServiceType(@RequestParam ServiceTypeEnum serviceType) {
        return service.findByServiceType(serviceType);
    }

    @GetMapping("/find-by-id")
    public ProviderUserDto findById(@RequestParam int id) {
        var providerEntity = service.findById(id);
        return new ProviderUserDto(providerEntity);
    }

    @GetMapping()
    public Page<ProviderUserDto> find(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) ServiceTypeEnum serviceType, @RequestParam int consumerId) {
        return service.findPageable(page, size, serviceType, consumerId);
    }

    @PutMapping("/{providerId}")
    public ProviderUserDto changeProviderUser(@PathVariable  int providerId, @RequestBody CoreProviderDto coreProvider) {
        return service.changeProviderUser(providerId, coreProvider);
    }

    @PutMapping("/change-photos/{providerId}")
    public void changeProviderUser(@PathVariable  int providerId, @RequestBody PhotosDto photos) {
        service.changeProviderPhotos(providerId, photos.getPhotos());
    }

    @GetMapping("/find-comment-by-provider")
    public Page<CommentDto> findByProviderId(@RequestParam int page, @RequestParam int size, @RequestParam int providerId) {
        return commentService.findByProviderId(page, size, providerId);
    }

    @GetMapping("/find-photos-provider")
    public List<PhotoDto> findPhotosProvider(@RequestParam int providerId) {
        return service.findPhotosProvider(providerId);
    }

    @GetMapping("/find-main-address")
    public AddressDto findMainAddres(@RequestParam int providerId) {
        return addressService.findByProviderIdAndMainIsTrue(providerId);
    }

    @GetMapping("/random-providers")
    public List<ProviderUserDto> findRandomProviders() {
        return service.findRandomProviders();
    }

    @GetMapping("/new-providers")
    public List<ProviderUserDto> findNewProviders() {
        return service.findNewProviders();
    }
}
