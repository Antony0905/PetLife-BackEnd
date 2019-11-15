package br.com.matheus.resources;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.domain.Week;

@RestController
public class WeekResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/getCurrentWeek", produces = MediaType.APPLICATION_JSON_VALUE)
	public Week getAgendaByUserId() {

		logger.info("Resgatando Current Week!");
		Week week = new Week();
		Date date2 = new Date();
		
		System.out.println("Date: " + date2);
		
		LocalDate date = new LocalDate(date2);
		LocalDate date3 = new LocalDate();

		System.out.println("LocalDate: " + date);
		System.out.println("LocalDate new: " + date3);



		try {

			DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
			for (int i = 0; i < 7; i++) {

				String currentDateString = formatter.print(date.plusDays(i));
				LocalDate currentDate = LocalDate.parse(currentDateString, formatter);

				String dayOfWeek = currentDate.dayOfWeek().getAsText();
				
				System.out.println("Time: " + i);
				System.out.println("DayOfWeek: " + dayOfWeek);

				switch (dayOfWeek) {
				case "Domingo":

					week.setSunday(currentDateString);
					break;
				case "Segunda-feira":

					week.setMonday(currentDateString);
					break;
				case "Terça-feira":

					week.setTuesday(currentDateString);
					break;
				case "Quarta-feira":

					week.setWednesday(currentDateString);
					break;
				case "Quinta-feira":

					week.setThursday(currentDateString);
					break;
				case "Sexta-feira":

					week.setFriday(currentDateString);
					break;
				case "Sábado":

					week.setSaturday(currentDateString);
					break;
				default:
					break;
				}

			}
			return week;
		} catch (Exception e) {
			logger.info("Ocorreu erro ao recuperar Current Week. " + e);
			return new Week();
		}

	}

}
