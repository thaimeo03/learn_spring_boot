package com.basic.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.basic.demo.entity.Booking;
import com.basic.demo.entity.Room;
import com.basic.demo.entity.User;
import com.basic.demo.repository.RoomRepository;
import com.basic.demo.repository.UserRepository;
import com.basic.demo.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication {
	private static final int MAX_CREATED_USERS = 100;
	private static final int MAX_CREATED_ROOMS = 70;
	private static final int TRANSACTIONS_PER_THREAD = 5;

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		BookingService bookingService = context.getBean(BookingService.class);
		RoomRepository roomRepository = context.getBean(RoomRepository.class);
		UserRepository userRepository = context.getBean(UserRepository.class);

		// createFakedRoom(roomRepository);
		// createFakedUser(userRepository);

		// List<Thread> allThreads = new ArrayList<>();
		// for (long userId = 1; userId <= TRANSACTIONS_PER_THREAD; userId++) {
		// Thread t = new Thread(new BookingRunnable(bookingService, userId, 1L));
		// log.info("start thread userId={}", userId);

		// t.start();
		// allThreads.add(t);
		// }

		// for (Thread t : allThreads) {
		// t.join();
		// }
	}

	private static class BookingRunnable implements Runnable {
		private Long userId;
		private Long roomId;
		private BookingService bookingService;

		public BookingRunnable(BookingService bookingService, Long userId, Long roomId) {
			this.userId = userId;
			this.roomId = roomId;
			this.bookingService = bookingService;
		}

		@Override
		public void run() {
			// Test Optimistic Locking
			// Booking booking = bookingService.bookOptimistic(userId, roomId);

			// Test Pessimistic Locking
			Booking booking = bookingService.bookPessimistic(userId, roomId);

			log.info("userId={}, booking id={}", userId, booking.getId());
		}
	}

	private static void createFakedRoom(RoomRepository roomRepository) {
		for (long i = 1; i <= MAX_CREATED_ROOMS; i++) {
			roomRepository.save(Room.builder().name("Room " + i).available(true).build());
			log.info("created Room {}", i);
		}
	}

	private static void createFakedUser(UserRepository userRepository) {
		for (long i = 1; i <= MAX_CREATED_USERS; i++) {
			userRepository.save(User.builder().username("User" + i).password("123456").build());
			log.info("created User {}", i);
		}
	}
}
