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

	private String tableName = "ufo_per_shape";

	// byte werte mit hbase names
	// column family
	public static byte[] CF_CC = Bytes.toBytes("cc");

	// columns
	private byte[] qCode = Bytes.toBytes("code");

	private byte[] qValue = Bytes.toBytes("number");

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
						new BinaryComparator(qShape))),
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
