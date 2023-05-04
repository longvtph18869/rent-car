package com.example.rent.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rent.DTO.CarDTO;
import com.example.rent.DTO.RentCarDTO;
import com.example.rent.entities.Car;
import com.example.rent.entities.CarImage;
import com.example.rent.entities.RentCar;
import com.example.rent.entities.User;
import com.example.rent.repositories.CarRepository;
import com.example.rent.repositories.RentCarRepository;
import com.example.rent.repositories.UserRepository;

@Service
public class RentCarService {
	@Autowired
	private RentCarRepository rentCarRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	public RentCar addRentCar(RentCarDTO rentCarDTO) {
		RentCar rentCar = new RentCar();
		rentCar.setRentalDate(rentCarDTO.getRentalDate());
		rentCar.setReturnDate(rentCarDTO.getReturnDate());
		rentCar.setPickupDate(rentCarDTO.getPickupDate());
		rentCar.setPickupLocation(rentCarDTO.getPickupLocation());
		rentCar.setReturnLocation(rentCarDTO.getReturnLocation());
		rentCar.setRentalPrice(rentCarDTO.getRentalPrice());
		rentCar.setRentalStatus(rentCarDTO.getRentalStatus());
		rentCar.setPaymentStatus(rentCarDTO.getPaymentStatus());
		User owner = userRepository.findById(rentCarDTO.getOwnerId());
		rentCar.setOwner(owner);
		;
		User user = userRepository.findById(rentCarDTO.getUserId());
		rentCar.setUser(user);
		Car car = carRepository.findById(rentCarDTO.getCar().getId()).orElse(null);
		rentCar.setCar(car);
		return rentCarRepository.save(rentCar);
	}

	public RentCarDTO findById(int id) {
		RentCar rentCar = rentCarRepository.findById(id).get();
		RentCarDTO rentCarDTO = new RentCarDTO();
		rentCarDTO.setId(rentCar.getId());
		rentCarDTO.setRentalDate(rentCar.getRentalDate());
		rentCarDTO.setReturnDate(rentCar.getReturnDate());
		rentCarDTO.setPickupDate(rentCar.getPickupDate());
		rentCarDTO.setPickupLocation(rentCar.getPickupLocation());
		rentCarDTO.setReturnLocation(rentCar.getReturnLocation());
		rentCarDTO.setRentalPrice(rentCar.getRentalPrice());
		rentCarDTO.setRentalStatus(rentCar.getRentalStatus());
		rentCarDTO.setUserId(rentCar.getUser().getId());
		rentCarDTO.setPaymentStatus(rentCar.getPaymentStatus());
		CarDTO carDTO = new CarDTO();
		carDTO.setId(rentCar.getCar().getId());
		carDTO.setLicensePlates(rentCar.getCar().getLicensePlates());
		carDTO.setColor(rentCar.getCar().getColor());
		carDTO.setDescription(rentCar.getCar().getDescription());
		carDTO.setLatitude(rentCar.getCar().getLocation().getLatitude());
		carDTO.setLongitude(rentCar.getCar().getLocation().getLongitude());
		carDTO.setManufacturerId(rentCar.getCar().getManufacturer().getId());
		carDTO.setName(rentCar.getCar().getName());
		carDTO.setLocation(rentCar.getCar().getLocation().getName());
		carDTO.setOwner(rentCar.getCar().getUser().getId());
		carDTO.setType(rentCar.getCar().getType().getValue());
		carDTO.setStatus(rentCar.getCar().getStatus());
		List<String> carImages = new ArrayList<>();
		for (CarImage image : rentCar.getCar().getCarImage()) {
			carImages.add(image.getImage());
		}
		carDTO.setRentalPrice(rentCar.getCar().getRentalPrice());
		carDTO.setYearOfManufacture(rentCar.getCar().getYearOfManufacture());
		carDTO.setCarImages(carImages);
		rentCarDTO.setCar(carDTO);
		rentCarDTO.setOwnerId(rentCar.getOwner().getId());
		return rentCarDTO;
	}

