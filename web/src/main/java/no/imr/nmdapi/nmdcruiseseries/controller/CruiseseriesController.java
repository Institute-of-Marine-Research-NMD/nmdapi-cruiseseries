package no.imr.nmdapi.nmdcruiseseries.controller;

import no.imr.framework.logging.slf4j.aspects.stereotype.ArgumentLogging;
import no.imr.framework.logging.slf4j.aspects.stereotype.PerformanceLogging;
import no.imr.nmd.commons.cruiseseries.domain.v1.CruiseSerieType;
import no.imr.nmd.commons.dataset.jaxb.DatasetType;
import no.imr.nmd.commons.dataset.jaxb.DatasetsType;
import no.imr.nmdapi.generic.response.v1.ListElementType;
import no.imr.nmdapi.nmdcruiseseries.service.NMDCruiseseriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller object for mission requests.
 *
 * @author kjetilf
 */
@Controller
public class CruiseseriesController {

    /**
     * Url part that defines it as mission.
     */
    public static final String CRUISESERIES_URL = "/cruiseseries";

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CruiseseriesController.class);

    /**
     * Service layer object for nmd mission queries.
     */
    @Autowired
    private NMDCruiseseriesService nmdCruiseseriesService;

    /**
     * Get data for cruiseserie.
     *
     * @param name
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object find(@PathVariable(value = "name") String name) {
        LOGGER.info("Start CruiseController.find");
        return nmdCruiseseriesService.getData(name);
    }

    /**
     * Delete cruiseserie data for mission.
     *
     * @param name
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable(value = "name") String name) {
        LOGGER.info("Start CruiseseriesController.delete");
        nmdCruiseseriesService.deleteData(name);
    }

    /**
     *  Insert mission data for mission.
     *
     * @param name
     * @param cruiseserieData
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void insert(@PathVariable(value = "name") String name, @RequestBody CruiseSerieType cruiseserieData) {
        LOGGER.info("Start CruiseseriesController.insert");
        nmdCruiseseriesService.insertData(name, cruiseserieData);
    }

    /**
     * Update  mission data for mission.
     *
     * @param name
     * @param cruiseserieData
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void update(@PathVariable(value = "name") String name, @RequestBody CruiseSerieType cruiseserieData) {
        LOGGER.info("Start CruiseseriesController.update");
        nmdCruiseseriesService.updateData(name, cruiseserieData);
    }

    /**
     * List all cruiseseries.
     *
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ListElementType list() {
        LOGGER.info("Start CruiseseriesController.list");
        return nmdCruiseseriesService.list();
    }

    /**
     * List all dataset information.
     *
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(params = "dataset",value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DatasetsType listDatasets() {
        LOGGER.info("Start CruiseseriesController.listDatasets");
        return nmdCruiseseriesService.listDatasets();
    }

    /**
     * Update dataset information.
     *
     * @param dataset
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(params = "dataset",value = "/", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateDatasetInfo(@RequestBody DatasetType dataset) {
        LOGGER.info("Start CruiseseriesController.updateDatasetInfo");
        nmdCruiseseriesService.updateDatasets(dataset);
    }

    /**
     * Get data.
     *
     * @param name
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, params = {"type=info"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object findInfo(@PathVariable(value = "name") String name) {
        LOGGER.info("Start CruiseseriesController.findInfo");
        return nmdCruiseseriesService.getInfo(name);
    }

}

