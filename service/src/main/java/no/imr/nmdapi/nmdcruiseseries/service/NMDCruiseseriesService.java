package no.imr.nmdapi.nmdcruiseseries.service;

import no.imr.nmd.commons.cruiseseries.domain.v1.CruiseSerieType;
import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmd.commons.dataset.jaxb.DatasetsType;
import no.imr.nmdapi.generic.response.v1.ListElementType;


/**
 * Service API for cruiseseries data.
 *
 * @author kjetilf
 */
public interface NMDCruiseseriesService {

    /**
     * Get .
     *
     * @param name
     * @return
     */
    Object getData(String name);

    /**
     * Delete
     *
     * @param name
     */
    void deleteData(String name);

    /**
     * Update
     *
     * @param name
     * @param cruise
     */
    void updateData(String name, CruiseSerieType cruise);

    /**
     * Insert
     *
     * @param name
     * @param cruiseserie
     */
    void insertData(String name, CruiseSerieType cruiseserie);

    /**
     *
     * @return
     */
    ListElementType list();

    /**
     *
     * @return
     */
    DatasetsType listDatasets();

    /**
     *
     * @param dataset
     */
    void updateDatasets(DatasetType dataset);

    /**
     *
     * @param name
     * @return
     */
    Object getInfo(String name);

}
