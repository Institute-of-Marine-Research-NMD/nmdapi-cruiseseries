package no.imr.nmdapi.nmdcruiseseries.converters.mapper;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 *
 * @author kjetilf
 */
public class CruiseseriesNamespacePrefixMapper extends NamespacePrefixMapper {

    public static final String BIOTIC_NS = "http://www.imr.no/formats/nmdcruiseseries/v1";

    @Override
    public String getPreferredPrefix(String namespaceUri,
                               String suggestion,
                               boolean requirePrefix) {
        return "";
    }

}
