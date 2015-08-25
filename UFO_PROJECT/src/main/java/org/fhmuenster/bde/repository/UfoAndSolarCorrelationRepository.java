package org.fhmuenster.bde.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.fhmuenster.bde.entity.UfoAndSolarCorrelation;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UfoAndSolarCorrelationRepository {

	@Inject
	private HbaseTemplate hbaseTemplate;

	private String tableName = "hbase_ufos_wheater";

	private String columnFamily = "a";

	// byte werte mit hbase names
	public static byte[] CF_CC = Bytes.toBytes("a");

	private byte[] qMonth = Bytes.toBytes("monat");

	private byte[] qYear = Bytes.toBytes("jahr");

	private byte[] qValue = Bytes.toBytes("anz");

	private byte[] qSolar = Bytes.toBytes("avg_solar");

	/**
	 * Liefert alle UFOs und Sonnenkraftkorrelationen aus HBase aus.
	 * 
	 * @return Liste aller UfoAndSolarCorrelation
	 */
	public List<UfoAndSolarCorrelation> findAll() {
		return hbaseTemplate.find(tableName, columnFamily,
				new RowMapper<UfoAndSolarCorrelation>() {
					@Override
					public UfoAndSolarCorrelation mapRow(Result result,
							int rowNum) throws Exception {
						// Operationen fuer int werte muessen einzeln
						// durchgefuehrt werden. Sonst wird der int wert nicht
						// uebernommen.
						String valueString = Bytes.toString(result.getValue(
								CF_CC, qValue));
						int value = Integer.valueOf(valueString);
						String solarString = Bytes.toString(result.getValue(
								CF_CC, qSolar));
						Integer solar = null;
						if (solarString != null) {
							solar = Integer.valueOf(solarString);
						}
						return new UfoAndSolarCorrelation(Bytes.toString(result
								.getValue(CF_CC, qYear)), Bytes.toString(result
								.getValue(CF_CC, qMonth)), value, solar);
					}
				});
	}

}
