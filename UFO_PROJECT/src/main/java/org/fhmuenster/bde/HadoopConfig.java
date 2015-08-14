package org.fhmuenster.bde;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;

@Configuration
@EnableHadoop
@ComponentScan(basePackages = { "org.fhmuenster.bde.service",
		"org.fhmuenster.bde.repository", "org.fhmuenster.bde.controller" })
public class HadoopConfig extends SpringHadoopConfigurerAdapter {

	@Override
	public void configure(HadoopConfigConfigurer config) throws Exception {
		config.fileSystemUri("hdfs://localhost:8020");
	}

}
