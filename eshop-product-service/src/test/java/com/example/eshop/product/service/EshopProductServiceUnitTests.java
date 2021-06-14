package com.example.eshop.product.service;

import com.example.eshop.product.persistence.entity.Watch;
import com.example.eshop.product.persistence.repository.WatchRepository;
import com.example.eshop.product.service.dto.WatchDto;
import com.example.eshop.product.service.impl.WatchServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Base64;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

/**
 * Just single showcase.
 */
@RunWith(SpringRunner.class)
public class EshopProductServiceUnitTests {

    @TestConfiguration
    static class WatchServiceImplTestContextConfiguration {

        @MockBean
        private WatchRepository watchRepository;

        @Bean
        public WatchService watchService() {
            return new WatchServiceImpl(watchRepository, new ModelMapper());
        }
    }

    @Autowired
    private WatchService watchService;

    @Autowired
    private WatchRepository watchRepository;

    @Test
    public void whenValidWatch_thenShouldCreate() {
        String fountainPicMock = "test input";
        WatchDto watchDto = new WatchDto();
        watchDto.setTitle("title");
        watchDto.setPrice(20000);
        watchDto.setDescription("description");
        watchDto.setFountain(Base64.getEncoder().encode(fountainPicMock.getBytes()));

        ArgumentCaptor<Watch> param = ArgumentCaptor.forClass(Watch.class);
        when(watchRepository.save(any(Watch.class))).thenAnswer(returnsFirstArg());

        WatchDto created = watchService.create(watchDto);
        assertThat(created.getId() != null);
        assertThat(created.getTitle()).isEqualTo(watchDto.getTitle());
        assertThat(created.getDescription()).isEqualTo(watchDto.getDescription());
        assertThat(created.getPrice()).isEqualTo(watchDto.getPrice());
        assertThat(created.getFountain()).isEqualTo(watchDto.getFountain());
    }
}
