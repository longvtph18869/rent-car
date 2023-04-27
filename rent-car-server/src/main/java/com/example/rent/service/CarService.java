package com.example.rent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rent.DTO.CarDTO;
import com.example.rent.entities.Car;
import com.example.rent.entities.CarImage;
import com.example.rent.entities.Location;
import com.example.rent.entities.Manufacturer;
import com.example.rent.entities.User;
import com.example.rent.enums.CarType;
import com.example.rent.repositories.CarImageRepository;
import com.example.rent.repositories.CarRepository;
import com.example.rent.repositories.LocationRepository;
import com.example.rent.repositories.ManufacturerRepository;
import com.example.rent.repositories.UserRepository;

@Service
public class CarService {
	@Autowired
	CarRepository carRepository;
	@Autowired
	ManufacturerRepository manufacturerRepository;
	@Autowired
	LocationRepository locationRepository;
	@Autowired
	UserRepository userRepository;
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

	public List<CarDTO> findByUser(Integer userId) {
		User user = userRepository.findById(userId);
		List<Car> cars = carRepository.findByUser(user);
		List<CarDTO> carDTOs = new ArrayList<>();
		for (Car car : cars) {
			CarDTO carDTO = new CarDTO();
			carDTO.setId(car.getId());
			carDTO.setLicensePlates(car.getLicensePlates());
			carDTO.setColor(car.getColor());
			carDTO.setDescription(car.getDescription());
			carDTO.setLatitude(car.getLocation().getLatitude());
			carDTO.setLongitude(car.getLocation().getLongitude());
			carDTO.setManufacturerId(car.getManufacturer().getId());
			carDTO.setName(car.getName());
			carDTO.setLocation(car.getLocation().getName());
			carDTO.setOwner(car.getUser().getId());
			carDTO.setStatus(car.getStatus());
			List<String> carImages = new ArrayList<>();
			for (CarImage image : car.getCarImage()) {
				carImages.add(image.getImage());
			}
			carDTO.setRentalPrice(car.getRentalPrice());
			carDTO.setYearOfManufacture(car.getYearOfManufacture());
			carDTO.setCarImages(carImages);
			carDTOs.add(carDTO);
		}
		return carDTOs;
	}

	public List<Manufacturer> getAllManufacturers() {
		return manufacturerRepository.findAll();
	}

	@Transactional(rollbackFor = Exception.class)
	public Car registerCar(CarDTO carDTO) throws Exception {
		try {
			// Thực hiện các thao tác trên cơ sở dữ liệu trong một transaction
			Car car = new Car();
			car.setLicensePlates(carDTO.getLicensePlates());
			car.setName(carDTO.getName());
			car.setYearOfManufacture(carDTO.getYearOfManufacture());
			car.setColor(carDTO.getColor());
			car.setType(convertToCarType(carDTO.getType()));
			car.setRentalPrice(carDTO.getRentalPrice());
			car.setDescription(carDTO.getDescription());
			car.setStatus(1);
			car.setUser(userRepository.findById(carDTO.getOwner()));
			car.setManufacturer(manufacturerRepository.findById(carDTO.getManufacturerId())
					.orElseThrow(() -> new NotFoundException()));
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
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
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