	public List<RentCarDTO> findRentalsByUser(int userId) {
		User user = userRepository.findById(userId);
		List<RentCarDTO> rentCarDTOs = new ArrayList<>();
		if (user != null) {
			List<RentCar> rentCars = rentCarRepository.findByUserOrderByRentalDateDesc(user);
			for (RentCar rentCar : rentCars) {
				RentCarDTO rentCarDTO = new RentCarDTO();
				rentCarDTO.setId(rentCar.getId());
				rentCarDTO.setRentalDate(rentCar.getRentalDate());
				rentCarDTO.setReturnDate(rentCar.getReturnDate());
				rentCarDTO.setPickupDate(rentCar.getPickupDate());
				rentCarDTO.setPickupLocation(rentCar.getPickupLocation());
				rentCarDTO.setReturnLocation(rentCar.getReturnLocation());
				rentCarDTO.setRentalPrice(rentCar.getRentalPrice());
				rentCarDTO.setRentalStatus(rentCar.getRentalStatus());
				rentCarDTO.setUserId(rentCar.getUser().getId());
				rentCarDTO.setPaymentStatus(rentCar.getPaymentStatus());
				CarDTO carDTO = new CarDTO();
				carDTO.setId(rentCar.getCar().getId());
				carDTO.setLicensePlates(rentCar.getCar().getLicensePlates());
				carDTO.setColor(rentCar.getCar().getColor());
				carDTO.setDescription(rentCar.getCar().getDescription());
				carDTO.setLatitude(rentCar.getCar().getLocation().getLatitude());
				carDTO.setLongitude(rentCar.getCar().getLocation().getLongitude());
				carDTO.setManufacturerId(rentCar.getCar().getManufacturer().getId());
				carDTO.setName(rentCar.getCar().getName());
				carDTO.setLocation(rentCar.getCar().getLocation().getName());
				carDTO.setOwner(rentCar.getCar().getUser().getId());
				carDTO.setType(rentCar.getCar().getType().getValue());
				carDTO.setStatus(rentCar.getCar().getStatus());
				List<String> carImages = new ArrayList<>();
				for (CarImage image : rentCar.getCar().getCarImage()) {
					carImages.add(image.getImage());
				}
				carDTO.setRentalPrice(rentCar.getCar().getRentalPrice());
				carDTO.setYearOfManufacture(rentCar.getCar().getYearOfManufacture());
				carDTO.setCarImages(carImages);
				rentCarDTO.setCar(carDTO);
				rentCarDTO.setOwnerId(rentCar.getOwner().getId());
				rentCarDTOs.add(rentCarDTO);
			}
		}
		return rentCarDTOs;
	}

	public List<RentCarDTO> findRentalsByOwner(int userId) {
		User user = userRepository.findById(userId);
		List<RentCarDTO> rentCarDTOs = new ArrayList<>();
		if (user != null) {
			List<RentCar> rentCars = rentCarRepository.findByOwnerOrderByRentalDateDesc(user);
			for (RentCar rentCar : rentCars) {
				RentCarDTO rentCarDTO = new RentCarDTO();
				rentCarDTO.setId(rentCar.getId());
				rentCarDTO.setRentalDate(rentCar.getRentalDate());
				rentCarDTO.setReturnDate(rentCar.getReturnDate());
				rentCarDTO.setPickupDate(rentCar.getPickupDate());
				rentCarDTO.setPickupLocation(rentCar.getPickupLocation());
				rentCarDTO.setReturnLocation(rentCar.getReturnLocation());
				rentCarDTO.setRentalPrice(rentCar.getRentalPrice());
				rentCarDTO.setRentalStatus(rentCar.getRentalStatus());
				rentCarDTO.setUserId(rentCar.getUser().getId());
				rentCarDTO.setPaymentStatus(rentCar.getPaymentStatus());
				CarDTO carDTO = new CarDTO();
				carDTO.setId(rentCar.getCar().getId());
				carDTO.setLicensePlates(rentCar.getCar().getLicensePlates());
				carDTO.setColor(rentCar.getCar().getColor());
				carDTO.setDescription(rentCar.getCar().getDescription());
				carDTO.setLatitude(rentCar.getCar().getLocation().getLatitude());
				carDTO.setLongitude(rentCar.getCar().getLocation().getLongitude());
				carDTO.setManufacturerId(rentCar.getCar().getManufacturer().getId());
				carDTO.setName(rentCar.getCar().getName());
				carDTO.setLocation(rentCar.getCar().getLocation().getName());
				carDTO.setOwner(rentCar.getCar().getUser().getId());
				carDTO.setType(rentCar.getCar().getType().getValue());
				carDTO.setStatus(rentCar.getCar().getStatus());
				List<String> carImages = new ArrayList<>();
				for (CarImage image : rentCar.getCar().getCarImage()) {
					carImages.add(image.getImage());
				}
				carDTO.setRentalPrice(rentCar.getCar().getRentalPrice());
				carDTO.setYearOfManufacture(rentCar.getCar().getYearOfManufacture());
				carDTO.setCarImages(carImages);
				rentCarDTO.setCar(carDTO);
				rentCarDTO.setOwnerId(rentCar.getOwner().getId());
				rentCarDTOs.add(rentCarDTO);
			}
		}
		return rentCarDTOs;
	}

