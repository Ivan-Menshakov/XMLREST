package ru.ivan.xmlrest.webservise;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ivan.xmlrest.dataservise.DataServise;
import ru.ivan.xmlrest.model.RequestModel;

import java.util.List;


@RestController
@RequestMapping("/")
public class RestServise {
    Logger logger = LogManager.getLogger(RestServise.class);


    @Autowired
    DataServise dataServise;

    @RequestMapping (method = RequestMethod.POST, value = "/test")
    public List<Integer> addBox (@RequestBody RequestModel requestModel) {
        logger.debug("call URL /test");
        logger.debug("boxId: " + requestModel.getBox());
        logger.debug("colorItem: " + requestModel.getColor());
        List<Integer> result = dataServise.getItems(requestModel.getBox(), requestModel.getColor());
        logger.debug("result: " + result);
        return result;
    }
}
