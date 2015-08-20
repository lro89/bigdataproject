package org.fhmuenster.bde.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.fhmuenster.bde.entity.UfoAndTemperatureCorrelation;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UfoAndTemperatureCorrelationRepository {

	@Inject
	private HbaseTemplate hbaseTemplate;

	private String tableName = "ufo_and temp";

	private String columnFamily = "cc";

	// byte werte mit hbase names
	public static byte[] CF_CC = Bytes.toBytes("cc");

	private byte[] qMonth = Bytes.toBytes("month");

	private byte[] qYear = Bytes.toBytes("year");

	private byte[] qValue = Bytes.toBytes("number");

	private byte[] qTemp = Bytes.toBytes("temperature");

	/**
	 * Liefert alle UFOs und Temperaturkorrelationen aus HBase aus.
	 * 
	 * @return Liste aller UfoAndTemperatureCorrelation
	 */
	public List<UfoAndTemperatureCorrelation> findAll() {
		return hbaseTemplate.find(tableName, columnFamily,
				new RowMapper<UfoAndTemperatureCorrelation>() {
					@Override
					public UfoAndTemperatureCorrelation mapRow(Result result,
							int rowNum) throws Exception {
						return new UfoAndTemperatureCorrelation(Bytes
								.toString(result.getValue(CF_CC, qYear)), Bytes
								.toString(result.getValue(CF_CC, qMonth)),
								Integer.getInteger(Bytes.toString(result
										.getValue(CF_CC, qValue))), Integer
										.getInteger(Bytes.toString(result
												.getValue(CF_CC, qTemp))));
					}
				});
	}

}
