package com.sd.his.service;

import com.sd.his.configuration.AppConfigProperties;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomConfigService {

    private AppConfigProperties appConfigProperties;

    CustomConfigService(AppConfigProperties appConfigProperties){
        this.appConfigProperties = appConfigProperties;
    }

    public List<String> getConfigDetail(){
        appConfigProperties.getClientId();
        return Stream.
                of(appConfigProperties.getClientId(), appConfigProperties.getAuthServerScheme(),
                        appConfigProperties.getClientSecret()
                ).collect(Collectors.toList());
    }

}
