package by.andersen.training.weathercast.services.rabbitmq.implementation;

import by.andersen.training.weathercast.models.DirectionWind;
import by.andersen.training.weathercast.models.rabbitmq.DirectionWindRMQ;
import by.andersen.training.weathercast.rmq.ParserJsonAndAnswerRMQ;
import by.andersen.training.weathercast.services.repository.interfaces.DirectionWindService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DirectionWindParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private DirectionWindService directionWindService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        DirectionWindRMQ directionWindRMQ = gson.fromJson(json, DirectionWindRMQ.class);
        return doOperation(directionWindRMQ.getOperation(), directionWindRMQ.getDirectionWind());
    }

    private String doOperation(String operation, DirectionWind directionWind) {
        String answer = "";
        switch (operation) {
            case "SAVE": {
                directionWindService.save(directionWind);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                directionWindService.deleteById(directionWind.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                directionWindService.save(directionWind);
                answer = "true";
                return answer;
            }
            case "GETBYID": {
                DirectionWind findDirectionWind = directionWindService.findById(directionWind.getId());
                findDirectionWind.setWeatherInformations(null);
                return gson.toJson(findDirectionWind);
            }
            case "GETALL": {
                List<DirectionWind> directionWinds = directionWindService.findAll();
                for (DirectionWind currentDirectionWind : directionWinds) {
                    currentDirectionWind.setWeatherInformations(null);
                }
                return gson.toJson(directionWinds);
            }
        }
        return answer;
    }

}