	@Transactional
	public void updateRentalStatus(int rentalId, int rentalStatus) {
		rentCarRepository.updateRentalStatus(rentalId, rentalStatus);
	}

	public List<RentCarDTO> findByCar(int carId) {
		Car car = carRepository.findById(carId).get();
		List<RentCarDTO> rentCarDTOs = new ArrayList<>();
		if (car != null) {
			List<RentCar> rentCars = rentCarRepository.findByCar(car);
			for (RentCar rentCar : rentCars) {
				RentCarDTO rentCarDTO = new RentCarDTO();
				rentCarDTO.setId(rentCar.getId());
				rentCarDTO.setRentalDate(rentCar.getRentalDate());
				rentCarDTO.setReturnDate(rentCar.getReturnDate());
				rentCarDTO.setPickupDate(rentCar.getPickupDate());
				rentCarDTO.setPickupLocation(rentCar.getPickupLocation());
				rentCarDTO.setReturnLocation(rentCar.getReturnLocation());
				rentCarDTO.setRentalPrice(rentCar.getRentalPrice());
				rentCarDTO.setRentalStatus(rentCar.getRentalStatus());
				rentCarDTO.setUserId(rentCar.getUser().getId());
				rentCarDTO.setPaymentStatus(rentCar.getPaymentStatus());
				CarDTO carDTO = new CarDTO();
				carDTO.setId(rentCar.getCar().getId());
				carDTO.setLicensePlates(rentCar.getCar().getLicensePlates());
				carDTO.setColor(rentCar.getCar().getColor());
				carDTO.setDescription(rentCar.getCar().getDescription());
				carDTO.setLatitude(rentCar.getCar().getLocation().getLatitude());
				carDTO.setLongitude(rentCar.getCar().getLocation().getLongitude());
				carDTO.setManufacturerId(rentCar.getCar().getManufacturer().getId());
				carDTO.setName(rentCar.getCar().getName());
				carDTO.setLocation(rentCar.getCar().getLocation().getName());
				carDTO.setOwner(rentCar.getCar().getUser().getId());
				carDTO.setType(rentCar.getCar().getType().getValue());
				carDTO.setStatus(rentCar.getCar().getStatus());
				List<String> carImages = new ArrayList<>();
				for (CarImage image : rentCar.getCar().getCarImage()) {
					carImages.add(image.getImage());
				}
				carDTO.setRentalPrice(rentCar.getCar().getRentalPrice());
				carDTO.setYearOfManufacture(rentCar.getCar().getYearOfManufacture());
				carDTO.setCarImages(carImages);
				rentCarDTO.setCar(carDTO);
				rentCarDTO.setOwnerId(rentCar.getOwner().getId());
				rentCarDTOs.add(rentCarDTO);
			}
		}
		return rentCarDTOs;
	}
}
