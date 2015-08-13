package org.fhmuenster.bde;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

@Configuration
@EnableHadoop
@ComponentScan
public class HadoopConfig extends SpringHadoopConfigurerAdapter {

	@Override
	public void configure(HadoopConfigConfigurer config) throws Exception {
		config.fileSystemUri("hdfs://localhost:8020");
	}

	@Bean(name = "hbaseTemplate")
	HbaseTemplate hbaseTemplate() {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/application-context.xml", HadoopConfig.class);
		context.registerShutdownHook();
		return context.getBean(HbaseTemplate.class);
	}

}
