package ru.mrsash.testtask.appsmart.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mrsash.testtask.appsmart.service.CustomerService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Override
    public String test() {
        return "ITS OK";
    }
}
