package no.imr.nmdapi.nmdcruiseseries.service;

import java.util.List;
import no.imr.commons.nmdcruiseseries.domain.v1.CruiseSerieType;
import no.imr.nmdapi.dao.file.NMDDataDao;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.generic.response.v1.ResultElementType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NMDCruiseseries service layer implementation.
 *
 * @author kjetilf
 */
public class NMDCruiseseriesServiceImpl implements NMDCruiseseriesService {

    @Autowired
    private NMDDataDao nmdDataDao;

    @Override
    public Object getData(final String name) {
        return nmdDataDao.get(name, CruiseSerieType.class);
    }

    @Override
    public void deleteData(final String name) {
        nmdDataDao.delete(name);
    }

   @Override
    public void insertData(final String name, final CruiseSerieType cruiseserie) {
        nmdDataDao.insert(name, cruiseserie, CruiseSerieType.class);
    }


    @Override
    public void updateData(final String name, final CruiseSerieType cruiseserie) {
        nmdDataDao.update(name, cruiseserie, CruiseSerieType.class);
    }

    @Override
    public ListElementType list() {
        List<String> names = nmdDataDao.listSeries();
        ListElementType elementType = new ListElementType();
        for (String name : names) {
            ResultElementType resultElementType = new ResultElementType();
            resultElementType.setResult(name);
            elementType.getElement().add(resultElementType);
        }
        return elementType;
    }

}
