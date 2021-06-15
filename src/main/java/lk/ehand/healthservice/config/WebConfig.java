package lk.ehand.healthservice.config;

import lk.ehand.healthservice.domain.DoctorAppoinmentSession;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@PropertySource({ "classpath:application-${spring.profiles.active}.yml" })
public class    WebConfig {

    @Bean(name = "doctorSession")
    @Scope("prototype")
    public DoctorAppoinmentSession getDoctorAppoinmentSession(){
        return new DoctorAppoinmentSession();
    }

    @Bean(name = "modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
