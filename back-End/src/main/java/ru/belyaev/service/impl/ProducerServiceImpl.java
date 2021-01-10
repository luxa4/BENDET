package ru.belyaev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.belyaev.entity.Category;
import ru.belyaev.entity.Producer;
import ru.belyaev.repository.ProducerRepository;

import java.util.List;

@Service
public class ProducerServiceImpl {

    @Autowired
    ProducerRepository producerRepository;

    public List<Producer> getProducersByCategory(Category category) {
        return producerRepository.findProducersByCategory(category);
    }
}
