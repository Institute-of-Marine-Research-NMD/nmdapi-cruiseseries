package no.imr.nmdapi.nmdcruiseseries.service.config;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author kjetilf
 */
public class TestNMDCruiseseriesServiceConfig {

    private NMDCruiseseriesServiceConfig config = new NMDCruiseseriesServiceConfig();

    @Test
    public void testGetNMDCruiseService() {
        assertNotNull(config.getNMDCruiseService());
    }

}
