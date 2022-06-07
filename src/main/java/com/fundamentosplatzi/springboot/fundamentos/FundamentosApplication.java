package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER= LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyMessage myMessage;

	private MyBeanWithDependency myBeanWithDependency;

	private MyMessageWithDependency myMessageWithDependency;

	private MyBeanWithProperties myBeanWithProperties;

	private UserPojo userPojo;

	private UserRepository userRepository;

	private UserService userService;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyMessage myMessage, MyMessageWithDependency myMessageWithDependency, MyBeanWithProperties myBeanWithProperties,UserPojo userPojo,UserRepository userRepository, UserService userService){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myMessage=myMessage;
		this.myMessageWithDependency=myMessageWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;
		this.userRepository=userRepository;
		this.userService=userService;
	}
	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
		saveWithErrorTransactional();
	}
	private void saveWithErrorTransactional(){
		User test1=new User("TestTransactional1","TestTransactional1@gmail.com",LocalDate.now());
		User test2=new User("TestTransactional2","TestTransactional2@gmail.com",LocalDate.now());
		User test3=new User("TestTransactional3","TestTransactional1@gmail.com",LocalDate.now());
		User test4=new User("TestTransactional4","TestTransactional4@gmail.com",LocalDate.now());
		List<User> users=Arrays.asList(test1,test2,test3,test4);
		try {
			userService.saveTransactional(users);
		}catch (Exception e){
			LOGGER.error("Esta es una excepción del metodo transaccional");
		}
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del método transaccional "+ user));
	}

	private void getInformationJpqlFromUser(){
		/*LOGGER.info("Usuario con el método findByUserEmail "+
				userRepository.findByUserEmail("juanchoramirez@gmail.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("User", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info("usuario con metodo sort: "+ user));

		userRepository.findByName("Juan")
				.stream()
				.forEach(user->LOGGER.info("usuario con query method "+ user));

		LOGGER.info("usuario con query method findByEmailAndName "+ userRepository.findByEmailAndName("juanchoramirez@gmail.com","Juan")
				.orElseThrow(()-> new RuntimeException("usuario no encontrado")));

		userRepository.findByNameLike("%J%")
				.stream()
				.forEach(user->LOGGER.info("usuario con findByNameLike "+user));

		userRepository.findByNameOrEmail("User10",null)
				.stream()
				.forEach(user->LOGGER.info("usuario con findByNameOrEmail "+user));*/

		userRepository.findByBirthDateBetween(LocalDate.of(2021,01,01),LocalDate.of(2022,07,02))
				.stream()
				.forEach(user -> LOGGER.info("usuario con intervalo de fechas: "+user));

		userRepository.findByNameContainingOrderByIdDesc("User")
				.stream()
				.forEach(user -> LOGGER.info("usuario encontrado por findByNameContainingOrderByIdDesc "+user));


		LOGGER.info("El usuario a partir del named parameter es :"+ userRepository.getAllBirthDateAndEmail(LocalDate.of(2022,06,11),"user1@gmail.com")
				.orElseThrow(()->
						new RuntimeException("No se encontro el usuario a partir del named parameter")));
}
	private void saveUsersInDataBase(){
		User user1= new User("User1","user1@gmail.com", LocalDate.of(2022,06,11));
		User user2= new User("User2","user2@gmail.com", LocalDate.of(2022,01,22));
		User user3= new User("Juan","user3@gmail.com", LocalDate.of(2022,02,3));
		User user4= new User("User4","user4@gmail.com", LocalDate.of(2022,03,4));
		User user5= new User("User5","user5@gmail.com", LocalDate.of(2022,04,5));
		User user6= new User("User6","user6@gmail.com", LocalDate.of(2022,05,7));
		User user7= new User("User7","user7@gmail.com", LocalDate.of(2022,06,8));
		User user8= new User("User8","user8@gmail.com", LocalDate.of(2022,07,14));
		User user9= new User("User9","user9@gmail.com", LocalDate.of(2022,8,16));
		User user10= new User("User10","user10@gmail.com", LocalDate.of(2022,9,21));
		User user= new User("Juan","juanchoramirez@gmail.com", LocalDate.of(2022,06,8));
		List<User> list= Arrays.asList(user,user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		myMessage.msg("tan tan");
		myMessageWithDependency.msgWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getAge()+"-"+userPojo.getPassword()+"-"+userPojo.getEmail());
		try{
			//error
			int value=10/0;
			LOGGER.debug("Mi valor: "+value);
		}catch (Exception e) {
			LOGGER.error("Esto es un error de dividir por cero");
		}
	}
}
