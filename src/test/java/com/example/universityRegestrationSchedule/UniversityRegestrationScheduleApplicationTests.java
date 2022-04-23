package com.example.universityRegestrationSchedule;

import com.example.universityRegestrationSchedule.Controllers.HomeController;
import com.example.universityRegestrationSchedule.Models.Courses;
import com.example.universityRegestrationSchedule.Repository.CoursesRepo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UniversityRegestrationScheduleApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webapplicationContext;

	@Autowired
    HomeController homeController;

	@Autowired
	CoursesRepo coursesRepo;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webapplicationContext).build();
	}


//	@Test
//	public void testGetHomePage() throws Exception {
//
//		mockMvc.perform(MockMvcRequestBuilders.get("/"))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType("text/html;charset=UTF-8"))
//				.andExpect(view().name("home"))
//				.andExpect(content().string(Matchers.containsString("pioneers University")))
//				.andExpect(content().string(Matchers.containsString("Science is the foundation stone for progress and development taking place in human life,\n" +
//						"    the Pioneers University (PU), which was founded in 2000.\n" +
//						"    aims to become a global university excelling in pedagogy, research,\n" +
//						"    and innovation and advancing in global standing, it has offered a wide choice of academic programs for\n" +
//						"    students who can choose from more than 40 Programs.\n" +
//						"    PU offers 50 bachelors in different programs in Medicine, Dentistry, Pharmacy,Nursing, Rehabilitation Science, Science, Agriculture, Engineering, Information Technology, Educational Sciences, law, Physical Education, Arts and Design,\n" +
//						"    International Studies, Foreign Languages.")))
//				.andExpect(content().string(Matchers.containsString("At graduate level, PU provides 18 doctoral Programs,\n" +
//						"        and 30 master programs. Since its foundation, the number of PU graduates have exceeded 30.000 graduates around the world,\n" +
//						"        and PU’s employment reputation is 87 worldwide according to QS World Universities Ranking 2018."))
//				);
//
//	}
@Test
public void testGetStudents() throws Exception {
//	Item item = new Item();
	Courses courses = new Courses("Java", "Manar","101", "11:00-12:00", "sun,tue,thu");
	courses.setName("java");
	courses.setInstructorName("Manar");
	courses.setClassRoom("101");
	courses.setTime("11:00-12:00");
	courses.setDays("sun,tue,thu");

	coursesRepo.save(courses);
//	Item item2 = new Item();
//	item2.setName("Fanta");
//	item2.setCost(BigDecimal.valueOf(30));
//	shoppingService.saveAnItem(item2);
//	shoppingService.saveAnItem(item);

	// Note all the setup above required to setup the mockMvc
	// we can easily check the status, the content type, and the view
	// to check the 'content' (the returned hmtl text) we have to use the Matchers.*
	mockMvc.perform(MockMvcRequestBuilders.get("/addcourse"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/html;charset=UTF-8"))
			.andExpect(view().name("courses"))
			.andExpect(content().string(Matchers.containsString("java")));
//			.andExpect(content().string(Matchers.containsString("Manar")))
//			.andExpect(content().string(Matchers.containsString("101")))
//			.andExpect(content().string(Matchers.containsString("11:00-12:00")))
//			.andExpect(content().string(Matchers.containsString("sun,tue,thu")));

}


}


