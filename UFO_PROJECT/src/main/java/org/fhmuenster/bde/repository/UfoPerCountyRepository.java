package org.fhmuenster.bde.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.fhmuenster.bde.entity.UfoPerCounty;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UfoPerCountyRepository {

	@Inject
	private HbaseTemplate hbaseTemplate;

	private String tableName = "hbase_ufo_county";

	private String columnFamily = "a";

	// byte werte mit hbase names
	public static byte[] CF_CC = Bytes.toBytes("a");

	private byte[] qCode = Bytes.toBytes("countycode");

	private byte[] qValue = Bytes.toBytes("sum_ufos");

	private byte[] qCounty = Bytes.toBytes("county");

	/**
	 * Liefert alle UFOs per County aus HBase.
	 * 
	 * @return Liste aller UfoPerCounty
	 */
	public List<UfoPerCounty> findAll() {
		return hbaseTemplate.find(tableName, columnFamily,
				new RowMapper<UfoPerCounty>() {
					@Override
					public UfoPerCounty mapRow(Result result, int rowNum)
							throws Exception {
						return new UfoPerCounty(Bytes.toString(result.getValue(
								CF_CC, qCode)), Integer.getInteger(Bytes
								.toString(result.getValue(CF_CC, qValue))),
								Bytes.toString(result.getValue(CF_CC, qCounty)));
					}
				});
	}

}
