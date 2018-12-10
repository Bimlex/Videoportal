package de.awk.videoverwaltung.facade.impl.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGen {

	public IdGen() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long twepoch = 1288834974657L;
	private static final long sequenceBits = 17;
	private static final long sequenceMax = 65536;
	private static volatile long lastTimestamp = -1L;
	private static volatile long sequence = 0L;

	public static synchronized Long generateLongId() {
		long timestamp = System.currentTimeMillis();
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) % sequenceMax;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		Long id = ((timestamp - twepoch) << sequenceBits) | sequence;
		return id;
	}

	public static long tilNextMillis(long lastTimestamp) {
		long timestamp = System.currentTimeMillis();
		while (timestamp <= lastTimestamp) {
			timestamp = System.currentTimeMillis();
		}
		return timestamp;
	}

	public String unique_id() {
		long longPlus = generateLongId();
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		String fullDate = dateFormat.format(new Date()) +longPlus ;
		System.out.println(fullDate);
		return fullDate;
	}

}
