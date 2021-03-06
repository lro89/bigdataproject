package org.fhmuenster.bde.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.fhmuenster.bde.entity.UfoPerCounty;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UfoPerShapeRepository {

	@Inject
	private HbaseTemplate hbaseTemplate;

	private String tableName = "hbase_ufo_county_shapes";

	// byte werte mit hbase names
	// column family
	public static byte[] CF_CC = Bytes.toBytes("a");

	// columns
	private byte[] qCode = Bytes.toBytes("countycode");

	private byte[] qValue = Bytes.toBytes("sum_ufos");

	private byte[] qCounty = Bytes.toBytes("county");

	private byte[] qShape = Bytes.toBytes("shape");

	/**
	 * Liefert alle UFOs per County gefiltert auf eine Shape-Art aus HBase.
	 * 
	 * @return Liste aller UfoPerCounty einer bestimmten Shape-Art
	 */
	public List<UfoPerCounty> findFilterByShape(String shape) {
		return hbaseTemplate.find(tableName, new Scan(Bytes.toBytes("0"),
				new SingleColumnValueFilter(CF_CC, qShape, CompareOp.EQUAL,
						new BinaryComparator(Bytes.toBytes(shape)))),
				new RowMapper<UfoPerCounty>() {
					@Override
					public UfoPerCounty mapRow(Result result, int rowNum)
							throws Exception {
						// Operationen fuer int werte muessen einzeln
						// durchgefuehrt werden. Sonst wird der int wert nicht
						// uebernommen.
						String valueString = Bytes.toString(result.getValue(
								CF_CC, qValue));
						int value = Integer.valueOf(valueString);
						return new UfoPerCounty(Bytes.toString(result.getValue(
								CF_CC, qCode)), value, Bytes.toString(result
								.getValue(CF_CC, qCounty)));
					}
				});
	}
}
