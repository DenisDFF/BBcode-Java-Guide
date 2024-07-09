package com.example.DF;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.perm.kefir.bbcode.BBProcessorFactory;
import ru.perm.kefir.bbcode.TextProcessor;

@Configuration
public class AppConfig {

    @Bean
    public TextProcessor bbCodeProcessor() {
        return BBProcessorFactory.getInstance().create();
    }
}
