package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyMessage;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyMessageWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyMessage myMessage;

	private MyBeanWithDependency myBeanWithDependency;

	private MyMessageWithDependency myMessageWithDependency;

	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyMessage myMessage, MyMessageWithDependency myMessageWithDependency){
		this.componentDependency=componentDependency;
		this.myBean=myBean;
		this.myBeanWithDependency=myBeanWithDependency;
		this.myMessage=myMessage;
		this.myMessageWithDependency=myMessageWithDependency;
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
	}
}
