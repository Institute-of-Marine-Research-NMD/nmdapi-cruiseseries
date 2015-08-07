package no.imr.nmdapi.nmdcruiseseries.service.config;

import no.imr.nmdapi.nmdcruiseseries.service.NMDCruiseseriesService;
import no.imr.nmdapi.nmdcruiseseries.service.NMDCruiseseriesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This contains all configuration for the reference services.
 *
 * @author kjetilf
 */
@Configuration
public class NMDCruiseseriesServiceConfig {

    /**
     * Creates the service implementation.
     *
     * @return  A reference service implementation.
     */
    @Bean(name="nmdCruiseService")
    public NMDCruiseseriesService getNMDCruiseService() {
        return new NMDCruiseseriesServiceImpl();
    }

}
