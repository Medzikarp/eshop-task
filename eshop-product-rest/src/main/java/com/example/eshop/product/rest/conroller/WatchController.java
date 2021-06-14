package com.example.eshop.product.rest.conroller;

import com.example.eshop.product.service.dto.WatchDto;
import com.example.eshop.product.service.WatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Slf4j
public class WatchController {

    private final WatchService watchService;

    @Autowired
    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @PostMapping(value = "/watches")
    public ResponseEntity<WatchDto> addWatches(@Valid @RequestBody WatchDto watchDto) throws URISyntaxException {
        log.debug("REST request to save product : {}", watchDto);
        WatchDto newWatches = watchService.create(watchDto);
        return ResponseEntity.created(new URI("/watches/" + newWatches.getId())).body(newWatches);
    }

    @GetMapping(value = "/watches/{id}")
    public ResponseEntity<WatchDto> getWatches(@PathVariable("id") Long id) {
        log.debug("REST request to get product : {}", id);
        return ResponseEntity.ok().body(watchService.findById(id));
    }

}
