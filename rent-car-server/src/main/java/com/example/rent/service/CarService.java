package com.example.rent.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.rent.DTO.CarDTO;
import com.example.rent.entities.Car;
import com.example.rent.entities.CarImage;
import com.example.rent.entities.Location;
import com.example.rent.entities.Manufacturer;
import com.example.rent.enums.CarType;
import com.example.rent.repositories.CarImageRepository;
import com.example.rent.repositories.CarRepository;
import com.example.rent.repositories.LocationRepository;
import com.example.rent.repositories.ManufacturerRepository;

@Service
public class CarService {
	@Autowired
	CarRepository carRepository;
	@Autowired
	ManufacturerRepository manufacturerRepository;
	@Autowired
	LocationRepository locationRepository;

	@Autowired
	CarImageRepository carImageRepository;

	public List<Car> getAllCar() {
		return carRepository.findAll();
	}

	public Optional<Car> findByID(int id) {
		return carRepository.findById(id);
	}

	public List<Car> filter(String latitude, String longitude) {
		double delta = 0.1;

		double minLat = Double.parseDouble(latitude) - delta;
		double maxLat = Double.parseDouble(latitude) + delta;
		double minLong = Double.parseDouble(longitude) - delta;
		double maxLong = Double.parseDouble(longitude) + delta;

		return carRepository.filter(minLat, maxLat, minLong, maxLong);
	}

	public List<Manufacturer> getAllManufacturers() {
		return manufacturerRepository.findAll();
	}

	public Car registeCar(CarDTO carDTO) throws IOException {
		Car car = new Car();
		car.setLicensePlates(carDTO.getLicensePlates());
		car.setName(carDTO.getName());
		car.setYearOfManufacture(carDTO.getYearOfManufacture());
		car.setColor(carDTO.getColor());
		car.setType(convertToCarType(carDTO.getType()));
		System.out.println(carDTO.getType());
		car.setRentalPrice(carDTO.getRentalPrice());
		car.setDescription(carDTO.getDescription());
		car.setStatus(true);
//        car.setUser(userRepository.findById(carDTO.getOwnerId()).orElseThrow(() -> new NotFoundException("Owner not found")));
		try {
			car.setManufacturer(manufacturerRepository.findById(carDTO.getManufacturerId())
					.orElseThrow(() -> new NotFoundException()));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		carRepository.save(car);
		Location location = new Location();
		location.setName(carDTO.getLocation());
		location.setLatitude(carDTO.getLatitude());
		location.setLongitude(carDTO.getLongitude());
		location.setStatus(true);
		location.setCar(car);
		car.setLocation(locationRepository.save(location));
		List<CarImage> carImages = new ArrayList<>();
		for (String carImage : carDTO.getCarImages()) {
			CarImage carImageNew = new CarImage();
			carImageNew.setImage(carImage);
			carImageNew.setCar(car);
			carImages.add(carImageNew);
		}
		car.setCarImage(carImageRepository.saveAll(carImages));
		return car;
	}
	public CarType convertToCarType(int type) {
	    CarType carType = null;
	    switch (type) {
	        case 2:
	            carType = CarType.TWO_SEATER;
	            break;
	        case 4:
	            carType = CarType.FOUR_SEATER;
	            break;
	        case 5:
	            carType = CarType.FIVE_SEATER;
	            break;
	        case 6:
	            carType = CarType.SIX_SEATER;
	            break;
	        case 7:
	            carType = CarType.SEVEN_SEATER;
	            break;
	        default:
	            // handle the case where the input type is not a valid CarType
	            break;
	    }
	    return carType;
	}
}
