package org.fhmuenster.bde.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.fhmuenster.bde.entity.CitiesCounties;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CitiesCountiesRepository {

	@Inject
	private HbaseTemplate hbaseTemplate;

	private String tableName = "cities_counties";

	// column family
	public static byte[] CF_CC = Bytes.toBytes("cc");

	private byte[] qCity = Bytes.toBytes("city");
	private byte[] qCounty = Bytes.toBytes("county");

	public List<CitiesCounties> findAll() {
		return hbaseTemplate.find(tableName, "cc",
				new RowMapper<CitiesCounties>() {
					@Override
					public CitiesCounties mapRow(Result result, int rowNum)
							throws Exception {
						return new CitiesCounties(Bytes.toString(result
								.getValue(CF_CC, qCity)), Bytes.toString(result
								.getValue(CF_CC, qCounty)));
					}
				});

	}
}
