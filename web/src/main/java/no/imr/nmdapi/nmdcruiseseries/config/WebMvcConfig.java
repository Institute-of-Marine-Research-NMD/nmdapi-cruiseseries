package no.imr.nmdapi.nmdcruiseseries.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import java.net.URL;
import java.util.List;
import javax.xml.bind.JAXBException;
import no.imr.nmdapi.common.jaxb.converters.JAXBHttpMessageConverter;
import no.imr.nmdapi.nmdcruiseseries.converters.mapper.CruiseseriesNamespacePrefixMapper;
import no.imr.nmdapi.nmdcruiseseries.converters.mapper.DatasetNamespacePrefixMapper;
import no.imr.nmdapi.nmdcruiseseries.converters.mapper.ResponseNamespacePrefixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author kjetilf
 *
 * This is the configuration class for the MVC.
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

    /**
     * Configures the content negotiation.
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
                configurer.favorPathExtension(false).
                favorParameter(true).
                ignoreAcceptHeader(true).
                parameterName("format").
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_XML).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }

    /**
     * Override the defualt converters as we cannot control them and adds json
     * and xml converters.
     *
     * @param converters Default converters.
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(getMappingJacksonHttpMessageConverter());
        converters.add(getCruiseseriesMappingJaxBHttpMessageConverter());
        converters.add(getDatasetMappingJaxBHttpMessageConverter());
        converters.add(getResponseMappingJaxBHttpMessageConverter());
    }

    /**
     * Create the json converter.
     *
     * @return The json converter.
     */
    @Bean(name = "mappingJacksonHttpMessageConverter")
    public HttpMessageConverter getMappingJacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setPrettyPrint(true);
        converter.getObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return converter;
    }

    /**
     * Creates the xml converter for nmdechosounder.
     *
     * @return The xml converter.
     */
    @Bean(name = "jaxbMissionMessageConverter")
    public HttpMessageConverter getCruiseseriesMappingJaxBHttpMessageConverter() {
        JAXBHttpMessageConverter converter = null;
        try {
            URL schemaFile = Thread.currentThread().getContextClassLoader().getResource("cruiseseries.xsd");
            converter = new JAXBHttpMessageConverter(new CruiseseriesNamespacePrefixMapper(), false, schemaFile,
                    "no.imr.nmd.commons.cruiseseries.domain.v1");
        } catch (JAXBException ex) {
            LOGGER.error("Error creating message converter.", ex);
        }
        return converter;
    }

    private HttpMessageConverter<?> getResponseMappingJaxBHttpMessageConverter() {
        JAXBHttpMessageConverter converter = null;
        try {
            converter = new JAXBHttpMessageConverter(new ResponseNamespacePrefixMapper(), false,
                    "no.imr.nmdapi.generic.response.v1");
        } catch (JAXBException ex) {
            LOGGER.error("Error creating message converter.", ex);
        }
        return converter;
    }

    /**
     * Creates the xml converter for nmddataset
     *
     * @return The xml converter.
     */
    @Bean(name = "jaxbDatasetMessageConverter")
    public HttpMessageConverter getDatasetMappingJaxBHttpMessageConverter() {
        JAXBHttpMessageConverter converter = null;
        try {
            converter = new JAXBHttpMessageConverter(new DatasetNamespacePrefixMapper(), false, "no.imr.nmd.commons.dataset.jaxb");
        } catch (JAXBException ex) {
            LOGGER.error("Error creating message converter.", ex);
        }
        return converter;
    }

}
