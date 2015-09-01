package no.imr.nmdapi.nmdcruiseseries.service;

import java.io.File;
import java.util.List;
import no.imr.commons.nmdcruiseseries.domain.v1.CruiseSerieType;
import no.imr.nmdapi.dao.file.NMDSeriesReferenceDao;
import no.imr.nmdapi.exceptions.BadRequestException;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.generic.response.v1.ResultElementType;
import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NMDCruiseseries service layer implementation.
 *
 * @author kjetilf
 */
public class NMDCruiseseriesServiceImpl implements NMDCruiseseriesService {

    /**
     * Data type.
     */
    private static final String TYPE = "cruiseseries";

    @Autowired
    private NMDSeriesReferenceDao seriesReferenceDao;

    @Autowired
    private Configuration configuration;

    @Override
    public Object getData(final String name) {
        return seriesReferenceDao.get(name, CruiseSerieType.class.getPackage().getName());
    }

    @Override
    public void deleteData(final String name) {
        seriesReferenceDao.delete(TYPE, name, true);
    }

   @Override
    public void insertData(final String name, final CruiseSerieType cruiseserie) {
        if (!name.equalsIgnoreCase(cruiseserie.getCruiseseriename())) {
            throw new BadRequestException("Cruiseserie name is not equal to value in the data.");
        }
        String readRole = configuration.getString("default.readrole");
        String writeRole = configuration.getString("default.writerole");
        String owner = configuration.getString("default.owner");
        seriesReferenceDao.insert(writeRole, readRole, owner, TYPE, name, cruiseserie, true);
    }

    @Override
    public void updateData(final String name, final CruiseSerieType cruiseserie) {
        seriesReferenceDao.update(name, cruiseserie);
    }

    @Override
    public ListElementType list() {
        List<String> names = seriesReferenceDao.list();
        ListElementType elementType = new ListElementType();
        for (String name : names) {
            ResultElementType resultElementType = new ResultElementType();
            resultElementType.setResult(name);
            elementType.getElement().add(resultElementType);
        }
        return elementType;
    }

}
