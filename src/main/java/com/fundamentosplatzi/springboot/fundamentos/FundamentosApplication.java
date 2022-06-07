package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyMessage myMessage, MyMessageWithDependency myMessageWithDependency, MyBeanWithProperties myBeanWithProperties,UserPojo userPojo){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myMessage=myMessage;
		this.myMessageWithDependency=myMessageWithDependency;
		this.myBeanWithProperties=myBeanWithProperties;
		this.userPojo=userPojo;
	}
	public static void main(String[] args) {

		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
