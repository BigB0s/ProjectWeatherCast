package by.andersen.training.weathercast.services.rabbitmq.implementation;

import by.andersen.training.weathercast.models.City;
import by.andersen.training.weathercast.models.WeatherClothing;
import by.andersen.training.weathercast.models.WeatherInformation;
import by.andersen.training.weathercast.models.rabbitmq.WeatherInformationRMQ;
import by.andersen.training.weathercast.rmq.ParserJsonAndAnswerRMQ;
import by.andersen.training.weathercast.services.repository.interfaces.CityService;
import by.andersen.training.weathercast.services.repository.interfaces.WeatherInformationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherInformationParserJsonAndAnswerRMQ implements ParserJsonAndAnswerRMQ {

    @Autowired
    private WeatherInformationService weatherInformationService;

    @Autowired
    private CityService cityService;

    private Gson gson = new Gson();

    @Override
    public String parseAndAnswer(String json) {
        WeatherInformationRMQ weatherInformationRMQ = gson.fromJson(json, WeatherInformationRMQ.class);
        return doOperation(weatherInformationRMQ.getOperation(), weatherInformationRMQ.getWeatherInformation());
    }

    private String doOperation(String operation, WeatherInformation weatherInformation) {
        String answer = "";
        switch (operation) {
            case "SAVE": {
                weatherInformationService.save(weatherInformation);
                answer = "true";
                return answer;
            }
            case "DELETE": {
                weatherInformationService.deleteById(weatherInformation.getId());
                answer = "true";
                return answer;
            }
            case "UPDATE": {
                weatherInformationService.save(weatherInformation);
                answer = "true";
                return answer;
            }
            case "GETALLWITHLAZY": {
                List<WeatherInformation> weatherInformations = weatherInformationService.findWithAllLazyAll();
                for (WeatherInformation currentWeatherInformation : weatherInformations) {
                    currentWeatherInformation.getCity().setPersonalInformations(null);
                    currentWeatherInformation.getCity().setWeatherInformations(null);
                    currentWeatherInformation.getCity().getCountry().setCities(null);
                    currentWeatherInformation.getOvercast().setWeatherInformations(null);
                    currentWeatherInformation.getWeatherCondition().setWeatherInformations(null);
                    currentWeatherInformation.getDirectionWind().setWeatherInformations(null);
                    WeatherClothing weatherClothing = currentWeatherInformation.getWeatherClothing();
                    weatherClothing.getAccessory().setWeatherClothings(null);
                    weatherClothing.getCap().setWeatherClothingList(null);
                    weatherClothing.getFootWear().setWeatherClothingList(null);
                    weatherClothing.getOuterWear().setWeatherClothings(null);
                    weatherClothing.getOuterWear().setWeatherClothings(null);
                    weatherClothing.setWeatherInformations(null);
                }
                return gson.toJson(weatherInformations);
            }
            case "GETBYID": {
                WeatherInformation findWeatherInformation = weatherInformationService.findById(weatherInformation.getId());
                findWeatherInformation.setCity(null);
                findWeatherInformation.setDirectionWind(null);
                findWeatherInformation.setOvercast(null);
                findWeatherInformation.setWeatherClothing(null);
                findWeatherInformation.setWeatherCondition(null);
                return gson.toJson(findWeatherInformation);
            }
            case "GETBYLAZYID": {
                WeatherInformation findWeatherInformation = weatherInformationService.findWithAllLazyById(weatherInformation.getId());
                findWeatherInformation.getCity().setPersonalInformations(null);
                findWeatherInformation.getCity().setWeatherInformations(null);
                findWeatherInformation.getCity().getCountry().setCities(null);
                findWeatherInformation.getOvercast().setWeatherInformations(null);
                findWeatherInformation.getWeatherCondition().setWeatherInformations(null);
                findWeatherInformation.getDirectionWind().setWeatherInformations(null);
                WeatherClothing weatherClothing = findWeatherInformation.getWeatherClothing();
                weatherClothing.getAccessory().setWeatherClothings(null);
                weatherClothing.getCap().setWeatherClothingList(null);
                weatherClothing.getFootWear().setWeatherClothingList(null);
                weatherClothing.getOuterWear().setWeatherClothings(null);
                weatherClothing.getUnderWear().setWeatherClothings(null);
                weatherClothing.setWeatherInformations(null);
                return gson.toJson(findWeatherInformation);
            }
            case "GETALL": {
                List<WeatherInformation> weatherInformations = weatherInformationService.findAll();
                for (WeatherInformation currentWeatherInformation : weatherInformations) {
                    currentWeatherInformation.setCity(null);
                    currentWeatherInformation.setDirectionWind(null);
                    currentWeatherInformation.setOvercast(null);
                    currentWeatherInformation.setWeatherClothing(null);
                    currentWeatherInformation.setWeatherCondition(null);
                }
                return gson.toJson(weatherInformations);
            }
            case "GETBYDATE": {
                List<WeatherInformation> findWeatherInformations = weatherInformationService.findLazyByDate(weatherInformation.getDate());
                for (WeatherInformation findWeatherInformation : findWeatherInformations) {
                    findWeatherInformation.getCity().setPersonalInformations(null);
                    findWeatherInformation.getCity().setWeatherInformations(null);
                    findWeatherInformation.getCity().getCountry().setCities(null);
                    findWeatherInformation.getOvercast().setWeatherInformations(null);
                    findWeatherInformation.getWeatherCondition().setWeatherInformations(null);
                    findWeatherInformation.getDirectionWind().setWeatherInformations(null);
                    WeatherClothing weatherClothing = findWeatherInformation.getWeatherClothing();
                    if (weatherClothing.getAccessory() != null) {
                        weatherClothing.getAccessory().setWeatherClothings(null);
                    }
                    if (weatherClothing.getCap() != null) {
                        weatherClothing.getCap().setWeatherClothingList(null);
                    }
                    weatherClothing.getFootWear().setWeatherClothingList(null);
                    weatherClothing.getOuterWear().setWeatherClothings(null);
                    weatherClothing.getUnderWear().setWeatherClothings(null);
                    weatherClothing.setWeatherInformations(null);
                }
                return gson.toJson(findWeatherInformations);
            }
            case "GETBYDATEANDCITY": {
                City city = cityService.findWithAllLazyById(weatherInformation.getCity().getId());
                WeatherInformation findWeatherInformation = weatherInformationService.findLazyByDateAndCity(weatherInformation.getDate(), city);
                if (findWeatherInformation == null) {
                    return "false";
                }
                findWeatherInformation.getCity().setPersonalInformations(null);
                findWeatherInformation.getCity().setWeatherInformations(null);
                findWeatherInformation.getCity().getCountry().setCities(null);
                findWeatherInformation.getOvercast().setWeatherInformations(null);
                findWeatherInformation.getWeatherCondition().setWeatherInformations(null);
                findWeatherInformation.getDirectionWind().setWeatherInformations(null);
                WeatherClothing weatherClothing = findWeatherInformation.getWeatherClothing();
                if (weatherClothing.getAccessory() != null) {
                    weatherClothing.getAccessory().setWeatherClothings(null);
                }
                if (weatherClothing.getCap() != null) {
                    weatherClothing.getCap().setWeatherClothingList(null);
                }
                weatherClothing.getFootWear().setWeatherClothingList(null);
                weatherClothing.getOuterWear().setWeatherClothings(null);
                weatherClothing.getUnderWear().setWeatherClothings(null);
                weatherClothing.setWeatherInformations(null);
                return gson.toJson(findWeatherInformation);
            }
        }
        return answer;
    }

}
